

/*
 * Java controller for entity table alerts 
 * Created on 2025-01-11 ( Time 04:45:54 )
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
Controller for table "alerts"
 * 
 * @author SFL Back-End developper
 *
 */
@Log
@CrossOrigin("*")
@RestController
@RequestMapping(value="/alerts")
public class AlertsController {

	@Autowired
    private ControllerFactory<AlertsDto> controllerFactory;
	@Autowired
	private AlertsBusiness alertsBusiness;

	@RequestMapping(value="/create",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<AlertsDto> create(@RequestBody Request<AlertsDto> request) {
    	log.info("start method /alerts/create");
        Response<AlertsDto> response = controllerFactory.create(alertsBusiness, request, FunctionalityEnum.CREATE_ALERTS);
		log.info("end method /alerts/create");
        return response;
    }

	@RequestMapping(value="/update",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<AlertsDto> update(@RequestBody Request<AlertsDto> request) {
    	log.info("start method /alerts/update");
        Response<AlertsDto> response = controllerFactory.update(alertsBusiness, request, FunctionalityEnum.UPDATE_ALERTS);
		log.info("end method /alerts/update");
        return response;
    }

	@RequestMapping(value="/delete",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<AlertsDto> delete(@RequestBody Request<AlertsDto> request) {
    	log.info("start method /alerts/delete");
        Response<AlertsDto> response = controllerFactory.delete(alertsBusiness, request, FunctionalityEnum.DELETE_ALERTS);
		log.info("end method /alerts/delete");
        return response;
    }

	@RequestMapping(value="/getByCriteria",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<AlertsDto> getByCriteria(@RequestBody Request<AlertsDto> request) {
    	log.info("start method /alerts/getByCriteria");
        Response<AlertsDto> response = controllerFactory.getByCriteria(alertsBusiness, request, FunctionalityEnum.VIEW_ALERTS);
		log.info("end method /alerts/getByCriteria");
        return response;
    }
}
