package org.valentingonzalezpuleo.practicaspringweb.model.entities.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UserCreateDTO {
    @NotBlank(message = "El nombre de usuario no puede estar vacío")
    @Schema(description = "Nombre completo del usuario", example = "Juan Pérez")
    private String username;
    @NotBlank(message = "El email no puede estar vacío")
    @Email(message = "Debe tener un formato de correo válido")
    @Schema(description = "Correo electrónico institucional", example = "juan.perez@utn.edu.ar")
    private String email;
    @NotBlank
    @Schema(description = "Contraseña de acceso (mínimo 8 caracteres)", example = "Password123!")
    private String password;

    public UserCreateDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
