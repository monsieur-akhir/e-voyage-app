

/*
 * Java controller for entity table financial_reports 
 * Created on 2025-01-11 ( Time 04:46:00 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2017 Savoir Faire Linux. All Rights Reserved.
 */

package tech.synelia.eVoyageBackend.rest.api;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tech.synelia.eVoyageBackend.utils.*;
import tech.synelia.eVoyageBackend.utils.dto.*;
import tech.synelia.eVoyageBackend.utils.contract.*;
import tech.synelia.eVoyageBackend.utils.contract.Request;
import tech.synelia.eVoyageBackend.utils.contract.Response;
import tech.synelia.eVoyageBackend.utils.enums.FunctionalityEnum;
import tech.synelia.eVoyageBackend.business.*;
import tech.synelia.eVoyageBackend.rest.fact.ControllerFactory;

/**
Controller for table "financial_reports"
 * 
 * @author SFL Back-End developper
 *
 */
@Log
@CrossOrigin("*")
@RestController
@RequestMapping(value="/financialReports")
public class FinancialReportsController {

	@Autowired
    private ControllerFactory<FinancialReportsDto> controllerFactory;
	@Autowired
	private FinancialReportsBusiness financialReportsBusiness;

	@RequestMapping(value="/create",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<FinancialReportsDto> create(@RequestBody Request<FinancialReportsDto> request) {
    	log.info("start method /financialReports/create");
        Response<FinancialReportsDto> response = controllerFactory.create(financialReportsBusiness, request, FunctionalityEnum.CREATE_FINANCIAL_REPORTS);
		log.info("end method /financialReports/create");
        return response;
    }

	@RequestMapping(value="/update",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<FinancialReportsDto> update(@RequestBody Request<FinancialReportsDto> request) {
    	log.info("start method /financialReports/update");
        Response<FinancialReportsDto> response = controllerFactory.update(financialReportsBusiness, request, FunctionalityEnum.UPDATE_FINANCIAL_REPORTS);
		log.info("end method /financialReports/update");
        return response;
    }

	@RequestMapping(value="/delete",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<FinancialReportsDto> delete(@RequestBody Request<FinancialReportsDto> request) {
    	log.info("start method /financialReports/delete");
        Response<FinancialReportsDto> response = controllerFactory.delete(financialReportsBusiness, request, FunctionalityEnum.DELETE_FINANCIAL_REPORTS);
		log.info("end method /financialReports/delete");
        return response;
    }

	@RequestMapping(value="/getByCriteria",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<FinancialReportsDto> getByCriteria(@RequestBody Request<FinancialReportsDto> request) {
    	log.info("start method /financialReports/getByCriteria");
        Response<FinancialReportsDto> response = controllerFactory.getByCriteria(financialReportsBusiness, request, FunctionalityEnum.VIEW_FINANCIAL_REPORTS);
		log.info("end method /financialReports/getByCriteria");
        return response;
    }
}
