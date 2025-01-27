                                            										
/*
 * Java business for entity table param_notification 
 * Created on 2024-09-02 ( Time 12:26:07 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2018 Geo. All Rights Reserved.
 */

package tech.dev.eVoyageBackend.business;

import lombok.extern.java.Log;
import tech.dev.eVoyageBackend.dao.entity.*;
import tech.dev.eVoyageBackend.dao.repository.*;
import tech.dev.eVoyageBackend.utils.*;
import tech.dev.eVoyageBackend.utils.contract.*;
import tech.dev.eVoyageBackend.utils.dto.*;
import tech.dev.eVoyageBackend.utils.dto.transformer.*;
import tech.dev.eVoyageBackend.utils.enums.*;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
BUSINESS for table "param_notification"
 * 
 * @author Geo
 *
 */
@Log
@Component
public class ParamNotificationBusiness implements IBasicBusiness<Request<ParamNotificationDto>, Response<ParamNotificationDto>> {

	private Response<ParamNotificationDto> response;
	@Autowired
	private ParamNotificationRepository paramNotificationRepository;
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

	public ParamNotificationBusiness() {
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	}
	
	/**
	 * create ParamNotification by using ParamNotificationDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<ParamNotificationDto> create(Request<ParamNotificationDto> request, Locale locale)  throws ParseException {
		log.info("----begin create ParamNotification-----");

		Response<ParamNotificationDto> response = new Response<ParamNotificationDto>();
		List<ParamNotification>        items    = new ArrayList<ParamNotification>();
			
		for (ParamNotificationDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, Object> fieldsToVerify = new HashMap<String, Object>();
			fieldsToVerify.put("lang", dto.getLang());
			fieldsToVerify.put("message", dto.getMessage());
			fieldsToVerify.put("type", dto.getType());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verify if paramNotification to insert do not exist
			ParamNotification existingEntity = null;
			if (existingEntity != null) {
				response.setStatus(functionalError.DATA_EXIST("paramNotification id -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

				ParamNotification entityToSave = null;
			entityToSave = ParamNotificationTransformer.INSTANCE.toEntity(dto);
			entityToSave.setCreatedAt(Utilities.getCurrentDate());
			entityToSave.setCreatedBy(request.getUser());
			entityToSave.setIsDeleted(false);
			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<ParamNotification> itemsSaved = null;
			// inserer les donnees en base de donnees
			itemsSaved = paramNotificationRepository.saveAll((Iterable<ParamNotification>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("paramNotification", locale));
				response.setHasError(true);
				return response;
			}
			List<ParamNotificationDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? ParamNotificationTransformer.INSTANCE.toLiteDtos(itemsSaved) : ParamNotificationTransformer.INSTANCE.toDtos(itemsSaved);

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

		log.info("----end create ParamNotification-----");
		return response;
	}

	/**
	 * update ParamNotification by using ParamNotificationDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<ParamNotificationDto> update(Request<ParamNotificationDto> request, Locale locale)  throws ParseException {
		log.info("----begin update ParamNotification-----");

		Response<ParamNotificationDto> response = new Response<ParamNotificationDto>();
		List<ParamNotification>        items    = new ArrayList<ParamNotification>();
			
		for (ParamNotificationDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, Object> fieldsToVerify = new HashMap<String, Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la paramNotification existe
			ParamNotification entityToSave = null;
			entityToSave = paramNotificationRepository.findOne(dto.getId(), false);
			if (entityToSave == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("paramNotification id -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			if (dto.getCreatedBy() != null && dto.getCreatedBy() > 0) {
				entityToSave.setCreatedBy(dto.getCreatedBy());
			}
			if (Utilities.notBlank(dto.getLang())) {
				entityToSave.setLang(dto.getLang());
			}
			if (Utilities.notBlank(dto.getMessage())) {
				entityToSave.setMessage(dto.getMessage());
			}
			if (Utilities.notBlank(dto.getType())) {
				entityToSave.setType(dto.getType());
			}
			if (dto.getUpdatedBy() != null && dto.getUpdatedBy() > 0) {
				entityToSave.setUpdatedBy(dto.getUpdatedBy());
			}
			entityToSave.setUpdatedAt(Utilities.getCurrentDate());
			entityToSave.setUpdatedBy(request.getUser());
			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<ParamNotification> itemsSaved = null;
			// maj les donnees en base
			itemsSaved = paramNotificationRepository.saveAll((Iterable<ParamNotification>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("paramNotification", locale));
				response.setHasError(true);
				return response;
			}
			List<ParamNotificationDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? ParamNotificationTransformer.INSTANCE.toLiteDtos(itemsSaved) : ParamNotificationTransformer.INSTANCE.toDtos(itemsSaved);

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

		log.info("----end update ParamNotification-----");
		return response;
	}

	/**
	 * delete ParamNotification by using ParamNotificationDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<ParamNotificationDto> delete(Request<ParamNotificationDto> request, Locale locale)  {
		log.info("----begin delete ParamNotification-----");

		Response<ParamNotificationDto> response = new Response<ParamNotificationDto>();
		List<ParamNotification>        items    = new ArrayList<ParamNotification>();
			
		for (ParamNotificationDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, Object> fieldsToVerify = new HashMap<String, Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la paramNotification existe
			ParamNotification existingEntity = null;
			existingEntity = paramNotificationRepository.findOne(dto.getId(), false);
			if (existingEntity == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("paramNotification -> " + dto.getId(), locale));
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
			paramNotificationRepository.saveAll((Iterable<ParamNotification>) items);

			response.setHasError(false);
		}

		log.info("----end delete ParamNotification-----");
		return response;
	}

	/**
	 * get ParamNotification by using ParamNotificationDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<ParamNotificationDto> getByCriteria(Request<ParamNotificationDto> request, Locale locale)  throws Exception {
		log.info("----begin get ParamNotification-----");

		Response<ParamNotificationDto> response = new Response<ParamNotificationDto>();
		List<ParamNotification> items 			 = paramNotificationRepository.getByCriteria(request, em, locale);

		if (items != null && !items.isEmpty()) {
			List<ParamNotificationDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? ParamNotificationTransformer.INSTANCE.toLiteDtos(items) : ParamNotificationTransformer.INSTANCE.toDtos(items);

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
			response.setCount(paramNotificationRepository.count(request, em, locale));
			response.setHasError(false);
		} else {
			response.setStatus(functionalError.DATA_EMPTY("paramNotification", locale));
			response.setHasError(false);
			return response;
		}

		log.info("----end get ParamNotification-----");
		return response;
	}

	/**
	 * get full ParamNotificationDto by using ParamNotification as object.
	 * 
	 * @param dto
	 * @param size
	 * @param isSimpleLoading
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	private ParamNotificationDto getFullInfos(ParamNotificationDto dto, Integer size, Boolean isSimpleLoading, Locale locale) throws Exception {
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
