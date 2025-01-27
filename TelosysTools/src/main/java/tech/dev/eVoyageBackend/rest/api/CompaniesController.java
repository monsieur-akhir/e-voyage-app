

/*
 * Java controller for entity table companies 
 * Created on 2025-01-12 ( Time 17:39:52 )
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
Controller for table "companies"
 * 
 * @author SFL Back-End developper
 *
 */
@Log
@CrossOrigin("*")
@RestController
@RequestMapping(value="/companies")
public class CompaniesController {

	@Autowired
    private ControllerFactory<CompaniesDto> controllerFactory;
	@Autowired
	private CompaniesBusiness companiesBusiness;

	@RequestMapping(value="/create",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<CompaniesDto> create(@RequestBody Request<CompaniesDto> request) {
    	log.info("start method /companies/create");
        Response<CompaniesDto> response = controllerFactory.create(companiesBusiness, request, FunctionalityEnum.CREATE_COMPANIES);
		log.info("end method /companies/create");
        return response;
    }

	@RequestMapping(value="/update",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<CompaniesDto> update(@RequestBody Request<CompaniesDto> request) {
    	log.info("start method /companies/update");
        Response<CompaniesDto> response = controllerFactory.update(companiesBusiness, request, FunctionalityEnum.UPDATE_COMPANIES);
		log.info("end method /companies/update");
        return response;
    }

	@RequestMapping(value="/delete",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<CompaniesDto> delete(@RequestBody Request<CompaniesDto> request) {
    	log.info("start method /companies/delete");
        Response<CompaniesDto> response = controllerFactory.delete(companiesBusiness, request, FunctionalityEnum.DELETE_COMPANIES);
		log.info("end method /companies/delete");
        return response;
    }

	@RequestMapping(value="/getByCriteria",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<CompaniesDto> getByCriteria(@RequestBody Request<CompaniesDto> request) {
    	log.info("start method /companies/getByCriteria");
        Response<CompaniesDto> response = controllerFactory.getByCriteria(companiesBusiness, request, FunctionalityEnum.VIEW_COMPANIES);
		log.info("end method /companies/getByCriteria");
        return response;
    }
}
