                                                                    															
/*
 * Java business for entity table companies 
 * Created on 2025-01-12 ( Time 17:39:52 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2018 Geo. All Rights Reserved.
 */

package tech.dev.eVoyageBackend.business;

import lombok.extern.java.Log;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.apache.commons.io.FilenameUtils;

import javax.imageio.ImageIO;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import tech.dev.eVoyageBackend.utils.*;
import tech.dev.eVoyageBackend.utils.dto.*;
import tech.dev.eVoyageBackend.utils.enums.*;
import tech.dev.eVoyageBackend.utils.contract.*;
import tech.dev.eVoyageBackend.utils.contract.IBasicBusiness;
import tech.dev.eVoyageBackend.utils.contract.Request;
import tech.dev.eVoyageBackend.utils.contract.Response;
import tech.dev.eVoyageBackend.utils.dto.transformer.*;
import tech.dev.eVoyageBackend.dao.entity.Companies;
import tech.dev.eVoyageBackend.dao.entity.*;
import tech.dev.eVoyageBackend.dao.repository.*;

/**
BUSINESS for table "companies"
 * 
 * @author Geo
 *
 */
@Log
@Component
public class CompaniesBusiness implements IBasicBusiness<Request<CompaniesDto>, Response<CompaniesDto>> {

	private Response<CompaniesDto> response;
	@Autowired
	private CompaniesRepository companiesRepository;
	@Autowired
	private FinancialReportsRepository financialReportsRepository;
	@Autowired
	private TicketsRepository ticketsRepository;
	@Autowired
	private BusesRepository busesRepository;
	@Autowired
	private UsersRepository usersRepository;
	@Autowired
	private TripTrackingRepository tripTrackingRepository;
	@Autowired
	private PaymentsRepository paymentsRepository;
	@Autowired
	private BookingsRepository bookingsRepository;
	@Autowired
	private AlertsRepository alertsRepository;
	@Autowired
	private TravelSchedulesRepository travelSchedulesRepository;
	@Autowired
	private RoutesRepository routesRepository;
	@Autowired
	private DepartsRepository departsRepository;
	@Autowired
	private NotificationsRepository notificationsRepository;
	@Autowired
	private FunctionalError functionalError;
	@Autowired
	private ParamsUtils paramsUtils;
	@Autowired
	private TechnicalError technicalError;
	@Autowired
	private ExceptionUtils exceptionUtils;
	@PersistenceContext
	private EntityManager em;

	private SimpleDateFormat dateFormat;
	private SimpleDateFormat dateTimeFormat;

	public CompaniesBusiness() {
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	}
	
