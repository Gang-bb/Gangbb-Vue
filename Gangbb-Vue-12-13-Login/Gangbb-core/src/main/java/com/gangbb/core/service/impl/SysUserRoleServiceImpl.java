package com.gangbb.core.service.impl;

import com.gangbb.core.mapper.SysUserRoleMapper;
import com.gangbb.core.service.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * SysUserRoleServiceImpl
 *
 * @author Gangbb
 * @since 2021-05-27
 */

@Service
public class SysUserRoleServiceImpl implements SysUserRoleService {

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;
	
}