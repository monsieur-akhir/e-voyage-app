
/*
 * Java dto for entity table buses 
 * Created on 2025-01-12 ( Time 17:39:50 )
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
import tech.dev.eVoyageBackend.utils.dto.customize._BusesDto;

/**
 * DTO for table "buses"
 *
 * @author Geo
 */
@Data
@ToString
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder(alphabetic = true)
public class BusesDto extends _BusesDto implements Cloneable{

    private Integer    id                   ; // Primary Key

    private Integer    companyId            ;
    private Integer    routeId              ;
    private String     busNumber            ;
    private Integer    capacity             ;
	private Integer availableSeats;
	private String     seatNumbers          ;
    private String     status               ;
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

	// Search param
	private SearchParam<Integer>  idParam               ;                     
	private SearchParam<Integer>  companyIdParam        ;                     
	private SearchParam<Integer>  routeIdParam          ;                     
	private SearchParam<String>   busNumberParam        ;                     
	private SearchParam<Integer>  capacityParam         ;
	private SearchParam<Integer>  availableSeatsParam   ;
	private SearchParam<String>   seatNumbersParam      ;                     
	private SearchParam<String>   statusParam           ;                     
	private SearchParam<String>   createdAtParam        ;                     
	private SearchParam<Integer>  createdByParam        ;                     
	private SearchParam<String>   updatedAtParam        ;                     
	private SearchParam<Integer>  updatedByParam        ;                     
	private SearchParam<Boolean>  isDeletedParam        ;                     
	private SearchParam<String>   deletedAtParam        ;                     
	private SearchParam<Integer>  deletedByParam        ;                     
	private SearchParam<String>   companiesNameParam    ;                     
    /**
     * Default constructor
     */
    public BusesDto()
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
