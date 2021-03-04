package com.baiyang.oms.modular.business.service.impl;

import com.baiyang.oms.modular.business.dao.MdComboProductMapper;
import com.baiyang.oms.modular.business.dao.MdProductMapper;
import com.baiyang.oms.modular.business.model.MdComboProduct;
import com.baiyang.oms.modular.business.service.IMdComboProductService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * @author qinghaipeng
 */
@Service("mdComboProductService")
public class MdComboProductServiceImpl extends ServiceImpl<MdComboProductMapper, MdComboProduct> implements IMdComboProductService {
    @Autowired
    MdProductMapper mdProductMapper;
    @Autowired
    MdComboProductMapper mdComboProductMapper;

    @Override
    public void getMdComboProducts() {
        List<MdComboProduct> list = this.baseMapper.getMdComboProducts();
        for (MdComboProduct mco : list) {

            Map<String, Object> map = mdProductMapper.getMdProductByCodeAndMerchantId
                    (this.baseMapper.getMdProductsCode(mco.getSingleProductId().intValue()), 6314l);
            mco.setSingleProductId(Long.parseLong(map.get("id") + ""));
            mco.setCreatedBy("sys");
            mco.setCreateTime(new Date());
            this.baseMapper.insert(mco);
        }
    }

    @Override
    public String getMdProductsCode(Integer id) {
        return this.baseMapper.getMdProductsCode(id);
    }

    @Override
    public void deleteAllByMainProductId(Long mainProductId) {
        this.mdComboProductMapper.deleteAllByMainProductId(mainProductId);
    }

    @Override
    public List<MdComboProduct> getMdComboProductsByMainProductId(Long mainProductId) {
        return this.mdComboProductMapper.getMdComboProductsByMainProductId(mainProductId);
    }


}
