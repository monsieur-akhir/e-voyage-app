

/*
 * Java controller for entity table fonctionalite 
 * Created on 2024-08-20 ( Time 10:24:21 )
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
Controller for table "fonctionalite"
 * 
 * @author SFL Back-End developper
 *
 */
@Log
@CrossOrigin("*")
@RestController
@RequestMapping(value="/fonctionalite")
public class FonctionaliteController {

	@Autowired
    private ControllerFactory<FonctionaliteDto> controllerFactory;
	@Autowired
	private FonctionaliteBusiness fonctionaliteBusiness;

	@RequestMapping(value="/create",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<FonctionaliteDto> create(@RequestBody Request<FonctionaliteDto> request) {
    	log.info("start method /fonctionalite/create");
        Response<FonctionaliteDto> response = controllerFactory.create(fonctionaliteBusiness, request, FunctionalityEnum.CREATE_FONCTIONALITE);
		log.info("end method /fonctionalite/create");
        return response;
    }

	@RequestMapping(value="/update",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<FonctionaliteDto> update(@RequestBody Request<FonctionaliteDto> request) {
    	log.info("start method /fonctionalite/update");
        Response<FonctionaliteDto> response = controllerFactory.update(fonctionaliteBusiness, request, FunctionalityEnum.UPDATE_FONCTIONALITE);
		log.info("end method /fonctionalite/update");
        return response;
    }

	@RequestMapping(value="/delete",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<FonctionaliteDto> delete(@RequestBody Request<FonctionaliteDto> request) {
    	log.info("start method /fonctionalite/delete");
        Response<FonctionaliteDto> response = controllerFactory.delete(fonctionaliteBusiness, request, FunctionalityEnum.DELETE_FONCTIONALITE);
		log.info("end method /fonctionalite/delete");
        return response;
    }

	@RequestMapping(value="/getByCriteria",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<FonctionaliteDto> getByCriteria(@RequestBody Request<FonctionaliteDto> request) {
    	log.info("start method /fonctionalite/getByCriteria");
        Response<FonctionaliteDto> response = controllerFactory.getByCriteria(fonctionaliteBusiness, request, FunctionalityEnum.VIEW_FONCTIONALITE);
		log.info("end method /fonctionalite/getByCriteria");
        return response;
    }
}
