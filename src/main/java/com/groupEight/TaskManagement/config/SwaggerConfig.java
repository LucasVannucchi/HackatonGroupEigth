package com.groupEight.TaskManagement.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        final String securitySchemeName = "bearerAuth";

        return new OpenAPI()
                .info(new Info()
                        .title("Sistema de Gerenciamento de Tarefas")
                        .description("API para Gerenciamento de Tarefas - Documentada com Swagger")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Equipe Grupo 10")
                                .email("contato@grupo10.com")))
                // adiciona corretamente o esquema de segurança
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName,
                                new SecurityScheme()
                                        .name(securitySchemeName)
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")))
                // adiciona o requisito de segurança global
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName));
    }
}
