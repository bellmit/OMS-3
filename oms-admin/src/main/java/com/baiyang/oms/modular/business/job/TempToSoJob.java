//package com.baiyang.oms.modular.business.job;
//
//import java.math.BigDecimal;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.quartz.JobDetail;
//import org.quartz.JobExecutionContext;
//import org.quartz.JobExecutionException;
//import org.quartz.JobKey;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
//
//import com.baiyang.oms.core.common.constant.factory.ConstantFactory;
//import com.baiyang.oms.core.page.PageInfoBT;
//import com.baiyang.oms.modular.business.model.PmWarehouseStock;
//import com.baiyang.oms.modular.business.model.SoOrder;
//import com.baiyang.oms.modular.business.model.TempSo;
//import com.baiyang.oms.modular.business.model.TempSoItem;
//import com.baiyang.oms.modular.business.service.IMdProductService;
//import com.baiyang.oms.modular.business.service.IMdWarehouseService;
//import com.baiyang.oms.modular.business.service.IPmWarehouseStockService;
//import com.baiyang.oms.modular.business.service.ISoItemService;
//import com.baiyang.oms.modular.business.service.ISoOrderService;
//import com.baiyang.oms.modular.business.service.ITempSoItemService;
//import com.baiyang.oms.modular.business.service.ITempSoService;
//import com.baiyang.oms.modular.business.service.OmsAreaService;
//import com.baiyang.oms.modular.business.util.ObjectUtils;
//import com.baiyang.oms.modular.job.BaseJob;
//import com.baiyang.oms.modular.job.model.JobAndTrigger;
//import com.baiyang.oms.modular.job.service.IJobAndTriggerService;
//import com.baiyang.oms.modular.job.service.impl.JobAndTriggerService;
//
////org.quartz.dataSource.myDS.discardIdleConnectionsSeconds
//
//public class TempToSoJob extends BaseJob {
//	
////	@Autowired
////    private ITempSoService tempSoService;
//
//	@Override
//	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
//		// TODO Auto-generated method stub
//		super.execute(jobExecutionContext);
//				System.out.println("定时任务开始了。。。。。。");
//		        JobDetail jobDetail=jobExecutionContext.getJobDetail();
//		        JobKey jobKey=jobDetail.getKey();
//		        String jobName=jobKey.getName();
//		        String jobGroup=jobKey.getGroup();
////		System.out.println("ddd==="+jobName+"==="+jobGroup);
//		    /**在 Quartz 中获取Spring ApplicationContext,以在类中 获取 相应Spring管理的Bean对象**/
//		    ApplicationContext acx=null;
//		    try {
//		        acx=getApplicationContext(jobExecutionContext);
//		    } catch (Exception e) {
//		        // TODO Auto-generated catch block
//		        e.printStackTrace();
//		    }
////		    ISoOrderService soOrderService = (ISoOrderService)acx.getBean("soOrderService");
////		    Map<String, Object> map = new HashMap<>();
////		    map.put("status", 0);//未处理
////		    map.put("orderStatus", 1);//已付款
////		    soOrderService.jobInsert(map);
//		    
//		    
//		    
//	}
//}
