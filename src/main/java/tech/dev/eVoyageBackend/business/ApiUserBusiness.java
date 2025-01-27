                                                                                                                                        																																	
/*
 * Java business for entity table api_user 
 * Created on 2024-11-16 ( Time 14:33:28 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2018 Geo. All Rights Reserved.
 */

package tech.dev.eVoyageBackend.business;

import lombok.extern.java.Log;
import tech.dev.eVoyageBackend.business.UserBusiness;
import tech.dev.eVoyageBackend.dao.entity.*;
import tech.dev.eVoyageBackend.dao.repository.*;
import tech.dev.eVoyageBackend.utils.*;
import tech.dev.eVoyageBackend.utils.contract.*;
import tech.dev.eVoyageBackend.utils.dto.*;
import tech.dev.eVoyageBackend.utils.dto.transformer.*;
import tech.dev.eVoyageBackend.utils.enums.*;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

/**
BUSINESS for table "api_user"
 * 
 * @author Geo
 *
 */
@Log
@Component
public class ApiUserBusiness implements IBasicBusiness<Request<ApiUserDto>, Response<ApiUserDto>> {

	private Response<ApiUserDto> response;
	@Autowired
	private ApiUserRepository apiUserRepository;
	@Autowired
	private FunctionalError functionalError;
	@Autowired
	private TechnicalError technicalError;
	@Autowired
	private ExceptionUtils exceptionUtils;
	@PersistenceContext
	private EntityManager em;

	private SimpleDateFormat dateFormat;
	private SimpleDateFormat dateTimeFormat;

	private static final Logger log = LoggerFactory.getLogger(UserBusiness.class);

	public ApiUserBusiness() {
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	}
	
