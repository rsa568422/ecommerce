package com.icodeap.ecommerce.infrastructure.controller;

import com.icodeap.ecommerce.application.service.LoginService;
import com.icodeap.ecommerce.domain.UserType;
import com.icodeap.ecommerce.infrastructure.dto.UserDTO;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/login")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping
    public String login() {
        return "login";
    }

    @PostMapping
    public String access(UserDTO userDTO, HttpSession httpSession) {
        userDTO.setEmail(userDTO.getUsername());
        log.info("userDTO.email: {}", userDTO.getEmail());
        log.info("userDTO.pass: {}", userDTO.getPassword());
        if (loginService.existUser(userDTO)) {
            httpSession.setAttribute("iduser", loginService.getUserId(userDTO.getEmail()));
            if (UserType.ADMIN.equals(loginService.getUserType(userDTO))) {
                return "redirect:/admin";
            } else {
                return "redirect:/home";
            }
        }
        return "redirect:/home";
    }
}