	/**
	 * create Companies by using CompaniesDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<CompaniesDto> create(Request<CompaniesDto> request, Locale locale) throws ParseException {
		log.info("----begin create Companies-----");

		Response<CompaniesDto> response = new Response<CompaniesDto>();
		List<Companies> items = new ArrayList<Companies>();

		// Chemin du dossier où les logos seront stockés
		String logoDirectoryPath = paramsUtils.getTiketsRopository(); // Remplacez par le chemin réel
		File logoDirectory = new File(logoDirectoryPath);

		// Créer le dossier s'il n'existe pas
		if (!logoDirectory.exists()) {
			logoDirectory.mkdirs();
		}

		// Taille maximale autorisée pour les fichiers (par exemple, 5 Mo)
		final long MAX_FILE_SIZE = 5 * 1024 * 1024; // 5 Mo

		for (CompaniesDto dto : request.getDatas()) {
			// Vérification des champs obligatoires
			Map<String, Object> fieldsToVerify = new HashMap<String, Object>();
			fieldsToVerify.put("name", dto.getName());
			fieldsToVerify.put("address", dto.getAddress());
			fieldsToVerify.put("contact", dto.getContact());

			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Vérification si l'entreprise existe déjà
			Companies existingEntity = companiesRepository.findByName(dto.getName(), false);
			if (existingEntity != null) {
				response.setStatus(functionalError.DATA_EXIST("companies name -> " + dto.getName(), locale));
				response.setHasError(true);
				return response;
			}

			// Vérification du nom unique dans les éléments à sauvegarder
			if (items.stream().anyMatch(a -> a.getName().equalsIgnoreCase(dto.getName()))) {
				response.setStatus(functionalError.DATA_DUPLICATE(" name ", locale));
				response.setHasError(true);
				return response;
			}

			// Enregistrement du logo
			if (dto.getLogoPath() != null && !dto.getLogoPath().isEmpty()) {
				try {
					// Détecter le type MIME et l'extension à partir des données base64
					String base64Data = dto.getLogoPath();
					String fileExtension = ".png"; // Par défaut
					String mimeType = null;

					if (base64Data.startsWith("data:image")) {
						// Extraire le type MIME (par exemple, "image/png" ou "image/jpeg")
						mimeType = base64Data.split(";")[0].split(":")[1];
						switch (mimeType) {
							case "image/png":
								fileExtension = ".png";
								break;
							case "image/jpeg":
								fileExtension = ".jpg";
								break;
							case "image/gif":
								fileExtension = ".gif";
								break;
							default:
								throw new IllegalArgumentException("Unsupported image format: " + mimeType);
						}
						// Supprimer le préfixe "data:image/...;base64,"
						base64Data = base64Data.split(",")[1];
					}

					// Vérifier la taille des données base64
					if (base64Data.length() > MAX_FILE_SIZE * 4 / 3) { // Base64 augmente la taille d'environ 33%
						response.setStatus(functionalError.FILE_TOO_LARGE("logo file", locale));
						response.setHasError(true);
						return response;
					}

					// Décoder les données base64
					byte[] decodedBytes = Base64.getDecoder().decode(base64Data);

					// Vérifier que les données décodées correspondent à une image valide
					if (!isValidImage(decodedBytes, mimeType)) {
						response.setStatus(functionalError.INVALID_FILE_TYPE("logo file", locale));
						response.setHasError(true);
						return response;
					}

					// Générer un nom de fichier unique avec la bonne extension
					String uniqueFileName = UUID.randomUUID().toString() + fileExtension;
					File logoFile = new File(logoDirectory, uniqueFileName);

					// Écrire les données décodées dans un fichier
					try (OutputStream out = new FileOutputStream(logoFile)) {
						out.write(decodedBytes);
					}

					// Mettre à jour le chemin du logo dans l'entité
					dto.setLogoPath(logoDirectoryPath + "/" + uniqueFileName);
				} catch (IOException | IllegalArgumentException e) {
					response.setStatus(functionalError.SAVE_FAIL("logo file", locale));
					response.setHasError(true);
					return response;
				}
			}

			// Transformation de DTO en entité et ajout des métadonnées
			Companies entityToSave = CompaniesTransformer.INSTANCE.toEntity(dto);
			entityToSave.setCreatedAt(Utilities.getCurrentDate());
			entityToSave.setCreatedBy(request.getUser());
			entityToSave.setIsDeleted(false);
			items.add(entityToSave);
		}

		// Sauvegarde des entreprises dans la base de données
		if (!items.isEmpty()) {
			List<Companies> itemsSaved = companiesRepository.saveAll(items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("companies", locale));
				response.setHasError(true);
				return response;
			}

			// Transformation des entités sauvegardées en DTOs
			List<CompaniesDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ?
					CompaniesTransformer.INSTANCE.toLiteDtos(itemsSaved) :
					CompaniesTransformer.INSTANCE.toDtos(itemsSaved);

			// Récupération des informations complètes pour chaque DTO
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

			// Gestion des erreurs lors de la récupération des informations complètes
			if (Utilities.isNotEmpty(listOfError)) {
				Object[] objArray = listOfError.stream().distinct().toArray();
				throw new RuntimeException(StringUtils.join(objArray, ", "));
			}

			// Retour de la réponse avec les données sauvegardées
			response.setItems(itemsDto);
			response.setHasError(false);
		}

		log.info("----end create Companies-----");
		return response;
	}

	/**
	 * Vérifie si les données décodées correspondent à une image valide.
	 *
	 * @param data     Les données binaires de l'image.
	 * @param mimeType Le type MIME de l'image (par exemple, "image/png").
	 * @return true si les données sont une image valide, sinon false.
	 */
	private boolean isValidImage(byte[] data, String mimeType) {
		try (InputStream is = new ByteArrayInputStream(data)) {
			// Vérifier que les données peuvent être lues comme une image
			BufferedImage image = ImageIO.read(is);
			return image != null; // Si l'image est lue correctement, elle est valide
		} catch (IOException e) {
			return false; // Si une exception est levée, les données ne sont pas une image valide
		}
	}

