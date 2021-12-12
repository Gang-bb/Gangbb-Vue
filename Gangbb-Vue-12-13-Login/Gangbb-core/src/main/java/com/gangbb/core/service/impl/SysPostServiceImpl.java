package com.gangbb.core.service.impl;

import com.gangbb.core.mapper.SysPostMapper;
import com.gangbb.core.service.SysPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * SysPostServiceImpl
 *
 * @author Gangbb
 * @since 2021-05-27
 */

@Service
public class SysPostServiceImpl implements SysPostService {

    @Autowired
    private SysPostMapper sysPostMapper;
	
}