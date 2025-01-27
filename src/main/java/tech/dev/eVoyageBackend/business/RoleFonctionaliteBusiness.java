                                        									
/*
 * Java business for entity table role_fonctionalite 
 * Created on 2023-08-30 ( Time 17:27:04 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2018 Geo. All Rights Reserved.
 */

 package tech.dev.eVoyageBackend.business;

 import java.text.ParseException;
 import java.text.SimpleDateFormat;
 import java.util.ArrayList;
 import java.util.Collections;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Locale;
 import java.util.Map;
 
 import javax.persistence.EntityManager;
 import javax.persistence.PersistenceContext;
 
 import org.apache.commons.lang3.StringUtils;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Component;
 
 import lombok.extern.java.Log;
import tech.dev.eVoyageBackend.dao.entity.Fonctionalite;
import tech.dev.eVoyageBackend.dao.entity.Role;
import tech.dev.eVoyageBackend.dao.entity.RoleFonctionalite;
import tech.dev.eVoyageBackend.dao.repository.FonctionaliteRepository;
import tech.dev.eVoyageBackend.dao.repository.RoleFonctionaliteRepository;
import tech.dev.eVoyageBackend.dao.repository.RoleRepository;
import tech.dev.eVoyageBackend.utils.ExceptionUtils;
import tech.dev.eVoyageBackend.utils.FunctionalError;
import tech.dev.eVoyageBackend.utils.TechnicalError;
import tech.dev.eVoyageBackend.utils.Utilities;
import tech.dev.eVoyageBackend.utils.Validate;
import tech.dev.eVoyageBackend.utils.contract.IBasicBusiness;
import tech.dev.eVoyageBackend.utils.contract.Request;
import tech.dev.eVoyageBackend.utils.contract.Response;
import tech.dev.eVoyageBackend.utils.dto.RoleFonctionaliteDto;
import tech.dev.eVoyageBackend.utils.dto.transformer.RoleFonctionaliteTransformer;

/**
 BUSINESS for table "role_fonctionalite"
 *
 * @author Geo
 *
 */
@Log
@Component
public class RoleFonctionaliteBusiness implements IBasicBusiness<Request<RoleFonctionaliteDto>, Response<RoleFonctionaliteDto>> {

	private Response<RoleFonctionaliteDto> response;
	@Autowired
	private RoleFonctionaliteRepository roleFonctionaliteRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private FonctionaliteRepository fonctionaliteRepository;
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

	public RoleFonctionaliteBusiness() {
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	}

