package top.zang.core;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.validation.constraints.Min;


@ApiModel(description="分页请求头")
@Data
public class PageQuery extends QueryHead {

	@ApiModelProperty(value = "pagesize页大小,默认20")
	@Min(1)
	private int pagesize = 20;

	@ApiModelProperty(value = "pagenum 第几页1为第一页2位第二页 默认1")
	@Min(1)
	private int pagenum = 1;

}
