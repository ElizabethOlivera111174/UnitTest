package com.UnitTest.UnitTestMokito.Config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.context.annotation.Configuration;


@Configuration
@OpenAPIDefinition(info = @Info(title = "Banco",
        description = "Trabajo Practico de pruebas Unitarias",
        version = "1.Muchas",
        contact = @Contact(
                name = "Elizabeth Olivera Gutierrez",
                email = "eliolivera@gmail.com"
        ),
        license = @License(
                name = "Curso de Udemi"
        )
))
public class OpenApiConfig {

  }
