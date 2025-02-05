                                                                														
/*
 * Java business for entity table tickets 
 * Created on 2025-01-12 ( Time 17:39:58 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2018 Geo. All Rights Reserved.
 */

package tech.dev.eVoyageBackend.business;

import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import lombok.extern.java.Log;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.ByteArrayInputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import tech.dev.eVoyageBackend.rest.api.TicketsController;
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

import com.google.zxing.*;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeReader;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

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
	private static final Logger log = LoggerFactory.getLogger(TicketsBusiness.class);

	private SimpleDateFormat dateFormat;
	private SimpleDateFormat dateTimeFormat;

	public TicketsBusiness() {
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	}
	@Value("${ticket.validity.days:3}") // Durée de validité configurable
	private int ticketValidityDays;
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
			Map<String, Object> fieldsToVerify = new HashMap<String, Object>();
			fieldsToVerify.put("bookingId", dto.getBookingId());
			fieldsToVerify.put("companyId", dto.getCompanyId());
			fieldsToVerify.put("qrCode", dto.getQrCode());
//			fieldsToVerify.put("status", dto.getStatus());
			fieldsToVerify.put("scannedBy", dto.getScannedBy());
//			fieldsToVerify.put("deletedAt", dto.getDeletedAt());
//			fieldsToVerify.put("deletedBy", dto.getDeletedBy());
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
			Map<String, Object> fieldsToVerify = new HashMap<String, Object>();
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
			Map<String, Object> fieldsToVerify = new HashMap<String, Object>();
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



	public Response<TicketValidationDto> controlQrCode(Request<TicketValidationRequestDto> request, Locale locale) {
		log.info("----begin controlQrCode-----");

		Response<TicketValidationDto> response = new Response<>();

		// Vérification des données de la requête
		if (request.getDatas() == null || request.getDatas().isEmpty()) {
			log.warn("Échec : QR Code manquant dans la requête.");
			response.setStatus(functionalError.FIELD_EMPTY("QR Code", locale));
			response.setHasError(true);
			return response;
		}

		TicketValidationRequestDto requestData = request.getDatas().get(0);
		String base64QrCode = requestData.getBase64QrCode();

		if (base64QrCode == null || base64QrCode.trim().isEmpty()) {
			log.warn("Échec : Le QR Code est vide ou invalide.");
			response.setStatus(functionalError.FIELD_EMPTY("QR Code", locale));
			response.setHasError(true);
			return response;
		}

		log.info("Décodage du QR Code en cours...");
		String qrData;
		try {
			qrData = decodeQrCode(base64QrCode);
			if (qrData == null || qrData.trim().isEmpty()) {
				log.warn("Échec : QR Code non reconnu ou mal formaté.");
				response.setStatus(functionalError.INVALID_FILE_TYPE("QR Code", locale));
				response.setHasError(true);
				return response;
			}
			log.info("QR Code décodé avec succès : {}", qrData);
		} catch (Exception e) {
			log.error("Erreur lors du décodage du QR Code", e);
			response.setStatus(functionalError.QR_CODE_DECODE_FAILED("QR Code decoding", locale));
			response.setHasError(true);
			return response;
		}

		log.info("Extraction des informations du QR Code...");
		Map<String, String> qrInfo = parseQrData(qrData);
		if (qrInfo.isEmpty()) {
			log.warn("Échec : Données QR Code invalides.");
			response.setStatus(functionalError.INVALID_FILE_TYPE("QR Code", locale));
			response.setHasError(true);
			return response;
		}

		String bookingId = qrInfo.get("booking_id");
		String clientId = qrInfo.get("client_id");
		String clientName = qrInfo.get("client_name");
		String departureCity = qrInfo.get("departure_city");
		String departureStation = qrInfo.get("departure_station");
		String destinationCity = qrInfo.get("destination_city");
		String destinationStation = qrInfo.get("destination_station");
		String compagnie = qrInfo.get("compagnie");
		String status = qrInfo.get("status");

		log.info("QR Code analysé : BookingID={}, Client ID={}, Client={}, Départ={} ({}) → Destination={} ({}), Compagnie={}, Statut={}",
				bookingId, clientId, clientName, departureCity, departureStation, destinationCity, destinationStation, compagnie, status);

		// Vérifier la validité du Booking ID
		if (!Utilities.isNumeric(bookingId)) {
			log.warn("Échec : Booking ID invalide : {}", bookingId);
			response.setStatus(functionalError.INVALID_DATA("Booking ID", locale));
			response.setHasError(true);
			return response;
		}

		// Rechercher le ticket correspondant dans la base
		log.info("Recherche du ticket en base pour Booking ID {}", bookingId);
		Tickets ticket;
		try {
			ticket = ticketsRepository.findByBookingId2(Integer.parseInt(bookingId))
					.orElseThrow(() -> new RuntimeException("Ticket introuvable pour Booking ID: " + bookingId));
			log.info("Ticket trouvé en base : Booking ID {}", bookingId);
		} catch (Exception e) {
			log.warn("Échec : Ticket introuvable pour Booking ID {}", bookingId);
			response.setStatus(functionalError.DATA_NOT_EXIST("Booking ID: " + bookingId, locale));
			response.setHasError(true);
			return response;
		}

		// Vérifier si le ticket a déjà été consommé
		if ("CONSUMED".equalsIgnoreCase(ticket.getStatus())) {
			log.warn("Échec : Ticket déjà consommé pour Booking ID {}", bookingId);
			response.setStatus(functionalError.DATA_EXIST("Ce ticket a déjà été consommé", locale));
			response.setHasError(true);
			return response;
		}

		// Vérifier la validité temporelle du ticket (Fuseau horaire: Africa/Abidjan)
		log.info("Vérification de la validité du ticket...");
		Date purchaseDate = ticket.getCreatedAt();
		ZoneId zoneId = ZoneId.of("Africa/Abidjan");
		LocalDate validUntilDate = purchaseDate.toInstant()
				.atZone(zoneId)
				.toLocalDate()
				.plusDays(ticketValidityDays);

		if (LocalDate.now(zoneId).isAfter(validUntilDate)) {
			log.warn("Échec : Ticket expiré pour Booking ID {}", bookingId);
			response.setStatus(functionalError.DATA_EXPIRED("Ce ticket a expiré", locale));
			response.setHasError(true);
			return response;
		}

		// Mettre à jour le statut du ticket
		log.info("Validation du ticket en cours...");
		ticket.setStatus("CONSUMED");
		ticket.setUpdatedAt(new Date());
		ticketsRepository.save(ticket);
		log.info("Ticket validé avec succès pour Booking ID {}", bookingId);

		// Créer l'objet DTO pour la réponse
		// Créer l'objet DTO pour la réponse avec les nouvelles informations
		TicketValidationDto ticketDto = new TicketValidationDto(
				bookingId,
				clientId,
				clientName,
				departureCity,
				departureStation,
				destinationCity,
				destinationStation,
				compagnie,
				ticket.getStatus()
		);


		response.setItems(Collections.singletonList(ticketDto));
		response.setStatus(functionalError.SUCCESS("Validation du ticket", locale));
		response.setHasError(false);

		log.info("----end controlQrCode-----");
		return response;
	}

	/**
	 * Parse les données extraites du QR Code sous forme de clé-valeur.
	 *
	 * @param qrData Données brutes extraites du QR Code.
	 * @return Map contenant les informations du ticket.
	 */
	private Map<String, String> parseQrData(String qrData) {
		Map<String, String> qrInfo = new HashMap<>();
		try {
			log.info("Début du parsing du QR Code : {}", qrData);

			String[] data = qrData.split(",");
			for (String entry : data) {
				String[] keyValue = entry.split(":", 2); // Limite le split pour éviter de couper les valeurs contenant ":"
				if (keyValue.length == 2) {
					String key = keyValue[0].trim().toLowerCase().replace(" ", "_"); // Normalise la clé
					String value = keyValue[1].trim();
					qrInfo.put(key, value);
					log.info("Clé détectée : {} - Valeur : {}", key, value);
				} else {
					log.warn("Format invalide détecté dans le QR Code : {}", entry);
				}
			}
		} catch (Exception e) {
			log.error("Erreur lors du parsing du QR Code : {}", e.getMessage(), e);
		}

		log.info("Données extraites du QR Code : {}", qrInfo);
		return qrInfo;
	}



	private String decodeQrCode(String base64QrCode) {
		try {
			// Décoder le Base64 en image
			byte[] decodedBytes = Base64.getDecoder().decode(base64QrCode);
			ByteArrayInputStream bais = new ByteArrayInputStream(decodedBytes);
			BufferedImage bufferedImage = ImageIO.read(bais);

			if (bufferedImage == null) {
				throw new RuntimeException("L'image décodée du QR Code est invalide.");
			}

			// Lire le QR Code à partir de l'image
			LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
			BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
			Result result = new MultiFormatReader().decode(bitmap);

			return result.getText(); // Retourner le texte extrait du QR code
		} catch (Exception e) {
			throw new RuntimeException("Erreur lors du décodage du QR Code.", e);
		}
	}

}
