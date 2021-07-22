package top.zang.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import top.zang.config.token.MyToken;
import top.zang.core.MyException;
import top.zang.dao.TUserDODao;
import top.zang.dao.TUserFeedbackDODao;
import top.zang.mbg.model.TUserDO;

import javax.annotation.Resource;


@Service
public abstract class AbstractService {
    public static final Logger logger = LoggerFactory.getLogger(AbstractService.class);

    @Resource
    TUserDODao tuserDODao;
    @Resource
    TUserFeedbackDODao tuserFeedbackDODao;

    public TUserDO getTUserDOFromToken(MyToken myToken){
        TUserDO tuserDO = tuserDODao.selectByPrimaryKey(myToken.getUserId());
        if(tuserDO==null){
            throw  new MyException("获取用户失败");
        }
        return tuserDO;
    }



}
