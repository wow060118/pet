package com.yfr.mapper;

import java.io.Serializable;

/**
 * 创建者：yfr【YST】   日期：2017/9/24
 * 说说功能：
 */
public class MyQuery implements Serializable {
    private String category;
    private String product;
    private String item;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }
}
