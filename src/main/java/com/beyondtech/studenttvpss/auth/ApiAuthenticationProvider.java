package com.beyondtech.studenttvpss.auth;

import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.beyondtech.studenttvpss.model.Student;
import com.beyondtech.studenttvpss.response.StudentApiResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ApiAuthenticationProvider implements AuthenticationProvider {
	private static final Logger logger = LoggerFactory.getLogger(ApiAuthenticationProvider.class);
	private final RestTemplate restTemplate = new RestTemplate();
	private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		try {
			String email = authentication.getName();
			String enteredIdCard = authentication.getCredentials().toString();

			System.out.println("Entered idcard: " + enteredIdCard);
			System.out.println("Entered email: " + email);


			logger.debug("Authentication attempt for email: {}", email);

			String apiUrl = "http://localhost:8083/api/students/email/" + email;
			String jsonResponse = restTemplate.getForObject(apiUrl, String.class);

			System.out.println("apiUrl: " + apiUrl);

			if (jsonResponse == null) {
				throw new AuthenticationException("Unable to connect to student API") {
				};
			}

			ObjectMapper objectMapper = new ObjectMapper();
			StudentApiResponse response = objectMapper.readValue(jsonResponse, StudentApiResponse.class);

			logger.debug("API Response: {}", response);

			if (response == null || !response.isSuccess() || response.getData() == null) {
				logger.debug("Invalid response data: {}", response);
				System.out.print("Invalid response data " + response);

				throw new AuthenticationException("Invalid credentials") {
				};
			}

 			Student student = response.getData();
			logger.debug("Retrieved Student: {}", student);
			System.out.print("Retrieved Student: {}" + student);
 			if (student.getIdentificationNumber() == null || !student.getIdentificationNumber().equals(enteredIdCard)) {
				System.out.print("Error Student: {}" + student);

				throw new AuthenticationException("Invalid identification card") {
				};

			}

 			return new UsernamePasswordAuthenticationToken(student, // Principal (student object)
					enteredIdCard, // Credentials
					Collections.singletonList(new SimpleGrantedAuthority("ROLE_STUDENT")));

		} catch (JsonProcessingException e) {
			logger.error("Error parsing API response: {}", e.getMessage());
			System.out.print("Error Student: {}" + e.getMessage());
			throw new AuthenticationException("Error processing authentication request") {
			}; // Specific message for JSON errors
		} catch (RestClientException e) {
			logger.error("API request failed: {}", e.getMessage());
			System.out.print("Error Student: {}" + e.getMessage());

			throw new AuthenticationException("Unable to communicate with the student API") {
			};
		} catch (Exception e) {
			System.out.print("Error Student: {}" + e.getMessage());

			logger.error("Unexpected error: {}", e.getMessage());
			throw new AuthenticationException("Authentication failed due to unexpected error") {
			};
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}

}
