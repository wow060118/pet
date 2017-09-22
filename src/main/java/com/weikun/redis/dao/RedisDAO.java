package com.weikun.redis.dao;

import com.weikun.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * 创建者：weikun【YST】   日期：2017/9/17
 * 说说功能：
 */
@Repository
public class RedisDAO {
    @Autowired
    private RedisTemplate<Serializable, Serializable> redisTemplate;

    @Autowired
    private RedisTemplate<String,Integer> redisTemplate1;

    @Autowired
    private StringRedisTemplate stringRedisTemplate; //1

    public void saveHashByRedis(String tablename, String key, Object obj) {
        HashOperations hash=redisTemplate.opsForHash();
        hash.put(tablename,key,obj);
    }
    public void saveSetByRedis(String key,Object value){
        SetOperations set=redisTemplate.opsForSet();
        set.add(key,value);
    }

    public void getHashByKey(){
        HashOperations hashOper=redisTemplate.opsForHash();

        Account a=(Account)hashOper.get("account","weikun");
        System.out.println(a);
    }

}
