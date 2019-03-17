package pl.dziedziul.videorentalstore.infra;

import static springfox.documentation.builders.RequestHandlerSelectors.basePackage;
import static springfox.documentation.spi.DocumentationType.SWAGGER_2;

import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import pl.dziedziul.videorentalstore.Application;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
class SwaggerConfig {
    @Bean
    Docket productApi() {
        return new Docket(SWAGGER_2)
            .produces(Set.of("application/json;charset=UTF-8"))
            .consumes(Set.of("application/json;charset=UTF-8"))
            .select()
            .apis(basePackage(Application.class.getPackageName()))
            .build();
    }
}
