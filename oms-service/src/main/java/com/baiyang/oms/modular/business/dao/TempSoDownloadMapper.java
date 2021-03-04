package com.baiyang.oms.modular.business.dao;

import com.baiyang.oms.modular.business.model.TempSoDownload;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 订单导出资源记录表 Mapper 接口
 * </p>
 *
 * @author qinghai.peng123
 * @since 2018-08-18
 */
@Repository
public interface TempSoDownloadMapper extends BaseMapper<TempSoDownload> {

    List<Map<String,Object>> selectBySearchCondition(@Param("page") Page<TempSoDownload> page, @Param("fileCode") String fileCode, @Param("status") Integer status,@Param("keyword") String keyword);
}
