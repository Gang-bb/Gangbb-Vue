package com.gangbb.test.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gangbb.common.utils.redis.RedisUtil;
import com.gangbb.test.model.entity.TestMybatis;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : Gangbb
 * @ClassName : TestRedisController
 * @Description :
 * @Date : 2021/3/9 7:32
 */
@RestController
@RequestMapping("/test/redis")
public class TestRedisController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RedisUtil redisUtil;



    @GetMapping("/hello")
    public String helloRedis(){
        // 测试写入一个string类型键值,过期时间20S
        redisUtil.set("key1", "Gangbb", 20);
        System.out.println("获取key1值"+ redisUtil.set("key1", "测试redis", 20));
        return "测试redis";
    }

    @Cacheable("cache1")
    @GetMapping("/hello2")
    public String helloRedis2(){
        return "xxredis";
    }

    @GetMapping("/hello3")
    public JSONObject helloRedis3(){
        return getTestDetail();
    }

    public JSONObject getTestDetail() {
        JSONObject retJson = new JSONObject();
        String retCode = "1";
        String retMsg = "操作失败！";
        JSONObject bizDataJson = new JSONObject();
        try {
            TestMybatis testMybatis = new TestMybatis();
            testMybatis.setId(22);
            testMybatis.setName("梁yx");
            testMybatis.setGender("女");

            String key = "TestMybatis::"+testMybatis.getId();
            //向Redis中缓存数据，-1为设置永久时效
            redisUtil.set(key, testMybatis,-1);

            bizDataJson = JSONObject.parseObject(JSON.toJSONString(testMybatis));
            retCode = "0";
            retMsg = "操作成功！";
        } catch (Exception e) {
            log.error(String.valueOf(e));
        }
        retJson.put("retCode", retCode);
        retJson.put("retMsg", retMsg);
        retJson.put("bizData", bizDataJson);
        return retJson;
    }
}
