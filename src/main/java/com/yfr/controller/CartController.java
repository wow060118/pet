package com.yfr.controller;

import com.yfr.model.Cart;
import com.yfr.model.CartKey;
import com.yfr.model.Orders;
import com.yfr.service.CartService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * �����ߣ�yfr��YST��   ���ڣ�2017/9/29
 * ˵˵���ܣ�
 */
@RestController
@RequestMapping("/cart")
@CrossOrigin
@Api(value = "cartController", description = "���ﳵ���������")
public class CartController {
    @Autowired
    private CartService cservice;

    @RequestMapping(value = "/del/{oid}/{username}/{iid}/{pid}/",method = RequestMethod.DELETE)
    @ApiOperation(value="ɾ�����ﳵ����", notes="ɾ�����ﳵ����")
    @ApiImplicitParam(name = "cart", value = "���ﳵ����",
            required = true, dataType = "Cart")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "�ɹ�")})
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
    @ApiOperation(value="�޸Ĺ��ﳵ����", notes="�޸Ĺ��ﳵ����")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "�ɹ�")})
    public ResponseEntity<List<Cart>> updateCart(@RequestBody Cart cart){

        cservice.updateByPrimaryKey(cart);
        return queryCart(cart.getUsername());
    }
    @RequestMapping(value = "/checkout/",method = RequestMethod.POST)
    @ApiOperation(value="�޸Ķ�������", notes="�޸Ķ�������")
    @ApiImplicitParam(name = "orders", value = "��������",
            required = true, dataType = "Orders")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "�ɹ�")})
    public ResponseEntity<Void> checkOut(@RequestBody Orders orders) {
        cservice.checkOut(orders);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
    @RequestMapping(value = "/query/{username}/",method = RequestMethod.GET)
    @ApiOperation(value="��ѯ���ﳵ����", notes="��ѯ���ﳵ����")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "�ɹ�")})
    public ResponseEntity<List<Cart>> queryCart(@PathVariable  String username){

        List<Cart> clist=cservice.queryCart(username);
        System.out.println("ok");

        return new ResponseEntity<List<Cart>>(clist,HttpStatus.OK);
    }

    @RequestMapping(value = "/add/",method = RequestMethod.POST)
    @ApiOperation(value="���ӹ��ﳵ����", notes="���ӹ��ﳵ����")
    @ApiImplicitParam(name = "cart", value = "���ﳵ����",
            required = true, dataType = "Cart")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "�ɹ�")})
    public ResponseEntity<Void> addCart(@RequestBody Cart cart){
        Map map = new HashMap();
        map.put("in_itemid",cart.getItemid());
        map.put("in_username",cart.getUsername());
        map.put("in_quantity",cart.getQuantity());
        map.put("in_productid",cart.getProductid());
        map.put("out_oid","");
        cservice.addCart(map);//��mysql���д洢
        //��redis���д洢
        String oid=map.get("out_oid").toString();
        System.out.println(oid);
        queryCart(cart.getUsername());
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
