package com.baiyang.oms.modular.electronPort.model.pojo.orderInfo;

import java.util.List;

import com.baiyang.oms.modular.electronPort.model.common.JkfGoodsPurchaser;
import com.baiyang.oms.modular.electronPort.model.common.JkfOrderDetail;
import com.baiyang.oms.modular.electronPort.model.common.JkfOrderImportHead;
import com.baiyang.oms.modular.electronPort.model.common.JkfSign;


public class OrderInfoPojo {

	/** 业务类型 */
	private String businessType;
	
	/** 签名信息 */
	private JkfSign jkfSign;
	
	/** 订单表头信息 */
	private JkfOrderImportHead jfkOrderImportHead;
	
	/** 订单表体信息 */
	private List<JkfOrderDetail> jfkOrderDetailList;
	
	/** 购买人信息 */
	private JkfGoodsPurchaser jkfGoodsPurchaser;

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public JkfSign getJkfSign() {
		return jkfSign;
	}

	public void setJkfSign(JkfSign jkfSign) {
		this.jkfSign = jkfSign;
	}

	public JkfOrderImportHead getJfkOrderImportHead() {
		return jfkOrderImportHead;
	}

	public void setJfkOrderImportHead(JkfOrderImportHead jfkOrderImportHead) {
		this.jfkOrderImportHead = jfkOrderImportHead;
	}

	public List<JkfOrderDetail> getJfkOrderDetailList() {
		return jfkOrderDetailList;
	}

	public void setJfkOrderDetailList(List<JkfOrderDetail> jfkOrderDetailList) {
		this.jfkOrderDetailList = jfkOrderDetailList;
	}

	public JkfGoodsPurchaser getJkfGoodsPurchaser() {
		return jkfGoodsPurchaser;
	}

	public void setJkfGoodsPurchaser(JkfGoodsPurchaser jkfGoodsPurchaser) {
		this.jkfGoodsPurchaser = jkfGoodsPurchaser;
	}
	
}
