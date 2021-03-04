package com.baiyang.oms.core.constant;

import com.baiyang.oms.core.util.ReadProperties;

/**
 * 官网接口url
 *
 * @author fengshuonan
 * @date 2017年2月12日 下午9:42:53
 */
public interface UrlConst {

    String URL = ReadProperties.getInstance().getValue("baiyang_url");

    //抓单接口
    String  CATCHORDERURL = URL+ReadProperties.getInstance().getValue("catchOrder_url");

    //物流发货接口
    String  CARRYURL = URL+ReadProperties.getInstance().getValue("carrier_no_url");

    //回传仓库发货状态到平台
    String  GUANURL = URL+ReadProperties.getInstance().getValue("wareHouse_delivery_url");


    //抓单接口
    String  REDOUND_ORDER_URL = URL+ReadProperties.getInstance().getValue("baiyang_getRefundOrderList_url");


    //中外运rest服务地址
    String  ORDERURL = ReadProperties.getInstance().getValue("orderCreateUrl");
    //中外运订单取消接口
    String  ORDECANCELRURL = ReadProperties.getInstance().getValue("orderCancelUrl");


    //excel保存路径
    String  excelPath = ReadProperties.getInstance().getValue("excelPath");

    //获取分办和地推信息接口路径
    String  officeNameAndIsDtURL = ReadProperties.getInstance().getValue("officeNameAndIsDt_url");

    //查询是否生成售后单接口
    String  refoundURL = URL + ReadProperties.getInstance().getValue("refound_url");

    //黄岛保税仓报文生成
    String  huangDaoXml = ReadProperties.getInstance().getValue("huangDao_xml");

    //推送黄岛保税仓
    String  huangDaoSend = ReadProperties.getInstance().getValue("huangDao_send");

    //保税仓状态同步
    String hdStatusSynchro = ReadProperties.getInstance().getValue("hd_query_order_status_url");

    //保税仓快递状态同步
    String hdKdSynchro = ReadProperties.getInstance().getValue("hd_query_goods_status_url");



}