	/**
	 * create RoleFonctionalite by using RoleFonctionaliteDto as object.
	 *
	 * @param request
	 * @return response
	 *
	 */
	@Override
	public Response<RoleFonctionaliteDto> create(Request<RoleFonctionaliteDto> request, Locale locale)  throws ParseException {
		log.info("----begin create RoleFonctionalite-----");

		Response<RoleFonctionaliteDto> response = new Response<RoleFonctionaliteDto>();
		List<RoleFonctionalite>        items    = new ArrayList<RoleFonctionalite>();

		for (RoleFonctionaliteDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, Object> fieldsToVerify = new HashMap<String, Object>();
			fieldsToVerify.put("roleId", dto.getRoleId());
			fieldsToVerify.put("fonctionnaliteId", dto.getFonctionnaliteId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verify if roleFonctionalite to insert do not exist
			RoleFonctionalite existingEntity = null;
			if (existingEntity != null) {
				response.setStatus(functionalError.DATA_EXIST("roleFonctionalite id -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			// Verify if role exist
			Role existingRole = null;
			if (dto.getRoleId() != null && dto.getRoleId() > 0){
				existingRole = roleRepository.findOne(dto.getRoleId(), false);
				if (existingRole == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("role roleId -> " + dto.getRoleId(), locale));
					response.setHasError(true);
					return response;
				}
			}
			// Verify if fonctionalite exist
			Fonctionalite existingFonctionalite = null;
			if (dto.getFonctionnaliteId() != null && dto.getFonctionnaliteId() > 0){
				existingFonctionalite = fonctionaliteRepository.findOne(dto.getFonctionnaliteId(), false);
				if (existingFonctionalite == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("fonctionalite fonctionnaliteId -> " + dto.getFonctionnaliteId(), locale));
					response.setHasError(true);
					return response;
				}
			}
			RoleFonctionalite entityToSave = null;
			entityToSave = RoleFonctionaliteTransformer.INSTANCE.toEntity(dto, existingRole, existingFonctionalite);
			entityToSave.setCreatedAt(Utilities.getCurrentDate());
			entityToSave.setCreatedBy(request.getUser());
			entityToSave.setIsDeleted(false);
			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<RoleFonctionalite> itemsSaved = null;
			// inserer les donnees en base de donnees
			itemsSaved = roleFonctionaliteRepository.saveAll((Iterable<RoleFonctionalite>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("roleFonctionalite", locale));
				response.setHasError(true);
				return response;
			}
			List<RoleFonctionaliteDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? RoleFonctionaliteTransformer.INSTANCE.toLiteDtos(itemsSaved) : RoleFonctionaliteTransformer.INSTANCE.toDtos(itemsSaved);

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

		log.info("----end create RoleFonctionalite-----");
		return response;
	}

	/**
	 * update RoleFonctionalite by using RoleFonctionaliteDto as object.
	 *
	 * @param request
	 * @return response
	 *
	 */
	@Override
	public Response<RoleFonctionaliteDto> update(Request<RoleFonctionaliteDto> request, Locale locale)  throws ParseException {
		log.info("----begin update RoleFonctionalite-----");

		Response<RoleFonctionaliteDto> response = new Response<RoleFonctionaliteDto>();
		List<RoleFonctionalite>        items    = new ArrayList<RoleFonctionalite>();

		for (RoleFonctionaliteDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, Object> fieldsToVerify = new HashMap<String, Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la roleFonctionalite existe
			RoleFonctionalite entityToSave = null;
			entityToSave = roleFonctionaliteRepository.findOne(dto.getId(), false);
			if (entityToSave == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("roleFonctionalite id -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			// Verify if role exist
			if (dto.getRoleId() != null && dto.getRoleId() > 0){
				Role existingRole = roleRepository.findOne(dto.getRoleId(), false);
				if (existingRole == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("role roleId -> " + dto.getRoleId(), locale));
					response.setHasError(true);
					return response;
				}
				entityToSave.setRole(existingRole);
			}
			// Verify if fonctionalite exist
			if (dto.getFonctionnaliteId() != null && dto.getFonctionnaliteId() > 0){
				Fonctionalite existingFonctionalite = fonctionaliteRepository.findOne(dto.getFonctionnaliteId(), false);
				if (existingFonctionalite == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("fonctionalite fonctionnaliteId -> " + dto.getFonctionnaliteId(), locale));
					response.setHasError(true);
					return response;
				}
				entityToSave.setFonctionalite(existingFonctionalite);
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
			List<RoleFonctionalite> itemsSaved = null;
			// maj les donnees en base
			itemsSaved = roleFonctionaliteRepository.saveAll((Iterable<RoleFonctionalite>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("roleFonctionalite", locale));
				response.setHasError(true);
				return response;
			}
			List<RoleFonctionaliteDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? RoleFonctionaliteTransformer.INSTANCE.toLiteDtos(itemsSaved) : RoleFonctionaliteTransformer.INSTANCE.toDtos(itemsSaved);

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

		log.info("----end update RoleFonctionalite-----");
		return response;
	}

	/**
	 * delete RoleFonctionalite by using RoleFonctionaliteDto as object.
	 *
	 * @param request
	 * @return response
	 *
	 */
	@Override
	public Response<RoleFonctionaliteDto> delete(Request<RoleFonctionaliteDto> request, Locale locale)  {
		log.info("----begin delete RoleFonctionalite-----");

		Response<RoleFonctionaliteDto> response = new Response<RoleFonctionaliteDto>();
		List<RoleFonctionalite>        items    = new ArrayList<RoleFonctionalite>();

		for (RoleFonctionaliteDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, Object> fieldsToVerify = new HashMap<String, Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la roleFonctionalite existe
			RoleFonctionalite existingEntity = null;
			existingEntity = roleFonctionaliteRepository.findOne(dto.getId(), false);
			if (existingEntity == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("roleFonctionalite -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			// -----------------------------------------------------------------------
			// ----------- CHECK IF DATA IS USED
			// -----------------------------------------------------------------------



			existingEntity.setIsDeleted(true);
			items.add(existingEntity);
		}

		if (!items.isEmpty()) {
			// supprimer les donnees en base
			roleFonctionaliteRepository.saveAll((Iterable<RoleFonctionalite>) items);

			response.setHasError(false);
		}

		log.info("----end delete RoleFonctionalite-----");
		return response;
	}

	/**
	 * get RoleFonctionalite by using RoleFonctionaliteDto as object.
	 *
	 * @param request
	 * @return response
	 *
	 */
	@Override
	public Response<RoleFonctionaliteDto> getByCriteria(Request<RoleFonctionaliteDto> request, Locale locale)  throws Exception {
		log.info("----begin get RoleFonctionalite-----");

		Response<RoleFonctionaliteDto> response = new Response<RoleFonctionaliteDto>();
		List<RoleFonctionalite> items 			 = roleFonctionaliteRepository.getByCriteria(request, em, locale);

		if (items != null && !items.isEmpty()) {
			List<RoleFonctionaliteDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? RoleFonctionaliteTransformer.INSTANCE.toLiteDtos(items) : RoleFonctionaliteTransformer.INSTANCE.toDtos(items);

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
			response.setCount(roleFonctionaliteRepository.count(request, em, locale));
			response.setHasError(false);
		} else {
			response.setStatus(functionalError.DATA_EMPTY("roleFonctionalite", locale));
			response.setHasError(false);
			return response;
		}

		log.info("----end get RoleFonctionalite-----");
		return response;
	}

	/**
	 * get full RoleFonctionaliteDto by using RoleFonctionalite as object.
	 *
	 * @param dto
	 * @param size
	 * @param isSimpleLoading
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	private RoleFonctionaliteDto getFullInfos(RoleFonctionaliteDto dto, Integer size, Boolean isSimpleLoading, Locale locale) throws Exception {
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