                                                        												
/*
 * Java business for entity table districts 
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
import tech.dev.eVoyageBackend.dao.entity.Districts;
import tech.dev.eVoyageBackend.dao.entity.Cities;
import tech.dev.eVoyageBackend.dao.entity.*;
import tech.dev.eVoyageBackend.dao.repository.*;

/**
BUSINESS for table "districts"
 * 
 * @author Geo
 *
 */
@Log
@Component
public class DistrictsBusiness implements IBasicBusiness<Request<DistrictsDto>, Response<DistrictsDto>> {

	private Response<DistrictsDto> response;
	@Autowired
	private DistrictsRepository districtsRepository;
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

	public DistrictsBusiness() {
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	}
	
	/**
	 * create Districts by using DistrictsDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<DistrictsDto> create(Request<DistrictsDto> request, Locale locale)  throws ParseException {
		log.info("----begin create Districts-----");

		Response<DistrictsDto> response = new Response<DistrictsDto>();
		List<Districts>        items    = new ArrayList<Districts>();
			
		for (DistrictsDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("name", dto.getName());
			fieldsToVerify.put("cityId", dto.getCityId());
			fieldsToVerify.put("isAvailable", dto.getIsAvailable());
			fieldsToVerify.put("deletedAt", dto.getDeletedAt());
			fieldsToVerify.put("deletedBy", dto.getDeletedBy());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verify if districts to insert do not exist
			Districts existingEntity = null;
			if (existingEntity != null) {
				response.setStatus(functionalError.DATA_EXIST("districts id -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			// verif unique name in db
			existingEntity = districtsRepository.findByName(dto.getName(), false);
			if (existingEntity != null) {
				response.setStatus(functionalError.DATA_EXIST("districts name -> " + dto.getName(), locale));
				response.setHasError(true);
				return response;
			}
			// verif unique name in items to save
			if (items.stream().anyMatch(a -> a.getName().equalsIgnoreCase(dto.getName()))) {
				response.setStatus(functionalError.DATA_DUPLICATE(" name ", locale));
				response.setHasError(true);
				return response;
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
				Districts entityToSave = null;
			entityToSave = DistrictsTransformer.INSTANCE.toEntity(dto, existingCities);
			entityToSave.setCreatedAt(Utilities.getCurrentDate());
			entityToSave.setCreatedBy(request.getUser());
			entityToSave.setIsDeleted(false);
			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<Districts> itemsSaved = null;
			// inserer les donnees en base de donnees
			itemsSaved = districtsRepository.saveAll((Iterable<Districts>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("districts", locale));
				response.setHasError(true);
				return response;
			}
			List<DistrictsDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? DistrictsTransformer.INSTANCE.toLiteDtos(itemsSaved) : DistrictsTransformer.INSTANCE.toDtos(itemsSaved);

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

		log.info("----end create Districts-----");
		return response;
	}

	/**
	 * update Districts by using DistrictsDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<DistrictsDto> update(Request<DistrictsDto> request, Locale locale)  throws ParseException {
		log.info("----begin update Districts-----");

		Response<DistrictsDto> response = new Response<DistrictsDto>();
		List<Districts>        items    = new ArrayList<Districts>();
			
		for (DistrictsDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la districts existe
			Districts entityToSave = null;
			entityToSave = districtsRepository.findOne(dto.getId(), false);
			if (entityToSave == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("districts id -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
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
			List<Districts> itemsSaved = null;
			// maj les donnees en base
			itemsSaved = districtsRepository.saveAll((Iterable<Districts>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("districts", locale));
				response.setHasError(true);
				return response;
			}
			List<DistrictsDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? DistrictsTransformer.INSTANCE.toLiteDtos(itemsSaved) : DistrictsTransformer.INSTANCE.toDtos(itemsSaved);

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

		log.info("----end update Districts-----");
		return response;
	}

	/**
	 * delete Districts by using DistrictsDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<DistrictsDto> delete(Request<DistrictsDto> request, Locale locale)  {
		log.info("----begin delete Districts-----");

		Response<DistrictsDto> response = new Response<DistrictsDto>();
		List<Districts>        items    = new ArrayList<Districts>();
			
		for (DistrictsDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la districts existe
			Districts existingEntity = null;
			existingEntity = districtsRepository.findOne(dto.getId(), false);
			if (existingEntity == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("districts -> " + dto.getId(), locale));
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
			districtsRepository.saveAll((Iterable<Districts>) items);

			response.setHasError(false);
		}

		log.info("----end delete Districts-----");
		return response;
	}

	/**
	 * get Districts by using DistrictsDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<DistrictsDto> getByCriteria(Request<DistrictsDto> request, Locale locale)  throws Exception {
		log.info("----begin get Districts-----");

		Response<DistrictsDto> response = new Response<DistrictsDto>();
		List<Districts> items 			 = districtsRepository.getByCriteria(request, em, locale);

		if (items != null && !items.isEmpty()) {
			List<DistrictsDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? DistrictsTransformer.INSTANCE.toLiteDtos(items) : DistrictsTransformer.INSTANCE.toDtos(items);

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
			response.setCount(districtsRepository.count(request, em, locale));
			response.setHasError(false);
		} else {
			response.setStatus(functionalError.DATA_EMPTY("districts", locale));
			response.setHasError(false);
			return response;
		}

		log.info("----end get Districts-----");
		return response;
	}

	/**
	 * get full DistrictsDto by using Districts as object.
	 * 
	 * @param dto
	 * @param size
	 * @param isSimpleLoading
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	private DistrictsDto getFullInfos(DistrictsDto dto, Integer size, Boolean isSimpleLoading, Locale locale) throws Exception {
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
