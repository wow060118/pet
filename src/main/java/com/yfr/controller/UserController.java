package com.yfr.controller;

import com.yfr.model.Account;
import com.yfr.model.Category;
import com.yfr.service.PetService;
import com.yfr.service.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * �����ߣ�yfr��YST��   ���ڣ�2017/9/22
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
    @ApiImplicitParam(name = "account", value = "��¼�Ķ���",
            required = true, dataType = "Account")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "�ɹ�"),
            @ApiResponse(code = 404, message = "ע��ʧ�ܣ�")})
    public ResponseEntity<Void> reg(@RequestBody Account account) {//@RequestBody Account account

        if(uservice.insert(account)>0){
            //�ѵ�ǰ�û��洢��redis�£�ģ��session
            return new ResponseEntity<Void>(HttpStatus.OK);
        }
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);//204
    }



    @RequestMapping(value = "/logout/",method = RequestMethod.GET)
    @ApiOperation(value="�û�ע��", notes="�û�ע��API")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "�ɹ�"),
            @ApiResponse(code = 404, message = "ע��ʧ�ܣ�")})
    public ResponseEntity<Void> loginout(){//@RequestBody Account account

        uservice.removeSession();

        return new ResponseEntity<Void>(HttpStatus.OK);

    }



    @RequestMapping(value = "/login/",method = RequestMethod.POST)
    @ApiOperation(value="�û���¼", notes="�û���¼API")
    @ApiImplicitParam(name = "account", value = "��¼�Ķ���",
            required = true, dataType = "Account")
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
