
/*
 * Java dto for entity table notifications 
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
import tech.dev.eVoyageBackend.utils.dto.customize._NotificationsDto;

/**
 * DTO for table "notifications"
 *
 * @author Geo
 */
@Data
@ToString
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder(alphabetic = true)
public class NotificationsDto extends _NotificationsDto implements Cloneable{

    private Integer    id                   ; // Primary Key

    private Integer    companyId            ;
    private Integer    userId               ;
    private String     type                 ;
    private String     content              ;
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
	private String usersName;
	private String companiesName;

	// Search param
	private SearchParam<Integer>  idParam               ;                     
	private SearchParam<Integer>  companyIdParam        ;                     
	private SearchParam<Integer>  userIdParam           ;                     
	private SearchParam<String>   typeParam             ;                     
	private SearchParam<String>   contentParam          ;                     
	private SearchParam<String>   statusParam           ;                     
	private SearchParam<String>   createdAtParam        ;                     
	private SearchParam<Integer>  createdByParam        ;                     
	private SearchParam<String>   updatedAtParam        ;                     
	private SearchParam<Integer>  updatedByParam        ;                     
	private SearchParam<Boolean>  isDeletedParam        ;                     
	private SearchParam<String>   deletedAtParam        ;                     
	private SearchParam<Integer>  deletedByParam        ;                     
	private SearchParam<String>   usersNameParam        ;                     
	private SearchParam<String>   companiesNameParam    ;                     
    /**
     * Default constructor
     */
    public NotificationsDto()
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
