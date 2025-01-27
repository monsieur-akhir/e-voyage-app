                                                            													
/*
 * Java business for entity table notifications 
 * Created on 2025-01-11 ( Time 04:46:00 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2018 Geo. All Rights Reserved.
 */

package tech.synelia.eVoyageBackend.business;

import lombok.extern.java.Log;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import tech.synelia.eVoyageBackend.utils.*;
import tech.synelia.eVoyageBackend.utils.dto.*;
import tech.synelia.eVoyageBackend.utils.enums.*;
import tech.synelia.eVoyageBackend.utils.contract.*;
import tech.synelia.eVoyageBackend.utils.contract.IBasicBusiness;
import tech.synelia.eVoyageBackend.utils.contract.Request;
import tech.synelia.eVoyageBackend.utils.contract.Response;
import tech.synelia.eVoyageBackend.utils.dto.transformer.*;
import tech.synelia.eVoyageBackend.dao.entity.Notifications;
import tech.synelia.eVoyageBackend.dao.entity.Users;
import tech.synelia.eVoyageBackend.dao.entity.*;
import tech.synelia.eVoyageBackend.dao.repository.*;

/**
BUSINESS for table "notifications"
 * 
 * @author Geo
 *
 */
@Log
@Component
public class NotificationsBusiness implements IBasicBusiness<Request<NotificationsDto>, Response<NotificationsDto>> {

	private Response<NotificationsDto> response;
	@Autowired
	private NotificationsRepository notificationsRepository;
	@Autowired
	private UsersRepository usersRepository;
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

	public NotificationsBusiness() {
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	}
	
	/**
	 * create Notifications by using NotificationsDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<NotificationsDto> create(Request<NotificationsDto> request, Locale locale)  throws ParseException {
		log.info("----begin create Notifications-----");

		Response<NotificationsDto> response = new Response<NotificationsDto>();
		List<Notifications>        items    = new ArrayList<Notifications>();
			
		for (NotificationsDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("userId", dto.getUserId());
			fieldsToVerify.put("type", dto.getType());
			fieldsToVerify.put("content", dto.getContent());
			fieldsToVerify.put("status", dto.getStatus());
			fieldsToVerify.put("deletedAt", dto.getDeletedAt());
			fieldsToVerify.put("deletedBy", dto.getDeletedBy());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verify if notifications to insert do not exist
			Notifications existingEntity = null;
			if (existingEntity != null) {
				response.setStatus(functionalError.DATA_EXIST("notifications id -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			// Verify if users exist
			Users existingUsers = null;
			if (dto.getUserId() != null && dto.getUserId() > 0){
				existingUsers = usersRepository.findOne(dto.getUserId(), false);
				if (existingUsers == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("users userId -> " + dto.getUserId(), locale));
					response.setHasError(true);
					return response;
				}
			}
				Notifications entityToSave = null;
			entityToSave = NotificationsTransformer.INSTANCE.toEntity(dto, existingUsers);
			entityToSave.setCreatedAt(Utilities.getCurrentDate());
			entityToSave.setCreatedBy(request.getUser());
			entityToSave.setIsDeleted(false);
			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<Notifications> itemsSaved = null;
			// inserer les donnees en base de donnees
			itemsSaved = notificationsRepository.saveAll((Iterable<Notifications>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("notifications", locale));
				response.setHasError(true);
				return response;
			}
			List<NotificationsDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? NotificationsTransformer.INSTANCE.toLiteDtos(itemsSaved) : NotificationsTransformer.INSTANCE.toDtos(itemsSaved);

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

		log.info("----end create Notifications-----");
		return response;
	}

	/**
	 * update Notifications by using NotificationsDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<NotificationsDto> update(Request<NotificationsDto> request, Locale locale)  throws ParseException {
		log.info("----begin update Notifications-----");

		Response<NotificationsDto> response = new Response<NotificationsDto>();
		List<Notifications>        items    = new ArrayList<Notifications>();
			
		for (NotificationsDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la notifications existe
			Notifications entityToSave = null;
			entityToSave = notificationsRepository.findOne(dto.getId(), false);
			if (entityToSave == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("notifications id -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			// Verify if users exist
			if (dto.getUserId() != null && dto.getUserId() > 0){
				Users existingUsers = usersRepository.findOne(dto.getUserId(), false);
				if (existingUsers == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("users userId -> " + dto.getUserId(), locale));
					response.setHasError(true);
					return response;
				}
				entityToSave.setUsers(existingUsers);
			}
			if (Utilities.notBlank(dto.getType())) {
				entityToSave.setType(dto.getType());
			}
			if (Utilities.notBlank(dto.getContent())) {
				entityToSave.setContent(dto.getContent());
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
			List<Notifications> itemsSaved = null;
			// maj les donnees en base
			itemsSaved = notificationsRepository.saveAll((Iterable<Notifications>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("notifications", locale));
				response.setHasError(true);
				return response;
			}
			List<NotificationsDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? NotificationsTransformer.INSTANCE.toLiteDtos(itemsSaved) : NotificationsTransformer.INSTANCE.toDtos(itemsSaved);

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

		log.info("----end update Notifications-----");
		return response;
	}

	/**
	 * delete Notifications by using NotificationsDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<NotificationsDto> delete(Request<NotificationsDto> request, Locale locale)  {
		log.info("----begin delete Notifications-----");

		Response<NotificationsDto> response = new Response<NotificationsDto>();
		List<Notifications>        items    = new ArrayList<Notifications>();
			
		for (NotificationsDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la notifications existe
			Notifications existingEntity = null;
			existingEntity = notificationsRepository.findOne(dto.getId(), false);
			if (existingEntity == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("notifications -> " + dto.getId(), locale));
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
			notificationsRepository.saveAll((Iterable<Notifications>) items);

			response.setHasError(false);
		}

		log.info("----end delete Notifications-----");
		return response;
	}

	/**
	 * get Notifications by using NotificationsDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<NotificationsDto> getByCriteria(Request<NotificationsDto> request, Locale locale)  throws Exception {
		log.info("----begin get Notifications-----");

		Response<NotificationsDto> response = new Response<NotificationsDto>();
		List<Notifications> items 			 = notificationsRepository.getByCriteria(request, em, locale);

		if (items != null && !items.isEmpty()) {
			List<NotificationsDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? NotificationsTransformer.INSTANCE.toLiteDtos(items) : NotificationsTransformer.INSTANCE.toDtos(items);

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
			response.setCount(notificationsRepository.count(request, em, locale));
			response.setHasError(false);
		} else {
			response.setStatus(functionalError.DATA_EMPTY("notifications", locale));
			response.setHasError(false);
			return response;
		}

		log.info("----end get Notifications-----");
		return response;
	}

	/**
	 * get full NotificationsDto by using Notifications as object.
	 * 
	 * @param dto
	 * @param size
	 * @param isSimpleLoading
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	private NotificationsDto getFullInfos(NotificationsDto dto, Integer size, Boolean isSimpleLoading, Locale locale) throws Exception {
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
