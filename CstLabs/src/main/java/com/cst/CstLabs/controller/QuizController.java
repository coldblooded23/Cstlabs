package com.cst.CstLabs.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.cst.CstLabs.model.CourseInfo;
import com.cst.CstLabs.model.QuizInfo;
import com.cst.CstLabs.model.ResultInfo;
import com.cst.CstLabs.service.QuizService;
import com.cst.CstLabs.utils.CstLabsExpection;



@Controller
public class QuizController {
	
	
	@Autowired
	QuizService quizService;
	

	
	@ModelAttribute("answers")
	public QuizInfo quizinfo(){
		return new QuizInfo();
		
	}
	
	@PostMapping("/quizSubmit")
	public String Result(@ModelAttribute("answers") QuizInfo quizInfo, String id,Model model,HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		System.out.println("========================"+principal.getName());
		
		ResultInfo resultInfo = quizService.result(quizInfo, principal.getName());
		model.addAttribute("resultInfo", resultInfo);
		return "result";	
	}
	
	
	
	
	@GetMapping("/Certificate/{id}")
	public String certificate(HttpServletRequest request,Model model,@PathVariable Long id) {
		Principal principal = request.getUserPrincipal();	
		ResultInfo resultinfo=null;
		
		CourseInfo courseInfo=null;
		try {
			System.out.println("========================"+principal.getName());
			resultinfo=quizService.getCertificateDetails(principal.getName(),id);
			courseInfo =quizService.getCourseDetails(id);
			if(resultinfo==null) {
				model.addAttribute("noCertificate", "no");
				return "redirect:/";
			}
			model.addAttribute("courseInfo", courseInfo);
			model.addAttribute("resultinfo", resultinfo);
			return "crtificate";
			
		}catch (Exception e) {
			throw new CstLabsExpection("error occered while accing the data");
		}
	
	}

}
