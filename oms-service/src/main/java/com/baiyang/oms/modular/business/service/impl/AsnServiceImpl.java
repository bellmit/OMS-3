package com.baiyang.oms.modular.business.service.impl;

import com.baiyang.oms.core.constant.WarehouseConst;
import com.baiyang.oms.modular.business.dao.AsnMapper;
import com.baiyang.oms.modular.business.model.Asn;
import com.baiyang.oms.modular.business.service.IAsnService;
import com.baiyang.oms.modular.ningpocang.model.request.EntryOrderCreate;
import com.baiyang.oms.modular.ningpocang.model.request.GodownEntryCreateReq;
import com.baiyang.oms.modular.ningpocang.model.request.OrderLineCreate;
import com.baiyang.oms.modular.ningpocang.service.NingPoCangService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 采购单 服务实现类
 * </p>
 *
 * @author menglinghui123
 * @since 2018-07-06
 */
@Slf4j
@Service
public class AsnServiceImpl extends ServiceImpl<AsnMapper, Asn> implements IAsnService {

    @Resource
    private NingPoCangService ningPoCangService;

    @Override
    public String synchProcurement(Asn asn){

        if(asn.getWarehouseId().intValue() == WarehouseConst.NINGBO ){

            GodownEntryCreateReq godownEntryCreateReq = this.conventNbWarehouse(asn);
            ningPoCangService.godownEntryCreate(godownEntryCreateReq);
        }


        return "SUCCESS";


    }


    /**
     * 字段转换
     */
    private GodownEntryCreateReq conventNbWarehouse(Asn asn){
        GodownEntryCreateReq req = new GodownEntryCreateReq();
        try{

            EntryOrderCreate entryOrderCreater = new EntryOrderCreate();
            List<OrderLineCreate> orderLines = new ArrayList<>();
            OrderLineCreate orderLineCreate = new OrderLineCreate();

            SimpleDateFormat sd = new SimpleDateFormat(" YYYY-MM-DD HH:MM:SS");
            Date date = new Date();
            String dateFormat = sd.format(date);

            //
            entryOrderCreater.setEntryOrderCode(asn.getPoCode());
            entryOrderCreater.setOwnerCode("4000023120");
            entryOrderCreater.setWarehouseCode("17300");
            entryOrderCreater.setEntryOrderCode(asn.getAsnCode());
            entryOrderCreater.setOrderCreateTime(dateFormat);
            entryOrderCreater.setOrderType("CGRK");//采购入库

            entryOrderCreater.setExpectStartTime(sd.format(asn.getExptArriveTime()));//预期到货时间
            entryOrderCreater.setExpectEndTime(sd.format(asn.getValidDate()));//预期到货时间
//            entryOrderCreater.setLogisticsCode("ZTO");//物流公司编码 中通
//            entryOrderCreater.setLogisticsName("中通");//物流公司名称
//            entryOrderCreater.setExpressCode("2222222");//运单号
            entryOrderCreater.setSupplierCode(asn.getSupplierId()+"");//供应商编码
            entryOrderCreater.setSupplierName("");//供应商名称

//            entryOrderCreater.setOperatorCode(shiroUser.getId()+"");
//            entryOrderCreater.setOperatorName(shiroUser.getName());
//            entryOrderCreater.setOperateTime(dateFormat);
//
//            //发货人信息
//            SenderInfo senderInfo = new SenderInfo();
            orderLineCreate.setOrderLineNo("1");
            orderLineCreate.setOwnerCode("4000023120");
            orderLineCreate.setItemCode("");
            orderLineCreate.setItemId("");
            orderLineCreate.setItemName("");
            orderLineCreate.setPlanQty("");


            orderLines.add(orderLineCreate);
            req.setEntryOrder(entryOrderCreater);
            req.setOrderLines(orderLines);
        }catch (Exception e){
            log.info("数据转换异常！");
        }

        return req;
    }


}
