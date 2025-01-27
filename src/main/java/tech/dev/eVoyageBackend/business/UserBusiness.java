
/*
 * Java business for entity table user
 * Created on 2023-08-30 ( Time 17:27:05 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2018 Geo. All Rights Reserved.
 */

package tech.dev.eVoyageBackend.business;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.java.Log;
import okhttp3.OkHttpClient;
import tech.dev.eVoyageBackend.dao.entity.Fonctionalite;
import tech.dev.eVoyageBackend.dao.entity.Role;
import tech.dev.eVoyageBackend.dao.entity.RoleFonctionalite;
import tech.dev.eVoyageBackend.dao.entity.User;
import tech.dev.eVoyageBackend.dao.repository.FonctionaliteRepository;
import tech.dev.eVoyageBackend.dao.repository.RoleFonctionaliteRepository;
import tech.dev.eVoyageBackend.dao.repository.RoleRepository;
import tech.dev.eVoyageBackend.dao.repository.UserRepository;
import tech.dev.eVoyageBackend.factory.RedisUser;
import tech.dev.eVoyageBackend.utils.*;
import tech.dev.eVoyageBackend.utils.contract.IBasicBusiness;
import tech.dev.eVoyageBackend.utils.contract.Request;
import tech.dev.eVoyageBackend.utils.contract.Response;
import tech.dev.eVoyageBackend.utils.dto.RoleFonctionaliteDto;
import tech.dev.eVoyageBackend.utils.dto.UserDto;
import tech.dev.eVoyageBackend.utils.dto.transformer.RoleFonctionaliteTransformer;
import tech.dev.eVoyageBackend.utils.dto.transformer.UserTransformer;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.PermissionDeniedDataAccessException;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.context.Context;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


/**
 * BUSINESS for table "user"
 *
 * @author Geo
 */
@Log
@Component
@EnableScheduling
public class UserBusiness implements IBasicBusiness<Request<UserDto>, Response<UserDto>> {

    private Response<UserDto> response;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private FonctionaliteRepository fonctionaliteRepository;
    @Autowired
    private RoleFonctionaliteRepository roleFonctionaliteRepository;

    @Autowired
    private RedisUser redisUser;

    @Autowired
    private FunctionalError functionalError;
    @Autowired
    private TechnicalError technicalError;
    @Autowired
    private ExceptionUtils exceptionUtils;
    @PersistenceContext
    private EntityManager em;
    //	@Autowired
    //	private CacheUtils cacheUtils;
    //	@Autowired
    //	private NotificationSms notificationSms;

    @Autowired
    private LdapService ldapService;

    @Autowired
    private HostingUtils hostingUtils;

    @Autowired
    private EmailService emailService;
//	@Autowired
//	private PasswordEncoder passwordEncoder;

//	@Autowired
//	private JwtTokenProvider jwtTokenProvider;


    @Autowired
    private ParamsUtils paramsUtils;
    @Autowired private Environment environment;

    private Context context;

    private SimpleDateFormat dateFormat;
    private SimpleDateFormat dateTimeFormat;
    private Map<String, String> otpStore = new ConcurrentHashMap<>();
    private Map<String, Instant> otpTimestamps = new ConcurrentHashMap<>();
    private LocalDateTime otpCodeSentAt;

    public UserBusiness() {
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    }

    private Logger slf4jLogger = LoggerFactory.getLogger(getClass());

    private static final Logger log = LoggerFactory.getLogger(UserBusiness.class);

    private final String SENDER = "Orange  - RDC";
    private final String ENTETE = "Orange - RDC";
    private final String TITRE = "Plateforme APPROVISIONNEMENT CARTE VISA";

    // String[] activeProfiles = environment.getActiveProfiles();
    // List<String> activeProfiles = Arrays.asList(environment.getActiveProfiles());

