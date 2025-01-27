

/*
 * Java controller for entity table routes 
 * Created on 2025-01-12 ( Time 17:39:57 )
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
Controller for table "routes"
 * 
 * @author SFL Back-End developper
 *
 */
@Log
@CrossOrigin("*")
@RestController
@RequestMapping(value="/routes")
public class RoutesController {

	@Autowired
    private ControllerFactory<RoutesDto> controllerFactory;
	@Autowired
	private RoutesBusiness routesBusiness;

	@RequestMapping(value="/create",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<RoutesDto> create(@RequestBody Request<RoutesDto> request) {
    	log.info("start method /routes/create");
        Response<RoutesDto> response = controllerFactory.create(routesBusiness, request, FunctionalityEnum.CREATE_ROUTES);
		log.info("end method /routes/create");
        return response;
    }

	@RequestMapping(value="/update",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<RoutesDto> update(@RequestBody Request<RoutesDto> request) {
    	log.info("start method /routes/update");
        Response<RoutesDto> response = controllerFactory.update(routesBusiness, request, FunctionalityEnum.UPDATE_ROUTES);
		log.info("end method /routes/update");
        return response;
    }

	@RequestMapping(value="/delete",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<RoutesDto> delete(@RequestBody Request<RoutesDto> request) {
    	log.info("start method /routes/delete");
        Response<RoutesDto> response = controllerFactory.delete(routesBusiness, request, FunctionalityEnum.DELETE_ROUTES);
		log.info("end method /routes/delete");
        return response;
    }

	@RequestMapping(value="/getByCriteria",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<RoutesDto> getByCriteria(@RequestBody Request<RoutesDto> request) {
    	log.info("start method /routes/getByCriteria");
        Response<RoutesDto> response = controllerFactory.getByCriteria(routesBusiness, request, FunctionalityEnum.VIEW_ROUTES);
		log.info("end method /routes/getByCriteria");
        return response;
    }
}
