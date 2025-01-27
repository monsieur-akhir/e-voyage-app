

/*
 * Java controller for entity table districts 
 * Created on 2025-01-21 ( Time 18:28:29 )
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
Controller for table "districts"
 * 
 * @author SFL Back-End developper
 *
 */
@Log
@CrossOrigin("*")
@RestController
@RequestMapping(value="/districts")
public class DistrictsController {

	@Autowired
    private ControllerFactory<DistrictsDto> controllerFactory;
	@Autowired
	private DistrictsBusiness districtsBusiness;

	@RequestMapping(value="/create",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<DistrictsDto> create(@RequestBody Request<DistrictsDto> request) {
    	log.info("start method /districts/create");
        Response<DistrictsDto> response = controllerFactory.create(districtsBusiness, request, FunctionalityEnum.CREATE_DISTRICTS);
		log.info("end method /districts/create");
        return response;
    }

	@RequestMapping(value="/update",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<DistrictsDto> update(@RequestBody Request<DistrictsDto> request) {
    	log.info("start method /districts/update");
        Response<DistrictsDto> response = controllerFactory.update(districtsBusiness, request, FunctionalityEnum.UPDATE_DISTRICTS);
		log.info("end method /districts/update");
        return response;
    }

	@RequestMapping(value="/delete",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<DistrictsDto> delete(@RequestBody Request<DistrictsDto> request) {
    	log.info("start method /districts/delete");
        Response<DistrictsDto> response = controllerFactory.delete(districtsBusiness, request, FunctionalityEnum.DELETE_DISTRICTS);
		log.info("end method /districts/delete");
        return response;
    }

	@RequestMapping(value="/getByCriteria",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<DistrictsDto> getByCriteria(@RequestBody Request<DistrictsDto> request) {
    	log.info("start method /districts/getByCriteria");
        Response<DistrictsDto> response = controllerFactory.getByCriteria(districtsBusiness, request, FunctionalityEnum.VIEW_DISTRICTS);
		log.info("end method /districts/getByCriteria");
        return response;
    }
}
