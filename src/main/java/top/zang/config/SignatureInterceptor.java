package top.zang.config;

import cn.hutool.core.util.StrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import top.zang.config.bodyReader.RequestWrapper;
import top.zang.config.token.MyToken;
import top.zang.core.MyException;
import top.zang.util.MyIpUtil;
import top.zang.util.MyJwtUtil;
import top.zang.util.MyRedisUtil;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Component
public class SignatureInterceptor implements HandlerInterceptor{
    private static final Logger logger = LoggerFactory.getLogger(SignatureInterceptor.class);
    private final static String userId = "userId"; //用户id
    private final static String requestURI = "requestURI"; //请求URI
    private final static String requestIP = "requestIP"; //请求IP
    private final static String traceID = "traceId"; //请求内部根据记录id
    private final static String requestTime = "requestTime"; //请求耗时
    private final static String hostIP = "hostIP"; //hostIP
    private static String hostIPValue = ""; //hostIPValue
    private static final ThreadLocal<Long> threadLocal = new ThreadLocal();
    public static final ThreadLocal<Integer> threadLocal_userid = new ThreadLocal();
    @Resource
    public MyRedisUtil myRedisUtil;

    @PostConstruct
    public void hostIPValue(){
        try {
            InetAddress inet = InetAddress.getLocalHost();
            //当前机器的ip
            hostIPValue = inet.getHostName();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    //对于一些接口进行限制，一个用户一个接口同时并发只能访问一次
    public static Set<String> limit_paths = new HashSet<>();
    //对于一些为上传文件的接口，不打印包体日志
    public static Set<String> upload_paths = new HashSet<>();
    static {
        //接口限制
        limit_paths.add("/user/user_feedback");

        //上传文件
        upload_paths.add("/m/upimage");
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        threadLocal.set(System.currentTimeMillis());
        threadLocal_userid.remove();
        String requestUri = request.getRequestURI();
        String requestMethod = request.getMethod();
        String  requestbody = "";
        if(upload_paths.contains(requestUri)){ // 上传图片等不打印，请求内容
            requestbody = getAllRequestParam(request);
        }else {
            if (requestMethod.toLowerCase().equals("get")) {
                requestbody = getAllRequestParam(request);
            } else if (requestMethod.toLowerCase().equals("post"))  {
                RequestWrapper requestWrapper = new RequestWrapper(request);
                requestbody = requestWrapper.getBody();
            }
        }
        MDC.clear();
        MDC.put(hostIP, hostIPValue);
        MDC.put(requestIP, MyIpUtil.getIpAddr(request));
        MDC.put(requestURI, CommandLineRunnerConfig.requestURIName.get(requestUri)==null?requestUri:CommandLineRunnerConfig.requestURIName.get(requestUri));
        MDC.put(traceID, UUID.randomUUID().toString().replaceAll("-",""));
        try{
            String tokenKey = request.getHeader(MyToken.Authorization_TOKEN);
            if(StrUtil.isNotBlank(tokenKey)){
                MyToken token = MyJwtUtil.parseJwt(tokenKey);
                if(token!=null){
                    MDC.put(userId, token.getUserId()+"");
                    if(limit_paths.contains(requestUri)){
                        threadLocal_userid.set(token.getUserId());
                        interfaceLimit(requestUri,token.getUserId());
                    }
                }

            }
        }catch (Exception e){
            logger.warn("获取token属性错误:{}",  e.getMessage());
        }
        logger.info("请求成功,请求URI:{},请求方式:{},请求参数:{}",  requestUri, requestMethod, requestbody);
        return true;
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        //解决中文乱码
        response.setCharacterEncoding("UTF-8");
        response.setHeader("accept", "application/json;charset=UTF-8");
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        Long starttime = threadLocal.get();
        Long endtime = System.currentTimeMillis();
        String requestUri = request.getRequestURI();
        String requestMethod = request.getMethod();
        Integer userId = threadLocal_userid.get();
        if(userId!=null){
            myRedisUtil.del(getInterfaceLimitKey(requestUri,userId));
        }
        MDC.put(requestTime, (endtime-starttime)+"");
        logger.info("响应成功,请求URI:{},请求方式:{},耗时时间:{}ms", requestUri, requestMethod,(endtime-starttime));
        MDC.clear();
    }

    /**
     * 把request内容转化成字符串
     *
     * @param request
     * @return
     */
    private String getAllRequestParam(final HttpServletRequest request) {
        StringBuilder sb = new StringBuilder();
        Enumeration<?> temp = request.getParameterNames();
        if (null != temp) {
            while (temp.hasMoreElements()) {
                String key = (String) temp.nextElement();
                String value = request.getParameter(key);
                sb.append(key + "=" + value + "&");
            }
            if (sb.length() > 0) {
                return sb.substring(0, sb.length() - 1);
            }
        }
        return sb.toString();
    }

    //接口限制，一个用户一个接口同时并发只能访问一次
    private void interfaceLimit(String requestUri,Integer userId) throws Exception{
        String redisKey = getInterfaceLimitKey(requestUri,userId);
        long count = myRedisUtil.incr(redisKey, 1);
        if(count == 1) {
            myRedisUtil.expire(redisKey, 2);//默认超时时间2秒
        }else{
            Thread.sleep(160);
            if(myRedisUtil.getExpire(redisKey)==0){
                logger.warn("当前交易limit Key过期为0");
                //防止极端情况客户一直请求不了
                Thread.sleep(2000);
                if(myRedisUtil.hasKey(redisKey) && myRedisUtil.getExpire(redisKey)==0){
                    logger.error("极端情况客户正常请求被阻碍，删除limit Key");
                    myRedisUtil.del(redisKey);
                }
            }
            logger.info("您点击太快!请稍等");
            throw new MyException("您点击太快!请稍等");
        }
    }

    public static String getInterfaceLimitKey(String servletPath,Integer userId){
        if(StrUtil.isBlank(servletPath) || userId==null){
            throw new MyException("接口限制配置出错");
        }
        String redisKey = "interfaceLimit:"+userId + ":" + servletPath;
        return redisKey;
    }
}
