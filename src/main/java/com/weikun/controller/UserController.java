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
 * �����ߣ�weikun��YST��   ���ڣ�2017/9/22
 * ˵˵���ܣ�
 */
@RestController
@RequestMapping("/user")
@CrossOrigin
@Api(value = "userController", description = "�û����������")
public class UserController {
    @Autowired
    private UserService service;
    @RequestMapping(value = "/login/{username}/{password}/",method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="�û���¼", notes="�û���¼API")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "�ɹ�"),
            @ApiResponse(code = 404, message = "��¼ʧ�ܣ�")})
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
