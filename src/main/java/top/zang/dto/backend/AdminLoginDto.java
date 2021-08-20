package top.zang.dto.backend;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import top.zang.core.QueryHead;

import javax.validation.constraints.NotEmpty;


@Data
@ApiModel(description = "后台登录实体类")
public class AdminLoginDto extends QueryHead {
    @ApiModelProperty(value = "登陆名",example = "zang")
    @NotEmpty
    private String login_name;
    @ApiModelProperty(value = "密码",example = "zang")
    @NotEmpty
    private String login_pwd;
}
