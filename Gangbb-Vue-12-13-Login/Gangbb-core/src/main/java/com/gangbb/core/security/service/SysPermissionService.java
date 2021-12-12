package com.gangbb.core.security.service;

import com.gangbb.core.model.entity.SysUser;
import com.gangbb.core.service.SysMenuService;
import com.gangbb.core.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * @author : Gangbb
 * @ClassName : SysPermissionService
 * @Description : 用户权限处理
 * @Date : 2021/5/28 12:56
 */
@Component
public class SysPermissionService {

    @Autowired
    private SysRoleService roleService;

    @Autowired
    private SysMenuService menuService;


    /**
     * 获取角色数据权限
     *
     * @param user 用户信息
     * @return 角色权限信息
     */
    public Set<String> getRolePermission(SysUser user)
    {
        Set<String> roles = new HashSet<String>();
        // 管理员拥有所有权限
        if (user.isAdmin())
        {
            roles.add("admin");
        }
        else
        {
            roles.addAll(roleService.selectRolePermissionByUserId(user.getId()));
        }
        return roles;
    }

    /**
     * 获取菜单数据权限
     *
     * @param user 用户信息
     * @return 菜单权限信息
     */
    public Set<String> getMenuPermission(SysUser user)
    {
        Set<String> perms = new HashSet<String>();
        // 管理员拥有所有权限
        if (user.isAdmin())
        {
            perms.add("*:*:*");
        }
        else
        {
            perms.addAll(menuService.selectMenuPermsByUserId(user.getId()));
        }
        return perms;
    }
}

