package top.zang.mbg.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import top.zang.mbg.model.AdminResourceCategoryDO;
import top.zang.mbg.model.AdminResourceCategoryDOExample;

public interface AdminResourceCategoryDOMapper {
    long countByExample(AdminResourceCategoryDOExample example);

    int deleteByExample(AdminResourceCategoryDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AdminResourceCategoryDO record);

    int insertSelective(AdminResourceCategoryDO record);

    List<AdminResourceCategoryDO> selectByExample(AdminResourceCategoryDOExample example);

    AdminResourceCategoryDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AdminResourceCategoryDO record, @Param("example") AdminResourceCategoryDOExample example);

    int updateByExample(@Param("record") AdminResourceCategoryDO record, @Param("example") AdminResourceCategoryDOExample example);

    int updateByPrimaryKeySelective(AdminResourceCategoryDO record);

    int updateByPrimaryKey(AdminResourceCategoryDO record);

    //非官方-自定义自动生成
    int batchInsert(@Param("list") List<AdminResourceCategoryDO> list);

    //非官方-自定义自动生成
    int batchUpdate(@Param("list") List<AdminResourceCategoryDO> list);
}