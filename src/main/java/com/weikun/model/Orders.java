package com.weikun.model;

import java.math.BigDecimal;
import java.util.Date;

public class Orders extends OrdersKey {
    private Date orderdate;

    private BigDecimal totalprice;

    public Date getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(Date orderdate) {
        this.orderdate = orderdate;
    }

    public BigDecimal getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(BigDecimal totalprice) {
        this.totalprice = totalprice;
    }
}