package tech.dev.eVoyageBackend.utils;


import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("eVoyageBackend API")
                        .version("1.0")
                        .description("Documentation des APIs de l'application eVoyageBackend"));
    }
}
