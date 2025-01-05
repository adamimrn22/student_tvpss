package com.beyondtech.tvpss.controller;

import com.beyondtech.tvpss.model.Student;
import com.beyondtech.tvpss.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.beyondtech.tvpss.config.SecurityUtils;

@Controller
public class HomeController {

	private final StudentService studentService;

	@Autowired
	public HomeController(StudentService studentService) {
		this.studentService = studentService;
	}


	@GetMapping("/login")
	public String loginPage(Model model, @RequestParam(value = "error", required = false) String error) {
		if (SecurityUtils.isAuthenticated()) {
			return "redirect:/dashboard";
		}

		if (error != null) {
			model.addAttribute("error", "Authentication failed. Please check your credentials.");
		}

		return "login";
	}

	@GetMapping("/")
	public String index(Model model) {

		return "index";
	}

	@GetMapping("/dashboard")
	public String dashboard(Model model) {

		return "dashboard";
	}

	@GetMapping("/apply")
	public String apply(Model model) {
		return "apply";
	}

	@GetMapping("/apply/detail")
	public String applicationdetail(Model model) {
		return "application-detail";
	}

}