    /**
     * create User by using UserDto as object.
     *
     * @param request
     * @return response
     * @throws ParseException
     * @throws Exception
     */
    @Override
    public Response<UserDto> create(Request<UserDto> request, Locale locale) throws ParseException {
        log.info("----begin create User-----");
        Response<UserDto> response = new Response<UserDto>();
        List<User> items = new ArrayList<User>();
        List<String> activeProfiles = Arrays.asList(environment.getActiveProfiles());


        for (UserDto dto : request.getDatas()) {
            // Definir les parametres obligatoires
            Map<String, Object> fieldsToVerify = new HashMap<String, Object>();
            fieldsToVerify.put("login", dto.getLogin());
            fieldsToVerify.put("firstName", dto.getFirstName());
            fieldsToVerify.put("lastName", dto.getLastName());
            fieldsToVerify.put("email", dto.getEmail());
            fieldsToVerify.put("telephone", dto.getTelephone());
            fieldsToVerify.put("roleId", dto.getRoleId());
            fieldsToVerify.put("isLdapUser", dto.getIsLdapUser());

            if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
                response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
                response.setHasError(true);
                return response;
            }

            // Vérification du format du téléphone
            if (!dto.getTelephone().matches("^\\+?[0-9]+$")) {
                response.setStatus(functionalError.INVALID_VALUE("Le numéro de téléphone ne doit contenir que des chiffres et peut commencer par un '+'", locale));
                response.setHasError(true);
                return response;
            }

            User existingEntity = null;
            // verif unique login in db
            existingEntity = userRepository.findByLogin(dto.getLogin(), false);
            if (existingEntity != null) {
                response.setStatus(functionalError.DATA_EXIST("user login -> " + dto.getLogin(), locale));
                response.setHasError(true);
                return response;
            }

            // Vérification que le login ne soit pas uniquement des chiffres et ne commence pas par un chiffre
            if (dto.getLogin().matches("\\d+")) {
                response.setStatus(functionalError.INVALID_VALUE("Le login ne doit pas être uniquement composé de chiffres", locale));
                response.setHasError(true);
                return response;
            }

            if (Character.isDigit(dto.getLogin().charAt(0))) {
                response.setStatus(functionalError.INVALID_VALUE("Le login ne doit pas commencer par un chiffre", locale));
                response.setHasError(true);
                return response;
            }

            // verif unique login in items to save
            if (items.stream().anyMatch(a -> a.getLogin().equalsIgnoreCase(dto.getLogin()))) {
                response.setStatus(functionalError.DATA_DUPLICATE(" login ", locale));
                response.setHasError(true);
                return response;
            }

            // verif unique email in db
            existingEntity = userRepository.findByEmail(dto.getEmail(), false);
            if (existingEntity != null) {
                response.setStatus(functionalError.DATA_EXIST("user email -> " + dto.getEmail(), locale));
                response.setHasError(true);
                return response;
            }

            // verif unique email in items to save
            if (items.stream().anyMatch(a -> a.getEmail().equalsIgnoreCase(dto.getEmail()))) {
                response.setStatus(functionalError.DATA_DUPLICATE(" email ", locale));
                response.setHasError(true);
                return response;
            }

            // Verify if role exist
            Role existingRole = null;
            if (dto.getRoleId() != null && dto.getRoleId() > 0) {
                existingRole = roleRepository.findOne(dto.getRoleId(), false);
                if (existingRole == null) {
                    response.setStatus(functionalError.DATA_NOT_EXIST("role roleId -> " + dto.getRoleId(), locale));
                    response.setHasError(true);
                    return response;
                }
            }

            User entityToSave = null;
            entityToSave = UserTransformer.INSTANCE.toEntity(dto, existingRole);
            entityToSave.setCreatedAt(Utilities.getCurrentDate());
            entityToSave.setCreatedBy(request.getUser());
            entityToSave.setIsDeleted(false);
            entityToSave.setLoginAttempts(0);
            entityToSave.setIsLocked(false);

            if (!dto.getIsLdapUser()) {
                String passwordString = Utilities.notBlank(dto.getPassword()) ? dto.getPassword() : Utilities.generateAlphanumericCode(8);
                String subjects = "Paramètres de connexion !";
                String emailTemplate = "new_user_1";

                if (activeProfiles.contains("development")) {
                    sendMail(entityToSave.getLogin(), entityToSave.getEmail(), "", "", subjects, emailTemplate, passwordString, "", locale);
                } else {
                    try {
                        emailService.sendPasswordEmail(entityToSave.getEmail(), entityToSave.getFirstName(), entityToSave.getLogin(), passwordString);
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }

                try {
                    entityToSave.setPassword(Utilities.encrypt(passwordString));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            items.add(entityToSave);
        }

        if (!items.isEmpty()) {
            List<User> itemsSaved = null;
            // inserer les donnees en base de donnees
            itemsSaved = userRepository.saveAll((Iterable<User>) items);
            List<UserDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? UserTransformer.INSTANCE.toLiteDtos(itemsSaved) : UserTransformer.INSTANCE.toDtos(itemsSaved);

            final int size = itemsSaved.size();
            List<String> listOfError = Collections.synchronizedList(new ArrayList<String>());
            itemsDto.parallelStream().forEach(dto -> {
                try {
                    dto = getFullInfos(dto, size, request.getIsSimpleLoading(), locale);
                } catch (Exception e) {
                    listOfError.add(e.getMessage());
                    e.printStackTrace();
                }
            });

            if (Utilities.isNotEmpty(listOfError)) {
                Object[] objArray = listOfError.stream().distinct().toArray();
                throw new RuntimeException(StringUtils.join(objArray, ", "));
            }

            response.setStatus(functionalError.SUCCESS("", locale));
            response.setItems(itemsDto);
            response.setHasError(false);
        }

        log.info("----end create User-----");
        response.setActionEffectue("creation de l'utilisateur");
        return response;
    }

    /**
     * update User by using UserDto as object.
     *
     * @param request
     * @return response
     */

    @Transactional
    @Override
    public Response<UserDto> update(Request<UserDto> request, Locale locale) throws ParseException {
        log.info("----begin update User-----");
        Response<UserDto> response = new Response<UserDto>();
        List<User> items = new ArrayList<User>();

        for (UserDto dto : request.getDatas()) {
            // Définir les paramètres obligatoires
            Map<String, Object> fieldsToVerify = new HashMap<String, Object>();
            fieldsToVerify.put("id", dto.getId());
            if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
                response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
                response.setHasError(true);
                return response;
            }

            // Vérifier si l'utilisateur existe
            User entityToSave = userRepository.findOne(dto.getId(), false);
            if (entityToSave == null) {
                response.setStatus(functionalError.DATA_NOT_EXIST("user id -> " + dto.getId(), locale));
                response.setHasError(true);
                return response;
            }

            // Vérification de l'unicité du login si modifié
            if (Utilities.notBlank(dto.getLogin())) {
                if (!dto.getLogin().equalsIgnoreCase(entityToSave.getLogin())) {
                    User existingEntity = userRepository.findByLogin(dto.getLogin(), false);
                    if (existingEntity != null) {
                        response.setStatus(functionalError.DATA_EXIST("user login -> " + dto.getLogin(), locale));
                        response.setHasError(true);
                        return response;
                    }
                    // Vérification que le login ne soit pas uniquement des chiffres et ne commence pas par un chiffre
                    if (dto.getLogin().matches("\\d+")) {
                        response.setStatus(functionalError.INVALID_VALUE("Le login ne doit pas être uniquement composé de chiffres", locale));
                        response.setHasError(true);
                        return response;
                    }
                    if (Character.isDigit(dto.getLogin().charAt(0))) {
                        response.setStatus(functionalError.INVALID_VALUE("Le login ne doit pas commencer par un chiffre", locale));
                        response.setHasError(true);
                        return response;
                    }
                    // Vérification de l'unicité du login dans les items à sauvegarder
                    if (items.stream().anyMatch(a -> a.getLogin().equalsIgnoreCase(dto.getLogin()))) {
                        response.setStatus(functionalError.DATA_DUPLICATE(" login ", locale));
                        response.setHasError(true);
                        return response;
                    }
                    entityToSave.setLogin(dto.getLogin());
                }
            }

            // Vérification de l'unicité de l'email si modifié
            if (Utilities.notBlank(dto.getEmail())) {
                if (!dto.getEmail().equalsIgnoreCase(entityToSave.getEmail())) {
                    User existingEntity = userRepository.findByEmail(dto.getEmail(), false);
                    if (existingEntity != null) {
                        response.setStatus(functionalError.DATA_EXIST("user email -> " + dto.getEmail(), locale));
                        response.setHasError(true);
                        return response;
                    }
                    // Vérification de l'unicité de l'email dans les items à sauvegarder
                    if (items.stream().anyMatch(a -> a.getEmail().equalsIgnoreCase(dto.getEmail()))) {
                        response.setStatus(functionalError.DATA_DUPLICATE(" email ", locale));
                        response.setHasError(true);
                        return response;
                    }
                    entityToSave.setEmail(dto.getEmail());
                }
            }

            // Vérifier si le rôle existe
            if (dto.getRoleId() != null && dto.getRoleId() > 0) {
                Role existingRole = roleRepository.findOne(dto.getRoleId(), false);
                if (existingRole == null) {
                    response.setStatus(functionalError.DATA_NOT_EXIST("role roleId -> " + dto.getRoleId(), locale));
                    response.setHasError(true);
                    return response;
                }
                entityToSave.setRole(existingRole);
            }

            // Mise à jour des autres champs
            if (Utilities.notBlank(dto.getPassword())) {
                entityToSave.setPassword(dto.getPassword());
            }
            if (Utilities.notBlank(dto.getFirstName())) {
                entityToSave.setFirstName(dto.getFirstName());
            }
            if (Utilities.notBlank(dto.getLastName())) {
                entityToSave.setLastName(dto.getLastName());
            }
            if (Utilities.notBlank(dto.getFonction())) {
                entityToSave.setFonction(dto.getFonction());
            }
            if (Utilities.notBlank(dto.getLieuFonction())) {
                entityToSave.setLieuFonction(dto.getLieuFonction());
            }
            if (Utilities.notBlank(dto.getBornOn())) {
                entityToSave.setBornOn(dateFormat.parse(dto.getBornOn()));
            }
            if (Utilities.notBlank(dto.getTelephone())) {
                // Vérification du format du téléphone
                if (!dto.getTelephone().matches("^\\+?[0-9]+$")) {
                    response.setStatus(functionalError.INVALID_VALUE("Le numéro de téléphone ne doit contenir que des chiffres et peut commencer par un '+'", locale));
                    response.setHasError(true);
                    return response;
                }
                entityToSave.setTelephone(dto.getTelephone());
            }
            if (dto.getIsDefaultPassword() != null) {
                entityToSave.setIsDefaultPassword(dto.getIsDefaultPassword());
            }
            if (dto.getIsConnected() != null) {
                entityToSave.setIsConnected(dto.getIsConnected());
            }
            if (dto.getIsLocked() != null) {
                entityToSave.setIsLocked(dto.getIsLocked());
            }
            if (Utilities.notBlank(dto.getLastConnectionDate())) {
                entityToSave.setLastConnectionDate(dateFormat.parse(dto.getLastConnectionDate()));
            }
            if (Utilities.notBlank(dto.getLastLockDate())) {
                entityToSave.setLastLockDate(dateFormat.parse(dto.getLastLockDate()));
            }
            if (Utilities.notBlank(dto.getPassCode())) {
                entityToSave.setPassCode(dto.getPassCode());
            }
            if (dto.getIsValidPassCode() != null) {
                entityToSave.setIsValidPassCode(dto.getIsValidPassCode());
            }
            if (Utilities.notBlank(dto.getPassCodeExpireAt())) {
                entityToSave.setPassCodeExpireAt(dateFormat.parse(dto.getPassCodeExpireAt()));
            }
            if (dto.getCreatedBy() != null && dto.getCreatedBy() > 0) {
                entityToSave.setCreatedBy(dto.getCreatedBy());
            }
            if (dto.getUpdatedBy() != null && dto.getUpdatedBy() > 0) {
                entityToSave.setUpdatedBy(dto.getUpdatedBy());
            }
            if (dto.getIsActive() != null) {
                entityToSave.setIsActive(dto.getIsActive());
            }
            if (Utilities.notBlank(dto.getSearchString())) {
                entityToSave.setSearchString(dto.getSearchString());
            }

            // Gestion du champ isLdapUser
            Boolean previousIsLdapUser = entityToSave.getIsLdapUser();
            if (dto.getIsLdapUser() != null) {
                entityToSave.setIsLdapUser(dto.getIsLdapUser());
                log.info("Updated entity isLdapUser to: " + entityToSave.getIsLdapUser());

                // Si isLdapUser passe de false à true, désactiver le mot de passe (car géré par LDAP)
                if (previousIsLdapUser != null && !previousIsLdapUser && dto.getIsLdapUser()) {
                    entityToSave.setPassword(null);
                }

                // Si isLdapUser passe de true à false, aucune action spécifique concernant le mot de passe
                // Vous pouvez ajouter ici toute logique nécessaire si vous souhaitez gérer d'autres aspects
            }

            // Mise à jour des dates et autres champs spécifiques
            if (Utilities.notBlank(dto.getPassCodeCreatedAt())) {
                entityToSave.setPassCodeCreatedAt(dateFormat.parse(dto.getPassCodeCreatedAt()));
            }
            if (Utilities.notBlank(dto.getToken())) {
                entityToSave.setToken(dto.getToken());
            }
            if (Utilities.notBlank(dto.getIsValidToken())) {
                entityToSave.setIsValidToken(dto.getIsValidToken());
            }
            if (Utilities.notBlank(dto.getTokenCreatedAt())) {
                entityToSave.setTokenCreatedAt(dateFormat.parse(dto.getTokenCreatedAt()));
            }
            if (Utilities.notBlank(dto.getTokenExpireAt())) {
                entityToSave.setTokenExpireAt(dateFormat.parse(dto.getTokenExpireAt()));
            }

            entityToSave.setUpdatedAt(Utilities.getCurrentDate());
            entityToSave.setUpdatedBy(request.getUser());
            items.add(entityToSave);
        }

        if (!items.isEmpty()) {
            List<User> itemsSaved = null;
            // Mise à jour des données en base de données
            itemsSaved = userRepository.saveAll(items);
            List<UserDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading()))
                    ? UserTransformer.INSTANCE.toLiteDtos(itemsSaved)
                    : UserTransformer.INSTANCE.toDtos(itemsSaved);

            final int size = itemsSaved.size();
            List<String> listOfError = Collections.synchronizedList(new ArrayList<String>());
            itemsDto.parallelStream().forEach(dto -> {
                try {
                    dto = getFullInfos(dto, size, request.getIsSimpleLoading(), locale);
                } catch (Exception e) {
                    listOfError.add(e.getMessage());
                    e.printStackTrace();
                }
            });

            if (Utilities.isNotEmpty(listOfError)) {
                Object[] objArray = listOfError.stream().distinct().toArray();
                throw new RuntimeException(StringUtils.join(objArray, ", "));
            }

            response.setStatus(functionalError.SUCCESS("", locale));
            response.setItems(itemsDto);
            response.setHasError(false);
        }

