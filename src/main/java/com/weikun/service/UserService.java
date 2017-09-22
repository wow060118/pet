package com.weikun.service;

import com.weikun.mapper.AccountMapper;
import com.weikun.model.Account;
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

    public boolean login(Account account){
        return dao.login(account)==null?false:true;
    }
}
