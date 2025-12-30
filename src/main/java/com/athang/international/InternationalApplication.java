package com.athang.international;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "BOB International Payment Gateway acceptance API",version = "1.0",description = "API documentation for Athang to support International Payment Gateway integrations")
)
public class InternationalApplication {

    public static void main(String[] args) {
        SpringApplication.run(InternationalApplication.class, args);
    }

}
