package top.zang.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.Data;

@ApiModel(description="用户基础信息表")
@Data
public class TUserVo {
    @ApiModelProperty(value = "用户ID")
    private Integer id;
    @ApiModelProperty(value = "手机号码")
    private String mobile;
    @ApiModelProperty(value = "用户名")
    private String user_name;
    @ApiModelProperty(value = "昵称")
    private String nick_name;
    @ApiModelProperty(value = "状态：0:正常,1:冻结")
    private String status;
    @ApiModelProperty(value = "创建时间")
    private Date create_time;
    @ApiModelProperty(value = "更新时间")
    private Date update_time;
}