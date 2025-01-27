                                                                                																		
/*
 * Java business for entity table bookings 
 * Created on 2025-01-21 ( Time 19:33:16 )
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
import tech.dev.eVoyageBackend.dao.entity.Bookings;
import tech.dev.eVoyageBackend.dao.entity.Users;
import tech.dev.eVoyageBackend.dao.entity.Buses;
import tech.dev.eVoyageBackend.dao.entity.Stations;
import tech.dev.eVoyageBackend.dao.entity.Departs;
import tech.dev.eVoyageBackend.dao.entity.Stations;
import tech.dev.eVoyageBackend.dao.entity.Companies;
import tech.dev.eVoyageBackend.dao.entity.*;
import tech.dev.eVoyageBackend.dao.repository.*;

/**
BUSINESS for table "bookings"
 * 
 * @author Geo
 *
 */
@Log
@Component
public class BookingsBusiness implements IBasicBusiness<Request<BookingsDto>, Response<BookingsDto>> {

	private Response<BookingsDto> response;
	@Autowired
	private BookingsRepository bookingsRepository;
	@Autowired
	private TicketsRepository ticketsRepository;
	@Autowired
	private UsersRepository usersRepository;
	@Autowired
	private BusesRepository busesRepository;
	@Autowired
	private StationsRepository stations2Repository;
	@Autowired
	private DepartsRepository departsRepository;
	@Autowired
	private PaymentsRepository paymentsRepository;
	@Autowired
	private StationsRepository stationsRepository;
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

	public BookingsBusiness() {
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	}
	
