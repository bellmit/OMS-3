package com.baiyang.oms.modular.business.service;

import com.baiyang.oms.modular.business.model.GrfHeader;
import com.baiyang.oms.modular.business.model.Shop;
import com.baiyang.oms.modular.business.model.pojo.GrfOrderPojo;
import com.baiyang.oms.modular.business.model.pojo.RefundOrderPojo;
import com.baiyang.oms.modular.electronPort.model.pojo.orderInfo.RefoundOrderPojo;
import com.baiyang.oms.modular.system.model.ResultMessage;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 退换货单 服务类
 * </p>
 *
 * @author zhangjilong123
 * @since 2018-07-13
 */
public interface IGrfHeaderService extends IService<GrfHeader> {

    public void getRefundOrderList(Shop shop, String url);

    public List<Map<String, Object>> selectRefoundListPage(Page<RefoundOrderPojo> page, RefoundOrderPojo refundOrder);

    public List<Map<String, Object>> selectGrfListPage(Page<GrfOrderPojo> page, GrfOrderPojo pojo);

    public ResultMessage cancelOrder(String originalCode);

    public ResultMessage enterOrder(String originalCode,int status);
}
