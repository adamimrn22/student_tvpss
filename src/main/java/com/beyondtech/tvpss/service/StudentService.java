package com.beyondtech.tvpss.service;

import com.beyondtech.tvpss.model.Student;
import com.beyondtech.tvpss.response.StudentApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class StudentService {
    private final RestTemplate restTemplate;
    private final String apiUrl = "http://localhost:8083/api/students/email/";
    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);

    public StudentService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Student getStudentInfo(String email) {
        String getStudentInfo = apiUrl + email;

        try {
            StudentApiResponse response = restTemplate.getForObject(getStudentInfo, StudentApiResponse.class);
            if (response != null && response.isSuccess()) {
                return response.getData();
            } else {
                logger.error("Failed to fetch student data for email: {}. Error message: {}", email, response != null ? response.getMessage() : "Unknown error");
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
