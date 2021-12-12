package com.gangbb.test.model.dao;

import com.gangbb.test.model.entity.TestMybatis;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (TestMybatis)表数据库访问层
 *
 * @author Gangbb
 * @since 2021-03-07 08:39:31
 */
@Mapper
public interface TestMybatisMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TestMybatis queryById(Integer id);

}