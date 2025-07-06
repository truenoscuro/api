package org.nofre.api.base.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        // El nombre que daremos al esquema de seguridad. Puede ser cualquiera.
        final String securitySchemeName = "bearerAuth";

        return new OpenAPI()
                // Primero, definimos el esquema de seguridad.
                // En este caso, es de tipo HTTP, con esquema "bearer" y formato "JWT".
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName,
                                new SecurityScheme()
                                        .name(securitySchemeName)
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                        )
                )
                // Después, aplicamos ese esquema de seguridad globalmente a todos los endpoints.
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                // (Opcional) Añadimos información general sobre la API.
                .info(new Info()
                        .title("Mi API")
                        .version("v1.0.0")
                        .description("Descripción de la API para el proyecto.")
                );
    }
}
