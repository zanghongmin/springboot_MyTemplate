package top.zang.config;

import top.zang.config.token.MyToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ScalarType;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ParameterType;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

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
        return new Docket(DocumentationType.OAS_30)
                .enable(swaggerEnable)
                .apiInfo(buildApiInf())
                .select()
                .apis(RequestHandlerSelectors.basePackage(web_package))
                .paths(PathSelectors.any())
                .build()
                .globalRequestParameters(singletonList(new springfox.documentation.builders.RequestParameterBuilder()
                        .name(MyToken.Authorization_TOKEN)
                        .description("令牌token")
                        .in(ParameterType.HEADER)
                        .required(false)
                        .query(q -> q.model(m -> m.scalarModel(ScalarType.STRING)))
                        .build()));
    }

    private ApiInfo buildApiInf(){
        return new ApiInfoBuilder()
                .title(title)
                .version(version)
                .description(description)
                .build();
    }


}