        log.info("----end update User-----");
        response.setActionEffectue("mise à jour de l'utilisateur");
        return response;
    }

    /**
     * delete User by using UserDto as object.
     *
     * @param request
     * @return response
     */
    @Transactional
    @Override
    public Response<UserDto> delete(Request<UserDto> request, Locale locale) {
        log.info("----begin delete User-----");
        Response<UserDto> response = new Response<UserDto>();
        List<User> items = new ArrayList<User>();
        for (UserDto dto : request.getDatas()) {
            // Definir les parametres obligatoires
            Map<String, Object> fieldsToVerify = new HashMap<String, Object>();
            fieldsToVerify.put("id", dto.getId());
            if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
                response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
                response.setHasError(true);
                return response;
            }
            // Verifier si la user existe
            User existingEntity = null;
            existingEntity = userRepository.findOne(dto.getId(), false);
            if (existingEntity == null) {
                response.setStatus(functionalError.DATA_NOT_EXIST("user -> " + dto.getId(), locale));
                response.setHasError(true);
                return response;
            }
            // -----------------------------------------------------------------------
            // ----------- CHECK IF DATA IS USED
            // -----------------------------------------------------------------------
            existingEntity.setIsDeleted(true);
            items.add(existingEntity);
        }
        if (!items.isEmpty()) {
            // supprimer les donnees en base
            userRepository.saveAll((Iterable<User>) items);
            response.setStatus(functionalError.SUCCESS("", locale));
            response.setHasError(false);
        }
        log.info("----end delete User-----");
        response.setActionEffectue("delete de l'utilisateur");
        return response;
    }

    /**
     * get User by using UserDto as object.
     *
     * @param request
     * @return response
     */
    @Override
    public Response<UserDto> getByCriteria(Request<UserDto> request, Locale locale) throws Exception {
        log.info("----begin get User-----");
        Response<UserDto> response = new Response<UserDto>();
        List<User> items = userRepository.getByCriteria(request, em, locale);
        if (items != null && !items.isEmpty()) {
            List<UserDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading()))
                    ? UserTransformer.INSTANCE.toLiteDtos(items)
                    : UserTransformer.INSTANCE.toDtos(items);

            final int size = items.size();
            List<String> listOfError = Collections.synchronizedList(new ArrayList<String>());
            itemsDto.parallelStream().forEach(dto -> {
                try {
                    dto = getFullInfos(dto, size, request.getIsSimpleLoading(), locale);
                } catch (Exception e) {
                    listOfError.add(e.getMessage());
                    e.printStackTrace();
                }
            });
            if (Utilities.isNotEmpty(listOfError)) {
                Object[] objArray = listOfError.stream().distinct().toArray();
                throw new RuntimeException(StringUtils.join(objArray, ", "));
            }
            response.setItems(itemsDto);
            response.setCount(userRepository.count(request, em, locale));
            response.setHasError(false);
        } else {
            response.setStatus(functionalError.DATA_EMPTY("user", locale));
            response.setHasError(false);
            return response;
        }
        log.info("----end get User-----");
        response.setActionEffectue("GetByCriteria user");
        return response;
    }

    public Response<UserDto> custom(Request<UserDto> request, Locale locale) {
        log.info("----begin custom UserDto-----");
        Response<UserDto> response = new Response<UserDto>();
        response.setHasError(false);
        response.setCount(1L);
        response.setItems(Arrays.asList(new UserDto()));
        log.info("----end custom UserDto-----");
        return response;
    }

    @SuppressWarnings("unused")
    public Response<UserDto> connexion(Request<UserDto> request, Locale locale) {
        log.info("----begin login User-----");
        log.debug("This is a debug connexion");
        String loginString = null;
        response = new Response<UserDto>();
        try {
            log.info("----begin connexion Utilisateur-----");
            UserDto dto = request.getData();
            log.debug("Début de la méthode de connexion pour l'utilisateur: {}", dto.getLogin());
            // Validation des champs obligatoires
            Map<String, Object> fieldsToVerify = new HashMap<>();
            fieldsToVerify.put("login", dto.getLogin());
            fieldsToVerify.put("password", dto.getPassword());
            if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
                response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
                response.setHasError(true);
                return response;
            }
            loginString = dto.getLogin();
            User userToConnect = null;
            String otpCode = null;
            Boolean isLdapUserv = dto.getIsLdapUser();
            log.info("isLdapUserv====================> " + isLdapUserv);
            if (isLdapUserv == null) {
                // Retournez une erreur si isLdapUser n'est pas spécifié dans la requête
                response.setStatus(functionalError
                        .DISALLOWED_OPERATION("Veuillez spécifier le type de connexion (LDAP ou standard).", locale));
                response.setHasError(true);
                return response;
            }
            boolean isAuthenticated = false;
            if (dto.getIsLdapUser() != null && dto.getIsLdapUser()) {
                // LDAP Authentication
                isAuthenticated = ldapService.authenticateUser(dto.getLogin(), dto.getPassword());
                log.info("isAuthenticated====================> " + isAuthenticated);
                // Vérifier si l'utilisateur existe en base après authentification LDAP réussie
                if (isAuthenticated) {
                    userToConnect = userRepository.findByLogin(dto.getLogin(), false);
                    if (userToConnect == null) {
                        response.setStatus(functionalError
                                .DATE_FORMAT_NOT_CORRECT("Utilisateur LDAP non trouvé en base de données", locale));
                        response.setHasError(Boolean.TRUE);
                        return response;
                    }
                    if (userToConnect.getIsLocked() != null && userToConnect.getIsLocked()) {
                        response.setStatus(functionalError
                                .DATA_NOT_EXIST("Votre compte est verrouillé, contactez l'administrateur.", locale));
                        response.setHasError(true);
                        return response;
                    }
                    // Réinitialiser les tentatives de connexion après authentification LDAP réussie
                    userToConnect.setLoginAttempts(0);
                    userRepository.save(userToConnect);
                } else {
                    // Échec de l'authentification LDAP, vérifier l'existence de l'utilisateur en
                    // base
                    userToConnect = userRepository.findByLogin(dto.getLogin(), false);
                    if (userToConnect == null) {
                        response.setStatus(
                                functionalError.DISALLOWED_OPERATION("Échec de l'authentification LDAP", locale));
                        response.setHasError(true);
                        return response;
                    }
                    // Gérer les tentatives de connexion en cas d'échec d'authentification LDAP
                    int loginAttempts = userToConnect.getLoginAttempts() == null ? 0 : userToConnect.getLoginAttempts();
                    loginAttempts++;
                    userToConnect.setLoginAttempts(loginAttempts);
                    if (loginAttempts >= 3) {
                        userToConnect.setIsLocked(true);
                        response.setStatus(
                                functionalError.DISALLOWED_OPERATION("Votre compte a été verrouillé.", locale));
                    } else {
                        response.setStatus(functionalError.DISALLOWED_OPERATION(
                                "Mot de passe/login incorrect. Tentative " + loginAttempts + "/3", locale));
                    }
                    userRepository.save(userToConnect);
                    response.setHasError(true);
                    return response;
                }
            } else {
                // Standard Authentication
                userToConnect = userRepository.findByLogin(dto.getLogin(), false);
                if (userToConnect == null) {
                    response.setStatus(functionalError.DISALLOWED_OPERATION("Login incorrect", locale));
                    response.setHasError(Boolean.TRUE);
                    return response;
                }
                if (userToConnect.getIsLocked() != null && userToConnect.getIsLocked()) {
                    response.setStatus(functionalError
                            .DATA_NOT_EXIST("Votre compte est verrouillé, contactez l'administrateur.", locale));
                    response.setHasError(true);
                    return response;
                }
                if (userToConnect.getPassword() == null) {
                    response.setStatus(
                            functionalError.DATA_NOT_EXIST("Aucun mot de passe défini pour cet utilisateur.", locale));
                    response.setHasError(true);
                    return response;
                }
                try {
                    // String encryptedInputPassword = Utilities.encrypt(dto.getPassword());
                    if (!userToConnect.getPassword().equals(Utilities.encrypt(dto.getPassword()))) {
                        int loginAttempts = userToConnect.getLoginAttempts() == null ? 0
                                : userToConnect.getLoginAttempts();
                        loginAttempts++;
                        userToConnect.setLoginAttempts(loginAttempts);
                        userRepository.save(userToConnect);
                        if (loginAttempts >= 3) {
                            userToConnect.setIsLocked(true);
                            userRepository.save(userToConnect);
                            response.setStatus(
                                    functionalError.DISALLOWED_OPERATION("Votre compte a été verrouillé.", locale));
                        } else {
                            response.setStatus(functionalError.DISALLOWED_OPERATION(
                                    "Mot de passe incorrect. Tentative " + loginAttempts + "/3", locale));
                        }
                        response.setHasError(true);
                        return response;
                    }
                } catch (Exception e) {
                    response.setStatus(
                            functionalError.DISALLOWED_OPERATION("Erreur lors du cryptage du mot de passe.", locale));
                    response.setHasError(true);
                    return response;
                }
            }
            // Reset login attempts on successful login
            userToConnect.setLoginAttempts(0);
            userRepository.save(userToConnect);
            // Générer et envoyer le code OTP
            otpCode = Utilities.generatenumericCode(4);
            userToConnect.setOtpCode(otpCode);
            userToConnect.setDateSendCodeOtpAt(Utilities.getCurrentDate());
            userRepository.save(userToConnect);

            //lorsque la connexion est solicitée par l'api omrdc
            if (dto.getLogin().equals("omrdc")) {
                UserDto userDto = UserTransformer.INSTANCE.toDto(userToConnect);
                Request<UserDto> user = new Request<UserDto>();
                user.setData(userDto);
                return validateNumberCodeOtp(user, locale);
            }

            sendOtpCode(userToConnect, otpCode, isLdapUserv, locale);
            response.setStatus(functionalError.SUCCESS("Code OTP envoyé." + otpCode, locale));
            response.setHasError(false);
            log.debug("AuthentificationLdape {} pour l'utilisateur {}", (isAuthenticated ? "réussie" : "échouée"),
                    dto.getLogin());
        } catch (PermissionDeniedDataAccessException e) {
            exceptionUtils.PERMISSION_DENIED_DATA_ACCESS_EXCEPTION(response, locale, e);
        } catch (DataAccessResourceFailureException e) {
            exceptionUtils.DATA_ACCESS_RESOURCE_FAILURE_EXCEPTION(response, locale, e);
        } catch (DataAccessException e) {
            exceptionUtils.DATA_ACCESS_EXCEPTION(response, locale, e);
        } catch (RuntimeException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            log.error("Échec de connexion pour l'utilisateur: login={}, error={}, additionalInfo={}", loginString,
                    e.getMessage(), "Détails supplémentaires ici", e);
            throw new CustomDataAccessException("Échec de connexion avec détails", "CODE123",
                    "Détails supplémentaires ici", e);
        } finally {
            if (response != null && response.isHasError() && response.getStatus() != null) {
                log.info(String.format("Erreur | code: {} -  message: {}", response.getStatus().getCode(),
                        response.getStatus().getMessage()));
                throw new RuntimeException(response.getStatus().getCode() + ";" + response.getStatus().getMessage());
            }
        }
        response.setActionEffectue("Envoie de code otp: ");
        return response;
    }

    private void sendOtpCode(User user, String otpCode, Boolean isLdapUser, Locale locale) throws IOException {
        List<String> activeProfiles = Arrays.asList(environment.getActiveProfiles());
        if (user != null && otpCode != null && isLdapUser != null && isLdapUser.equals(Boolean.TRUE)) {
            // Envoi de OTP par SMS
            log.info("Valeur de isLdapUser : " + isLdapUser);
            log.info("Envoi====================> SMS is true ldap");
            String phoneNumber = user.getTelephone();
            String smsText = "Votre code OTP est : " + otpCode;
            String encodedPhoneNumber = URLEncoder.encode(phoneNumber, "UTF-8");
            String encodedSmsText = URLEncoder.encode(smsText, "UTF-8");
            String apiUrl = paramsUtils.getUrlEnvoieSMS();
            String apiUrlWithParams = apiUrl + "?username=smile&password=smile&from=smile&to=" + encodedPhoneNumber
                    + "&text=" + encodedSmsText;
            CallAPiLite(apiUrlWithParams);

        } else {
            // String mailResponse = sendOtpEmail(user.getEmail(), "Paramètres de connexion !", otpCode);
            // Envoi de OTP par email
            log.info("Valeur de isLdapUser : " + isLdapUser);
            log.info("Envoi====================> SMS is false ldap");
            String subjects = "Paramètres de connexion !";
            String emailTemplate = "new_user";
            log.info("Envoi====================>  e-mail");
            if (activeProfiles.contains("development")) {
                sendMail(user.getLogin(), user.getEmail(), "", "", subjects, emailTemplate, otpCode, "", locale);
            } else {
                emailService.sendOtpEmail(user.getEmail(), user.getLogin(), otpCode);
            }

        }
    }

    @Transactional
    public Response<UserDto> validateNumberCodeOtp(Request<UserDto> request, Locale locale) {
        response = new Response<UserDto>();
        UserDto useritemDto = new UserDto();
        try {
            // Vérifier les paramètres obligatoires
            UserDto dto = request.getData();
            Map<String, Object> fieldsToVerify = new HashMap<>();
            fieldsToVerify.put("otpCode", dto.getOtpCode());
            if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
                response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
                response.setHasError(true);
                return response;
            }
            // Récupérer l'utilisateur associé au code OTP
            User user = userRepository.findByOtpCode(dto.getOtpCode(), false);
            if (user == null) {
                response.setStatus(
                        functionalError.DATA_NOT_EXIST("Code OTP invalid, Veuillez saisir le bon code otp", locale));
                response.setHasError(true);
                return response;
            }

            // Supprimer l'ancienne clé Redis pour cet utilisateur
            String oldToken = user.getToken();
            if (oldToken != null) {
                redisUser.delete(oldToken); // Supprime l'ancienne clé
            }
            // Convertir Date en LocalDateTime
            Date dateSendCodeOtpAt = user.getDateSendCodeOtpAt();
            Instant instant = Instant.ofEpochMilli(dateSendCodeOtpAt.getTime());
            LocalDateTime otpCodeSentAt = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime otpCodeExpiry = otpCodeSentAt.plus(2, ChronoUnit.MINUTES);
            if (now.isAfter(otpCodeExpiry)) {
                // Gérer l'expiration du code OTP avant la validation
                response.setStatus(functionalError
                        .DISALLOWED_OPERATION("Votre Code OTP a expiré, Veuillez-vous reconnecter", locale));
                response.setHasError(true);
                return response;
            }
            String token = Utilities.generateAlphanumericCodeLite(256);
            ZonedDateTime tokenCreateDate = ZonedDateTime.now();
            ZonedDateTime datePlus40Minutes = tokenCreateDate.plus(40, ChronoUnit.MINUTES);
            user.setToken(token);
            // user.setDateSendCodeOtpAt(null);
            user.setTokenCreatedAt(Date.from(tokenCreateDate.toInstant()));
            user.setTokenExpireAt(Date.from(datePlus40Minutes.toInstant()));

            userRepository.save(user);

            UserDto userDto = UserTransformer.INSTANCE.toDto(user);

            userDto.setToken(token);
            redisUser.save(token, userDto, true);


            List<User> items = Arrays.asList(user);
            if (items != null && !items.isEmpty()) {
                List<UserDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading()))
                        ? UserTransformer.INSTANCE.toLiteDtos(items)
                        : UserTransformer.INSTANCE.toDtos(items);
                final int size = items.size();
                List<String> listOfError = Collections.synchronizedList(new ArrayList<String>());
                itemsDto.parallelStream().forEach(dto1 -> {
                    try {
                        dto1 = getFullInfos(dto1, size, request.getIsSimpleLoading(), locale);
                    } catch (Exception e) {
                        listOfError.add(e.getMessage());
                        e.printStackTrace();
                    }
                });
                if (Utilities.isNotEmpty(listOfError)) {
                    Object[] objArray = listOfError.stream().distinct().toArray();
                    throw new RuntimeException(StringUtils.join(objArray, ", "));
                }
                if (Utilities.isNotEmpty(itemsDto)) {
                    response.setItems(itemsDto);
                } else {
                    response.setItems(Arrays.asList(useritemDto));
                }
                response.setHasError(false);
                response.setStatus(functionalError.SUCCESS("", locale));
            } else {
                response.setStatus(functionalError.DATA_EMPTY("user", locale));
                response.setHasError(false);
                return response;
            }
            log.info("----end get User-----");

        } catch (PermissionDeniedDataAccessException e) {
            exceptionUtils.PERMISSION_DENIED_DATA_ACCESS_EXCEPTION(response, locale, e);
        } catch (DataAccessResourceFailureException e) {
            exceptionUtils.DATA_ACCESS_RESOURCE_FAILURE_EXCEPTION(response, locale, e);
        } catch (DataAccessException e) {
            exceptionUtils.DATA_ACCESS_EXCEPTION(response, locale, e);
        } catch (RuntimeException e) {
            exceptionUtils.RUNTIME_EXCEPTION(response, locale, e);
        } catch (Exception e) {
            exceptionUtils.EXCEPTION(response, locale, e);
        } finally {
            if (response != null && response.isHasError() && response.getStatus() != null) {
                log.info(String.format("Erreur| code: {} -  message: {}", response.getStatus().getCode(),
                        response.getStatus().getMessage()));
                throw new RuntimeException(response.getStatus().getCode() + ";" + response.getStatus().getMessage());
            }
        }
        response.setStatus(functionalError.SUCCESS("", locale));
        response.setActionEffectue("Connexion utilisateur via code otp");
        return response;
    }

    @SuppressWarnings("unused")
    @Transactional(rollbackFor = {RuntimeException.class, Exception.class})
    public Response<UserDto> lock(Request<UserDto> request, Locale locale) {
        log.info("----begin lock User-----");
        response = new Response<UserDto>();
        try {
            List<User> items = new ArrayList<>();
            for (UserDto dto : request.getDatas()) {
                // Définir les paramètres obligatoires
                Map<String, Object> fieldsToVerify = new HashMap<>();
                fieldsToVerify.put("id", dto.getId());
                if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
                    response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
                    response.setHasError(true);
                    response.setActionEffectue("Bloquer Utilisateur");
                    return response;
                }

                User requester = userRepository.findOne(request.getUser(), false);
                if (requester == null) {
                    response.setStatus(functionalError.DATA_EXIST("User [" + request.getUser() + "] inexistant", locale));
                    response.setHasError(true);
                    response.setActionEffectue("Bloquer Utilisateur");
                    return response;
                }

                if (requester.getIsLocked() != null && requester.getIsLocked()) {
                    response.setStatus(functionalError.USER_IS_LOCKED(
                            String.format("%1$s %2$s est déjà verrouillé(e)", requester.getFirstName(), requester.getLastName()), locale));
                    response.setHasError(true);
                    response.setActionEffectue("Bloquer Utilisateur");
                    return response;
                }

                // Vérifier si l'utilisateur à verrouiller existe
                User entityToLock = userRepository.findOne(dto.getId(), false);
                if (entityToLock == null) {
                    response.setStatus(functionalError.DATA_NOT_EXIST("User -> " + dto.getId(), locale));
                    response.setHasError(true);
                    response.setActionEffectue("Bloquer Utilisateur");
                    return response;
                }

                if (entityToLock.getIsLocked() != null && entityToLock.getIsLocked()) {
                    response.setStatus(functionalError.DISALLOWED_OPERATION(
                            String.format("%1$s %2$s est déjà verrouillé(e)", entityToLock.getFirstName(), entityToLock.getLastName()), locale));
                    response.setHasError(true);
                    response.setActionEffectue("Bloquer Utilisateur");
                    return response;
                }

                // Verrouiller l'utilisateur
                entityToLock.setIsLocked(true);
                items.add(entityToLock);

                // Supprimer le token de l'utilisateur de Redis pour le déconnecter
                String token = entityToLock.getToken();
                if (token != null) {
                    redisUser.delete(token); // Supprimer le token de Redis pour forcer la déconnexion
                    entityToLock.setToken(null); // Optionnel : Réinitialiser le token dans la base de données si nécessaire
                }
            }

            if (!items.isEmpty()) {
                List<User> itemsSaved = userRepository.saveAll(items);
                if (itemsSaved == null) {
                    response.setStatus(functionalError.SAVE_FAIL("User", locale));
                    response.setHasError(true);
                    response.setActionEffectue("Bloquer Utilisateur");
                    return response;
                }

                List<UserDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading()))
                        ? UserTransformer.INSTANCE.toLiteDtos(itemsSaved)
                        : UserTransformer.INSTANCE.toDtos(itemsSaved);

                final int size = itemsSaved.size();
                List<String> listOfError = Collections.synchronizedList(new ArrayList<>());
                itemsDto.parallelStream().forEach(dto -> {
                    try {
                        dto = getFullInfos(dto, size, request.getIsSimpleLoading(), locale);
                    } catch (Exception e) {
                        listOfError.add(e.getMessage());
                        e.printStackTrace();
                    }
                });

                if (Utilities.isNotEmpty(listOfError)) {
                    Object[] objArray = listOfError.stream().distinct().toArray();
                    throw new RuntimeException(StringUtils.join(objArray, ", "));
                }

                response.setItems(itemsDto);
                response.setStatus(functionalError.SUCCESS("Compte utilisateur verrouillé", locale));
                response.setHasError(false);
            }
            log.info("----end lock User-----");
        } catch (PermissionDeniedDataAccessException e) {
            exceptionUtils.PERMISSION_DENIED_DATA_ACCESS_EXCEPTION(response, locale, e);
        } catch (DataAccessResourceFailureException e) {
            exceptionUtils.DATA_ACCESS_RESOURCE_FAILURE_EXCEPTION(response, locale, e);
        } catch (DataAccessException e) {
            exceptionUtils.DATA_ACCESS_EXCEPTION(response, locale, e);
        } catch (RuntimeException e) {
            exceptionUtils.RUNTIME_EXCEPTION(response, locale, e);
        } catch (Exception e) {
            exceptionUtils.EXCEPTION(response, locale, e);
        } finally {
            if (response != null && response.isHasError() && response.getStatus() != null) {
                log.info(String.format("Erreur| code: %s - message: %s", response.getStatus().getCode(), response.getStatus().getMessage()));
                throw new RuntimeException(response.getStatus().getCode() + ";" + response.getStatus().getMessage());
            }
        }
        response.setActionEffectue("Bloquer utilisateur");
        return response;
    }

    @SuppressWarnings("unused")
    @Transactional(rollbackFor = {RuntimeException.class, Exception.class})
    public Response<UserDto> unlock(Request<UserDto> request, Locale locale) {
        log.info("----begin unlock User-----");
        response = new Response<UserDto>();
        try {
            List<User> items = new ArrayList<User>();
            for (UserDto dto : request.getDatas()) {
                // Vérification des champs obligatoires
                Map<String, Object> fieldsToVerify = new HashMap<String, Object>();
                fieldsToVerify.put("id", dto.getId());
                if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
                    response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
                    response.setHasError(true);
                    return response;
                }
                User requester = userRepository.findOne(request.getUser(), false);
                if (requester == null) {
                    response.setStatus(functionalError.DATA_EXIST("Utilisateur [" + request.getUser() + "] inexistant", locale));
                    response.setHasError(true);
                    return response;
                }
                if (requester.getIsLocked() != null && requester.getIsLocked()) {
                    response.setStatus(functionalError.USER_IS_UNLOCKED(
                            String.format("%1$s %2$s est déjà déverrouillé(e)", requester.getFirstName(), requester.getLastName()), locale));
                    response.setHasError(true);
                    return response;
                }
                // Vérification si l'utilisateur à déverrouiller existe
                User entityToUnlock = userRepository.findOne(dto.getId(), false);
                if (entityToUnlock == null) {
                    response.setStatus(functionalError.DATA_NOT_EXIST("Utilisateur -> " + dto.getId(), locale));
                    response.setHasError(true);
                    return response;
                }
                if (entityToUnlock.getIsLocked() != null && !entityToUnlock.getIsLocked()) {
                    response.setStatus(functionalError.USER_IS_UNLOCKED(
                            String.format("%1$s %2$s est déjà déverrouillé(e)", entityToUnlock.getFirstName(), entityToUnlock.getLastName()), locale));
                    response.setHasError(true);
                    return response;
                }
                entityToUnlock.setIsLocked(false);
                items.add(entityToUnlock);
            }
            if (!items.isEmpty()) {
                List<User> itemsSaved = userRepository.saveAll(items);
                if (itemsSaved == null) {
                    response.setStatus(functionalError.SAVE_FAIL("User", locale));
                    response.setHasError(true);
                    return response;
                }
                List<UserDto> itemsDto = Utilities.isTrue(request.getIsSimpleLoading()) ?
                        UserTransformer.INSTANCE.toLiteDtos(itemsSaved) :
                        UserTransformer.INSTANCE.toDtos(itemsSaved);
                final int size = itemsSaved.size();
                List<String> listOfError = Collections.synchronizedList(new ArrayList<>());
                itemsDto.parallelStream().forEach(dto -> {
                    try {
                        dto = getFullInfos(dto, size, request.getIsSimpleLoading(), locale);
                    } catch (Exception e) {
                        listOfError.add(e.getMessage());
                        e.printStackTrace();
                    }
                });
                if (Utilities.isNotEmpty(listOfError)) {
                    Object[] objArray = listOfError.stream().distinct().toArray();
                    throw new RuntimeException(StringUtils.join(objArray, ", "));
                }
                response.setActionEffectue("Débloquer Utilisateur");
                response.setItems(itemsDto);
                response.setStatus(functionalError.SUCCESS("Compte utilisateur déverrouillé", locale));
                response.setHasError(false);
            }
            log.info("----end unlock User-----");
        } catch (PermissionDeniedDataAccessException e) {
            exceptionUtils.PERMISSION_DENIED_DATA_ACCESS_EXCEPTION(response, locale, e);
        } catch (DataAccessResourceFailureException e) {
            exceptionUtils.DATA_ACCESS_RESOURCE_FAILURE_EXCEPTION(response, locale, e);
        } catch (DataAccessException e) {
            exceptionUtils.DATA_ACCESS_EXCEPTION(response, locale, e);
        } catch (RuntimeException e) {
            exceptionUtils.RUNTIME_EXCEPTION(response, locale, e);
        } catch (Exception e) {
            exceptionUtils.EXCEPTION(response, locale, e);
        } finally {
            if (response.isHasError() && response.getStatus() != null) {
                log.info(String.format("Erreur| code: %s - message: %s", response.getStatus().getCode(), response.getStatus().getMessage()));
                throw new RuntimeException(response.getStatus().getCode() + ";" + response.getStatus().getMessage());
            }
        }
        response.setActionEffectue("Débloquer Utilisateur");
        return response;
    }

    @SuppressWarnings("unused")
    public Response<UserDto> logOut(Request<UserDto> request, Locale locale) {
        log.info("----begin logOut-----");
        response = new Response<UserDto>();
        try {
            Map<String, Object> fieldsToVerifyUser = new HashMap<String, Object>();
            fieldsToVerifyUser.put("user", request.getUser());
            if (!Validate.RequiredValue(fieldsToVerifyUser).isGood()) {
                response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
                response.setHasError(true);
                return response;
            }
            UserDto data = request.getData();
            // Definir les parametres obligatoires
            Map<String, Object> fieldsToVerify = new HashMap<String, Object>();
            fieldsToVerify.put("id", data.getId());
            if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
                response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
                response.setHasError(true);
                return response;
            }
            User item = userRepository.findOne(data.getId(), Boolean.FALSE);
            if (item == null) {
                response.setStatus(functionalError.DATA_NOT_EXIST("user ->" + data.getId(), locale));
                response.setHasError(true);
                return response;
            }
            response.setStatus(functionalError.SUCCESS("Vous être déconnecté", locale));
            response.setHasError(false);

        } catch (PermissionDeniedDataAccessException e) {
            exceptionUtils.PERMISSION_DENIED_DATA_ACCESS_EXCEPTION(response, locale, e);
        } catch (DataAccessResourceFailureException e) {
            exceptionUtils.DATA_ACCESS_RESOURCE_FAILURE_EXCEPTION(response, locale, e);
        } catch (DataAccessException e) {
            exceptionUtils.DATA_ACCESS_EXCEPTION(response, locale, e);
        } catch (RuntimeException e) {
            exceptionUtils.RUNTIME_EXCEPTION(response, locale, e);
        } catch (Exception e) {
            exceptionUtils.EXCEPTION(response, locale, e);
        } finally {
            if (response.isHasError() && response.getStatus() != null) {
                log.info(String.format("Erreur| code: {} -  message: {}", response.getStatus().getCode(),
                        response.getStatus().getMessage()));
                throw new RuntimeException(response.getStatus().getCode() + ";" + response.getStatus().getMessage());
            }
        }
        response.setActionEffectue("Deconnexion de l'utilisateur");
        return response;
    }

    public Response<UserDto> validationInscription(Request<UserDto> request, Locale locale) throws ParseException {
        log.info("----begin validationInscription User-----");
        Response<UserDto> response = new Response<UserDto>();
        for (UserDto dto : request.getDatas()) {
            // UserDto dto = request.getData();
            // Definir les parametres obligatoires
            Map<String, Object> fieldsToVerify = new HashMap<String, Object>();
            fieldsToVerify.put("id", dto.getId());
            if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
                response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
                response.setHasError(true);
                return response;
            }
            // Verify if user to insert do not exist
            User existingEntity = null;
            existingEntity = userRepository.findOne(dto.getId(), false);
            if (existingEntity == null) {
                response.setStatus(functionalError.DATA_NOT_EXIST("user id -> " + dto.getId(), locale));
                response.setHasError(true);
                return response;
            }
            String passwordString = Utilities.notBlank(dto.getPassword()) ? dto.getPassword()
                    : Utilities.generateAlphanumericCode(8);
            String subjects = "Paramètres de connexion !";
            String emailTemplate = "new_user";

            sendMail(existingEntity.getLogin(), existingEntity.getEmail(), "", "", subjects, emailTemplate,
                    passwordString, "", locale);
            try {
                existingEntity.setPassword(Utilities.encrypt(passwordString));
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            existingEntity.setIsLocked(Boolean.FALSE);
            existingEntity.setIsActive(Boolean.TRUE);
            existingEntity.setIsDefaultPassword(Boolean.TRUE);

            userRepository.save(existingEntity);
        }
        response.setStatus(functionalError.SUCCESS("", locale));
        response.setHasError(false);
        log.info("----end validationInscription User-----");
        return response;
    }

    public Response<UserDto> getSessionUser(Request<UserDto> request, Locale locale) {
        response = new Response<UserDto>();
        try {
            UserDto dto = request.getData();
            // champs obligatoires
            HashMap<String, Object> fieldsToVerify = new HashMap<>();
            fieldsToVerify.put("token", dto.getToken());
            if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
                response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
                response.setHasError(true);
                return response;
            }
            UserDto userDto = redisUser.get(dto.getToken());
            if (userDto != null) {
                response.setItems(Arrays.asList(userDto));
                response.setStatus(functionalError.SUCCESS("Utilisateur connecté", locale));
                response.setHasError(false);
            } else {
                response.setStatus(functionalError.DISALLOWED_OPERATION("Token indisponible", locale));
                response.setHasError(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.warn("getValue : " + e.getCause(), e.getMessage());
        }
        return response;
    }

    @SuppressWarnings({"unused", "null"})
    public Response<UserDto> getActiveSession(Locale locale) {
        log.info("----begin getActiveSession User-----");
        response = new Response<UserDto>();
        try {
            String regex = "*";
            Set<String> getAllKey = redisUser.getKeys(regex);
            System.out.println("getAllKey === " + getAllKey);
            // List<String> getAllKey = redisUser.getKeys();
            List<String> keys = new ArrayList<String>();
            List<UserDto> datas = new ArrayList<UserDto>();
            List<User> datasx = new ArrayList<User>();
            if (getAllKey != null && !getAllKey.isEmpty()) {
                getAllKey.stream().forEach(k -> {
                    if (Utilities.notBlank(k) && k.length() == 8) {
                        keys.add(k);
                    }
                });
                if (keys != null && !keys.isEmpty()) {
                    keys.stream().forEach(item -> {
                        UserDto dto = this.redisUser.get(item);
                        if (dto != null) {
                            datas.add(dto);
                        }
                    });
                }
                if (datas != null && !datas.isEmpty()) {
                    response.setItems(datas);
                    response.setHasError(Boolean.FALSE);
                    response.setStatus(functionalError.SUCCESS("", locale));
                    response.setCount((long) datas.size());
                } else {
                    response.setItems(new ArrayList<>());
                    response.setHasError(Boolean.FALSE);
                    response.setStatus(functionalError.SUCCESS("Liste vide", locale));
                }
            } else {
                response.setItems(new ArrayList<>());
                response.setHasError(Boolean.FALSE);
                response.setStatus(functionalError.SUCCESS("Liste vide", locale));
            }
            log.info("----end getActiveSession User-----");
        } catch (Exception e) {
            e.printStackTrace();
            log.warn("getValue : " + e.getCause(), e.getMessage());

        }
        return response;
    }

    public Response<Map<String, Object>> deleteKeys(Locale locale) {
        Response<Map<String, Object>> response = new Response<>();
        try {
            Set<String> keys = redisUser.getKeys("*node-");
            if (keys != null && !keys.isEmpty()) {
                keys.stream().forEach(item -> {
                    redisUser.delete(item);
                    log.info("delete key : " + item);
                });
                response.setStatus(functionalError.SUCCESS("", locale));
                response.setHasError(true);
            } else {
                response.setStatus(functionalError.DATA_EMPTY("", locale));
                response.setHasError(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.warn("getValue : " + e.getCause(), e.getMessage());
        }
        return response;
    }

    public Response<UserDto> getUserSessionTTL(Request<UserDto> request, Locale locale) {
        response = new Response<>();
        String TokenUser = request.getSessionUser();
        try {
            log.info("----begin  getUserSessionTTL-----");
            if (Utilities.isBlank(TokenUser)) {
                response.setStatus(functionalError.NO_USER("", locale));
                response.setHasError(true);
                return response;
            }

            Long ccf = redisUser.getTTL(TokenUser);
            if (ccf < 0) {
                ccf = (long) 0;
            }
            if (ccf == null || ccf == 0) {
                response.setStatus(functionalError.SESSION_EXPIRED("", locale));
                response.setHasError(true);
                return response;
            }

            response.setSessionUserExpire(ccf);
            response.setStatus(functionalError.SUCCESS("", locale));
            log.info("----end getUserSessionTTL-----");
        } catch (PermissionDeniedDataAccessException e) {
            exceptionUtils.PERMISSION_DENIED_DATA_ACCESS_EXCEPTION(response, locale, e);
        } catch (DataAccessResourceFailureException e) {
            exceptionUtils.DATA_ACCESS_RESOURCE_FAILURE_EXCEPTION(response, locale, e);
        } catch (DataAccessException e) {
            exceptionUtils.DATA_ACCESS_EXCEPTION(response, locale, e);
        } catch (RuntimeException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (response.isHasError() && response.getStatus() != null) {
                log.info(String.format("Erreur | code: {} -  message: {}", response.getStatus().getCode(),
                        response.getStatus().getMessage()));
                throw new RuntimeException(response.getStatus().getCode() + ";" + response.getStatus().getMessage());
            }
        }

        return response;
    }

    public void sendMail(String login, String email, String name, String lastName, String subjects,
                         String emailTemplate, String password, String maessage, Locale locale) {
        // --------------------------------------
        // SEND PASSWORD VIA MAIL
        // --------------------------------------
        // set mail to user
        Map<String, String> from = new HashMap<>();
        from.put("email", paramsUtils.getSmtpLogin());
        from.put("user", SENDER);
        // recipients
        List<Map<String, String>> toRecipients = new ArrayList<Map<String, String>>();
        Map<String, String> recipient = new HashMap<String, String>();
        recipient = new HashMap<String, String>();
        recipient.put("email", email);
        // recipient.put("user", login);
        toRecipients.add(recipient);
        // choisir la vraie url
        String appLink = paramsUtils.getUrlAdmin();
        // subject
        String subject = subjects;
        String body = "";
        context = new Context();
        String template = null;
        template = emailTemplate;
        context.setVariable("entete", ENTETE);
        context.setVariable("titre", TITRE);
        context.setVariable("login", login);
        context.setVariable("password", password);
        context.setVariable("appLink", appLink);
        Response<UserDto> responseEnvoiEmail = new Response<>();
        responseEnvoiEmail = hostingUtils.sendEmail(from, toRecipients, subject, body, null, context, template, locale);
    }

    public Response<UserDto> isGranted(Integer userId, String functionalityCode, Locale locale) {
        log.info("----begin get isGranted-----");
        response = new Response<UserDto>();
        try {
            User currentUser = userRepository.findOne(userId, false);
            if (currentUser == null) {
                response.setStatus(functionalError.DATA_NOT_EXIST("Utilisateur -> " + userId, locale));
                response.setHasError(true);
                return response;
            }
            response.setHasError(false);
            log.info("----end get isGranted-----");

        } catch (PermissionDeniedDataAccessException e) {
            exceptionUtils.PERMISSION_DENIED_DATA_ACCESS_EXCEPTION(response, locale, e);
        } catch (DataAccessResourceFailureException e) {
            exceptionUtils.DATA_ACCESS_RESOURCE_FAILURE_EXCEPTION(response, locale, e);
        } catch (DataAccessException e) {
            exceptionUtils.DATA_ACCESS_EXCEPTION(response, locale, e);
        } catch (RuntimeException e) {
            exceptionUtils.RUNTIME_EXCEPTION(response, locale, e);
        } catch (Exception e) {
            exceptionUtils.EXCEPTION(response, locale, e);
        } finally {
            if (response.isHasError() && response.getStatus() != null) {
                log.info(String.format("Erreur| code: {} -  message: {}", response.getStatus().getCode(),
                        response.getStatus().getMessage()));
                throw new RuntimeException(response.getStatus().getCode() + ";" + response.getStatus().getMessage());
            }
        }

        return response;
    }

    /**
     * get full UserDto by using User as object.
     *
     * @param dto
     * @param size
     * @param isSimpleLoading
     * @param locale
     * @return
     * @throws Exception
     */
    private UserDto getFullInfos(UserDto dto, Integer size, Boolean isSimpleLoading, Locale locale) throws Exception {
        // put code here
        List<RoleFonctionaliteDto> dataToEntity = new ArrayList<>();
        List<RoleFonctionalite> roleFonctionnalites = new ArrayList<RoleFonctionalite>();
        roleFonctionnalites = roleFonctionaliteRepository.findByRoleId(dto.getRoleId(), Boolean.FALSE);
        dataToEntity = RoleFonctionaliteTransformer.INSTANCE.toDtos(roleFonctionnalites);
        for (RoleFonctionalite roleFonctionnalite : roleFonctionnalites) {
            if (roleFonctionnalite != null) {
                if (roleFonctionnalite.getFonctionalite() != null) {
                    Fonctionalite oneFonc = fonctionaliteRepository
                            .findOne(roleFonctionnalite.getFonctionalite().getId(), Boolean.FALSE);
                    if (oneFonc != null) {
                        if (oneFonc.getFonctionalite() != null) {
                            if (oneFonc.getFonctionalite().getId() != null) {
                                List<RoleFonctionalite> foncToAdd = roleFonctionaliteRepository
                                        .findByFonctionnaliteId(oneFonc.getFonctionalite().getId(), Boolean.FALSE);
                                if (Utilities.isNotEmpty(foncToAdd)) {
                                    dataToEntity.add(RoleFonctionaliteTransformer.INSTANCE.toDto(foncToAdd.get(0)));
                                }
                            }
                        }
                    }
                }
            }
        }
        ArrayList<RoleFonctionaliteDto> data = new ArrayList<>();
        for (RoleFonctionaliteDto roleFonctionnalite : dataToEntity) {
            data.add(roleFonctionnalite);
        }
        ArrayList<RoleFonctionaliteDto> newList = Utilities.removeDuplicates(data);
        List<RoleFonctionaliteDto> fonctToAdd = new ArrayList<>();
        for (RoleFonctionaliteDto roleFonctionnalite : newList) {
            fonctToAdd.add(roleFonctionnalite);
        }
        if (fonctToAdd != null && !fonctToAdd.isEmpty()) {
            dto.setFonctionnaliteData(fonctToAdd);
        }
        if (Utilities.isTrue(isSimpleLoading)) {
            return dto;
        }
        if (size > 1) {
            return dto;
        }
        return dto;
    }

    public Map<String, Object> CallAPi(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        // Créer la requête GET
        okhttp3.Request request = new okhttp3.Request.Builder().url(url).get()
                .addHeader("Content-Type", "application/json").build();
        // Exécuter la requête
        okhttp3.Response resp = client.newCall(request).execute();
        String outputString = resp.body().string();
        System.out.println("outputString ===================>> " + outputString);
        Map<String, Object> map = new Gson().fromJson(outputString, new TypeToken<Map<String, Object>>() {
        }.getType());
        resp.close();
        return map;
    }

    public String CallAPiLite(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        // Créer la requête GET
        okhttp3.Request request = new okhttp3.Request.Builder().url(url).get()
                .addHeader("Content-Type", "application/json").build();
        // Exécuter la requête
        okhttp3.Response resp = client.newCall(request).execute();
        // Vérifier si la réponse est réussie (statut 200 OK)
        if (resp.isSuccessful()) {
            // Lire la réponse en tant que chaîne
            String responseString = resp.body().string();
            resp.close();
            return responseString;
        } else {
            // Gérer le cas d'une réponse non réussie (autre que 200 OK)
            resp.close();
            throw new IOException("Requête non réussie, code de réponse : " + resp.code());
        }
    }

    private JavaMailSender mailSender;

    public String sendOtpEmail(String to, String subject, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        String response = null;
        message.setFrom("nebdev007@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText("Votre OTP est : " + otp);

        try {
            mailSender.send(message);
            response = "succès";
            System.out.println("E-mail envoyé avec succès !");
        } catch (MailAuthenticationException e) {
            System.err.println("Erreur d'authentification : " + e.getMessage());
        } catch (MailSendException e) {
            System.err.println("Erreur d'envoi : " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Erreur lors de l'envoi de l'e-mail : " + e.getMessage());
        }
        return response;
    }

    public Response<UserDto> sendOtpForPasswordChange(Request<UserDto> request, Locale locale) {
        log.info("Début de l'envoi de l'OTP pour le changement de mot de passe.");
        Response<UserDto> response = new Response<>();

        try {
            // Validation de l'email
            UserDto dto = request.getData();
            if (dto.getEmail() == null || dto.getEmail().isEmpty()) {
                response.setStatus(functionalError.FIELD_EMPTY("email", locale));
                response.setHasError(true);
                return response;
            }

            // Vérification si l'utilisateur existe
            User user = userRepository.findByEmail(dto.getEmail(), false);
            if (user == null) {
                response.setStatus(functionalError.NO_USER("Aucun utilisateur trouvé pour cet email.", locale));
                response.setHasError(true);
                return response;
            }

            // Vérification si l'utilisateur est un utilisateur LDAP
            if (Boolean.TRUE.equals(user.getIsLdapUser())) {
                response.setStatus(functionalError.DISALLOWED_OPERATION(
                        "Les utilisateurs LDAP ne peuvent pas modifier leur mot de passe ici.", locale));
                response.setHasError(true);
                log.error("Tentative de modification de mot de passe pour un utilisateur LDAP : {}", dto.getEmail());
                return response;
            }

            // Vérification si l'utilisateur est verrouillé
            if (Boolean.TRUE.equals(user.getIsLocked())) {
                response.setStatus(functionalError.DISALLOWED_OPERATION(
                        "Votre compte est verrouillé. Veuillez contacter l'administrateur.", locale));
                response.setHasError(true);
                log.error("Compte verrouillé pour l'utilisateur avec l'email : {}", dto.getEmail());
                return response;
            }

            // Génération du code OTP
            String otpCode = Utilities.generatenumericCode(4);
            user.setOtpCode(otpCode);
            user.setDateSendCodeOtpAt(Utilities.getCurrentDate());
            userRepository.save(user);

            // Envoi de l'OTP par email
            // emailService.sendOtpResetEmail(user.getEmail(), user.getLogin(), otpCode);

            // Envoi de l'OTP par email
            sendOtpCodeToReset(user, otpCode, locale);


            response.setStatus(functionalError.SUCCESS("Un code OTP a été envoyé à votre adresse email.", locale));
            response.setHasError(false);
            log.info("OTP envoyé à l'utilisateur avec l'email : {}", dto.getEmail());
        } catch (Exception e) {
            log.error("Erreur lors de l'envoi de l'OTP : {}", e.getMessage(), e);
            response.setStatus(functionalError.UNEXPECTED_ERROR2("Erreur inattendue.", locale));
            response.setHasError(true);
        }

        log.info("Fin de l'envoi de l'OTP pour le changement de mot de passe.");
        return response;
    }

    private void sendOtpCodeToReset(User user, String otpCode, Locale locale) throws IOException {

        // Envoi de OTP par email
        log.info("Envoi====================> SMS is false ldap");
        String subjects = "Code OTP pour changement de mot de passe ";
        String emailTemplate = "new_user";
        log.info("Envoi====================> e-mail");
        sendMail(user.getLogin(), user.getEmail(), "", "", subjects, emailTemplate,
                otpCode, "", locale);

    }

    private void sendConfirmationMail(User user, Locale locale) throws IOException {

        // Envoi de OTP par email
        log.info("Envoi====================> SMS is false ldap");
        List<String> activeProfiles = Arrays.asList(environment.getActiveProfiles());
        String subjects = "Confirmation de changement de mot de passe !";
        String emailTemplate = "";
        if (activeProfiles.contains("development")) {
            emailTemplate = "confirmation-template";
        }else{
            emailTemplate = "email/confirmation-template";
        }
        log.info("Envoi====================> e-mail");
        sendMail(user.getLogin(), user.getEmail(), "", "", subjects, emailTemplate,
                "", "", locale);

    }




    public Response<UserDto> changePasswordWithOtp(Request<UserDto> request, Locale locale) {
        log.info("Début du processus de changement de mot de passe.");
        Response<UserDto> response = new Response<>();
        List<String> activeProfiles = Arrays.asList(environment.getActiveProfiles());

        try {
            // Validation des champs requis
            UserDto dto = request.getData();
            Map<String, Object> fieldsToVerify = new HashMap<>();
            fieldsToVerify.put("email", dto.getEmail());
            fieldsToVerify.put("newPassword", dto.getNewPassword());
            fieldsToVerify.put("otpCode", dto.getOtpCode());
            if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
                response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
                response.setHasError(true);
                return response;
            }

            // Vérification si l'utilisateur existe
            User user = userRepository.findByEmail(dto.getEmail(), false);
            if (user == null) {
                response.setStatus(functionalError.NO_USER("Utilisateur introuvable pour cet email.", locale));
                response.setHasError(true);
                return response;
            }

            // Vérification du code OTP
            if (!dto.getOtpCode().equals(user.getOtpCode())) {
                response.setStatus(functionalError.INVALID_VALUE("Code OTP incorrect.", locale));
                response.setHasError(true);
                return response;
            }

            // Vérification de l'expiration du code OTP
            long otpValidityDuration = 2 * 60 * 1000; // 2 minutes en millisecondes
            if (System.currentTimeMillis() - user.getDateSendCodeOtpAt().getTime() > otpValidityDuration) {
                response.setStatus(functionalError.INVALID_VALUE("Code OTP expiré.", locale));
                response.setHasError(true);
                return response;
            }

            // Vérification de la complexité du mot de passe
            if (!Utilities.isPasswordComplex(dto.getNewPassword())) {
                response.setStatus(functionalError.INVALID_VALUE("Le nouveau mot de passe ne respecte pas les critères de complexité.", locale));
                response.setHasError(true);
                return response;
            }

            // Mise à jour du mot de passe
            user.setPassword(Utilities.encrypt(dto.getNewPassword()));
            user.setOtpCode(null); // Nettoyage de l'OTP
            userRepository.save(user);

            if (activeProfiles.contains("development")) {
            // Envoi d'un email de confirmation
            sendConfirmationMail(user, locale);
            }else{
            // Envoi d'un email de confirmation
               emailService.sendPasswordChangedEmail(user.getEmail(), user.getLogin());
            }


            response.setStatus(functionalError.SUCCESS("Mot de passe changé avec succès.", locale));
            response.setHasError(false);
            log.info("Mot de passe changé avec succès pour l'utilisateur : {}", dto.getEmail());
        } catch (Exception e) {
            log.error("Erreur lors du changement de mot de passe : {}", e.getMessage(), e);
            response.setStatus(functionalError.UNEXPECTED_ERROR2("Erreur inattendue.", locale));
            response.setHasError(true);
        }

        log.info("Fin du processus de changement de mot de passe.");
        return response;
    }

}
