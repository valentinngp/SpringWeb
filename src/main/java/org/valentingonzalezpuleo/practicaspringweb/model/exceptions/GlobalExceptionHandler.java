package org.valentingonzalezpuleo.practicaspringweb.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice // Combina @ControllerAdvice y @ResponseBody
public class GlobalExceptionHandler {

    // 1. Manejo de excepciones propias (Producto o Usuario no encontrado)
    @ExceptionHandler({ProductNotFoundException.class, UserNotFoundException.class})
    public ResponseEntity<ErrorDetalles> manejarRecursosNoEncontrados(RuntimeException ex, WebRequest request) {
        ErrorDetalles error = new ErrorDetalles(
                LocalDateTime.now(),
                ex.getMessage(), // Ej: "Producto no encontrado con ID: 5"
                request.getDescription(false) // Ej: "uri=/api/products/5"
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND); // Devuelve un 404
    }

    // 2. Manejo de errores del @Valid (Validation API)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> manejarValidaciones(MethodArgumentNotValidException ex) {
        Map<String, String> errores = new HashMap<>();

        // Recorro todos los errores que capturó el @Valid y armo un diccionario {campo: mensaje}
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errores.put(error.getField(), error.getDefaultMessage());
        });

        return new ResponseEntity<>(errores, HttpStatus.BAD_REQUEST); // Devuelve un 400 Bad Request
    }

    // 3. Manejo de cualquier error inesperado (Failsafe)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetalles> manejarExcepcionGlobal(Exception ex, WebRequest request) {
        ErrorDetalles error = new ErrorDetalles(
                LocalDateTime.now(),
                "Ocurrió un error interno en el servidor. Por favor, intente más tarde.",
                request.getDescription(false)
        );
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR); // Devuelve un 500
    }
}
