package com.beyondtech.studenttvpss.controller;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.beyondtech.studenttvpss.config.SecurityUtils;
import com.beyondtech.studenttvpss.model.ApplicationStatus;
import com.beyondtech.studenttvpss.model.Student;
import com.beyondtech.studenttvpss.model.TvpssCrew;
import com.beyondtech.studenttvpss.model.TvpssPosition;
import com.beyondtech.studenttvpss.service.TvpssCrewService;
import com.beyondtech.studenttvpss.service.TvpssPositionService;

@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	private final TvpssPositionService tvpssPositionService;
	private final TvpssCrewService tvpssCrewService;

	@Autowired
	public HomeController(TvpssPositionService tvpssPositionService, TvpssCrewService tvpssCrewService) {
		this.tvpssPositionService = tvpssPositionService;
		this.tvpssCrewService = tvpssCrewService;
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
	public String dashboard(Model model, Student student) {
		List<TvpssCrew> tvpssApplications = tvpssCrewService
				.getCrewApplicationByIdentificationNumber(student.getIdentificationNumber());
		model.addAttribute("tvpssApplications", tvpssApplications);
		logger.debug("list of application: ", tvpssApplications);
		logger.debug("list of application: ", student.getIdentificationNumber());
		return "dashboard";
	}

	@GetMapping("/apply")
	public String apply(Model model, Student student) {
		List<TvpssPosition> roles = tvpssPositionService.getPositionsBySchoolCode(student.getSchool().getCode());
		model.addAttribute("roles", roles);
		System.out.println(roles);
		logger.debug("list of roles: ", roles);

		return "apply";
	}

	@PostMapping("/apply")
	public String submitApplication(Model model, @ModelAttribute TvpssCrew tvpssCrew, Student student,
			RedirectAttributes redirectAttributes) {

		if (tvpssCrew.getIdentificationNumber() == null) {
			tvpssCrew.setIdentificationNumber(student.getIdentificationNumber());
		}

		if (tvpssCrew.getName() == null) {
			tvpssCrew.setName(student.getName());
		}

		if (tvpssCrew.getEmail() == null) {
			tvpssCrew.setEmail(student.getEmail());
		}

		if (tvpssCrew.getSchoolCode() == null) {
			tvpssCrew.setSchoolCode(student.getSchool().getCode());
		}

		if (tvpssCrew.getStatus() == null) {
			tvpssCrew.setStatus(ApplicationStatus.PENDING);
		}

		if (tvpssCrew.getDateApplied() == null) {
			tvpssCrew.setDateApplied(new Date());
		}

		tvpssCrewService.saveCrew(tvpssCrew);

		logger.debug("Crew member applied: {}", tvpssCrew);

		redirectAttributes.addFlashAttribute("alertMessage", "Permohonan Dihantar Berjaya!");

		return "redirect:/dashboard";
	}

	@GetMapping("/apply/detail/{id}")
	public String applicationDetail(@PathVariable("id") Long id, Model model) {
		TvpssCrew tvpssCrew = tvpssCrewService.getApplicationById(id);

		if (tvpssCrew == null) {
			model.addAttribute("errorMessage", "Application not found.");
			return "error";
		}

		model.addAttribute("tvpssCrew", tvpssCrew);
		return "application-detail";
	}

}
