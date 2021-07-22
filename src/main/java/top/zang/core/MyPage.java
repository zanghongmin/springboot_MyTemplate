package top.zang.core;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 分页数据封装类
 */
@Data
public class MyPage<T> {

    @ApiModelProperty(name= "pagenum", value = "当前页，第一页为1，第二页为2 ...",example = "1",required = true)
    private Integer pageNum;
    @ApiModelProperty(name= "pagesize", value = "页大小，默认为20，一页有20个数据",example = "20",required = true)
    private Integer pageSize;
    @ApiModelProperty(name= "totalPage", value = "总页数",example = "0",required = true)
    private Integer totalPage;
    @ApiModelProperty(name= "total", value = "总条数",example = "0",required = true)
    private Long total;
    @ApiModelProperty(name= "pageList", value = "当前分页数据",required = true)
    private List<T> list;

    /**
     * 将PageHelper分页后的list转为分页信息
     */
    public static <T> MyPage<T> restPage(List<T> list) {
        MyPage<T> result = new MyPage<T>();
        PageInfo<T> pageInfo = new PageInfo<T>(list);
        result.setTotalPage(pageInfo.getPages());
        result.setPageNum(pageInfo.getPageNum());
        result.setPageSize(pageInfo.getPageSize());
        result.setTotal(pageInfo.getTotal());
        result.setList(pageInfo.getList());
        return result;
    }

    /**
     * 将SpringData分页后的list转为分页信息
     */
    public static <T> MyPage<T> restPage(Page<T> pageInfo) {
        MyPage<T> result = new MyPage<T>();
        result.setTotalPage(pageInfo.getTotalPages());
        result.setPageNum(pageInfo.getNumber());
        result.setPageSize(pageInfo.getSize());
        result.setTotal(pageInfo.getTotalElements());
        result.setList(pageInfo.getContent());
        return result;
    }
}
