                                                                        																
/*
 * Java business for entity table users 
 * Created on 2025-01-12 ( Time 17:40:01 )
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
import tech.dev.eVoyageBackend.dao.entity.Users;
import tech.dev.eVoyageBackend.dao.entity.Companies;
import tech.dev.eVoyageBackend.dao.entity.*;
import tech.dev.eVoyageBackend.dao.repository.*;

/**
BUSINESS for table "users"
 * 
 * @author Geo
 *
 */
@Log
@Component
public class UsersBusiness implements IBasicBusiness<Request<UsersDto>, Response<UsersDto>> {

	private Response<UsersDto> response;
	@Autowired
	private UsersRepository usersRepository;
	@Autowired
	private CompaniesRepository companiesRepository;
	@Autowired
	private TicketsRepository ticketsRepository;
	@Autowired
	private BookingsRepository bookingsRepository;
	@Autowired
	private NotificationsRepository notificationsRepository;
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

	public UsersBusiness() {
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	}
	
	/**
	 * create Users by using UsersDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<UsersDto> create(Request<UsersDto> request, Locale locale)  throws ParseException {
		log.info("----begin create Users-----");

		Response<UsersDto> response = new Response<UsersDto>();
		List<Users>        items    = new ArrayList<Users>();
			
		for (UsersDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("companyId", dto.getCompanyId());
			fieldsToVerify.put("name", dto.getName());
			fieldsToVerify.put("email", dto.getEmail());
			fieldsToVerify.put("phone", dto.getPhone());
			fieldsToVerify.put("password", dto.getPassword());
			fieldsToVerify.put("roleId", dto.getRoleId());
			fieldsToVerify.put("status", dto.getStatus());
			fieldsToVerify.put("deletedAt", dto.getDeletedAt());
			fieldsToVerify.put("deletedBy", dto.getDeletedBy());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verify if users to insert do not exist
			Users existingEntity = null;
			if (existingEntity != null) {
				response.setStatus(functionalError.DATA_EXIST("users id -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			// verif unique name in db
			existingEntity = usersRepository.findByName(dto.getName(), false);
			if (existingEntity != null) {
				response.setStatus(functionalError.DATA_EXIST("users name -> " + dto.getName(), locale));
				response.setHasError(true);
				return response;
			}
			// verif unique name in items to save
			if (items.stream().anyMatch(a -> a.getName().equalsIgnoreCase(dto.getName()))) {
				response.setStatus(functionalError.DATA_DUPLICATE(" name ", locale));
				response.setHasError(true);
				return response;
			}

			// verif unique email in db
			existingEntity = usersRepository.findByEmail(dto.getEmail(), false);
			if (existingEntity != null) {
				response.setStatus(functionalError.DATA_EXIST("users email -> " + dto.getEmail(), locale));
				response.setHasError(true);
				return response;
			}
			// verif unique email in items to save
			if (items.stream().anyMatch(a -> a.getEmail().equalsIgnoreCase(dto.getEmail()))) {
				response.setStatus(functionalError.DATA_DUPLICATE(" email ", locale));
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
				Users entityToSave = null;
			entityToSave = UsersTransformer.INSTANCE.toEntity(dto, existingCompanies);
			entityToSave.setCreatedAt(Utilities.getCurrentDate());
			entityToSave.setCreatedBy(request.getUser());
			entityToSave.setIsDeleted(false);
			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<Users> itemsSaved = null;
			// inserer les donnees en base de donnees
			itemsSaved = usersRepository.saveAll((Iterable<Users>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("users", locale));
				response.setHasError(true);
				return response;
			}
			List<UsersDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? UsersTransformer.INSTANCE.toLiteDtos(itemsSaved) : UsersTransformer.INSTANCE.toDtos(itemsSaved);

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

		log.info("----end create Users-----");
		return response;
	}

	/**
	 * update Users by using UsersDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<UsersDto> update(Request<UsersDto> request, Locale locale)  throws ParseException {
		log.info("----begin update Users-----");

		Response<UsersDto> response = new Response<UsersDto>();
		List<Users>        items    = new ArrayList<Users>();
			
		for (UsersDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la users existe
			Users entityToSave = null;
			entityToSave = usersRepository.findOne(dto.getId(), false);
			if (entityToSave == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("users id -> " + dto.getId(), locale));
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
			if (Utilities.notBlank(dto.getName())) {
				entityToSave.setName(dto.getName());
			}
			if (Utilities.notBlank(dto.getEmail())) {
				entityToSave.setEmail(dto.getEmail());
			}
			if (Utilities.notBlank(dto.getPhone())) {
				entityToSave.setPhone(dto.getPhone());
			}
			if (Utilities.notBlank(dto.getPassword())) {
				entityToSave.setPassword(dto.getPassword());
			}
			if (dto.getRoleId() != null && dto.getRoleId() > 0) {
				entityToSave.setRoleId(dto.getRoleId());
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
			List<Users> itemsSaved = null;
			// maj les donnees en base
			itemsSaved = usersRepository.saveAll((Iterable<Users>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("users", locale));
				response.setHasError(true);
				return response;
			}
			List<UsersDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? UsersTransformer.INSTANCE.toLiteDtos(itemsSaved) : UsersTransformer.INSTANCE.toDtos(itemsSaved);

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

		log.info("----end update Users-----");
		return response;
	}

	/**
	 * delete Users by using UsersDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<UsersDto> delete(Request<UsersDto> request, Locale locale)  {
		log.info("----begin delete Users-----");

		Response<UsersDto> response = new Response<UsersDto>();
		List<Users>        items    = new ArrayList<Users>();
			
		for (UsersDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la users existe
			Users existingEntity = null;
			existingEntity = usersRepository.findOne(dto.getId(), false);
			if (existingEntity == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("users -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			// -----------------------------------------------------------------------
			// ----------- CHECK IF DATA IS USED
			// -----------------------------------------------------------------------

			// tickets
			List<Tickets> listOfTickets = ticketsRepository.findByScannedBy(existingEntity.getId(), false);
			if (listOfTickets != null && !listOfTickets.isEmpty()){
				response.setStatus(functionalError.DATA_NOT_DELETABLE("(" + listOfTickets.size() + ")", locale));
				response.setHasError(true);
				return response;
			}
			// bookings
			List<Bookings> listOfBookings = bookingsRepository.findByUserId(existingEntity.getId(), false);
			if (listOfBookings != null && !listOfBookings.isEmpty()){
				response.setStatus(functionalError.DATA_NOT_DELETABLE("(" + listOfBookings.size() + ")", locale));
				response.setHasError(true);
				return response;
			}
			// notifications
			List<Notifications> listOfNotifications = notificationsRepository.findByUserId(existingEntity.getId(), false);
			if (listOfNotifications != null && !listOfNotifications.isEmpty()){
				response.setStatus(functionalError.DATA_NOT_DELETABLE("(" + listOfNotifications.size() + ")", locale));
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
			usersRepository.saveAll((Iterable<Users>) items);

			response.setHasError(false);
		}

		log.info("----end delete Users-----");
		return response;
	}

	/**
	 * get Users by using UsersDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<UsersDto> getByCriteria(Request<UsersDto> request, Locale locale)  throws Exception {
		log.info("----begin get Users-----");

		Response<UsersDto> response = new Response<UsersDto>();
		List<Users> items 			 = usersRepository.getByCriteria(request, em, locale);

		if (items != null && !items.isEmpty()) {
			List<UsersDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? UsersTransformer.INSTANCE.toLiteDtos(items) : UsersTransformer.INSTANCE.toDtos(items);

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
			response.setCount(usersRepository.count(request, em, locale));
			response.setHasError(false);
		} else {
			response.setStatus(functionalError.DATA_EMPTY("users", locale));
			response.setHasError(false);
			return response;
		}

		log.info("----end get Users-----");
		return response;
	}

	/**
	 * get full UsersDto by using Users as object.
	 * 
	 * @param dto
	 * @param size
	 * @param isSimpleLoading
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	private UsersDto getFullInfos(UsersDto dto, Integer size, Boolean isSimpleLoading, Locale locale) throws Exception {
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
