package com.baiyang.oms.modular.business.model.pojo;

import java.io.Serializable;

/**
 * 优惠券信息
 * Created by Administrator on 2018/11/23.
 */
public class VoucherInfoPojo implements Serializable {
    private String voucher_code;
    private String voucher_title;
    private Double voucher_price;
    private Double order_voucher_price;
    private String join_type;

    public String getVoucher_code() {
        return voucher_code;
    }

    public void setVoucher_code(String voucher_code) {
        this.voucher_code = voucher_code;
    }

    public String getVoucher_title() {
        return voucher_title;
    }

    public void setVoucher_title(String voucher_title) {
        this.voucher_title = voucher_title;
    }

    public Double getVoucher_price() {
        return voucher_price;
    }

    public void setVoucher_price(Double voucher_price) {
        this.voucher_price = voucher_price;
    }

    public Double getOrder_voucher_price() {
        return order_voucher_price;
    }

    public void setOrder_voucher_price(Double order_voucher_price) {
        this.order_voucher_price = order_voucher_price;
    }

    public String getJoin_type() {
        return join_type;
    }

    public void setJoin_type(String join_type) {
        this.join_type = join_type;
    }
}
