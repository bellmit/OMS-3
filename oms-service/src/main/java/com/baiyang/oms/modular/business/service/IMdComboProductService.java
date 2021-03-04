package com.baiyang.oms.modular.business.service;

import com.baiyang.oms.modular.business.model.MdComboProduct;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;


/**
 * <p>
 * 产品信息表 服务类
 * </p>
 *
 * @author zhangjilong123
 * @since 2018-07-12
 */
public interface IMdComboProductService extends IService<MdComboProduct> {

    void getMdComboProducts();

    String getMdProductsCode(Integer id);

    void deleteAllByMainProductId(Long mainProductId);

    List<MdComboProduct> getMdComboProductsByMainProductId(Long mainProductId);
}
