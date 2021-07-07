package com.cst.CstLabs.controller;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cst.CstLabs.model.SignUpInfo;
import com.cst.CstLabs.service.SignUpService;
import com.cst.CstLabs.utils.Constants;
import com.cst.CstLabs.utils.CstLabsExpection;

@Controller
@RequestMapping("/signup")
public class SignUpController {

	@Autowired
	SignUpService signUpService;

	private static Logger LOGGER = LogManager.getLogger(SignUpController.class);

	@GetMapping
	public String SignUp() {

		return "register";

	}

	@ModelAttribute("user")
	public SignUpInfo signUpInfo() {
		return new SignUpInfo();

	}

	@PostMapping
	public String contactSubmit(@Valid @ModelAttribute("user") SignUpInfo ssignUpInfo, BindingResult bindingResult,
			Model model) {
		if (bindingResult.hasErrors()) {
			System.out.println("BINDING RESULT ERROR");
			LOGGER.info("BINDING RESULT ERROR");
			return "register";
		}
		if (ssignUpInfo.getPassword().equals(ssignUpInfo.getConfirmPassword())) {
			String Response = signUpService.createUser(ssignUpInfo);
			if (Response.contains("already exists")) {
				model.addAttribute("Exests", "Email already exists");
				return "register";
			}
			if (Response.contains(Constants.AuthenticationSuccess))
				model.addAttribute("success", "success");
			return "register";
		}
		model.addAttribute("pass", "pass");
		return "register";
	}



}
