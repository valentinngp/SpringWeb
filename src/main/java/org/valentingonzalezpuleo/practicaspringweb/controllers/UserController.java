package org.valentingonzalezpuleo.practicaspringweb.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.valentingonzalezpuleo.practicaspringweb.model.entities.dtos.UserCreateDTO;
import org.valentingonzalezpuleo.practicaspringweb.model.entities.dtos.UserDTO;
import org.valentingonzalezpuleo.practicaspringweb.model.entities.dtos.UserUpdateDTO;
import org.valentingonzalezpuleo.practicaspringweb.model.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@Tag(name = "Usuarios", description = "Operaciones relacionadas con la gestión de usuarios")
public class UserController {
    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.findAll());

    }

    @GetMapping("/search")
    public ResponseEntity<UserDTO> searchUserByUserName(@RequestParam String userName) {
        UserDTO user = userService.findByUsername(userName);
        return ResponseEntity.ok().body(user);
    }
    @Operation(summary = "Crear un nuevo usuario", description = "Registra un usuario en el sistema a partir de sus datos básicos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuario creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    })
    //POST: Crear un usuario.
    @PostMapping
    public ResponseEntity<Void> createUser(@Valid @RequestBody UserCreateDTO userCreateDTO) {
        // El servicio recibe el DTO y se encarga del mapeo
        userService.save(userCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    //PUT: Actualizar un usuario.
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable long id, @Valid @RequestBody UserUpdateDTO userUpdateDTO) {
        // Aquí deberías implementar la lógica en el servicio similar a save
        // Por ahora lo dejamos consistente con la estructura
        userService.update(id, userUpdateDTO);
        return ResponseEntity.noContent().build();
    }

    //DELETE: Eliminar un usuario.
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    /// WebRequest
    @GetMapping("/detalles")
    public ResponseEntity<String> obtenerDetallesPeticion(WebRequest request) {
        // WebRequest nos permite sacar datos sin usar tantas anotaciones
        String agent = request.getHeader("User-Agent"); // Vemos qué programa nos llama
        String locale = request.getLocale().getDisplayLanguage(); // Vemos el idioma del cliente

        String respuesta = "Estás consultando desde: " + agent + " en el idioma: " + locale;

        return ResponseEntity.ok().body(respuesta);
    }

    /// buscar por id
     @Operation(summary = "Obtener usuario por ID")
     @ApiResponses(value = {
         @ApiResponse(responseCode = "200", description = "Usuario encontrado"),
         @ApiResponse(responseCode = "404", description = "El ID proporcionado no pertenece a ningún usuario")
     })
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findById(id));
    }


}
