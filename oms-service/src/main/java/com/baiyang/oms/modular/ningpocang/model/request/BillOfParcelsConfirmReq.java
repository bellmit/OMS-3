package com.baiyang.oms.modular.ningpocang.model.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

import java.util.List;

/**
 * 说明：发货单确认接口实体
 *
 * @author:wangjunpeng
 * @Date:2018/12/25
 */
@Data
@XStreamAlias("request")
public class BillOfParcelsConfirmReq {

    private DeliverGoodsConfirm deliveryOrder;

    private List<PackageInfoConfirm> packages;

    private List<BillOfParcelsConfirmOrderLine> orderLines;

    private List<CustomsDeclarationInfo> customsDeclarationinfos;
}
