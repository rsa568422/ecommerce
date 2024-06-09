package com.icodeap.ecommerce.infrastructure.controller;

import com.icodeap.ecommerce.application.service.RegistrationService;
import com.icodeap.ecommerce.infrastructure.dto.UserDTO;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/register")
public class RegistrationController {

    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping
    public String register() {
        return "register";
    }

    @PostMapping
    public String registerUser(@Valid UserDTO user, BindingResult bindResult) {
        if (bindResult.hasErrors()) {
            bindResult.getAllErrors().forEach(objectError -> log.info("ERROR: {}", objectError.getDefaultMessage()));
        }
        registrationService.register(user.userDtoToUser());
        return "redirect:/register";
    }
}
