                                                                                        																					
/*
 * Java business for entity table logs 
 * Created on 2024-08-20 ( Time 10:24:21 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2018 Geo. All Rights Reserved.
 */

package tech.dev.eVoyageBackend.business;

import lombok.extern.java.Log;
import tech.dev.eVoyageBackend.business.fact.BusinessFactory;
import tech.dev.eVoyageBackend.dao.entity.Logs;
import tech.dev.eVoyageBackend.dao.repository.*;
import tech.dev.eVoyageBackend.utils.*;
import tech.dev.eVoyageBackend.utils.contract.IBasicBusiness;
import tech.dev.eVoyageBackend.utils.contract.Request;
import tech.dev.eVoyageBackend.utils.contract.Response;
import tech.dev.eVoyageBackend.utils.dto.*;
import tech.dev.eVoyageBackend.utils.dto.transformer.*;
import tech.dev.eVoyageBackend.utils.enums.FunctionalityEnum;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
BUSINESS for table "logs"
 * 
 * @author Geo
 *
 */
@Log
@Component
public class LogsBusiness implements IBasicBusiness<Request<LogsDto>, Response<LogsDto>> {


	@Value("${logs.directory}")
	private String logsDirectoy;

	private Response<LogsDto> response;
	@Autowired
	private LogsRepository logsRepository;
	@Autowired
	private FunctionalError functionalError;
	@Autowired
	private TechnicalError technicalError;
	@Autowired
	private ExceptionUtils exceptionUtils;
	@PersistenceContext
	private EntityManager em;
	@Autowired private BusinessFactory businessFactory;

	private SimpleDateFormat dateFormat;
	private SimpleDateFormat dateTimeFormat;

	public LogsBusiness() {
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	}
	
