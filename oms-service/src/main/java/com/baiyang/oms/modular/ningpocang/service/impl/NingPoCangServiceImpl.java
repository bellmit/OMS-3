package com.baiyang.oms.modular.ningpocang.service.impl;

import com.baiyang.oms.modular.business.model.SoOperateLog;
import com.baiyang.oms.modular.business.service.ISoOperateLogService;
import com.baiyang.oms.modular.business.service.ISoOrderService;
import com.baiyang.oms.modular.esinotrans.model.stockNotice.StockNPackage;
import com.baiyang.oms.modular.esinotrans.model.stockNotice.StockNPackages;
import com.baiyang.oms.modular.esinotrans.util.DateUtil;
import com.baiyang.oms.modular.esinotrans.util.JsonUtil;
import com.baiyang.oms.modular.ningpocang.model.base.BaseResponse;
import com.baiyang.oms.modular.ningpocang.model.pojo.*;
import com.baiyang.oms.modular.ningpocang.model.request.*;
import com.baiyang.oms.modular.ningpocang.model.response.ExitListConfirmResp;
import com.baiyang.oms.modular.ningpocang.service.NingPoCangService;
import com.baiyang.oms.modular.rabbitMq.queen.QueuesType;
import com.baiyang.oms.modular.rabbitMq.service.RabbitMqService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class NingPoCangServiceImpl implements NingPoCangService {

    @Resource
    private RabbitMqService rabbitMqService;
    @Resource
	private ISoOperateLogService soOperateLogService; 
    @Resource
	ISoOrderService soOrderService;

    @Override
    public String goodsSynchronize(SynchronizeGoodsReq pojo) {
        if (pojo == null) {
            return "fail";
        }
        SynchronizeGoodsReqLog pojoLog = new SynchronizeGoodsReqLog();
        pojoLog.setPojo(pojo);
        rabbitMqService.send(QueuesType.NINGPOCANG_GOODSSYNCHRONIZE, JsonUtil.beanToJsonString(pojoLog));
        return "success";
    }

    @Override
    public String godownEntryCreate(GodownEntryCreateReq pojo) {
        if (pojo == null) {
            return "fail";
        }
        GodownEntryCreateReqLog pojoLog = new GodownEntryCreateReqLog();
        pojoLog.setPojo(pojo);
        rabbitMqService.send(QueuesType.NINGPOCANG_GODOWNENTRYCREATE, JsonUtil.beanToJsonString(pojoLog));
        return "success";
    }

    @Override
    public BaseResponse godownEntryConfirm(GodownEntryConfirmReq pojo) {
        BaseResponse response = new BaseResponse();
        if (pojo == null) {
            response.setFlag("failure");
            response.setCode("-1");
            response.setMessage("参数不可为空");
            return response;
        }
        // TODO 处理宁波仓回调传过来的参数逻辑

        // 成功
        response.setFlag("success");
        response.setCode("000");
        return response;
    }

    @Override
    public String exitListCreate(ExitListCreateReq pojo) {
        if (pojo == null) {
            return "fail";
        }
        ExitListCreateReqLog pojoLog = new ExitListCreateReqLog();
        pojoLog.setPojo(pojo);
        rabbitMqService.send(QueuesType.NINGPOCANG_EXITLISTCREATE, JsonUtil.beanToJsonString(pojoLog));
        return "success";
    }

    @Override
    public ExitListConfirmResp exitListConfirm(ExitListConfirmReq pojo) {
        ExitListConfirmResp response = new ExitListConfirmResp();
        if (pojo == null) {
            response.setFlag("failure");
            response.setCode("-1");
            response.setMessage("参数不可为空");
            return response;
        }
        // TODO 处理宁波仓回调传过来的参数逻辑

        // 成功
        response.setCreateTime(DateUtil.getDateStrByFormat(new Date(), null));
        response.setDeliveryOrderId(pojo.getDeliveryOrder().getDeliveryOrderId());
        response.setFlag("success");
        response.setCode("000");
        return response;
    }

    @Override
    public String billOfParcelsCreate(BillOfParcelsCreateReq pojo) {
        if (pojo == null) {
            return "fail";
        }
        BillOfParcelsCreateReqLog pojoLog = new BillOfParcelsCreateReqLog();
        pojoLog.setPojo(pojo);
        rabbitMqService.send(QueuesType.NINGPOCANG_BILLOFPARCELSCREATE, JsonUtil.beanToJsonString(pojoLog));
        return "success";
    }

    @Override
    public BaseResponse billOfParcelsConfirm(BillOfParcelsConfirmReq pojo) {
//    	System.out.println("BillOfParcelsConfirmReq===="+pojo);
        BaseResponse response = new BaseResponse();
        if (pojo == null) {
            response.setFlag("failure");
            response.setCode("-1");
            response.setMessage("参数不可为空");
            return response;
        }
        // TODO 处理宁波仓回调传过来的参数逻辑
        
       String soNo = pojo.getDeliveryOrder().getDeliveryOrderCode();//deliveryOrderCode
       String plantOrder = pojo.getDeliveryOrder().getDeliveryOrderId();
       String storeCode =  pojo.getDeliveryOrder().getWarehouseCode();
       String status = pojo.getDeliveryOrder().getStatus();
       if(status.equals("DELIVERED")){//发货完成
    	   StockNPackages packages = new StockNPackages();
           for(PackageInfoConfirm pf:pojo.getPackages()){
           	StockNPackage np = new StockNPackage();
       		np.setExpressCode(pf.getExpressCode());
       		np.setLogisticsCode(pf.getLogisticsCode());
       		packages.setStockNPackage(np);
           }
           soOrderService.clearCustomsDeliveryOrderCallBack(soNo, packages, 
        		   new Date(), 1, null, storeCode, plantOrder);
       }else{//其他状态
    	   SoOperateLog sol = new SoOperateLog();
    	   sol.setOperationTime(new Date());
		    sol.setOperator("system");
		    sol.setSoCode(soNo);
		    sol.setTenantId(172);
    	   switch (status) {
           case "NEW":
			    sol.setRemark("出库单状态是 未开始处理");
           case "ACCEPT":
			    sol.setRemark("出库单状态是 仓库接单");
           case "PARTDELIVERED":
			    sol.setRemark("出库单状态是 部分发货完成");
           case "EXCEPTION":
			    sol.setRemark("出库单状态是 异常");
           case "CANCELED":
			    sol.setRemark("出库单状态是 取消");
           case "CLOSED":
			    sol.setRemark("出库单状态是 关闭");
           case "REJECT":
			    sol.setRemark("出库单状态是 拒单");
           case "CANCELEDFAIL":
			    sol.setRemark("出库单状态是 取消失败");
           default:  
        	   sol.setRemark("出库单状态是 其他错误");
			    soOperateLogService.insert(sol);
    	   }
       }

        // 成功
        response.setFlag("success");
        response.setCode("000");
        return response;
    }

    /**
     * 订单流水通知接口
     *
     * @param pojo
     */
    @Override
    public String orderProcessReport(OrderProcessReportReq pojo) {
        if (pojo == null) {
            return "fail";
        }
        OrderProcessReportReqLog pojoLog = new OrderProcessReportReqLog();
        pojoLog.setPojo(pojo);
        rabbitMqService.send(QueuesType.NINGPO_ORDER_PROCESS_REPORT, JsonUtil.beanToJsonString(pojoLog));
        return "success";
    }

    /**
     * 单据取消接口
     *
     * @param pojo
     */
    @Override
    public String cancelOrder(CancelOrder pojo) {
        if (pojo == null) {
            return "fail";
        }
        CancelOrderReqLog pojoLog = new CancelOrderReqLog();
        pojoLog.setPojo(pojo);
        rabbitMqService.send(QueuesType.NINGPO_ORDER_CANCEL, JsonUtil.beanToJsonString(pojoLog));
        return "success";
    }

    /**
     * 单据取消接口
     *
     * @param pojo
     */
    @Override
    public String closeOrder(CloseOrder pojo) {
        if (pojo == null) {
            return "fail";
        }
        CloseOrderReqLog pojoLog = new CloseOrderReqLog();
        pojoLog.setPojo(pojo);
        rabbitMqService.send(QueuesType.NINGPO_ORDER_CLOSE, JsonUtil.beanToJsonString(pojoLog));
        return "success";
    }

    /**
     * 发货单缺货通知接口
     *
     * @param pojo
     */
    @Override
    public String itemLackReport(ItemLackReport pojo) {
        if (pojo == null) {
            return "fail";
        }
        ItemLackReportReqLog pojoLog = new ItemLackReportReqLog();
        pojoLog.setPojo(pojo);
        rabbitMqService.send(QueuesType.NINGPO_ITEM_LACK_REPORT, JsonUtil.beanToJsonString(pojoLog));
        return "success";
    }

    /**
     * 盘点单创建接口
     *
     * @param pojo
     * @return
     */
    @Override
    public String inventoryPlanCreate(InventoryPlanCreate pojo) {
        if (pojo == null) {
            return "fail";
        }
        InventoryPlanCreateReqLog pojoLog = new InventoryPlanCreateReqLog();
        pojoLog.setPojo(pojo);
        rabbitMqService.send(QueuesType.NINGPO_INVENTORY_PLAN_CREATE, JsonUtil.beanToJsonString(pojoLog));
        return "success";
    }

    /**
     * 盘点单确认接口
     *
     * @param pojo
     */
    @Override
    public BaseResponse inventoryPlanConfirm(InventoryPlanConfirm pojo) {
        BaseResponse response = new BaseResponse();
        if (pojo == null) {
            response.setFlag("failure");
            response.setCode("-1");
            response.setMessage("参数不可为空");
            return response;
        }
        // TODO 处理宁波仓回调传过来的参数逻辑

        // 成功
        response.setFlag("success");
        response.setCode("000");
        return response;
    }

    /**
     * 损益单通知接口
     *
     * @param pojo
     */
    @Override
    public String inventoryAdjustmentReport(InventoryAdjustmentReport pojo) {
        if (pojo == null) {
            return "fail";
        }
        InventoryAdjustmentReportReqLog pojoLog = new InventoryAdjustmentReportReqLog();
        pojoLog.setPojo(pojo);
        rabbitMqService.send(QueuesType.NINGPO_INVENTORY_ADJUSTMENT_REPORT, JsonUtil.beanToJsonString(pojoLog));
        return "success";
    }
}
