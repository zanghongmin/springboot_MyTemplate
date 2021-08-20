package top.zang.mbg.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import top.zang.mbg.model.AdminRoleDO;
import top.zang.mbg.model.AdminRoleDOExample;

public interface AdminRoleDOMapper {
    long countByExample(AdminRoleDOExample example);

    int deleteByExample(AdminRoleDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AdminRoleDO record);

    int insertSelective(AdminRoleDO record);

    List<AdminRoleDO> selectByExample(AdminRoleDOExample example);

    AdminRoleDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AdminRoleDO record, @Param("example") AdminRoleDOExample example);

    int updateByExample(@Param("record") AdminRoleDO record, @Param("example") AdminRoleDOExample example);

    int updateByPrimaryKeySelective(AdminRoleDO record);

    int updateByPrimaryKey(AdminRoleDO record);

    //非官方-自定义自动生成
    int batchInsert(@Param("list") List<AdminRoleDO> list);

    //非官方-自定义自动生成
    int batchUpdate(@Param("list") List<AdminRoleDO> list);
}