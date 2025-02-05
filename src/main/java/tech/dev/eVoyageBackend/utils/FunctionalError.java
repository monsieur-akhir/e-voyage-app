
/*
 * Created on 2024-02-19 ( Time 10:54:59 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2018 Geo. All Rights Reserved.
 */

package tech.dev.eVoyageBackend.utils;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import tech.dev.eVoyageBackend.utils.Status;
import tech.dev.eVoyageBackend.utils.StatusCode;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Locale;

/**
 * Functional Error
 *
 * @author Geo
 *
 */
@Data
@ToString
@NoArgsConstructor
@XmlRootElement
@Component
public class FunctionalError {
	private String code;
	private String message;
	@Autowired
	private MessageSource messageSource;

	private static tech.dev.eVoyageBackend.utils.Status status = new tech.dev.eVoyageBackend.utils.Status();

	public tech.dev.eVoyageBackend.utils.Status SUCCESS(String message, Locale locale) {
		status.setCode(tech.dev.eVoyageBackend.utils.StatusCode.SUCCESS);
		status.setMessage(messageSource.getMessage("StatusMessage.SUCCESS", new Object[] {}, locale) + ": " + message);
		return status;
	}

	public tech.dev.eVoyageBackend.utils.Status ALREADY_TREATY(String message, Locale locale) {
		status.setCode(tech.dev.eVoyageBackend.utils.StatusCode.SUCCESS);
		status.setMessage(messageSource.getMessage("StatusMessage.FUNC_DATA_TREATY", new Object[] {}, locale) + ": " + message);
		return status;
	}

	public tech.dev.eVoyageBackend.utils.Status UNEXPECTED_ERROR2(String message, Locale locale) {
		status.setCode(tech.dev.eVoyageBackend.utils.StatusCode.TECH_INTERN_ERROR);
		status.setMessage(messageSource.getMessage("StatusMessage.TECH_INTERN_ERROR", new Object[] {}, locale) + ": " + message);
		return status;
	}


	public tech.dev.eVoyageBackend.utils.Status UNAUTHORIZED_APPROVAL(String message, Locale locale) {
		status.setCode(tech.dev.eVoyageBackend.utils.StatusCode.SUCCESS);
		status.setMessage(messageSource.getMessage("StatusMessage.FUNC_UNAUTHORIZED_APPROVAL", new Object[] {}, locale) + ": " + message);
		return status;
	}

	public tech.dev.eVoyageBackend.utils.Status AUTH_FAIL(String message, Locale locale) {
		status.setCode(tech.dev.eVoyageBackend.utils.StatusCode.FUNC_AUTH_FAIL);
		status.setMessage(
				messageSource.getMessage("StatusMessage.FUNC_AUTH_FAIL", new Object[] {}, locale) + ": " + message);
		return status;
	}

	public tech.dev.eVoyageBackend.utils.Status DATA_NOT_EXIST(String message, Locale locale) {
		status.setCode(tech.dev.eVoyageBackend.utils.StatusCode.FUNC_DATA_NOT_EXIST);
		status.setMessage(messageSource.getMessage("StatusMessage.FUNC_DATA_NOT_EXIST", new Object[] {}, locale) + ": "
				+ message);
		return status;
	}

	public tech.dev.eVoyageBackend.utils.Status DATA_TOO_LONG(String message, Locale locale) {
		status.setCode(tech.dev.eVoyageBackend.utils.StatusCode.FUNC_DATA_TOO_LONG);
		status.setMessage(
				messageSource.getMessage("StatusMessage.FUNC_DATA_TOO_LONG", new Object[] {}, locale) + ": " + message);
		return status;
	}

	public tech.dev.eVoyageBackend.utils.Status DATA_EMPTY(String message, Locale locale) {
		status.setCode(tech.dev.eVoyageBackend.utils.StatusCode.FUNC_DATA_EMPTY);
		status.setMessage(
				messageSource.getMessage("StatusMessage.FUNC_DATA_EMPTY", new Object[] {}, locale) + ": " + message);
		return status;
	}

	public tech.dev.eVoyageBackend.utils.Status DATA_EXIST(String message, Locale locale) {
		status.setCode(tech.dev.eVoyageBackend.utils.StatusCode.FUNC_DATA_EXIST);
		status.setMessage(
				messageSource.getMessage("StatusMessage.FUNC_DATA_EXIST", new Object[] {}, locale) + ": " + message);
		return status;
	}

