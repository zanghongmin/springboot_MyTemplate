package top.zang.dto.backend;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import top.zang.core.QueryHead;

import javax.validation.constraints.NotNull;

/**
*  老师
*  Created by zang on '2021-05-11 09:49:22'.
*/
@ApiModel(description = "老师密码修改请求类")
@Data
public class AdminUpdatePwdDto extends QueryHead {

        @ApiModelProperty(name= "old_pwd", value = "原密码", example="888888",required = false)
        @NotNull(message = "原密码不能为空")
        private String old_pwd;

        @ApiModelProperty(name= "new_pwd", value = "新密码", example="888888",required = false)
        @NotNull(message = "新密码不能为空")
        private String new_pwd;

}