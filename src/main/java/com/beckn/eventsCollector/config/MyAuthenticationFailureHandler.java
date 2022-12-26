package com.beckn.eventsCollector.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response, AuthenticationException exception)
            throws IOException, AuthenticationException {

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> errorObject = new HashMap<>();
        int errorCode = 401;
        errorObject.put("message", "Unauthorized access of protected resource, invalid credentials");
        errorObject.put("error", HttpStatus.UNAUTHORIZED.toString());
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(errorCode);
        response.getWriter().write(objectMapper.writeValueAsString(errorObject));
    }

}