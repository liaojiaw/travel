package com.xmairtravel.manager.api.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;

/**
 * Swagger的配置,不同的工程,请修改apInfo函数中的代码
 */
@Configuration
@EnableKnife4j
@EnableSwagger2
public class SwaggerConfig {

    @Value("${swagger.enable:true}")
    private Boolean enable;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(enable)
                .apiInfo(apiInfo())
                .genericModelSubstitutes(DeferredResult.class)
                .genericModelSubstitutes(ResponseEntity.class)
                .genericModelSubstitutes(Callable.class)
                .genericModelSubstitutes(CompletableFuture.class)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.xmairtravel.manager.api.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("接驳车预定系统")
                .description("[应用名]与文档")
                .termsOfServiceUrl("http://www.xiamenair.com/")
                .contact(new Contact("dev","https://github.com","1328519603@qq.com"))
                .version("1.0")
                .build();
    }
}
