                                                        												
/*
 * Java business for entity table alerts 
 * Created on 2025-01-11 ( Time 04:45:54 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2018 Geo. All Rights Reserved.
 */

package tech.synelia.eVoyageBackend.business;

import lombok.extern.java.Log;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import tech.synelia.eVoyageBackend.utils.*;
import tech.synelia.eVoyageBackend.utils.dto.*;
import tech.synelia.eVoyageBackend.utils.enums.*;
import tech.synelia.eVoyageBackend.utils.contract.*;
import tech.synelia.eVoyageBackend.utils.contract.IBasicBusiness;
import tech.synelia.eVoyageBackend.utils.contract.Request;
import tech.synelia.eVoyageBackend.utils.contract.Response;
import tech.synelia.eVoyageBackend.utils.dto.transformer.*;
import tech.synelia.eVoyageBackend.dao.entity.Alerts;
import tech.synelia.eVoyageBackend.dao.entity.Routes;
import tech.synelia.eVoyageBackend.dao.entity.*;
import tech.synelia.eVoyageBackend.dao.repository.*;

/**
BUSINESS for table "alerts"
 * 
 * @author Geo
 *
 */
@Log
@Component
public class AlertsBusiness implements IBasicBusiness<Request<AlertsDto>, Response<AlertsDto>> {

	private Response<AlertsDto> response;
	@Autowired
	private AlertsRepository alertsRepository;
	@Autowired
	private RoutesRepository routesRepository;
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

	public AlertsBusiness() {
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	}
	
