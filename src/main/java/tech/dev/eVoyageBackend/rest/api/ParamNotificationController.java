

/*
 * Java controller for entity table param_notification 
 * Created on 2024-09-02 ( Time 12:26:07 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2017 Savoir Faire Linux. All Rights Reserved.
 */

package tech.dev.eVoyageBackend.rest.api;

import lombok.extern.java.Log;
import tech.dev.eVoyageBackend.business.*;
import tech.dev.eVoyageBackend.rest.fact.ControllerFactory;
import tech.dev.eVoyageBackend.utils.*;
import tech.dev.eVoyageBackend.utils.contract.*;
import tech.dev.eVoyageBackend.utils.dto.*;
import tech.dev.eVoyageBackend.utils.enums.FunctionalityEnum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
Controller for table "param_notification"
 * 
 * @author SFL Back-End developper
 *
 */
@Log
@CrossOrigin("*")
@RestController
@RequestMapping(value="/paramNotification")
public class ParamNotificationController {

	@Autowired
    private ControllerFactory<ParamNotificationDto> controllerFactory;
	@Autowired
	private ParamNotificationBusiness paramNotificationBusiness;

	@RequestMapping(value="/create",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<ParamNotificationDto> create(@RequestBody Request<ParamNotificationDto> request) {
    	log.info("start method /paramNotification/create");
        Response<ParamNotificationDto> response = controllerFactory.create(paramNotificationBusiness, request, FunctionalityEnum.CREATE_PARAM_NOTIFICATION);
		log.info("end method /paramNotification/create");
        return response;
    }

	@RequestMapping(value="/update",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<ParamNotificationDto> update(@RequestBody Request<ParamNotificationDto> request) {
    	log.info("start method /paramNotification/update");
        Response<ParamNotificationDto> response = controllerFactory.update(paramNotificationBusiness, request, FunctionalityEnum.UPDATE_PARAM_NOTIFICATION);
		log.info("end method /paramNotification/update");
        return response;
    }

	@RequestMapping(value="/delete",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<ParamNotificationDto> delete(@RequestBody Request<ParamNotificationDto> request) {
    	log.info("start method /paramNotification/delete");
        Response<ParamNotificationDto> response = controllerFactory.delete(paramNotificationBusiness, request, FunctionalityEnum.DELETE_PARAM_NOTIFICATION);
		log.info("end method /paramNotification/delete");
        return response;
    }

	@RequestMapping(value="/getByCriteria",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<ParamNotificationDto> getByCriteria(@RequestBody Request<ParamNotificationDto> request) {
    	log.info("start method /paramNotification/getByCriteria");
        Response<ParamNotificationDto> response = controllerFactory.getByCriteria(paramNotificationBusiness, request, FunctionalityEnum.VIEW_PARAM_NOTIFICATION);
		log.info("end method /paramNotification/getByCriteria");
        return response;
    }
}
