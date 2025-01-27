                                                										
/*
 * Java business for entity table trip_tracking 
 * Created on 2025-01-12 ( Time 17:40:00 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2018 Geo. All Rights Reserved.
 */

package tech.dev.eVoyageBackend.business;

import lombok.extern.java.Log;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
import tech.dev.eVoyageBackend.dao.entity.TripTracking;
import tech.dev.eVoyageBackend.dao.entity.Companies;
import tech.dev.eVoyageBackend.dao.entity.Routes;
import tech.dev.eVoyageBackend.dao.entity.*;
import tech.dev.eVoyageBackend.dao.repository.*;

/**
BUSINESS for table "trip_tracking"
 * 
 * @author Geo
 *
 */
@Log
@Component
public class TripTrackingBusiness implements IBasicBusiness<Request<TripTrackingDto>, Response<TripTrackingDto>> {

	private Response<TripTrackingDto> response;
	@Autowired
	private TripTrackingRepository tripTrackingRepository;
	@Autowired
	private CompaniesRepository companiesRepository;
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

	public TripTrackingBusiness() {
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	}
	
	/**
	 * create TripTracking by using TripTrackingDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<TripTrackingDto> create(Request<TripTrackingDto> request, Locale locale)  throws ParseException {
		log.info("----begin create TripTracking-----");

		Response<TripTrackingDto> response = new Response<TripTrackingDto>();
		List<TripTracking>        items    = new ArrayList<TripTracking>();
			
		for (TripTrackingDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("companyId", dto.getCompanyId());
			fieldsToVerify.put("routeId", dto.getRouteId());
			fieldsToVerify.put("location", dto.getLocation());
			fieldsToVerify.put("timestamp", dto.getTimestamp());
			fieldsToVerify.put("status", dto.getStatus());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verify if tripTracking to insert do not exist
			TripTracking existingEntity = null;
			if (existingEntity != null) {
				response.setStatus(functionalError.DATA_EXIST("tripTracking id -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			// Verify if companies exist
			Companies existingCompanies = null;
			if (dto.getCompanyId() != null && dto.getCompanyId() > 0){
				existingCompanies = companiesRepository.findOne(dto.getCompanyId(), false);
				if (existingCompanies == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("companies companyId -> " + dto.getCompanyId(), locale));
					response.setHasError(true);
					return response;
				}
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
				TripTracking entityToSave = null;
			entityToSave = TripTrackingTransformer.INSTANCE.toEntity(dto, existingCompanies, existingRoutes);
			entityToSave.setCreatedAt(Utilities.getCurrentDate());
			entityToSave.setCreatedBy(request.getUser());
			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<TripTracking> itemsSaved = null;
			// inserer les donnees en base de donnees
			itemsSaved = tripTrackingRepository.saveAll((Iterable<TripTracking>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("tripTracking", locale));
				response.setHasError(true);
				return response;
			}
			List<TripTrackingDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? TripTrackingTransformer.INSTANCE.toLiteDtos(itemsSaved) : TripTrackingTransformer.INSTANCE.toDtos(itemsSaved);

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

		log.info("----end create TripTracking-----");
		return response;
	}

	/**
	 * update TripTracking by using TripTrackingDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<TripTrackingDto> update(Request<TripTrackingDto> request, Locale locale)  throws ParseException {
		log.info("----begin update TripTracking-----");

		Response<TripTrackingDto> response = new Response<TripTrackingDto>();
		List<TripTracking>        items    = new ArrayList<TripTracking>();
			
		for (TripTrackingDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la tripTracking existe
			TripTracking entityToSave = null;
			entityToSave = tripTrackingRepository.findOne(dto.getId());
			if (entityToSave == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("tripTracking id -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			// Verify if companies exist
			if (dto.getCompanyId() != null && dto.getCompanyId() > 0){
				Companies existingCompanies = companiesRepository.findOne(dto.getCompanyId(), false);
				if (existingCompanies == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("companies companyId -> " + dto.getCompanyId(), locale));
					response.setHasError(true);
					return response;
				}
				entityToSave.setCompanies(existingCompanies);
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
			if (Utilities.notBlank(dto.getLocation())) {
				entityToSave.setLocation(dto.getLocation());
			}
			if (Utilities.notBlank(dto.getTimestamp())) {
				entityToSave.setTimestamp(dateFormat.parse(dto.getTimestamp()));
			}
			if (Utilities.notBlank(dto.getStatus())) {
				entityToSave.setStatus(dto.getStatus());
			}
			if (dto.getCreatedBy() != null && dto.getCreatedBy() > 0) {
				entityToSave.setCreatedBy(dto.getCreatedBy());
			}
			if (dto.getUpdatedBy() != null && dto.getUpdatedBy() > 0) {
				entityToSave.setUpdatedBy(dto.getUpdatedBy());
			}
			entityToSave.setUpdatedAt(Utilities.getCurrentDate());
			entityToSave.setUpdatedBy(request.getUser());
			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<TripTracking> itemsSaved = null;
			// maj les donnees en base
			itemsSaved = tripTrackingRepository.saveAll((Iterable<TripTracking>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("tripTracking", locale));
				response.setHasError(true);
				return response;
			}
			List<TripTrackingDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? TripTrackingTransformer.INSTANCE.toLiteDtos(itemsSaved) : TripTrackingTransformer.INSTANCE.toDtos(itemsSaved);

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

		log.info("----end update TripTracking-----");
		return response;
	}

	/**
	 * delete TripTracking by using TripTrackingDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<TripTrackingDto> delete(Request<TripTrackingDto> request, Locale locale)  {
		log.info("----begin delete TripTracking-----");

		Response<TripTrackingDto> response = new Response<TripTrackingDto>();
		List<TripTracking>        items    = new ArrayList<TripTracking>();
			
		for (TripTrackingDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la tripTracking existe
			TripTracking existingEntity = null;
			existingEntity = tripTrackingRepository.findOne(dto.getId());
			if (existingEntity == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("tripTracking -> " + dto.getId(), locale));
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
			tripTrackingRepository.deleteAll((Iterable<TripTracking>) items);

			response.setHasError(false);
		}

		log.info("----end delete TripTracking-----");
		return response;
	}

	/**
	 * get TripTracking by using TripTrackingDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<TripTrackingDto> getByCriteria(Request<TripTrackingDto> request, Locale locale)  throws Exception {
		log.info("----begin get TripTracking-----");

		Response<TripTrackingDto> response = new Response<TripTrackingDto>();
		List<TripTracking> items 			 = tripTrackingRepository.getByCriteria(request, em, locale);

		if (items != null && !items.isEmpty()) {
			List<TripTrackingDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? TripTrackingTransformer.INSTANCE.toLiteDtos(items) : TripTrackingTransformer.INSTANCE.toDtos(items);

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
			response.setCount(tripTrackingRepository.count(request, em, locale));
			response.setHasError(false);
		} else {
			response.setStatus(functionalError.DATA_EMPTY("tripTracking", locale));
			response.setHasError(false);
			return response;
		}

		log.info("----end get TripTracking-----");
		return response;
	}

	/**
	 * get full TripTrackingDto by using TripTracking as object.
	 * 
	 * @param dto
	 * @param size
	 * @param isSimpleLoading
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	private TripTrackingDto getFullInfos(TripTrackingDto dto, Integer size, Boolean isSimpleLoading, Locale locale) throws Exception {
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
