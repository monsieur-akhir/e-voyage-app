                                                                                																		
/*
 * Java business for entity table departs 
 * Created on 2025-01-21 ( Time 18:33:54 )
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
import tech.dev.eVoyageBackend.dao.entity.Departs;
import tech.dev.eVoyageBackend.dao.entity.Buses;
import tech.dev.eVoyageBackend.dao.entity.Stations;
import tech.dev.eVoyageBackend.dao.entity.Stations;
import tech.dev.eVoyageBackend.dao.entity.*;
import tech.dev.eVoyageBackend.dao.repository.*;

/**
BUSINESS for table "departs"
 * 
 * @author Geo
 *
 */
@Log
@Component
public class DepartsBusiness implements IBasicBusiness<Request<DepartsDto>, Response<DepartsDto>> {

	private Response<DepartsDto> response;
	@Autowired
	private DepartsRepository departsRepository;
	@Autowired
	private BusesRepository busesRepository;
	@Autowired
	private StationsRepository stations2Repository;
	@Autowired
	private StationsRepository stationsRepository;
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

	public DepartsBusiness() {
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	}
	
	/**
	 * create Departs by using DepartsDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<DepartsDto> create(Request<DepartsDto> request, Locale locale)  throws ParseException {
		log.info("----begin create Departs-----");

		Response<DepartsDto> response = new Response<DepartsDto>();
		List<Departs>        items    = new ArrayList<Departs>();
			
		for (DepartsDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("busId", dto.getBusId());
			fieldsToVerify.put("originStationId", dto.getOriginStationId());
			fieldsToVerify.put("destinationStationId", dto.getDestinationStationId());
			fieldsToVerify.put("departureDate", dto.getDepartureDate());
			fieldsToVerify.put("departureTime", dto.getDepartureTime());
			fieldsToVerify.put("price", dto.getPrice());
			fieldsToVerify.put("maxSeats", dto.getMaxSeats());
			fieldsToVerify.put("availableSeats", dto.getAvailableSeats());
			fieldsToVerify.put("isActive", dto.getIsActive());
			fieldsToVerify.put("deletedAt", dto.getDeletedAt());
			fieldsToVerify.put("deletedBy", dto.getDeletedBy());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verify if departs to insert do not exist
			Departs existingEntity = null;
			if (existingEntity != null) {
				response.setStatus(functionalError.DATA_EXIST("departs id -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			// Verify if buses exist
			Buses existingBuses = null;
			if (dto.getBusId() != null && dto.getBusId() > 0){
				existingBuses = busesRepository.findOne(dto.getBusId(), false);
				if (existingBuses == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("buses busId -> " + dto.getBusId(), locale));
					response.setHasError(true);
					return response;
				}
			}
			// Verify if stations2 exist
			Stations existingStations2 = null;
			if (dto.getDestinationStationId() != null && dto.getDestinationStationId() > 0){
				existingStations2 = stations2Repository.findOne(dto.getDestinationStationId(), false);
				if (existingStations2 == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("stations2 destinationStationId -> " + dto.getDestinationStationId(), locale));
					response.setHasError(true);
					return response;
				}
			}
			// Verify if stations exist
			Stations existingStations = null;
			if (dto.getOriginStationId() != null && dto.getOriginStationId() > 0){
				existingStations = stationsRepository.findOne(dto.getOriginStationId(), false);
				if (existingStations == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("stations originStationId -> " + dto.getOriginStationId(), locale));
					response.setHasError(true);
					return response;
				}
			}
				Departs entityToSave = null;
			entityToSave = DepartsTransformer.INSTANCE.toEntity(dto, existingBuses, existingStations2, existingStations);
			entityToSave.setCreatedAt(Utilities.getCurrentDate());
			entityToSave.setCreatedBy(request.getUser());
			entityToSave.setIsDeleted(false);
			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<Departs> itemsSaved = null;
			// inserer les donnees en base de donnees
			itemsSaved = departsRepository.saveAll((Iterable<Departs>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("departs", locale));
				response.setHasError(true);
				return response;
			}
			List<DepartsDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? DepartsTransformer.INSTANCE.toLiteDtos(itemsSaved) : DepartsTransformer.INSTANCE.toDtos(itemsSaved);

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

		log.info("----end create Departs-----");
		return response;
	}

	/**
	 * update Departs by using DepartsDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<DepartsDto> update(Request<DepartsDto> request, Locale locale)  throws ParseException {
		log.info("----begin update Departs-----");

		Response<DepartsDto> response = new Response<DepartsDto>();
		List<Departs>        items    = new ArrayList<Departs>();
			
		for (DepartsDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la departs existe
			Departs entityToSave = null;
			entityToSave = departsRepository.findOne(dto.getId(), false);
			if (entityToSave == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("departs id -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			// Verify if buses exist
			if (dto.getBusId() != null && dto.getBusId() > 0){
				Buses existingBuses = busesRepository.findOne(dto.getBusId(), false);
				if (existingBuses == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("buses busId -> " + dto.getBusId(), locale));
					response.setHasError(true);
					return response;
				}
				entityToSave.setBuses(existingBuses);
			}
			// Verify if stations2 exist
			if (dto.getDestinationStationId() != null && dto.getDestinationStationId() > 0){
				Stations existingStations2 = stations2Repository.findOne(dto.getDestinationStationId(), false);
				if (existingStations2 == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("stations2 destinationStationId -> " + dto.getDestinationStationId(), locale));
					response.setHasError(true);
					return response;
				}
				entityToSave.setStations2(existingStations2);
			}
			// Verify if stations exist
			if (dto.getOriginStationId() != null && dto.getOriginStationId() > 0){
				Stations existingStations = stationsRepository.findOne(dto.getOriginStationId(), false);
				if (existingStations == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("stations originStationId -> " + dto.getOriginStationId(), locale));
					response.setHasError(true);
					return response;
				}
				entityToSave.setStations(existingStations);
			}
			if (dto.getDepartureDate() != null) {
				entityToSave.setDepartureDate(dto.getDepartureDate());
			}
			if (dto.getDepartureTime() != null) {
				entityToSave.setDepartureTime(dto.getDepartureTime());
			}
			if (dto.getPrice() != null && dto.getPrice() > 0) {
				entityToSave.setPrice(dto.getPrice());
			}
			if (dto.getMaxSeats() != null && dto.getMaxSeats() > 0) {
				entityToSave.setMaxSeats(dto.getMaxSeats());
			}
			if (dto.getAvailableSeats() != null && dto.getAvailableSeats() > 0) {
				entityToSave.setAvailableSeats(dto.getAvailableSeats());
			}
			if (dto.getIsActive() != null) {
				entityToSave.setIsActive(dto.getIsActive());
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
			List<Departs> itemsSaved = null;
			// maj les donnees en base
			itemsSaved = departsRepository.saveAll((Iterable<Departs>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("departs", locale));
				response.setHasError(true);
				return response;
			}
			List<DepartsDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? DepartsTransformer.INSTANCE.toLiteDtos(itemsSaved) : DepartsTransformer.INSTANCE.toDtos(itemsSaved);

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

		log.info("----end update Departs-----");
		return response;
	}

	/**
	 * delete Departs by using DepartsDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<DepartsDto> delete(Request<DepartsDto> request, Locale locale)  {
		log.info("----begin delete Departs-----");

		Response<DepartsDto> response = new Response<DepartsDto>();
		List<Departs>        items    = new ArrayList<Departs>();
			
		for (DepartsDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la departs existe
			Departs existingEntity = null;
			existingEntity = departsRepository.findOne(dto.getId(), false);
			if (existingEntity == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("departs -> " + dto.getId(), locale));
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
			departsRepository.saveAll((Iterable<Departs>) items);

			response.setHasError(false);
		}

		log.info("----end delete Departs-----");
		return response;
	}

	/**
	 * get Departs by using DepartsDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<DepartsDto> getByCriteria(Request<DepartsDto> request, Locale locale)  throws Exception {
		log.info("----begin get Departs-----");

		Response<DepartsDto> response = new Response<DepartsDto>();
		List<Departs> items 			 = departsRepository.getByCriteria(request, em, locale);

		if (items != null && !items.isEmpty()) {
			List<DepartsDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? DepartsTransformer.INSTANCE.toLiteDtos(items) : DepartsTransformer.INSTANCE.toDtos(items);

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
			response.setCount(departsRepository.count(request, em, locale));
			response.setHasError(false);
		} else {
			response.setStatus(functionalError.DATA_EMPTY("departs", locale));
			response.setHasError(false);
			return response;
		}

		log.info("----end get Departs-----");
		return response;
	}

	/**
	 * get full DepartsDto by using Departs as object.
	 * 
	 * @param dto
	 * @param size
	 * @param isSimpleLoading
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	private DepartsDto getFullInfos(DepartsDto dto, Integer size, Boolean isSimpleLoading, Locale locale) throws Exception {
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
