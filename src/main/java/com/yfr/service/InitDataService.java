package com.yfr.service;

import com.yfr.initdao.InitDataDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 创建者：yfr【YST】   日期：2017/9/22
 * 说说功能：
 */
@Service
public class InitDataService {

    @Autowired
    private InitDataDAO dao;

    public void init(){
        dao.initAccount();
        dao.initProfile();
        dao.initCategory();
        dao.initProduct();
        dao.initItem();
        dao.initCarts();
        dao.initOrders();

    }
    }
