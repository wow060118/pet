package com.weikun.model;

import java.io.Serializable;

public class Cart extends CartKey implements Serializable {
    private Integer quantity;

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}