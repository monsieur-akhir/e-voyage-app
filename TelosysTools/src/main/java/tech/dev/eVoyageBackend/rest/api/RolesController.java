

/*
 * Java controller for entity table roles 
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
Controller for table "roles"
 * 
 * @author SFL Back-End developper
 *
 */
@Log
@CrossOrigin("*")
@RestController
@RequestMapping(value="/roles")
public class RolesController {

	@Autowired
    private ControllerFactory<RolesDto> controllerFactory;
	@Autowired
	private RolesBusiness rolesBusiness;

	@RequestMapping(value="/create",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<RolesDto> create(@RequestBody Request<RolesDto> request) {
    	log.info("start method /roles/create");
        Response<RolesDto> response = controllerFactory.create(rolesBusiness, request, FunctionalityEnum.CREATE_ROLES);
		log.info("end method /roles/create");
        return response;
    }

	@RequestMapping(value="/update",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<RolesDto> update(@RequestBody Request<RolesDto> request) {
    	log.info("start method /roles/update");
        Response<RolesDto> response = controllerFactory.update(rolesBusiness, request, FunctionalityEnum.UPDATE_ROLES);
		log.info("end method /roles/update");
        return response;
    }

	@RequestMapping(value="/delete",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<RolesDto> delete(@RequestBody Request<RolesDto> request) {
    	log.info("start method /roles/delete");
        Response<RolesDto> response = controllerFactory.delete(rolesBusiness, request, FunctionalityEnum.DELETE_ROLES);
		log.info("end method /roles/delete");
        return response;
    }

	@RequestMapping(value="/getByCriteria",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<RolesDto> getByCriteria(@RequestBody Request<RolesDto> request) {
    	log.info("start method /roles/getByCriteria");
        Response<RolesDto> response = controllerFactory.getByCriteria(rolesBusiness, request, FunctionalityEnum.VIEW_ROLES);
		log.info("end method /roles/getByCriteria");
        return response;
    }
}
