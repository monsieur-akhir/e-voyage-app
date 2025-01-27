
/*
 * Java dto for entity table role_fonctionalite 
 * Created on 2023-08-30 ( Time 17:27:04 )
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
import tech.dev.eVoyageBackend.utils.dto.customize._RoleFonctionaliteDto;
 
 /**
  * DTO for table "role_fonctionalite"
  *
  * @author Geo
  */
 @Data
 @ToString
 @JsonInclude(Include.NON_NULL)
 @JsonPropertyOrder(alphabetic = true)
 public class RoleFonctionaliteDto extends _RoleFonctionaliteDto implements Cloneable{
 
     private Integer    id                   ; // Primary Key
 
     private Integer    roleId               ;
     private Integer    fonctionnaliteId     ;
     private String     createdAt            ;
     private String     updatedAt            ;
     private Integer    createdBy            ;
     private Integer    updatedBy            ;
     private Boolean    isDeleted            ;
 
     //----------------------------------------------------------------------
     // ENTITY LINKS FIELD ( RELATIONSHIP )
     //----------------------------------------------------------------------
     private String roleLibelle;
     private String fonctionaliteCode;
     private String fonctionaliteLibelle;
 
     // Search param
     private SearchParam<Integer>  idParam               ;                     
     private SearchParam<Integer>  roleIdParam           ;                     
     private SearchParam<Integer>  fonctionnaliteIdParam ;                     
     private SearchParam<String>   createdAtParam        ;                     
     private SearchParam<String>   updatedAtParam        ;                     
     private SearchParam<Integer>  createdByParam        ;                     
     private SearchParam<Integer>  updatedByParam        ;                     
     private SearchParam<Boolean>  isDeletedParam        ;                     
     private SearchParam<String>   roleLibelleParam      ;                     
     private SearchParam<String>   fonctionaliteCodeParam;                     
     private SearchParam<String>   fonctionaliteLibelleParam;                     
     /**
      * Default constructor
      */
     public RoleFonctionaliteDto()
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
 