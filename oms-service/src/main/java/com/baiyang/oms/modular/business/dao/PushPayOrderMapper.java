package com.baiyang.oms.modular.business.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baiyang.oms.modular.business.model.PushPayOrder;
import com.baiyang.oms.modular.business.model.SoOrder;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
 * 用户订单处理表 Mapper 接口
 * </p>
 *
 * @author zhangjilong123
 * @since 2018-07-12
 */
public interface PushPayOrderMapper extends BaseMapper<PushPayOrder> {
	
	 PushPayOrder getPushPayOrderByOriginalCode(@Param("code") String code);
	 
	 void updateFildById(PushPayOrder ppo);
}
