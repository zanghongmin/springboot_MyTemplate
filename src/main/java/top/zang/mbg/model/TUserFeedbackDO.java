package top.zang.mbg.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.Data;

@ApiModel(value = "TUserFeedbackDO",description="意见反馈表")
@Data
public class TUserFeedbackDO {
    @ApiModelProperty(value = "ID")
    private Integer id;

    @ApiModelProperty(value = "用户ID")
    private Integer user_id;

    @ApiModelProperty(value = "反馈意见内容")
    private String content;

    @ApiModelProperty(value = "创建时间")
    private Date create_time;

    @ApiModelProperty(value = "更新时间")
    private Date update_time;
}