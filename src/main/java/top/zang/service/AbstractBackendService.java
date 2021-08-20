package top.zang.service;


import cn.hutool.core.util.StrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import top.zang.dao.*;
import top.zang.dto.backend.AdminMenuMeta;
import top.zang.dto.backend.AdminMenuVo;
import top.zang.enums.MenuTypeEnum;
import top.zang.mbg.model.AdminMenuDO;
import top.zang.util.MyRedisUtil;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Service
public abstract class AbstractBackendService extends AbstractService {
    public static final Logger logger = LoggerFactory.getLogger(AbstractBackendService.class);

    /**
     * 后台菜单列表转化
     */
    public  AdminMenuVo covertMenuNode(AdminMenuDO menu, List<AdminMenuDO> menuList) {
        AdminMenuVo node = new AdminMenuVo();
        AdminMenuMeta adminMenuMeta = new AdminMenuMeta();
        adminMenuMeta.setIcon(menu.getIcon());
        adminMenuMeta.setNoCache(menu.getIs_cache());
        adminMenuMeta.setTitle(menu.getName());
        BeanUtils.copyProperties(menu, node);
        node.setHidden(!menu.getVisible());
        node.setName(getRouteName(menu));//设置name
        node.setPath(getRouterPath(menu));//设置path
        node.setComponent(getComponent(menu));//设置component
        node.setRedirect(menu.getIs_frame() ? "Redirect" : "noRedirect");
        node.setAlwaysShow(menu.getVisible());
        node.setMeta(adminMenuMeta);
        List<AdminMenuVo> children = menuList.stream()
                .filter(subMenu -> subMenu.getPid().equals(menu.getId()))
                .map(subMenu -> covertMenuNode(subMenu, menuList))
                .sorted(Comparator.comparing(AdminMenuVo::getSort)).collect(Collectors.toList());
        node.setChildren(children);
        return node;
    }

    //设置权限-路由名称name
    private   String  getRouteName(AdminMenuDO menu){
        String path = menu.getPath();
        path = path.substring(0, 1).toUpperCase() + path.substring(1);
        menu.setName(path);
        if(isMeunFrame(menu)){
            menu.setName("");
        }
        return menu.getName();
    }
    //判断权限类型是否为菜单
    private  Boolean  isMeunFrame(AdminMenuDO menu) {
        if(menu.getPid()==0 && MenuTypeEnum.C.getCode().equals(menu.getMenu_type()) && !menu.getIs_frame()){
            return true;
        }
        return false;
    }
    //设置权限-路由path
    private  String  getRouterPath(AdminMenuDO menu){
        if(menu.getPid()==0 && MenuTypeEnum.M.getCode().equals(menu.getMenu_type()) && !menu.getIs_frame()){
            menu.setPath("/"+menu.getPath());
        }else if(isMeunFrame(menu)){
            menu.setPath("/");
        }
        return menu.getPath();
    }
    //设置权限-路由Component
    private  String  getComponent(AdminMenuDO menu){
        if(StrUtil.isNotBlank(menu.getComponent()) && !isMeunFrame(menu)){
            return menu.getComponent();
        }else if(StrUtil.isBlank(menu.getComponent()) && menu.getPid()==0
                && isParentView(menu)){
            return "ParentView";
        }
        return "Layout";
    }
    //判断权限类型是否为父级目录
    private  Boolean  isParentView(AdminMenuDO menu) {
        if(menu.getPid()!=0 && MenuTypeEnum.M.getCode().equals(menu.getMenu_type())){
            return true;
        }
        return false;
    }

}
