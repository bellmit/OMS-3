package com.baiyang.oms.modular.ningpocang.model.request;

import lombok.Data;

/**
 * 说明：收件人信息
 *
 * @author:wangjunpeng
 * @Date:2018/12/21
 */
@Data
public class ReceiverInfo {

    /**
     * 收件人证件类型，string (50)， 1-身份证 2-军官证 3-护照 4-其他</idType>
     */
    private String idType;
    /**
     * 收件人证件号码，string (50)</idNumber>
     */
    private String idNumber;

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
