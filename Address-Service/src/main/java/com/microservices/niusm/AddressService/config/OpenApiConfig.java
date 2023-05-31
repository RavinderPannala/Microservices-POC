package com.microservices.niusm.AddressService.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class OpenApiConfig {


    @Value("${address.openapi.dev-url}")
    private String devUrl;

    @Value("${address.openapi.prod-url}")
    private String prodUrl;

    @Bean
    public OpenAPI openAPI() {
        Server dev = new Server();
        dev.setUrl(devUrl);
        dev.setDescription("Server Url in Development Environment");

        Server prod = new Server();
        prod.setUrl(prodUrl);
        prod.setDescription("Server Url in Production Environment");

        Contact contact = new Contact();
        contact.setEmail("ravinder@gmail.com");
        contact.setName("Ravinder");
        contact.setUrl("https://localhost:8081");

        License mitLicense = new License().name("RAV License").url("https://choosealicense.com/licenses/RAV/");

        Info info = new Info().title("Address Management").contact(contact).description("This API exposes endpoints to manage tutorials.").termsOfService("https://localhost:8081/terms")
                .license(mitLicense).version("1.0");
        return new OpenAPI().info(info).servers(Arrays.asList(dev, prod));
    }
}
