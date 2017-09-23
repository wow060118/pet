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
 * �����ߣ�weikun��YST��   ���ڣ�2017/9/22
 * ˵˵���ܣ�
 */
@RestController
@RequestMapping("/user")
@CrossOrigin
@Api(value = "userController", description = "�û����������")
public class UserController {
    @Autowired
    private PetService pservice;

    @Autowired
    private UserService uservice;


    @RequestMapping(value = "/reg/",method = RequestMethod.POST)
    @ApiOperation(value="�û�ע��", notes="�û�ע��API")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "�ɹ�"),
            @ApiResponse(code = 404, message = "ע��ʧ�ܣ�")})
    public ResponseEntity<Void> reg(@RequestBody Account account) {//@RequestBody Account account

        if(uservice.insert(account)>0){
            //�ѵ�ǰ�û��洢��redis�£�ģ��session
            return new ResponseEntity<Void>(HttpStatus.OK);
        }
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);//204
    }
    @RequestMapping(value = "/login/",method = RequestMethod.POST)
    @ApiOperation(value="�û���¼", notes="�û���¼API")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "�ɹ�"),
            @ApiResponse(code = 404, message = "��¼ʧ�ܣ�")})
    public ResponseEntity<Void> login(@RequestBody Account account){//@RequestBody Account account

        if(uservice.login(account)){
            //�ѵ�ǰ�û��洢��redis�£�ģ��session
            uservice.saveSession(account.getUsername());
            return new ResponseEntity<Void>(HttpStatus.OK);
        }
        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }
    @RequestMapping(value = "/category/",method = RequestMethod.GET)
    @ApiOperation(value="ȡ��¼��������", notes="ȡ��¼��������")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "�ɹ�")})
    public ResponseEntity<List<Category>> selectCategoryAll(){
        List<Category> list=pservice.selectCategoryAll();
        return new ResponseEntity<List<Category>>(list,HttpStatus.OK);
    }
    @RequestMapping(value = "/get/",method = RequestMethod.GET)
    @ApiOperation(value="ȡ��¼�ɹ�����û���", notes="ȡ��¼�ɹ�����û���API")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "�ɹ�"),
            @ApiResponse(code = 404, message = "û�и��û����Ƿ����֣�")})
    public ResponseEntity<Object> getSessionUsername() {
        Object username=uservice.getSession("sessionusername");
        if(username!=null){
            username=username.toString();
            return new ResponseEntity<Object>(username,HttpStatus.OK);
        }
        return new ResponseEntity<Object>(null,HttpStatus.NOT_FOUND);
    }

}
