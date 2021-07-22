package top.zang.config.token;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class MyToken {
    /*
        用户登录后，把token放入http head中Authorization
     */
    @JsonIgnore
    @ApiModelProperty(hidden=true)
    public static final String Authorization_TOKEN = "x-access-token";
    @ApiModelProperty(value = "用户id",hidden=true)
    private Integer userId;
//    @ApiModelProperty(value = "用户信息类",hidden=true)
//    @JsonIgnore
//    private TUserDO userinfo;

}
