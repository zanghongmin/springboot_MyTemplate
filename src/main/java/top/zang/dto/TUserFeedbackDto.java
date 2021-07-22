package top.zang.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@ApiModel(value = "TUserFeedbackDto",description="意见反馈表")
@Data
public class TUserFeedbackDto {
    @ApiModelProperty(value = "反馈意见内容")
    private String content;
}