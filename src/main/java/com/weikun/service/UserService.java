package com.weikun.service;

import com.weikun.mapper.AccountMapper;
import com.weikun.mapper.ProfileMapper;
import com.weikun.model.Account;
import com.weikun.redis.dao.RedisDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 创建者：weikun【YST】   日期：2017/9/22
 * 说说功能：
 */
@Service
public class UserService {
    @Autowired
    private AccountMapper adao;

    @Autowired
    private ProfileMapper pdao;

    @Autowired
    private RedisDAO rdao;
    public boolean login(Account account){
        return adao.login(account)==null?false:true;
    }
    public void saveSession(Object object){
        rdao.setSessionByRedis(object);
    }
    public void removeSession(){
        rdao.removeSessionByRedis();
    }

    public Object getSession(String key){
        return rdao.getSessionByRedis(key);
    }
    public int insert(Account record){

        if( adao.insert(record)>0  && pdao.insert(record.getProfile())>0){
                rdao.saveHashByRedis("account",record.getUsername(),record); // redis同步
            return 1;
        }
        return 0;//失败

    }
}
