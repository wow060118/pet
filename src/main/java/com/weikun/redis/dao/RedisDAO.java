package com.weikun.redis.dao;

import com.weikun.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * �����ߣ�weikun��YST��   ���ڣ�2017/9/17
 * ˵˵���ܣ�
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
    public Set<Object> keysUnion(Set<Serializable> sourceKeys){
        SetOperations s=redisTemplate.opsForSet();
        Set <Object> s1=s.union("",sourceKeys);
        return s1;
    }
    public Set<Serializable> getAllKeys(Serializable pattern){
        return redisTemplate.keys(pattern);
    }
    public Object getSetByKey(String key){
        SetOperations s=redisTemplate.opsForSet();
        return s.members(key);
    }
    public void getHashByKey(){
        HashOperations hashOper=redisTemplate.opsForHash();

        Account a=(Account)hashOper.get("account","weikun");
        System.out.println(a);
    }
    public void saveStringByRedis(String key,Object value){
        ValueOperations vo=redisTemplate.opsForValue();
        vo.set(key,value);
    }
    public void setSessionByRedis(Object value){//ͨ��redis�洢session
        saveStringByRedis("sessionusername",value);
        redisTemplate.expire("sessionusername",1800, TimeUnit.SECONDS);//cookieusername
    }

    public void removeSessionByRedis(){//ע���ã�ɾ��redis��ģ��session
        redisTemplate.delete("sessionusername");
    }

    public Object getSessionByRedis(String key){//ͨ��redis��ȡsession

        ValueOperations vo=redisTemplate.opsForValue();
        return vo.get("sessionusername");

    }

}
