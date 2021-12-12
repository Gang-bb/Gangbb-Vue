package com.gangbb.test.service.impl;

import com.gangbb.test.model.entity.TestMybatis;
import com.gangbb.test.mapper.TestMybatisMapper;
import com.gangbb.test.service.TestMybatisService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * (TestMybatis)表服务实现类
 *
 * @author Gangbb
 * @since 2021-03-07 08:39:32
 */
@Service("testMybatisService")
public class TestMybatisServiceImpl implements TestMybatisService {
    @Resource
    private TestMybatisMapper testMybatisMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public TestMybatis queryById(Integer id) {
        return this.testMybatisMapper.queryById(id);
    }

}