	/**
	 * create ApiUser by using ApiUserDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<ApiUserDto> create(Request<ApiUserDto> request, Locale locale)  throws ParseException {
		log.info("----begin create ApiUser-----");

		Response<ApiUserDto> response = new Response<ApiUserDto>();
		List<ApiUser>        items    = new ArrayList<ApiUser>();
			
		for (ApiUserDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, Object> fieldsToVerify = new HashMap<String, Object>();
			fieldsToVerify.put("dateSendCodeOtpAt", dto.getDateSendCodeOtpAt());
			fieldsToVerify.put("email", dto.getEmail());
			fieldsToVerify.put("firstConnection", dto.getFirstConnection());
			fieldsToVerify.put("isActive", dto.getIsActive());
			fieldsToVerify.put("isConnected", dto.getIsConnected());
			fieldsToVerify.put("isDefaultPassword", dto.getIsDefaultPassword());
			fieldsToVerify.put("isLocked", dto.getIsLocked());
			fieldsToVerify.put("isValidPassCode", dto.getIsValidPassCode());
			fieldsToVerify.put("isValidToken", dto.getIsValidToken());
			fieldsToVerify.put("lastActivityDate", dto.getLastActivityDate());
			fieldsToVerify.put("lastConnectionDate", dto.getLastConnectionDate());
			fieldsToVerify.put("lastLockDate", dto.getLastLockDate());
			fieldsToVerify.put("login", dto.getLogin());
			fieldsToVerify.put("loginAttempts", dto.getLoginAttempts());
			fieldsToVerify.put("otpCode", dto.getOtpCode());
			fieldsToVerify.put("passCode", dto.getPassCode());
			fieldsToVerify.put("passCodeCreatedAt", dto.getPassCodeCreatedAt());
			fieldsToVerify.put("passCodeExpireAt", dto.getPassCodeExpireAt());
			fieldsToVerify.put("password", dto.getPassword());
			fieldsToVerify.put("searchString", dto.getSearchString());
			fieldsToVerify.put("telephone", dto.getTelephone());
			fieldsToVerify.put("token", dto.getToken());
			fieldsToVerify.put("tokenCreatedAt", dto.getTokenCreatedAt());
			fieldsToVerify.put("tokenExpireAt", dto.getTokenExpireAt());
			fieldsToVerify.put("roleId", dto.getRoleId());
			fieldsToVerify.put("type", dto.getType());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verify if apiUser to insert do not exist
			ApiUser existingEntity = null;
			if (existingEntity != null) {
				response.setStatus(functionalError.DATA_EXIST("apiUser id -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			// verif unique email in db
			existingEntity = apiUserRepository.findByEmail(dto.getEmail(), false);
			if (existingEntity != null) {
				response.setStatus(functionalError.DATA_EXIST("apiUser email -> " + dto.getEmail(), locale));
				response.setHasError(true);
				return response;
			}
			// verif unique email in items to save
			if (items.stream().anyMatch(a -> a.getEmail().equalsIgnoreCase(dto.getEmail()))) {
				response.setStatus(functionalError.DATA_DUPLICATE(" email ", locale));
				response.setHasError(true);
				return response;
			}

			// verif unique login in db
			existingEntity = apiUserRepository.findByLogin(dto.getLogin(), false);
			if (existingEntity != null) {
				response.setStatus(functionalError.DATA_EXIST("apiUser login -> " + dto.getLogin(), locale));
				response.setHasError(true);
				return response;
			}
			// verif unique login in items to save
			if (items.stream().anyMatch(a -> a.getLogin().equalsIgnoreCase(dto.getLogin()))) {
				response.setStatus(functionalError.DATA_DUPLICATE(" login ", locale));
				response.setHasError(true);
				return response;
			}

				ApiUser entityToSave = null;
			entityToSave = ApiUserTransformer.INSTANCE.toEntity(dto);
			entityToSave.setCreatedAt(Utilities.getCurrentDate());
			entityToSave.setCreatedBy(request.getUser());
			entityToSave.setIsDeleted(false);
			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<ApiUser> itemsSaved = null;
			// inserer les donnees en base de donnees
			itemsSaved = apiUserRepository.saveAll((Iterable<ApiUser>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("apiUser", locale));
				response.setHasError(true);
				return response;
			}
			List<ApiUserDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? ApiUserTransformer.INSTANCE.toLiteDtos(itemsSaved) : ApiUserTransformer.INSTANCE.toDtos(itemsSaved);

			final int size = itemsSaved.size();
			List<String>  listOfError      = Collections.synchronizedList(new ArrayList<String>());
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
			response.setHasError(false);
		}

		log.info("----end create ApiUser-----");
		return response;
	}

	/**
	 * update ApiUser by using ApiUserDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<ApiUserDto> update(Request<ApiUserDto> request, Locale locale)  throws ParseException {
		log.info("----begin update ApiUser-----");

		Response<ApiUserDto> response = new Response<ApiUserDto>();
		List<ApiUser>        items    = new ArrayList<ApiUser>();
			
		for (ApiUserDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, Object> fieldsToVerify = new HashMap<String, Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la apiUser existe
			ApiUser entityToSave = null;
			entityToSave = apiUserRepository.findOne(dto.getId(), false);
			if (entityToSave == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("apiUser id -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			if (dto.getCreatedBy() != null && dto.getCreatedBy() > 0) {
				entityToSave.setCreatedBy(dto.getCreatedBy());
			}
			if (Utilities.notBlank(dto.getDateSendCodeOtpAt())) {
				entityToSave.setDateSendCodeOtpAt(dateFormat.parse(dto.getDateSendCodeOtpAt()));
			}
			if (Utilities.notBlank(dto.getEmail())) {
				entityToSave.setEmail(dto.getEmail());
			}
			if (Utilities.notBlank(dto.getFirstConnection())) {
				entityToSave.setFirstConnection(dateFormat.parse(dto.getFirstConnection()));
			}
			if (dto.getIsActive() != null) {
				entityToSave.setIsActive(dto.getIsActive());
			}
			if (dto.getIsConnected() != null) {
				entityToSave.setIsConnected(dto.getIsConnected());
			}
			if (dto.getIsDefaultPassword() != null) {
				entityToSave.setIsDefaultPassword(dto.getIsDefaultPassword());
			}
			if (dto.getIsLocked() != null) {
				entityToSave.setIsLocked(dto.getIsLocked());
			}
			if (dto.getIsValidPassCode() != null) {
				entityToSave.setIsValidPassCode(dto.getIsValidPassCode());
			}
			if (Utilities.notBlank(dto.getIsValidToken())) {
				entityToSave.setIsValidToken(dto.getIsValidToken());
			}
			if (Utilities.notBlank(dto.getLastActivityDate())) {
				entityToSave.setLastActivityDate(dateFormat.parse(dto.getLastActivityDate()));
			}
			if (Utilities.notBlank(dto.getLastConnectionDate())) {
				entityToSave.setLastConnectionDate(dateFormat.parse(dto.getLastConnectionDate()));
			}
			if (Utilities.notBlank(dto.getLastLockDate())) {
				entityToSave.setLastLockDate(dateFormat.parse(dto.getLastLockDate()));
			}
			if (Utilities.notBlank(dto.getLogin())) {
				entityToSave.setLogin(dto.getLogin());
			}
			if (dto.getLoginAttempts() != null && dto.getLoginAttempts() > 0) {
				entityToSave.setLoginAttempts(dto.getLoginAttempts());
			}
			if (Utilities.notBlank(dto.getOtpCode())) {
				entityToSave.setOtpCode(dto.getOtpCode());
			}
			if (Utilities.notBlank(dto.getPassCode())) {
				entityToSave.setPassCode(dto.getPassCode());
			}
			if (Utilities.notBlank(dto.getPassCodeCreatedAt())) {
				entityToSave.setPassCodeCreatedAt(dateFormat.parse(dto.getPassCodeCreatedAt()));
			}
			if (Utilities.notBlank(dto.getPassCodeExpireAt())) {
				entityToSave.setPassCodeExpireAt(dateFormat.parse(dto.getPassCodeExpireAt()));
			}
			if (Utilities.notBlank(dto.getPassword())) {
				entityToSave.setPassword(dto.getPassword());
			}
			if (Utilities.notBlank(dto.getSearchString())) {
				entityToSave.setSearchString(dto.getSearchString());
			}
			if (Utilities.notBlank(dto.getTelephone())) {
				entityToSave.setTelephone(dto.getTelephone());
			}
			if (Utilities.notBlank(dto.getToken())) {
				entityToSave.setToken(dto.getToken());
			}
			if (Utilities.notBlank(dto.getTokenCreatedAt())) {
				entityToSave.setTokenCreatedAt(dateFormat.parse(dto.getTokenCreatedAt()));
			}
			if (Utilities.notBlank(dto.getTokenExpireAt())) {
				entityToSave.setTokenExpireAt(dateFormat.parse(dto.getTokenExpireAt()));
			}
			if (dto.getUpdatedBy() != null && dto.getUpdatedBy() > 0) {
				entityToSave.setUpdatedBy(dto.getUpdatedBy());
			}
			if (dto.getRoleId() != null && dto.getRoleId() > 0) {
				entityToSave.setRoleId(dto.getRoleId());
			}
			if (Utilities.notBlank(dto.getType())) {
				entityToSave.setType(dto.getType());
			}
			entityToSave.setUpdatedAt(Utilities.getCurrentDate());
			entityToSave.setUpdatedBy(request.getUser());
			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<ApiUser> itemsSaved = null;
			// maj les donnees en base
			itemsSaved = apiUserRepository.saveAll((Iterable<ApiUser>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("apiUser", locale));
				response.setHasError(true);
				return response;
			}
			List<ApiUserDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? ApiUserTransformer.INSTANCE.toLiteDtos(itemsSaved) : ApiUserTransformer.INSTANCE.toDtos(itemsSaved);

			final int size = itemsSaved.size();
			List<String>  listOfError      = Collections.synchronizedList(new ArrayList<String>());
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
			response.setHasError(false);
		}

		log.info("----end update ApiUser-----");
		return response;
	}

	/**
	 * delete ApiUser by using ApiUserDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<ApiUserDto> delete(Request<ApiUserDto> request, Locale locale)  {
		log.info("----begin delete ApiUser-----");

		Response<ApiUserDto> response = new Response<ApiUserDto>();
		List<ApiUser>        items    = new ArrayList<ApiUser>();
			
		for (ApiUserDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, Object> fieldsToVerify = new HashMap<String, Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la apiUser existe
			ApiUser existingEntity = null;
			existingEntity = apiUserRepository.findOne(dto.getId(), false);
			if (existingEntity == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("apiUser -> " + dto.getId(), locale));
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
			apiUserRepository.saveAll((Iterable<ApiUser>) items);

			response.setHasError(false);
		}

		log.info("----end delete ApiUser-----");
		return response;
	}

	/**
	 * get ApiUser by using ApiUserDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<ApiUserDto> getByCriteria(Request<ApiUserDto> request, Locale locale)  throws Exception {
		log.info("----begin get ApiUser-----");

		Response<ApiUserDto> response = new Response<ApiUserDto>();
		List<ApiUser> items 			 = apiUserRepository.getByCriteria(request, em, locale);

		if (items != null && !items.isEmpty()) {
			List<ApiUserDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? ApiUserTransformer.INSTANCE.toLiteDtos(items) : ApiUserTransformer.INSTANCE.toDtos(items);

			final int size = items.size();
			List<String>  listOfError      = Collections.synchronizedList(new ArrayList<String>());
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
			response.setCount(apiUserRepository.count(request, em, locale));
			response.setHasError(false);
		} else {
			response.setStatus(functionalError.DATA_EMPTY("apiUser", locale));
			response.setHasError(false);
			return response;
		}

		log.info("----end get ApiUser-----");
		return response;
	}


		@SuppressWarnings("unused")
	public Response<ApiUserDto> connexion(Request<ApiUserDto> request, Locale locale) throws UnsupportedEncodingException {
		log.info("----begin login User-----");
		log.debug("This is a debug connexion");

		Response<ApiUserDto> response = new Response<>();
		try {
			ApiUserDto dto = request.getData();
			log.debug("Début de la méthode de connexion pour l'utilisateur: {}", dto.getLogin());

			// Validation des champs obligatoires
			Map<String, Object> fieldsToVerify = Map.of(
				"login", dto.getLogin(),
				"password", dto.getPassword()
			);

			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			ApiUser userToConnect = apiUserRepository.findByLogin(dto.getLogin(), false);
			if (userToConnect == null) {
				response.setStatus(functionalError.DISALLOWED_OPERATION("Login incorrect", locale));
				response.setHasError(true);
				return response;
			}

			if (Boolean.TRUE.equals(userToConnect.getIsLocked())) {
				response.setStatus(functionalError.DATA_NOT_EXIST("Votre compte est verrouillé, contactez l'administrateur.", locale));
				response.setHasError(true);
				return response;
			}

			if (userToConnect.getPassword() == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("Aucun mot de passe défini pour cet utilisateur.", locale));
				response.setHasError(true);
				return response;
			}

			// Vérification du mot de passe
			if (userToConnect.getPassword().equals(Utilities.encrypt(dto.getPassword()))) {
				ApiUserDto useritemDto = new ApiUserDto();
				String token = Utilities.generateAlphanumericCodeLite(256);
				ZonedDateTime tokenCreateDate = ZonedDateTime.now();
				ZonedDateTime datePlus40Minutes = tokenCreateDate.plus(40, ChronoUnit.MINUTES);

				userToConnect.setToken(token);
				userToConnect.setTokenCreatedAt(Date.from(tokenCreateDate.toInstant()));
				userToConnect.setTokenExpireAt(Date.from(datePlus40Minutes.toInstant()));

				// Mise à jour de l'utilisateur dans la base de données
				apiUserRepository.save(userToConnect);

				// Remplissage de la réponse
				useritemDto.setToken(token);
				useritemDto.setTokenExpireAt(userToConnect.getTokenExpireAt().toString());
				response.setItem(useritemDto);
				response.setStatus(functionalError.SUCCESS("Success",locale));
				response.setHasError(false);
			} else {
				response.setStatus(functionalError.DISALLOWED_OPERATION("Mot de passe incorrect", locale));
				response.setHasError(true);
			}
		} catch (Exception e) {
			log.error("Erreur lors de la connexion de l'utilisateur", e);
			response.setStatus(functionalError.AUTH_FAIL("Erreur lors de la connexion de l'utilisateur", locale));
			response.setHasError(true);
		}

		log.info("----end login User-----");
		return response;
	}


	/**
	 * get full ApiUserDto by using ApiUser as object.
	 * 
	 * @param dto
	 * @param size
	 * @param isSimpleLoading
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	private ApiUserDto getFullInfos(ApiUserDto dto, Integer size, Boolean isSimpleLoading, Locale locale) throws Exception {
		// put code here

		if (Utilities.isTrue(isSimpleLoading)) {
			return dto;
		}
		if (size > 1) {
			return dto;
		}

		return dto;
	}


}
