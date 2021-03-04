package com.baiyang.oms.modular.business.model.pojo;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/12/13.
 */
public class PaymentInfoPojo implements Serializable {
    private String payment_code;
    private String payment_name;

    public String getPayment_code() {
        return payment_code;
    }

    public void setPayment_code(String payment_code) {
        this.payment_code = payment_code;
    }

    public String getPayment_name() {
        return payment_name;
    }

    public void setPayment_name(String payment_name) {
        this.payment_name = payment_name;
    }
}
