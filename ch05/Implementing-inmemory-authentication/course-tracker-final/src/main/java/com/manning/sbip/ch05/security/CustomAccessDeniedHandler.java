package com.manning.sbip.ch05.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
	
	private Log log = LogFactory.getLog(CustomAccessDeniedHandler.class);
	
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      
		if (authentication != null) {
			log.warn("User: " + authentication.getName() 
            + " attempted to access the protected URL: "
            + request.getRequestURI());
      }

      response.sendRedirect(request.getContextPath() + "/accessDenied");
		
	}

}
