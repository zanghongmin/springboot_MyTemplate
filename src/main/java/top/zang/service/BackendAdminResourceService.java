package top.zang.service;

import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import top.zang.config.token.MyToken;
import top.zang.core.*;
import top.zang.core.exception.ReturnTEnum;
import top.zang.dto.backend.*;
import top.zang.enums.ItemStatusEnum;
import top.zang.enums.UserSourceTypeEnum;
import top.zang.mbg.model.*;
import top.zang.util.MyJsonUtil;
import top.zang.util.MyJwtUtil;
import top.zang.util.MyMd5Util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BackendAdminResourceService extends AbstractBackendService {
    public static final Logger logger = LoggerFactory.getLogger(BackendAdminResourceService.class);


    //后台资源分类列表
    public ReturnT<MyPage<AdminResourceCategoryDO>> getAdminResourceCategoryList(MyToken myToken, PageQuery pageQuery) throws Exception {
        AdminResourceCategoryDOExample adminResourceCategoryDOExample = new AdminResourceCategoryDOExample();
        adminResourceCategoryDOExample.createCriteria().andStatusEqualTo(ItemStatusEnum.NORMAL.getCode());
        PageHelper.startPage(pageQuery.getPagenum(), pageQuery.getPagesize());
        List<AdminResourceCategoryDO> adminResourceCategoryDOs = adminResourceCategoryDODao.selectByExample(adminResourceCategoryDOExample);
        return ReturnT.Success(MyPage.restPage(adminResourceCategoryDOs));
    }

}
