package com.leads.microcube.verifidadmin.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
  
  @Bean
  OpenAPI apiInfo() {
    return new OpenAPI()
            .info(
                    new Info()
                            .title("Service Provider")
                            .description("Service Provider")
                            .version("1.0.0")
            );
  }
}