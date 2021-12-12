package com.gangbb.test.service;

import com.gangbb.test.model.entity.TestMybatis;
import java.util.List;

/**
 * (TestMybatis)表服务接口
 *
 * @author Gangbb
 * @since 2021-03-07 08:39:31
 */
public interface TestMybatisService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TestMybatis queryById(Integer id);


}