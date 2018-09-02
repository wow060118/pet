package com.yfr.redis.dao;

import com.yfr.model.Account;
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
 * �����ߣ�yfr��YST��   ���ڣ�2017/9/17
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
    public Set getSetByKey(String key){
        SetOperations s=redisTemplate.opsForSet();
        return s.members(key);
    }

    public void getAccountByKey(){
        HashOperations hashOper=redisTemplate.opsForHash();

        Account a=(Account)hashOper.get("account","yfr");
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
    public List<String> queryCart( String username,String oid) {//��Զ����Ž��в�ѯ���ﳵ
        List<String> keys = new ArrayList<String>();
        StringBuffer sb=new StringBuffer();
        sb.append("local maxid ");
        sb.append("local carts ");
        sb.append("maxid=redis.call('get','maxid:'..ARGV[1]) ");
        sb.append("local nullflag=string.sub(maxid,2,2) ");// 0 ����1
        sb.append("if nullflag=='0' then ");
        sb.append("return  carts ");

        sb.append("else ");//������Ʒ
        sb.append("carts=redis.call('keys','carts:'..ARGV[1]..':'..ARGV[2]..'*') ");
        sb.append("end ");
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
    //ʹ��lua����redis�����жϵ�ǰ�û��͹��ﳵ�Ĺ�ϵ��
    //���ڴ˶����������ͬ���Ʒ�����Ǿ���ֻ��Ҫ���������е���
    //���ڴ˶��������û��ͬ���Ʒ�����Ǿ���һ���µ���Ʒ��
    public void addCart(final String username,
                        final String itemid,final String productid, final String quantity) {
        List<String> list=new ArrayList<String>();
        StringBuffer sb=new StringBuffer();
        //"1109"
        sb.append("local maxid=redis.call('get','maxid:'..ARGV[4]) ");	//1:����û���ύ 1124����ʵ�Ķ��������
        sb.append("local nullflag=string.sub(maxid,2,2) ");// 0 ����1

       // sb.append("local maxid=redis.call('get','maxid:'..ARGV[4]) ");//ȡ�����ǰ�û��Ķ������
        //�ֽ�maxid��ȡ���������ݣ���һ���Ƿ���1 ���� 0 1,��1�����Լ������0���Ѿ��ύ����
        //�ڶ����ǵ�ǰ�Ķ������
       // sb.append("local nullflag=string.sub(maxid,1,1) ");//ȡ1 ���� 0

        sb.append("local max=string.sub(maxid,3,-2) ");//ȡ����ı��
        //���Ϊ0�Ļ���������Ҫ����max��ţ�
        sb.append("if nullflag=='0' then ");
        sb.append("max=max+1 ");//����
        sb.append("maxid='\"1'..max..'\"' ");//1108
        sb.append("end ");//����
        sb.append("redis.call('set','maxid:'..ARGV[4],maxid) ");//������ֵ
        //�ж��Ƿ��ڹ��ﳵ��������Ʒ
        sb.append("local f=0 ");
        sb.append("local oq=0 ");//������
        sb.append("local nq=0 ");//������
        sb.append("f=redis.call('exists','carts:'..ARGV[4]..':'..max..':'..ARGV[1]..':'..ARGV[2]) ");// "carts:yfr:47:EST_1:FI-SW-01"
        sb.append("if f==1 then ");//��������Ʒ��������Ҫ������Ʒ������
        sb.append("oq=redis.call('get','carts:'..ARGV[4]..':'..max..':'..ARGV[1]..':'..ARGV[2]) ");
        //���ϵ���
        sb.append("nq=oq+ARGV[3] ");//�õ�������
        sb.append("redis.call('set','carts:'..ARGV[4]..':'..max..':'..ARGV[1]..':'..ARGV[2],nq) ");//������ֵ

        sb.append("else ");//������Ʒ
        sb.append("nq=ARGV[3] ");
        sb.append("redis.call('set','carts:'..ARGV[4]..':'..max..':'..ARGV[1]..':'..ARGV[2],nq) ");//������ֵ
        sb.append("end ");
        //�����µĶ�����ţ����ַ�������ʽ
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
