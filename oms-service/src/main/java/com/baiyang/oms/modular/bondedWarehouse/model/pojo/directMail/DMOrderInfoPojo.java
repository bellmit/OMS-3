package com.baiyang.oms.modular.bondedWarehouse.model.pojo.directMail;

import com.baiyang.oms.modular.bondedWarehouse.model.dto.directMail.DMOrderGoodsDetail;
import com.baiyang.oms.modular.bondedWarehouse.model.dto.directMail.DMOrderHead;
import com.baiyang.oms.modular.bondedWarehouse.model.dto.inBond.BaseTransfer;
import lombok.Data;

import java.util.List;

/**
 * 说明：直邮订单
 *
 * @author:wangjunpeng
 * @Date:2019/1/10
 */
@Data
public class DMOrderInfoPojo {

    /**
     * 订单表头信息
     */
    private DMOrderHead orderHead;

    /**
     * 订单商品表体信息
     */
    private List<DMOrderGoodsDetail> orderList;
    /**
     * 传输信息
     */
    private BaseTransfer baseTransfer;
}
