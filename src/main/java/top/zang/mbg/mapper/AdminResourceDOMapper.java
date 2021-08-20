package top.zang.mbg.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import top.zang.mbg.model.AdminResourceDO;
import top.zang.mbg.model.AdminResourceDOExample;

public interface AdminResourceDOMapper {
    long countByExample(AdminResourceDOExample example);

    int deleteByExample(AdminResourceDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AdminResourceDO record);

    int insertSelective(AdminResourceDO record);

    List<AdminResourceDO> selectByExample(AdminResourceDOExample example);

    AdminResourceDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AdminResourceDO record, @Param("example") AdminResourceDOExample example);

    int updateByExample(@Param("record") AdminResourceDO record, @Param("example") AdminResourceDOExample example);

    int updateByPrimaryKeySelective(AdminResourceDO record);

    int updateByPrimaryKey(AdminResourceDO record);

    //非官方-自定义自动生成
    int batchInsert(@Param("list") List<AdminResourceDO> list);

    //非官方-自定义自动生成
    int batchUpdate(@Param("list") List<AdminResourceDO> list);
}