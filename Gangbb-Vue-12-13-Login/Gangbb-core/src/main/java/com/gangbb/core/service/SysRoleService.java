package com.gangbb.core.service;

import java.util.Set;

/**
 * SysRoleService
 *
 * @author Gangbb
 * @since 2021-05-27
 */

public interface SysRoleService {
    /**
     * 根据用户ID查询角色
     *
     * @param userId 用户ID
     * @return 权限列表
     */
    Set<String> selectRolePermissionByUserId(Long userId);
}