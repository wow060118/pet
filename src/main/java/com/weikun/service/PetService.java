package com.weikun.service;

import com.weikun.mapper.CategoryMapper;
import com.weikun.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 创建者：weikun【YST】   日期：2017/9/23
 * 说说功能：
 */
@Service
public class PetService {
    @Autowired
    private CategoryMapper categoryMapper;

    public List<Category> selectCategoryAll(){
        return categoryMapper.selectCategoryAll();
    }
}
