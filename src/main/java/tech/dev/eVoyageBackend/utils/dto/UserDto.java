
/*
 * Java dto for entity table user 
 * Created on 2023-08-30 ( Time 17:27:05 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2018 Geo. All Rights Reserved.
 */

 package tech.dev.eVoyageBackend.utils.dto;

 import com.fasterxml.jackson.annotation.JsonInclude;
 import com.fasterxml.jackson.annotation.JsonInclude.Include;
 import com.fasterxml.jackson.annotation.JsonPropertyOrder;
 
 import lombok.Data;
 import lombok.ToString;
import tech.dev.eVoyageBackend.utils.contract.SearchParam;
import tech.dev.eVoyageBackend.utils.dto.customize._UserDto;
 
 /**
  * DTO for table "user"
  *
  * @author Geo
  */
 @Data
 @ToString
 @JsonInclude(Include.NON_NULL)
 @JsonPropertyOrder(alphabetic = true)
 public class UserDto extends _UserDto implements Cloneable{
 
	 private Integer    id                   ; // Primary Key
 
	 private String     login                ;
	 private String     password             ;
	 private String     firstName            ;
	 private String     lastName             ;
	 private String     fonction             ;
	 private String     lieuFonction         ;
	 private String     email                ;
	 private String     bornOn               ;
	 private String     otpCode               ;
	 private String     dateSendCodeOtpAt    ;
	 private String     firstConnection      ;
	 
	 private String     telephone            ;
	 private Integer    roleId               ;
	 private Boolean    isDefaultPassword    ;
	 private Boolean    isConnected          ;
	 private Boolean    isLocked             ;
	 private String     lastConnectionDate   ;
	 private String     lastLockDate         ;
	 private String     passCode             ;
	 private Boolean    isValidPassCode      ;
	 private String     passCodeExpireAt     ;
	 private String     passCodeCreatedAt    ;
	 
	 private String     token                ;
	 private String     isValidToken         ;
	 private String     tokenCreatedAt       ;
	 private String     tokenExpireAt        ;
	 private String     createdAt            ;
	 private Integer    createdBy            ;
	 private String     updatedAt            ;
	 private Integer    updatedBy            ;
	 private Integer    loginAttempts            ;
	 private Boolean    isActive             ;
	 private String     searchString         ;
	 private Boolean    isDeleted            ;
	 private Boolean isLdapUser;
 
	 //----------------------------------------------------------------------
	 // ENTITY LINKS FIELD ( RELATIONSHIP )
	 //----------------------------------------------------------------------
	 private String roleLibelle;
 
	 // Search param
	 private SearchParam<Integer>  idParam               ;                     
	 private SearchParam<String>   loginParam            ;                     
	 private SearchParam<String>   passwordParam         ;                     
	 private SearchParam<String>   firstNameParam        ;                     
	 private SearchParam<String>   lastNameParam         ;                     
	 private SearchParam<String>   fonctionParam         ;                     
	 private SearchParam<String>   lieuFonctionParam     ;                     
	 private SearchParam<String>   emailParam            ;                     
	 private SearchParam<String>   bornOnParam           ;                     
	 private SearchParam<String>   telephoneParam        ;           
	 private SearchParam<Integer>  roleIdParam           ;                     
	 private SearchParam<Boolean>  isDefaultPasswordParam;                     
	 private SearchParam<Boolean>  isConnectedParam      ;                     
	 private SearchParam<Boolean>  isLockedParam         ;                     
	 private SearchParam<String>   lastConnectionDateParam;      
	 private SearchParam<String>   firstConnectionParam  ;                     
 
	 private SearchParam<String>   lastLockDateParam     ;                     
	 private SearchParam<String>   passCodeParam         ;                     
	 private SearchParam<Boolean>  isValidPassCodeParam  ;                     
	 private SearchParam<String>   passCodeExpireAtParam ;                     
	 private SearchParam<String>   passCodeCreatedAtParam;                     
	 private SearchParam<String>   tokenParam            ;         
	 private SearchParam<String>   otpCodeParam            ;    
	 private SearchParam<String>   dateSendCodeOtpAtParam    ; 
	 private SearchParam<String>   isValidTokenParam     ;                     
	 private SearchParam<String>   tokenCreatedAtParam   ;                     
	 private SearchParam<String>   tokenExpireAtParam    ;     
  
	 private SearchParam<String>   createdAtParam        ;                     
	 private SearchParam<Integer>  createdByParam        ;                     
	 private SearchParam<String>   updatedAtParam        ;                     
	 private SearchParam<Integer>  updatedByParam        ;   
	 private SearchParam<Integer>  loginAttemptsParam        ;        
	 
	 private SearchParam<Boolean>  isActiveParam         ;                     
	 private SearchParam<String>   searchStringParam     ;                     
	 private SearchParam<Boolean>  isDeletedParam        ;                     
	 private SearchParam<String>   roleLibelleParam      ;                     
	 /**
	  * Default constructor
	  */
	 public UserDto()
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
 