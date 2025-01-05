package com.beyondtech.tvpss.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/login")
	public String login(Model model) {
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
