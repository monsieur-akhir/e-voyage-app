                                                                														
/*
 * Java business for entity table payments 
 * Created on 2025-01-12 ( Time 17:39:54 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2018 Geo. All Rights Reserved.
 */

package tech.dev.eVoyageBackend.business;

import com.google.zxing.client.j2se.MatrixToImageWriter;
import lombok.extern.java.Log;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

import javax.imageio.ImageIO;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeParseException;
import java.util.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import org.springframework.transaction.annotation.Transactional;
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

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Base64;
import javax.imageio.ImageIO;


/**
BUSINESS for table "payments"
 * 
 * @author Geo
 *
 */
@Log
@Component
public class PaymentsBusiness implements IBasicBusiness<Request<PaymentsDto>, Response<PaymentsDto>> {
	private static final Logger log = LoggerFactory.getLogger(PaymentsBusiness.class);
	private Response<PaymentsDto> response;
	@Autowired
	private PaymentsRepository paymentsRepository;
	@Autowired
	private BookingsRepository bookingsRepository;
	@Autowired
	private CompaniesRepository companiesRepository;

	@Autowired
	private ParamsUtils paramUtils;

	@Autowired
	private TicketsRepository ticketsRepository;

	@Autowired
	private DepartsRepository departsRepository;

	@Autowired
	private PdfGenerator pdfGenerator;

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

//	@Override
	public Response<PaymentsDto> createold(Request<PaymentsDto> request, Locale locale) throws ParseException {
		log.info("----begin create Payments-----");

		Response<PaymentsDto> response = new Response<PaymentsDto>();
		List<Payments> items = new ArrayList<>();

		for (PaymentsDto dto : request.getDatas()) {
			// Vérification des paramètres obligatoires
			Map<String, Object> fieldsToVerify = new HashMap<>();
			fieldsToVerify.put("bookingId", dto.getBookingId());
			fieldsToVerify.put("companyId", dto.getCompanyId());
			fieldsToVerify.put("amount", dto.getAmount());
			fieldsToVerify.put("paymentMethod", dto.getPaymentMethod());

			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Vérifie si la réservation existe
			Bookings existingBookings = null;
			if (dto.getBookingId() != null && dto.getBookingId() > 0) {
				existingBookings = bookingsRepository.findOne(dto.getBookingId(), false);
				if (existingBookings == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("bookings bookingId -> " + dto.getBookingId(), locale));
					response.setHasError(true);
					return response;
				}
			}

			// Vérifie si la compagnie existe
			Companies existingCompanies = null;
			if (dto.getCompanyId() != null && dto.getCompanyId() > 0) {
				existingCompanies = companiesRepository.findOne(dto.getCompanyId(), false);
				if (existingCompanies == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("companies companyId -> " + dto.getCompanyId(), locale));
					response.setHasError(true);
					return response;
				}
			}

