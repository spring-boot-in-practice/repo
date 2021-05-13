package com.manning.sbip.ch06.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.authentication.WebAuthenticationDetails;

import lombok.Getter;

public class AdditionalWebAuthenticationDetails extends WebAuthenticationDetails {

	private static final long serialVersionUID = 8574689359077719102L;

	@Getter
    private String securityPin;

    public AdditionalWebAuthenticationDetails(HttpServletRequest request) {
        super(request);
        String securityPin = request.getParameter("securityPin");
        if(securityPin != null) {
            this.securityPin = securityPin;
        }
    }
}
