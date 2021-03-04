package com.baiyang.oms.modular.ningpocang.model.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

import java.util.List;

/**
 * 说明：入库单确定接口实体
 *
 * @author:wangjunpeng
 * @Date:2018/12/22
 */
@Data
@XStreamAlias("request")
public class GodownEntryConfirmReq {

    private EntryOrderConfirm entryOrder;

    private List<OrderLineConfirm> orderLines;
}
