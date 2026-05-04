package org.valentingonzalezpuleo.practicaspringweb.model.entities.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.valentingonzalezpuleo.practicaspringweb.model.entities.Crear;

public class UserUpdateDTO {
    @NotBlank(message = "El email no puede estar vacío")
    @Email(message = "Debe tener un formato de correo válido")
    private String email;

    @NotBlank(groups = {Crear.class})
    private String password;

    public UserUpdateDTO() {}

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
