package com.gangbb.common.utils.redis;

import com.gangbb.common.spring.SpringUtils;
import org.apache.ibatis.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;

import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author : Gangbb
 * @ClassName : RedisCache
 * @Description : 自定义Mybatis缓存的Cache接口
 * @Date : 2021/3/9 10:27
 */
public class RedisCache implements Cache {
    private static final Logger logger = LoggerFactory.getLogger(RedisCache.class);

    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock(true);

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private String id;


    public RedisCache(final String id) {
        if (id == null) {
            throw new IllegalArgumentException("Cache instances require an ID");
        }
        logger.info("Redis Cache id " + id);
        this.id = id;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void putObject(Object key, Object value) {
        RedisTemplate redisTemplate = getRedisTemplate();
        if (value != null) {
            //向redis中加入数据，有效期是2天
            redisTemplate.opsForValue().set(key.toString(), value, 2, TimeUnit.DAYS);
        }
    }

    @Override
    public Object getObject(Object key) {
        RedisTemplate redisTemplate = getRedisTemplate();
        try {
            if (key != null) {
                Object object = redisTemplate.opsForValue().get(key.toString());
                return object;
            }
        } catch (Exception e) {
            logger.error("redis exception.....");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Object removeObject(Object key) {
        RedisTemplate redisTemplate = getRedisTemplate();
        try {
            if (key != null) {
                redisTemplate.delete(key.toString());
            }
        } catch (Exception e) {
        }
        return null;
    }

    @Override
    public void clear() {
        RedisTemplate redisTemplate = getRedisTemplate();
        logger.debug("清空缓存");
        try {
            Set<String> keys = redisTemplate.keys("*:" + this.id + "*");
            if (!CollectionUtils.isEmpty(keys)) {
                redisTemplate.delete(keys);
            }
        } catch (Exception e) {
            logger.error("clear exception....");
        }
    }

    @Override
    public int getSize() {
        RedisTemplate redisTemplate = getRedisTemplate();
        Long size = (Long) redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.dbSize();
            }
        });
        return size.intValue();
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return this.readWriteLock;
    }

    private RedisTemplate getRedisTemplate() {
        if (redisTemplate == null) {
            redisTemplate = SpringUtils.getBean("redisTemplate");
        }
        return redisTemplate;
    }
}
