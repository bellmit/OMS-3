package com.baiyang.oms.modular.business.dao;

import com.baiyang.oms.modular.business.model.MdWarehouse;
import com.baiyang.oms.modular.system.warpper.DataRuleWrapper;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 仓库表 Mapper 接口
 * </p>
 *
 * @author stylefeng123
 * @since 2018-07-06
 */
@Repository
public interface MdWarehouseMapper extends BaseMapper<MdWarehouse> {
    //	Integer insert(BWarehouse bWarehouse);
    List<Map<String, Object>> selectHouseList(MdWarehouse bwh);

    Boolean updateStatusById(Map<String, Object> map);

    public List<DataRuleWrapper> selectMdWarehouse();

    Long selectIdByCode(String code);

    Map<String, Object> getWarehouseMapByCode(String code);

    String getNameById(Integer houseId);

    String getHouseCodeById(Long id);

    List<Map<String, Object>> getHouseIdAndName();

    String getWareHouseNameByCode(@Param("storeCode") String storeCode);

    List<MdWarehouse> selectWarehouseByTenantId(int tendId);

    /**
     * 分页查询
     *
     * @param page
     * @param bwh
     * @param keyword
     * @return
     */
    List<Map<String, Object>> pageGrade(@Param("page") Page<MdWarehouse> page, @Param("bwh") MdWarehouse bwh, @Param("keyword") String keyword);
}
