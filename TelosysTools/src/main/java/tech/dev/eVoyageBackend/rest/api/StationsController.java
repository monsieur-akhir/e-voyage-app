

/*
 * Java controller for entity table stations 
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
Controller for table "stations"
 * 
 * @author SFL Back-End developper
 *
 */
@Log
@CrossOrigin("*")
@RestController
@RequestMapping(value="/stations")
public class StationsController {

	@Autowired
    private ControllerFactory<StationsDto> controllerFactory;
	@Autowired
	private StationsBusiness stationsBusiness;

	@RequestMapping(value="/create",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<StationsDto> create(@RequestBody Request<StationsDto> request) {
    	log.info("start method /stations/create");
        Response<StationsDto> response = controllerFactory.create(stationsBusiness, request, FunctionalityEnum.CREATE_STATIONS);
		log.info("end method /stations/create");
        return response;
    }

	@RequestMapping(value="/update",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<StationsDto> update(@RequestBody Request<StationsDto> request) {
    	log.info("start method /stations/update");
        Response<StationsDto> response = controllerFactory.update(stationsBusiness, request, FunctionalityEnum.UPDATE_STATIONS);
		log.info("end method /stations/update");
        return response;
    }

	@RequestMapping(value="/delete",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<StationsDto> delete(@RequestBody Request<StationsDto> request) {
    	log.info("start method /stations/delete");
        Response<StationsDto> response = controllerFactory.delete(stationsBusiness, request, FunctionalityEnum.DELETE_STATIONS);
		log.info("end method /stations/delete");
        return response;
    }

	@RequestMapping(value="/getByCriteria",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<StationsDto> getByCriteria(@RequestBody Request<StationsDto> request) {
    	log.info("start method /stations/getByCriteria");
        Response<StationsDto> response = controllerFactory.getByCriteria(stationsBusiness, request, FunctionalityEnum.VIEW_STATIONS);
		log.info("end method /stations/getByCriteria");
        return response;
    }
}