	/**
	 * create Bookings by using BookingsDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<BookingsDto> create(Request<BookingsDto> request, Locale locale)  throws ParseException {
		log.info("----begin create Bookings-----");

		Response<BookingsDto> response = new Response<BookingsDto>();
		List<Bookings>        items    = new ArrayList<Bookings>();
			
		for (BookingsDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, Object> fieldsToVerify = new HashMap<String, Object>();
		//	fieldsToVerify.put("seatNumber", dto.getSeatNumber());
			fieldsToVerify.put("numberOfSeats", dto.getNumberOfSeats());
		//	fieldsToVerify.put("status", dto.getStatus());
//			fieldsToVerify.put("deletedAt", dto.getDeletedAt());
//			fieldsToVerify.put("deletedBy", dto.getDeletedBy());
			fieldsToVerify.put("busId", dto.getBusId());
			fieldsToVerify.put("companyId", dto.getCompanyId());
			fieldsToVerify.put("departureId", dto.getDepartureId());
			fieldsToVerify.put("userId", dto.getUserId());
			fieldsToVerify.put("originStationId", dto.getOriginStationId());
			fieldsToVerify.put("destinationStationId", dto.getDestinationStationId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verify if bookings to insert do not exist
			Bookings existingEntity = null;
			if (existingEntity != null) {
				response.setStatus(functionalError.DATA_EXIST("bookings id -> " + dto.getId(), locale));
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
			// Verify if buses exist
			Buses existingBuses = null;
			if (dto.getBusId() != null && dto.getBusId() > 0){
				existingBuses = busesRepository.findOne(dto.getBusId(), false);
				if (existingBuses == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("buses busId -> " + dto.getBusId(), locale));
					response.setHasError(true);
					return response;
				}
			}
			// Verify if stations2 exist
			Stations existingStations2 = null;
			if (dto.getDestinationStationId() != null && dto.getDestinationStationId() > 0){
				existingStations2 = stations2Repository.findOne(dto.getDestinationStationId(), false);
				if (existingStations2 == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("stations2 destinationStationId -> " + dto.getDestinationStationId(), locale));
					response.setHasError(true);
					return response;
				}
			}
			// Verify if departs exist
			Departs existingDeparts = null;
			if (dto.getDepartureId() != null && dto.getDepartureId() > 0){
				existingDeparts = departsRepository.findOne(dto.getDepartureId(), false);
				if (existingDeparts == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("departs departureId -> " + dto.getDepartureId(), locale));
					response.setHasError(true);
					return response;
				}
			}
			// Verify if stations exist
			Stations existingStations = null;
			if (dto.getOriginStationId() != null && dto.getOriginStationId() > 0){
				existingStations = stationsRepository.findOne(dto.getOriginStationId(), false);
				if (existingStations == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("stations originStationId -> " + dto.getOriginStationId(), locale));
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
				Bookings entityToSave = null;
			entityToSave = BookingsTransformer.INSTANCE.toEntity(dto, existingUsers, existingBuses, existingStations2, existingDeparts, existingStations, existingCompanies);
			entityToSave.setStatus("PENDING");
			entityToSave.setCreatedAt(Utilities.getCurrentDate());
			entityToSave.setCreatedBy(request.getUser());
			entityToSave.setIsDeleted(false);
			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<Bookings> itemsSaved = null;
			// inserer les donnees en base de donnees
			itemsSaved = bookingsRepository.saveAll((Iterable<Bookings>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("bookings", locale));
				response.setHasError(true);
				return response;
			}
			List<BookingsDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? BookingsTransformer.INSTANCE.toLiteDtos(itemsSaved) : BookingsTransformer.INSTANCE.toDtos(itemsSaved);

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

		log.info("----end create Bookings-----");
		return response;
	}

	/**
	 * update Bookings by using BookingsDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<BookingsDto> update(Request<BookingsDto> request, Locale locale)  throws ParseException {
		log.info("----begin update Bookings-----");

		Response<BookingsDto> response = new Response<BookingsDto>();
		List<Bookings>        items    = new ArrayList<Bookings>();
			
		for (BookingsDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, Object> fieldsToVerify = new HashMap<String, Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la bookings existe
			Bookings entityToSave = null;
			entityToSave = bookingsRepository.findOne(dto.getId(), false);
			if (entityToSave == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("bookings id -> " + dto.getId(), locale));
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
			// Verify if buses exist
			if (dto.getBusId() != null && dto.getBusId() > 0){
				Buses existingBuses = busesRepository.findOne(dto.getBusId(), false);
				if (existingBuses == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("buses busId -> " + dto.getBusId(), locale));
					response.setHasError(true);
					return response;
				}
				entityToSave.setBuses(existingBuses);
			}
			// Verify if stations2 exist
			if (dto.getDestinationStationId() != null && dto.getDestinationStationId() > 0){
				Stations existingStations2 = stations2Repository.findOne(dto.getDestinationStationId(), false);
				if (existingStations2 == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("stations2 destinationStationId -> " + dto.getDestinationStationId(), locale));
					response.setHasError(true);
					return response;
				}
				entityToSave.setStations2(existingStations2);
			}
			// Verify if departs exist
			if (dto.getDepartureId() != null && dto.getDepartureId() > 0){
				Departs existingDeparts = departsRepository.findOne(dto.getDepartureId(), false);
				if (existingDeparts == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("departs departureId -> " + dto.getDepartureId(), locale));
					response.setHasError(true);
					return response;
				}
				entityToSave.setDeparts(existingDeparts);
			}
			// Verify if stations exist
			if (dto.getOriginStationId() != null && dto.getOriginStationId() > 0){
				Stations existingStations = stationsRepository.findOne(dto.getOriginStationId(), false);
				if (existingStations == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("stations originStationId -> " + dto.getOriginStationId(), locale));
					response.setHasError(true);
					return response;
				}
				entityToSave.setStations(existingStations);
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
			if (Utilities.notBlank(dto.getSeatNumber())) {
				entityToSave.setSeatNumber(dto.getSeatNumber());
			}
			if (dto.getNumberOfSeats() != null && dto.getNumberOfSeats() > 0) {
				entityToSave.setNumberOfSeats(dto.getNumberOfSeats());
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
			List<Bookings> itemsSaved = null;
			// maj les donnees en base
			itemsSaved = bookingsRepository.saveAll((Iterable<Bookings>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("bookings", locale));
				response.setHasError(true);
				return response;
			}
			List<BookingsDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? BookingsTransformer.INSTANCE.toLiteDtos(itemsSaved) : BookingsTransformer.INSTANCE.toDtos(itemsSaved);

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

		log.info("----end update Bookings-----");
		return response;
	}

	/**
	 * delete Bookings by using BookingsDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<BookingsDto> delete(Request<BookingsDto> request, Locale locale)  {
		log.info("----begin delete Bookings-----");

		Response<BookingsDto> response = new Response<BookingsDto>();
		List<Bookings>        items    = new ArrayList<Bookings>();
			
		for (BookingsDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, Object> fieldsToVerify = new HashMap<String, Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la bookings existe
			Bookings existingEntity = null;
			existingEntity = bookingsRepository.findOne(dto.getId(), false);
			if (existingEntity == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("bookings -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			// -----------------------------------------------------------------------
			// ----------- CHECK IF DATA IS USED
			// -----------------------------------------------------------------------

			// tickets
			List<Tickets> listOfTickets = ticketsRepository.findByBookingId(existingEntity.getId(), false);
			if (listOfTickets != null && !listOfTickets.isEmpty()){
				response.setStatus(functionalError.DATA_NOT_DELETABLE("(" + listOfTickets.size() + ")", locale));
				response.setHasError(true);
				return response;
			}
			// payments
			List<Payments> listOfPayments = paymentsRepository.findByBookingId(existingEntity.getId(), false);
			if (listOfPayments != null && !listOfPayments.isEmpty()){
				response.setStatus(functionalError.DATA_NOT_DELETABLE("(" + listOfPayments.size() + ")", locale));
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
			bookingsRepository.saveAll((Iterable<Bookings>) items);

			response.setHasError(false);
		}

		log.info("----end delete Bookings-----");
		return response;
	}

	/**
	 * get Bookings by using BookingsDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<BookingsDto> getByCriteria(Request<BookingsDto> request, Locale locale)  throws Exception {
		log.info("----begin get Bookings-----");

		Response<BookingsDto> response = new Response<BookingsDto>();
		List<Bookings> items 			 = bookingsRepository.getByCriteria(request, em, locale);

		if (items != null && !items.isEmpty()) {
			List<BookingsDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? BookingsTransformer.INSTANCE.toLiteDtos(items) : BookingsTransformer.INSTANCE.toDtos(items);

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
			response.setCount(bookingsRepository.count(request, em, locale));
			response.setHasError(false);
		} else {
			response.setStatus(functionalError.DATA_EMPTY("bookings", locale));
			response.setHasError(false);
			return response;
		}

		log.info("----end get Bookings-----");
		return response;
	}

	/**
	 * get full BookingsDto by using Bookings as object.
	 * 
	 * @param dto
	 * @param size
	 * @param isSimpleLoading
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	private BookingsDto getFullInfos(BookingsDto dto, Integer size, Boolean isSimpleLoading, Locale locale) throws Exception {
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
