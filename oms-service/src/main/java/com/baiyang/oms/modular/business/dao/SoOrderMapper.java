package com.baiyang.oms.modular.business.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baiyang.oms.modular.business.model.SoOrder;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
 * 用户订单处理表 Mapper 接口
 * </p>
 *
 * @author zhangjilong123
 * @since 2018-07-12
 */
public interface SoOrderMapper extends BaseMapper<SoOrder> {
	List<Map<String, Object>> selectSoOrderList(Map<String, Object> map);
	Integer pageCount(Map<String, Object> map);
//	List<Map<String, Object>> selectSoOrderList(Page<SoOrder> page,@Param("originalCode") String originalCode,
//			@Param("virtualType") Integer virtualType,@Param("orderStatus") Integer orderStatus);
	
	void updateSoById(SoOrder so);
	
	Integer updateSoByoriginalCode(SoOrder so);
	
	Map<String,Object> getGoodReceiverById(@Param("soId") Integer soId);
	
	List<SoOrder> getSoOrdersByMap(Map<String, Object> parameterMap);
	
	List<String> getSoOrderCodeByOriginalCode(@Param("originalCode") String originalCode);

	SoOrder getSoOrdersByOrderCode(String originalCode);

	Integer updatehangupByCode(SoOrder so);
	
	Map<String,Object> getSoOrderMapById(@Param("soId") Integer soId,@Param("tenantId") Integer tenantId);

	List<SoOrder> getSoOrderListByExportCondition(Map<String, Object> map);

    List<Map<String,Object>> getVirtualOrderExportList(Map<String, Object> map);
}
