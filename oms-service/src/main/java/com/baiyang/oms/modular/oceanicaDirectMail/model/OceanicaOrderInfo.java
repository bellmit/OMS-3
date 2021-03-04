package com.baiyang.oms.modular.oceanicaDirectMail.model;

import lombok.Data;

import java.util.List;

/**
 * 说明：訂單信息
 *
 * @author:wangjunpeng
 * @Date:2019/1/23
 */
@Data
public class OceanicaOrderInfo {

    List<OrderInfoItem> items;
    /**
     * 订单号
     */
    private String orderNo;
    /**
     * 购买人姓名
     */
    private String name;
    /**
     * 收件人手机号
     */
    private String phone;
    /**
     * 购买人身份证号
     */
    private String idcard;
    /**
     * 省
     */
    private String province;
    /**
     * 市
     */
    private String city;
    /**
     * 区
     */
    private String county;
    /**
     * 区域
     */
    private String area;
    /**
     * 收货人地址
     */
    private String address;
    /**
     * 身份证信息
     */
    private CardBase64image card_base64image;

}
