package com.wuzhimin.shardingjdbcdemo.entity;

public class Order implements Comparable<Long>{
    private Long orderId;

    private String orderNo;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    @Override
    public int compareTo(Long o) {
        return this.getOrderId().compareTo(o);
    }
}