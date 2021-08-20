package top.zang.mbg.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import top.zang.mbg.model.AdminMenuDO;
import top.zang.mbg.model.AdminMenuDOExample;

public interface AdminMenuDOMapper {
    long countByExample(AdminMenuDOExample example);

    int deleteByExample(AdminMenuDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AdminMenuDO record);

    int insertSelective(AdminMenuDO record);

    List<AdminMenuDO> selectByExample(AdminMenuDOExample example);

    AdminMenuDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AdminMenuDO record, @Param("example") AdminMenuDOExample example);

    int updateByExample(@Param("record") AdminMenuDO record, @Param("example") AdminMenuDOExample example);

    int updateByPrimaryKeySelective(AdminMenuDO record);

    int updateByPrimaryKey(AdminMenuDO record);

    //非官方-自定义自动生成
    int batchInsert(@Param("list") List<AdminMenuDO> list);

    //非官方-自定义自动生成
    int batchUpdate(@Param("list") List<AdminMenuDO> list);
}