package com.weikun.service;

import com.weikun.mapper.CartMapper;
import com.weikun.model.Cart;
import com.weikun.model.Item;
import com.weikun.redis.dao.RedisDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 创建者：weikun【YST】   日期：2017/9/29
 * 说说功能：
 */
@Service
public class CartService {

    @Autowired
    private CartMapper mdao;
    @Autowired
    private RedisDAO rdao;
    public  void addCart(Map map){
        mdao.addCart(map);
        rdao.addCart(map.get("in_username").toString() ,
                map.get("in_itemid").toString() ,
                map.get("in_productid").toString(),
                map.get("in_quantity").toString());
    }
    public List<Cart> queryCart(String username){
        List<String> ls=rdao.queryCart(username ,getOidByUsername(username));
        return makeCList(ls,username);

    }
    private List<Cart>  makeCList(List<String> slist,String username) {
        //carts:weikun:1109":EST_18:AV-CB-01
        List<Cart> cartList=new ArrayList<Cart>();
        for(String s:slist){
            String s1=s.split(":")[2];
            String orderid=(s1).substring(1,s1.length()-1);
            String itemid=s.split(":")[3];
            String productid=s.split(":")[4];

            Set<Item> iset=rdao.getSetByKey(productid+":"+itemid);
            System.out.println("ok");
            Cart c=new Cart();
            Item item=(Item)iset.iterator().next();
            c.setItem(item);
            c.setItemid(itemid);
            c.setQuantity(Integer.parseInt(rdao.getStringByRedis(s)));//从字符串得到

            c.setOrderid(Integer.parseInt(orderid));;
            cartList.add(c);
        }
        return cartList;

    }

    public  String getOidByUsername(String username){//取当前用户的最大订单编号
        return rdao.getStringByRedis("maxid:"+username);

    }

}
