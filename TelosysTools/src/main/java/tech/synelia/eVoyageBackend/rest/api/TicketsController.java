

/*
 * Java controller for entity table tickets 
 * Created on 2025-01-11 ( Time 04:46:03 )
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
Controller for table "tickets"
 * 
 * @author SFL Back-End developper
 *
 */
@Log
@CrossOrigin("*")
@RestController
@RequestMapping(value="/tickets")
public class TicketsController {

	@Autowired
    private ControllerFactory<TicketsDto> controllerFactory;
	@Autowired
	private TicketsBusiness ticketsBusiness;

	@RequestMapping(value="/create",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<TicketsDto> create(@RequestBody Request<TicketsDto> request) {
    	log.info("start method /tickets/create");
        Response<TicketsDto> response = controllerFactory.create(ticketsBusiness, request, FunctionalityEnum.CREATE_TICKETS);
		log.info("end method /tickets/create");
        return response;
    }

	@RequestMapping(value="/update",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<TicketsDto> update(@RequestBody Request<TicketsDto> request) {
    	log.info("start method /tickets/update");
        Response<TicketsDto> response = controllerFactory.update(ticketsBusiness, request, FunctionalityEnum.UPDATE_TICKETS);
		log.info("end method /tickets/update");
        return response;
    }

	@RequestMapping(value="/delete",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<TicketsDto> delete(@RequestBody Request<TicketsDto> request) {
    	log.info("start method /tickets/delete");
        Response<TicketsDto> response = controllerFactory.delete(ticketsBusiness, request, FunctionalityEnum.DELETE_TICKETS);
		log.info("end method /tickets/delete");
        return response;
    }

	@RequestMapping(value="/getByCriteria",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<TicketsDto> getByCriteria(@RequestBody Request<TicketsDto> request) {
    	log.info("start method /tickets/getByCriteria");
        Response<TicketsDto> response = controllerFactory.getByCriteria(ticketsBusiness, request, FunctionalityEnum.VIEW_TICKETS);
		log.info("end method /tickets/getByCriteria");
        return response;
    }
}
