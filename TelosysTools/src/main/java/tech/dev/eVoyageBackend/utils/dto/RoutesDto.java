
/*
 * Java dto for entity table routes 
 * Created on 2025-01-12 ( Time 17:39:56 )
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
import tech.dev.eVoyageBackend.utils.dto.customize._RoutesDto;

/**
 * DTO for table "routes"
 *
 * @author Geo
 */
@Data
@ToString
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder(alphabetic = true)
public class RoutesDto extends _RoutesDto implements Cloneable{

    private Integer    id                   ; // Primary Key

    private Integer    companyId            ;
    private Integer    originCityId         ;
    private Integer    destinationCityId    ;
    private Integer    duration             ;
    private Double     price                ;
    private String     status               ;
    private Double     rating               ;
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
	private String citiesName;
	private String citiesCode;
	private String companiesName;
	private String citiesName;
	private String citiesCode;

	// Search param
	private SearchParam<Integer>  idParam               ;                     
	private SearchParam<Integer>  companyIdParam        ;                     
	private SearchParam<Integer>  originCityIdParam     ;                     
	private SearchParam<Integer>  destinationCityIdParam;                     
	private SearchParam<Integer>  durationParam         ;                     
	private SearchParam<Double>   priceParam            ;                     
	private SearchParam<String>   statusParam           ;                     
	private SearchParam<Double>   ratingParam           ;                     
	private SearchParam<String>   createdAtParam        ;                     
	private SearchParam<Integer>  createdByParam        ;                     
	private SearchParam<String>   updatedAtParam        ;                     
	private SearchParam<Integer>  updatedByParam        ;                     
	private SearchParam<Boolean>  isDeletedParam        ;                     
	private SearchParam<String>   deletedAtParam        ;                     
	private SearchParam<Integer>  deletedByParam        ;                     
	private SearchParam<String>   citiesNameParam       ;                     
	private SearchParam<String>   citiesCodeParam       ;                     
	private SearchParam<String>   companiesNameParam    ;                     
	private SearchParam<String>   citiesNameParam       ;                     
	private SearchParam<String>   citiesCodeParam       ;                     
    /**
     * Default constructor
     */
    public RoutesDto()
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
