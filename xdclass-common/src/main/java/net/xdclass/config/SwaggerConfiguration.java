package net.xdclass.config;

import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author WJH
 * @class xdclass-1024-shop SwaggerConfiguration
 * @date 2021/7/11 下午12:38
 * @QQ 1151777592
 */
@Component
@Data
@EnableOpenApi
public class SwaggerConfiguration {

    /**
     * 对c端的用户的接口文档
     * @return
     */
    @Bean
    public Docket webApiDoc(){

        return new Docket(DocumentationType.OAS_30)
                .groupName("用户接口文档")
                .pathMapping("/")
                //是否开启Swagger，线上关闭
                .enable(true)
                //配置文档的元信息
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("net.xdclass"))
                .paths(PathSelectors.ant("/api/**"))
                .build();
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("1024电商平台")
                .description("微服务接口文档")
                .contact(new Contact("王建欢","https://wangjianhuan.com","1151777592@qq.com"))
                .version("v1.0")
                .build();
    }

    /**
     * 对管理端的接口文档
     * @return
     */
    @Bean
    public Docket AdminApiDoc(){

        return new Docket(DocumentationType.OAS_30)
                .groupName("管理端接口文档")
                .pathMapping("/")
                //是否开启Swagger，线上关闭
                .enable(true)
                //配置文档的元信息
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("net.xdclass"))
                .paths(PathSelectors.ant("/api/**"))
                .build();
    }

}
