package com.beyondtech.tvpss.auth;

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

import com.beyondtech.tvpss.model.Student;
import com.beyondtech.tvpss.response.StudentApiResponse;
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

			logger.debug("Authentication attempt for email: {}", email);

			// Construct the API URL to get student details
			String apiUrl = "http://localhost:8083/api/students/email/" + email;
			String jsonResponse = restTemplate.getForObject(apiUrl, String.class);

			if (jsonResponse == null) {
				throw new AuthenticationException("Unable to connect to student API") {
				};
			}

			// Parse the JSON response
			ObjectMapper objectMapper = new ObjectMapper();
			StudentApiResponse response = objectMapper.readValue(jsonResponse, StudentApiResponse.class);

			logger.debug("API Response: {}", response);

			// Check if the API response is valid
			if (response == null || !response.isSuccess() || response.getData() == null) {
				logger.debug("Invalid response data: {}", response);
				throw new AuthenticationException("Invalid credentials") {
				}; // More generic message for security
			}

			// Retrieve student data from API response
			Student student = response.getData();
			logger.debug("Retrieved Student: {}", student);

			// Check if the entered identification number matches the student data
			if (student.getIdentificationNumber() == null || !student.getIdentificationNumber().equals(enteredIdCard)) {
				throw new AuthenticationException("Invalid identification card") {
				};
			}

			// Return a valid authentication token with user details and authorities
			return new UsernamePasswordAuthenticationToken(student, // Principal (student object)
					enteredIdCard, // Credentials
					Collections.singletonList(new SimpleGrantedAuthority("ROLE_STUDENT"))); // Granted authorities

		} catch (JsonProcessingException e) {
			logger.error("Error parsing API response: {}", e.getMessage());
			throw new AuthenticationException("Error processing authentication request") {
			}; // Specific message for JSON errors
		} catch (RestClientException e) {
			logger.error("API request failed: {}", e.getMessage());
			throw new AuthenticationException("Unable to communicate with the student API") {
			}; // Handle API call failures
		} catch (Exception e) {
			logger.error("Unexpected error: {}", e.getMessage());
			throw new AuthenticationException("Authentication failed due to unexpected error") {
			}; // Generic fallback
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}

}
