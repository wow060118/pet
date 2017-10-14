package com.weikun.redis.dao;

import com.weikun.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

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
    public Set<Object> keysUnion(Set<Serializable> sourceKeys){
        SetOperations s=redisTemplate.opsForSet();
        Set <Object> s1=s.union("",sourceKeys);
        return s1;
    }
    public Set<Serializable> getAllKeys(Serializable pattern){
        return redisTemplate.keys(pattern);
    }
    public Set getSetByKey(String key){
        SetOperations s=redisTemplate.opsForSet();
        return s.members(key);
    }

    public void getAccountByKey(){
        HashOperations hashOper=redisTemplate.opsForHash();

        Account a=(Account)hashOper.get("account","weikun");
        System.out.println(a);
    }
    public void delStringByRedis(String key){
        redisTemplate.delete(key);
    }
    public String getStringByRedis(String key){
        ValueOperations vo=redisTemplate.opsForValue();
        return vo.get(key).toString();
    }


    public void saveStringByRedis(String key,Object value){
        ValueOperations vo=redisTemplate.opsForValue();
        vo.set(key,value);
    }
    public void setSessionByRedis(Object value){//通过redis存储session
        saveStringByRedis("sessionusername",value);
        redisTemplate.expire("sessionusername",1800, TimeUnit.SECONDS);//cookieusername
    }

    public void removeSessionByRedis(){//注销用，删除redis的模拟session
        redisTemplate.delete("sessionusername");
    }

    public Object getSessionByRedis(String key){//通过redis读取session

        ValueOperations vo=redisTemplate.opsForValue();
        return vo.get("sessionusername");

    }
    public List<String> queryCart( String username,String oid) {//针对订单号进行查询购物车
        List<String> keys = new ArrayList<String>();
        StringBuffer sb=new StringBuffer();
        sb.append("local carts ");
        sb.append("carts=redis.call('keys','carts:'..ARGV[1]..':'..ARGV[2]..'*') ");
        sb.append("return carts ");
        ArrayList f=redisTemplate.execute(new RedisCallback<ArrayList>() {

            @Override
            public ArrayList doInRedis(RedisConnection redisConnection) throws DataAccessException {
                Jedis j= (Jedis)redisConnection.getNativeConnection();
                List keys=new ArrayList();
                List values=new ArrayList();

                values.add(username);
                values.add(oid);


                return (ArrayList)j.eval(sb.toString(),keys,values);
            }
        });
        System.out.println(f);
        return f;

    }
    //使用lua操作redis，来判断当前用户和购物车的关系，
    //当在此订单编号下有同类产品，我们就是只需要把数量进行叠加
    //当在此订单编号下没有同类产品，我们就是一个新的商品，
    public void addCart(final String username,
                        final String itemid,final String productid, final String quantity) {
        List<String> list=new ArrayList<String>();
        StringBuffer sb=new StringBuffer();
        //"1109"
        sb.append("local maxid=redis.call('get','maxid:'..ARGV[4]) ");	//1:代表还没有提交 1124：真实的订单最大编号
        sb.append("local nullflag=string.sub(maxid,2,2) ");// 0 还是1

       // sb.append("local maxid=redis.call('get','maxid:'..ARGV[4]) ");//取得最大当前用户的订单编号
        //分解maxid，取出两段内容，第一段是否是1 或者 0 1,：1：可以继续购物，0：已经提交。，
        //第二段是当前的订单编号
       // sb.append("local nullflag=string.sub(maxid,1,1) ");//取1 或者 0

        sb.append("local max=string.sub(maxid,3,-2) ");//取后面的编号
        //如果为0的话，我们需要自增max编号，
        sb.append("if nullflag=='0' then ");
        sb.append("max=max+1 ");//自增
        sb.append("maxid='\"1'..max..'\"' ");//1108
        sb.append("end ");//自增
        sb.append("redis.call('set','maxid:'..ARGV[4],maxid) ");//送入新值
        //判断是否在购物车中有老商品
        sb.append("local f=0 ");
        sb.append("local oq=0 ");//老数量
        sb.append("local nq=0 ");//新数量
        sb.append("f=redis.call('exists','carts:'..ARGV[4]..':'..max..':'..ARGV[1]..':'..ARGV[2]) ");// "carts:weikun:47:EST_1:FI-SW-01"
        sb.append("if f==1 then ");//存在老商品，我们需要叠加商品的数量
        sb.append("oq=redis.call('get','carts:'..ARGV[4]..':'..max..':'..ARGV[1]..':'..ARGV[2]) ");
        //新老叠加
        sb.append("nq=oq+ARGV[3] ");//得到新数量
        sb.append("redis.call('set','carts:'..ARGV[4]..':'..max..':'..ARGV[1]..':'..ARGV[2],nq) ");//送入新值

        sb.append("else ");//纯新商品
        sb.append("nq=ARGV[3] ");
        sb.append("redis.call('set','carts:'..ARGV[4]..':'..max..':'..ARGV[1]..':'..ARGV[2],nq) ");//送入新值
        sb.append("end ");
        //返回新的订单编号，以字符串的形式
        sb.append("return maxid..'-'..max..'-'..nullflag..'-'..f..'-'..nq..'' ");

        Object f=redisTemplate.execute(new RedisCallback<Object>() {

            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                Jedis j= (Jedis)redisConnection.getNativeConnection();
                List keys=new ArrayList();
                List values=new ArrayList();
                values.add(itemid);
                values.add(productid);
                values.add(quantity);
                values.add(username);


                return j.eval(sb.toString(),keys,values);
            }
        });

        System.out.println(f);

    }

}
