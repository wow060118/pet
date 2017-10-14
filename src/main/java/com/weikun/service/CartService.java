package com.weikun.service;

import com.weikun.mapper.CartMapper;
import com.weikun.mapper.OrdersMapper;
import com.weikun.model.Cart;
import com.weikun.model.CartKey;
import com.weikun.model.Item;
import com.weikun.model.Orders;
import com.weikun.redis.dao.RedisDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 创建者：weikun【YST】   日期：2017/9/29
 * 说说功能：
 */
@Service
public class CartService {

    @Autowired
    private CartMapper mdao;
    @Autowired
    private OrdersMapper odao;

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
    public void updateByPrimaryKey(Cart record){
        //redis--先删除，再增加
        deleteRedis(record);

        rdao.addCart(record.getUsername(),
                record.getItemid() ,
                record.getProductid(),
                record.getQuantity().toString());

        mdao.updateByPrimaryKey(record);


    }
    public void checkOut(Orders orders){
        orders.setOrderdate(new Date());
        odao.updateByPrimaryKey(orders);
        String txt=new SimpleDateFormat("yyyy-MM-dd").format(new Date())+":"+orders.getTotalprice();
        //改总计
        rdao.saveStringByRedis("orders:" + orders.getUsername() + ":" + orders.getOrderid(), txt);
        //改订单编号
        rdao.saveStringByRedis("maxid:"+orders.getUsername(),"0"+orders.getOrderid()+"");
    }

    public void deleteRedis(CartKey key){
        StringBuffer sb=new StringBuffer();
        sb.append("carts");
        sb.append(":");
        sb.append(key.getUsername());
        sb.append(":");
        sb.append(key.getOrderid());
        sb.append(":");
        sb.append(key.getItemid());
        sb.append(":");
        sb.append(key.getProductid());

        rdao.delStringByRedis(sb.toString());

    }

    public void deleteByPrimaryKey(CartKey key){
        StringBuffer sb=new StringBuffer();
        sb.append("carts");
        sb.append(":");
        sb.append(key.getUsername());
        sb.append(":");
        sb.append(key.getOrderid());
        sb.append(":");
        sb.append(key.getItemid());
        sb.append(":");
        sb.append(key.getProductid());

        rdao.delStringByRedis(sb.toString());
        mdao.deleteByPrimaryKey(key);//删除mysql
    }

    private List<Cart>  makeCList(List<String> slist,String username) {
        //carts:weikun:1109":EST_18:AV-CB-01
        List<Cart> cartList=new ArrayList<Cart>();
        for(String s:slist){
            String s1=s.split(":")[2];
            String orderid=s1;
           // String orderid=(s1).substring(1,s1.length()-1);
            String itemid=s.split(":")[3];
            String productid=s.split(":")[4];

            Set<Item> iset=rdao.getSetByKey(productid+":"+itemid);
            System.out.println("ok");
            Cart c=new Cart();
            Item item=(Item)iset.iterator().next();
            c.setItem(item);
            c.setItemid(itemid);
            c.setQuantity(Integer.parseInt(rdao.getStringByRedis(s)));//从字符串得到
            c.setUsername(username);
            c.setOrderid(Integer.parseInt(orderid));
            c.setProductid(productid);
            cartList.add(c);
        }
        return cartList;

    }

    public  String getOidByUsername(String username){//取当前用户的最大订单编号
        return rdao.getStringByRedis("maxid:"+username).substring(1);

    }

}
