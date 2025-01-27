                                                            														
/*
 * Java business for entity table api_logs 
 * Created on 2024-11-25 ( Time 15:12:19 )
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
BUSINESS for table "api_logs"
 * 
 * @author Geo
 *
 */
@Log
@Component
public class ApiLogsBusiness implements IBasicBusiness<Request<ApiLogsDto>, Response<ApiLogsDto>> {

	private Response<ApiLogsDto> response;
	@Autowired
	private ApiLogsRepository apiLogsRepository;
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

	public ApiLogsBusiness() {
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	}
	
	/**
	 * create ApiLogs by using ApiLogsDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<ApiLogsDto> create(Request<ApiLogsDto> request, Locale locale)  throws ParseException {
		log.info("----begin create ApiLogs-----");

		Response<ApiLogsDto> response = new Response<ApiLogsDto>();
		List<ApiLogs>        items    = new ArrayList<ApiLogs>();
			
		for (ApiLogsDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, Object> fieldsToVerify = new HashMap<String, Object>();
			fieldsToVerify.put("requestTime", dto.getRequestTime());
			fieldsToVerify.put("requestUrl", dto.getRequestUrl());
			fieldsToVerify.put("requestMethod", dto.getRequestMethod());
			fieldsToVerify.put("requestHeaders", dto.getRequestHeaders());
			fieldsToVerify.put("requestBody", dto.getRequestBody());
			fieldsToVerify.put("responseTime", dto.getResponseTime());
			fieldsToVerify.put("responseStatus", dto.getResponseStatus());
			fieldsToVerify.put("responseBody", dto.getResponseBody());
			fieldsToVerify.put("createdDate", dto.getCreatedDate());
			fieldsToVerify.put("lastModifiedBy", dto.getLastModifiedBy());
			fieldsToVerify.put("lastModifiedDate", dto.getLastModifiedDate());
			fieldsToVerify.put("status", dto.getStatus());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verify if apiLogs to insert do not exist
			ApiLogs existingEntity = null;
			if (existingEntity != null) {
				response.setStatus(functionalError.DATA_EXIST("apiLogs id -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

				ApiLogs entityToSave = null;
			entityToSave = ApiLogsTransformer.INSTANCE.toEntity(dto);
			entityToSave.setCreatedBy(request.getUser().toString());
			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<ApiLogs> itemsSaved = null;
			// inserer les donnees en base de donnees
			itemsSaved = apiLogsRepository.saveAll((Iterable<ApiLogs>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("apiLogs", locale));
				response.setHasError(true);
				return response;
			}
			List<ApiLogsDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? ApiLogsTransformer.INSTANCE.toLiteDtos(itemsSaved) : ApiLogsTransformer.INSTANCE.toDtos(itemsSaved);

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

		log.info("----end create ApiLogs-----");
		return response;
	}

	/**
	 * update ApiLogs by using ApiLogsDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<ApiLogsDto> update(Request<ApiLogsDto> request, Locale locale)  throws ParseException {
		log.info("----begin update ApiLogs-----");

		Response<ApiLogsDto> response = new Response<ApiLogsDto>();
		List<ApiLogs>        items    = new ArrayList<ApiLogs>();
			
		for (ApiLogsDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, Object> fieldsToVerify = new HashMap<String, Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la apiLogs existe
			ApiLogs entityToSave = null;
			entityToSave = apiLogsRepository.findOne(dto.getId());
			if (entityToSave == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("apiLogs id -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			if (Utilities.notBlank(dto.getRequestTime())) {
				entityToSave.setRequestTime(dateFormat.parse(dto.getRequestTime()));
			}
			if (Utilities.notBlank(dto.getRequestUrl())) {
				entityToSave.setRequestUrl(dto.getRequestUrl());
			}
			if (Utilities.notBlank(dto.getRequestMethod())) {
				entityToSave.setRequestMethod(dto.getRequestMethod());
			}
			if (Utilities.notBlank(dto.getRequestHeaders())) {
				entityToSave.setRequestHeaders(dto.getRequestHeaders());
			}
			if (Utilities.notBlank(dto.getRequestBody())) {
				entityToSave.setRequestBody(dto.getRequestBody());
			}
			if (Utilities.notBlank(dto.getResponseTime())) {
				entityToSave.setResponseTime(dateFormat.parse(dto.getResponseTime()));
			}
			if (dto.getResponseStatus() != null && dto.getResponseStatus() > 0) {
				entityToSave.setResponseStatus(dto.getResponseStatus());
			}
			if (Utilities.notBlank(dto.getResponseBody())) {
				entityToSave.setResponseBody(dto.getResponseBody());
			}
			if (Utilities.notBlank(dto.getCreatedBy())) {
				entityToSave.setCreatedBy(dto.getCreatedBy());
			}
			if (Utilities.notBlank(dto.getCreatedDate())) {
				entityToSave.setCreatedDate(dateFormat.parse(dto.getCreatedDate()));
			}
			if (Utilities.notBlank(dto.getLastModifiedBy())) {
				entityToSave.setLastModifiedBy(dto.getLastModifiedBy());
			}
			if (Utilities.notBlank(dto.getLastModifiedDate())) {
				entityToSave.setLastModifiedDate(dateFormat.parse(dto.getLastModifiedDate()));
			}
			if (Utilities.notBlank(dto.getStatus())) {
				entityToSave.setStatus(dto.getStatus());
			}
			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<ApiLogs> itemsSaved = null;
			// maj les donnees en base
			itemsSaved = apiLogsRepository.saveAll((Iterable<ApiLogs>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("apiLogs", locale));
				response.setHasError(true);
				return response;
			}
			List<ApiLogsDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? ApiLogsTransformer.INSTANCE.toLiteDtos(itemsSaved) : ApiLogsTransformer.INSTANCE.toDtos(itemsSaved);

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

		log.info("----end update ApiLogs-----");
		return response;
	}

	/**
	 * delete ApiLogs by using ApiLogsDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<ApiLogsDto> delete(Request<ApiLogsDto> request, Locale locale)  {
		log.info("----begin delete ApiLogs-----");

		Response<ApiLogsDto> response = new Response<ApiLogsDto>();
		List<ApiLogs>        items    = new ArrayList<ApiLogs>();
			
		for (ApiLogsDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, Object> fieldsToVerify = new HashMap<String, Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la apiLogs existe
			ApiLogs existingEntity = null;
			existingEntity = apiLogsRepository.findOne(dto.getId());
			if (existingEntity == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("apiLogs -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			// -----------------------------------------------------------------------
			// ----------- CHECK IF DATA IS USED
			// -----------------------------------------------------------------------



			items.add(existingEntity);
		}

		if (!items.isEmpty()) {
			// supprimer les donnees en base
			apiLogsRepository.deleteAll((Iterable<ApiLogs>) items);

			response.setHasError(false);
		}

		log.info("----end delete ApiLogs-----");
		return response;
	}

	/**
	 * get ApiLogs by using ApiLogsDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<ApiLogsDto> getByCriteria(Request<ApiLogsDto> request, Locale locale)  throws Exception {
		log.info("----begin get ApiLogs-----");

		Response<ApiLogsDto> response = new Response<ApiLogsDto>();
		List<ApiLogs> items 			 = apiLogsRepository.getByCriteria(request, em, locale);

		if (items != null && !items.isEmpty()) {
			List<ApiLogsDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? ApiLogsTransformer.INSTANCE.toLiteDtos(items) : ApiLogsTransformer.INSTANCE.toDtos(items);

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
			response.setCount(apiLogsRepository.count(request, em, locale));
			response.setHasError(false);
		} else {
			response.setStatus(functionalError.DATA_EMPTY("apiLogs", locale));
			response.setHasError(false);
			return response;
		}

		log.info("----end get ApiLogs-----");
		return response;
	}

	/**
	 * get full ApiLogsDto by using ApiLogs as object.
	 * 
	 * @param dto
	 * @param size
	 * @param isSimpleLoading
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	private ApiLogsDto getFullInfos(ApiLogsDto dto, Integer size, Boolean isSimpleLoading, Locale locale) throws Exception {
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
