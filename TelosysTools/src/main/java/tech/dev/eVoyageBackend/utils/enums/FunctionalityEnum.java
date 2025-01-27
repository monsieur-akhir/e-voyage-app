/*
 * Created on 2018-04-14 ( Time 21:52:32 )
 * Generator tool : Telosys Tools Generator ( version 3.0.0 )
 * Copyright 2018 Geo. All Rights Reserved.
 */

package tech.dev.eVoyageBackend.utils.enums;

/**
 * 
 * @author Geo
 *
 */
 public enum FunctionalityEnum {
 	DEFAULT("DEFAULT"),

	// CITIES
	VIEW_CITIES("VIEW_CITIES"),	
	CREATE_CITIES("CREATE_CITIES"),
	UPDATE_CITIES("UPDATE_CITIES"),
	DELETE_CITIES("DELETE_CITIES"),
	// DEPARTS
	VIEW_DEPARTS("VIEW_DEPARTS"),	
	CREATE_DEPARTS("CREATE_DEPARTS"),
	UPDATE_DEPARTS("UPDATE_DEPARTS"),
	DELETE_DEPARTS("DELETE_DEPARTS"),
	// DISTRICTS
	VIEW_DISTRICTS("VIEW_DISTRICTS"),	
	CREATE_DISTRICTS("CREATE_DISTRICTS"),
	UPDATE_DISTRICTS("UPDATE_DISTRICTS"),
	DELETE_DISTRICTS("DELETE_DISTRICTS"),
	// STATIONS
	VIEW_STATIONS("VIEW_STATIONS"),	
	CREATE_STATIONS("CREATE_STATIONS"),
	UPDATE_STATIONS("UPDATE_STATIONS"),
	DELETE_STATIONS("DELETE_STATIONS");

	private final String value;
 	public String getValue() {
 		return value;
 	}
 	private FunctionalityEnum(String value) {
 		this.value = value;
 	}
}
