
/*
 * Java dto for entity table user 
 * Created on 2024-08-20 ( Time 10:24:22 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2018 Geo. All Rights Reserved.
 */

package tech.dev.eVoyageBackend.utils.dto.customize;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;
import lombok.ToString;
import tech.dev.eVoyageBackend.utils.contract.*;
import tech.dev.eVoyageBackend.utils.dto.FonctionaliteDto;
import tech.dev.eVoyageBackend.utils.dto.RoleFonctionaliteDto;

/**
 * DTO customize for table "user"
 * 
 * @author Geo
 *
 */
@Data
@ToString
@JsonPropertyOrder(alphabetic = true)
@JsonInclude(Include.NON_NULL)
public class _UserDto {
    
//	private String otpCode;
	private Integer otpCodeCreatedAt;
	private Integer otpCodeValidityMinutes;
	private LocalDateTime otpCodeSentAt;
	
    private List<RoleFonctionaliteDto> datasFonctionalites;
	private List<FonctionaliteDto> datasFonctionalite;
	private List<RoleFonctionaliteDto> fonctionnaliteData;
	private String newPassword;
    
}
