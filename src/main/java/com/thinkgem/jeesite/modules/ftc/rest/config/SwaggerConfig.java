package com.thinkgem.jeesite.modules.ftc.rest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@EnableWebMvc
@ComponentScan(basePackages = {"com.thinkgem.jeesite.modules.ftc.rest"})
public class SwaggerConfig {

	
   @Bean
    public Docket platformApi() {
        return new Docket(DocumentationType.SWAGGER_2)
        		.groupName("f2c-web")
                .apiInfo(apiInfo())  
                .forCodeGeneration(true);
    }  

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("F2C RESTful APIs")
                .description("Copyright © 2017, 王宁, F2C. All Rights Reserved.")
                .contact(new Contact("王宁", "https://github.com/wangning82/FTC", "wa_ning@163.com"))
                .license("Apache License Version 2.0")
                .termsOfServiceUrl("https://github.com/wangning82/FTC")
                .version("1.0.0-SNAPSHOT")
                .build();  
    }  
}
