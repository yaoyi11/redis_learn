package com.redis.learn;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.net.URI;

public class RedisFactory {
    private static JedisPool jedisPool = null;

    private RedisFactory(){

    }
    public static JedisPool getInstance(){
        if(jedisPool == null) {
            synchronized (RedisFactory.class){
                if(jedisPool == null) {
                    JedisPoolConfig config = new JedisPoolConfig();

                    config.setMaxTotal(64);
                    config.setMaxIdle(64);
                    config.setMinIdle(64);

                    config.setTestOnBorrow(true);
                    config.setTestOnReturn(true);
                    config.setTestWhileIdle(true);

                    config.setMaxWaitMillis(3000);
                    config.setMinEvictableIdleTimeMillis(60);
                    config.setTimeBetweenEvictionRunsMillis(30);
                    config.setBlockWhenExhausted(false);
                    URI url= URI.create("redis://192.168.253.137:6379");

                    jedisPool = new JedisPool(config, url, 2000, 2000);
                }
            }
        }
        return jedisPool;
    }
}
