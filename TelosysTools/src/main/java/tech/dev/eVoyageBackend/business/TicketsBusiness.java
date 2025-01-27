                                                                														
/*
 * Java business for entity table tickets 
 * Created on 2025-01-12 ( Time 17:39:58 )
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
import tech.dev.eVoyageBackend.dao.entity.Tickets;
import tech.dev.eVoyageBackend.dao.entity.Companies;
import tech.dev.eVoyageBackend.dao.entity.Bookings;
import tech.dev.eVoyageBackend.dao.entity.Users;
import tech.dev.eVoyageBackend.dao.entity.*;
import tech.dev.eVoyageBackend.dao.repository.*;

/**
BUSINESS for table "tickets"
 * 
 * @author Geo
 *
 */
@Log
@Component
public class TicketsBusiness implements IBasicBusiness<Request<TicketsDto>, Response<TicketsDto>> {

	private Response<TicketsDto> response;
	@Autowired
	private TicketsRepository ticketsRepository;
	@Autowired
	private CompaniesRepository companiesRepository;
	@Autowired
	private BookingsRepository bookingsRepository;
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

	public TicketsBusiness() {
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	}
	
	/**
	 * create Tickets by using TicketsDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<TicketsDto> create(Request<TicketsDto> request, Locale locale)  throws ParseException {
		log.info("----begin create Tickets-----");

		Response<TicketsDto> response = new Response<TicketsDto>();
		List<Tickets>        items    = new ArrayList<Tickets>();
			
		for (TicketsDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("bookingId", dto.getBookingId());
			fieldsToVerify.put("companyId", dto.getCompanyId());
			fieldsToVerify.put("qrCode", dto.getQrCode());
			fieldsToVerify.put("status", dto.getStatus());
			fieldsToVerify.put("scannedBy", dto.getScannedBy());
			fieldsToVerify.put("deletedAt", dto.getDeletedAt());
			fieldsToVerify.put("deletedBy", dto.getDeletedBy());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verify if tickets to insert do not exist
			Tickets existingEntity = null;
			if (existingEntity != null) {
				response.setStatus(functionalError.DATA_EXIST("tickets id -> " + dto.getId(), locale));
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
			// Verify if bookings exist
			Bookings existingBookings = null;
			if (dto.getBookingId() != null && dto.getBookingId() > 0){
				existingBookings = bookingsRepository.findOne(dto.getBookingId(), false);
				if (existingBookings == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("bookings bookingId -> " + dto.getBookingId(), locale));
					response.setHasError(true);
					return response;
				}
			}
			// Verify if users exist
			Users existingUsers = null;
			if (dto.getScannedBy() != null && dto.getScannedBy() > 0){
				existingUsers = usersRepository.findOne(dto.getScannedBy(), false);
				if (existingUsers == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("users scannedBy -> " + dto.getScannedBy(), locale));
					response.setHasError(true);
					return response;
				}
			}
				Tickets entityToSave = null;
			entityToSave = TicketsTransformer.INSTANCE.toEntity(dto, existingCompanies, existingBookings, existingUsers);
			entityToSave.setCreatedAt(Utilities.getCurrentDate());
			entityToSave.setCreatedBy(request.getUser());
			entityToSave.setIsDeleted(false);
			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<Tickets> itemsSaved = null;
			// inserer les donnees en base de donnees
			itemsSaved = ticketsRepository.saveAll((Iterable<Tickets>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("tickets", locale));
				response.setHasError(true);
				return response;
			}
			List<TicketsDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? TicketsTransformer.INSTANCE.toLiteDtos(itemsSaved) : TicketsTransformer.INSTANCE.toDtos(itemsSaved);

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

		log.info("----end create Tickets-----");
		return response;
	}

	/**
	 * update Tickets by using TicketsDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<TicketsDto> update(Request<TicketsDto> request, Locale locale)  throws ParseException {
		log.info("----begin update Tickets-----");

		Response<TicketsDto> response = new Response<TicketsDto>();
		List<Tickets>        items    = new ArrayList<Tickets>();
			
		for (TicketsDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la tickets existe
			Tickets entityToSave = null;
			entityToSave = ticketsRepository.findOne(dto.getId(), false);
			if (entityToSave == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("tickets id -> " + dto.getId(), locale));
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
			// Verify if bookings exist
			if (dto.getBookingId() != null && dto.getBookingId() > 0){
				Bookings existingBookings = bookingsRepository.findOne(dto.getBookingId(), false);
				if (existingBookings == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("bookings bookingId -> " + dto.getBookingId(), locale));
					response.setHasError(true);
					return response;
				}
				entityToSave.setBookings(existingBookings);
			}
			// Verify if users exist
			if (dto.getScannedBy() != null && dto.getScannedBy() > 0){
				Users existingUsers = usersRepository.findOne(dto.getScannedBy(), false);
				if (existingUsers == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("users scannedBy -> " + dto.getScannedBy(), locale));
					response.setHasError(true);
					return response;
				}
				entityToSave.setUsers(existingUsers);
			}
			if (Utilities.notBlank(dto.getQrCode())) {
				entityToSave.setQrCode(dto.getQrCode());
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
			List<Tickets> itemsSaved = null;
			// maj les donnees en base
			itemsSaved = ticketsRepository.saveAll((Iterable<Tickets>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("tickets", locale));
				response.setHasError(true);
				return response;
			}
			List<TicketsDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? TicketsTransformer.INSTANCE.toLiteDtos(itemsSaved) : TicketsTransformer.INSTANCE.toDtos(itemsSaved);

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

		log.info("----end update Tickets-----");
		return response;
	}

	/**
	 * delete Tickets by using TicketsDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<TicketsDto> delete(Request<TicketsDto> request, Locale locale)  {
		log.info("----begin delete Tickets-----");

		Response<TicketsDto> response = new Response<TicketsDto>();
		List<Tickets>        items    = new ArrayList<Tickets>();
			
		for (TicketsDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la tickets existe
			Tickets existingEntity = null;
			existingEntity = ticketsRepository.findOne(dto.getId(), false);
			if (existingEntity == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("tickets -> " + dto.getId(), locale));
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
			ticketsRepository.saveAll((Iterable<Tickets>) items);

			response.setHasError(false);
		}

		log.info("----end delete Tickets-----");
		return response;
	}

	/**
	 * get Tickets by using TicketsDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<TicketsDto> getByCriteria(Request<TicketsDto> request, Locale locale)  throws Exception {
		log.info("----begin get Tickets-----");

		Response<TicketsDto> response = new Response<TicketsDto>();
		List<Tickets> items 			 = ticketsRepository.getByCriteria(request, em, locale);

		if (items != null && !items.isEmpty()) {
			List<TicketsDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? TicketsTransformer.INSTANCE.toLiteDtos(items) : TicketsTransformer.INSTANCE.toDtos(items);

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
			response.setCount(ticketsRepository.count(request, em, locale));
			response.setHasError(false);
		} else {
			response.setStatus(functionalError.DATA_EMPTY("tickets", locale));
			response.setHasError(false);
			return response;
		}

		log.info("----end get Tickets-----");
		return response;
	}

	/**
	 * get full TicketsDto by using Tickets as object.
	 * 
	 * @param dto
	 * @param size
	 * @param isSimpleLoading
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	private TicketsDto getFullInfos(TicketsDto dto, Integer size, Boolean isSimpleLoading, Locale locale) throws Exception {
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
