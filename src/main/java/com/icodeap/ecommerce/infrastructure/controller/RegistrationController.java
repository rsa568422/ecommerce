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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
@RequestMapping("/register")
public class RegistrationController {

    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping
    public String register(UserDTO userDTO) {
        return "register";
    }

    @PostMapping
    public String registerUser(@Valid UserDTO userDTO, BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(objectError -> log.info("ERROR: {}", objectError.getDefaultMessage()));
            return "register";
        }
        registrationService.register(userDTO.userDtoToUser());
        redirectAttributes.addFlashAttribute("success", "Usuario registrado correctamente");
        return "redirect:/register";
    }
}
