package com.weikun.service;

import com.weikun.mapper.CartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 创建者：weikun【YST】   日期：2017/9/29
 * 说说功能：
 */
@Service
public class CartService {

    @Autowired
    private CartMapper mdao;

    public  void addCart(Map map){
        mdao.addCart(map);
    }

}
