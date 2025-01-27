
/*
 * Java dto for entity table payments 
 * Created on 2025-01-12 ( Time 17:39:54 )
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
import tech.dev.eVoyageBackend.utils.dto.customize._PaymentsDto;

/**
 * DTO for table "payments"
 *
 * @author Geo
 */
@Data
@ToString
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder(alphabetic = true)
public class PaymentsDto extends _PaymentsDto implements Cloneable{

    private Integer    id                   ; // Primary Key

    private Integer    bookingId            ;
    private Integer    companyId            ;
    private Double     amount               ;
    private String     paymentMethod        ;
    private String     status               ;
	private String     reference            ;
	private String     transactionId        ;
	private String     numeroTel          ;
	private String     cardCredit          ;
	private String     cardType          ;
	private String     cardExpire          ;
	private String     cardCvv          ;
	private String     cardHolder          ;
	private String    cardIssuer          ;
	private String     cardCountry          ;
	private String 		currency;

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
	private SearchParam<Integer>  bookingIdParam        ;                     
	private SearchParam<Integer>  companyIdParam        ;                     
	private SearchParam<Double>   amountParam           ;                     
	private SearchParam<String>   paymentMethodParam    ;                     
	private SearchParam<String>   statusParam           ;
	private SearchParam<String>   referenceParam        ;
	private SearchParam<String>   transactionIdParam    ;
	private SearchParam<String>   numeroTelParam        ;
	private SearchParam<String>   cardCreditParam       ;
	private SearchParam<String>   cardTypeParam         ;
	private SearchParam<String>   cardExpireParam       ;
	private SearchParam<String>   cardCvvParam          ;
	private SearchParam<String>   cardHolderParam       ;
	private SearchParam<String>   cardIssuerParam       ;
	private SearchParam<String>   cardCountryParam      ;
	private SearchParam<String>   currencyParam         ;
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
    public PaymentsDto()
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
