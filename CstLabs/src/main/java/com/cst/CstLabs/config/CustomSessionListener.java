package com.cst.CstLabs.config;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Configuration;




@Configuration
public class CustomSessionListener implements HttpSessionListener {
	private static final Logger LOGGER = LogManager.getLogger(CustomSessionListener.class);	
/*	 @Override
	    public void sessionCreated(HttpSessionEvent event) {
		 LOGGER.info("Session time set of 6o mins");
		 
		 HttpSession session =event.getSession();
				 session.setMaxInactiveInterval(3600);
	 }
	 
	 @Override
	 public void sessionDestroyed(HttpSessionEvent event) {
		 LOGGER.info("Session time set of 6o mins" +event.getSession().getId());
		 
	 }*/
	 
	 
	

}
