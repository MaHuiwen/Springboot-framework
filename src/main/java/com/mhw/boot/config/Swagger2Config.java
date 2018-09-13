package com.mhw.boot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger2的配置类
 *
 * 1.通过@Configuration来让Spring加载该类
 * 2.通过@EnableSwagger2注解来启动Swagger2
 *
 * @Author mhw_mhw
 * @Date 2018/7/6 15:15
 * @Version
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    /**
     * 1.通过@Bean创建Docket的bean
     * 2.apiInfo()用来创建该API的基本信息（这些基本信息会展现在文档页面）
     * 3.select()函数返回一个ApiSelectorBuilder实例用来控制哪些接口暴露给Swagger来展示
     * 4.这里通过指定扫描包路径来选择需要暴露的接口
     *
     * @param
     * @return Docket
     * @author mhw_mhw
     * @data 2018/7/6 15:13
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.mhw.boot.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Spring Boot中使用Swagger2构建RESTful APIs")
                .description("更多Spring Boot相关文章请关注：http://blog.didispace.com/")
                .termsOfServiceUrl("http://blog.didispace.com/")
                .contact("程序猿DD")
                .version("1.0")
                .build();
    }
}
