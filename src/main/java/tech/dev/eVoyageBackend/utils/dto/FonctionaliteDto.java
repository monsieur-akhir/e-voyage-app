
/*
 * Java dto for entity table fonctionalite 
 * Created on 2023-08-30 ( Time 17:27:02 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2018 Geo. All Rights Reserved.
 */

 package tech.dev.eVoyageBackend.utils.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;
import lombok.ToString;
import tech.dev.eVoyageBackend.utils.contract.SearchParam;
import tech.dev.eVoyageBackend.utils.dto.customize._FonctionaliteDto;

/**
 * DTO for table "fonctionalite"
 *
 * @author Geo
 */
@Data
@ToString
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder(alphabetic = true)
public class FonctionaliteDto extends _FonctionaliteDto implements Cloneable{

    private Integer    id                   ; // Primary Key

    private Integer    parentId             ;
    private String     code                 ;
    private String     libelle              ;
    private String     isAvailableForUser   ;
	private String     createdAt            ;
    private Integer    createdBy            ;
	private String     updatedAt            ;
    private Integer    updatedBy            ;
    private Boolean    isDeleted            ;

    //----------------------------------------------------------------------
    // ENTITY LINKS FIELD ( RELATIONSHIP )
    //----------------------------------------------------------------------
	private String fonctionaliteCode;
	private String fonctionaliteLibelle;

	// Search param
	private SearchParam<Integer>  idParam               ;                     
	private SearchParam<Integer>  parentIdParam         ;                     
	private SearchParam<String>   codeParam             ;                     
	private SearchParam<String>   libelleParam          ;                     
	private SearchParam<String>   isAvailableForUserParam;                     
	private SearchParam<String>   createdAtParam        ;                     
	private SearchParam<Integer>  createdByParam        ;                     
	private SearchParam<String>   updatedAtParam        ;                     
	private SearchParam<Integer>  updatedByParam        ;                     
	private SearchParam<Boolean>  isDeletedParam        ;                     
	private SearchParam<String>   fonctionaliteCodeParam;                     
	private SearchParam<String>   fonctionaliteLibelleParam;                     
    /**
     * Default constructor
     */
    public FonctionaliteDto()
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
