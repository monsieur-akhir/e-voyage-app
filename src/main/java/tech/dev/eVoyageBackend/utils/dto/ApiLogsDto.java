
/*
 * Java dto for entity table api_logs 
 * Created on 2024-11-25 ( Time 15:12:19 )
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
import tech.dev.eVoyageBackend.utils.dto.customize._ApiLogsDto;

/**
 * DTO for table "api_logs"
 *
 * @author Geo
 */
@Data
@ToString
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder(alphabetic = true)
public class ApiLogsDto extends _ApiLogsDto implements Cloneable{

    private Long       id                   ; // Primary Key

	private String     requestTime          ;
    private String     requestUrl           ;
    private String     requestMethod        ;
    private String     requestHeaders       ;
    private String     requestBody          ;
	private String     responseTime         ;
    private Integer    responseStatus       ;
    private String     responseBody         ;
    private String     createdBy            ;
	private String     createdDate          ;
    private String     lastModifiedBy       ;
	private String     lastModifiedDate     ;
    private String     status               ;

    //----------------------------------------------------------------------
    // ENTITY LINKS FIELD ( RELATIONSHIP )
    //----------------------------------------------------------------------

	// Search param
	private SearchParam<Long>     idParam               ;                     
	private SearchParam<String>   requestTimeParam      ;                     
	private SearchParam<String>   requestUrlParam       ;                     
	private SearchParam<String>   requestMethodParam    ;                     
	private SearchParam<String>   requestHeadersParam   ;                     
	private SearchParam<String>   requestBodyParam      ;                     
	private SearchParam<String>   responseTimeParam     ;                     
	private SearchParam<Integer>  responseStatusParam   ;                     
	private SearchParam<String>   responseBodyParam     ;                     
	private SearchParam<String>   createdByParam        ;                     
	private SearchParam<String>   createdDateParam      ;                     
	private SearchParam<String>   lastModifiedByParam   ;                     
	private SearchParam<String>   lastModifiedDateParam ;                     
	private SearchParam<String>   statusParam           ;                     
    /**
     * Default constructor
     */
    public ApiLogsDto()
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
