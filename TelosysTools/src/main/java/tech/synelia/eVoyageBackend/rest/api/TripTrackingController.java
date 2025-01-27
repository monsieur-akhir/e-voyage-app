

/*
 * Java controller for entity table trip_tracking 
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
Controller for table "trip_tracking"
 * 
 * @author SFL Back-End developper
 *
 */
@Log
@CrossOrigin("*")
@RestController
@RequestMapping(value="/tripTracking")
public class TripTrackingController {

	@Autowired
    private ControllerFactory<TripTrackingDto> controllerFactory;
	@Autowired
	private TripTrackingBusiness tripTrackingBusiness;

	@RequestMapping(value="/create",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<TripTrackingDto> create(@RequestBody Request<TripTrackingDto> request) {
    	log.info("start method /tripTracking/create");
        Response<TripTrackingDto> response = controllerFactory.create(tripTrackingBusiness, request, FunctionalityEnum.CREATE_TRIP_TRACKING);
		log.info("end method /tripTracking/create");
        return response;
    }

	@RequestMapping(value="/update",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<TripTrackingDto> update(@RequestBody Request<TripTrackingDto> request) {
    	log.info("start method /tripTracking/update");
        Response<TripTrackingDto> response = controllerFactory.update(tripTrackingBusiness, request, FunctionalityEnum.UPDATE_TRIP_TRACKING);
		log.info("end method /tripTracking/update");
        return response;
    }

	@RequestMapping(value="/delete",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<TripTrackingDto> delete(@RequestBody Request<TripTrackingDto> request) {
    	log.info("start method /tripTracking/delete");
        Response<TripTrackingDto> response = controllerFactory.delete(tripTrackingBusiness, request, FunctionalityEnum.DELETE_TRIP_TRACKING);
		log.info("end method /tripTracking/delete");
        return response;
    }

	@RequestMapping(value="/getByCriteria",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<TripTrackingDto> getByCriteria(@RequestBody Request<TripTrackingDto> request) {
    	log.info("start method /tripTracking/getByCriteria");
        Response<TripTrackingDto> response = controllerFactory.getByCriteria(tripTrackingBusiness, request, FunctionalityEnum.VIEW_TRIP_TRACKING);
		log.info("end method /tripTracking/getByCriteria");
        return response;
    }
}
