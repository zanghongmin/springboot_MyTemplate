package top.zang.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@ApiModel(value = "TUserFeedbackVo",description="意见反馈表")
@Data
public class TUserFeedbackVo {
    @ApiModelProperty(value = "ID")
    private Integer id;
    @ApiModelProperty(value = "用户ID")
    private Integer user_id;
    @ApiModelProperty(value = "手机号码")
    private String mobile;
    @ApiModelProperty(value = "用户名")
    private String user_name;
    @ApiModelProperty(value = "昵称")
    private String nick_name;
    @ApiModelProperty(value = "反馈意见内容")
    private String content;
    @ApiModelProperty(value = "创建时间")
    private Date create_time;
    @ApiModelProperty(value = "更新时间")
    private Date update_time;
}