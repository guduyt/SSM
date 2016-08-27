package com.yt.commons.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/**
 * ShardedJedisCache
 *
 * @author yitao
 * @version 1.0.0
 * @date 2016/7/13 17:09
 */

@Service("ShardedJedisCache")
public class ShardedJedisCache implements IRedis {

    @Autowired
    private ShardedJedisPool jedisPool;


    private ShardedJedis jedis;

    public  ShardedJedis getJedis(){
        if(jedis==null){
            jedis=jedisPool.getResource();
        }
        return jedis;
    }

    @Override
    public void del(byte[] key) {
        this.getJedis().del(key);
    }

    @Override
    public void del(String key) {
        this.getJedis().del(key);
    }

    @Override
    public void set(byte[] key, byte[] value, int liveTime) {
        this.set(key, value);
        this.getJedis().expire(key, liveTime);
    }

    @Override
    public void set(String key, String value, int liveTime) {
        this.set(key, value);
        this.getJedis().expire(key, liveTime);
    }


    @Override
    public void set(byte[] key, byte[] value) {
        this.getJedis().set(key, value);
    }

    @Override
    public void set(String key, String value) {
            this.getJedis().set(key, value);
    }

    @Override
    public byte[] get(byte[] key) {
        return this.getJedis().get(key);
    }

    @Override
    public String get(String key) {
        return  this.getJedis().get(key);
    }

    @Override
    public boolean exists(String key) {
        return this.getJedis().exists(key);
    }

}
