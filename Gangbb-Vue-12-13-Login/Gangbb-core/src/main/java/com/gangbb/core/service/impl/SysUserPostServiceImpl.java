package com.gangbb.core.service.impl;

import com.gangbb.core.mapper.SysUserPostMapper;
import com.gangbb.core.service.SysUserPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * SysUserPostServiceImpl
 *
 * @author Gangbb
 * @since 2021-05-27
 */

@Service
public class SysUserPostServiceImpl implements SysUserPostService {

    @Autowired
    private SysUserPostMapper sysUserPostMapper;
	
}