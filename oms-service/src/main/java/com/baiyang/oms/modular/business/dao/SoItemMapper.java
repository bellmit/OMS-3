package com.baiyang.oms.modular.business.dao;

import java.util.List;
import java.util.Map;

import com.baiyang.oms.modular.business.model.SoItem;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用户订单明细表 Mapper 接口
 * </p>
 *
 * @author zhangjilong123
 * @since 2018-07-12
 */
public interface SoItemMapper extends BaseMapper<SoItem> {

    List<SoItem> getSoItemBySoId(Long soId);
    
    List<SoItem> getSoItemBySoIdAndGift(@Param("soId") Long soId,@Param("gift") Integer gift);

    void updateInsteaPriceByParm(SoItem si);

    List<Map<String, Object>> getSoItemByList(@Param("page") Page<SoItem> page, @Param("soId") Long soId);
}
