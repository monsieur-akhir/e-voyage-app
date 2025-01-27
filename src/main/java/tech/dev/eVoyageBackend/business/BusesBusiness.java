                                                                    															
/*
 * Java business for entity table buses 
 * Created on 2025-01-12 ( Time 17:39:51 )
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
import tech.dev.eVoyageBackend.dao.entity.Buses;
import tech.dev.eVoyageBackend.dao.entity.Routes;
import tech.dev.eVoyageBackend.dao.entity.Companies;
import tech.dev.eVoyageBackend.dao.entity.*;
import tech.dev.eVoyageBackend.dao.repository.*;

/**
BUSINESS for table "buses"
 * 
 * @author Geo
 *
 */
@Log
@Component
public class BusesBusiness implements IBasicBusiness<Request<BusesDto>, Response<BusesDto>> {

	private Response<BusesDto> response;
	@Autowired
	private BusesRepository busesRepository;
	@Autowired
	private RoutesRepository routesRepository;
	@Autowired
	private CompaniesRepository companiesRepository;
	@Autowired
	private BookingsRepository bookingsRepository;
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

	public BusesBusiness() {
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	}
	
	/**
	 * create Buses by using BusesDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<BusesDto> create(Request<BusesDto> request, Locale locale)  throws ParseException {
		log.info("----begin create Buses-----");

		Response<BusesDto> response = new Response<BusesDto>();
		List<Buses>        items    = new ArrayList<Buses>();
			
		for (BusesDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, Object> fieldsToVerify = new HashMap<String, Object>();
			fieldsToVerify.put("companyId", dto.getCompanyId());
			fieldsToVerify.put("routeId", dto.getRouteId());
			fieldsToVerify.put("busNumber", dto.getBusNumber());
			fieldsToVerify.put("capacity", dto.getCapacity());
//			fieldsToVerify.put("seatNumbers", dto.getSeatNumbers());
//			fieldsToVerify.put("status", dto.getStatus());
//			fieldsToVerify.put("deletedAt", dto.getDeletedAt());
//			fieldsToVerify.put("deletedBy", dto.getDeletedBy());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verify if buses to insert do not exist
			Buses existingEntity = null;
			if (existingEntity != null) {
				response.setStatus(functionalError.DATA_EXIST("buses id -> " + dto.getId(), locale));
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
				Buses entityToSave = null;
			entityToSave = BusesTransformer.INSTANCE.toEntity(dto, existingRoutes, existingCompanies);
			entityToSave.setCreatedAt(Utilities.getCurrentDate());
			entityToSave.setCreatedBy(request.getUser());
			entityToSave.setIsDeleted(false);
			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<Buses> itemsSaved = null;
			// inserer les donnees en base de donnees
			itemsSaved = busesRepository.saveAll((Iterable<Buses>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("buses", locale));
				response.setHasError(true);
				return response;
			}
			List<BusesDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? BusesTransformer.INSTANCE.toLiteDtos(itemsSaved) : BusesTransformer.INSTANCE.toDtos(itemsSaved);

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

		log.info("----end create Buses-----");
		return response;
	}

	/**
	 * update Buses by using BusesDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<BusesDto> update(Request<BusesDto> request, Locale locale)  throws ParseException {
		log.info("----begin update Buses-----");

		Response<BusesDto> response = new Response<BusesDto>();
		List<Buses>        items    = new ArrayList<Buses>();
			
		for (BusesDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, Object> fieldsToVerify = new HashMap<String, Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la buses existe
			Buses entityToSave = null;
			entityToSave = busesRepository.findOne(dto.getId(), false);
			if (entityToSave == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("buses id -> " + dto.getId(), locale));
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
			if (Utilities.notBlank(dto.getBusNumber())) {
				entityToSave.setBusNumber(dto.getBusNumber());
			}
			if (dto.getCapacity() != null && dto.getCapacity() > 0) {
				entityToSave.setCapacity(dto.getCapacity());
			}
			if (Utilities.notBlank(dto.getSeatNumbers())) {
				entityToSave.setSeatNumbers(dto.getSeatNumbers());
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
			List<Buses> itemsSaved = null;
			// maj les donnees en base
			itemsSaved = busesRepository.saveAll((Iterable<Buses>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("buses", locale));
				response.setHasError(true);
				return response;
			}
			List<BusesDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? BusesTransformer.INSTANCE.toLiteDtos(itemsSaved) : BusesTransformer.INSTANCE.toDtos(itemsSaved);

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

		log.info("----end update Buses-----");
		return response;
	}

	/**
	 * delete Buses by using BusesDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<BusesDto> delete(Request<BusesDto> request, Locale locale)  {
		log.info("----begin delete Buses-----");

		Response<BusesDto> response = new Response<BusesDto>();
		List<Buses>        items    = new ArrayList<Buses>();
			
		for (BusesDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, Object> fieldsToVerify = new HashMap<String, Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la buses existe
			Buses existingEntity = null;
			existingEntity = busesRepository.findOne(dto.getId(), false);
			if (existingEntity == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("buses -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			// -----------------------------------------------------------------------
			// ----------- CHECK IF DATA IS USED
			// -----------------------------------------------------------------------

			// bookings
			List<Bookings> listOfBookings = bookingsRepository.findByBusId(existingEntity.getId(), false);
			if (listOfBookings != null && !listOfBookings.isEmpty()){
				response.setStatus(functionalError.DATA_NOT_DELETABLE("(" + listOfBookings.size() + ")", locale));
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
			busesRepository.saveAll((Iterable<Buses>) items);

			response.setHasError(false);
		}

		log.info("----end delete Buses-----");
		return response;
	}

	/**
	 * get Buses by using BusesDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<BusesDto> getByCriteria(Request<BusesDto> request, Locale locale)  throws Exception {
		log.info("----begin get Buses-----");

		Response<BusesDto> response = new Response<BusesDto>();
		List<Buses> items 			 = busesRepository.getByCriteria(request, em, locale);

		if (items != null && !items.isEmpty()) {
			List<BusesDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? BusesTransformer.INSTANCE.toLiteDtos(items) : BusesTransformer.INSTANCE.toDtos(items);

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
			response.setCount(busesRepository.count(request, em, locale));
			response.setHasError(false);
		} else {
			response.setStatus(functionalError.DATA_EMPTY("buses", locale));
			response.setHasError(false);
			return response;
		}

		log.info("----end get Buses-----");
		return response;
	}

	/**
	 * get full BusesDto by using Buses as object.
	 * 
	 * @param dto
	 * @param size
	 * @param isSimpleLoading
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	private BusesDto getFullInfos(BusesDto dto, Integer size, Boolean isSimpleLoading, Locale locale) throws Exception {
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
