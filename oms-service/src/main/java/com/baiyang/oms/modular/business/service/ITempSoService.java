package com.baiyang.oms.modular.business.service;

import java.util.List;
import java.util.Map;

import com.baiyang.oms.modular.business.model.Shop;
import com.baiyang.oms.modular.business.model.TempSo;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 用户订单表 服务类
 * </p>
 *
 * @author will123
 * @since 2018-07-11
 */
public interface ITempSoService extends IService<TempSo> {
	
	
	List<Map<String, Object>> getSearchList(Map<String, Object> map);
	
	TempSo getTempSoByOrderCode(String orderCode);

    void getAndSaveTempSoList(int state, Shop shop, String catchOrderUrl);
    
    List<TempSo> getTemSoListByStatus(Map<String, Object> map);

    String catchedOrderManual(String platformOrderCodeStr, String account, Integer tenantId, String catchOrderUrl);

    List<Map<String,Object>> getTempSoSearchListByPage(Page<TempSo> page, List<String> platformOrderCodeSearchList, String platformIdSearch,
                                                       String merchantIdSearch, String shopIdSearch, String receiverMobileSearch,
                                                       String buyerNickSearch, String statusSearch, String errReasonSearch, String prescriptionSearch,
                                                       String orderStatusSearch, String sourceSearch, String payTypeSearch,
                                                       String createTimeSearchBegin, String createTimeSearchEnd,
                                                       String receiverNameSearch, String orderPaymentSearch,
                                                       String provinceName, String cityName, String countyName,
                                                       String customerRemarkSearch, String csRemarkSearch, String paidTimeSearchBegin, String paidTimeSearchEnd,
                                                       Integer[] statusArr,
                                                       Integer tenantId, List<String> merchantList, List<String> shopList);

    List<String> getTempSoPlatformOrderCodeByStatusAndOrderCode(List<String> platformOrderCodeList, Integer tenantId);

//    void generateOmsOrderManual(List<String> platformOrderCodeStatusErrorList);

    TempSo getUnAbnormalGoodsTempSoByStatusAndOrderCode(String abnormalGoodsPlatformOrderCode);

    List<TempSo> getTempSoListByExportCondition(Map<String, Object> searchMap);

    void getOfficeNameAndIsDt(String officeNameAndIsDtURL);
}
