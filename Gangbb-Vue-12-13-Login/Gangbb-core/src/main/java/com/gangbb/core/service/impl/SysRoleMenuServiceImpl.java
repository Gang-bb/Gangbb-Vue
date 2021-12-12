package com.gangbb.core.service.impl;

import com.gangbb.core.mapper.SysRoleMenuMapper;
import com.gangbb.core.service.SysRoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * SysRoleMenuServiceImpl
 *
 * @author Gangbb
 * @since 2021-05-27
 */

@Service
public class SysRoleMenuServiceImpl implements SysRoleMenuService {

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

}