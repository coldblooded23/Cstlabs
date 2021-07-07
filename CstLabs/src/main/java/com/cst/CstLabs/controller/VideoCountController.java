package com.cst.CstLabs.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cst.CstLabs.service.VideoCountService;
import com.cst.CstLabs.utils.Constants;
import com.cst.CstLabs.utils.CstLabsExpection;

@Controller
public class VideoCountController {

	@Autowired
	VideoCountService videoCountService;

	private static Logger LOGGER = LogManager.getLogger(VideoCountController.class);


	@GetMapping("/quizz{id}")
	public String videoWatched(@RequestParam Long id, Model model, HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		String Email = principal.getName();
		System.out.println(" +++++++++++++++++++++++" + Email);
		LOGGER.info(" +++++++++++++++++++++++" + Email);
		try {

			String Response = videoCountService.AddVideoWatched(Email, id);

			if (Response.equals(Constants.AuthenticationSuccess)) {

				return "redirect:/Quiz/"+id;
			}
			model.addAttribute("ErrorMessage", "Error occered While Accessing the Data ");
			return "redirect:/course";

		} catch (Exception e) {
			throw new CstLabsExpection("Error occered While Accessing the Data");
		}
	}

}
