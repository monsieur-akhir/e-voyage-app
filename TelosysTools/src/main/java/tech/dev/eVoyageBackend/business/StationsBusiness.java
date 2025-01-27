                                                                														
/*
 * Java business for entity table stations 
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
import tech.dev.eVoyageBackend.dao.entity.Stations;
import tech.dev.eVoyageBackend.dao.entity.Companies;
import tech.dev.eVoyageBackend.dao.entity.Cities;
import tech.dev.eVoyageBackend.dao.entity.*;
import tech.dev.eVoyageBackend.dao.repository.*;

/**
BUSINESS for table "stations"
 * 
 * @author Geo
 *
 */
@Log
@Component
public class StationsBusiness implements IBasicBusiness<Request<StationsDto>, Response<StationsDto>> {

	private Response<StationsDto> response;
	@Autowired
	private StationsRepository stationsRepository;
	@Autowired
	private DepartsRepository departsRepository;
	@Autowired
	private CompaniesRepository companiesRepository;
	@Autowired
	private CitiesRepository citiesRepository;
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

	public StationsBusiness() {
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	}
	
	/**
	 * create Stations by using StationsDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<StationsDto> create(Request<StationsDto> request, Locale locale)  throws ParseException {
		log.info("----begin create Stations-----");

		Response<StationsDto> response = new Response<StationsDto>();
		List<Stations>        items    = new ArrayList<Stations>();
			
		for (StationsDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("name", dto.getName());
			fieldsToVerify.put("address", dto.getAddress());
			fieldsToVerify.put("cityId", dto.getCityId());
			fieldsToVerify.put("companyId", dto.getCompanyId());
			fieldsToVerify.put("isAvailable", dto.getIsAvailable());
			fieldsToVerify.put("deletedAt", dto.getDeletedAt());
			fieldsToVerify.put("deletedBy", dto.getDeletedBy());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verify if stations to insert do not exist
			Stations existingEntity = null;
			if (existingEntity != null) {
				response.setStatus(functionalError.DATA_EXIST("stations id -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			// verif unique name in db
			existingEntity = stationsRepository.findByName(dto.getName(), false);
			if (existingEntity != null) {
				response.setStatus(functionalError.DATA_EXIST("stations name -> " + dto.getName(), locale));
				response.setHasError(true);
				return response;
			}
			// verif unique name in items to save
			if (items.stream().anyMatch(a -> a.getName().equalsIgnoreCase(dto.getName()))) {
				response.setStatus(functionalError.DATA_DUPLICATE(" name ", locale));
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
			// Verify if cities exist
			Cities existingCities = null;
			if (dto.getCityId() != null && dto.getCityId() > 0){
				existingCities = citiesRepository.findOne(dto.getCityId(), false);
				if (existingCities == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("cities cityId -> " + dto.getCityId(), locale));
					response.setHasError(true);
					return response;
				}
			}
				Stations entityToSave = null;
			entityToSave = StationsTransformer.INSTANCE.toEntity(dto, existingCompanies, existingCities);
			entityToSave.setCreatedAt(Utilities.getCurrentDate());
			entityToSave.setCreatedBy(request.getUser());
			entityToSave.setIsDeleted(false);
			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<Stations> itemsSaved = null;
			// inserer les donnees en base de donnees
			itemsSaved = stationsRepository.saveAll((Iterable<Stations>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("stations", locale));
				response.setHasError(true);
				return response;
			}
			List<StationsDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? StationsTransformer.INSTANCE.toLiteDtos(itemsSaved) : StationsTransformer.INSTANCE.toDtos(itemsSaved);

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

		log.info("----end create Stations-----");
		return response;
	}

	/**
	 * update Stations by using StationsDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<StationsDto> update(Request<StationsDto> request, Locale locale)  throws ParseException {
		log.info("----begin update Stations-----");

		Response<StationsDto> response = new Response<StationsDto>();
		List<Stations>        items    = new ArrayList<Stations>();
			
		for (StationsDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la stations existe
			Stations entityToSave = null;
			entityToSave = stationsRepository.findOne(dto.getId(), false);
			if (entityToSave == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("stations id -> " + dto.getId(), locale));
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
			// Verify if cities exist
			if (dto.getCityId() != null && dto.getCityId() > 0){
				Cities existingCities = citiesRepository.findOne(dto.getCityId(), false);
				if (existingCities == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("cities cityId -> " + dto.getCityId(), locale));
					response.setHasError(true);
					return response;
				}
				entityToSave.setCities(existingCities);
			}
			if (Utilities.notBlank(dto.getName())) {
				entityToSave.setName(dto.getName());
			}
			if (Utilities.notBlank(dto.getAddress())) {
				entityToSave.setAddress(dto.getAddress());
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
			List<Stations> itemsSaved = null;
			// maj les donnees en base
			itemsSaved = stationsRepository.saveAll((Iterable<Stations>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("stations", locale));
				response.setHasError(true);
				return response;
			}
			List<StationsDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? StationsTransformer.INSTANCE.toLiteDtos(itemsSaved) : StationsTransformer.INSTANCE.toDtos(itemsSaved);

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

		log.info("----end update Stations-----");
		return response;
	}

	/**
	 * delete Stations by using StationsDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<StationsDto> delete(Request<StationsDto> request, Locale locale)  {
		log.info("----begin delete Stations-----");

		Response<StationsDto> response = new Response<StationsDto>();
		List<Stations>        items    = new ArrayList<Stations>();
			
		for (StationsDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la stations existe
			Stations existingEntity = null;
			existingEntity = stationsRepository.findOne(dto.getId(), false);
			if (existingEntity == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("stations -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			// -----------------------------------------------------------------------
			// ----------- CHECK IF DATA IS USED
			// -----------------------------------------------------------------------

			// departs
			List<Departs> listOfDeparts2 = departsRepository.findByDestinationStationId(existingEntity.getId(), false);
			if (listOfDeparts2 != null && !listOfDeparts2.isEmpty()){
				response.setStatus(functionalError.DATA_NOT_DELETABLE("(" + listOfDeparts2.size() + ")", locale));
				response.setHasError(true);
				return response;
			}
			// departs
			List<Departs> listOfDeparts = departsRepository.findByDestinationStationId(existingEntity.getId(), false);
			if (listOfDeparts != null && !listOfDeparts.isEmpty()){
				response.setStatus(functionalError.DATA_NOT_DELETABLE("(" + listOfDeparts.size() + ")", locale));
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
			stationsRepository.saveAll((Iterable<Stations>) items);

			response.setHasError(false);
		}

		log.info("----end delete Stations-----");
		return response;
	}

	/**
	 * get Stations by using StationsDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<StationsDto> getByCriteria(Request<StationsDto> request, Locale locale)  throws Exception {
		log.info("----begin get Stations-----");

		Response<StationsDto> response = new Response<StationsDto>();
		List<Stations> items 			 = stationsRepository.getByCriteria(request, em, locale);

		if (items != null && !items.isEmpty()) {
			List<StationsDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? StationsTransformer.INSTANCE.toLiteDtos(items) : StationsTransformer.INSTANCE.toDtos(items);

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
			response.setCount(stationsRepository.count(request, em, locale));
			response.setHasError(false);
		} else {
			response.setStatus(functionalError.DATA_EMPTY("stations", locale));
			response.setHasError(false);
			return response;
		}

		log.info("----end get Stations-----");
		return response;
	}

	/**
	 * get full StationsDto by using Stations as object.
	 * 
	 * @param dto
	 * @param size
	 * @param isSimpleLoading
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	private StationsDto getFullInfos(StationsDto dto, Integer size, Boolean isSimpleLoading, Locale locale) throws Exception {
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
