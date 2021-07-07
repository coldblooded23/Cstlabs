package com.cst.CstLabs.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.cst.CstLabs.model.CourseInfo;
import com.cst.CstLabs.model.SignUpInfo;
import com.cst.CstLabs.service.QuizService;
import com.cst.CstLabs.service.SignUpService;
import com.cst.CstLabs.service.VideoCountService;
import com.cst.CstLabs.utils.Constants;
import com.cst.CstLabs.utils.CstLabsExpection;

@Controller
public class LoginController {

	@Autowired
	VideoCountService videoCountService;
	
	
	@Autowired
	QuizService QuizService;

	
	@Autowired
	QuizService quizService;
	
	@Autowired
	SignUpService signUpService;
	private static Logger LOGGER = LogManager.getLogger(LoginController.class);

	@GetMapping("/login")
	public String HomePage() {
Authentication authentication=SecurityContextHolder.getContext().getAuthentication();

if(authentication==null||authentication instanceof AnonymousAuthenticationToken) {
	return "login";
}
	return "redirect:/";	
	
	}

	@GetMapping("/")
	public String Home() {
		return "index";
	}

	@GetMapping("/Course/{id}")
	public String Course( Model model, @PathVariable Long id) {
		
		
		try {
			CourseInfo courseInfo = quizService.getCourseDetails(id);
		System.out.println(courseInfo.getCourseId()+"-------------------------------------- "+courseInfo);
		
		model.addAttribute("courseinfo", courseInfo);
	
		return "video";
		}catch (Exception e) {
			// TODO: handle exception
			throw new CstLabsExpection("Error occered to while accing the data");
		}
	}

	

	@GetMapping("/coursee/{id}")
	public RedirectView video(Model model, HttpServletRequest request,@PathVariable Long id, RedirectAttributes attributes) {
		Principal principal = request.getUserPrincipal();

		System.out.println(id+"==============" + principal.getName());
		String Response = null;
		
		try {
			Response = videoCountService.ifUserWatchedVideo(principal.getName(),id);

			if (Response == "Video") {
				RedirectView redirectView = new RedirectView();
				
		
				redirectView.setUrl("/Course/"+id);
				return redirectView;
			} else if (Response == "Quiz") {
				return new RedirectView("/Quiz/"+id);
			}
			return new RedirectView("/ ?error");
		} catch (Exception e) {
			throw new CstLabsExpection("Error occered to while accing the data");
		}

	}
	
	
	
	@GetMapping("/Courses")
	public String getCompleatedAndIncompleatedCourse(Model model, HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		
		try {
			List<CourseInfo> InCompleatedCourses = quizService.getIncompleatedCourse(principal.getName());
			if(null==InCompleatedCourses) { 
				model.addAttribute("error", "error");
				return "Courses";
			}
			List<CourseInfo> CompleatedCourses = quizService.getCompleatedCourse(principal.getName());
			if(null==CompleatedCourses) { 
				model.addAttribute("error", "error");
				return "Courses";
			}
			model.addAttribute("InCompleatedCourses", InCompleatedCourses);
			model.addAttribute("CompleatedCourses", CompleatedCourses);
			return "Courses";
		
		}catch (Exception e) {
		throw new CstLabsExpection("exeption occered while accessing the data");
		}
		
		
	}
	
	@GetMapping("/profile")
  public String getUserDetails(Model model, HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		try {
			
			SignUpInfo responce = signUpService.getUser(principal.getName());
			model.addAttribute("userDetail", responce);
			return "UserProfile";
			  	
		}catch (Exception e) {
			throw new CstLabsExpection("exeption occered while accessing the data");
		}
	
  }
	@ModelAttribute("userr")
	public SignUpInfo signUpInfo() {
		return new SignUpInfo();

	}
	
	@PostMapping("/profile")
	public String UpdateUser( @ModelAttribute("userr") SignUpInfo ssignUpInfo,Model model) {

		try {
			
			String responce = signUpService.updateUser(ssignUpInfo);

			if (responce.equals(Constants.AuthenticationSuccess)) {
				SignUpInfo responcee = signUpService.getUser(ssignUpInfo.getEmailId());
				model.addAttribute("userDetail", responcee);
				model.addAttribute("success", "user");
				return "UserProfile";
			} else {
				model.addAttribute("error", "error");
				return "UserProfile";
			}

		} catch (Exception e) {
			throw new CstLabsExpection("exeption occered while accessing the data"+ e.getMessage());
		}

	}

}
