package top.zang.mbg.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import top.zang.mbg.model.AdminDO;
import top.zang.mbg.model.AdminDOExample;

public interface AdminDOMapper {
    long countByExample(AdminDOExample example);

    int deleteByExample(AdminDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AdminDO record);

    int insertSelective(AdminDO record);

    List<AdminDO> selectByExample(AdminDOExample example);

    AdminDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AdminDO record, @Param("example") AdminDOExample example);

    int updateByExample(@Param("record") AdminDO record, @Param("example") AdminDOExample example);

    int updateByPrimaryKeySelective(AdminDO record);

    int updateByPrimaryKey(AdminDO record);

    //非官方-自定义自动生成
    int batchInsert(@Param("list") List<AdminDO> list);

    //非官方-自定义自动生成
    int batchUpdate(@Param("list") List<AdminDO> list);
}