
/*
 * Java dto for entity table users 
 * Created on 2025-01-12 ( Time 17:40:01 )
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
import tech.dev.eVoyageBackend.utils.dto.customize._UsersDto;

/**
 * DTO for table "users"
 *
 * @author Geo
 */
@Data
@ToString
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder(alphabetic = true)
public class UsersDto extends _UsersDto implements Cloneable{

    private Integer    id                   ; // Primary Key

    private Integer    companyId            ;
    private String     name                 ;
    private String     email                ;
    private String     phone                ;
    private String     password             ;
    private Integer    roleId               ;
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
	private SearchParam<String>   nameParam             ;                     
	private SearchParam<String>   emailParam            ;                     
	private SearchParam<String>   phoneParam            ;                     
	private SearchParam<String>   passwordParam         ;                     
	private SearchParam<Integer>  roleIdParam           ;                     
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
    public UsersDto()
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
