package com.baiyang.oms.modular.business.service;

import java.util.List;
import java.util.Map;

import com.baiyang.oms.modular.business.model.TempSo;
import com.baiyang.oms.modular.business.model.TempSoItem;
import com.baiyang.oms.modular.business.model.pojo.SalesAccountPojo;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 用户订单明细表 服务类
 * </p>
 *
 * @author will123
 * @since 2018-07-13
 */
public interface ITempSoItemService extends IService<TempSoItem> {
	
	List<TempSoItem> getTemSoItemListByOrderCode(String orderCode);
	List<TempSoItem> getTemSoItemListByOrderCode(Page<TempSoItem> page,String orderCode);

    int saveTempSoAndTempSoItem(List<TempSo> tempSoList, List<TempSoItem> tempSoItemList);

    List<Map<String,Object>> selectTemSoItemListByOrderCode(String orderCode, Integer tenantId);

    int updateTempSoItemAndTempSo(String id, String localCode, String abnormalGoodsPlatformOrderCode);

    int updateTempSoItemOfficeNameAndisDt(List<TempSoItem> tempSoItemSaveList);

    int updateTempSoAndTempSoItem(List<TempSo> tempSoRepeatList, List<TempSoItem> tempSoItemRepeatList);
}
