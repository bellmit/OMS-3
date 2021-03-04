package com.baiyang.oms.modular.ningpocang.model.base;

import com.baiyang.oms.modular.business.util.ReadProperties;
import com.baiyang.oms.modular.esinotrans.util.DateUtil;
import lombok.Data;

import java.util.Date;

/**
 * 说明：宁波仓通用数据发送实体
 *
 * @author:wangjunpeng
 * @Date:2018/12/20
 */
@Data
public class BaseRequest {

    public BaseRequest(String method) {
        this.method = method;
        this.format = "xml";
        this.app_key = ReadProperties.getInstance().getValue("ningpo_app_key");
        this.v = ReadProperties.getInstance().getValue("ningpo_v");
        this.customerId = ReadProperties.getInstance().getValue("ningpo_customerid");
        this.timestamp = DateUtil.getDateStrByFormat(new Date(), null);

    }

    public BaseRequest() {

    }

    /**
     * 1.仓储服务开放平台发给WMS的请求，如入库单创建接口的请求，method= entryorder.create
     * 2.WMS发给仓储服务开放平台的请求，如入库单确认接口的请求，method= yunji.esb.entryorder.confirm
     */
    private String method;
    /**
     * content格式目前支持XML
     */
    private String format;
    /**
     * 应用接入时申请的appkey
     * WMS的appkey
     */
    private String app_key;
    /**
     * 协议版本号，1.0或者2.0
     */
    private String v;
    /**
     * 根据url和密钥计算的结果。具体算法参看附录1
     */
    private String sign;
    /**
     * 参数加密方法，md5
     */
    private String sign_method = "md5";
    /**
     * WMS颁发给用户的ID
     */
    private String customerId;

    /**
     * 时间戳
     */
    private String timestamp;

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format.replaceAll("\r|\n", "");
    }
}
