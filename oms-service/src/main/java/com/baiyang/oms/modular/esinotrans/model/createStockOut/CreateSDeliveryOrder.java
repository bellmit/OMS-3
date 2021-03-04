package com.baiyang.oms.modular.esinotrans.model.createStockOut;

import com.baiyang.oms.modular.esinotrans.model.common.BaseDeliveryOrder;

public class CreateSDeliveryOrder extends BaseDeliveryOrder{
	
	/** 单据总行数 */
	private Integer totalOrderLines = 1;
	/** 出库单创建时间 */
	private String createTime = "";
	/** 要求出库时间 */
	private String scheduleDate = "";
	/** 提货方式 */
	private String transportMode = "";
	/** 发件人信息 */
	private CreateSenderInfo senderInfo = new CreateSenderInfo();
	/** 收件人信息 */
	private CreateSReceiverInfo receiverInfo = new CreateSReceiverInfo();
	/** 备注 */
	private String remark = "";

	public Integer getTotalOrderLines() {
		return totalOrderLines;
	}

	public void setTotalOrderLines(Integer totalOrderLines) {
		this.totalOrderLines = totalOrderLines;
	}


	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getScheduleDate() {
		return scheduleDate;
	}

	public void setScheduleDate(String scheduleDate) {
		this.scheduleDate = scheduleDate;
	}

	public String getTransportMode() {
		return transportMode;
	}

	public void setTransportMode(String transportMode) {
		this.transportMode = transportMode;
	}

	public CreateSenderInfo getSenderInfo() {
		return senderInfo;
	}

	public void setSenderInfo(CreateSenderInfo senderInfo) {
		this.senderInfo = senderInfo;
	}

	public CreateSReceiverInfo getReceiverInfo() {
		return receiverInfo;
	}

	public void setReceiverInfo(CreateSReceiverInfo receiverInfo) {
		this.receiverInfo = receiverInfo;
	}
	
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
