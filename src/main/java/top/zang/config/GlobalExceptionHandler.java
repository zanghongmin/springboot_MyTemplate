package top.zang.config;


import top.zang.config.token.TokenException;
import top.zang.core.MyException;
import top.zang.core.ReturnT;
import top.zang.core.ReturnTEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import top.zang.util.MyJsonUtil;
import top.zang.util.MyRedisUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    public MyRedisUtil myRedisUtil;
    /**
    * 统一异常处理
    */
    @ExceptionHandler(value = Exception.class)
    public ReturnT defaultErrorHandler(HttpServletRequest request, Exception ex) throws Exception {
        Integer userId = SignatureInterceptor.threadLocal_userid.get();
        String requestUri = request.getRequestURI();
        if(userId!=null){
            myRedisUtil.del(SignatureInterceptor.getInterfaceLimitKey(requestUri,userId));
        }
        ReturnT errorResult;
        if(ex instanceof TokenException){ //token异常
            errorResult = ReturnT.Common(ReturnTEnum.INVALID_TOKEN, ((TokenException) ex).getDetailMessage());
        }else if( ex instanceof MethodArgumentNotValidException){//post请求 @RequestBody中bean参数校验异常
           FieldError error = ((MethodArgumentNotValidException)ex).getBindingResult().getFieldErrors().get(0);
            errorResult = ReturnT.Common(ReturnTEnum.BAD_REQUEST, error.getDefaultMessage());
        }else if( ex instanceof BindException){//get请求中bean参数校验异常
            FieldError error = ((BindException)ex).getBindingResult().getFieldErrors().get(0);
            errorResult = ReturnT.Common(ReturnTEnum.BAD_REQUEST, error.getDefaultMessage());
        }else if( ex instanceof MyException){
            errorResult = ReturnT.Fail(ex.getMessage());
        }else{
            logger.error("统一异常处理,请求URI:{},请求方式:{},异常内容:{}", request.getRequestURI(), request.getMethod(), ex.getMessage());
            errorResult = ReturnT.Fail("服务器开小差，请重试");
        }
        logger.error("统一异常处理,请求URI:{},请求方式:{},异常内容:{}", request.getRequestURI(), request.getMethod(), MyJsonUtil.toJSONString(errorResult));
        MDC.clear();
        return errorResult;
    }
}