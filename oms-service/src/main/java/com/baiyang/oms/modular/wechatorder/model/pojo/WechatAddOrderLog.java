package com.baiyang.oms.modular.wechatorder.model.pojo;

import com.baiyang.oms.modular.wechatorder.model.request.WechatAddOrder;
import lombok.Data;

/**
 * 说明：
 *
 * @author:wangjunpeng
 * @Date:2018/12/11
 */
@Data
public class WechatAddOrderLog {

    private WechatAddOrder pojo;

    private Integer logId;
    
    private String payOrderNo;
    
    private String orderCode;
}
