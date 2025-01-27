
/*
 * Java dto for entity table stations 
 * Created on 2025-01-21 ( Time 18:33:54 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2018 Geo. All Rights Reserved.
 */

package tech.dev.eVoyageBackend.utils.dto;

import java.util.Date;
import java.util.List;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.*;

import tech.dev.eVoyageBackend.utils.contract.*;
import tech.dev.eVoyageBackend.utils.dto.customize._StationsDto;

/**
 * DTO for table "stations"
 *
 * @author Geo
 */
@Data
@ToString
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder(alphabetic = true)
public class StationsDto extends _StationsDto implements Cloneable{

    private Integer    id                   ; // Primary Key

    private String     name                 ;
    private String     address              ;
    private Integer    cityId               ;
    private Integer    companyId            ;
    private Boolean    isAvailable          ;
	private String     createdAt            ;
    private Integer    createdBy            ;
	private String     updatedAt            ;
    private Integer    updatedBy            ;
    private Boolean    isDeleted            ;
	private String     deletedAt            ;
    private Integer    deletedBy            ;

    //----------------------------------------------------------------------
    // ENTITY LINKS FIELD ( RELATIONSHIP )
    //----------------------------------------------------------------------
	private String companiesName;
	private String citiesName;

	// Search param
	private SearchParam<Integer>  idParam               ;                     
	private SearchParam<String>   nameParam             ;                     
	private SearchParam<String>   addressParam          ;                     
	private SearchParam<Integer>  cityIdParam           ;                     
	private SearchParam<Integer>  companyIdParam        ;                     
	private SearchParam<Boolean>  isAvailableParam      ;                     
	private SearchParam<String>   createdAtParam        ;                     
	private SearchParam<Integer>  createdByParam        ;                     
	private SearchParam<String>   updatedAtParam        ;                     
	private SearchParam<Integer>  updatedByParam        ;                     
	private SearchParam<Boolean>  isDeletedParam        ;                     
	private SearchParam<String>   deletedAtParam        ;                     
	private SearchParam<Integer>  deletedByParam        ;                     
	private SearchParam<String>   companiesNameParam    ;                     
	private SearchParam<String>   citiesNameParam       ;                     
    /**
     * Default constructor
     */
    public StationsDto()
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
