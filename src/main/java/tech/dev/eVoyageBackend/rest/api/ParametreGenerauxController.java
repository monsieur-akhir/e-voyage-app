

/*
 * Java controller for entity table parametre_generaux 
 * Created on 2024-12-19 ( Time 23:36:43 )
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
Controller for table "parametre_generaux"
 * 
 * @author SFL Back-End developper
 *
 */
@Log
@CrossOrigin("*")
@RestController
@RequestMapping(value="/parametreGeneraux")
public class ParametreGenerauxController {

	@Autowired
    private ControllerFactory<ParametreGenerauxDto> controllerFactory;
	@Autowired
	private ParametreGenerauxBusiness parametreGenerauxBusiness;

	@RequestMapping(value="/create",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<ParametreGenerauxDto> create(@RequestBody Request<ParametreGenerauxDto> request) {
    	log.info("start method /parametreGeneraux/create");
        Response<ParametreGenerauxDto> response = controllerFactory.create(parametreGenerauxBusiness, request, FunctionalityEnum.CREATE_PARAMETRE_GENERAUX);
		log.info("end method /parametreGeneraux/create");
        return response;
    }

	@RequestMapping(value="/update",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<ParametreGenerauxDto> update(@RequestBody Request<ParametreGenerauxDto> request) {
    	log.info("start method /parametreGeneraux/update");
        Response<ParametreGenerauxDto> response = controllerFactory.update(parametreGenerauxBusiness, request, FunctionalityEnum.UPDATE_PARAMETRE_GENERAUX);
		log.info("end method /parametreGeneraux/update");
        return response;
    }

	@RequestMapping(value="/delete",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<ParametreGenerauxDto> delete(@RequestBody Request<ParametreGenerauxDto> request) {
    	log.info("start method /parametreGeneraux/delete");
        Response<ParametreGenerauxDto> response = controllerFactory.delete(parametreGenerauxBusiness, request, FunctionalityEnum.DELETE_PARAMETRE_GENERAUX);
		log.info("end method /parametreGeneraux/delete");
        return response;
    }

	@RequestMapping(value="/getByCriteria",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<ParametreGenerauxDto> getByCriteria(@RequestBody Request<ParametreGenerauxDto> request) {
    	log.info("start method /parametreGeneraux/getByCriteria");
        Response<ParametreGenerauxDto> response = controllerFactory.getByCriteria(parametreGenerauxBusiness, request, FunctionalityEnum.VIEW_PARAMETRE_GENERAUX);
		log.info("end method /parametreGeneraux/getByCriteria");
        return response;
    }
}
