
/*
 * Created on 2024-02-19 ( Time 10:54:59 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2017 Savoir Faire Linux. All Rights Reserved.
 */

package tech.dev.eVoyageBackend.rest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class RestInterceptor extends HandlerInterceptorAdapter {

	private static String	defaultTenant	= "null";
	
	private static String defaultLanguage = "fr";

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {

		String tenantValue = req.getHeader("tenantID");

		if (tenantValue != null) {
			req.setAttribute("CURRENT_TENANT_IDENTIFIER", tenantValue);
		} else {
			req.setAttribute("CURRENT_TENANT_IDENTIFIER", defaultTenant);
		}
		
		String langValue = req.getHeader("lang");

		if (langValue != null) {
			req.setAttribute("CURRENT_LANGUAGE_IDENTIFIER", langValue);
		} else {
			req.setAttribute("CURRENT_LANGUAGE_IDENTIFIER", defaultLanguage);
		}
		return true;
	}
}