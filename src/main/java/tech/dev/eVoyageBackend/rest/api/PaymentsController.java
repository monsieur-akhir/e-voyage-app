

/*
 * Java controller for entity table payments 
 * Created on 2025-01-12 ( Time 17:39:55 )
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
Controller for table "payments"
 * 
 * @author SFL Back-End developper
 *
 */
@Log
@CrossOrigin("*")
@RestController
@RequestMapping(value="/payments")
public class PaymentsController {

	@Autowired
    private ControllerFactory<PaymentsDto> controllerFactory;
	@Autowired
	private PaymentsBusiness paymentsBusiness;

	@RequestMapping(value="/create",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<PaymentsDto> create(@RequestBody Request<PaymentsDto> request) {
    	log.info("start method /payments/create");
        Response<PaymentsDto> response = controllerFactory.create(paymentsBusiness, request, FunctionalityEnum.CREATE_PAYMENTS);
		log.info("end method /payments/create");
        return response;
    }

	@RequestMapping(value="/update",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<PaymentsDto> update(@RequestBody Request<PaymentsDto> request) {
    	log.info("start method /payments/update");
        Response<PaymentsDto> response = controllerFactory.update(paymentsBusiness, request, FunctionalityEnum.UPDATE_PAYMENTS);
		log.info("end method /payments/update");
        return response;
    }

	@RequestMapping(value="/delete",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<PaymentsDto> delete(@RequestBody Request<PaymentsDto> request) {
    	log.info("start method /payments/delete");
        Response<PaymentsDto> response = controllerFactory.delete(paymentsBusiness, request, FunctionalityEnum.DELETE_PAYMENTS);
		log.info("end method /payments/delete");
        return response;
    }

	@RequestMapping(value="/getByCriteria",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<PaymentsDto> getByCriteria(@RequestBody Request<PaymentsDto> request) {
    	log.info("start method /payments/getByCriteria");
        Response<PaymentsDto> response = controllerFactory.getByCriteria(paymentsBusiness, request, FunctionalityEnum.VIEW_PAYMENTS);
		log.info("end method /payments/getByCriteria");
        return response;
    }
}
