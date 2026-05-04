package org.valentingonzalezpuleo.practicaspringweb.model.entities.dtos;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data // me da getters, setters, toString, etc.
public class ProductDTO {

    private Long id;

    @NotBlank(message = "El nombre de producto no puede estar vacío")
    @Size(min = 6, max = 10, message = "El nombre debe tener entre 6 y 10 caracteres")
    private String name;

    @PositiveOrZero(message = "El precio no puede ser negativo")
    private double price;

    @Size(min = 1, max = 20, message = "La descripción debe tener entre 1 y 20 caracteres")
    private String description;
}
