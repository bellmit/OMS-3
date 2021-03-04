package com.baiyang.oms.modular.business.dao;

import com.baiyang.oms.modular.business.model.TempSo;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户订单表 Mapper 接口
 * </p>
 *
 * @author will123
 * @since 2018-07-11
 */
@Repository
public interface TempSoMapper extends BaseMapper<TempSo> {
	List<Map<String, Object>> getSearchList(Map<String, Object> map);
	TempSo getTempSoByOrderCode(String orderCode);

    int saveTempSoBatch(List<TempSo> tempSoList);

    List<TempSo> selectTempSoListBySyncTime(@Param("date_start") Date date_start, @Param("date") Date date);

    int updateTempSoAddressBatch(List<TempSo> tempSoAddressList);

    int updateTempSoCsRemarkBatch(List<TempSo> tempSoCsRemarkList);

    int updateTempSoOrderStatusBatch(List<TempSo> tempSoOrderStatusList);

    int updateTempSoPayTypeBatch(List<TempSo> tempSoPayTypeList);
    
    List<TempSo> getTemSoListByStatus(Map<String, Object> map);

    int updateTempSo(TempSo tempSoDb);

    List<Map<String,Object>> getTempSoSearchListByPage(@Param("page") Page<TempSo> page, @Param("platformOrderCodeSearchList") List<String> platformOrderCodeSearchList,
                                                       @Param("platformIdSearch") String platformIdSearch, @Param("merchantIdSearch") String merchantIdSearch,
                                                       @Param("shopIdSearch") String shopIdSearch, @Param("receiverMobileSearch") String receiverMobileSearch,
                                                       @Param("buyerNickSearch") String buyerNickSearch, @Param("statusSearch") String statusSearch,
                                                       @Param("errReasonSearch") String errReasonSearch, @Param("prescriptionSearch") String prescriptionSearch,
                                                       @Param("orderStatusSearch") String orderStatusSearch, @Param("sourceSearch") String sourceSearch, @Param("payTypeSearch") String payTypeSearch,
                                                       @Param("createTimeSearchBegin") String createTimeSearchBegin, @Param("createTimeSearchEnd") String createTimeSearchEnd,
                                                       @Param("receiverNameSearch") String receiverNameSearch, @Param("orderPaymentSearch") String orderPaymentSearch,
                                                       @Param("provinceName") String provinceName, @Param("cityName") String cityName, @Param("countyName") String countyName,
                                                       @Param("customerRemarkSearch") String customerRemarkSearch, @Param("csRemarkSearch") String csRemarkSearch,
                                                       @Param("paidTimeSearchBegin") String paidTimeSearchBegin, @Param("paidTimeSearchEnd") String paidTimeSearchEnd,
                                                       @Param("statusArr") Integer[] statusArr,
                                                       @Param("tenantId") Integer tenantId, @Param("merchantList") List<String> merchantList, @Param("shopList") List<String> shopList);

    List<String> getPlatformOrderCodeList(@Param("tenantId") Integer tenantId);

    int updateTempSoBatch(List<TempSo> tempSoRepeatList);

    List<String> getTempSoPlatformOrderCodeByStatusAndOrderCode(@Param("platformOrderCodeList") List<String> platformOrderCodeList, @Param("tenantId") Integer tenantId);

    List<TempSo> getTempSoListByPlatformOrderCodeList(@Param("platformOrderCodeStatusErrorList") List<String> platformOrderCodeStatusErrorList);

    int updateTempSoStatus(@Param("abnormalGoodsPlatformOrderCode") String abnormalGoodsPlatformOrderCode, @Param("errReason") String errReason);

    TempSo getUnAbnormalGoodsTempSoByStatusAndOrderCode(@Param("abnormalGoodsPlatformOrderCode") String abnormalGoodsPlatformOrderCode);

    List<TempSo> getTempSoListByExportCondition(Map<String, Object> searchMap);

    List<TempSo> getTempSoListByOrderStatus(@Param("dateStart")Date dateStart, @Param("dateEnd")Date dateEnd, @Param("orderStatus")String orderStatus);
}
