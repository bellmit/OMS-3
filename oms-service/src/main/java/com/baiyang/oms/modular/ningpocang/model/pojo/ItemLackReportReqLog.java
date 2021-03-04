package com.baiyang.oms.modular.ningpocang.model.pojo;

import com.baiyang.oms.modular.ningpocang.model.request.ItemLackReport;
import lombok.Data;

/**
 * 说明：发货单缺货通知接口
 *
 * @author qinghaipeng
 * Date:2018/12/25
 */
@Data
public class ItemLackReportReqLog {

    private ItemLackReport pojo;

    private Integer logId;
}
