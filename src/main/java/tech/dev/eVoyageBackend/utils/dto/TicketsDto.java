
/*
 * Java dto for entity table tickets 
 * Created on 2025-01-12 ( Time 17:39:58 )
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
import tech.dev.eVoyageBackend.utils.dto.customize._TicketsDto;

/**
 * DTO for table "tickets"
 *
 * @author Geo
 */
@Data
@ToString
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder(alphabetic = true)
public class TicketsDto extends _TicketsDto implements Cloneable{

    private Integer    id                   ; // Primary Key

    private Integer    bookingId            ;
    private Integer    companyId            ;
    private String     qrCode               ;
    private String     status               ;
    private Integer    scannedBy            ;
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
	private String usersName;

	// Search param
	private SearchParam<Integer>  idParam               ;                     
	private SearchParam<Integer>  bookingIdParam        ;                     
	private SearchParam<Integer>  companyIdParam        ;                     
	private SearchParam<String>   qrCodeParam           ;                     
	private SearchParam<String>   statusParam           ;                     
	private SearchParam<Integer>  scannedByParam        ;                     
	private SearchParam<String>   createdAtParam        ;                     
	private SearchParam<Integer>  createdByParam        ;                     
	private SearchParam<String>   updatedAtParam        ;                     
	private SearchParam<Integer>  updatedByParam        ;                     
	private SearchParam<Boolean>  isDeletedParam        ;                     
	private SearchParam<String>   deletedAtParam        ;                     
	private SearchParam<Integer>  deletedByParam        ;                     
	private SearchParam<String>   companiesNameParam    ;                     
	private SearchParam<String>   usersNameParam        ;                     
    /**
     * Default constructor
     */
    public TicketsDto()
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
