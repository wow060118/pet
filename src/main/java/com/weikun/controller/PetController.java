package com.weikun.controller;

import com.weikun.mapper.MyQuery;
import com.weikun.model.Category;
import com.weikun.model.Item;
import com.weikun.model.Product;
import com.weikun.service.PetService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 创建者：weikun【YST】   日期：2017/9/23
 * 说说功能：
 */
@RestController
@RequestMapping("/pet")
@CrossOrigin
@Api(value = "petController", description = "宠物管理控制器")
public class PetController {
    @Autowired
    private PetService pservice;
    @RequestMapping(value = "/query/",method = RequestMethod.POST)
    @ApiOperation(value="取各种宠物数据", notes="取各种宠物数据")
    @ApiImplicitParam(name = "query", value = "查询的对象",
            required = true, dataType = "MyQuery")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "成功")})
    public ResponseEntity<List> queryPet(@RequestBody MyQuery query){
        List list=null;
        if (!query.getCategory().equals("")){//通过种类查询产品
            list=pservice.queryPetProduct(query.getCategory());
        }else if (!query.getProduct().equals("")){
            list=pservice.queryPetProduct(query.getProduct());
        }else if (!query.getItem().equals("")){
            list=pservice.queryPetProduct(query.getItem());
        }

        return new ResponseEntity<List>(list, HttpStatus.OK);
    }
}