	public tech.dev.eVoyageBackend.utils.Status INVALID_CODE_LANGUAGE(String message, Locale locale) {
		status.setCode(tech.dev.eVoyageBackend.utils.StatusCode.FUNC_INVALID_CODE_LANGUAGE);
		status.setMessage(messageSource.getMessage("StatusMessage.FUNC_INVALID_CODE_LANGUAGE", new Object[] {}, locale)
				+ ": " + message);
		return status;
	}


	public tech.dev.eVoyageBackend.utils.Status FIELD_EMPTY(String message, Locale locale) {
		status.setCode(tech.dev.eVoyageBackend.utils.StatusCode.FUNC_FIELD_EMPTY);
		status.setMessage(
				messageSource.getMessage("StatusMessage.FUNC_FIELD_EMPTY", new Object[] {}, locale) + ": " + message);
		return status;
	}

	public tech.dev.eVoyageBackend.utils.Status SEESION_EXPIRER(String message, Locale locale) {
		status.setCode(tech.dev.eVoyageBackend.utils.StatusCode.FUNC_FIELD_EMPTY);
		status.setMessage("Session Expirer");
		return status;
	}

	public tech.dev.eVoyageBackend.utils.Status USER_ALREADY_CONNECTED(String message, Locale locale) {
		status.setCode(tech.dev.eVoyageBackend.utils.StatusCode.FUNC_USER_ALREADY_CONNECTED);
		status.setMessage(messageSource.getMessage("StatusMessage.FUNC_USER_ALREADY_CONNECTED", new Object[] {}, locale)
				+ ": " + message);
		return status;
	}

	public tech.dev.eVoyageBackend.utils.Status USER_IS_UNLOCKED(String message, Locale locale) {
		status.setCode(tech.dev.eVoyageBackend.utils.StatusCode.FUNC_USER_IS_LOCKED);
		status.setMessage(messageSource.getMessage("StatusMessage.FUNC_USER_IS_UNLOCKED", new Object[] {}, locale) + ": "
				+ message);
		return status;
	}

	public tech.dev.eVoyageBackend.utils.Status REQUEST_FAIL(String message, Locale locale) {
		status.setCode(tech.dev.eVoyageBackend.utils.StatusCode.FUNC_REQUEST_FAIL);
		status.setMessage(
				messageSource.getMessage("StatusMessage.FUNC_REQUEST_FAIL", new Object[] {}, locale) + ": " + message);
		return status;
	}

	public tech.dev.eVoyageBackend.utils.Status UNEXPECTED_ERROR(String message, Locale locale) {
		status.setCode(tech.dev.eVoyageBackend.utils.StatusCode.FUNC_FAIL);
		status.setMessage(messageSource.getMessage("StatusMessage.UNEXPECTED_ERROR", new Object[] {}, locale) + ": " + message);
		return status;
	}

	public tech.dev.eVoyageBackend.utils.Status SAVE_FAIL(String message, Locale locale) {
		status.setCode(tech.dev.eVoyageBackend.utils.StatusCode.FUNC_SAVE_FAIL);
		status.setMessage(
				messageSource.getMessage("StatusMessage.FUNC_SAVE_FAIL", new Object[] {}, locale) + ": " + message);
		return status;
	}

	public tech.dev.eVoyageBackend.utils.Status TYPE_NOT_CORRECT(String message, Locale locale) {
		status.setCode(tech.dev.eVoyageBackend.utils.StatusCode.FUNC_TYPE_NOT_CORRECT);
		status.setMessage(messageSource.getMessage("StatusMessage.FUNC_TYPE_NOT_CORRECT", new Object[] {}, locale)
				+ ": " + message);
		return status;
	}

	public tech.dev.eVoyageBackend.utils.Status DATE_FORMAT_NOT_CORRECT(String message, Locale locale) {
		status.setCode(tech.dev.eVoyageBackend.utils.StatusCode.FUNC_DATE_FORMAT_NOT_CORRECT);
		status.setMessage(
				messageSource.getMessage("StatusMessage.FUNC_DATE_FORMAT_NOT_CORRECT", new Object[] {}, locale) + ": "
						+ message);
		return status;
	}

