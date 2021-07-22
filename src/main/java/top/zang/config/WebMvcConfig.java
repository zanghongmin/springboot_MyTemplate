package top.zang.config;

import top.zang.config.token.TokenArgumentResolver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * web mvc config
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

    @Resource
    private SignatureInterceptor signatureInterceptor;


    @Value("${swagger.enable}")
    private boolean swaggerEnable;

    @Resource
    private TokenArgumentResolver resolver;

    @Override
    protected void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        super.addArgumentResolvers(argumentResolvers);
        argumentResolvers.add(resolver);
    }

    /**
     * 添加自定义拦截器
     *  1.给对外提供api接口，增加签名检验
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(signatureInterceptor).addPathPatterns("/**").excludePathPatterns("/swagger-ui/**","/swagger-resources/**","/v3/api-docs");
    }


    /**
     * swagger-ui 页面可以加载和显示
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if(swaggerEnable){
            registry.
                    addResourceHandler("/swagger-ui/**")
                    .addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/")
                    .resourceChain(false);
        }

    }
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        if(swaggerEnable){
            registry.addViewController("/swagger-ui/")
                    .setViewName("forward:" +"/swagger-ui/index.html");
        }
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //对那些请求路径进行跨域处理
        registry.addMapping("/**")
                // 允许的请求头，默认允许所有的请求头
                .allowedHeaders("*")
                // 允许的方法，默认允许GET、POST、HEAD
                .allowedMethods("*")
                // 探测请求有效时间，单位秒
                .maxAge(3600)
                // 支持的域
                .allowedOrigins("*");
    }

}