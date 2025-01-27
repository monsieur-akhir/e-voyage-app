
/*
 * Java dto for entity table bookings 
 * Created on 2025-01-21 ( Time 19:33:16 )
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
import tech.dev.eVoyageBackend.utils.dto.customize._BookingsDto;

/**
 * DTO for table "bookings"
 *
 * @author Geo
 */
@Data
@ToString
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder(alphabetic = true)
public class BookingsDto extends _BookingsDto implements Cloneable{

    private Integer    id                   ; // Primary Key

    private String     seatNumber           ;
    private Integer    numberOfSeats        ;
    private String     status               ;
	private String     createdAt            ;
    private Integer    createdBy            ;
	private String     updatedAt            ;
    private Integer    updatedBy            ;
    private Boolean    isDeleted            ;
	private String     deletedAt            ;
    private Integer    deletedBy            ;
    private Integer    busId                ;
    private Integer    companyId            ;
    private Integer    departureId          ;
    private Integer    userId               ;
    private Integer    originStationId      ;
    private Integer    destinationStationId ;

    //----------------------------------------------------------------------
    // ENTITY LINKS FIELD ( RELATIONSHIP )
    //----------------------------------------------------------------------
	private String usersName;
	private String usersPhone;
	private String usersEmail;
	private String stationsDepartureName;
	private String stationsArrivalName;
	private String companiesName;
	private String villeArrivee;
	private String villeDepart;
	private String numeroCar;

	// Search param
	private SearchParam<Integer>  idParam               ;                     
	private SearchParam<String>   seatNumberParam       ;                     
	private SearchParam<Integer>  numberOfSeatsParam    ;                     
	private SearchParam<String>   statusParam           ;                     
	private SearchParam<String>   createdAtParam        ;                     
	private SearchParam<Integer>  createdByParam        ;                     
	private SearchParam<String>   updatedAtParam        ;                     
	private SearchParam<Integer>  updatedByParam        ;                     
	private SearchParam<Boolean>  isDeletedParam        ;                     
	private SearchParam<String>   deletedAtParam        ;                     
	private SearchParam<Integer>  deletedByParam        ;                     
	private SearchParam<Integer>  busIdParam            ;                     
	private SearchParam<Integer>  companyIdParam        ;                     
	private SearchParam<Integer>  departureIdParam      ;                     
	private SearchParam<Integer>  userIdParam           ;                     
	private SearchParam<Integer>  originStationIdParam  ;                     
	private SearchParam<Integer>  destinationStationIdParam;                     
	private SearchParam<String>   usersNameParam        ;
	private SearchParam<String>   usersPhoneParam       ;
	private SearchParam<String>   usersEmailParam       ;
	private SearchParam<String>   stationsNameDepartureParam     ;
	private SearchParam<String>   stationsNameArrivalParam    ;
	private SearchParam<String>   companiesNameParam    ;
	private SearchParam<String>   villeArriveeParam      ;
	private SearchParam<String>   villeDepartParam       ;
	private SearchParam<String>   numeroCarParam         ;
    /**
     * Default constructor
     */
    public BookingsDto()
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
