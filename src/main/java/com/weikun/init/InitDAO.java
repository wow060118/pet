package com.weikun.init;

import com.weikun.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * �����ߣ�weikun��YST��   ���ڣ�2017/9/17
 * ˵˵���ܣ�
 */
@Repository
public class InitDAO {
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

    public void getHashByKey(){
        HashOperations hashOper=redisTemplate.opsForHash();

        Account a=(Account)hashOper.get("account","weikun");
        System.out.println(a);
    }

}
