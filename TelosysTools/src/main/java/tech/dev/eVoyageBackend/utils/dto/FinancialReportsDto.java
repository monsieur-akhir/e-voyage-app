
/*
 * Java dto for entity table financial_reports 
 * Created on 2025-01-12 ( Time 17:39:53 )
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
import tech.dev.eVoyageBackend.utils.dto.customize._FinancialReportsDto;

/**
 * DTO for table "financial_reports"
 *
 * @author Geo
 */
@Data
@ToString
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder(alphabetic = true)
public class FinancialReportsDto extends _FinancialReportsDto implements Cloneable{

    private Integer    id                   ; // Primary Key

    private Integer    companyId            ;
    private Double     totalRevenue         ;
    private Integer    totalBookings        ;
    private Date       reportDate           ;
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
	private SearchParam<Double>   totalRevenueParam     ;                     
	private SearchParam<Integer>  totalBookingsParam    ;                     
	private SearchParam<Date>     reportDateParam       ;                     
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
    public FinancialReportsDto()
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
