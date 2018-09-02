package com.yfr.service;

import com.yfr.mapper.CategoryMapper;
import com.yfr.model.Category;
import com.yfr.redis.dao.RedisDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 创建者：yfr【YST】   日期：2017/9/23
 * 说说功能：
 */
@Service
public class PetService {
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private RedisDAO rdao;

    public List<Category> selectCategoryAll(){
        return categoryMapper.selectCategoryAll();
    }
    //根据key 查询所有产品,和所有项目
    public List queryPetProduct(String key){

        List list=new ArrayList(rdao.keysUnion(rdao.getAllKeys(key+"*")));
        return list;
    }

    //根据key 查询单个项目
    public List queryPetItem(String key) {

        return Arrays.asList(rdao.getSetByKey(key));
    }
}
