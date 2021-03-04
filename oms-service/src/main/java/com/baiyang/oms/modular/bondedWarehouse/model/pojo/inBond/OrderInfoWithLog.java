package com.baiyang.oms.modular.bondedWarehouse.model.pojo.inBond;


public class OrderInfoWithLog {


    private OrderInfoPojo pojo;

    private Integer logId;

    public OrderInfoPojo getPojo() {
        return pojo;
    }

    public void setPojo(OrderInfoPojo pojo) {
        this.pojo = pojo;
    }

    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }
}
