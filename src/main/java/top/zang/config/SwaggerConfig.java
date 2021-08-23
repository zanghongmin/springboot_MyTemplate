package top.zang.config;

import springfox.documentation.service.*;
import springfox.documentation.spi.service.contexts.SecurityContext;
import top.zang.config.token.MyToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ScalarType;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.singletonList;

@Configuration
public class SwaggerConfig {

    @Value("${swagger.enable}")
    private boolean swaggerEnable;
    String web_package = "top.zang.controller";
    String title = "springboot自定义模板系统";
    String version = "1.0";
    String description = "<h4>全局文档说明<h4>";

    @Bean
    public Docket buildDocket(){
        Docket docket =  new Docket(DocumentationType.OAS_30)
                .enable(swaggerEnable)
                .apiInfo(buildApiInf())
                .select()
                .apis(RequestHandlerSelectors.basePackage(web_package))
                .paths(PathSelectors.any())
                .build();
            if (swaggerEnable) {
                docket.securitySchemes(securitySchemes()).securityContexts(securityContexts());
            }
        return docket;
    }

    private List<SecurityScheme> securitySchemes() {
        //设置请求头信息
        List<SecurityScheme> result = new ArrayList<>();
        ApiKey apiKey = new ApiKey(MyToken.Authorization_TOKEN, MyToken.Authorization_TOKEN, "header");
        result.add(apiKey);
        return result;
    }

    private List<SecurityContext> securityContexts() {
        //设置需要登录认证的路径
        List<SecurityContext> result = new ArrayList<>();
        result.add(getContextByPath("/*/.*"));
        return result;
    }

    private SecurityContext getContextByPath(String pathRegex) {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex(pathRegex))
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        List<SecurityReference> result = new ArrayList<>();
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        result.add(new SecurityReference(MyToken.Authorization_TOKEN, authorizationScopes));
        return result;
    }

    private ApiInfo buildApiInf(){
        return new ApiInfoBuilder()
                .title(title)
                .version(version)
                .description(description)
                .build();
    }


}
