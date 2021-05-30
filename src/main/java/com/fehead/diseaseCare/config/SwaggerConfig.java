package com.fehead.diseaseCare.config;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Zero
 * @Date 2021/5/11 11:22
 * @Since 1.8
 **/
@Configuration
@EnableSwagger2
@Profile("prod")
public class SwaggerConfig {
    @Bean
    public Docket docket1() {
        ParameterBuilder parameterBuilder = new ParameterBuilder();
        List<Parameter> params = new ArrayList<>();
        parameterBuilder.name("token").description("请求令牌").modelRef(new ModelRef("string")).parameterType("header").required(true).build();
        params.add(parameterBuilder.build());
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("Default")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.fehead.diseaseCare.controller"))
                .paths(PathSelectors.ant("/**"))
                .build()
                .globalOperationParameters(params); //全局参数配置
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("智慧医疗文档")
                .description("用户管理")
                .contact(new Contact("ktoking", null, "ktoking@qq.com"))
                .version("0.0.1")
                .build();
    }
}
