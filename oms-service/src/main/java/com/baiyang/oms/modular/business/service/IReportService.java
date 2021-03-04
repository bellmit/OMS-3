package com.baiyang.oms.modular.business.service;

import com.baiyang.oms.modular.business.model.pojo.*;
import com.baomidou.mybatisplus.plugins.Page;

import java.util.List;
import java.util.Map;

/**
 * 说明：报表接口类
 *
 * @author:wangjunpeng
 * @Date:2018/10/9
 */
public interface IReportService {

    /**
     * 查询订单明细报表数据
     *
     * @param page
     * @param obj
     * @return
     */
    List<Map<String,Object>> getSalesAccountList(Page<SalesAccountSearchPojo> page, SalesAccountSearchPojo obj);

    /**
     * 查询导出订单明细数据
     * @param obj
     * @return
     */
    List<SalesAccountPojo> getSalesAccountListByExportCondition(SalesAccountSearchPojo obj);

    /**
     * 查询税金明细报表数据
     *
     * @param page
     * @param obj
     * @return
     */
    List<Map<String, Object>> getTaxStatementList(Page<TaxStatementPojo> page, TaxStatementPojo obj);

    /**
     * 查询导出税金报表数据
     *
     * @param obj
     * @return
     */
    List<TaxStatementPojo> getTaxStatementListByExportCondition(TaxStatementPojo obj);

    /**
     * 查询税金对账单数据
     *
     * @param page
     * @param obj
     * @return
     */
    List<Map<String, Object>> getTaxBalanceList(Page<TaxBalancePojo> page, TaxBalancePojo obj);

    /**
     * 查询导出税金对账单数据
     * @param obj
     * @return
     */
    List<TaxStatementPojo> getTaxBalanceListByExportCondition(TaxBalancePojo obj);

    SalesAccountTotalCountPojo getSalesAccountTotalCountSum(SalesAccountSearchPojo obj);
}