	public tech.dev.eVoyageBackend.utils.Status INVALID_DATE_PERIOD(String message, Locale locale) {
		status.setCode(tech.dev.eVoyageBackend.utils.StatusCode.FUNC_INVALID_DATE_PERIOD);
		status.setMessage(messageSource.getMessage("StatusMessage.FUNC_INVALID_DATE_PERIOD", new Object[] {}, locale)
				+ ": " + message);
		return status;
	}

	public tech.dev.eVoyageBackend.utils.Status INVALID_FORMAT(String message, Locale locale) {
		status.setCode(tech.dev.eVoyageBackend.utils.StatusCode.FUNC_INVALID_FORMAT);
		status.setMessage(messageSource.getMessage("StatusMessage.FUNC_INVALID_FORMAT", new Object[] {}, locale) + ": "
				+ message);
		return status;
	}

	public tech.dev.eVoyageBackend.utils.Status INVALID_ENTITY_NAME(String message, Locale locale) {
		status.setCode(tech.dev.eVoyageBackend.utils.StatusCode.FUNC_INVALID_ENTITY_NAME);
		status.setMessage(messageSource.getMessage("StatusMessage.FUNC_INVALID_ENTITY_NAME", new Object[] {}, locale)
				+ ": " + message);
		return status;
	}

	public tech.dev.eVoyageBackend.utils.Status IMEI_INCORRECT(String message, Locale locale) {
		status.setCode(tech.dev.eVoyageBackend.utils.StatusCode.FUNC_IMEI_INCORRECT);
		status.setMessage(messageSource.getMessage("StatusMessage.FUNC_IMEI_INCORRECT", new Object[] {}, locale) + ": "
				+ message);
		return status;
	}

	public tech.dev.eVoyageBackend.utils.Status FUNC_ONE_MUST_BE_FIELD_EMPTY(String message, Locale locale) {
		status.setCode(tech.dev.eVoyageBackend.utils.StatusCode.FUNC_ONE_MUST_BE_FIELD_EMPTY);
		status.setMessage(
				messageSource.getMessage("StatusMessage.FUNC_ONE_MUST_BE_FIELD_EMPTY", new Object[] {}, locale) + ": "
						+ message);
		return status;
	}

	public tech.dev.eVoyageBackend.utils.Status FUNC_PERCENT_VALUE(String message, Locale locale) {
		status.setCode(tech.dev.eVoyageBackend.utils.StatusCode.FUNC_MUST_BE_INFERIOR_TO_100);
		status.setMessage(
				messageSource.getMessage("StatusMessage.FUNC_MUST_BE_INFERIOR_TO_100", new Object[] {}, locale) + ": "
						+ message);
		return status;
	}

	public tech.dev.eVoyageBackend.utils.Status FILE_GENERATION_ERROR(String message, Locale locale) {
		status.setCode(tech.dev.eVoyageBackend.utils.StatusCode.FUNC_FILE_GENERATION_ERROR);
		status.setMessage(messageSource.getMessage("StatusMessage.FUNC_FILE_GENERATION_ERROR", new Object[] {}, locale)
				+ ": " + message);
		return status;
	}

	public tech.dev.eVoyageBackend.utils.Status LOGIN_FAIL(Locale locale) {
		status.setCode(tech.dev.eVoyageBackend.utils.StatusCode.FUNC_LOGIN_FAIL);
		status.setMessage(messageSource.getMessage("StatusMessage.FUNC_LOGIN_FAIL", new Object[] {}, locale));
		return status;
	}

	public tech.dev.eVoyageBackend.utils.Status DISALLOWED_OPERATION(String message, Locale locale) {
		status.setCode(tech.dev.eVoyageBackend.utils.StatusCode.FUNC_FILE_GENERATION_ERROR);
		status.setMessage(messageSource.getMessage("StatusMessage.FUNC_DISALLOWED_OPERATION", new Object[] {}, locale)
				+ ": " + message);
		return status;
	}

	public tech.dev.eVoyageBackend.utils.Status DATA_NOT_DELETABLE(String message, Locale locale) {
		status.setCode(tech.dev.eVoyageBackend.utils.StatusCode.FUNC_DATA_NOT_DELETABLE);
		status.setMessage(messageSource.getMessage("StatusMessage.FUNC_DATA_NOT_DELETABLE", new Object[] {}, locale)
				+ ": " + message);
		return status;
	}

