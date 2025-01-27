

/*
 * Java controller for entity table bookings 
 * Created on 2025-01-11 ( Time 04:45:59 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2017 Savoir Faire Linux. All Rights Reserved.
 */

package tech.synelia.eVoyageBackend.rest.api;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tech.synelia.eVoyageBackend.utils.*;
import tech.synelia.eVoyageBackend.utils.dto.*;
import tech.synelia.eVoyageBackend.utils.contract.*;
import tech.synelia.eVoyageBackend.utils.contract.Request;
import tech.synelia.eVoyageBackend.utils.contract.Response;
import tech.synelia.eVoyageBackend.utils.enums.FunctionalityEnum;
import tech.synelia.eVoyageBackend.business.*;
import tech.synelia.eVoyageBackend.rest.fact.ControllerFactory;

/**
Controller for table "bookings"
 * 
 * @author SFL Back-End developper
 *
 */
@Log
@CrossOrigin("*")
@RestController
@RequestMapping(value="/bookings")
public class BookingsController {

	@Autowired
    private ControllerFactory<BookingsDto> controllerFactory;
	@Autowired
	private BookingsBusiness bookingsBusiness;

	@RequestMapping(value="/create",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<BookingsDto> create(@RequestBody Request<BookingsDto> request) {
    	log.info("start method /bookings/create");
        Response<BookingsDto> response = controllerFactory.create(bookingsBusiness, request, FunctionalityEnum.CREATE_BOOKINGS);
		log.info("end method /bookings/create");
        return response;
    }

	@RequestMapping(value="/update",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<BookingsDto> update(@RequestBody Request<BookingsDto> request) {
    	log.info("start method /bookings/update");
        Response<BookingsDto> response = controllerFactory.update(bookingsBusiness, request, FunctionalityEnum.UPDATE_BOOKINGS);
		log.info("end method /bookings/update");
        return response;
    }

	@RequestMapping(value="/delete",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<BookingsDto> delete(@RequestBody Request<BookingsDto> request) {
    	log.info("start method /bookings/delete");
        Response<BookingsDto> response = controllerFactory.delete(bookingsBusiness, request, FunctionalityEnum.DELETE_BOOKINGS);
		log.info("end method /bookings/delete");
        return response;
    }

	@RequestMapping(value="/getByCriteria",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<BookingsDto> getByCriteria(@RequestBody Request<BookingsDto> request) {
    	log.info("start method /bookings/getByCriteria");
        Response<BookingsDto> response = controllerFactory.getByCriteria(bookingsBusiness, request, FunctionalityEnum.VIEW_BOOKINGS);
		log.info("end method /bookings/getByCriteria");
        return response;
    }
}
