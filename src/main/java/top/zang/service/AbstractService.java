package top.zang.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import top.zang.dao.*;
import top.zang.dto.backend.AdminMenuMeta;
import top.zang.dto.backend.AdminMenuVo;
import top.zang.mbg.model.AdminDO;
import top.zang.mbg.model.AdminMenuDO;
import top.zang.util.MyRedisUtil;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;


@Service
public abstract class AbstractService {
    public static final Logger logger = LoggerFactory.getLogger(AbstractService.class);
    @Resource
    AdminDODao adminDODao;
    @Resource
    AdminMenuDODao adminMenuDODao;
    @Resource
    AdminResourceCategoryDODao adminResourceCategoryDODao;
    @Resource
    AdminResourceDODao adminResourceDODao;
    @Resource
    AdminRoleDODao adminRoleDODao;
    @Resource
    MyRedisUtil myRedisUtil;
}
