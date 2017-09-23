package com.weikun.controller;

import com.weikun.model.Account;
import com.weikun.model.Category;
import com.weikun.service.PetService;
import com.weikun.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 创建者：weikun【YST】   日期：2017/9/22
 * 说说功能：
 */
@RestController
@RequestMapping("/user")
@CrossOrigin
@Api(value = "userController", description = "用户管理控制器")
public class UserController {
    @Autowired
    private PetService pservice;

    @Autowired
    private UserService uservice;


    @RequestMapping(value = "/reg/",method = RequestMethod.POST)
    @ApiOperation(value="用户注册", notes="用户注册API")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 404, message = "注册失败！")})
    public ResponseEntity<Void> reg(@RequestBody Account account) {//@RequestBody Account account

        if(uservice.insert(account)>0){
            //把当前用户存储到redis下，模拟session
            return new ResponseEntity<Void>(HttpStatus.OK);
        }
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);//204
    }
    @RequestMapping(value = "/login/",method = RequestMethod.POST)
    @ApiOperation(value="用户登录", notes="用户登录API")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 404, message = "登录失败！")})
    public ResponseEntity<Void> login(@RequestBody Account account){//@RequestBody Account account

        if(uservice.login(account)){
            //把当前用户存储到redis下，模拟session
            uservice.saveSession(account.getUsername());
            return new ResponseEntity<Void>(HttpStatus.OK);
        }
        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }
    @RequestMapping(value = "/category/",method = RequestMethod.GET)
    @ApiOperation(value="取登录宠物种类", notes="取登录宠物种类")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "成功")})
    public ResponseEntity<List<Category>> selectCategoryAll(){
        List<Category> list=pservice.selectCategoryAll();
        return new ResponseEntity<List<Category>>(list,HttpStatus.OK);
    }
    @RequestMapping(value = "/get/",method = RequestMethod.GET)
    @ApiOperation(value="取登录成功后的用户名", notes="取登录成功后的用户名API")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 404, message = "没有该用户，非法入侵！")})
    public ResponseEntity<Object> getSessionUsername() {
        Object username=uservice.getSession("sessionusername");
        if(username!=null){
            username=username.toString();
            return new ResponseEntity<Object>(username,HttpStatus.OK);
        }
        return new ResponseEntity<Object>(null,HttpStatus.NOT_FOUND);
    }

}
