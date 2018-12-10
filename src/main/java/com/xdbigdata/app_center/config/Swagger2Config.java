package com.xdbigdata.app_center.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by tangyijun on 2017/2/24.
 * good good study,day day up!
 */
@Configuration
@EnableSwagger2
@Profile({"dev", "prod","test"})
public class Swagger2Config {
    @Value("${my-config.controller-package}")
    private String controllerPackage;

    @Value("${my-config.swagger.title}")
    private String title;

    @Value("${my-config.swagger.description}")
    private String description;

    @Value("${my-config.swagger.contact}")
    private String contact;

    @Value("${my-config.swagger.version}")
    private String version;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(controllerPackage))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(title)
                .description(description)
                .contact(contact)
                .version(version)
                .build();
    }
}
