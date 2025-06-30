package net.engineeringdigest.journalApp.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI myCustomConfig(){
        return new OpenAPI().info(new Info()
                .title("Journal App API")
                .version("1.0.0")
                .description("API documentation for Journal App")
                .contact(new Contact()
                        .name("Deenanath")
                        .email("support@gmail.com")
                        .url("https://github.com/pdeenanath")))
                .tags(Arrays.asList(
                        new Tag().name("User APIs"),
                        new Tag().name("Journal APIs"),
                        new Tag().name("Admin APIs")
                ));
    }
}
