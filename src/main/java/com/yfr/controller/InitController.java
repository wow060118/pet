package com.yfr.controller;

import com.yfr.service.InitDataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * �����ߣ�yfr��YST��   ���ڣ�2017/9/17
 * ˵˵���ܣ�
 */
@RestController
@RequestMapping("/init")
@CrossOrigin
@Api(value = "initController", description = "��ʼ������API")
public class InitController {
    @Autowired
    private InitDataService service;

    @RequestMapping(value = "/init",method = RequestMethod.GET)
    @ApiOperation(value="��ʼ������", notes="��ʼ��������ϸ����")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "�ɹ�")})
    public ResponseEntity<Void> init(){
        service.init();
        return new ResponseEntity<Void>(HttpStatus.OK);
    }


}
