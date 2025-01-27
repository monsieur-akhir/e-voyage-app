

/*
 * Java controller for entity table buses 
 * Created on 2025-01-11 ( Time 04:46:00 )
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
Controller for table "buses"
 * 
 * @author SFL Back-End developper
 *
 */
@Log
@CrossOrigin("*")
@RestController
@RequestMapping(value="/buses")
public class BusesController {

	@Autowired
    private ControllerFactory<BusesDto> controllerFactory;
	@Autowired
	private BusesBusiness busesBusiness;

	@RequestMapping(value="/create",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<BusesDto> create(@RequestBody Request<BusesDto> request) {
    	log.info("start method /buses/create");
        Response<BusesDto> response = controllerFactory.create(busesBusiness, request, FunctionalityEnum.CREATE_BUSES);
		log.info("end method /buses/create");
        return response;
    }

	@RequestMapping(value="/update",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<BusesDto> update(@RequestBody Request<BusesDto> request) {
    	log.info("start method /buses/update");
        Response<BusesDto> response = controllerFactory.update(busesBusiness, request, FunctionalityEnum.UPDATE_BUSES);
		log.info("end method /buses/update");
        return response;
    }

	@RequestMapping(value="/delete",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<BusesDto> delete(@RequestBody Request<BusesDto> request) {
    	log.info("start method /buses/delete");
        Response<BusesDto> response = controllerFactory.delete(busesBusiness, request, FunctionalityEnum.DELETE_BUSES);
		log.info("end method /buses/delete");
        return response;
    }

	@RequestMapping(value="/getByCriteria",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<BusesDto> getByCriteria(@RequestBody Request<BusesDto> request) {
    	log.info("start method /buses/getByCriteria");
        Response<BusesDto> response = controllerFactory.getByCriteria(busesBusiness, request, FunctionalityEnum.VIEW_BUSES);
		log.info("end method /buses/getByCriteria");
        return response;
    }
}
