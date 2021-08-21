package top.zang.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.zang.config.token.MyToken;
import top.zang.config.token.RequestToken;
import top.zang.core.MyPage;
import top.zang.core.PageQuery;
import top.zang.core.ReturnT;
import top.zang.dto.backend.AdminVO;
import top.zang.mbg.model.AdminResourceCategoryDO;
import top.zang.service.BackendAdminResourceService;
import top.zang.service.BackendCommonService;

import javax.annotation.Resource;
import javax.validation.Valid;


@Api(value = "后台资源管理", description = "后台资源管理")
@RestController
@RequestMapping("/backend/admin_resource")
public class BackendAdminResourceController {

    @Resource
    private BackendAdminResourceService backendAdminResourceService;


    @ApiOperation(value = "后台资源分类列表")
    @GetMapping(value = "/getAdminResourceCategoryList")
    public ReturnT<MyPage<AdminResourceCategoryDO>> getAdminResourceCategoryList(@RequestToken MyToken myToken, @Valid PageQuery pageQuery) throws Exception {
        return backendAdminResourceService.getAdminResourceCategoryList(myToken,pageQuery);
    }

}
