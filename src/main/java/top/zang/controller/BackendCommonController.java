package top.zang.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import top.zang.config.token.MyToken;
import top.zang.config.token.RequestToken;
import top.zang.core.ReturnT;
import top.zang.dto.backend.*;
import top.zang.service.BackendCommonService;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@Api(value = "后台-公用API", description = "后台-无接口权限资源校验")
@RestController
@RequestMapping("/backend/common")
public class BackendCommonController {
    @Resource
    private BackendCommonService backendCommonService;

    @ApiOperation(value = "后台登录接口")
    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ReturnT<AdminLoginVo> login(@Valid @RequestBody AdminLoginDto adminLoginDto) throws Exception {
        return backendCommonService.login(adminLoginDto);
    }
    @ApiOperation(value = "后台退出接口")
    @PostMapping(value = "/logout", produces = MediaType.APPLICATION_JSON_VALUE)
    public ReturnT logout(@RequestToken MyToken myToken) throws Exception {
        return backendCommonService.logout(myToken);
    }
    @ApiOperation(value = "后台获取个人数据")
    @GetMapping(value = "/getUserInfo")
    public ReturnT<AdminVO> getUserInfo(@RequestToken MyToken myToken) throws Exception {
        return backendCommonService.getUserInfo(myToken);
    }
    @ApiOperation(value = "后台修改个人密码")
    @PostMapping(value = "/update_pwd", produces = MediaType.APPLICATION_JSON_VALUE)
    public ReturnT update_pwd(@RequestToken MyToken myToken, @Valid @RequestBody AdminUpdatePwdDto adminUpdatePwdDto) throws Exception {
        return backendCommonService.update_pwd(myToken, adminUpdatePwdDto);
    }

    @ApiOperation(value = "后台菜单权限列表")
    @GetMapping(value = "/menu_list")
    public ReturnT<List<AdminMenuVo>> menu_list(@RequestToken MyToken myToken, @Valid AdminMenuGetDto adminMenuGetDto) throws Exception {
        return backendCommonService.menu_list(myToken,adminMenuGetDto);
    }


}