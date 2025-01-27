
/*
 * Java dto for entity table api_user 
 * Created on 2024-11-16 ( Time 14:33:28 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2018 Geo. All Rights Reserved.
 */

package tech.dev.eVoyageBackend.utils.dto;

import java.util.Date;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.*;
import tech.dev.eVoyageBackend.utils.contract.*;
import tech.dev.eVoyageBackend.utils.dto.customize._ApiUserDto;

/**
 * DTO for table "api_user"
 *
 * @author Geo
 */
@Data
@ToString
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder(alphabetic = true)
public class ApiUserDto extends _ApiUserDto implements Cloneable{

    private Integer    id                   ; // Primary Key

	private String     createdAt            ;
    private Integer    createdBy            ;
	private String     dateSendCodeOtpAt    ;
    private String     email                ;
	private String     firstConnection      ;
    private Boolean    isActive             ;
    private Boolean    isConnected          ;
    private Boolean    isDefaultPassword    ;
    private Boolean    isDeleted            ;
    private Boolean    isLocked             ;
    private Boolean    isValidPassCode      ;
    private String     isValidToken         ;
	private String     lastActivityDate     ;
	private String     lastConnectionDate   ;
	private String     lastLockDate         ;
    private String     login                ;
    private Integer    loginAttempts        ;
    private String     otpCode              ;
    private String     passCode             ;
	private String     passCodeCreatedAt    ;
	private String     passCodeExpireAt     ;
    private String     password             ;
    private String     searchString         ;
    private String     telephone            ;
    private String     token                ;
	private String     tokenCreatedAt       ;
	private String     tokenExpireAt        ;
	private String     updatedAt            ;
    private Integer    updatedBy            ;
    private Integer    roleId               ;
    private String     type                 ;

    //----------------------------------------------------------------------
    // ENTITY LINKS FIELD ( RELATIONSHIP )
    //----------------------------------------------------------------------

	// Search param
	private SearchParam<Integer>  idParam               ;                     
	private SearchParam<String>   createdAtParam        ;                     
	private SearchParam<Integer>  createdByParam        ;                     
	private SearchParam<String>   dateSendCodeOtpAtParam;                     
	private SearchParam<String>   emailParam            ;                     
	private SearchParam<String>   firstConnectionParam  ;                     
	private SearchParam<Boolean>  isActiveParam         ;                     
	private SearchParam<Boolean>  isConnectedParam      ;                     
	private SearchParam<Boolean>  isDefaultPasswordParam;                     
	private SearchParam<Boolean>  isDeletedParam        ;                     
	private SearchParam<Boolean>  isLockedParam         ;                     
	private SearchParam<Boolean>  isValidPassCodeParam  ;                     
	private SearchParam<String>   isValidTokenParam     ;                     
	private SearchParam<String>   lastActivityDateParam ;                     
	private SearchParam<String>   lastConnectionDateParam;                     
	private SearchParam<String>   lastLockDateParam     ;                     
	private SearchParam<String>   loginParam            ;                     
	private SearchParam<Integer>  loginAttemptsParam    ;                     
	private SearchParam<String>   otpCodeParam          ;                     
	private SearchParam<String>   passCodeParam         ;                     
	private SearchParam<String>   passCodeCreatedAtParam;                     
	private SearchParam<String>   passCodeExpireAtParam ;                     
	private SearchParam<String>   passwordParam         ;                     
	private SearchParam<String>   searchStringParam     ;                     
	private SearchParam<String>   telephoneParam        ;                     
	private SearchParam<String>   tokenParam            ;                     
	private SearchParam<String>   tokenCreatedAtParam   ;                     
	private SearchParam<String>   tokenExpireAtParam    ;                     
	private SearchParam<String>   updatedAtParam        ;                     
	private SearchParam<Integer>  updatedByParam        ;                     
	private SearchParam<Integer>  roleIdParam           ;                     
	private SearchParam<String>   typeParam             ;                     
    /**
     * Default constructor
     */
    public ApiUserDto()
    {
        super();
    }
    
	//----------------------------------------------------------------------
    // clone METHOD
    //----------------------------------------------------------------------
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
