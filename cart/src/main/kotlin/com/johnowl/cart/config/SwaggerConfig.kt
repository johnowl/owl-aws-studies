package com.johnowl.cart.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.PathSelectors.any
import springfox.documentation.builders.RequestHandlerSelectors.basePackage
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Contact
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@Configuration
@EnableSwagger2
class SwaggerConfig {

    @Bean
    fun api(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .produces(mutableSetOf("application/json"))
                .consumes(mutableSetOf("application/json"))
                .apiInfo(apiInfo())
                .select()
                .apis(basePackage("com.johnowl.cart.controller"))
                .paths(any())
                .build()
    }

    private fun apiInfo(): ApiInfo? {
        return ApiInfo("Cart API",
                "Shopping Cart API",
                "1.0.0",
                "",
                Contact("John Owl", "https://johnowl.com/", ""),
                "All rights reserved",
                "",
                emptyList())
    }

}