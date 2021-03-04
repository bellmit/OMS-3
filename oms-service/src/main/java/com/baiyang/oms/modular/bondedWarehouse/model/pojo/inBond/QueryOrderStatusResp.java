package com.baiyang.oms.modular.bondedWarehouse.model.pojo.inBond;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author qinghaipeng
 */
@Data
@NoArgsConstructor
public class QueryOrderStatusResp {

    /**
     * 电商企业要查询的订单编号
     * 失败返回：   "orderNo": "99：没有查到相关数据"
     */
    private String orderNo;


    /**
     * 商检状态代码
     * 商检状态对应表
     * 代码	名称
     * 0	待处理
     * 1	待审核
     * 2	审单异常
     * 3	抽中
     * 4	待查验
     * 5	查验完成
     * 6	放行
     * 7	不予放行
     * 8	审单不合格
     * 9	已撤单
     * 10	已退回
     * 11	已调回
     * 16	异常
     * 88   待申报
     * F	失败
     */
    private String ciqStatus;

    /**
     * 海关状态代码
     * 海关状态对应表
     * 代码	名称
     * 1	电子口岸已暂存
     * 2	电子口岸申报中
     * 3	发送海关成功
     * 4	发送海关失败
     * 100	海关退单
     * 120	海关入库
     * 300	人工审核
     * 399	海关审结
     * 800	发行
     * 899	结关
     * 500	查验
     * 501	扣留移送通关
     * 502	扣留移送缉私
     * 503	扣留移送法规
     * 599	其他扣留
     * 700	退运
     * 小于0数字	处理异常
     */
    private String custStatus;
}
