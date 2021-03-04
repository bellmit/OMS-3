package com.baiyang.oms.modular.ningpocang.model.request;

import lombok.Data;

/**
 * 说明：保险
 *
 * @author:wangjunpeng
 * @Date:2018/12/24
 */
@Data
public class Insurance {

    /**
     * 保险类型
     */
    private String type;
    /**
     * 保险金额
     */
    private String amount;
}
