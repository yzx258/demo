package com.ymc.demo.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author yucw
 * @date 2020/1/19 9:10
 */
/**
 * 拦截所有请求
 * Created by yanshao on 2019/4/30.
 */
@SpringBootConfiguration
public class SwaggerConfig implements WebMvcConfigurer {

    //    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(securityIntercept()).addPathPatterns("/**");
//    }
//
//    @Bean
//    public SecurityIntercept securityIntercept() {
//        return new SecurityIntercept();
//    }

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ymc.demo"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("DEMO接口文档")
                .description("DEMO接口文档 Swagger2 RESTful 风格的接口文档")
                .termsOfServiceUrl("")
                .version("2.0")
                .build();
    }

}
