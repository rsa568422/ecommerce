package com.icodeap.ecommerce.application.service;

import jakarta.servlet.http.HttpSession;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class LogoutService {

    public void logout(HttpSession httpSession) {
        httpSession.removeAttribute("iduser");
    }
}
