package com.weikun.controller;

import com.weikun.init.InitDataDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 创建者：weikun【YST】   日期：2017/9/17
 * 说说功能：
 */
@RestController
@RequestMapping("/init")
@CrossOrigin
public class InitController {
    @Autowired
    private  InitDataDAO initDataDao;

    @RequestMapping("/init")
    public void init(){
        initDataDao.initAccount();
        initDataDao.getHashByKey();


    }


}
