package com.beyondtech.studenttvpss.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.beyondtech.studenttvpss.model.Student;

@Component
@ControllerAdvice
public class GlobalControllerAdvice {

	@ModelAttribute
	public void addAuthenticatedUserToModel(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.isAuthenticated()) {
			Object principal = authentication.getPrincipal();
			if (principal instanceof Student student) { // If principal is a Student object
                model.addAttribute("student", student); // Add the entire Student object to the model
			}
		}
	}
}
