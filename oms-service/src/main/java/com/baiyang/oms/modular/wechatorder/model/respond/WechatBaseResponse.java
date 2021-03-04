package com.baiyang.oms.modular.wechatorder.model.respond;

import lombok.Data;

/**
 * 说明：wechat通用返回实体
 *
 * @author:wangjunpeng
 * @Date:2018/12/19
 */
@Data
public class WechatBaseResponse {

    /**
     * 返回状态码
     */
    private String return_code;
    /**
     * 返回信息
     */
    private String return_msg;
    /**
     * 以下字段在return_code为SUCCESS的时候有返回
     * 签名类型
     */
    private String sign_type;
    /**
     * 签名
     */
    private String sign;
    /**
     * 公众账号ID
     * 微信分配的公众账号ID
     */
    private String appid;
    /**
     * 商户号
     * 微信支付分配的商户号
     */
    private String mch_id;
    /**
     * 业务结果状态
     * SUCCESS/FAIL
     */
    private String result_code;
    /**
     * 错误代码
     */
    private String err_code;
    /**
     * 错误代码描述
     */
    private String err_code_des;
}
