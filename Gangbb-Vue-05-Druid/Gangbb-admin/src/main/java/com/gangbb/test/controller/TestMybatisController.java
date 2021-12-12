package com.gangbb.test.controller;

import com.gangbb.test.model.entity.TestMybatis;
import com.gangbb.test.service.TestMybatisService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (TestMybatis)表控制层
 *
 * @author Gangbb
 * @since 2021-03-07 08:39:32
 */
@RestController
@RequestMapping("testMybatis")
public class TestMybatisController {
    /**
     * 服务对象
     */
    @Resource
    private TestMybatisService testMybatisService;

    /**
     * 通过主键查询单条数据
     *
     * @return 单条数据
     */
    @GetMapping("/selectOne")
    public TestMybatis selectOne() {
        return this.testMybatisService.queryById(1);
    }

}