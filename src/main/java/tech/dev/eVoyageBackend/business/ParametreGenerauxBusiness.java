                                                    											
/*
 * Java business for entity table parametre_generaux 
 * Created on 2024-12-19 ( Time 23:36:43 )
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
BUSINESS for table "parametre_generaux"
 * 
 * @author Geo
 *
 */
@Log
@Component
public class ParametreGenerauxBusiness implements IBasicBusiness<Request<ParametreGenerauxDto>, Response<ParametreGenerauxDto>> {

	private Response<ParametreGenerauxDto> response;
	@Autowired
	private ParametreGenerauxRepository parametreGenerauxRepository;
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

	public ParametreGenerauxBusiness() {
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	}
	
	/**
	 * create ParametreGeneraux by using ParametreGenerauxDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<ParametreGenerauxDto> create(Request<ParametreGenerauxDto> request, Locale locale)  throws ParseException {
		log.info("----begin create ParametreGeneraux-----");

		Response<ParametreGenerauxDto> response = new Response<ParametreGenerauxDto>();
		List<ParametreGeneraux>        items    = new ArrayList<ParametreGeneraux>();
			
		for (ParametreGenerauxDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, Object> fieldsToVerify = new HashMap<String, Object>();
			fieldsToVerify.put("deletedAt", dto.getDeletedAt());
			fieldsToVerify.put("deletedBy", dto.getDeletedBy());
			fieldsToVerify.put("paramName", dto.getParamName());
			fieldsToVerify.put("paramValue", dto.getParamValue());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verify if parametreGeneraux to insert do not exist
			ParametreGeneraux existingEntity = null;
			if (existingEntity != null) {
				response.setStatus(functionalError.DATA_EXIST("parametreGeneraux id -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

				ParametreGeneraux entityToSave = null;
			entityToSave = ParametreGenerauxTransformer.INSTANCE.toEntity(dto);
			entityToSave.setCreatedAt(Utilities.getCurrentDate());
			entityToSave.setCreatedBy(request.getUser());
			entityToSave.setIsDeleted(false);
			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<ParametreGeneraux> itemsSaved = null;
			// inserer les donnees en base de donnees
			itemsSaved = parametreGenerauxRepository.saveAll((Iterable<ParametreGeneraux>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("parametreGeneraux", locale));
				response.setHasError(true);
				return response;
			}
			List<ParametreGenerauxDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? ParametreGenerauxTransformer.INSTANCE.toLiteDtos(itemsSaved) : ParametreGenerauxTransformer.INSTANCE.toDtos(itemsSaved);

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

		log.info("----end create ParametreGeneraux-----");
		return response;
	}

	/**
	 * update ParametreGeneraux by using ParametreGenerauxDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<ParametreGenerauxDto> update(Request<ParametreGenerauxDto> request, Locale locale)  throws ParseException {
		log.info("----begin update ParametreGeneraux-----");

		Response<ParametreGenerauxDto> response = new Response<ParametreGenerauxDto>();
		List<ParametreGeneraux>        items    = new ArrayList<ParametreGeneraux>();
			
		for (ParametreGenerauxDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, Object> fieldsToVerify = new HashMap<String, Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la parametreGeneraux existe
			ParametreGeneraux entityToSave = null;
			entityToSave = parametreGenerauxRepository.findOne(dto.getId(), false);
			if (entityToSave == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("parametreGeneraux id -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			if (dto.getCreatedBy() != null && dto.getCreatedBy() > 0) {
				entityToSave.setCreatedBy(dto.getCreatedBy());
			}
			if (Utilities.notBlank(dto.getDeletedAt())) {
				entityToSave.setDeletedAt(dateFormat.parse(dto.getDeletedAt()));
			}
			if (dto.getDeletedBy() != null && dto.getDeletedBy() > 0) {
				entityToSave.setDeletedBy(dto.getDeletedBy());
			}
			if (Utilities.notBlank(dto.getParamName())) {
				entityToSave.setParamName(dto.getParamName());
			}
			if (Utilities.notBlank(dto.getParamValue())) {
				entityToSave.setParamValue(dto.getParamValue());
			}
			if (dto.getUpdatedBy() != null && dto.getUpdatedBy() > 0) {
				entityToSave.setUpdatedBy(dto.getUpdatedBy());
			}
			entityToSave.setUpdatedAt(Utilities.getCurrentDate());
			entityToSave.setUpdatedBy(request.getUser());
			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<ParametreGeneraux> itemsSaved = null;
			// maj les donnees en base
			itemsSaved = parametreGenerauxRepository.saveAll((Iterable<ParametreGeneraux>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("parametreGeneraux", locale));
				response.setHasError(true);
				return response;
			}
			List<ParametreGenerauxDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? ParametreGenerauxTransformer.INSTANCE.toLiteDtos(itemsSaved) : ParametreGenerauxTransformer.INSTANCE.toDtos(itemsSaved);

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

		log.info("----end update ParametreGeneraux-----");
		return response;
	}

	/**
	 * delete ParametreGeneraux by using ParametreGenerauxDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<ParametreGenerauxDto> delete(Request<ParametreGenerauxDto> request, Locale locale)  {
		log.info("----begin delete ParametreGeneraux-----");

		Response<ParametreGenerauxDto> response = new Response<ParametreGenerauxDto>();
		List<ParametreGeneraux>        items    = new ArrayList<ParametreGeneraux>();
			
		for (ParametreGenerauxDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, Object> fieldsToVerify = new HashMap<String, Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la parametreGeneraux existe
			ParametreGeneraux existingEntity = null;
			existingEntity = parametreGenerauxRepository.findOne(dto.getId(), false);
			if (existingEntity == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("parametreGeneraux -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			// -----------------------------------------------------------------------
			// ----------- CHECK IF DATA IS USED
			// -----------------------------------------------------------------------



			existingEntity.setDeletedAt(Utilities.getCurrentDate());
			existingEntity.setDeletedBy(request.getUser());
			existingEntity.setIsDeleted(true);
			items.add(existingEntity);
		}

		if (!items.isEmpty()) {
			// supprimer les donnees en base
			parametreGenerauxRepository.saveAll((Iterable<ParametreGeneraux>) items);

			response.setHasError(false);
		}

		log.info("----end delete ParametreGeneraux-----");
		return response;
	}

	/**
	 * get ParametreGeneraux by using ParametreGenerauxDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<ParametreGenerauxDto> getByCriteria(Request<ParametreGenerauxDto> request, Locale locale)  throws Exception {
		log.info("----begin get ParametreGeneraux-----");

		Response<ParametreGenerauxDto> response = new Response<ParametreGenerauxDto>();
		List<ParametreGeneraux> items 			 = parametreGenerauxRepository.getByCriteria(request, em, locale);

		if (items != null && !items.isEmpty()) {
			List<ParametreGenerauxDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? ParametreGenerauxTransformer.INSTANCE.toLiteDtos(items) : ParametreGenerauxTransformer.INSTANCE.toDtos(items);

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
			response.setCount(parametreGenerauxRepository.count(request, em, locale));
			response.setHasError(false);
		} else {
			response.setStatus(functionalError.DATA_EMPTY("parametreGeneraux", locale));
			response.setHasError(false);
			return response;
		}

		log.info("----end get ParametreGeneraux-----");
		return response;
	}

	/**
	 * get full ParametreGenerauxDto by using ParametreGeneraux as object.
	 * 
	 * @param dto
	 * @param size
	 * @param isSimpleLoading
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	private ParametreGenerauxDto getFullInfos(ParametreGenerauxDto dto, Integer size, Boolean isSimpleLoading, Locale locale) throws Exception {
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
