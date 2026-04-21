package com.bookbuddy.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI bookBuddyOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("BookBuddy API")
                        .description("REST API для управления книгами, пользователями и отзывами")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("BookBuddy Team")
                                .email("support@bookbuddy.local")
                        )
                );
    }
}
