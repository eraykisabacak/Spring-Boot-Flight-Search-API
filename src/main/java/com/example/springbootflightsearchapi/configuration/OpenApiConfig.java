package com.example.springbootflightsearchapi.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                contact= @Contact(
                        name="Ali Eray KISABACAK",
                        email="eraykisabacak@hotmail.com",
                        url="https://github.com/eraykisabacak"
                ),
                description = "Spring Boot Flight Search API",
                title = "Spring Boot Flight Search API",
                version = "1.0"
        ),
        servers = {
                @Server(
                        description = "Local ENV",
                        url = "http://localhost:8080"
                )
        }
)
public class OpenApiConfig {
}