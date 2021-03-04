package com.baiyang.oms.modular.wechatorder.model.pojo;

import com.baiyang.oms.modular.wechatorder.model.request.WechatAddOrderRepush;
import lombok.Data;

/**
 * 说明：
 *
 * @author:wangjunpeng
 * @Date:2018/12/12
 */
@Data
public class WechatAddOrderRepushLog {

    private WechatAddOrderRepush pojo;

    private Integer logId;

}
