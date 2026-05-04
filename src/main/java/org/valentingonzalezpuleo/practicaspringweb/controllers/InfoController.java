package org.valentingonzalezpuleo.practicaspringweb.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/info")
public class InfoController {
///HttpServletRequest
    @GetMapping
    public ResponseEntity<String> getRequestInfo(HttpServletRequest request) {

        // Espiamos los datos de bajo nivel de la petición
        String ip = request.getRemoteAddr(); // La IP de quien nos llama [cite: 179]
        String metodo = request.getMethod(); // GET, POST, PUT, etc. [cite: 180]
        String navegador = request.getHeader("User-Agent"); // Qué programa está usando

        // Armamos un mensaje con todo lo que descubrimos
        String mensaje = "Método usado: " + metodo + "\n" +
                "IP del cliente: " + ip + "\n" +
                "Programa/Navegador (User-Agent): " + navegador;

        return ResponseEntity.ok().body(mensaje);
    }
}
