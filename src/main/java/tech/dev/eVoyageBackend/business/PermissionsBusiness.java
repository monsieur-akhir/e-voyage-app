                                    							
/*
 * Java business for entity table permissions 
 * Created on 2025-01-12 ( Time 17:39:56 )
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
import tech.dev.eVoyageBackend.dao.entity.Permissions;
import tech.dev.eVoyageBackend.dao.entity.*;
import tech.dev.eVoyageBackend.dao.repository.*;

/**
BUSINESS for table "permissions"
 * 
 * @author Geo
 *
 */
@Log
@Component
public class PermissionsBusiness implements IBasicBusiness<Request<PermissionsDto>, Response<PermissionsDto>> {

	private Response<PermissionsDto> response;
	@Autowired
	private PermissionsRepository permissionsRepository;
	@Autowired
	private RolePermissionsRepository rolePermissionsRepository;
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

	public PermissionsBusiness() {
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	}
	
	/**
	 * create Permissions by using PermissionsDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<PermissionsDto> create(Request<PermissionsDto> request, Locale locale)  throws ParseException {
		log.info("----begin create Permissions-----");

		Response<PermissionsDto> response = new Response<PermissionsDto>();
		List<Permissions>        items    = new ArrayList<Permissions>();
			
		for (PermissionsDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, Object> fieldsToVerify = new HashMap<String, Object>();
			fieldsToVerify.put("name", dto.getName());
			fieldsToVerify.put("description", dto.getDescription());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verify if permissions to insert do not exist
			Permissions existingEntity = null;
			if (existingEntity != null) {
				response.setStatus(functionalError.DATA_EXIST("permissions id -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			// verif unique name in db
			existingEntity = permissionsRepository.findByName(dto.getName());
			if (existingEntity != null) {
				response.setStatus(functionalError.DATA_EXIST("permissions name -> " + dto.getName(), locale));
				response.setHasError(true);
				return response;
			}
			// verif unique name in items to save
			if (items.stream().anyMatch(a -> a.getName().equalsIgnoreCase(dto.getName()))) {
				response.setStatus(functionalError.DATA_DUPLICATE(" name ", locale));
				response.setHasError(true);
				return response;
			}

				Permissions entityToSave = null;
			entityToSave = PermissionsTransformer.INSTANCE.toEntity(dto);
			entityToSave.setCreatedAt(Utilities.getCurrentDate());
			entityToSave.setCreatedBy(request.getUser());
			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<Permissions> itemsSaved = null;
			// inserer les donnees en base de donnees
			itemsSaved = permissionsRepository.saveAll((Iterable<Permissions>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("permissions", locale));
				response.setHasError(true);
				return response;
			}
			List<PermissionsDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? PermissionsTransformer.INSTANCE.toLiteDtos(itemsSaved) : PermissionsTransformer.INSTANCE.toDtos(itemsSaved);

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

		log.info("----end create Permissions-----");
		return response;
	}

	/**
	 * update Permissions by using PermissionsDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<PermissionsDto> update(Request<PermissionsDto> request, Locale locale)  throws ParseException {
		log.info("----begin update Permissions-----");

		Response<PermissionsDto> response = new Response<PermissionsDto>();
		List<Permissions>        items    = new ArrayList<Permissions>();
			
		for (PermissionsDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, Object> fieldsToVerify = new HashMap<String, Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la permissions existe
			Permissions entityToSave = null;
			entityToSave = permissionsRepository.findOne(dto.getId());
			if (entityToSave == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("permissions id -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			if (Utilities.notBlank(dto.getName())) {
				entityToSave.setName(dto.getName());
			}
			if (Utilities.notBlank(dto.getDescription())) {
				entityToSave.setDescription(dto.getDescription());
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
			List<Permissions> itemsSaved = null;
			// maj les donnees en base
			itemsSaved = permissionsRepository.saveAll((Iterable<Permissions>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("permissions", locale));
				response.setHasError(true);
				return response;
			}
			List<PermissionsDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? PermissionsTransformer.INSTANCE.toLiteDtos(itemsSaved) : PermissionsTransformer.INSTANCE.toDtos(itemsSaved);

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

		log.info("----end update Permissions-----");
		return response;
	}

	/**
	 * delete Permissions by using PermissionsDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<PermissionsDto> delete(Request<PermissionsDto> request, Locale locale)  {
		log.info("----begin delete Permissions-----");

		Response<PermissionsDto> response = new Response<PermissionsDto>();
		List<Permissions>        items    = new ArrayList<Permissions>();
			
		for (PermissionsDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, Object> fieldsToVerify = new HashMap<String, Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la permissions existe
			Permissions existingEntity = null;
			existingEntity = permissionsRepository.findOne(dto.getId());
			if (existingEntity == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("permissions -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			// -----------------------------------------------------------------------
			// ----------- CHECK IF DATA IS USED
			// -----------------------------------------------------------------------

			// rolePermissions
			List<RolePermissions> listOfRolePermissions = rolePermissionsRepository.findByPermissionId(existingEntity.getId());
			if (listOfRolePermissions != null && !listOfRolePermissions.isEmpty()){
				response.setStatus(functionalError.DATA_NOT_DELETABLE("(" + listOfRolePermissions.size() + ")", locale));
				response.setHasError(true);
				return response;
			}


			items.add(existingEntity);
		}

		if (!items.isEmpty()) {
			// supprimer les donnees en base
			permissionsRepository.deleteAll((Iterable<Permissions>) items);

			response.setHasError(false);
		}

		log.info("----end delete Permissions-----");
		return response;
	}

	/**
	 * get Permissions by using PermissionsDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<PermissionsDto> getByCriteria(Request<PermissionsDto> request, Locale locale)  throws Exception {
		log.info("----begin get Permissions-----");

		Response<PermissionsDto> response = new Response<PermissionsDto>();
		List<Permissions> items 			 = permissionsRepository.getByCriteria(request, em, locale);

		if (items != null && !items.isEmpty()) {
			List<PermissionsDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? PermissionsTransformer.INSTANCE.toLiteDtos(items) : PermissionsTransformer.INSTANCE.toDtos(items);

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
			response.setCount(permissionsRepository.count(request, em, locale));
			response.setHasError(false);
		} else {
			response.setStatus(functionalError.DATA_EMPTY("permissions", locale));
			response.setHasError(false);
			return response;
		}

		log.info("----end get Permissions-----");
		return response;
	}

	/**
	 * get full PermissionsDto by using Permissions as object.
	 * 
	 * @param dto
	 * @param size
	 * @param isSimpleLoading
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	private PermissionsDto getFullInfos(PermissionsDto dto, Integer size, Boolean isSimpleLoading, Locale locale) throws Exception {
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
