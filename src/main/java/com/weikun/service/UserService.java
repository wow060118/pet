package com.weikun.service;

import com.weikun.mapper.AccountMapper;
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
    private AccountMapper dao;

    @Autowired
    private RedisDAO rdao;

    public boolean login(Account account){
        return dao.login(account)==null?false:true;
    }
    public void saveSession(Object object){
        rdao.setSessionByRedis(object);
    }
    public Object getSession(String key){
        return rdao.getSessionByRedis(key);
    }
}
