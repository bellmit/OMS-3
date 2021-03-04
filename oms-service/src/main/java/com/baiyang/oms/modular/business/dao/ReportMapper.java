package com.baiyang.oms.modular.business.dao;

import com.baiyang.oms.modular.business.model.pojo.*;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 说明：报表统计mapper接口
 *
 * @author:wangjunpeng
 * @Date:2018/10/9
 */
public interface ReportMapper {

    List<Map<String, Object>> getSalesAccountList(@Param("page") Page<SalesAccountSearchPojo> page, @Param("obj") SalesAccountSearchPojo obj);

    List<SalesAccountPojo> getSalesAccountListByExportCondition(@Param("obj") SalesAccountSearchPojo obj);

    List<Map<String, Object>> getTaxStatementList(@Param("page") Page<TaxStatementPojo> page, @Param("obj") TaxStatementPojo obj);

    List<TaxStatementPojo> getTaxStatementListByExportCondition(@Param("obj") TaxStatementPojo obj);

    List<Map<String, Object>> getTaxBalanceList(@Param("page") Page<TaxBalancePojo> page, @Param("obj") TaxBalancePojo obj);

    List<TaxStatementPojo> getTaxBalanceListByExportCondition(@Param("obj") TaxBalancePojo obj);

    SalesAccountTotalCountPojo getSalesAccountTotalCountSum(@Param("obj") SalesAccountSearchPojo obj);
}
