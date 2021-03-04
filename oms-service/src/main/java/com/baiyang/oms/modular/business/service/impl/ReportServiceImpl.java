package com.baiyang.oms.modular.business.service.impl;

import com.baiyang.oms.modular.business.dao.ReportMapper;
import com.baiyang.oms.modular.business.model.pojo.*;
import com.baiyang.oms.modular.business.service.IReportService;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements IReportService {

    @Autowired
    private ReportMapper reportMapper;

    @Override
    public List<Map<String, Object>> getSalesAccountList(Page<SalesAccountSearchPojo> page, SalesAccountSearchPojo obj) {
        return reportMapper.getSalesAccountList(page,obj);
    }

    @Override
    public List<SalesAccountPojo> getSalesAccountListByExportCondition(SalesAccountSearchPojo obj) {
        return reportMapper.getSalesAccountListByExportCondition(obj);
    }

    @Override
    public List<Map<String, Object>> getTaxStatementList(Page<TaxStatementPojo> page, TaxStatementPojo obj) {
        return reportMapper.getTaxStatementList(page,obj);
    }

    @Override
    public List<TaxStatementPojo> getTaxStatementListByExportCondition(TaxStatementPojo obj) {
        return reportMapper.getTaxStatementListByExportCondition(obj);
    }

    @Override
    public List<Map<String, Object>> getTaxBalanceList(Page<TaxBalancePojo> page, TaxBalancePojo obj) {
        return reportMapper.getTaxBalanceList(page,obj);
    }

    @Override
    public List<TaxStatementPojo> getTaxBalanceListByExportCondition(TaxBalancePojo obj) {
        return reportMapper.getTaxBalanceListByExportCondition(obj);
    }

    @Override
    public SalesAccountTotalCountPojo getSalesAccountTotalCountSum(SalesAccountSearchPojo obj) {
        return reportMapper.getSalesAccountTotalCountSum(obj);
    }
}
