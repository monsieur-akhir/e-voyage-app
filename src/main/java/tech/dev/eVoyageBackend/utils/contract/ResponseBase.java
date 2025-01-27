
/*
 * Created on 2024-02-19 ( Time 10:54:59 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2018 Geo. All Rights Reserved.
 */

package tech.dev.eVoyageBackend.utils.contract;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import tech.dev.eVoyageBackend.utils.Status;

import org.apache.poi.ss.formula.functions.T;

import java.util.List;

/**
 * Response Base
 * 
 * @author Geo
 *
 */
@Data
@ToString
@NoArgsConstructor
public class ResponseBase {

	protected Status	status = new Status();
	protected boolean	hasError;
	protected String	sessionUser;
	protected Long		count;
	protected String    message;

	protected String	actionEffectue;
	protected Integer	countLite;
	protected List<T>	datasLite;
	
	protected Integer categoryId;
	protected String categoryLibelle;
	protected String filePath;
	
	protected Long		sessionUserExpire;
	
		
	
}
