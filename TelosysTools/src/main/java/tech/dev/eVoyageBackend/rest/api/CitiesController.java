

/*
 * Java controller for entity table cities 
 * Created on 2025-01-21 ( Time 18:33:53 )
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
Controller for table "cities"
 * 
 * @author SFL Back-End developper
 *
 */
@Log
@CrossOrigin("*")
@RestController
@RequestMapping(value="/cities")
public class CitiesController {

	@Autowired
    private ControllerFactory<CitiesDto> controllerFactory;
	@Autowired
	private CitiesBusiness citiesBusiness;

	@RequestMapping(value="/create",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<CitiesDto> create(@RequestBody Request<CitiesDto> request) {
    	log.info("start method /cities/create");
        Response<CitiesDto> response = controllerFactory.create(citiesBusiness, request, FunctionalityEnum.CREATE_CITIES);
		log.info("end method /cities/create");
        return response;
    }

	@RequestMapping(value="/update",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<CitiesDto> update(@RequestBody Request<CitiesDto> request) {
    	log.info("start method /cities/update");
        Response<CitiesDto> response = controllerFactory.update(citiesBusiness, request, FunctionalityEnum.UPDATE_CITIES);
		log.info("end method /cities/update");
        return response;
    }

	@RequestMapping(value="/delete",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<CitiesDto> delete(@RequestBody Request<CitiesDto> request) {
    	log.info("start method /cities/delete");
        Response<CitiesDto> response = controllerFactory.delete(citiesBusiness, request, FunctionalityEnum.DELETE_CITIES);
		log.info("end method /cities/delete");
        return response;
    }

	@RequestMapping(value="/getByCriteria",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<CitiesDto> getByCriteria(@RequestBody Request<CitiesDto> request) {
    	log.info("start method /cities/getByCriteria");
        Response<CitiesDto> response = controllerFactory.getByCriteria(citiesBusiness, request, FunctionalityEnum.VIEW_CITIES);
		log.info("end method /cities/getByCriteria");
        return response;
    }
}
