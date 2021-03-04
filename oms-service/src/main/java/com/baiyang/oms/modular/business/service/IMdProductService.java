package com.baiyang.oms.modular.business.service;

import com.baiyang.oms.modular.business.model.MdProduct;
import com.baiyang.oms.modular.business.model.pojo.ProductPojo;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 产品信息表 服务类
 * </p>
 *
 * @author zhangjilong123
 * @since 2018-07-12
 */
public interface IMdProductService extends IService<MdProduct> {

	int getCountByProductCode(String productCode);

	List<Map<String, Object>> selectListPage(Page<ProductPojo> page, MdProduct mdProduct,String keyword);
	
	List<MdProduct> getProductsByCode(String code);
	
	List<MdProduct> getProductsByCodes(MdProduct mdProduct);

	int addSku(MdProduct mdProduct) ;
	
	String syncProToHouse(String productCode,String storeCode);
	
	

}