	public tech.dev.eVoyageBackend.utils.Status DATA_DUPLICATE(String message, Locale locale) {
		status.setCode(tech.dev.eVoyageBackend.utils.StatusCode.FUNC_DATA_DUPLICATE);
		status.setMessage(messageSource.getMessage("StatusMessage.FUNC_DATA_DUPLICATE", new Object[] {}, locale) + ": "
				+ message);
		return status;
	}

	public tech.dev.eVoyageBackend.utils.Status USER_IS_LOCKED(String message, Locale locale) {
		status.setCode(tech.dev.eVoyageBackend.utils.StatusCode.FUNC_USER_IS_LOCKED);
		status.setMessage(messageSource.getMessage("StatusMessage.FUNC_USER_IS_LOCKED", new Object[] {}, locale) + ": "
				+ message);
		return status;
	}

	public tech.dev.eVoyageBackend.utils.Status SESSION_EXPIRED(String message, Locale locale) {
		status.setCode(tech.dev.eVoyageBackend.utils.StatusCode.FUNC_SESSION_EXPIRED);
		status.setMessage(
				messageSource.getMessage("StatusMessage.FUNC_SESSION_EXPIRED", new Object[] {}, locale) + "" + message);
		return status;
	}

	public tech.dev.eVoyageBackend.utils.Status NO_USER(String message, Locale locale) {
		tech.dev.eVoyageBackend.utils.Status status = new tech.dev.eVoyageBackend.utils.Status();
		status.setCode(tech.dev.eVoyageBackend.utils.StatusCode.FUNC_FIELD_EMPTY);
		status.setMessage(messageSource.getMessage("StatusMessage.FUNC_FIELD_EMPTY", new Object[] {}, locale) + message);
		return status;
	}

	public tech.dev.eVoyageBackend.utils.Status SESSION_EXPIRER(String message, Locale locale) {
		status.setCode(tech.dev.eVoyageBackend.utils.StatusCode.FUNC_SESSION_EXPIRED);
		status.setMessage("Session Expirer");
		return status;
	}

	public tech.dev.eVoyageBackend.utils.Status ECHEC_TRANSACTION(String message, Locale locale) {
		status.setCode(tech.dev.eVoyageBackend.utils.StatusCode.FUNC_ECHEC_TRANSACTION);
		status.setMessage("Echec de l'annulation de transaction");
		return status;
	}

	public tech.dev.eVoyageBackend.utils.Status ECHEC_GEL_TRANSACTION(String message, Locale locale) {
		status.setCode(tech.dev.eVoyageBackend.utils.StatusCode.FUNC_ECHEC_GEL_TRANSACTION);
		status.setMessage("Echec du gel de transaction ");
		return status;
	}

	public tech.dev.eVoyageBackend.utils.Status INVALID_VALUE(String message, Locale locale) {
		status.setCode(tech.dev.eVoyageBackend.utils.StatusCode.FUNC_INVALID_VALUE);
		status.setMessage(messageSource.getMessage("StatusMessage.FUNC_INVALID_VALUE", new Object[] {}, locale) + ": " + message);
		return status;
	}

	public tech.dev.eVoyageBackend.utils.Status TECH_INTERN_ERROR(String message, Locale locale) {
		status.setCode(tech.dev.eVoyageBackend.utils.StatusCode.TECH_INTERN_ERROR);
		status.setMessage(messageSource.getMessage("StatusMessage.TECH_INTERN_ERROR", new Object[] {}, locale) + ": " + message);
		return status;
	}

	public tech.dev.eVoyageBackend.utils.Status EXTRATION_ERROR(String message, Locale locale) {
		status.setCode(tech.dev.eVoyageBackend.utils.StatusCode.EXTRATION_ERROR);
		status.setMessage(messageSource.getMessage("StatusMessage.EXTRATION_ERROR", new Object[] {}, locale) + ": " + message);
		return status;
	}

	public Status INVALID_PIN(String message, Locale locale) {
		status.setCode(StatusCode.FUNC_INVALID_PIN);
		status.setMessage(messageSource.getMessage("StatusMessage.INVALID_PIN", new Object[] {}, locale) + ": " + message);
		return status;
	}

	public Status NO_PERMISSION(String message, Locale locale) {
		status.setCode(StatusCode.FUNC_NO_PERMISSION);
		status.setMessage(messageSource.getMessage("Accès non autorisé. Veuillez contacter l'administrateur.", new Object[] {}, locale) + ": " + message);
		return status;
	}

