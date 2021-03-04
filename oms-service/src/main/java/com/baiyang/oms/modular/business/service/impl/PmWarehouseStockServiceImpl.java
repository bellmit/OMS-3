package com.baiyang.oms.modular.business.service.impl;

import com.baiyang.oms.modular.business.dao.MdProductMapper;
import com.baiyang.oms.modular.business.dao.MdSkuMapper;
import com.baiyang.oms.modular.business.dao.PmWarehouseStockMapper;
import com.baiyang.oms.modular.business.model.PmWarehouseStock;
import com.baiyang.oms.modular.business.service.IPmWarehouseStockService;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 仓库库存表 服务实现类
 * </p>
 *
 * @author stylefeng123
 * @since 2018-07-05
 */
@Service("pmWarehouseStockService")
public class PmWarehouseStockServiceImpl extends ServiceImpl<PmWarehouseStockMapper, PmWarehouseStock> implements IPmWarehouseStockService {

//	@Autowired
//	private MdProductMapper mdProductMapper;
	@Autowired
	private MdSkuMapper mdSkuMapper;
	@Override
	public PmWarehouseStock getHouseStockByProductCodeAndHouseId(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return this.baseMapper.getHouseStockByProductCodeAndHouseId(map);
	}

	@Override
	public List<Map<String, Object>> getSearchList(Page<PmWarehouseStock> page,Integer tenantId,String productCode,
			String[] merchantIds) {
		// TODO Auto-generated method stub
		return this.baseMapper.getSearchList(page,tenantId,productCode,merchantIds);
	}

	@Override
	public void upDatePmInfo() {
		// TODO Auto-generated method stub
//		System.out.println("code ===="+this.baseMapper.getDistinctProductId().size());
		
		Map<String, Object> map = new HashMap<>();
		for(Long id:this.baseMapper.getDistinctProductId()){
			String code = mdSkuMapper.getProductCodeById(id);
			if(code == null){
				continue;
			}
			map.clear();
			map.put("productId", id);
			map.put("pmInfoId", code);
			this.baseMapper.updatePmInfoIdByProductId(map);
		}
		
		
		
	}

}
