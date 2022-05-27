package com.teste.entregas.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    public static final List<String> PATHS = Arrays.asList("/swagger-resources",
            "/swagger-resources/configuration/ui", "/swagger-resources/configuration/security", "/swagger-ui.html",
            "/webjars/springfox-swagger-ui/favicon-32x32.png", "/webjars/springfox-swagger-ui/swagger-ui-bundle.js",
            "/webjars/springfox-swagger-ui/swagger-ui.css",
            "/webjars/springfox-swagger-ui/swagger-ui-standalone-preset.js", "/webjars/**/**");

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.teste"))
                .paths(PathSelectors.any())
                .build();
    }


    private ApiInfo metaData(){
        return new ApiInfoBuilder()
                .title("Serviço Pedidos")
                .description("Funcionalidades de pedidos")
                .version("3.0.0")
                .build();
    }
}