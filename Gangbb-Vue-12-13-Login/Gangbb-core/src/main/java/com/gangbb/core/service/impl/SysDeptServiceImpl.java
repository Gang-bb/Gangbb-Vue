package com.gangbb.core.service.impl;

import com.gangbb.core.model.entity.SysDept;
import com.gangbb.core.mapper.SysDeptMapper;
import com.gangbb.core.service.SysDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * SysDeptServiceImpl
 *
 * @author Gangbb
 * @since 2021-05-27
 */

@Service
public class SysDeptServiceImpl implements SysDeptService {

    @Autowired
    private SysDeptMapper sysDeptMapper;
	
}