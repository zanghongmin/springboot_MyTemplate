package top.zang.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import top.zang.config.token.MyToken;
import top.zang.config.token.RequestToken;
import top.zang.core.MyPage;
import top.zang.core.PageQuery;
import top.zang.core.QueryHead;
import top.zang.core.ReturnT;
import top.zang.dto.*;
import top.zang.service.UserService;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@Api(value = "用户相关API", description = "用户相关API")
@RestController
@RequestMapping("/user")
public class UserController {
	@Resource
	public UserService userService;

    @ApiOperation(value = "用户登录接口")
    @PostMapping(value="/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ReturnT<String> login(@Valid @RequestBody LoginDto loginDto){
        return userService.login(loginDto);
    }

    @ApiOperation(value = "获取用户列表接口")
    @GetMapping(value="/getUserList")
    public ReturnT<MyPage<TUserVo>> getUserList(@RequestToken MyToken myToken, @Valid PageQuery pageQuery){
        return userService.getUserList(myToken,pageQuery);
    }

    @ApiOperation(value = "新增一个用户接口")
    @PostMapping(value="/insert_one", produces = MediaType.APPLICATION_JSON_VALUE )
    public ReturnT insert_one(@RequestToken MyToken myToken,@Valid @RequestBody TUserDto tuserDto){
        return userService.insert_one(myToken,tuserDto);
    }

    @ApiOperation(value = "新增多个用户接口")
    @PostMapping(value="/insert_many", produces = MediaType.APPLICATION_JSON_VALUE )
    public ReturnT insert_many(@RequestToken MyToken myToken, @Valid @RequestBody List<TUserDto>  tuserDtos){
        return userService.insert_many(myToken,tuserDtos);
    }

    @ApiOperation(value = "更新多个用户接口")
    @PostMapping(value="/update_many", produces = MediaType.APPLICATION_JSON_VALUE )
    public ReturnT update_many(@RequestToken MyToken myToken, @Valid QueryHead queryHead){
        return userService.update_many(myToken,queryHead);
    }

    @ApiOperation(value = "用户反馈接口")
    @PostMapping(value="/user_feedback", produces = MediaType.APPLICATION_JSON_VALUE )
    public ReturnT user_feedback(@RequestToken MyToken myToken, @Valid @RequestBody TUserFeedbackDto tuserFeedbackDto){
        return userService.user_feedback(myToken,tuserFeedbackDto);
    }

    @ApiOperation(value = "用户反馈列表接口")
    @PostMapping(value="/user_feedback_list", produces = MediaType.APPLICATION_JSON_VALUE )
    public ReturnT<MyPage<TUserFeedbackVo>> user_feedback_list(@RequestToken MyToken myToken, @Valid PageQuery pageQuery){
        return userService.user_feedback_list(myToken,pageQuery);
    }


}