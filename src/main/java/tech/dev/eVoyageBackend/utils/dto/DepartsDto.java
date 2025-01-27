
/*
 * Java dto for entity table departs 
 * Created on 2025-01-21 ( Time 18:33:54 )
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
import tech.dev.eVoyageBackend.utils.dto.customize._DepartsDto;

/**
 * DTO for table "departs"
 *
 * @author Geo
 */
@Data
@ToString
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder(alphabetic = true)
public class DepartsDto extends _DepartsDto implements Cloneable{

    private Integer    id                   ; // Primary Key

    private Integer    busId                ;
    private Integer    originStationId      ;
    private Integer    destinationStationId ;
    private Date       departureDate        ;
    private Date       departureTime        ;
    private Double     price                ;
    private Integer    maxSeats             ;
    private Integer    availableSeats       ;
	private Double     rating               ;
	private Integer    duration             ;
    private Boolean    isActive             ;
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
	private String stationsNameDeparture;
	private String stationsNameArrival;
	private String companiesName;
	private String villeDepart;
	private String villeArrivee;
	private String busNumber;
	// Search param
	private SearchParam<Integer>  idParam               ;                     
	private SearchParam<Integer>  busIdParam            ;                     
	private SearchParam<Integer>  originStationIdParam  ;                     
	private SearchParam<Integer>  destinationStationIdParam;                     
	private SearchParam<Date>     departureDateParam    ;                     
	private SearchParam<Date>     departureTimeParam    ;                     
	private SearchParam<Double>   priceParam            ;                     
	private SearchParam<Integer>  maxSeatsParam         ;                     
	private SearchParam<Integer>  availableSeatsParam   ;
	private SearchParam<Double>   ratingParam           ;
	private SearchParam<Integer>  durationParam         ;
	private SearchParam<Boolean>  isActiveParam         ;                     
	private SearchParam<String>   createdAtParam        ;                     
	private SearchParam<Integer>  createdByParam        ;                     
	private SearchParam<String>   updatedAtParam        ;                     
	private SearchParam<Integer>  updatedByParam        ;                     
	private SearchParam<Boolean>  isDeletedParam        ;                     
	private SearchParam<String>   deletedAtParam        ;                     
	private SearchParam<Integer>  deletedByParam        ;                     
	private SearchParam<String>   stationsNameDepartureParam     ;
	private SearchParam<String>   stationsNameArrivalParam     ;
	private SearchParam<String>   companiesNameParam     ;
	private SearchParam<String>   villeDepartParam     ;
	private SearchParam<String>   villeArriveeParam     ;
	private SearchParam<String>   busNumberParam     ;
    /**
     * Default constructor
     */
    public DepartsDto()
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
