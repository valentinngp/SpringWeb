package org.valentingonzalezpuleo.practicaspringweb.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Gestión de Productos y Ventas - UTN")
                        .version("1.0.0")
                        .description("API RESTful desarrollada con Spring Boot para el portafolio personal. Incluye operaciones CRUD, validaciones y manejo global de excepciones.")
                        .contact(new Contact()
                                .name("Valentín")
                                .email("vgonzalezpuleo@gmail.com"))
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }
}