			// Création de l'entité Payment
			Payments entityToSave = PaymentsTransformer.INSTANCE.toEntity(dto, existingBookings, existingCompanies);
			entityToSave.setCreatedAt(Utilities.getCurrentDate());
			entityToSave.setCreatedBy(request.getUser());
			entityToSave.setIsDeleted(false);
			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			// Sauvegarde des paiements
			List<Payments> itemsSaved = paymentsRepository.saveAll(items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("payments", locale));
				response.setHasError(true);
				return response;
			}

			// Mise à jour des statuts des réservations
			itemsSaved.forEach(payment -> {
				Bookings booking = payment.getBookings(); // Récupérer la réservation liée
				if (booking != null) {
					booking.setStatus("CONFIRMED"); // Mettre à jour le statut en CONFIRMED
					booking.setUpdatedAt(Utilities.getCurrentDate());
					booking.setUpdatedBy(request.getUser());
					bookingsRepository.save(booking); // Sauvegarder les modifications
				}
			});

			// Transformation des données pour la réponse
			List<PaymentsDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading()))
					? PaymentsTransformer.INSTANCE.toLiteDtos(itemsSaved)
					: PaymentsTransformer.INSTANCE.toDtos(itemsSaved);

			final int size = itemsSaved.size();
			List<String> listOfError = Collections.synchronizedList(new ArrayList<>());
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


	@Transactional
	@Override
	public Response<PaymentsDto> create(Request<PaymentsDto> request, Locale locale) throws ParseException {
		log.info("---- Début de la création des paiements -----");

		Response<PaymentsDto> response = new Response<>();
		List<PaymentsDto> dtos = new ArrayList<>();

		for (PaymentsDto dto : request.getDatas()) {
			log.info("Traitement du paiement pour la réservation ID : {}", dto.getBookingId());

			// Vérification des champs obligatoires
			Map<String, Object> fieldsToVerify = new HashMap<>();
			fieldsToVerify.put("bookingId", dto.getBookingId());
			fieldsToVerify.put("companyId", dto.getCompanyId());
			fieldsToVerify.put("paymentMethod", dto.getPaymentMethod());

			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				log.error("Champ obligatoire manquant : {}", Validate.getValidate().getField());
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Vérification de l'existence de la réservation
			log.info("Récupération de la réservation avec l'ID : {}", dto.getBookingId());
			Bookings existingBookings = bookingsRepository.findOne(dto.getBookingId(), false);
			if (existingBookings == null) {
				log.error("Réservation introuvable avec l'ID : {}", dto.getBookingId());
				response.setStatus(functionalError.DATA_NOT_EXIST("Booking ID: " + dto.getBookingId(), locale));
				response.setHasError(true);
				return response;
			}

			// Vérification de l'existence de la compagnie
			log.info("Récupération de la compagnie avec l'ID : {}", dto.getCompanyId());
			Companies existingCompanies = companiesRepository.findOne(dto.getCompanyId(), false);
			if (existingCompanies == null) {
				log.error("Compagnie introuvable avec l'ID : {}", dto.getCompanyId());
				response.setStatus(functionalError.DATA_NOT_EXIST("Company ID: " + dto.getCompanyId(), locale));
				response.setHasError(true);
				return response;
			}

			// Vérification du départ associé à la réservation
			log.info("Récupération et verrouillage du départ pour la réservation ID : {}", dto.getBookingId());
			Departs associatedDepart = departsRepository.findAndLockById(existingBookings.getDeparts().getId());
			if (associatedDepart == null) {
				log.error("Départ introuvable pour la réservation ID : {}", dto.getBookingId());
				response.setStatus(functionalError.DATA_NOT_EXIST("Depart for Booking ID: " + dto.getBookingId(), locale));
				response.setHasError(true);
				return response;
			}

			// Vérification de la validité de la date et de l'heure de départ
			try {
				DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

				LocalDate departureDate = LocalDate.parse(associatedDepart.getDepartureDate(), dateFormatter);
				LocalTime departureTime = LocalTime.parse(associatedDepart.getDepartureTime(), timeFormatter);
				LocalDateTime departureDateTime = LocalDateTime.of(departureDate, departureTime);

				if (departureDateTime.isBefore(LocalDateTime.now())) {
					log.error("Le départ du {} à {} est déjà passé", departureDate, departureTime);
					response.setStatus(functionalError.INVALID_DATE_PERIOD(
							"Départ déjà passé : " + departureDate + " " + departureTime, locale));
					response.setHasError(true);
					return response;
				}
			} catch (DateTimeParseException | NullPointerException e) {
				log.error("Erreur de parsing de date/heure pour le départ ID {}", associatedDepart.getId(), e);
				response.setStatus(functionalError.INVALID_FORMAT("Format invalide. Utilisez yyyy-MM-dd et HH:mm", locale));
				response.setHasError(true);
				return response;
			}

			// Vérification des places disponibles
			if (associatedDepart.getAvailableSeats() < existingBookings.getNumberOfSeats()) {
				log.error("Pas assez de places disponibles pour le départ ID : {}", associatedDepart.getId());
				response.setStatus(functionalError.INSUFFICIENT_SEATS("Available seats: " + associatedDepart.getAvailableSeats(), locale));
				response.setHasError(true);
				return response;
			}

			// Calcul du montant total
			double totalAmount = existingBookings.getNumberOfSeats() * associatedDepart.getPrice();
			dto.setAmount(totalAmount);
			log.info("Montant total calculé : {} pour la réservation ID : {}", totalAmount, dto.getBookingId());

			// Création et enregistrement du paiement
			Payments entityToSave = PaymentsTransformer.INSTANCE.toEntity(dto, existingBookings, existingCompanies);
			entityToSave.setStatus("COMPLETED");
			entityToSave.setCreatedAt(Utilities.getCurrentDate());
			Payments savedPayment = paymentsRepository.save(entityToSave);

			// Mise à jour du statut de la réservation
			existingBookings.setStatus("CONFIRMED");
			existingBookings.setUpdatedAt(Utilities.getCurrentDate());
			bookingsRepository.save(existingBookings);

			// Mise à jour du nombre de places disponibles
			int updatedAvailableSeats = associatedDepart.getAvailableSeats() - existingBookings.getNumberOfSeats();
			if (updatedAvailableSeats >= 0) {
				associatedDepart.setAvailableSeats(updatedAvailableSeats);
				departsRepository.save(associatedDepart);
			}

			// Génération du QR Code
			String qrCodeBase64;
			try {
				qrCodeBase64 = generateQrCode(existingBookings);
			} catch (Exception e) {
				log.error("Erreur lors de la génération du QR code", e);
				response.setStatus(functionalError.QR_CODE_GENERATION_FAILED("Booking ID: " + dto.getBookingId(), locale));
				response.setHasError(true);
				return response;
			}

			// Création du ticket
			Tickets ticket = new Tickets();
			ticket.setQrCode(qrCodeBase64);
			ticket.setStatus("VALID");
			ticket.setBookings(existingBookings);
			ticket.setCompanies(existingCompanies);
			ticket.setIsDeleted(false);
			ticket.setCreatedAt(Utilities.getCurrentDate());
			Tickets savedTicket = ticketsRepository.save(ticket);

			// Génération du PDF pour le ticket
			String pdfPath = paramUtils.getTiketsRopository() + "/ticket_" + savedTicket.getId() + ".pdf";
			try {
				pdfGenerator.generateTicketPdfWithFlyingSaucer(savedTicket, qrCodeBase64, pdfPath);
			} catch (Exception e) {
				log.error("Erreur lors de la génération du PDF", e);
				response.setStatus(functionalError.PDF_GENERATION_FAILED("Ticket ID: " + savedTicket.getId(), locale));
				response.setHasError(true);
				return response;
			}

			// Ajout du lien de téléchargement au DTO
			//String downloadLink = paramUtils.getBaseUrl() + "/download?file=" + pdfPath;
			String downloadLink = pdfPath;
			PaymentsDto paymentsDto = PaymentsTransformer.INSTANCE.toDto(savedPayment);
			paymentsDto.setDownloadLink(downloadLink);
			dtos.add(paymentsDto);
		}

		response.setItems(dtos);
		response.setStatus(functionalError.SUCCESS("Paiements créés avec succès", locale));
		response.setHasError(false);
		log.info("---- Fin de la création des paiements -----");
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
			Map<String, Object> fieldsToVerify = new HashMap<String, Object>();
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
			Map<String, Object> fieldsToVerify = new HashMap<String, Object>();
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

//	private boolean callPaymentAPI(String paymentMethod, String paymentReference, Double amount, Integer bookingId) {
//		log.info("Calling payment API for method: {}", paymentMethod);
//
//		// Simule un appel à une API de paiement
//		try {
//			// Exemple simplifié d'appel
//			PaymentRequest request = new PaymentRequest(paymentMethod, paymentReference, amount, bookingId);
//			PaymentResponse response = paymentApiService.processPayment(request); // Appel à un service externe
//
//			if (response.isSuccess()) {
//				log.info("Payment successful for Booking ID: {}", bookingId);
//				return true;
//			} else {
//				log.error("Payment failed: {}", response.getErrorMessage());
//				return false;
//			}
//		} catch (Exception e) {
//			log.error("Error during payment processing: {}", e.getMessage());
//			return false;
//		}
//	}

	private String generateQrCode(Bookings booking) {
		String qrData = String.format(
				"Booking ID: %s, Client Id: %s, Client Name: %s, Ville de Depart: %s, Departure Station: %s, Ville d'arrivée: %s, Destination Station: %s, Compagnie: %s, Status: %s",
				booking.getId(),
				booking.getUsers().getId(),
				booking.getUsers().getName(),
				booking.getStations().getCities().getName(),
				booking.getStations().getName(),
				booking.getStations2().getCities().getName(),
				booking.getStations2().getName(),
				booking.getCompanies().getName(),
				booking.getStatus()
		);

		try {
			QRCodeWriter qrCodeWriter = new QRCodeWriter();
			BitMatrix bitMatrix = qrCodeWriter.encode(qrData, BarcodeFormat.QR_CODE, 200, 200);
			BufferedImage qrImage = MatrixToImageWriter.toBufferedImage(bitMatrix);

			// Convertir l'image QR en base64
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(qrImage, "png", baos);
			byte[] qrBytes = baos.toByteArray();

			return Base64.getEncoder().encodeToString(qrBytes);
		} catch (Exception e) {
			throw new RuntimeException("Error generating QR Code", e);
		}
	}

}
