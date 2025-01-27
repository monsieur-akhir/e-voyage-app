

/*
 * Java controller for entity table notifications 
 * Created on 2025-01-12 ( Time 17:39:54 )
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
Controller for table "notifications"
 * 
 * @author SFL Back-End developper
 *
 */
@Log
@CrossOrigin("*")
@RestController
@RequestMapping(value="/notifications")
public class NotificationsController {

	@Autowired
    private ControllerFactory<NotificationsDto> controllerFactory;
	@Autowired
	private NotificationsBusiness notificationsBusiness;

	@RequestMapping(value="/create",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<NotificationsDto> create(@RequestBody Request<NotificationsDto> request) {
    	log.info("start method /notifications/create");
        Response<NotificationsDto> response = controllerFactory.create(notificationsBusiness, request, FunctionalityEnum.CREATE_NOTIFICATIONS);
		log.info("end method /notifications/create");
        return response;
    }

	@RequestMapping(value="/update",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<NotificationsDto> update(@RequestBody Request<NotificationsDto> request) {
    	log.info("start method /notifications/update");
        Response<NotificationsDto> response = controllerFactory.update(notificationsBusiness, request, FunctionalityEnum.UPDATE_NOTIFICATIONS);
		log.info("end method /notifications/update");
        return response;
    }

	@RequestMapping(value="/delete",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<NotificationsDto> delete(@RequestBody Request<NotificationsDto> request) {
    	log.info("start method /notifications/delete");
        Response<NotificationsDto> response = controllerFactory.delete(notificationsBusiness, request, FunctionalityEnum.DELETE_NOTIFICATIONS);
		log.info("end method /notifications/delete");
        return response;
    }

	@RequestMapping(value="/getByCriteria",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<NotificationsDto> getByCriteria(@RequestBody Request<NotificationsDto> request) {
    	log.info("start method /notifications/getByCriteria");
        Response<NotificationsDto> response = controllerFactory.getByCriteria(notificationsBusiness, request, FunctionalityEnum.VIEW_NOTIFICATIONS);
		log.info("end method /notifications/getByCriteria");
        return response;
    }
}
