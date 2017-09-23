package com.weikun.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 创建者：weikun【YST】   日期：2017/9/23
 * 说说功能：
 */
@RestController
@RequestMapping("/pet")
@CrossOrigin
@Api(value = "petController", description = "宠物管理控制器")
public class PetController {
}
