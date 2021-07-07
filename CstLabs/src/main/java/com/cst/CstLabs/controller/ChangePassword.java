package com.cst.CstLabs.controller;



import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cst.CstLabs.model.SignUpInfo;
import com.cst.CstLabs.service.LoginService;
import com.cst.CstLabs.service.SignUpService;
import com.cst.CstLabs.utils.Constants;
import com.cst.CstLabs.utils.CstLabsExpection;

@Controller
@RequestMapping("/changePassword")
public class ChangePassword {
	
	
	@Autowired
	SignUpService signUpService;

	private static Logger LOGGER = LogManager.getLogger(SignUpController.class);
	
	@GetMapping()
	public String changePassword() {
		return "changePassword"  ;
	}
	
	@ModelAttribute("pass")
	public SignUpInfo signUpInfo() {
		return new SignUpInfo();

	}
	
	@PostMapping
	public String updatePassword(@ModelAttribute("pass") SignUpInfo signUpInfo,HttpServletRequest request,Model model) {
		Principal principal = request.getUserPrincipal();
		LOGGER.info("user can now update the password" +principal.getName());
		System.out.println(signUpInfo.getConfirmPassword()+"=="+signUpInfo.getNewPassword());
		try {
		if(signUpInfo.getConfirmPassword().equals(signUpInfo.getNewPassword())) {
			LOGGER.info("user can now update the password" +principal.getName());
			String response=signUpService.updatePassWord(signUpInfo, principal.getName());
		
			if(response.equals(Constants.AuthenticationSuccess)) {
			model.addAttribute("success", "correct");
			return "changePassword"  ;
			}else if(response.equals(Constants.UserNotExests)) {
			    model.addAttribute("Exests", "exest");
				return "changePassword"  ;
			}else if(response.equals(Constants.Password)) {
				model.addAttribute("oldNnew", "error");
				return "changePassword"  ;
			}
			
		    model.addAttribute("incorrect", "exest");
			return "changePassword"  ;
			
		}else {
			model.addAttribute("newNcon", "error");
			return "changePassword"  ;
		}}
		catch (Exception e) {
			throw new CstLabsExpection("Exeoccered while accessing the data");
		}
		
		
		
		

		
	}

}
