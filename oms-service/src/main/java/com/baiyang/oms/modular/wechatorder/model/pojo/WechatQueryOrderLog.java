package com.baiyang.oms.modular.wechatorder.model.pojo;

import com.baiyang.oms.modular.wechatorder.model.request.WechatQueryOrder;
import lombok.Data;

/**
 * 说明：
 *
 * @author qinghai.peng
 * @Date 2018/12/12
 */
@Data
public class WechatQueryOrderLog {

    private WechatQueryOrder pojo;

    private Integer logId;
}
