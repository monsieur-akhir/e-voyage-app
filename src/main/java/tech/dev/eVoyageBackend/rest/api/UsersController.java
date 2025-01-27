

/*
 * Java controller for entity table users 
 * Created on 2025-01-12 ( Time 17:40:01 )
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
Controller for table "users"
 * 
 * @author SFL Back-End developper
 *
 */
@Log
@CrossOrigin("*")
@RestController
@RequestMapping(value="/users")
public class UsersController {

	@Autowired
    private ControllerFactory<UsersDto> controllerFactory;
	@Autowired
	private UsersBusiness usersBusiness;

	@RequestMapping(value="/create",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<UsersDto> create(@RequestBody Request<UsersDto> request) {
    	log.info("start method /users/create");
        Response<UsersDto> response = controllerFactory.create(usersBusiness, request, FunctionalityEnum.CREATE_USERS);
		log.info("end method /users/create");
        return response;
    }

	@RequestMapping(value="/update",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<UsersDto> update(@RequestBody Request<UsersDto> request) {
    	log.info("start method /users/update");
        Response<UsersDto> response = controllerFactory.update(usersBusiness, request, FunctionalityEnum.UPDATE_USERS);
		log.info("end method /users/update");
        return response;
    }

	@RequestMapping(value="/delete",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<UsersDto> delete(@RequestBody Request<UsersDto> request) {
    	log.info("start method /users/delete");
        Response<UsersDto> response = controllerFactory.delete(usersBusiness, request, FunctionalityEnum.DELETE_USERS);
		log.info("end method /users/delete");
        return response;
    }

	@RequestMapping(value="/getByCriteria",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<UsersDto> getByCriteria(@RequestBody Request<UsersDto> request) {
    	log.info("start method /users/getByCriteria");
        Response<UsersDto> response = controllerFactory.getByCriteria(usersBusiness, request, FunctionalityEnum.VIEW_USERS);
		log.info("end method /users/getByCriteria");
        return response;
    }
}
