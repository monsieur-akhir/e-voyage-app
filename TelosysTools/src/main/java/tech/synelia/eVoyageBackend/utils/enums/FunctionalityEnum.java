/*
 * Created on 2018-04-14 ( Time 21:52:32 )
 * Generator tool : Telosys Tools Generator ( version 3.0.0 )
 * Copyright 2018 Geo. All Rights Reserved.
 */

package tech.synelia.eVoyageBackend.utils.enums;

/**
 * 
 * @author Geo
 *
 */
 public enum FunctionalityEnum {
 	DEFAULT("DEFAULT"),

	// ALERTS
	VIEW_ALERTS("VIEW_ALERTS"),	
	CREATE_ALERTS("CREATE_ALERTS"),
	UPDATE_ALERTS("UPDATE_ALERTS"),
	DELETE_ALERTS("DELETE_ALERTS"),
	// BOOKINGS
	VIEW_BOOKINGS("VIEW_BOOKINGS"),	
	CREATE_BOOKINGS("CREATE_BOOKINGS"),
	UPDATE_BOOKINGS("UPDATE_BOOKINGS"),
	DELETE_BOOKINGS("DELETE_BOOKINGS"),
	// BUSES
	VIEW_BUSES("VIEW_BUSES"),	
	CREATE_BUSES("CREATE_BUSES"),
	UPDATE_BUSES("UPDATE_BUSES"),
	DELETE_BUSES("DELETE_BUSES"),
	// COMPANIES
	VIEW_COMPANIES("VIEW_COMPANIES"),	
	CREATE_COMPANIES("CREATE_COMPANIES"),
	UPDATE_COMPANIES("UPDATE_COMPANIES"),
	DELETE_COMPANIES("DELETE_COMPANIES"),
	// FINANCIAL_REPORTS
	VIEW_FINANCIAL_REPORTS("VIEW_FINANCIAL_REPORTS"),	
	CREATE_FINANCIAL_REPORTS("CREATE_FINANCIAL_REPORTS"),
	UPDATE_FINANCIAL_REPORTS("UPDATE_FINANCIAL_REPORTS"),
	DELETE_FINANCIAL_REPORTS("DELETE_FINANCIAL_REPORTS"),
	// NOTIFICATIONS
	VIEW_NOTIFICATIONS("VIEW_NOTIFICATIONS"),	
	CREATE_NOTIFICATIONS("CREATE_NOTIFICATIONS"),
	UPDATE_NOTIFICATIONS("UPDATE_NOTIFICATIONS"),
	DELETE_NOTIFICATIONS("DELETE_NOTIFICATIONS"),
	// PAYMENTS
	VIEW_PAYMENTS("VIEW_PAYMENTS"),	
	CREATE_PAYMENTS("CREATE_PAYMENTS"),
	UPDATE_PAYMENTS("UPDATE_PAYMENTS"),
	DELETE_PAYMENTS("DELETE_PAYMENTS"),
	// ROLES
	VIEW_ROLES("VIEW_ROLES"),	
	CREATE_ROLES("CREATE_ROLES"),
	UPDATE_ROLES("UPDATE_ROLES"),
	DELETE_ROLES("DELETE_ROLES"),
	// ROUTES
	VIEW_ROUTES("VIEW_ROUTES"),	
	CREATE_ROUTES("CREATE_ROUTES"),
	UPDATE_ROUTES("UPDATE_ROUTES"),
	DELETE_ROUTES("DELETE_ROUTES"),
	// TICKETS
	VIEW_TICKETS("VIEW_TICKETS"),	
	CREATE_TICKETS("CREATE_TICKETS"),
	UPDATE_TICKETS("UPDATE_TICKETS"),
	DELETE_TICKETS("DELETE_TICKETS"),
	// TRIP_TRACKING
	VIEW_TRIP_TRACKING("VIEW_TRIP_TRACKING"),	
	CREATE_TRIP_TRACKING("CREATE_TRIP_TRACKING"),
	UPDATE_TRIP_TRACKING("UPDATE_TRIP_TRACKING"),
	DELETE_TRIP_TRACKING("DELETE_TRIP_TRACKING"),
	// USER_ROLES
	VIEW_USER_ROLES("VIEW_USER_ROLES"),	
	CREATE_USER_ROLES("CREATE_USER_ROLES"),
	UPDATE_USER_ROLES("UPDATE_USER_ROLES"),
	DELETE_USER_ROLES("DELETE_USER_ROLES"),
	// USERS
	VIEW_USERS("VIEW_USERS"),	
	CREATE_USERS("CREATE_USERS"),
	UPDATE_USERS("UPDATE_USERS"),
	DELETE_USERS("DELETE_USERS");

	private final String value;
 	public String getValue() {
 		return value;
 	}
 	private FunctionalityEnum(String value) {
 		this.value = value;
 	}
}
