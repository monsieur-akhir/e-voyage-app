
/*
 * Java dto for entity table companies 
 * Created on 2025-01-12 ( Time 17:39:52 )
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
import tech.dev.eVoyageBackend.utils.dto.customize._CompaniesDto;

/**
 * DTO for table "companies"
 *
 * @author Geo
 */
@Data
@ToString
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder(alphabetic = true)
public class CompaniesDto extends _CompaniesDto implements Cloneable{

    private Integer    id                   ; // Primary Key

    private String     name                 ;
    private String     address              ;
    private String     contact              ;
    private String     licenseNumber        ;
    private Double     rating               ;
    private String     status               ;
	private String     logoPath				;
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

	// Search param
	private SearchParam<Integer>  idParam               ;                     
	private SearchParam<String>   nameParam             ;                     
	private SearchParam<String>   addressParam          ;                     
	private SearchParam<String>   contactParam          ;                     
	private SearchParam<String>   licenseNumberParam    ;                     
	private SearchParam<Double>   ratingParam           ;                     
	private SearchParam<String>   statusParam           ;
	private SearchParam<String>   logoPathParam         ;
	private SearchParam<String>   createdAtParam        ;                     
	private SearchParam<Integer>  createdByParam        ;                     
	private SearchParam<String>   updatedAtParam        ;                     
	private SearchParam<Integer>  updatedByParam        ;                     
	private SearchParam<Boolean>  isDeletedParam        ;                     
	private SearchParam<String>   deletedAtParam        ;                     
	private SearchParam<Integer>  deletedByParam        ;                     
    /**
     * Default constructor
     */
    public CompaniesDto()
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
