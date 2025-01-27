
/*
 * Java dto for entity table role_permissions 
 * Created on 2025-01-12 ( Time 17:39:56 )
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
import tech.dev.eVoyageBackend.utils.dto.customize._RolePermissionsDto;

/**
 * DTO for table "role_permissions"
 *
 * @author Geo
 */
@Data
@ToString
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder(alphabetic = true)
public class RolePermissionsDto extends _RolePermissionsDto implements Cloneable{

    private Integer    id                   ; // Primary Key

    private Integer    roleId               ;
    private Integer    permissionId         ;
    private String     status               ;
	private String     createdAt            ;
    private Integer    createdBy            ;
	private String     updatedAt            ;
    private Integer    updatedBy            ;

    //----------------------------------------------------------------------
    // ENTITY LINKS FIELD ( RELATIONSHIP )
    //----------------------------------------------------------------------
	private String rolesName;
	private String permissionsName;

	// Search param
	private SearchParam<Integer>  idParam               ;                     
	private SearchParam<Integer>  roleIdParam           ;                     
	private SearchParam<Integer>  permissionIdParam     ;                     
	private SearchParam<String>   statusParam           ;                     
	private SearchParam<String>   createdAtParam        ;                     
	private SearchParam<Integer>  createdByParam        ;                     
	private SearchParam<String>   updatedAtParam        ;                     
	private SearchParam<Integer>  updatedByParam        ;                     
	private SearchParam<String>   rolesNameParam        ;                     
	private SearchParam<String>   permissionsNameParam  ;                     
    /**
     * Default constructor
     */
    public RolePermissionsDto()
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
