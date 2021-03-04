package com.baiyang.oms.modular.business.dao;

import com.baiyang.oms.modular.business.model.GrfHeader;
import com.baiyang.oms.modular.business.model.RefundOrder;
import com.baiyang.oms.modular.business.model.pojo.GrfOrderPojo;
import com.baiyang.oms.modular.electronPort.model.pojo.orderInfo.RefoundOrderPojo;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 退换货单 Mapper 接口
 * </p>
 *
 * @author zhangjilong123
 * @since 2018-07-13
 */
public interface GrfHeaderMapper extends BaseMapper<GrfHeader> {

    public GrfOrderPojo selectSoOrderInfo(@Param("originalCode")String originalCode);

    public List<Map<String, Object>> selectGrfListPage(Page<GrfOrderPojo> page, GrfOrderPojo refundOrder);

    int insertGrfOrder(GrfHeader grfHeader);


}
