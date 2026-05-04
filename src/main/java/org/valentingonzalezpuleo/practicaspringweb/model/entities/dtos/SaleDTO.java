package org.valentingonzalezpuleo.practicaspringweb.model.entities.dtos;

import lombok.Data;
import java.time.LocalDate;

@Data
public class SaleDTO {
    private Long id;
    private ProductDTO product; // Devuelve el producto completo
    private UserDTO user;       // Devuelve el usuario completo
    private Long quantity;
    private LocalDate date;
}
