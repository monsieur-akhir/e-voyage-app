

/*
 * Java controller for entity table travel_schedules 
 * Created on 2025-01-12 ( Time 17:39:59 )
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
Controller for table "travel_schedules"
 * 
 * @author SFL Back-End developper
 *
 */
@Log
@CrossOrigin("*")
@RestController
@RequestMapping(value="/travelSchedules")
public class TravelSchedulesController {

	@Autowired
    private ControllerFactory<TravelSchedulesDto> controllerFactory;
	@Autowired
	private TravelSchedulesBusiness travelSchedulesBusiness;

	@RequestMapping(value="/create",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<TravelSchedulesDto> create(@RequestBody Request<TravelSchedulesDto> request) {
    	log.info("start method /travelSchedules/create");
        Response<TravelSchedulesDto> response = controllerFactory.create(travelSchedulesBusiness, request, FunctionalityEnum.CREATE_TRAVEL_SCHEDULES);
		log.info("end method /travelSchedules/create");
        return response;
    }

	@RequestMapping(value="/update",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<TravelSchedulesDto> update(@RequestBody Request<TravelSchedulesDto> request) {
    	log.info("start method /travelSchedules/update");
        Response<TravelSchedulesDto> response = controllerFactory.update(travelSchedulesBusiness, request, FunctionalityEnum.UPDATE_TRAVEL_SCHEDULES);
		log.info("end method /travelSchedules/update");
        return response;
    }

	@RequestMapping(value="/delete",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<TravelSchedulesDto> delete(@RequestBody Request<TravelSchedulesDto> request) {
    	log.info("start method /travelSchedules/delete");
        Response<TravelSchedulesDto> response = controllerFactory.delete(travelSchedulesBusiness, request, FunctionalityEnum.DELETE_TRAVEL_SCHEDULES);
		log.info("end method /travelSchedules/delete");
        return response;
    }

	@RequestMapping(value="/getByCriteria",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<TravelSchedulesDto> getByCriteria(@RequestBody Request<TravelSchedulesDto> request) {
    	log.info("start method /travelSchedules/getByCriteria");
        Response<TravelSchedulesDto> response = controllerFactory.getByCriteria(travelSchedulesBusiness, request, FunctionalityEnum.VIEW_TRAVEL_SCHEDULES);
		log.info("end method /travelSchedules/getByCriteria");
        return response;
    }
}
