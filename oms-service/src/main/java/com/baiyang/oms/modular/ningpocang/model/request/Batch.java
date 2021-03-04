package com.baiyang.oms.modular.ningpocang.model.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

/**
 * 批次
 *
 * @author:wangjunpeng
 * @Date:2018/12/22
 */
@Data
@XStreamAlias("batch")
public class Batch {

    /**
     * 批次编号
     */
    private String batchCode;
    /**
     * 生产日期
     */
    private String productDate;
    /**
     * 过期日期
     */
    private String expireDate;
    /**
     * 生产批号
     */
    private String produceCode;
    /**
     * 库存类型，string (50) , ZP=正品, CC=残次,JS=机损, XS= 箱损，默认为ZP, (收到商品总数=正品数+残品数+机损数+箱损数)
     */
    private String inventoryType;
    /**
     * 残品等级，string (50) ,AG=一级残，BG=二级残，CG=三级残 ,退货仓调拨业务使用</imperfectGrade>
     */
    private String imperfectGrade;
    /**
     * 不同接口含义不同
     * 入库单确认接口：实收数量, int，要求batchs节点下所有的实收数量之和等于orderline中的实收数量
     *
     * 出库单确认接口：实发数量, int，要求batchs节点下所有的实发数量之和等于orderline中的实发数量
     * 发货单确认接口：实发数量, int，要求batchs节点下所有的实发数量之和等于orderline中的实发数量
     */
    private String actualQty;
}
