package top.zang.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import top.zang.core.QueryHead;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@ApiModel(description="后台用户表")
@Data
public class TUserDto extends QueryHead {

    @ApiModelProperty(value = "手机号码")
    @NotEmpty
    private String mobile;
    @ApiModelProperty(value = "用户名")
    @NotEmpty
    private String user_name;
    @ApiModelProperty(value = "昵称")
    @NotEmpty
    private String nick_name;
    @ApiModelProperty(value = "密码")
    @NotEmpty
    private String password;
}