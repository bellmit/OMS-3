package com.baiyang.oms.modular.business.service.impl;

import com.baiyang.oms.modular.business.dao.TempSoItemMapper;
import com.baiyang.oms.modular.business.dao.TempSoMapper;
import com.baiyang.oms.modular.business.model.TempSo;
import com.baiyang.oms.modular.business.model.TempSoItem;
import com.baiyang.oms.modular.business.model.pojo.SalesAccountPojo;
import com.baiyang.oms.modular.business.service.ITempSoItemService;
import com.baiyang.oms.modular.business.util.ObjectUtils;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户订单明细表 服务实现类
 * </p>
 *
 * @author will123
 * @since 2018-07-13
 */
@Service("tempSoItemService")
public class TempSoItemServiceImpl extends ServiceImpl<TempSoItemMapper, TempSoItem> implements ITempSoItemService {

	@Autowired
	private TempSoMapper tempSoMapper;
	@Autowired
	private TempSoItemMapper tempSoItemMapper;
	@Override
	public List<TempSoItem> getTemSoItemListByOrderCode(String orderCode) {
		return baseMapper.getTemSoItemListByOrderCode(orderCode);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
	public int saveTempSoAndTempSoItem(List<TempSo> tempSoList, List<TempSoItem> tempSoItemList) {
		int count = 0;
		if(!ObjectUtils.isEmpty(tempSoList)){
			count = tempSoMapper.saveTempSoBatch(tempSoList);
		}
		if(!ObjectUtils.isEmpty(tempSoItemList)){
			int count1 = tempSoItemMapper.saveTempSoItemBatch(tempSoItemList);
		}
		return count;
	}

	@Override
	public List<Map<String, Object>> selectTemSoItemListByOrderCode(String orderCode, Integer tenantId) {
		return baseMapper.selectTemSoItemListByOrderCode(orderCode, tenantId);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
	public int updateTempSoItemAndTempSo(String id, String localCode, String abnormalGoodsPlatformOrderCode) {
		int count = tempSoItemMapper.updateTempSoItemCodeById(id, localCode);
		if(count > 0){
			String errReason = "";
			int count1 = tempSoMapper.updateTempSoStatus(abnormalGoodsPlatformOrderCode, errReason);
		}
		return count;
	}

	@Override
	public int updateTempSoItemOfficeNameAndisDt(List<TempSoItem> tempSoItemSaveList) {
		int count = tempSoItemMapper.updateTempSoItemOfficeNameAndisDt(tempSoItemSaveList);
		return count;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
	public int updateTempSoAndTempSoItem(List<TempSo> tempSoRepeatList, List<TempSoItem> tempSoItemRepeatList) {
		int count = 0;
		count = tempSoMapper.updateTempSoBatch(tempSoRepeatList);
		if(!ObjectUtils.isEmpty(tempSoItemRepeatList)){
			int count1 = tempSoItemMapper.updateTempSoItemBatch(tempSoItemRepeatList);
		}
		return count;
	}

	@Override
	public List<TempSoItem> getTemSoItemListByOrderCode(Page<TempSoItem> page, String orderCode) {
		return baseMapper.getTemSoItemListByOrderCodeList(page,orderCode);
	}
}
