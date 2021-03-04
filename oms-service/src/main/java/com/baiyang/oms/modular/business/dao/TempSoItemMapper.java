package com.baiyang.oms.modular.business.dao;

import java.util.List;
import java.util.Map;

import com.baiyang.oms.modular.business.model.TempSoItem;
import com.baiyang.oms.modular.business.model.pojo.SalesAccountPojo;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用户订单明细表 Mapper 接口
 * </p>
 *
 * @author will123
 * @since 2018-07-13
 */
public interface TempSoItemMapper extends BaseMapper<TempSoItem> {
	List<TempSoItem> getTemSoItemListByOrderCode(String orderCode);

    int saveTempSoItemBatch(List<TempSoItem> tempSoItemList);

    List<Map<String,Object>> selectTemSoItemListByOrderCode(@Param("orderCode") String orderCode, @Param("tenantId") Integer tenantId);

    int updateTempSoItemCodeById(@Param("id") String id, @Param("localCode") String localCode);

    int updateTempSoItemOfficeNameAndisDt(List<TempSoItem> tempSoItemSaveList);

    int updateTempSoItemBatch(List<TempSoItem> tempSoItemRepeatList);

    List<TempSoItem> getTemSoItemListByOrderCodeList(@Param("page") Page<TempSoItem> page, @Param("orderCode") String orderCode);
}
