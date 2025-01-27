

/*
 * Java controller for entity table departs 
 * Created on 2025-01-21 ( Time 18:33:54 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2017 Savoir Faire Linux. All Rights Reserved.
 */

package tech.dev.eVoyageBackend.rest.api;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tech.dev.eVoyageBackend.utils.*;
import tech.dev.eVoyageBackend.utils.dto.*;
import tech.dev.eVoyageBackend.utils.contract.*;
import tech.dev.eVoyageBackend.utils.contract.Request;
import tech.dev.eVoyageBackend.utils.contract.Response;
import tech.dev.eVoyageBackend.utils.enums.FunctionalityEnum;
import tech.dev.eVoyageBackend.business.*;
import tech.dev.eVoyageBackend.rest.fact.ControllerFactory;

/**
Controller for table "departs"
 * 
 * @author SFL Back-End developper
 *
 */
@Log
@CrossOrigin("*")
@RestController
@RequestMapping(value="/departs")
public class DepartsController {

	@Autowired
    private ControllerFactory<DepartsDto> controllerFactory;
	@Autowired
	private DepartsBusiness departsBusiness;

	@RequestMapping(value="/create",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<DepartsDto> create(@RequestBody Request<DepartsDto> request) {
    	log.info("start method /departs/create");
        Response<DepartsDto> response = controllerFactory.create(departsBusiness, request, FunctionalityEnum.CREATE_DEPARTS);
		log.info("end method /departs/create");
        return response;
    }

	@RequestMapping(value="/update",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<DepartsDto> update(@RequestBody Request<DepartsDto> request) {
    	log.info("start method /departs/update");
        Response<DepartsDto> response = controllerFactory.update(departsBusiness, request, FunctionalityEnum.UPDATE_DEPARTS);
		log.info("end method /departs/update");
        return response;
    }

	@RequestMapping(value="/delete",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<DepartsDto> delete(@RequestBody Request<DepartsDto> request) {
    	log.info("start method /departs/delete");
        Response<DepartsDto> response = controllerFactory.delete(departsBusiness, request, FunctionalityEnum.DELETE_DEPARTS);
		log.info("end method /departs/delete");
        return response;
    }

	@RequestMapping(value="/getByCriteria",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<DepartsDto> getByCriteria(@RequestBody Request<DepartsDto> request) {
    	log.info("start method /departs/getByCriteria");
        Response<DepartsDto> response = controllerFactory.getByCriteria(departsBusiness, request, FunctionalityEnum.VIEW_DEPARTS);
		log.info("end method /departs/getByCriteria");
        return response;
    }
}
