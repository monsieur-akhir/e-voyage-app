                                                                														
/*
 * Java business for entity table travel_schedules 
 * Created on 2025-01-12 ( Time 17:39:59 )
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
import tech.dev.eVoyageBackend.dao.entity.TravelSchedules;
import tech.dev.eVoyageBackend.dao.entity.Routes;
import tech.dev.eVoyageBackend.dao.entity.Companies;
import tech.dev.eVoyageBackend.dao.entity.*;
import tech.dev.eVoyageBackend.dao.repository.*;

/**
BUSINESS for table "travel_schedules"
 * 
 * @author Geo
 *
 */
@Log
@Component
public class TravelSchedulesBusiness implements IBasicBusiness<Request<TravelSchedulesDto>, Response<TravelSchedulesDto>> {

	private Response<TravelSchedulesDto> response;
	@Autowired
	private TravelSchedulesRepository travelSchedulesRepository;
	@Autowired
	private RoutesRepository routesRepository;
	@Autowired
	private CompaniesRepository companiesRepository;
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

	public TravelSchedulesBusiness() {
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	}
	
	/**
	 * create TravelSchedules by using TravelSchedulesDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<TravelSchedulesDto> create(Request<TravelSchedulesDto> request, Locale locale)  throws ParseException {
		log.info("----begin create TravelSchedules-----");

		Response<TravelSchedulesDto> response = new Response<TravelSchedulesDto>();
		List<TravelSchedules>        items    = new ArrayList<TravelSchedules>();
			
		for (TravelSchedulesDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, Object> fieldsToVerify = new HashMap<String, Object>();
			fieldsToVerify.put("companyId", dto.getCompanyId());
			fieldsToVerify.put("routeId", dto.getRouteId());
			fieldsToVerify.put("departureStation", dto.getDepartureStation());
			fieldsToVerify.put("arrivalStation", dto.getArrivalStation());
			fieldsToVerify.put("departureTime", dto.getDepartureTime());
			fieldsToVerify.put("arrivalTime", dto.getArrivalTime());
			fieldsToVerify.put("travelDate", dto.getTravelDate());
			fieldsToVerify.put("price", dto.getPrice());
			fieldsToVerify.put("status", dto.getStatus());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verify if travelSchedules to insert do not exist
			TravelSchedules existingEntity = null;
			if (existingEntity != null) {
				response.setStatus(functionalError.DATA_EXIST("travelSchedules id -> " + dto.getId(), locale));
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
				TravelSchedules entityToSave = null;
			entityToSave = TravelSchedulesTransformer.INSTANCE.toEntity(dto, existingRoutes, existingCompanies);
			entityToSave.setCreatedAt(Utilities.getCurrentDate());
			entityToSave.setCreatedBy(request.getUser());
			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<TravelSchedules> itemsSaved = null;
			// inserer les donnees en base de donnees
			itemsSaved = travelSchedulesRepository.saveAll((Iterable<TravelSchedules>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("travelSchedules", locale));
				response.setHasError(true);
				return response;
			}
			List<TravelSchedulesDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? TravelSchedulesTransformer.INSTANCE.toLiteDtos(itemsSaved) : TravelSchedulesTransformer.INSTANCE.toDtos(itemsSaved);

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

		log.info("----end create TravelSchedules-----");
		return response;
	}

	/**
	 * update TravelSchedules by using TravelSchedulesDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<TravelSchedulesDto> update(Request<TravelSchedulesDto> request, Locale locale)  throws ParseException {
		log.info("----begin update TravelSchedules-----");

		Response<TravelSchedulesDto> response = new Response<TravelSchedulesDto>();
		List<TravelSchedules>        items    = new ArrayList<TravelSchedules>();
			
		for (TravelSchedulesDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, Object> fieldsToVerify = new HashMap<String, Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la travelSchedules existe
			TravelSchedules entityToSave = null;
			entityToSave = travelSchedulesRepository.findOne(dto.getId());
			if (entityToSave == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("travelSchedules id -> " + dto.getId(), locale));
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
			if (Utilities.notBlank(dto.getDepartureStation())) {
				entityToSave.setDepartureStation(dto.getDepartureStation());
			}
			if (Utilities.notBlank(dto.getArrivalStation())) {
				entityToSave.setArrivalStation(dto.getArrivalStation());
			}
			if (dto.getDepartureTime() != null) {
				entityToSave.setDepartureTime(dto.getDepartureTime());
			}
			if (dto.getArrivalTime() != null) {
				entityToSave.setArrivalTime(dto.getArrivalTime());
			}
			if (dto.getTravelDate() != null) {
				entityToSave.setTravelDate(dto.getTravelDate());
			}
			if (dto.getPrice() != null && dto.getPrice() > 0) {
				entityToSave.setPrice(dto.getPrice());
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
			List<TravelSchedules> itemsSaved = null;
			// maj les donnees en base
			itemsSaved = travelSchedulesRepository.saveAll((Iterable<TravelSchedules>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("travelSchedules", locale));
				response.setHasError(true);
				return response;
			}
			List<TravelSchedulesDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? TravelSchedulesTransformer.INSTANCE.toLiteDtos(itemsSaved) : TravelSchedulesTransformer.INSTANCE.toDtos(itemsSaved);

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

		log.info("----end update TravelSchedules-----");
		return response;
	}

	/**
	 * delete TravelSchedules by using TravelSchedulesDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<TravelSchedulesDto> delete(Request<TravelSchedulesDto> request, Locale locale)  {
		log.info("----begin delete TravelSchedules-----");

		Response<TravelSchedulesDto> response = new Response<TravelSchedulesDto>();
		List<TravelSchedules>        items    = new ArrayList<TravelSchedules>();
			
		for (TravelSchedulesDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, Object> fieldsToVerify = new HashMap<String, Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la travelSchedules existe
			TravelSchedules existingEntity = null;
			existingEntity = travelSchedulesRepository.findOne(dto.getId());
			if (existingEntity == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("travelSchedules -> " + dto.getId(), locale));
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
			travelSchedulesRepository.deleteAll((Iterable<TravelSchedules>) items);

			response.setHasError(false);
		}

		log.info("----end delete TravelSchedules-----");
		return response;
	}

	/**
	 * get TravelSchedules by using TravelSchedulesDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<TravelSchedulesDto> getByCriteria(Request<TravelSchedulesDto> request, Locale locale)  throws Exception {
		log.info("----begin get TravelSchedules-----");

		Response<TravelSchedulesDto> response = new Response<TravelSchedulesDto>();
		List<TravelSchedules> items 			 = travelSchedulesRepository.getByCriteria(request, em, locale);

		if (items != null && !items.isEmpty()) {
			List<TravelSchedulesDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? TravelSchedulesTransformer.INSTANCE.toLiteDtos(items) : TravelSchedulesTransformer.INSTANCE.toDtos(items);

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
			response.setCount(travelSchedulesRepository.count(request, em, locale));
			response.setHasError(false);
		} else {
			response.setStatus(functionalError.DATA_EMPTY("travelSchedules", locale));
			response.setHasError(false);
			return response;
		}

		log.info("----end get TravelSchedules-----");
		return response;
	}

	/**
	 * get full TravelSchedulesDto by using TravelSchedules as object.
	 * 
	 * @param dto
	 * @param size
	 * @param isSimpleLoading
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	private TravelSchedulesDto getFullInfos(TravelSchedulesDto dto, Integer size, Boolean isSimpleLoading, Locale locale) throws Exception {
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
