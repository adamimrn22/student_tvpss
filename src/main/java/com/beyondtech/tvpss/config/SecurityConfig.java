package com.beyondtech.tvpss.config;

//import com.beyondtech.tvpss.auth.CustomAuthenticationFailureHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.beyondtech.tvpss.auth.ApiAuthenticationProvider;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

//	@Bean
//	public AuthenticationFailureHandler authenticationFailureHandler() {
//		return new CustomAuthenticationFailureHandler();
//	}


	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable()).authenticationProvider(authenticationProvider())
				.authorizeHttpRequests(authz -> authz
						.requestMatchers("/", "/resources/**", "/images/**", "/css/**", "/js/**").permitAll()
						// Notice we removed /login from permitAll above
						.requestMatchers("/dashboard", "/apply/**").authenticated().anyRequest().authenticated())
				.formLogin(form -> form.loginPage("/login").loginProcessingUrl("/login").usernameParameter("email")
						.passwordParameter("password").defaultSuccessUrl("/dashboard").failureUrl("/login?error=true")
						.permitAll())
				.logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/").permitAll());

		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		return new ApiAuthenticationProvider();
	}

}
