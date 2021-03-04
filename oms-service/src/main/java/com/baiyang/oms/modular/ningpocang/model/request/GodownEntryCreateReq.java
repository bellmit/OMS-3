package com.baiyang.oms.modular.ningpocang.model.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

import java.util.List;

/**
 * 说明：入库单实体
 *
 * @author:wangjunpeng
 * @Date:2018/12/21
 */
@Data
@XStreamAlias("request")
public class GodownEntryCreateReq {

    private EntryOrderCreate entryOrder;

    private List<OrderLineCreate> orderLines;

}
