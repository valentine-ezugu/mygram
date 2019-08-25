package com.valentine.gram.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        // This is invoked when user tries to access a secured REST resource without supplying any credentials
        // We should just send a 401 Unauthorized response because there is no 'login page' to redirect to
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
    }


//    boolean isPalindrome(String str) {
//        StringBuilder sb = new StringBuilder();
//        char[] chars = str.toCharArray();
//
//        for (int i = chars.length-1; i < chars.length; i--) {
//            sb.append(i);
//        }
//        return sb.toString().equals(str);
//    }

}

