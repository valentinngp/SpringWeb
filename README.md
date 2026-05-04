#  API RESTful - Gestión de Ventas

![Java](https://img.shields.io/badge/Java-21-orange.svg)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4-brightgreen.svg)
![MySQL](https://img.shields.io/badge/MySQL-Database-blue.svg)

##  Descripción del Proyecto
API REST multicapa desarrollada para gestionar de forma eficiente el registro de usuarios, el inventario de productos y las transacciones de ventas. 

Este proyecto fue construido desde cero aplicando buenas prácticas de desarrollo backend, arquitectura multicapa (Controladores, Servicios, Repositorios) y principios SOLID.

##  Tecnologías y Herramientas
* **Lenguaje:** Java 21
* **Framework principal:** Spring Boot 3
* **Persistencia y Base de Datos:** Spring Data JPA (Hibernate) y MySQL
* **Documentación:** Swagger / OpenAPI
* **Utilidades:** Lombok, ModelMapper, Spring Validation API

##  Características Técnicas Destacadas
* **Arquitectura Limpia:** Separación clara de responsabilidades mediante Controladores (manejo HTTP) y Servicios (lógica de negocio).
* **Patrón DTO (Data Transfer Object):** Implementación de DTOs junto con ModelMapper para proteger la capa de persistencia (Entidades) y optimizar el tráfico de datos.
* **Manejo Global de Excepciones:** Sistema centralizado para capturar errores utilizando `@RestControllerAdvice`, devolviendo respuestas HTTP estandarizadas.
* **Validaciones de Entrada:** Aplicación de Validation API para garantizar la integridad de los datos antes de que lleguen a la base de datos.
* **Respuestas HTTP Profesionales:** Uso de `ResponseEntity` para gestionar correctamente los códigos de estado (200 OK, 201 Created, 204 No Content, 404 Not Found).

##  Documentación Interactiva
La totalidad de los endpoints de la API se encuentran documentados y pueden ser testeados interactivamente levantando el proyecto y accediendo a:
`http://localhost:8080/swagger-ui/index.html`

---
*Desarrollado por Valentín* 
