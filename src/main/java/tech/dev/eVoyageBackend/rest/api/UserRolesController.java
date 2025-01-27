

/*
 * Java controller for entity table user_roles 
 * Created on 2025-01-11 ( Time 04:46:04 )
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
Controller for table "user_roles"
 * 
 * @author SFL Back-End developper
 *
 */
@Log
@CrossOrigin("*")
@RestController
@RequestMapping(value="/userRoles")
public class UserRolesController {

	@Autowired
    private ControllerFactory<UserRolesDto> controllerFactory;
	@Autowired
	private UserRolesBusiness userRolesBusiness;

	@RequestMapping(value="/create",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<UserRolesDto> create(@RequestBody Request<UserRolesDto> request) {
    	log.info("start method /userRoles/create");
        Response<UserRolesDto> response = controllerFactory.create(userRolesBusiness, request, FunctionalityEnum.CREATE_USER_ROLES);
		log.info("end method /userRoles/create");
        return response;
    }

	@RequestMapping(value="/update",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<UserRolesDto> update(@RequestBody Request<UserRolesDto> request) {
    	log.info("start method /userRoles/update");
        Response<UserRolesDto> response = controllerFactory.update(userRolesBusiness, request, FunctionalityEnum.UPDATE_USER_ROLES);
		log.info("end method /userRoles/update");
        return response;
    }

	@RequestMapping(value="/delete",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<UserRolesDto> delete(@RequestBody Request<UserRolesDto> request) {
    	log.info("start method /userRoles/delete");
        Response<UserRolesDto> response = controllerFactory.delete(userRolesBusiness, request, FunctionalityEnum.DELETE_USER_ROLES);
		log.info("end method /userRoles/delete");
        return response;
    }

	@RequestMapping(value="/getByCriteria",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<UserRolesDto> getByCriteria(@RequestBody Request<UserRolesDto> request) {
    	log.info("start method /userRoles/getByCriteria");
        Response<UserRolesDto> response = controllerFactory.getByCriteria(userRolesBusiness, request, FunctionalityEnum.VIEW_USER_ROLES);
		log.info("end method /userRoles/getByCriteria");
        return response;
    }
}
