package top.zang.dto.backend;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "后端页面登录返回类")
public class AdminLoginVo {
    @ApiModelProperty(name= "access_token",value = "登录令牌token", example = "token",required = true)
    private String access_token;

}
