package org.valentingonzalezpuleo.practicaspringweb.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.valentingonzalezpuleo.practicaspringweb.model.entities.dtos.ProductDTO;
import org.valentingonzalezpuleo.practicaspringweb.model.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products") // Defino la ruta base para todos los endpoints de esta clase
@RequiredArgsConstructor // Inyecto el ProductService automáticamente
public class ProductController {

    private final ProductService productService;

    // GET /api/products -> Obtener todos
    @Operation(
            summary = "Obtener todos los productos",
            description = "Devuelve una lista completa de todos los productos almacenados en la base de datos."
    )
    @ApiResponse(responseCode = "200", description = "Lista de productos obtenida exitosamente")
    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        // Retorno un 200 OK con la lista de productos
        return ResponseEntity.ok(productService.findAll());
    }

    // GET /api/products/{id} -> Obtener uno específico
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        // Retorno un 200 OK con el producto encontrado
        return ResponseEntity.ok(productService.findById(id));
    }

    // POST /api/products -> Crear un producto nuevo
    @Operation(
            summary = "Crear un nuevo producto",
            description = "Registra un nuevo producto en el sistema. Requiere validación de nombre, precio y descripción."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Producto creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Error de validación en los datos enviados")
    })
    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@Valid @RequestBody ProductDTO productDTO) {
        // La anotación @Valid activa las validaciones (@NotBlank, @Size) del DTO
        ProductDTO savedProduct = productService.save(productDTO);
        // Retorno un 201 CREATED indicando que el recurso se creó con éxito
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }

    // DELETE /api/products/{id} -> Eliminar un producto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.delete(id);
        // Retorno un 204 NO CONTENT indicando éxito pero sin devolver un cuerpo
        return ResponseEntity.noContent().build();
    }
}