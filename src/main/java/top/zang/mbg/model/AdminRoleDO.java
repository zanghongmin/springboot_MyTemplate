package top.zang.mbg.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "AdminRoleDO",description="后台用户角色")
@Data
public class AdminRoleDO {
    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "角色名称")
    private String name;

    @ApiModelProperty(value = "0正常1删除")
    private Byte status;

    @ApiModelProperty(value = "角色菜单权限列表，json格式")
    private String admin_menu_ids;

    @ApiModelProperty(value = "角色接口资源权限列表，json格式")
    private String admin_resource_ids;

    @ApiModelProperty(value = "备注")
    private String remark;
}