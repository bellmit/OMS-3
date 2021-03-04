package com.baiyang.oms.modular.business.model.pojo;

/**
 * Created by Administrator on 2018/8/20.
 */
public class TempSoPlatformOrderPojo {
    private String platformOrderCode;//平台订单

    public TempSoPlatformOrderPojo() {
    }

    public TempSoPlatformOrderPojo(String platformOrderCode) {
        this.platformOrderCode = platformOrderCode;
    }

    public String getPlatformOrderCode() {
        return platformOrderCode;
    }

    public void setPlatformOrderCode(String platformOrderCode) {
        this.platformOrderCode = platformOrderCode;
    }
}