	/**
	 * create Logs by using LogsDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<LogsDto> create(Request<LogsDto> request, Locale locale)  throws ParseException {
		log.info("----begin create Logs-----");

		Response<LogsDto> response = new Response<LogsDto>();
		List<Logs>        items    = new ArrayList<Logs>();
			
		for (LogsDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, Object> fieldsToVerify = new HashMap<String, Object>();
			fieldsToVerify.put("actionService", dto.getActionService());
			fieldsToVerify.put("date", dto.getDate());
			fieldsToVerify.put("idStatus", dto.getIdStatus());
			fieldsToVerify.put("ipadress", dto.getIpadress());
			fieldsToVerify.put("isConnexion", dto.getIsConnexion());
			fieldsToVerify.put("login", dto.getLogin());
			fieldsToVerify.put("machine", dto.getMachine());
			fieldsToVerify.put("nom", dto.getNom());
			fieldsToVerify.put("prenom", dto.getPrenom());
			fieldsToVerify.put("request", dto.getRequest());
			fieldsToVerify.put("response", dto.getResponse());
			fieldsToVerify.put("searchString", dto.getSearchString());
			fieldsToVerify.put("statusConnection", dto.getStatusConnection());
			fieldsToVerify.put("uri", dto.getUri());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verify if logs to insert do not exist
			Logs existingEntity = null;
			if (existingEntity != null) {
				response.setStatus(functionalError.DATA_EXIST("logs id -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			// verif unique login in db
			existingEntity = logsRepository.findByLogin(dto.getLogin(), false);
			if (existingEntity != null) {
				response.setStatus(functionalError.DATA_EXIST("logs login -> " + dto.getLogin(), locale));
				response.setHasError(true);
				return response;
			}
			// verif unique login in items to save
			if (items.stream().anyMatch(a -> a.getLogin().equalsIgnoreCase(dto.getLogin()))) {
				response.setStatus(functionalError.DATA_DUPLICATE(" login ", locale));
				response.setHasError(true);
				return response;
			}

				Logs entityToSave = null;
			entityToSave = LogsTransformer.INSTANCE.toEntity(dto);
			entityToSave.setCreatedAt(Utilities.getCurrentDate());
			entityToSave.setCreatedBy(request.getUser());
			entityToSave.setIsDeleted(false);
			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<Logs> itemsSaved = null;
			// inserer les donnees en base de donnees
			itemsSaved = logsRepository.saveAll((Iterable<Logs>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("logs", locale));
				response.setHasError(true);
				return response;
			}
			List<LogsDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? LogsTransformer.INSTANCE.toLiteDtos(itemsSaved) : LogsTransformer.INSTANCE.toDtos(itemsSaved);

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

		log.info("----end create Logs-----");
		return response;
	}

	/**
	 * update Logs by using LogsDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<LogsDto> update(Request<LogsDto> request, Locale locale)  throws ParseException {
		log.info("----begin update Logs-----");

		Response<LogsDto> response = new Response<LogsDto>();
		List<Logs>        items    = new ArrayList<Logs>();
			
		for (LogsDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, Object> fieldsToVerify = new HashMap<String, Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la logs existe
			Logs entityToSave = null;
			entityToSave = logsRepository.findOne(dto.getId(), false);
			if (entityToSave == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("logs id -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			if (Utilities.notBlank(dto.getActionService())) {
				entityToSave.setActionService(dto.getActionService());
			}
			if (dto.getCreatedBy() != null && dto.getCreatedBy() > 0) {
				entityToSave.setCreatedBy(dto.getCreatedBy());
			}
			if (Utilities.notBlank(dto.getDate())) {
				entityToSave.setDate(dateFormat.parse(dto.getDate()));
			}
			if (dto.getIdStatus() != null && dto.getIdStatus() > 0) {
				entityToSave.setIdStatus(dto.getIdStatus());
			}
			if (Utilities.notBlank(dto.getIpadress())) {
				entityToSave.setIpadress(dto.getIpadress());
			}
			if (dto.getIsConnexion() != null) {
				entityToSave.setIsConnexion(dto.getIsConnexion());
			}
			if (Utilities.notBlank(dto.getLogin())) {
				entityToSave.setLogin(dto.getLogin());
			}
			if (Utilities.notBlank(dto.getMachine())) {
				entityToSave.setMachine(dto.getMachine());
			}
			if (Utilities.notBlank(dto.getNom())) {
				entityToSave.setNom(dto.getNom());
			}
			if (Utilities.notBlank(dto.getPrenom())) {
				entityToSave.setPrenom(dto.getPrenom());
			}
			if (Utilities.notBlank(dto.getRequest())) {
				entityToSave.setRequest(dto.getRequest());
			}
			if (Utilities.notBlank(dto.getResponse())) {
				entityToSave.setResponse(dto.getResponse());
			}
			if (Utilities.notBlank(dto.getSearchString())) {
				entityToSave.setSearchString(dto.getSearchString());
			}
			if (Utilities.notBlank(dto.getStatusConnection())) {
				entityToSave.setStatusConnection(dto.getStatusConnection());
			}
			if (dto.getUpdatedBy() != null && dto.getUpdatedBy() > 0) {
				entityToSave.setUpdatedBy(dto.getUpdatedBy());
			}
			if (Utilities.notBlank(dto.getUri())) {
				entityToSave.setUri(dto.getUri());
			}
			entityToSave.setUpdatedAt(Utilities.getCurrentDate());
			entityToSave.setUpdatedBy(request.getUser());
			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<Logs> itemsSaved = null;
			// maj les donnees en base
			itemsSaved = logsRepository.saveAll((Iterable<Logs>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("logs", locale));
				response.setHasError(true);
				return response;
			}
			List<LogsDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? LogsTransformer.INSTANCE.toLiteDtos(itemsSaved) : LogsTransformer.INSTANCE.toDtos(itemsSaved);

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

		log.info("----end update Logs-----");
		return response;
	}

	/**
	 * delete Logs by using LogsDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<LogsDto> delete(Request<LogsDto> request, Locale locale)  {
		log.info("----begin delete Logs-----");

		Response<LogsDto> response = new Response<LogsDto>();
		List<Logs>        items    = new ArrayList<Logs>();
			
		for (LogsDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, Object> fieldsToVerify = new HashMap<String, Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la logs existe
			Logs existingEntity = null;
			existingEntity = logsRepository.findOne(dto.getId(), false);
			if (existingEntity == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("logs -> " + dto.getId(), locale));
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
			logsRepository.saveAll((Iterable<Logs>) items);

			response.setHasError(false);
		}

		log.info("----end delete Logs-----");
		return response;
	}

	/**
	 * get Logs by using LogsDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<LogsDto> getByCriteria(Request<LogsDto> request, Locale locale)  throws Exception {
		log.info("----begin get Logs-----");

		Response<LogsDto> response = new Response<LogsDto>();
		List<Logs> items 			 = logsRepository.getByCriteria(request, em, locale);

		if (items != null && !items.isEmpty()) {
			List<LogsDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? LogsTransformer.INSTANCE.toLiteDtos(items) : LogsTransformer.INSTANCE.toDtos(items);

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
			response.setCount(logsRepository.count(request, em, locale));
			response.setHasError(false);
		} else {
			response.setStatus(functionalError.DATA_EMPTY("logs", locale));
			response.setHasError(false);
			return response;
		}

		log.info("----end get Logs-----");
		return response;
	}

	/**
	 * get full LogsDto by using Logs as object.
	 * 
	 * @param dto
	 * @param size
	 * @param isSimpleLoading
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	private LogsDto getFullInfos(LogsDto dto, Integer size, Boolean isSimpleLoading, Locale locale) throws Exception {
		// put code here

		if (Utilities.isTrue(isSimpleLoading)) {
			return dto;
		}
		if (size > 1) {
			return dto;
		}

		return dto;
	}

	
	
	
	public Response<LogsDto> getLogDetail(Request<LogsDto> request, Locale locale) {
		log.info("----begin getLogsDetail-----");
	
		
		Response<LogsDto> response = new Response<>();
		LogsDto dto = request.getData();
	
		// Validation de l'ID de Logs
		Integer logId = dto.getId();
		if (logId == null || logId < 0) {
			return createErrorResponse(response, functionalError.FIELD_EMPTY("id", locale));
		}
	
		// Utilisation d'Optional pour éviter les null
		Optional<Logs> optionalLog = Optional.ofNullable(logsRepository.findOne(logId, false));
	
		// Si aucune Logs n'est trouvée
		if (!optionalLog.isPresent()) {
			return createErrorResponse(response, functionalError.DATA_NOT_EXIST(
				"Aucun Logs trouvé pour l'identifiant de Logs '" + logId + "'", locale));
		}
	
		// Mapper Logs vers LogsDto
		LogsDto responseDto = mapLogsToDto(optionalLog.get());
	
		// Retourner la réponse avec succès
		response.setItem(responseDto);
		response.setHasError(false);
		log.info("----end getLogsDetail-----");
		return response;
	}
	
	// Méthode pour centraliser la gestion des erreurs
	private Response<LogsDto> createErrorResponse(Response<LogsDto> response, Status status) {
		response.setStatus(status);
		response.setHasError(true);
		return response;
	}

	// Mapper l'entité Logs vers LogsDto
	private LogsDto mapLogsToDto(Logs logs) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	
		LogsDto dto = new LogsDto();
		dto.setId(logs.getId());
		dto.setActionService(logs.getActionService());
		dto.setCreatedAt(dateFormat.format(logs.getCreatedAt()));
		dto.setCreatedBy(logs.getCreatedBy());
		dto.setIpadress(logs.getIpadress());
		dto.setIsConnexion(logs.getIsConnexion());
		dto.setLogin(logs.getLogin());
		dto.setMachine(logs.getMachine());
		dto.setNom(logs.getNom());
		dto.setPrenom(logs.getPrenom());
		dto.setRequest(logs.getRequest());
		dto.setResponse(logs.getResponse());
		dto.setSearchString(logs.getSearchString());
		dto.setStatusConnection(logs.getStatusConnection());
		dto.setUri(logs.getUri());
	
		return dto;
	}


	  // Planification de l'export des logs tous les jours à minuit
    @Scheduled(cron = "0 0 0 * * ?")
    public void scheduleLogExport() {
        exportLogsToFile();
    }

    // Export des logs dans un fichier txt et suppression des données sauvegardées
    public void exportLogsToFile() {
        String dateSuffix = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String filePathWithDate = logsDirectoy + "/logfile_" + dateSuffix + ".txt";

        try {
            // Vérifier si le répertoire existe, sinon le créer
            createLogDirectoryIfNotExists(logsDirectoy);

            List<Logs> logs = logsRepository.findAll();

            // Écriture des logs dans le fichier
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePathWithDate, true))) {
                for (Logs log : logs) {
                    String logLine = formatLog(log);
                    writer.write(logLine);
                    writer.newLine();
                }
                writer.flush();

                //vider la table logs
                logsRepository.deleteAllLogs();

            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            System.err.println("Erreur lors de la création du répertoire de logs : " + e.getMessage());
        }
    }

    // Méthode pour créer le répertoire s'il n'existe pas
    private void createLogDirectoryIfNotExists(String directoryPath) throws IOException {
        Path path = Paths.get(directoryPath);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
            System.out.println("Répertoire de logs créé : " + directoryPath);
        }
    }

    // Formater les logs dans une chaîne lisible
    private String formatLog(Logs log) {
        return String.format("Date: %s, Action: %s, User: %s, IP: %s, Request: %s, Response: %s",
                log.getCreatedAt(),
                log.getActionService(),
                log.getLogin(),
                log.getIpadress(),
               
				log.getRequest(),
                log.getResponse());
    }

}
