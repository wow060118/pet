package com.weikun.controller;

import com.weikun.initdao.InitDataDAO;
import com.weikun.service.InitDataService;
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
 * 创建者：weikun【YST】   日期：2017/9/17
 * 说说功能：
 */
@RestController
@RequestMapping("/init")
@CrossOrigin
@Api(value = "initController", description = "初始化数据API")
public class InitController {
    @Autowired
    private InitDataService service;

    @RequestMapping(value = "/init",method = RequestMethod.GET)
    @ApiOperation(value="初始化数据", notes="初始化数据详细描述")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "成功")})
    public ResponseEntity<Void> init(){
        service.init();
        return new ResponseEntity<Void>(HttpStatus.OK);
    }


}
