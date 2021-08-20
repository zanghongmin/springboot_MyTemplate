package top.zang.mbg.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.Data;

@ApiModel(value = "AdminMenuDO",description="后台菜单权限")
@Data
public class AdminMenuDO {
    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "上级菜单父id")
    private Long pid;

    @ApiModelProperty(value = "菜单名")
    private String name;

    @ApiModelProperty(value = "菜单图标")
    private String icon;

    @ApiModelProperty(value = "路由地址")
    private String path;

    @ApiModelProperty(value = "是否外链  true是false否")
    private Boolean is_frame;

    @ApiModelProperty(value = "菜单类型(M目录C菜单F按钮)")
    private String menu_type;

    @ApiModelProperty(value = "显示状态 true显示false隐藏")
    private Boolean visible;

    @ApiModelProperty(value = "组件路径")
    private String component;

    @ApiModelProperty(value = "权限标识")
    private String perms;

    @ApiModelProperty(value = "显示排序")
    private Integer sort;

    @ApiModelProperty(value = "是否缓存 true  false")
    private Boolean is_cache;

    @ApiModelProperty(value = "菜单状态 0正常1删除")
    private Byte status;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "创建时间")
    private Date create_time;

    @ApiModelProperty(value = "更新时间")
    private Date update_time;
}