package com.cst.CstLabs.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cst.CstLabs.model.CourseInfo;
import com.cst.CstLabs.service.QuestionsService;
import com.cst.CstLabs.utils.CstLabsExpection;

@Controller
public class QuestionsController {
	
	
	
	@Autowired
	QuestionsService questionsService;
	
	
	@GetMapping("/Quiz")
	public String getQuestions() {
		return "quiz";
		
	}
	
	@GetMapping("/Quiz/{id}")
	public String getAllQuestions(Model model , @PathVariable Long id){
		
		try {
		CourseInfo responce =	questionsService.getQuestions(id);
			model.addAttribute("questions", responce);
		return "quiz";
		}catch (Exception e) {
			throw new CstLabsExpection("exeption occered while accessing the data");
		}
		
	}

}
