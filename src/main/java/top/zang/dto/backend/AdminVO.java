package top.zang.dto.backend;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@ApiModel(description="后台用户表")
@Data
public class AdminVO {
    @ApiModelProperty(value = "用户ID")
    private Long id;

    @ApiModelProperty(value = "手机号码")
    private String mobile;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "登陆名")
    private String login_name;

    @ApiModelProperty(value = "后台用户角色ids,json格式")
    private List<Object> admin_role_ids;

    @ApiModelProperty(value = "用户名称")
    private String name;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "性别 0女1男")
    private Byte sex;

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "生日  格式为YYYY-MM-DD")
    private String birthday;

    @ApiModelProperty(value = "状态：0:正常,1:冻结")
    private Byte status;

    @ApiModelProperty(value = "创建时间")
    private Date create_time;

    @ApiModelProperty(value = "更新时间")
    private Date update_time;
}