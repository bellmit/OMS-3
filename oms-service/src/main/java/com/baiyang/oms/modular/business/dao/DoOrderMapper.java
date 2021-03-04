package com.baiyang.oms.modular.business.dao;

import com.baiyang.oms.modular.business.model.DoOrder;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 * 配送单信息 Mapper 接口
 * </p>
 *
 * @author will123
 * @since 2018-08-01
 */
public interface DoOrderMapper extends BaseMapper<DoOrder> {
	String getSoNoById(Long doId);
	Long getIdBySoNo(String soNo);

}
