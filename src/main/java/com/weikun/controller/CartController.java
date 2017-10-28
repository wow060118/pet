package com.weikun.controller;

import com.weikun.model.Cart;
import com.weikun.model.CartKey;
import com.weikun.model.Orders;
import com.weikun.service.CartService;
import com.weikun.service.PetService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 创建者：weikun【YST】   日期：2017/9/29
 * 说说功能：
 */
@RestController
@RequestMapping("/cart")
@CrossOrigin
@Api(value = "cartController", description = "购物车管理控制器")
public class CartController {
    @Autowired
    private CartService cservice;

    @RequestMapping(value = "/del/{oid}/{username}/{iid}/{pid}/",method = RequestMethod.DELETE)
    @ApiOperation(value="删除购物车数据", notes="删除购物车数据")
    @ApiImplicitParam(name = "cart", value = "购物车对象",
            required = true, dataType = "Cart")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "成功")})
    public ResponseEntity<List<Cart>> delCart(@PathVariable String oid,
                                              @PathVariable String username,
                                              @PathVariable String iid,
                                              @PathVariable String pid){
        CartKey key=new CartKey();
        key.setOrderid(Integer.parseInt(oid));
        key.setUsername(username);
        key.setItemid(iid);
        key.setProductid(pid);
        cservice.deleteByPrimaryKey(key);
        return queryCart(username);
    }
    @RequestMapping(value = "/update/",method = RequestMethod.PUT)
    @ApiOperation(value="修改购物车数据", notes="修改购物车数据")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "成功")})
    public ResponseEntity<List<Cart>> updateCart(@RequestBody Cart cart){

        cservice.updateByPrimaryKey(cart);
        return queryCart(cart.getUsername());
    }
    @RequestMapping(value = "/checkout/",method = RequestMethod.POST)
    @ApiOperation(value="修改订单数据", notes="修改订单数据")
    @ApiImplicitParam(name = "orders", value = "订单数据",
            required = true, dataType = "Orders")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "成功")})
    public ResponseEntity<Void> checkOut(@RequestBody Orders orders) {
        cservice.checkOut(orders);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
    @RequestMapping(value = "/query/{username}/",method = RequestMethod.GET)
    @ApiOperation(value="查询购物车数据", notes="查询购物车数据")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "成功")})
    public ResponseEntity<List<Cart>> queryCart(@PathVariable  String username){

        List<Cart> clist=cservice.queryCart(username);
        System.out.println("ok");

        return new ResponseEntity<List<Cart>>(clist,HttpStatus.OK);
    }

    @RequestMapping(value = "/add/",method = RequestMethod.POST)
    @ApiOperation(value="增加购物车数据", notes="增加购物车数据")
    @ApiImplicitParam(name = "cart", value = "购物车对象",
            required = true, dataType = "Cart")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "成功")})
    public ResponseEntity<Void> addCart(@RequestBody Cart cart){
        Map map = new HashMap();
        map.put("in_itemid",cart.getItemid());
        map.put("in_username",cart.getUsername());
        map.put("in_quantity",cart.getQuantity());
        map.put("in_productid",cart.getProductid());
        map.put("out_oid","");
        cservice.addCart(map);//对mysql进行存储
        //对redis进行存储
        String oid=map.get("out_oid").toString();
        System.out.println(oid);
        queryCart(cart.getUsername());
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
