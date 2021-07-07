package com.cst.CstLabs.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.cst.CstLabs.utils.CstLabsExpection;

@Controller
public class DummyController {
	
	private static Logger LOGGER = LogManager.getLogger(DummyController.class);
	
	
	@GetMapping("/dummy")
public ResponseEntity<String> dummyGetMethod(){
		LOGGER.info("Dummy method called");	
		try {
			String message ="hello";
			ResponseEntity<String> responce = new ResponseEntity<String>(message, null, HttpStatus.OK);
			return responce;	
		}catch (CstLabsExpection e) {
			 throw new CstLabsExpection("expection occered while accessing the data");
		}
}

}
