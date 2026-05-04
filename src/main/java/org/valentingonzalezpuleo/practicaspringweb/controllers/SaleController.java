package org.valentingonzalezpuleo.practicaspringweb.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.valentingonzalezpuleo.practicaspringweb.model.entities.dtos.SaleDTO;
import org.valentingonzalezpuleo.practicaspringweb.model.entities.dtos.SaleRequestDTO;
import org.valentingonzalezpuleo.practicaspringweb.model.services.SaleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sales")
@RequiredArgsConstructor
@Tag(name = "Ventas", description = "Endpoints para la gestión de ventas de productos")
public class SaleController {

    private final SaleService saleService;

    @Operation(
            summary = "Registrar una nueva venta",
            description = "Crea una venta vinculando un producto y un usuario existentes mediante sus IDs."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Venta registrada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
            @ApiResponse(responseCode = "404", description = "Producto o Usuario no encontrado")
    })
    @PostMapping
    public ResponseEntity<SaleDTO> registrarVenta(@Valid @RequestBody SaleRequestDTO saleRequestDTO) {
        // Si uso @Valid, el JSON no trae productId o quantity es <= 0, salta el GlobalExceptionHandler
        SaleDTO nuevaVenta = saleService.registrarVenta(saleRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaVenta);
    }

    @Operation(summary = "Listar todas las ventas", description = "Retorna el historial completo de ventas con detalles de productos " +
            "y usuarios.")
    @ApiResponse(responseCode = "200", description = "Lista de ventas obtenida correctamente")
    @GetMapping
    public ResponseEntity<List<SaleDTO>> obtenerTodasLasVentas() {
        return ResponseEntity.ok(saleService.listarVentas());
    }

}
