package com.icodeap.ecommerce.infrastructure.service;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

@Component
public class LoginHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {
        var redirectURL = new AtomicReference<>(request.getContextPath());
        var userDetails = (UserDetails) authentication.getPrincipal();
        userDetails.getAuthorities().forEach(grantedAuthority -> {
            if ("ROLE_ADMIN".equals(grantedAuthority.getAuthority())) {
                redirectURL.set("/admin");
            } else {
                redirectURL.set("/home");
            }
        });
        response.sendRedirect(redirectURL.get());
    }
}
