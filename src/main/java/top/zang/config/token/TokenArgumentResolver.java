package top.zang.config.token;

import cn.hutool.core.util.StrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import top.zang.util.MyJwtUtil;
import top.zang.util.MyRedisUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

//使用参数注解的方式 ，验证Token有效性，保证接口安全
@Component
public class TokenArgumentResolver implements HandlerMethodArgumentResolver {

    private static final Logger logger = LoggerFactory.getLogger(TokenArgumentResolver.class);

    @Resource
    MyRedisUtil myRedisUtil;


    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().isAssignableFrom(MyToken.class)
                && parameter.hasParameterAnnotation(RequestToken.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        String tokenKey = request.getHeader(MyToken.Authorization_TOKEN);
        if (StrUtil.isEmpty(tokenKey)) {
            throw new TokenException("Token不能为空");
        }
        try {
            MyToken token = MyJwtUtil.parseJwt(tokenKey);
//            String rediskey = MyRedisUtil.getLoginKey(token.getUserId());
//            Object object = myRedisUtil.get(rediskey);
//            if(object==null || StrUtil.isEmpty(object.toString()) || !tokenKey.equals(object.toString())){
//                throw new TokenException("token不一致，请重新登录");
//            }
            return token;
        }catch (TokenException ex){
            throw new TokenException(ex.getDetailMessage());
        }catch (Exception e) {
            throw new TokenException("解析token失败:"+tokenKey);
        }
    }
}