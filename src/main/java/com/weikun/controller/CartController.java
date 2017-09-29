package com.weikun.controller;

import com.weikun.model.Cart;
import com.weikun.service.CartService;
import com.weikun.service.PetService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        map.put("out_oid","");
        //cservice.addCart(map);
        String oid=map.get("out_oid").toString();
        System.out.println(oid);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
