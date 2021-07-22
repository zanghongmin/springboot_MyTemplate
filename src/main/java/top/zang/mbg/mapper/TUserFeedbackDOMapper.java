package top.zang.mbg.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import top.zang.mbg.model.TUserFeedbackDO;
import top.zang.mbg.model.TUserFeedbackDOExample;

public interface TUserFeedbackDOMapper {
    long countByExample(TUserFeedbackDOExample example);

    int deleteByExample(TUserFeedbackDOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TUserFeedbackDO record);

    int insertSelective(TUserFeedbackDO record);

    List<TUserFeedbackDO> selectByExample(TUserFeedbackDOExample example);

    TUserFeedbackDO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TUserFeedbackDO record, @Param("example") TUserFeedbackDOExample example);

    int updateByExample(@Param("record") TUserFeedbackDO record, @Param("example") TUserFeedbackDOExample example);

    int updateByPrimaryKeySelective(TUserFeedbackDO record);

    int updateByPrimaryKey(TUserFeedbackDO record);

    //非官方-自定义自动生成
    int batchInsert(@Param("list") List<TUserFeedbackDO> list);

    //非官方-自定义自动生成
    int batchUpdate(@Param("list") List<TUserFeedbackDO> list);
}