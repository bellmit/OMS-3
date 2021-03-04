package com.baiyang.oms.modular.business.service;

import com.baiyang.oms.modular.business.model.MdWarehouse;
import com.baiyang.oms.modular.system.warpper.DataRuleWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 仓库表 服务类
 * </p>
 *
 * @author stylefeng123
 * @since 2018-07-06
 */
public interface IMdWarehouseService extends IService<MdWarehouse> {

    List<Map<String, Object>> selectHouseList(MdWarehouse bwh);

    Boolean updateStatusById(Map<String, Object> map);

    List<DataRuleWrapper> selectMdWarehouse();

    Long selectIdByCode(String code);

    List<Map<String, Object>> getHouseIdAndName();

    public List<MdWarehouse> selectWarehouseByTenantId(int tendId);

    /**
     * 分页查询
     *
     * @param page
     * @param bwh
     * @param keyword
     * @return
     */
    List<Map<String, Object>> pageGrade(Page<MdWarehouse> page, MdWarehouse bwh, String keyword);

}
