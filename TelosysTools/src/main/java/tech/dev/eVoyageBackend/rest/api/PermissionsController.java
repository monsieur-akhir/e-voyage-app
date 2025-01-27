

/*
 * Java controller for entity table permissions 
 * Created on 2025-01-12 ( Time 17:39:56 )
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
Controller for table "permissions"
 * 
 * @author SFL Back-End developper
 *
 */
@Log
@CrossOrigin("*")
@RestController
@RequestMapping(value="/permissions")
public class PermissionsController {

	@Autowired
    private ControllerFactory<PermissionsDto> controllerFactory;
	@Autowired
	private PermissionsBusiness permissionsBusiness;

	@RequestMapping(value="/create",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<PermissionsDto> create(@RequestBody Request<PermissionsDto> request) {
    	log.info("start method /permissions/create");
        Response<PermissionsDto> response = controllerFactory.create(permissionsBusiness, request, FunctionalityEnum.CREATE_PERMISSIONS);
		log.info("end method /permissions/create");
        return response;
    }

	@RequestMapping(value="/update",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<PermissionsDto> update(@RequestBody Request<PermissionsDto> request) {
    	log.info("start method /permissions/update");
        Response<PermissionsDto> response = controllerFactory.update(permissionsBusiness, request, FunctionalityEnum.UPDATE_PERMISSIONS);
		log.info("end method /permissions/update");
        return response;
    }

	@RequestMapping(value="/delete",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<PermissionsDto> delete(@RequestBody Request<PermissionsDto> request) {
    	log.info("start method /permissions/delete");
        Response<PermissionsDto> response = controllerFactory.delete(permissionsBusiness, request, FunctionalityEnum.DELETE_PERMISSIONS);
		log.info("end method /permissions/delete");
        return response;
    }

	@RequestMapping(value="/getByCriteria",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<PermissionsDto> getByCriteria(@RequestBody Request<PermissionsDto> request) {
    	log.info("start method /permissions/getByCriteria");
        Response<PermissionsDto> response = controllerFactory.getByCriteria(permissionsBusiness, request, FunctionalityEnum.VIEW_PERMISSIONS);
		log.info("end method /permissions/getByCriteria");
        return response;
    }
}
