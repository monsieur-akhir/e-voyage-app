

/*
 * Java controller for entity table role_fonctionalite 
 * Created on 2024-08-20 ( Time 10:24:22 )
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
Controller for table "role_fonctionalite"
 * 
 * @author SFL Back-End developper
 *
 */
@Log
@CrossOrigin("*")
@RestController
@RequestMapping(value="/roleFonctionalite")
public class RoleFonctionaliteController {

	@Autowired
    private ControllerFactory<RoleFonctionaliteDto> controllerFactory;
	@Autowired
	private RoleFonctionaliteBusiness roleFonctionaliteBusiness;

	@RequestMapping(value="/create",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<RoleFonctionaliteDto> create(@RequestBody Request<RoleFonctionaliteDto> request) {
    	log.info("start method /roleFonctionalite/create");
        Response<RoleFonctionaliteDto> response = controllerFactory.create(roleFonctionaliteBusiness, request, FunctionalityEnum.CREATE_ROLE_FONCTIONALITE);
		log.info("end method /roleFonctionalite/create");
        return response;
    }

	@RequestMapping(value="/update",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<RoleFonctionaliteDto> update(@RequestBody Request<RoleFonctionaliteDto> request) {
    	log.info("start method /roleFonctionalite/update");
        Response<RoleFonctionaliteDto> response = controllerFactory.update(roleFonctionaliteBusiness, request, FunctionalityEnum.UPDATE_ROLE_FONCTIONALITE);
		log.info("end method /roleFonctionalite/update");
        return response;
    }

	@RequestMapping(value="/delete",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<RoleFonctionaliteDto> delete(@RequestBody Request<RoleFonctionaliteDto> request) {
    	log.info("start method /roleFonctionalite/delete");
        Response<RoleFonctionaliteDto> response = controllerFactory.delete(roleFonctionaliteBusiness, request, FunctionalityEnum.DELETE_ROLE_FONCTIONALITE);
		log.info("end method /roleFonctionalite/delete");
        return response;
    }

	@RequestMapping(value="/getByCriteria",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<RoleFonctionaliteDto> getByCriteria(@RequestBody Request<RoleFonctionaliteDto> request) {
    	log.info("start method /roleFonctionalite/getByCriteria");
        Response<RoleFonctionaliteDto> response = controllerFactory.getByCriteria(roleFonctionaliteBusiness, request, FunctionalityEnum.VIEW_ROLE_FONCTIONALITE);
		log.info("end method /roleFonctionalite/getByCriteria");
        return response;
    }
}
