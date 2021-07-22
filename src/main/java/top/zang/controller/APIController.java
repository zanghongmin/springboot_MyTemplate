package top.zang.controller;


import top.zang.core.ReturnT;
import top.zang.service.APIService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(value = "测试API", description = "测试API")
@RestController
@RequestMapping("/")
public class APIController {
	@Resource
	public APIService apiService;

    @ApiOperation(value = "测试API")
    @GetMapping(value="/")
	public ReturnT test(){
		return ReturnT.Success();
	}
}