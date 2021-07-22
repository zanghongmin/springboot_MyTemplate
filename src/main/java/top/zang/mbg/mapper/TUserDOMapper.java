package top.zang.mbg.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import top.zang.mbg.model.TUserDO;
import top.zang.mbg.model.TUserDOExample;

public interface TUserDOMapper {
    long countByExample(TUserDOExample example);

    int deleteByExample(TUserDOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TUserDO record);

    int insertSelective(TUserDO record);

    List<TUserDO> selectByExample(TUserDOExample example);

    TUserDO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TUserDO record, @Param("example") TUserDOExample example);

    int updateByExample(@Param("record") TUserDO record, @Param("example") TUserDOExample example);

    int updateByPrimaryKeySelective(TUserDO record);

    int updateByPrimaryKey(TUserDO record);

    //非官方-自定义自动生成
    int batchInsert(@Param("list") List<TUserDO> list);

    //非官方-自定义自动生成
    int batchUpdate(@Param("list") List<TUserDO> list);
}