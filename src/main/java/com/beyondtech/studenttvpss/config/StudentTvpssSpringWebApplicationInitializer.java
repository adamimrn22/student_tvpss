package com.beyondtech.studenttvpss.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class StudentTvpssSpringWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] { RootConfig.class, SecurityConfig.class }; // Root config class for student app
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { WebAppConfig.class }; // Web config class for student app
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" }; // Servlet mapping
	}

	@Override
	protected String getServletName() {
		return "studenttvpss-dispatcher"; // Use a unique name for your dispatcher servlet
	}


}
