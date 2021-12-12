package com.gangbb.core.service;

import java.util.Set;

/**
 * SysMenuService
 *
 * @author Gangbb
 * @since 2021-05-27
 */

public interface SysMenuService {
    /**
     * 根据用户ID查询菜单权限
     *
     * @param userId 用户ID
     * @return 权限列表
     */
    public Set<String> selectMenuPermsByUserId(Long userId);
    
	
}