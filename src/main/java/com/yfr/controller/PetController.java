package com.yfr.controller;

import com.yfr.mapper.MyQuery;
import com.yfr.service.PetService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * �����ߣ�yfr��YST��   ���ڣ�2017/9/23
 * ˵˵���ܣ�
 */
@RestController
@RequestMapping("/pet")
@CrossOrigin
@Api(value = "petController", description = "������������")
public class PetController {
    @Autowired
    private PetService pservice;
    @RequestMapping(value = "/query/",method = RequestMethod.POST)
    @ApiOperation(value="ȡ���ֳ�������", notes="ȡ���ֳ�������")
    @ApiImplicitParam(name = "query", value = "��ѯ�Ķ���",
            required = true, dataType = "MyQuery")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "�ɹ�")})
    public ResponseEntity<List> queryPet(@RequestBody MyQuery query){
        List list=null;
        if (!query.getCategory().equals("")){//ͨ�������ѯ��Ʒ
            list=pservice.queryPetProduct(query.getCategory());
        }else if (!query.getProduct().equals("")){
            list=pservice.queryPetProduct(query.getProduct());
        }else if (!query.getItem().equals("")){
            list=pservice.queryPetProduct(query.getItem());
        }

        return new ResponseEntity<List>(list, HttpStatus.OK);
    }
}
