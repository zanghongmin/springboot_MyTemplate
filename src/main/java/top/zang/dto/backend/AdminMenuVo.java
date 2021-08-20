package top.zang.dto.backend;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import top.zang.mbg.model.AdminMenuDO;

import java.util.List;

@Getter
@Setter
public class AdminMenuVo extends AdminMenuDO implements Comparable<AdminMenuVo> {

    @ApiModelProperty(value = "菜单meta", example="",required = false)
    private  AdminMenuMeta meta;

    @ApiModelProperty(value = "子级菜单")
    private List<AdminMenuVo> children;
    @ApiModelProperty(name= "hidden", value = "是否隐藏", example="",required = false)
    private Boolean hidden;
    @ApiModelProperty(name= "redirect", value = "是否外链 noRedirect:否 Redirect:是", example="",required = false)
    private String redirect;
    @ApiModelProperty(name= "alwaysShow", value = "显示状态", example="",required = false)
    private Boolean alwaysShow;
    @Override
    public int compareTo(AdminMenuVo o) {
        return this.getSort().compareTo(o.getSort());
    }
}