	/**
	 * create Alerts by using AlertsDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<AlertsDto> create(Request<AlertsDto> request, Locale locale)  throws ParseException {
		log.info("----begin create Alerts-----");

		Response<AlertsDto> response = new Response<AlertsDto>();
		List<Alerts>        items    = new ArrayList<Alerts>();
			
		for (AlertsDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("routeId", dto.getRouteId());
			fieldsToVerify.put("type", dto.getType());
			fieldsToVerify.put("message", dto.getMessage());
			fieldsToVerify.put("deletedAt", dto.getDeletedAt());
			fieldsToVerify.put("deletedBy", dto.getDeletedBy());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verify if alerts to insert do not exist
			Alerts existingEntity = null;
			if (existingEntity != null) {
				response.setStatus(functionalError.DATA_EXIST("alerts id -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			// Verify if routes exist
			Routes existingRoutes = null;
			if (dto.getRouteId() != null && dto.getRouteId() > 0){
				existingRoutes = routesRepository.findOne(dto.getRouteId(), false);
				if (existingRoutes == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("routes routeId -> " + dto.getRouteId(), locale));
					response.setHasError(true);
					return response;
				}
			}
				Alerts entityToSave = null;
			entityToSave = AlertsTransformer.INSTANCE.toEntity(dto, existingRoutes);
			entityToSave.setCreatedAt(Utilities.getCurrentDate());
			entityToSave.setCreatedBy(request.getUser());
			entityToSave.setIsDeleted(false);
			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<Alerts> itemsSaved = null;
			// inserer les donnees en base de donnees
			itemsSaved = alertsRepository.saveAll((Iterable<Alerts>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("alerts", locale));
				response.setHasError(true);
				return response;
			}
			List<AlertsDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? AlertsTransformer.INSTANCE.toLiteDtos(itemsSaved) : AlertsTransformer.INSTANCE.toDtos(itemsSaved);

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

		log.info("----end create Alerts-----");
		return response;
	}

	/**
	 * update Alerts by using AlertsDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<AlertsDto> update(Request<AlertsDto> request, Locale locale)  throws ParseException {
		log.info("----begin update Alerts-----");

		Response<AlertsDto> response = new Response<AlertsDto>();
		List<Alerts>        items    = new ArrayList<Alerts>();
			
		for (AlertsDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la alerts existe
			Alerts entityToSave = null;
			entityToSave = alertsRepository.findOne(dto.getId(), false);
			if (entityToSave == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("alerts id -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			// Verify if routes exist
			if (dto.getRouteId() != null && dto.getRouteId() > 0){
				Routes existingRoutes = routesRepository.findOne(dto.getRouteId(), false);
				if (existingRoutes == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("routes routeId -> " + dto.getRouteId(), locale));
					response.setHasError(true);
					return response;
				}
				entityToSave.setRoutes(existingRoutes);
			}
			if (Utilities.notBlank(dto.getType())) {
				entityToSave.setType(dto.getType());
			}
			if (Utilities.notBlank(dto.getMessage())) {
				entityToSave.setMessage(dto.getMessage());
			}
			if (dto.getCreatedBy() != null && dto.getCreatedBy() > 0) {
				entityToSave.setCreatedBy(dto.getCreatedBy());
			}
			if (dto.getUpdatedBy() != null && dto.getUpdatedBy() > 0) {
				entityToSave.setUpdatedBy(dto.getUpdatedBy());
			}
			if (Utilities.notBlank(dto.getDeletedAt())) {
				entityToSave.setDeletedAt(dateFormat.parse(dto.getDeletedAt()));
			}
			if (dto.getDeletedBy() != null && dto.getDeletedBy() > 0) {
				entityToSave.setDeletedBy(dto.getDeletedBy());
			}
			entityToSave.setUpdatedAt(Utilities.getCurrentDate());
			entityToSave.setUpdatedBy(request.getUser());
			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<Alerts> itemsSaved = null;
			// maj les donnees en base
			itemsSaved = alertsRepository.saveAll((Iterable<Alerts>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("alerts", locale));
				response.setHasError(true);
				return response;
			}
			List<AlertsDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? AlertsTransformer.INSTANCE.toLiteDtos(itemsSaved) : AlertsTransformer.INSTANCE.toDtos(itemsSaved);

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

		log.info("----end update Alerts-----");
		return response;
	}

	/**
	 * delete Alerts by using AlertsDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<AlertsDto> delete(Request<AlertsDto> request, Locale locale)  {
		log.info("----begin delete Alerts-----");

		Response<AlertsDto> response = new Response<AlertsDto>();
		List<Alerts>        items    = new ArrayList<Alerts>();
			
		for (AlertsDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la alerts existe
			Alerts existingEntity = null;
			existingEntity = alertsRepository.findOne(dto.getId(), false);
			if (existingEntity == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("alerts -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			// -----------------------------------------------------------------------
			// ----------- CHECK IF DATA IS USED
			// -----------------------------------------------------------------------



			existingEntity.setIsDeleted(true);
			existingEntity.setDeletedAt(Utilities.getCurrentDate());
			existingEntity.setDeletedBy(request.getUser());
			items.add(existingEntity);
		}

		if (!items.isEmpty()) {
			// supprimer les donnees en base
			alertsRepository.saveAll((Iterable<Alerts>) items);

			response.setHasError(false);
		}

		log.info("----end delete Alerts-----");
		return response;
	}

	/**
	 * get Alerts by using AlertsDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<AlertsDto> getByCriteria(Request<AlertsDto> request, Locale locale)  throws Exception {
		log.info("----begin get Alerts-----");

		Response<AlertsDto> response = new Response<AlertsDto>();
		List<Alerts> items 			 = alertsRepository.getByCriteria(request, em, locale);

		if (items != null && !items.isEmpty()) {
			List<AlertsDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? AlertsTransformer.INSTANCE.toLiteDtos(items) : AlertsTransformer.INSTANCE.toDtos(items);

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
			response.setCount(alertsRepository.count(request, em, locale));
			response.setHasError(false);
		} else {
			response.setStatus(functionalError.DATA_EMPTY("alerts", locale));
			response.setHasError(false);
			return response;
		}

		log.info("----end get Alerts-----");
		return response;
	}

	/**
	 * get full AlertsDto by using Alerts as object.
	 * 
	 * @param dto
	 * @param size
	 * @param isSimpleLoading
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	private AlertsDto getFullInfos(AlertsDto dto, Integer size, Boolean isSimpleLoading, Locale locale) throws Exception {
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
