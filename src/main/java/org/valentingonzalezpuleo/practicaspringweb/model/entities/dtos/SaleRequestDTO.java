package org.valentingonzalezpuleo.practicaspringweb.model.entities.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class SaleRequestDTO {

    @NotNull(message = "El ID del producto es obligatorio")
    private Long productId;

    @NotNull(message = "El ID del usuario es obligatorio")
    private Long userId;

    @Positive(message = "La cantidad debe ser mayor a 0")
    private Long quantity;
}
