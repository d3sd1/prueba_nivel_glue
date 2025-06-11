package org.glue.prueba_nivel;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Product Sorting API", version = "1.0", description = "API for sorting and scoring products"))
public class PruebaNivelApplication {
    public static void main(String[] args) {
        SpringApplication.run(PruebaNivelApplication.class, args);
    }
}
