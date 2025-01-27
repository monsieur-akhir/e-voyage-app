                                                    											
/*
 * Java business for entity table cities 
 * Created on 2025-01-21 ( Time 18:28:28 )
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
import tech.dev.eVoyageBackend.dao.entity.Cities;
import tech.dev.eVoyageBackend.dao.entity.*;
import tech.dev.eVoyageBackend.dao.repository.*;

/**
BUSINESS for table "cities"
 * 
 * @author Geo
 *
 */
@Log
@Component
public class CitiesBusiness implements IBasicBusiness<Request<CitiesDto>, Response<CitiesDto>> {

	private Response<CitiesDto> response;
	@Autowired
	private CitiesRepository citiesRepository;
	@Autowired
	private DistrictsRepository districtsRepository;
	@Autowired
	private StationsRepository stationsRepository;
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

	public CitiesBusiness() {
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	}
	
	/**
	 * create Cities by using CitiesDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<CitiesDto> create(Request<CitiesDto> request, Locale locale)  throws ParseException {
		log.info("----begin create Cities-----");

		Response<CitiesDto> response = new Response<CitiesDto>();
		List<Cities>        items    = new ArrayList<Cities>();
			
		for (CitiesDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, Object> fieldsToVerify = new HashMap<String, Object>();
			fieldsToVerify.put("name", dto.getName());
			fieldsToVerify.put("isAvailable", dto.getIsAvailable());
			//fieldsToVerify.put("deletedAt", dto.getDeletedAt());
			//fieldsToVerify.put("deletedBy", dto.getDeletedBy());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verify if cities to insert do not exist
			Cities existingEntity = null;
			if (existingEntity != null) {
				response.setStatus(functionalError.DATA_EXIST("cities id -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			// verif unique name in db
			existingEntity = citiesRepository.findByName(dto.getName(), false);
			if (existingEntity != null) {
				response.setStatus(functionalError.DATA_EXIST("cities name -> " + dto.getName(), locale));
				response.setHasError(true);
				return response;
			}
			// verif unique name in items to save
			if (items.stream().anyMatch(a -> a.getName().equalsIgnoreCase(dto.getName()))) {
				response.setStatus(functionalError.DATA_DUPLICATE(" name ", locale));
				response.setHasError(true);
				return response;
			}

				Cities entityToSave = null;
			entityToSave = CitiesTransformer.INSTANCE.toEntity(dto);
			entityToSave.setCreatedAt(Utilities.getCurrentDate());
			entityToSave.setCreatedBy(request.getUser());
			entityToSave.setIsDeleted(false);
			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<Cities> itemsSaved = null;
			// inserer les donnees en base de donnees
			itemsSaved = citiesRepository.saveAll((Iterable<Cities>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("cities", locale));
				response.setHasError(true);
				return response;
			}
			List<CitiesDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? CitiesTransformer.INSTANCE.toLiteDtos(itemsSaved) : CitiesTransformer.INSTANCE.toDtos(itemsSaved);

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

		log.info("----end create Cities-----");
		return response;
	}

	/**
	 * update Cities by using CitiesDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<CitiesDto> update(Request<CitiesDto> request, Locale locale)  throws ParseException {
		log.info("----begin update Cities-----");

		Response<CitiesDto> response = new Response<CitiesDto>();
		List<Cities>        items    = new ArrayList<Cities>();
			
		for (CitiesDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, Object> fieldsToVerify = new HashMap<String, Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la cities existe
			Cities entityToSave = null;
			entityToSave = citiesRepository.findOne(dto.getId(), false);
			if (entityToSave == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("cities id -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			if (Utilities.notBlank(dto.getName())) {
				entityToSave.setName(dto.getName());
			}
			if (dto.getIsAvailable() != null) {
				entityToSave.setIsAvailable(dto.getIsAvailable());
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
			List<Cities> itemsSaved = null;
			// maj les donnees en base
			itemsSaved = citiesRepository.saveAll((Iterable<Cities>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("cities", locale));
				response.setHasError(true);
				return response;
			}
			List<CitiesDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? CitiesTransformer.INSTANCE.toLiteDtos(itemsSaved) : CitiesTransformer.INSTANCE.toDtos(itemsSaved);

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

		log.info("----end update Cities-----");
		return response;
	}

	/**
	 * delete Cities by using CitiesDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<CitiesDto> delete(Request<CitiesDto> request, Locale locale)  {
		log.info("----begin delete Cities-----");

		Response<CitiesDto> response = new Response<CitiesDto>();
		List<Cities>        items    = new ArrayList<Cities>();
			
		for (CitiesDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, Object> fieldsToVerify = new HashMap<String, Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la cities existe
			Cities existingEntity = null;
			existingEntity = citiesRepository.findOne(dto.getId(), false);
			if (existingEntity == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("cities -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			// -----------------------------------------------------------------------
			// ----------- CHECK IF DATA IS USED
			// -----------------------------------------------------------------------

			// districts
			List<Districts> listOfDistricts = districtsRepository.findByCityId(existingEntity.getId(), false);
			if (listOfDistricts != null && !listOfDistricts.isEmpty()){
				response.setStatus(functionalError.DATA_NOT_DELETABLE("(" + listOfDistricts.size() + ")", locale));
				response.setHasError(true);
				return response;
			}
			// stations
			List<Stations> listOfStations = stationsRepository.findByCityId(existingEntity.getId(), false);
			if (listOfStations != null && !listOfStations.isEmpty()){
				response.setStatus(functionalError.DATA_NOT_DELETABLE("(" + listOfStations.size() + ")", locale));
				response.setHasError(true);
				return response;
			}
			// routes
			List<Routes> listOfRoutes2 = routesRepository.findByDestinationCityId(existingEntity.getId(), false);
			if (listOfRoutes2 != null && !listOfRoutes2.isEmpty()){
				response.setStatus(functionalError.DATA_NOT_DELETABLE("(" + listOfRoutes2.size() + ")", locale));
				response.setHasError(true);
				return response;
			}
			// routes
			List<Routes> listOfRoutes = routesRepository.findByDestinationCityId(existingEntity.getId(), false);
			if (listOfRoutes != null && !listOfRoutes.isEmpty()){
				response.setStatus(functionalError.DATA_NOT_DELETABLE("(" + listOfRoutes.size() + ")", locale));
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
			citiesRepository.saveAll((Iterable<Cities>) items);

			response.setHasError(false);
		}

		log.info("----end delete Cities-----");
		return response;
	}

	/**
	 * get Cities by using CitiesDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<CitiesDto> getByCriteria(Request<CitiesDto> request, Locale locale)  throws Exception {
		log.info("----begin get Cities-----");

		Response<CitiesDto> response = new Response<CitiesDto>();
		List<Cities> items 			 = citiesRepository.getByCriteria(request, em, locale);

		if (items != null && !items.isEmpty()) {
			List<CitiesDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? CitiesTransformer.INSTANCE.toLiteDtos(items) : CitiesTransformer.INSTANCE.toDtos(items);

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
			response.setCount(citiesRepository.count(request, em, locale));
			response.setHasError(false);
		} else {
			response.setStatus(functionalError.DATA_EMPTY("cities", locale));
			response.setHasError(false);
			return response;
		}

		log.info("----end get Cities-----");
		return response;
	}

	/**
	 * get full CitiesDto by using Cities as object.
	 * 
	 * @param dto
	 * @param size
	 * @param isSimpleLoading
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	private CitiesDto getFullInfos(CitiesDto dto, Integer size, Boolean isSimpleLoading, Locale locale) throws Exception {
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
