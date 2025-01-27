
/*
 * Java dto for entity table logs 
 * Created on 2024-08-20 ( Time 10:24:21 )
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
import tech.dev.eVoyageBackend.utils.dto.customize._LogsDto;

/**
 * DTO for table "logs"
 *
 * @author Geo
 */
@Data
@ToString
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder(alphabetic = true)
public class LogsDto extends _LogsDto implements Cloneable{

    private Integer    id                   ; // Primary Key

    private String     actionService        ;
	private String     createdAt            ;
    private Integer    createdBy            ;
	private String     date                 ;
    private Integer    idStatus             ;
    private String     ipadress             ;
    private Boolean    isConnexion          ;
    private Boolean    isDeleted            ;
    private String     login                ;
    private String     machine              ;
    private String     nom                  ;
    private String     prenom               ;
    private String     request              ;
    private String     response             ;
    private String     searchString         ;
    private String     statusConnection     ;
	private String     updatedAt            ;
    private Integer    updatedBy            ;
    private String     uri                  ;

    //----------------------------------------------------------------------
    // ENTITY LINKS FIELD ( RELATIONSHIP )
    //----------------------------------------------------------------------

	// Search param
	private SearchParam<Integer>  idParam               ;                     
	private SearchParam<String>   actionServiceParam    ;                     
	private SearchParam<String>   createdAtParam        ;                     
	private SearchParam<Integer>  createdByParam        ;                     
	private SearchParam<String>   dateParam             ;                     
	private SearchParam<Integer>  idStatusParam         ;                     
	private SearchParam<String>   ipadressParam         ;                     
	private SearchParam<Boolean>  isConnexionParam      ;                     
	private SearchParam<Boolean>  isDeletedParam        ;                     
	private SearchParam<String>   loginParam            ;                     
	private SearchParam<String>   machineParam          ;                     
	private SearchParam<String>   nomParam              ;                     
	private SearchParam<String>   prenomParam           ;                     
	private SearchParam<String>   requestParam          ;                     
	private SearchParam<String>   responseParam         ;                     
	private SearchParam<String>   searchStringParam     ;                     
	private SearchParam<String>   statusConnectionParam ;                     
	private SearchParam<String>   updatedAtParam        ;                     
	private SearchParam<Integer>  updatedByParam        ;                     
	private SearchParam<String>   uriParam              ;                     
    /**
     * Default constructor
     */
    public LogsDto()
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
