package top.zang.config.token;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import top.zang.mbg.model.AdminResourceDO;

import java.util.List;

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
    private Long userId;

    @ApiModelProperty(value = "用户类型 bankend:后台用户，app:前端app用户",hidden=true)
    private String userSource;

    @ApiModelProperty(value = "用户信息类adminDO或memberDO",hidden=true)
    @JsonIgnore
    private Object userinfo;

    @ApiModelProperty(value = "后台用户接口资源类",hidden=true)
    @JsonIgnore
    private List<AdminResourceDO> adminResourceDOs;

    @Override
    public String toString() {
        return userSource  + "-" + userId ;
    }

}
