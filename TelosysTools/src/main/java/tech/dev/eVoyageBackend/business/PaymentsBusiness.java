                                                                														
/*
 * Java business for entity table payments 
 * Created on 2025-01-12 ( Time 17:39:54 )
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
import tech.dev.eVoyageBackend.dao.entity.Payments;
import tech.dev.eVoyageBackend.dao.entity.Bookings;
import tech.dev.eVoyageBackend.dao.entity.Companies;
import tech.dev.eVoyageBackend.dao.entity.*;
import tech.dev.eVoyageBackend.dao.repository.*;

/**
BUSINESS for table "payments"
 * 
 * @author Geo
 *
 */
@Log
@Component
public class PaymentsBusiness implements IBasicBusiness<Request<PaymentsDto>, Response<PaymentsDto>> {

	private Response<PaymentsDto> response;
	@Autowired
	private PaymentsRepository paymentsRepository;
	@Autowired
	private BookingsRepository bookingsRepository;
	@Autowired
	private CompaniesRepository companiesRepository;
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

	public PaymentsBusiness() {
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	}
	
	/**
	 * create Payments by using PaymentsDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<PaymentsDto> create(Request<PaymentsDto> request, Locale locale)  throws ParseException {
		log.info("----begin create Payments-----");

		Response<PaymentsDto> response = new Response<PaymentsDto>();
		List<Payments>        items    = new ArrayList<Payments>();
			
		for (PaymentsDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("bookingId", dto.getBookingId());
			fieldsToVerify.put("companyId", dto.getCompanyId());
			fieldsToVerify.put("amount", dto.getAmount());
			fieldsToVerify.put("paymentMethod", dto.getPaymentMethod());
			fieldsToVerify.put("status", dto.getStatus());
			fieldsToVerify.put("deletedAt", dto.getDeletedAt());
			fieldsToVerify.put("deletedBy", dto.getDeletedBy());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verify if payments to insert do not exist
			Payments existingEntity = null;
			if (existingEntity != null) {
				response.setStatus(functionalError.DATA_EXIST("payments id -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
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
				Payments entityToSave = null;
			entityToSave = PaymentsTransformer.INSTANCE.toEntity(dto, existingBookings, existingCompanies);
			entityToSave.setCreatedAt(Utilities.getCurrentDate());
			entityToSave.setCreatedBy(request.getUser());
			entityToSave.setIsDeleted(false);
			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<Payments> itemsSaved = null;
			// inserer les donnees en base de donnees
			itemsSaved = paymentsRepository.saveAll((Iterable<Payments>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("payments", locale));
				response.setHasError(true);
				return response;
			}
			List<PaymentsDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? PaymentsTransformer.INSTANCE.toLiteDtos(itemsSaved) : PaymentsTransformer.INSTANCE.toDtos(itemsSaved);

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

		log.info("----end create Payments-----");
		return response;
	}

	/**
	 * update Payments by using PaymentsDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<PaymentsDto> update(Request<PaymentsDto> request, Locale locale)  throws ParseException {
		log.info("----begin update Payments-----");

		Response<PaymentsDto> response = new Response<PaymentsDto>();
		List<Payments>        items    = new ArrayList<Payments>();
			
		for (PaymentsDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la payments existe
			Payments entityToSave = null;
			entityToSave = paymentsRepository.findOne(dto.getId(), false);
			if (entityToSave == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("payments id -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
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
			if (dto.getAmount() != null && dto.getAmount() > 0) {
				entityToSave.setAmount(dto.getAmount());
			}
			if (Utilities.notBlank(dto.getPaymentMethod())) {
				entityToSave.setPaymentMethod(dto.getPaymentMethod());
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
			List<Payments> itemsSaved = null;
			// maj les donnees en base
			itemsSaved = paymentsRepository.saveAll((Iterable<Payments>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("payments", locale));
				response.setHasError(true);
				return response;
			}
			List<PaymentsDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? PaymentsTransformer.INSTANCE.toLiteDtos(itemsSaved) : PaymentsTransformer.INSTANCE.toDtos(itemsSaved);

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

		log.info("----end update Payments-----");
		return response;
	}

	/**
	 * delete Payments by using PaymentsDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<PaymentsDto> delete(Request<PaymentsDto> request, Locale locale)  {
		log.info("----begin delete Payments-----");

		Response<PaymentsDto> response = new Response<PaymentsDto>();
		List<Payments>        items    = new ArrayList<Payments>();
			
		for (PaymentsDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la payments existe
			Payments existingEntity = null;
			existingEntity = paymentsRepository.findOne(dto.getId(), false);
			if (existingEntity == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("payments -> " + dto.getId(), locale));
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
			paymentsRepository.saveAll((Iterable<Payments>) items);

			response.setHasError(false);
		}

		log.info("----end delete Payments-----");
		return response;
	}

	/**
	 * get Payments by using PaymentsDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<PaymentsDto> getByCriteria(Request<PaymentsDto> request, Locale locale)  throws Exception {
		log.info("----begin get Payments-----");

		Response<PaymentsDto> response = new Response<PaymentsDto>();
		List<Payments> items 			 = paymentsRepository.getByCriteria(request, em, locale);

		if (items != null && !items.isEmpty()) {
			List<PaymentsDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? PaymentsTransformer.INSTANCE.toLiteDtos(items) : PaymentsTransformer.INSTANCE.toDtos(items);

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
			response.setCount(paymentsRepository.count(request, em, locale));
			response.setHasError(false);
		} else {
			response.setStatus(functionalError.DATA_EMPTY("payments", locale));
			response.setHasError(false);
			return response;
		}

		log.info("----end get Payments-----");
		return response;
	}

	/**
	 * get full PaymentsDto by using Payments as object.
	 * 
	 * @param dto
	 * @param size
	 * @param isSimpleLoading
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	private PaymentsDto getFullInfos(PaymentsDto dto, Integer size, Boolean isSimpleLoading, Locale locale) throws Exception {
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
