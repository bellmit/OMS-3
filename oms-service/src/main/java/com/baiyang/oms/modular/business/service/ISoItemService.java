package com.baiyang.oms.modular.business.service;

import java.util.List;
import java.util.Map;

import com.baiyang.oms.modular.business.model.SoItem;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 用户订单明细表 服务类
 * </p>
 *
 * @author zhangjilong123
 * @since 2018-07-12
 */
public interface ISoItemService extends IService<SoItem> {

    List<SoItem> selectListBySoOrderId(Long soId);
    
    List<SoItem> getSoItemBySoIdAndGift(Long soId,Integer gift);

    List<Map<String, Object>> selectListBySoOrderId(Page<SoItem> page, Long soId);

    void updateInsteaPriceByParm(SoItem si);

}
