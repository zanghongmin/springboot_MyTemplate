package top.zang.service;


import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import top.zang.config.token.MyToken;
import top.zang.core.MyPage;
import top.zang.core.PageQuery;
import top.zang.core.QueryHead;
import top.zang.core.ReturnT;
import top.zang.core.exception.ReturnTEnum;
import top.zang.dto.*;
import top.zang.enums.ItemStatusEnum;
import top.zang.mbg.model.TUserDO;
import top.zang.mbg.model.TUserDOExample;
import top.zang.mbg.model.TUserFeedbackDO;
import top.zang.util.MyJwtUtil;
import top.zang.util.MyMd5Util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public  class UserService extends AbstractService{
    public static final Logger logger = LoggerFactory.getLogger(UserService.class);
    //用户登录接口
    public ReturnT<String> login(LoginDto loginDto){
        TUserDOExample example = new TUserDOExample();
        example.createCriteria().andUserNameEqualTo(loginDto.getUser_name()).andPasswordEqualTo(MyMd5Util.md5(loginDto.getPassword())).andStatusEqualTo(ItemStatusEnum.NORMAL.getCode());
        List<TUserDO> tuserDODOs = tuserDODao.selectByExample(example);
        if(tuserDODOs.size()<=0){
            return ReturnT.Fail("用户不存在或密码错误");
        }
        TUserDO tuserDODO = tuserDODOs.get(0);
        MyToken myToken = new MyToken();
        myToken.setUserId(tuserDODO.getId());
        return ReturnT.Success(MyJwtUtil.createJwt(myToken));
    }

    //获取用户列表接口
    public ReturnT<MyPage<TUserVo>> getUserList(MyToken myToken, PageQuery pageQuery){
        TUserDOExample example = new TUserDOExample();
        example.createCriteria().andStatusEqualTo(ItemStatusEnum.NORMAL.getCode());
        PageHelper.startPage(pageQuery.getPagenum(), pageQuery.getPagesize());
        List<TUserDO> tuserDODOs = tuserDODao.selectByExample(example);
        MyPage myPage  = MyPage.restPage(tuserDODOs);
        List<TUserVo> tuserVos = new ArrayList<>();
        for(TUserDO tuserDODO:tuserDODOs){
            TUserVo tuserVo = new TUserVo();
            BeanUtils.copyProperties(tuserDODO,tuserVo);
            tuserVos.add(tuserVo);
        }
        myPage.setList(tuserVos);
        return ReturnT.Success(myPage);
    }

   //新增一个用户接口
    public ReturnT insert_one(MyToken myToken, TUserDto tuserDto){
        TUserDO userinfo = getTUserDOFromToken(myToken);
        logger.info("新增加一个用户，操作用户为 "+ userinfo.getId() + " - " + userinfo.getUser_name());
        TUserDO tuserDO = new TUserDO();
        BeanUtils.copyProperties(tuserDto,tuserDO);
        tuserDO.setPassword(MyMd5Util.md5(tuserDto.getPassword()));
        tuserDO.setStatus(ItemStatusEnum.NORMAL.getCode());
        if(tuserDODao.insertSelective(tuserDO)<=0){
            return ReturnT.Fail("创建失败");
        }
        return ReturnT.Success();
    }

    //新增多个用户接口
    public ReturnT insert_many(MyToken myToken,List<TUserDto> tuserDtos){
        TUserDO userinfo = getTUserDOFromToken(myToken);
        logger.info("新增加多用户，操作用户为 "+ userinfo.getId() + " - " + userinfo.getUser_name());
        List<TUserDO> tuserDOs = new ArrayList<>();
        for(TUserDto tuserDto:tuserDtos){
            TUserDO tuserDO = new TUserDO();
            BeanUtils.copyProperties(tuserDto,tuserDO);
            tuserDO.setPassword(MyMd5Util.md5(tuserDto.getPassword()));
            tuserDO.setStatus(ItemStatusEnum.NORMAL.getCode());
            tuserDO.setCreate_time(new Date());
            tuserDO.setUpdate_time(tuserDO.getCreate_time());
            tuserDOs.add(tuserDO);
        }
        if(tuserDODao.batchInsert(tuserDOs)<=0){
            return ReturnT.Fail("创建失败");
        }
        return ReturnT.Success();
    }

    //更新多个用户接口
    public ReturnT update_many(MyToken myToken, QueryHead queryHead){
        TUserDOExample example = new TUserDOExample();
        example.createCriteria().andStatusEqualTo(ItemStatusEnum.NORMAL.getCode());
        List<TUserDO> tuserDODOs = tuserDODao.selectByExample(example);
        tuserDODOs.forEach(t->{
            t.setUpdate_time(new Date());
        });
       int count = tuserDODao.batchUpdate(tuserDODOs);
        return ReturnT.Success(count);
    }
    //用户反馈接口
    public ReturnT user_feedback(MyToken myToken, TUserFeedbackDto tuserFeedbackDto){
        TUserDO tuserDO = getTUserDOFromToken(myToken);
        TUserFeedbackDO tuserFeedbackDO = new TUserFeedbackDO();
        tuserFeedbackDO.setContent(tuserFeedbackDto.getContent());
        tuserFeedbackDO.setUser_id(tuserDO.getId());
        if(tuserFeedbackDODao.insertSelective(tuserFeedbackDO)<=0){
            return ReturnT.Fail("创建失败");
        }
        return ReturnT.Success();
    }

    //用户反馈列表接口
    public ReturnT<MyPage<TUserFeedbackVo>> user_feedback_list(MyToken myToken,  PageQuery pageQuery){
        PageHelper.startPage(pageQuery.getPagenum(), pageQuery.getPagesize());
        List<TUserFeedbackVo> tuserFeedbackVos =  tuserFeedbackDODao.getTUserFeedbackVos();
        return ReturnT.Success(MyPage.restPage(tuserFeedbackVos));
    }


}
