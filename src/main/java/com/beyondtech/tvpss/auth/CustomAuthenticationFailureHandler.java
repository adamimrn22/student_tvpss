//package com.beyondtech.tvpss.auth;
//
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.authentication.AuthenticationFailureHandler;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
//
//    @Override
//    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
//                                        AuthenticationException exception) throws IOException, ServletException {
//
//        String errorMessage = exception.getMessage();  // Get the error message from the exception
//
//        // You can add a custom error message to the model or redirect to a login page
//        request.getSession().setAttribute("error", errorMessage);  // Set error message in session
//        response.sendRedirect("/login?error=true");  // Redirect with an error query parameter
//    }
//}
