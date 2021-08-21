package top.zang.service;

import cn.hutool.core.util.StrUtil;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import top.zang.config.token.MyToken;
import top.zang.config.token.RequestToken;
import top.zang.core.Constant;
import top.zang.core.RedisKey;
import top.zang.core.ReturnT;
import top.zang.core.exception.ReturnTEnum;
import top.zang.dto.backend.*;
import top.zang.enums.ItemStatusEnum;
import top.zang.enums.UserSourceTypeEnum;
import top.zang.mbg.model.*;
import top.zang.util.MyJsonUtil;
import top.zang.util.MyJwtUtil;
import top.zang.util.MyMd5Util;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BackendCommonService extends AbstractBackendService {
    public static final Logger logger = LoggerFactory.getLogger(BackendCommonService.class);

    //后台登录接口
    public ReturnT<AdminLoginVo> login(AdminLoginDto adminLoginDto) throws Exception {
        AdminDOExample adminDOExample = new AdminDOExample();
        adminDOExample.createCriteria().andLoginNameEqualTo(adminLoginDto.getLogin_name());
        List<AdminDO> adminDOs = adminDODao.selectByExample(adminDOExample);
        if (adminDOs.size() > 0) {
            AdminDO adminDO = adminDOs.get(0);
            if (MyMd5Util.md5(adminLoginDto.getLogin_pwd()).equals(adminDO.getLogin_pwd())) {
                ReturnTEnum.ERROR.isTrue(ItemStatusEnum.NORMAL.getCode() != adminDO.getStatus(),"用户被冻结不能登陆");
                MyToken myToken = new MyToken();
                myToken.setUserSource(UserSourceTypeEnum.backend.getCode());
                myToken.setUserId(adminDO.getId());
                String access_token = MyJwtUtil.createJwt(myToken);
                AdminLoginVo adminLoginVo = new AdminLoginVo();
                adminLoginVo.setAccess_token(access_token);
                String rediskey = RedisKey.getLoginKey(myToken.getUserId(), myToken.getUserSource());
                myRedisUtil.set(rediskey, access_token, Constant.tokenTime);
                adminDO.setUpdate_time(new Date());
                adminDO.setLogin_token(access_token);
                adminDODao.updateByPrimaryKey(adminDO);
                return ReturnT.Success(adminLoginVo);
            }
        }
        return ReturnT.Fail("用户名密码错误");
    }

    //后台退出接口
    public ReturnT logout(MyToken myToken) throws Exception {
        AdminDO userinfo = (AdminDO) myToken.getUserinfo();
        String rediskey = RedisKey.getLoginKey(myToken.getUserId(), myToken.getUserSource());
        myRedisUtil.del(rediskey);
        return ReturnT.Success();
    }
    //后台获取个人数据
    public ReturnT<AdminVO> getUserInfo(MyToken myToken) throws Exception {
        AdminDO userinfo = (AdminDO) myToken.getUserinfo();
        AdminVO adminVO = new AdminVO();
        BeanUtils.copyProperties(userinfo,adminVO);
        adminVO.setAdmin_role_ids(MyJsonUtil.parseObject(userinfo.getAdmin_role_ids(),List.class));
        return ReturnT.Success(adminVO);
    }
    //后台修改个人密码
    public ReturnT update_pwd(MyToken myToken, AdminUpdatePwdDto adminUpdatePwdDto) throws Exception {
        AdminDO userinfo = (AdminDO) myToken.getUserinfo();
        if(adminUpdatePwdDto.getNew_pwd().equals(adminUpdatePwdDto.getOld_pwd())){
            return ReturnT.Fail("新密码和原密码不可一样");
        }
        if(!MyMd5Util.md5(adminUpdatePwdDto.getOld_pwd()).equals(userinfo.getLogin_pwd())){
            return ReturnT.Fail("原密码不对");
        }
        userinfo.setLogin_pwd(MyMd5Util.md5(adminUpdatePwdDto.getNew_pwd()));
        userinfo.setUpdate_time(new Date());
        if (adminDODao.updateByPrimaryKey(userinfo) > 0) {
            return ReturnT.Success();
        } else {
            return ReturnT.Fail();
        }
    }

    //后台菜单权限列表
    public ReturnT<List<AdminMenuVo>> menu_list(MyToken myToken, AdminMenuGetDto adminMenuGetDto) throws Exception {
        AdminDO userinfo = (AdminDO) myToken.getUserinfo();
        List<AdminMenuVo> adminMenuVos = new ArrayList<>();
        AdminMenuDOExample adminMenuDOExample = new AdminMenuDOExample();
        if(!adminMenuGetDto.getType().equals("1")){
            List<Long> admin_role_ids = MyJsonUtil.parseObject(userinfo.getAdmin_role_ids(),List.class);
            if(admin_role_ids.size()<=0){
                return ReturnT.Success(adminMenuVos);
            }
            AdminRoleDOExample adminRoleDOExample = new AdminRoleDOExample();
            adminRoleDOExample.createCriteria().andIdIn(admin_role_ids).andStatusEqualTo(ItemStatusEnum.NORMAL.getCode());
            List<AdminRoleDO> adminRoleDOs = adminRoleDODao.selectByExample(adminRoleDOExample);
            List<Long> admin_menu_ids = new ArrayList<>();
            adminRoleDOs.forEach(a->{
                admin_menu_ids.addAll(MyJsonUtil.parseObject(a.getAdmin_menu_ids(),List.class));
            });
            if(admin_menu_ids.size()<=0){
                return ReturnT.Success(adminMenuVos);
            }
            adminMenuDOExample.createCriteria().andIdIn(admin_menu_ids).andStatusEqualTo(ItemStatusEnum.NORMAL.getCode());
        }
        List<AdminMenuDO> adminMenuDOs = adminMenuDODao.selectByExample(adminMenuDOExample);
        List<AdminMenuVo> result = adminMenuDOs.stream()
                .filter(menu -> menu.getPid().equals(0L))
                .map(menu -> covertMenuNode(menu, adminMenuDOs))
                .sorted(Comparator.comparing(AdminMenuVo::getSort)).collect(Collectors.toList());

        return ReturnT.Success(result);
    }
}
