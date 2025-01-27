                                        								
/*
 * Java business for entity table role_permissions 
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
import tech.dev.eVoyageBackend.dao.entity.RolePermissions;
import tech.dev.eVoyageBackend.dao.entity.Roles;
import tech.dev.eVoyageBackend.dao.entity.Permissions;
import tech.dev.eVoyageBackend.dao.entity.*;
import tech.dev.eVoyageBackend.dao.repository.*;

/**
BUSINESS for table "role_permissions"
 * 
 * @author Geo
 *
 */
@Log
@Component
public class RolePermissionsBusiness implements IBasicBusiness<Request<RolePermissionsDto>, Response<RolePermissionsDto>> {

	private Response<RolePermissionsDto> response;
	@Autowired
	private RolePermissionsRepository rolePermissionsRepository;
	@Autowired
	private RolesRepository rolesRepository;
	@Autowired
	private PermissionsRepository permissionsRepository;
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

	public RolePermissionsBusiness() {
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	}
	
	/**
	 * create RolePermissions by using RolePermissionsDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<RolePermissionsDto> create(Request<RolePermissionsDto> request, Locale locale)  throws ParseException {
		log.info("----begin create RolePermissions-----");

		Response<RolePermissionsDto> response = new Response<RolePermissionsDto>();
		List<RolePermissions>        items    = new ArrayList<RolePermissions>();
			
		for (RolePermissionsDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, Object> fieldsToVerify = new HashMap<String, Object>();
			fieldsToVerify.put("roleId", dto.getRoleId());
			fieldsToVerify.put("permissionId", dto.getPermissionId());
//			fieldsToVerify.put("status", dto.getStatus());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verify if rolePermissions to insert do not exist
			RolePermissions existingEntity = null;
			if (existingEntity != null) {
				response.setStatus(functionalError.DATA_EXIST("rolePermissions id -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			// Verify if roles exist
			Roles existingRoles = null;
			if (dto.getRoleId() != null && dto.getRoleId() > 0){
				existingRoles = rolesRepository.findOne(dto.getRoleId(), false);
				if (existingRoles == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("roles roleId -> " + dto.getRoleId(), locale));
					response.setHasError(true);
					return response;
				}
			}
			// Verify if permissions exist
			Permissions existingPermissions = null;
			if (dto.getPermissionId() != null && dto.getPermissionId() > 0){
				existingPermissions = permissionsRepository.findOne(dto.getPermissionId());
				if (existingPermissions == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("permissions permissionId -> " + dto.getPermissionId(), locale));
					response.setHasError(true);
					return response;
				}
			}
				RolePermissions entityToSave = null;
			entityToSave = RolePermissionsTransformer.INSTANCE.toEntity(dto, existingRoles, existingPermissions);
			entityToSave.setCreatedAt(Utilities.getCurrentDate());
			entityToSave.setCreatedBy(request.getUser());
			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<RolePermissions> itemsSaved = null;
			// inserer les donnees en base de donnees
			itemsSaved = rolePermissionsRepository.saveAll((Iterable<RolePermissions>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("rolePermissions", locale));
				response.setHasError(true);
				return response;
			}
			List<RolePermissionsDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? RolePermissionsTransformer.INSTANCE.toLiteDtos(itemsSaved) : RolePermissionsTransformer.INSTANCE.toDtos(itemsSaved);

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

		log.info("----end create RolePermissions-----");
		return response;
	}

	/**
	 * update RolePermissions by using RolePermissionsDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<RolePermissionsDto> update(Request<RolePermissionsDto> request, Locale locale)  throws ParseException {
		log.info("----begin update RolePermissions-----");

		Response<RolePermissionsDto> response = new Response<RolePermissionsDto>();
		List<RolePermissions>        items    = new ArrayList<RolePermissions>();
			
		for (RolePermissionsDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, Object> fieldsToVerify = new HashMap<String, Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la rolePermissions existe
			RolePermissions entityToSave = null;
			entityToSave = rolePermissionsRepository.findOne(dto.getId());
			if (entityToSave == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("rolePermissions id -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			// Verify if roles exist
			if (dto.getRoleId() != null && dto.getRoleId() > 0){
				Roles existingRoles = rolesRepository.findOne(dto.getRoleId(), false);
				if (existingRoles == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("roles roleId -> " + dto.getRoleId(), locale));
					response.setHasError(true);
					return response;
				}
				entityToSave.setRoles(existingRoles);
			}
			// Verify if permissions exist
			if (dto.getPermissionId() != null && dto.getPermissionId() > 0){
				Permissions existingPermissions = permissionsRepository.findOne(dto.getPermissionId());
				if (existingPermissions == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("permissions permissionId -> " + dto.getPermissionId(), locale));
					response.setHasError(true);
					return response;
				}
				entityToSave.setPermissions(existingPermissions);
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
			List<RolePermissions> itemsSaved = null;
			// maj les donnees en base
			itemsSaved = rolePermissionsRepository.saveAll((Iterable<RolePermissions>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("rolePermissions", locale));
				response.setHasError(true);
				return response;
			}
			List<RolePermissionsDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? RolePermissionsTransformer.INSTANCE.toLiteDtos(itemsSaved) : RolePermissionsTransformer.INSTANCE.toDtos(itemsSaved);

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

		log.info("----end update RolePermissions-----");
		return response;
	}

	/**
	 * delete RolePermissions by using RolePermissionsDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<RolePermissionsDto> delete(Request<RolePermissionsDto> request, Locale locale)  {
		log.info("----begin delete RolePermissions-----");

		Response<RolePermissionsDto> response = new Response<RolePermissionsDto>();
		List<RolePermissions>        items    = new ArrayList<RolePermissions>();
			
		for (RolePermissionsDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, Object> fieldsToVerify = new HashMap<String, Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la rolePermissions existe
			RolePermissions existingEntity = null;
			existingEntity = rolePermissionsRepository.findOne(dto.getId());
			if (existingEntity == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("rolePermissions -> " + dto.getId(), locale));
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
			rolePermissionsRepository.deleteAll((Iterable<RolePermissions>) items);

			response.setHasError(false);
		}

		log.info("----end delete RolePermissions-----");
		return response;
	}

	/**
	 * get RolePermissions by using RolePermissionsDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<RolePermissionsDto> getByCriteria(Request<RolePermissionsDto> request, Locale locale)  throws Exception {
		log.info("----begin get RolePermissions-----");

		Response<RolePermissionsDto> response = new Response<RolePermissionsDto>();
		List<RolePermissions> items 			 = rolePermissionsRepository.getByCriteria(request, em, locale);

		if (items != null && !items.isEmpty()) {
			List<RolePermissionsDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? RolePermissionsTransformer.INSTANCE.toLiteDtos(items) : RolePermissionsTransformer.INSTANCE.toDtos(items);

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
			response.setCount(rolePermissionsRepository.count(request, em, locale));
			response.setHasError(false);
		} else {
			response.setStatus(functionalError.DATA_EMPTY("rolePermissions", locale));
			response.setHasError(false);
			return response;
		}

		log.info("----end get RolePermissions-----");
		return response;
	}

	/**
	 * get full RolePermissionsDto by using RolePermissions as object.
	 * 
	 * @param dto
	 * @param size
	 * @param isSimpleLoading
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	private RolePermissionsDto getFullInfos(RolePermissionsDto dto, Integer size, Boolean isSimpleLoading, Locale locale) throws Exception {
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
