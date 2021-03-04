package com.baiyang.oms.modular.bondedWarehouse.model.pojo.directMail;

import lombok.Data;

/**
 * 说明：
 *
 * @author:wangjunpeng
 * @Date:2019/1/10
 */
@Data
public class DMOrderInfoLog {
    /**
     * 订单信息
     */
    private DMOrderInfoPojo pojo;

    private Integer logId;
}
