                                                    											
/*
 * Java business for entity table roles 
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
import tech.dev.eVoyageBackend.dao.entity.Roles;
import tech.dev.eVoyageBackend.dao.entity.*;
import tech.dev.eVoyageBackend.dao.repository.*;

/**
BUSINESS for table "roles"
 * 
 * @author Geo
 *
 */
@Log
@Component
public class RolesBusiness implements IBasicBusiness<Request<RolesDto>, Response<RolesDto>> {

	private Response<RolesDto> response;
	@Autowired
	private RolesRepository rolesRepository;
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

	public RolesBusiness() {
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	}
	
	/**
	 * create Roles by using RolesDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<RolesDto> create(Request<RolesDto> request, Locale locale)  throws ParseException {
		log.info("----begin create Roles-----");

		Response<RolesDto> response = new Response<RolesDto>();
		List<Roles>        items    = new ArrayList<Roles>();
			
		for (RolesDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("name", dto.getName());
			fieldsToVerify.put("status", dto.getStatus());
			fieldsToVerify.put("deletedAt", dto.getDeletedAt());
			fieldsToVerify.put("deletedBy", dto.getDeletedBy());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verify if roles to insert do not exist
			Roles existingEntity = null;
			if (existingEntity != null) {
				response.setStatus(functionalError.DATA_EXIST("roles id -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			// verif unique name in db
			existingEntity = rolesRepository.findByName(dto.getName(), false);
			if (existingEntity != null) {
				response.setStatus(functionalError.DATA_EXIST("roles name -> " + dto.getName(), locale));
				response.setHasError(true);
				return response;
			}
			// verif unique name in items to save
			if (items.stream().anyMatch(a -> a.getName().equalsIgnoreCase(dto.getName()))) {
				response.setStatus(functionalError.DATA_DUPLICATE(" name ", locale));
				response.setHasError(true);
				return response;
			}

				Roles entityToSave = null;
			entityToSave = RolesTransformer.INSTANCE.toEntity(dto);
			entityToSave.setCreatedAt(Utilities.getCurrentDate());
			entityToSave.setCreatedBy(request.getUser());
			entityToSave.setIsDeleted(false);
			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<Roles> itemsSaved = null;
			// inserer les donnees en base de donnees
			itemsSaved = rolesRepository.saveAll((Iterable<Roles>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("roles", locale));
				response.setHasError(true);
				return response;
			}
			List<RolesDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? RolesTransformer.INSTANCE.toLiteDtos(itemsSaved) : RolesTransformer.INSTANCE.toDtos(itemsSaved);

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

		log.info("----end create Roles-----");
		return response;
	}

	/**
	 * update Roles by using RolesDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<RolesDto> update(Request<RolesDto> request, Locale locale)  throws ParseException {
		log.info("----begin update Roles-----");

		Response<RolesDto> response = new Response<RolesDto>();
		List<Roles>        items    = new ArrayList<Roles>();
			
		for (RolesDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la roles existe
			Roles entityToSave = null;
			entityToSave = rolesRepository.findOne(dto.getId(), false);
			if (entityToSave == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("roles id -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			if (Utilities.notBlank(dto.getName())) {
				entityToSave.setName(dto.getName());
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
			List<Roles> itemsSaved = null;
			// maj les donnees en base
			itemsSaved = rolesRepository.saveAll((Iterable<Roles>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("roles", locale));
				response.setHasError(true);
				return response;
			}
			List<RolesDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? RolesTransformer.INSTANCE.toLiteDtos(itemsSaved) : RolesTransformer.INSTANCE.toDtos(itemsSaved);

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

		log.info("----end update Roles-----");
		return response;
	}

	/**
	 * delete Roles by using RolesDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<RolesDto> delete(Request<RolesDto> request, Locale locale)  {
		log.info("----begin delete Roles-----");

		Response<RolesDto> response = new Response<RolesDto>();
		List<Roles>        items    = new ArrayList<Roles>();
			
		for (RolesDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la roles existe
			Roles existingEntity = null;
			existingEntity = rolesRepository.findOne(dto.getId(), false);
			if (existingEntity == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("roles -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			// -----------------------------------------------------------------------
			// ----------- CHECK IF DATA IS USED
			// -----------------------------------------------------------------------

			// rolePermissions
			List<RolePermissions> listOfRolePermissions = rolePermissionsRepository.findByRoleId(existingEntity.getId());
			if (listOfRolePermissions != null && !listOfRolePermissions.isEmpty()){
				response.setStatus(functionalError.DATA_NOT_DELETABLE("(" + listOfRolePermissions.size() + ")", locale));
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
			rolesRepository.saveAll((Iterable<Roles>) items);

			response.setHasError(false);
		}

		log.info("----end delete Roles-----");
		return response;
	}

	/**
	 * get Roles by using RolesDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<RolesDto> getByCriteria(Request<RolesDto> request, Locale locale)  throws Exception {
		log.info("----begin get Roles-----");

		Response<RolesDto> response = new Response<RolesDto>();
		List<Roles> items 			 = rolesRepository.getByCriteria(request, em, locale);

		if (items != null && !items.isEmpty()) {
			List<RolesDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? RolesTransformer.INSTANCE.toLiteDtos(items) : RolesTransformer.INSTANCE.toDtos(items);

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
			response.setCount(rolesRepository.count(request, em, locale));
			response.setHasError(false);
		} else {
			response.setStatus(functionalError.DATA_EMPTY("roles", locale));
			response.setHasError(false);
			return response;
		}

		log.info("----end get Roles-----");
		return response;
	}

	/**
	 * get full RolesDto by using Roles as object.
	 * 
	 * @param dto
	 * @param size
	 * @param isSimpleLoading
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	private RolesDto getFullInfos(RolesDto dto, Integer size, Boolean isSimpleLoading, Locale locale) throws Exception {
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
