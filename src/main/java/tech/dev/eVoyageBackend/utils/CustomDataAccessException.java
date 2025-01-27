package tech.dev.eVoyageBackend.utils;

import org.springframework.dao.DataAccessException;

import lombok.Getter;


@Getter
public class CustomDataAccessException extends DataAccessException {
    private static final long serialVersionUID = 1L;
	private final String errorCode;
    private final String additionalInfo;

    public CustomDataAccessException(String msg, String errorCode, String additionalInfo, Throwable cause) {
        super(msg, cause);
        this.errorCode = errorCode;
        this.additionalInfo = additionalInfo;
    }

}

