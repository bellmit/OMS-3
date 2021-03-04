package com.baiyang.oms.modular.ningpocang.model.request;

import lombok.Data;

/**
 * @author:wangjunpeng
 * @Date:2018/12/24
 */
@Data
public class DeliveryRequirements {

    /**
     * 投递时延要求,int, 1=工作日,2=节假日,101=当日达,102=次晨达,103=次日达, 104=预约达</scheduleType>
     */
    private String scheduleType;
    /**
     * 要求送达日期, string (10) , YYYY-MM-DD</scheduleDay>
     */
    private String scheduleDay;
    /**
     * 投递时间范围要求 (开始时间) , string (8) , HH:MM:SS</scheduleStartTime>
     */
    private String scheduleStartTime;
    /**
     * 投递时间范围要求 (结束时间) , string (8) , HH:MM:SS</scheduleEndTime>
     */
    private String scheduleEndTime;
    /**
     * 发货服务类型，PTPS（普通配送），LLPS（冷链配送），HBP（环保配）,string(50)</deliveryType>
     */
    private String deliveryType;
    /**
     * 不建议发货的物流公司编码 , string (50)，
     * SF=顺丰、EMS=标准快递、EYB=经济快件、ZJS=宅急送、YTO=圆通 、ZTO=中通 (ZTO) 、
     * HTKY=百世汇通、UC=优速、STO=申通、TTKDEX=天天快递 、QFKD=全峰、FAST=快捷、POSTB=邮政小包 、
     * GTO=国通、YUNDA=韵达、JD=京东配送、DD=当当宅配、AMAZON=亚马逊物流，多个物流公司编码以“,”区分, (只传英文编码) </notRecommendDelivery>
     */
    private String notRecommendDelivery;
}
