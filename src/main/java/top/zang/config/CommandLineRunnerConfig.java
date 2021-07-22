package top.zang.config;

import cn.hutool.core.util.StrUtil;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class CommandLineRunnerConfig implements CommandLineRunner, ApplicationListener<ApplicationReadyEvent> {
    private static final Logger logger = LoggerFactory.getLogger(CommandLineRunnerConfig.class);

    public final static Map<String,String> requestURIName = new HashMap<>(); //请求URI-中文名称映射

    @Autowired
    WebApplicationContext applicationContext;

    @Autowired
    RestTemplate restTemplate;
    @Override
    public void run(String... args) throws Exception {

        logger.info("把请求url中文名称找出来并映射-开始");
        RequestMappingHandlerMapping mapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
        Map<RequestMappingInfo, HandlerMethod> map = mapping.getHandlerMethods();
        for (Map.Entry<RequestMappingInfo, HandlerMethod> m : map.entrySet()) {
            RequestMappingInfo info = m.getKey();
            HandlerMethod method = m.getValue();
            PatternsRequestCondition p = info.getPatternsCondition();
            String url=null;
            for (String one : p.getPatterns()) {
                url = one;
                break;
            }
            Annotation[] annotations = method.getMethod().getDeclaredAnnotations();
            for (Annotation one : annotations) {
                if(one.annotationType() == ApiOperation.class){
                    ApiOperation apiOperation = (ApiOperation)one;
                    if(StrUtil.isNotBlank(url) && StrUtil.isNotBlank(apiOperation.value())){
                        requestURIName.put(url,apiOperation.value());
                    }
                }
            }

        }
        logger.info("把请求url中文名称找出来并映射-结束");
    }

    /**
     * @param applicationReadyEvent
     */
    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
    }
}
