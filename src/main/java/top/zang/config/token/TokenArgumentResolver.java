package top.zang.config.token;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import top.zang.config.SignatureInterceptor;
import top.zang.core.RedisKey;
import top.zang.core.exception.MyException;
import top.zang.core.exception.ReturnTEnum;
import top.zang.dao.AdminDODao;
import top.zang.dao.AdminResourceDODao;
import top.zang.dao.AdminRoleDODao;
import top.zang.enums.ItemStatusEnum;
import top.zang.enums.UserSourceTypeEnum;
import top.zang.mbg.model.*;
import top.zang.util.MyJsonUtil;
import top.zang.util.MyJwtUtil;
import top.zang.util.MyRedisUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//使用参数注解的方式 ，验证Token有效性，保证接口安全
@Component
public class TokenArgumentResolver implements HandlerMethodArgumentResolver {

    private static final Logger logger = LoggerFactory.getLogger(TokenArgumentResolver.class);

    @Resource
    MyRedisUtil myRedisUtil;
    @Resource
    AdminDODao adminDODao;
    @Resource
    AdminRoleDODao adminRoleDODao;
    @Resource
    AdminResourceDODao adminResourceDODao;

    //对于不进行动态接口权限限制的接口。动态接口权限限制：对于后台用户接口按照配置进行接口限制
    public static Set<String> interface_paths = new HashSet<>();
    static {
        //不进行动态接口权限限制的接口
        interface_paths.add("/backend/common/**");
    }

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
            String rediskey = RedisKey.getLoginKey(token.getUserId(),token.getUserSource());
            Object object = myRedisUtil.get(rediskey);
            if(object==null || StrUtil.isEmpty(object.toString()) || !tokenKey.equals(object.toString())){
                throw new TokenException("token不一致，请重新登录");
            }
            if(UserSourceTypeEnum.backend.getCode().equals(token.getUserSource())){
                AdminDO adminDO = adminDODao.selectByPrimaryKey(token.getUserId());
                if(adminDO==null){
                    throw new TokenException("token后台用户数据不对");
                }
                token.setUserinfo(adminDO);
                token.setAdminResourceDOs(new ArrayList<>());
                List<Long> admin_role_ids = MyJsonUtil.parseObject(adminDO.getAdmin_role_ids(),List.class);
                if(admin_role_ids.size()>0){
                    AdminRoleDOExample adminRoleDOExample = new AdminRoleDOExample();
                    adminRoleDOExample.createCriteria().andIdIn(admin_role_ids).andStatusEqualTo(ItemStatusEnum.NORMAL.getCode());
                    List<AdminRoleDO> adminRoleDOs = adminRoleDODao.selectByExample(adminRoleDOExample);
                    List<Long> admin_resource_ids = new ArrayList<>();
                    adminRoleDOs.forEach(a->{
                        admin_resource_ids.addAll(MyJsonUtil.parseObject(a.getAdmin_resource_ids(),List.class));
                    });
                    if(admin_resource_ids.size()>0){
                        AdminResourceDOExample adminResourceDOExample = new AdminResourceDOExample();
                        adminResourceDOExample.createCriteria().andIdIn(admin_resource_ids).andStatusEqualTo(ItemStatusEnum.NORMAL.getCode());
                        List<AdminResourceDO> adminResourceDOs = adminResourceDODao.selectByExample(adminResourceDOExample);
                        token.setAdminResourceDOs(adminResourceDOs);
                    }
                }
                //动态接口权限限制
                autoInterfaceSecurity(request.getRequestURI(), token);
            }else if(UserSourceTypeEnum.app.getCode().equals(token.getUserSource())){
                token.setUserinfo(null);
                token.setAdminResourceDOs(null);
            }else{
                throw new TokenException("token错误");
            }
            return token;
        }catch (TokenException ex){
            throw new TokenException(ex.getDetailMessage());
        }catch (MyException myex) {
            throw myex;
        }catch (Exception e) {
            throw new TokenException("解析token失败:"+tokenKey);
        }
    }


    //动态接口权限限制
    private void autoInterfaceSecurity(String requestUri, MyToken token) throws Exception {
        Boolean isInterfaceAuto = true;
        String path = URLUtil.getPath(requestUri);
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        for (String interface_path : interface_paths) {
            if (antPathMatcher.match(interface_path, path)) {
                isInterfaceAuto = false;
                break;
            }
        }
        if (isInterfaceAuto) {
            List<AdminResourceDO> adminResourceDOs = (List<AdminResourceDO>) token.getAdminResourceDOs();
            if (adminResourceDOs==null || adminResourceDOs.size() == 0) {
                ReturnTEnum.UNAUTHO_ERROR.throwException("抱歉，您没有访问权限");
            }
            for(AdminResourceDO adminResourceDO:adminResourceDOs){
                if (antPathMatcher.match(adminResourceDO.getUrl(), path)) {
                    return;
                }
            }
            ReturnTEnum.UNAUTHO_ERROR.throwException("抱歉，您没有访问权限");
        }
    }
}
