package com.icodeap.ecommerce.infrastructure.dto;

import com.icodeap.ecommerce.domain.User;
import com.icodeap.ecommerce.domain.UserType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDTO {

    private String username;

    @NotBlank(message = "Nombre es requerido")
    private String firstName;

    @NotBlank(message = "Apellido es requerido")
    private String lastName;

    @Email(message = "Debe ingresar un email válido")
    private String email;

    @NotBlank(message = "Dirección es requerido")
    private String address;

    @NotBlank(message = "Teléfono es requerido")
    private String cellphone;

    @NotBlank(message = "Contraseña es requerido")
    private String password;

    public User userDtoToUser() {
        return new User(null, email, firstName, lastName, email, address, cellphone, password, UserType.USER, LocalDateTime.now());
    }
}
