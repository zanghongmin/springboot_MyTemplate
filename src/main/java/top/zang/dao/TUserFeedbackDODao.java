package top.zang.dao;

import org.apache.ibatis.annotations.Select;
import top.zang.dto.TUserFeedbackVo;
import top.zang.mbg.mapper.TUserFeedbackDOMapper;

import java.util.List;

//非官方-自定义自动生成，dao文件中可写自定义的查询sql
public interface TUserFeedbackDODao extends TUserFeedbackDOMapper {


    @Select(" <script>    select t_user_feedback.*, t_user.user_name, t_user.nick_name, t_user.mobile" +
            "    from t_user_feedback" +
            "   left join t_user on t_user_feedback.user_id = t_user.id" +
            "   order by t_user_feedback.id desc" +
            "  </script> ")
    public List<TUserFeedbackVo> getTUserFeedbackVos();


}