	/**
	 * update Companies by using CompaniesDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<CompaniesDto> update(Request<CompaniesDto> request, Locale locale) throws ParseException {
		log.info("----begin update Companies-----");

		Response<CompaniesDto> response = new Response<CompaniesDto>();
		List<Companies> items = new ArrayList<>();

		// Chemin du dossier où les logos sont stockés
		String logoDirectoryPath = paramsUtils.getTiketsRopository(); // Remplacez par le chemin réel
		File logoDirectory = new File(logoDirectoryPath);

		if (!logoDirectory.exists()) {
			logoDirectory.mkdirs(); // Création du répertoire si inexistant
		}

		final long MAX_FILE_SIZE = 5 * 1024 * 1024; // 5 Mo

		for (CompaniesDto dto : request.getDatas()) {
			// Vérification des champs obligatoires
			Map<String, Object> fieldsToVerify = new HashMap<>();
			fieldsToVerify.put("id", dto.getId());

			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Vérification si l'entreprise existe
			Companies entityToSave = companiesRepository.findOne(dto.getId(), false);
			if (entityToSave == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("companies id -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			// Mise à jour des champs modifiables
			if (Utilities.notBlank(dto.getName())) entityToSave.setName(dto.getName());
			if (Utilities.notBlank(dto.getAddress())) entityToSave.setAddress(dto.getAddress());
			if (Utilities.notBlank(dto.getContact())) entityToSave.setContact(dto.getContact());
			if (Utilities.notBlank(dto.getLicenseNumber())) entityToSave.setLicenseNumber(dto.getLicenseNumber());
			if (dto.getRating() != null && dto.getRating() > 0) entityToSave.setRating(dto.getRating());
			if (Utilities.notBlank(dto.getStatus())) entityToSave.setStatus(dto.getStatus());
			if (dto.getUpdatedBy() != null && dto.getUpdatedBy() > 0) entityToSave.setUpdatedBy(dto.getUpdatedBy());
			entityToSave.setUpdatedAt(Utilities.getCurrentDate());
			entityToSave.setUpdatedBy(request.getUser());

			// Gestion de la mise à jour du logo
			if (dto.getLogoPath() != null && !dto.getLogoPath().isEmpty()) {
				try {
					String base64Data = dto.getLogoPath();
					String fileExtension = ".png"; // Par défaut
					String mimeType = null;

					if (base64Data.startsWith("data:image")) {
						mimeType = base64Data.split(";")[0].split(":")[1];
						switch (mimeType) {
							case "image/png":
								fileExtension = ".png";
								break;
							case "image/jpeg":
								fileExtension = ".jpg";
								break;
							case "image/gif":
								fileExtension = ".gif";
								break;
							default:
								throw new IllegalArgumentException("Unsupported image format: " + mimeType);
						}
						base64Data = base64Data.split(",")[1]; // Supprime le préfixe base64
					}

					// Vérification de la taille du fichier
					if (base64Data.length() > MAX_FILE_SIZE * 4 / 3) {
						response.setStatus(functionalError.FILE_TOO_LARGE("logo file", locale));
						response.setHasError(true);
						return response;
					}

					// Décodage du fichier base64
					byte[] decodedBytes = Base64.getDecoder().decode(base64Data);

					if (!isValidImage(decodedBytes, mimeType)) {
						response.setStatus(functionalError.INVALID_FILE_TYPE("logo file", locale));
						response.setHasError(true);
						return response;
					}

					// Suppression de l'ancien logo s'il existe
					if (entityToSave.getLogoPath() != null) {
						File oldLogoFile = new File(entityToSave.getLogoPath());
						if (oldLogoFile.exists()) {
							oldLogoFile.delete();
						}
					}

					// Génération du nouveau nom de fichier
					String uniqueFileName = UUID.randomUUID().toString() + fileExtension;
					File newLogoFile = new File(logoDirectory, uniqueFileName);

					// Écriture du fichier
					try (OutputStream out = new FileOutputStream(newLogoFile)) {
						out.write(decodedBytes);
					}

					// Mise à jour du chemin du logo dans l'entité
					entityToSave.setLogoPath(logoDirectoryPath + "/" + uniqueFileName);
				} catch (IOException | IllegalArgumentException e) {
					response.setStatus(functionalError.SAVE_FAIL("logo file", locale));
					response.setHasError(true);
					return response;
				}
			}

			items.add(entityToSave);
		}

		// Mise à jour des enregistrements en base de données
		if (!items.isEmpty()) {
			List<Companies> itemsSaved = companiesRepository.saveAll(items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("companies", locale));
				response.setHasError(true);
				return response;
			}

			List<CompaniesDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ?
					CompaniesTransformer.INSTANCE.toLiteDtos(itemsSaved) :
					CompaniesTransformer.INSTANCE.toDtos(itemsSaved);

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
			response.setHasError(false);
		}

		log.info("----end update Companies-----");
		return response;
	}



	/**
	 * delete Companies by using CompaniesDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<CompaniesDto> delete(Request<CompaniesDto> request, Locale locale)  {
		log.info("----begin delete Companies-----");

		Response<CompaniesDto> response = new Response<CompaniesDto>();
		List<Companies>        items    = new ArrayList<Companies>();
			
		for (CompaniesDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, Object> fieldsToVerify = new HashMap<String, Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la companies existe
			Companies existingEntity = null;
			existingEntity = companiesRepository.findOne(dto.getId(), false);
			if (existingEntity == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("companies -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			// -----------------------------------------------------------------------
			// ----------- CHECK IF DATA IS USED
			// -----------------------------------------------------------------------

			// financialReports
			List<FinancialReports> listOfFinancialReports = financialReportsRepository.findByCompanyId(existingEntity.getId(), false);
			if (listOfFinancialReports != null && !listOfFinancialReports.isEmpty()){
				response.setStatus(functionalError.DATA_NOT_DELETABLE("(" + listOfFinancialReports.size() + ")", locale));
				response.setHasError(true);
				return response;
			}
			// tickets
			List<Tickets> listOfTickets = ticketsRepository.findByCompanyId(existingEntity.getId(), false);
			if (listOfTickets != null && !listOfTickets.isEmpty()){
				response.setStatus(functionalError.DATA_NOT_DELETABLE("(" + listOfTickets.size() + ")", locale));
				response.setHasError(true);
				return response;
			}
			// buses
			List<Buses> listOfBuses = busesRepository.findByCompanyId(existingEntity.getId(), false);
			if (listOfBuses != null && !listOfBuses.isEmpty()){
				response.setStatus(functionalError.DATA_NOT_DELETABLE("(" + listOfBuses.size() + ")", locale));
				response.setHasError(true);
				return response;
			}
			// users
			List<Users> listOfUsers = usersRepository.findByCompanyId(existingEntity.getId(), false);
			if (listOfUsers != null && !listOfUsers.isEmpty()){
				response.setStatus(functionalError.DATA_NOT_DELETABLE("(" + listOfUsers.size() + ")", locale));
				response.setHasError(true);
				return response;
			}
			// tripTracking
			List<TripTracking> listOfTripTracking = tripTrackingRepository.findByCompanyId(existingEntity.getId());
			if (listOfTripTracking != null && !listOfTripTracking.isEmpty()){
				response.setStatus(functionalError.DATA_NOT_DELETABLE("(" + listOfTripTracking.size() + ")", locale));
				response.setHasError(true);
				return response;
			}
			// payments
			List<Payments> listOfPayments = paymentsRepository.findByCompanyId(existingEntity.getId(), false);
			if (listOfPayments != null && !listOfPayments.isEmpty()){
				response.setStatus(functionalError.DATA_NOT_DELETABLE("(" + listOfPayments.size() + ")", locale));
				response.setHasError(true);
				return response;
			}
			// bookings
			List<Bookings> listOfBookings = bookingsRepository.findByCompanyId(existingEntity.getId(), false);
			if (listOfBookings != null && !listOfBookings.isEmpty()){
				response.setStatus(functionalError.DATA_NOT_DELETABLE("(" + listOfBookings.size() + ")", locale));
				response.setHasError(true);
				return response;
			}
			// alerts
			List<Alerts> listOfAlerts = alertsRepository.findByCompanyId(existingEntity.getId(), false);
			if (listOfAlerts != null && !listOfAlerts.isEmpty()){
				response.setStatus(functionalError.DATA_NOT_DELETABLE("(" + listOfAlerts.size() + ")", locale));
				response.setHasError(true);
				return response;
			}
			// travelSchedules
			List<TravelSchedules> listOfTravelSchedules = travelSchedulesRepository.findByCompanyId(existingEntity.getId());
			if (listOfTravelSchedules != null && !listOfTravelSchedules.isEmpty()){
				response.setStatus(functionalError.DATA_NOT_DELETABLE("(" + listOfTravelSchedules.size() + ")", locale));
				response.setHasError(true);
				return response;
			}
			// departs
			List<Departs> listOfDeparts = departsRepository.findByCompanyId(existingEntity.getId(), false);
			if (listOfDeparts != null && !listOfDeparts.isEmpty()){
				response.setStatus(functionalError.DATA_NOT_DELETABLE("(" + listOfDeparts.size() + ")", locale));
				response.setHasError(true);
				return response;
			}
			// notifications
			List<Notifications> listOfNotifications = notificationsRepository.findByCompanyId(existingEntity.getId(), false);
			if (listOfNotifications != null && !listOfNotifications.isEmpty()){
				response.setStatus(functionalError.DATA_NOT_DELETABLE("(" + listOfNotifications.size() + ")", locale));
				response.setHasError(true);
				return response;
			}


			existingEntity.setIsDeleted(true);
			existingEntity.setDeletedAt(Utilities.getCurrentDate());
			existingEntity.setDeletedBy(request.getUser());
			items.add(existingEntity);
		}

		if (!items.isEmpty()) {
			// supprimer les donnees en base
			companiesRepository.saveAll((Iterable<Companies>) items);

			response.setHasError(false);
		}

		log.info("----end delete Companies-----");
		return response;
	}

	/**
	 * get Companies by using CompaniesDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<CompaniesDto> getByCriteria(Request<CompaniesDto> request, Locale locale)  throws Exception {
		log.info("----begin get Companies-----");

		Response<CompaniesDto> response = new Response<CompaniesDto>();
		List<Companies> items 			 = companiesRepository.getByCriteria(request, em, locale);

		if (items != null && !items.isEmpty()) {
			List<CompaniesDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? CompaniesTransformer.INSTANCE.toLiteDtos(items) : CompaniesTransformer.INSTANCE.toDtos(items);

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
			response.setCount(companiesRepository.count(request, em, locale));
			response.setHasError(false);
		} else {
			response.setStatus(functionalError.DATA_EMPTY("companies", locale));
			response.setHasError(false);
			return response;
		}

		log.info("----end get Companies-----");
		return response;
	}

	/**
	 * get full CompaniesDto by using Companies as object.
	 * 
	 * @param dto
	 * @param size
	 * @param isSimpleLoading
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	private CompaniesDto getFullInfos(CompaniesDto dto, Integer size, Boolean isSimpleLoading, Locale locale) throws Exception {
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
