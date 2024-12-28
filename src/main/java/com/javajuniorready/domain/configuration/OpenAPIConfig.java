package com.javajuniorready.domain.configuration;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    private static final Logger logger = LoggerFactory.getLogger(OpenApiConfig.class);
    @Bean
    public OpenAPI customOpenAPI() {
        logger.info("Creating OpenAPI...");
        OpenAPI openAPI = new OpenAPI()
                .info(new Info()
                        .title("My API")
                        .version("1.0")
                        .description("API documentation for my Spring Boot application"));
        logger.info("OpenAPI configuration finished");
        return openAPI;
    }
}