	public Status INVALID_USER_STATUS(String message, Locale locale) {
		status.setCode(StatusCode.FUNC_INVALID_USER_STATUS);
		status.setMessage(messageSource.getMessage("StatusMessage.FUNC_INVALID_USER_STATUS", new Object[] {}, locale) + ": " + message);
		return status;
	}

	public Status PERMISSION_DENIED(String message, Locale locale) {
		status.setCode(StatusCode.FUNC_PERMISSION_DENIED);
		status.setMessage(messageSource.getMessage("StatusMessage.FUNC_PERMISSION_DENIED", new Object[] {}, locale) + ": " + message);
		return status;
	}

	public Status INVALID_TOKEN(String message, Locale locale) {
		status.setCode(StatusCode.FUNC_INVALID_TOKEN);
		status.setMessage(messageSource.getMessage("StatusMessage.FUNC_INVALID_TOKEN", new Object[] {}, locale) + ": " + message);
		return status;
	}

	public Status UNAUTHORIZED_ACCESS(String message, Locale locale) {
		status.setCode(StatusCode.FUNC_UNAUTHORIZED_ACCESS);
		status.setMessage(messageSource.getMessage("StatusMessage.FUNC_UNAUTHORIZED_ACCESS", new Object[] {}, locale) + ": " + message);
		return status;
	}

	public Status INVALID_DATA(String message, Locale locale) {
		status.setCode(StatusCode.FUNC_INVALID_DATA);
		status.setMessage(messageSource.getMessage("StatusMessage.FUNC_INVALID_DATA", new Object[] {}, locale) + ": " + message);
		return status;
	}

	public Status SEATS_NOT_AVAILABLE(String message, Locale locale) {
		status.setCode(StatusCode.FUNC_SEATS_NOT_AVAILABLE);
		status.setMessage(messageSource.getMessage("StatusMessage.FUNC_INSUFFICIENT_SEATS", new Object[] {}, locale) + ": " + message);
		return status;
	}

	public Status PDF_GENERATION_FAILED(String message, Locale locale) {
		status.setCode(StatusCode.FUNC_PDF_GENERATION_FAILED);
		status.setMessage(messageSource.getMessage("StatusMessage.FUNC_PDF_GENERATION_FAILED", new Object[] {}, locale) + ": " + message);
		return status;
	}

	public Status QR_CODE_GENERATION_FAILED(String message, Locale locale) {
		status.setCode(StatusCode.FUNC_QR_CODE_GENERATION_FAILED);
		status.setMessage(messageSource.getMessage("StatusMessage.FUNC_QR_CODE_GENERATION_FAILED", new Object[] {}, locale) + ": " + message);
		return status;
	}

	public Status INSUFFICIENT_SEATS (String message, Locale locale) {
		status.setCode(StatusCode.FUNC_INSUFFICIENT_SEATS);
		status.setMessage(messageSource.getMessage("StatusMessage.FUNC_INSUFFICIENT_SEATS", new Object[] {}, locale) + ": " + message);
		return status;
	}

	public Status INVALID_FILE_TYPE (String message, Locale locale) {
		status.setCode(StatusCode.FUNC_INVALID_FILE_TYPE);
		status.setMessage(messageSource.getMessage("StatusMessage.FUNC_INVALID_FILE_TYPE", new Object[] {}, locale) + ": " + message);
		return status;
	}


	public Status FILE_TOO_LARGE (String message, Locale locale) {
		status.setCode(StatusCode.FILE_TOO_LARGE);
		status.setMessage(messageSource.getMessage("StatusMessage.FILE_TOO_LARGE", new Object[] {}, locale) + ": " + message);
		return status;
	}
public Status DATA_EXPIRED(String message, Locale locale) {
		status.setCode(StatusCode.FUNC_DATA_EXPIRED);
		status.setMessage(messageSource.getMessage("StatusMessage.FUNC_DATA_EXPIRED", new Object[] {}, locale) + ": " + message);
		return status;
	}

	public Status QR_CODE_DECODE_FAILED(String message, Locale locale) {
		status.setCode(StatusCode.FUNC_QR_CODE_DECODE_FAILED);
		status.setMessage(messageSource.getMessage("StatusMessage.FUNC_QR_CODE_DECODE_FAILED", new Object[] {}, locale) + ": " + message);
		return status;
	}







}