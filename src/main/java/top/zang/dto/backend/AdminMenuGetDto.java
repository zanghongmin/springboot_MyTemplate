package top.zang.dto.backend;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import top.zang.core.QueryHead;

import javax.validation.constraints.NotEmpty;


@Data
@ApiModel(description = "查询后台菜单列表")
public class AdminMenuGetDto extends QueryHead {
    @ApiModelProperty(value = "查询类型 1:查询全部后台菜单 2:查询当前用户的后台菜单",example = "1")
    @NotEmpty
    private String type = "1";

}
