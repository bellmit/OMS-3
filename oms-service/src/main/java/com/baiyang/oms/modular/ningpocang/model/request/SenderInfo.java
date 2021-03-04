package com.baiyang.oms.modular.ningpocang.model.request;

import lombok.Data;

/**
 * 说明：发件人信息
 *
 * @author:wangjunpeng
 * @Date:2018/12/21
 */
@Data
public class SenderInfo {

    /**
     * 公司名称
     */
    private String company;
    /**
     * 姓名
     */
    private String name;
    /**
     * 邮编
     */
    private String zipCode;
    /**
     * 固定电话
     */
    private String tel;
    /**
     * 移动电话
     */
    private String mobile;
    /**
     * 电子邮箱
     */
    private String email;
    /**
     * 国家二字码
     */
    private String countryCode;
    /**
     * 省份
     */
    private String province;
    /**
     * 城市
     */
    private String city;
    /**
     * 区域
     */
    private String area;
    /**
     * 村镇
     */
    private String town;
    /**
     * 详细地址
     */
    private String detailAddress;

    /**
     * 证件号
     */
    private String id;
}
