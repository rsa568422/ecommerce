package com.icodeap.ecommerce.application.service;

import com.icodeap.ecommerce.domain.User;
import com.icodeap.ecommerce.domain.UserType;
import com.icodeap.ecommerce.infrastructure.dto.UserDTO;

public class LoginService {

    private final UserService userService;

    public LoginService(UserService userService) {
        this.userService = userService;
    }

    public boolean existUser(UserDTO userDTO) {
        try {
            var user = userService.findByEmail(userDTO.getEmail());
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public Integer getUserId(String email) {
        try {
            return userService.findByEmail(email).getId();
        } catch (Exception e) {
            return 0;
        }
    }

    public UserType getUserType(UserDTO userDTO) {
        return userService.findByEmail(userDTO.getEmail()).getUserType();
    }

    public User getUser(String email) {
        try {
            return userService.findByEmail(email);
        } catch (Exception e) {
            return new User();
        }
    }
}
