package com.weikun.controller;

import com.weikun.model.Account;
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
    private UserService service;
    @RequestMapping(value = "/login/{username}/{password}/",method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="用户登录", notes="用户登录API")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 404, message = "登录失败！")})
    public ResponseEntity<Void> login(@PathVariable String username,@PathVariable String password){//@RequestBody Account account
        Account account=new Account();
        account.setUsername(username);
        account.setPassword(password);
        if(service.login(account)){
            return new ResponseEntity<Void>(HttpStatus.OK);
        }
        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }

}
