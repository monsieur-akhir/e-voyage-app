                                                                														
/*
 * Java business for entity table routes 
 * Created on 2025-01-11 ( Time 04:46:02 )
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
import tech.synelia.eVoyageBackend.dao.entity.Routes;
import tech.synelia.eVoyageBackend.dao.entity.Companies;
import tech.synelia.eVoyageBackend.dao.entity.*;
import tech.synelia.eVoyageBackend.dao.repository.*;

/**
BUSINESS for table "routes"
 * 
 * @author Geo
 *
 */
@Log
@Component
public class RoutesBusiness implements IBasicBusiness<Request<RoutesDto>, Response<RoutesDto>> {

	private Response<RoutesDto> response;
	@Autowired
	private RoutesRepository routesRepository;
	@Autowired
	private BookingsRepository bookingsRepository;
	@Autowired
	private CompaniesRepository companiesRepository;
	@Autowired
	private AlertsRepository alertsRepository;
	@Autowired
	private TripTrackingRepository tripTrackingRepository;
	@Autowired
	private BusesRepository busesRepository;
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

	public RoutesBusiness() {
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	}
	
	/**
	 * create Routes by using RoutesDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<RoutesDto> create(Request<RoutesDto> request, Locale locale)  throws ParseException {
		log.info("----begin create Routes-----");

		Response<RoutesDto> response = new Response<RoutesDto>();
		List<Routes>        items    = new ArrayList<Routes>();
			
		for (RoutesDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("companyId", dto.getCompanyId());
			fieldsToVerify.put("origin", dto.getOrigin());
			fieldsToVerify.put("destination", dto.getDestination());
			fieldsToVerify.put("duration", dto.getDuration());
			fieldsToVerify.put("price", dto.getPrice());
			fieldsToVerify.put("deletedAt", dto.getDeletedAt());
			fieldsToVerify.put("deletedBy", dto.getDeletedBy());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verify if routes to insert do not exist
			Routes existingEntity = null;
			if (existingEntity != null) {
				response.setStatus(functionalError.DATA_EXIST("routes id -> " + dto.getId(), locale));
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
				Routes entityToSave = null;
			entityToSave = RoutesTransformer.INSTANCE.toEntity(dto, existingCompanies);
			entityToSave.setCreatedAt(Utilities.getCurrentDate());
			entityToSave.setCreatedBy(request.getUser());
			entityToSave.setIsDeleted(false);
			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<Routes> itemsSaved = null;
			// inserer les donnees en base de donnees
			itemsSaved = routesRepository.saveAll((Iterable<Routes>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("routes", locale));
				response.setHasError(true);
				return response;
			}
			List<RoutesDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? RoutesTransformer.INSTANCE.toLiteDtos(itemsSaved) : RoutesTransformer.INSTANCE.toDtos(itemsSaved);

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

		log.info("----end create Routes-----");
		return response;
	}

	/**
	 * update Routes by using RoutesDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<RoutesDto> update(Request<RoutesDto> request, Locale locale)  throws ParseException {
		log.info("----begin update Routes-----");

		Response<RoutesDto> response = new Response<RoutesDto>();
		List<Routes>        items    = new ArrayList<Routes>();
			
		for (RoutesDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la routes existe
			Routes entityToSave = null;
			entityToSave = routesRepository.findOne(dto.getId(), false);
			if (entityToSave == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("routes id -> " + dto.getId(), locale));
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
			if (Utilities.notBlank(dto.getOrigin())) {
				entityToSave.setOrigin(dto.getOrigin());
			}
			if (Utilities.notBlank(dto.getDestination())) {
				entityToSave.setDestination(dto.getDestination());
			}
			if (dto.getDuration() != null && dto.getDuration() > 0) {
				entityToSave.setDuration(dto.getDuration());
			}
			if (dto.getPrice() != null && dto.getPrice() > 0) {
				entityToSave.setPrice(dto.getPrice());
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
			List<Routes> itemsSaved = null;
			// maj les donnees en base
			itemsSaved = routesRepository.saveAll((Iterable<Routes>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("routes", locale));
				response.setHasError(true);
				return response;
			}
			List<RoutesDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? RoutesTransformer.INSTANCE.toLiteDtos(itemsSaved) : RoutesTransformer.INSTANCE.toDtos(itemsSaved);

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

		log.info("----end update Routes-----");
		return response;
	}

	/**
	 * delete Routes by using RoutesDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<RoutesDto> delete(Request<RoutesDto> request, Locale locale)  {
		log.info("----begin delete Routes-----");

		Response<RoutesDto> response = new Response<RoutesDto>();
		List<Routes>        items    = new ArrayList<Routes>();
			
		for (RoutesDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la routes existe
			Routes existingEntity = null;
			existingEntity = routesRepository.findOne(dto.getId(), false);
			if (existingEntity == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("routes -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			// -----------------------------------------------------------------------
			// ----------- CHECK IF DATA IS USED
			// -----------------------------------------------------------------------

			// bookings
			List<Bookings> listOfBookings = bookingsRepository.findByRouteId(existingEntity.getId(), false);
			if (listOfBookings != null && !listOfBookings.isEmpty()){
				response.setStatus(functionalError.DATA_NOT_DELETABLE("(" + listOfBookings.size() + ")", locale));
				response.setHasError(true);
				return response;
			}
			// alerts
			List<Alerts> listOfAlerts = alertsRepository.findByRouteId(existingEntity.getId(), false);
			if (listOfAlerts != null && !listOfAlerts.isEmpty()){
				response.setStatus(functionalError.DATA_NOT_DELETABLE("(" + listOfAlerts.size() + ")", locale));
				response.setHasError(true);
				return response;
			}
			// tripTracking
			List<TripTracking> listOfTripTracking = tripTrackingRepository.findByRouteId(existingEntity.getId(), false);
			if (listOfTripTracking != null && !listOfTripTracking.isEmpty()){
				response.setStatus(functionalError.DATA_NOT_DELETABLE("(" + listOfTripTracking.size() + ")", locale));
				response.setHasError(true);
				return response;
			}
			// buses
			List<Buses> listOfBuses = busesRepository.findByRouteId(existingEntity.getId(), false);
			if (listOfBuses != null && !listOfBuses.isEmpty()){
				response.setStatus(functionalError.DATA_NOT_DELETABLE("(" + listOfBuses.size() + ")", locale));
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
			routesRepository.saveAll((Iterable<Routes>) items);

			response.setHasError(false);
		}

		log.info("----end delete Routes-----");
		return response;
	}

	/**
	 * get Routes by using RoutesDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<RoutesDto> getByCriteria(Request<RoutesDto> request, Locale locale)  throws Exception {
		log.info("----begin get Routes-----");

		Response<RoutesDto> response = new Response<RoutesDto>();
		List<Routes> items 			 = routesRepository.getByCriteria(request, em, locale);

		if (items != null && !items.isEmpty()) {
			List<RoutesDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? RoutesTransformer.INSTANCE.toLiteDtos(items) : RoutesTransformer.INSTANCE.toDtos(items);

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
			response.setCount(routesRepository.count(request, em, locale));
			response.setHasError(false);
		} else {
			response.setStatus(functionalError.DATA_EMPTY("routes", locale));
			response.setHasError(false);
			return response;
		}

		log.info("----end get Routes-----");
		return response;
	}

	/**
	 * get full RoutesDto by using Routes as object.
	 * 
	 * @param dto
	 * @param size
	 * @param isSimpleLoading
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	private RoutesDto getFullInfos(RoutesDto dto, Integer size, Boolean isSimpleLoading, Locale locale) throws Exception {
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
