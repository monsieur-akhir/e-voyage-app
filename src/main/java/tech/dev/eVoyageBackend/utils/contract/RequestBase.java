/*
 * Created on 2024-02-19 ( Time 10:54:59 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2018 Geo. All Rights Reserved.
 */

package tech.dev.eVoyageBackend.utils.contract;

import java.util.List;
import java.util.Map;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Request Base
 * 
 * @author Geo
 *
 */

@Data
@ToString
@NoArgsConstructor
public class RequestBase {
	protected String		sessionUser;
	protected Integer		size;
	protected Integer		index;
	protected String		lang;
	protected String		businessLineCode;
	protected String		caseEngine;
	protected Boolean		isAnd;
	protected Integer		user;
	protected Boolean 		isSimpleLoading;

	protected Boolean       hierarchyFormat;

	protected Boolean isLdapUser;
	
	protected String  token;
	protected String refreshToken;
	
	
	
	
	
	protected String        msisdn;
	protected String        msisdn2;
	protected String        points;
	protected Integer       idProduit;
	protected String        currency;;
	
	protected String     subscriberNum        ;
	protected String     marchantNum          ;
	protected String     amount               ;
	protected String     type               ;
	protected String     txnid                ;
	protected String     date                ;

	protected String     numeroMarchand          ;
	
	protected List<String>     numeroMarchandsLite          ;
	protected Map<String, String> blackListNumberWithMotif;
    
	private List<String> blackListNumber;


	protected String     motif               ;
	
	protected String pricePlanCode;
	
	  protected String 	moduleCode;


}