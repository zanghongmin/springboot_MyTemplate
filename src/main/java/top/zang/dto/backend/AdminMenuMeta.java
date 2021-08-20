package top.zang.dto.backend;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import top.zang.mbg.model.AdminMenuDO;

import java.util.List;


@ApiModel(value="菜单权限页面信息路由-meta")
@Data
public class AdminMenuMeta {
    @ApiModelProperty(name= "title", value = "菜单名", example="",required = false)
    private String title;
    @ApiModelProperty(name= "icon", value = "菜单图标", example="",required = false)
    private String icon;
    @ApiModelProperty(name= "noCache", value = "路由地址", example="",required = false)
    private Boolean noCache;
}
