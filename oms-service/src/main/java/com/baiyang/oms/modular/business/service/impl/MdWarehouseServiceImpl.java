package com.baiyang.oms.modular.business.service.impl;

import com.baiyang.oms.modular.business.dao.MdWarehouseMapper;
import com.baiyang.oms.modular.business.model.MdWarehouse;
import com.baiyang.oms.modular.business.service.IMdWarehouseService;
import com.baiyang.oms.modular.system.warpper.DataRuleWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 仓库表 服务实现类
 * </p>
 *
 * @author stylefeng123
 * @since 2018-07-06
 */
@Service("bWarehouseService")
public class MdWarehouseServiceImpl extends ServiceImpl<MdWarehouseMapper, MdWarehouse> implements IMdWarehouseService {

    @Autowired
    MdWarehouseMapper mdWarehouseMapper;

    @Override
    public List<Map<String, Object>> selectHouseList(MdWarehouse bwh) {
        // TODO Auto-generated method stub
        return this.baseMapper.selectHouseList(bwh);
    }

    @Override
    public Boolean updateStatusById(Map<String, Object> map) {
        // TODO Auto-generated method stub
        return this.baseMapper.updateStatusById(map);
    }

    @Override
    public List<DataRuleWrapper> selectMdWarehouse() {

        return this.baseMapper.selectMdWarehouse();
    }

    @Override
    public Long selectIdByCode(String code) {
        // TODO Auto-generated method stub
        return this.baseMapper.selectIdByCode(code);
    }

    @Override
    public List<Map<String, Object>> getHouseIdAndName() {
        // TODO Auto-generated method stub
        return this.baseMapper.getHouseIdAndName();
    }

    @Override
    public List<MdWarehouse> selectWarehouseByTenantId(int tendId) {
        return mdWarehouseMapper.selectWarehouseByTenantId(tendId);
    }

    /**
     * 分页查询
     *
     * @param page
     * @param bwh
     * @param keyword
     * @return
     */
    @Override
    public List<Map<String, Object>> pageGrade(Page<MdWarehouse> page, MdWarehouse bwh, String keyword) {
        return mdWarehouseMapper.pageGrade(page, bwh, keyword);
    }

}
