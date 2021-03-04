package com.baiyang.oms.modular.esinotrans.model.createEntryOrder;

public class CreateEntryOrder {
	
	/** 单据总行数 */
	private Integer totalOrderLines = 1;
	/** 入库单号 */
	private String entryOrderCode = "";
	/** 采购订单号 */
	private String purchaseOrderCode = "";
	/** 仓库编码 */
	private String warehouseCode = "";
	/** 订单创建时间(YYYY-MM-DD HH:MM:SS) */
	private String orderCreateTime = "";
	/** 业务类型 当前固定为：CGRK  */
	private String orderType = "CGRK";
	/** 预期到货时间(YYYY-MM-DD HH:MM:SS) */
	private String expectStartTime = "";
	/** 最迟预期到货时间(YYYY-MM-DD HH:MM:SS) */
	private String expectEndTime = "";
	/** 物流公司编码 */
	private String logisticsCode = "";
	/** 物流公司名称 */
	private String logisticsName = "";
	/** 提货单号 */
	private String expressCode = "";
	/** 供应商编码 */
	private String supplierCode = "";
	/** 供应商名称 */
	private String supplierName = "";
	/** 操作员编码 */
	private String operatorCode = "";
	/** 操作员名称 */
	private String operatorName = "";
	/** 操作时间(YYYY-MM-DD HH:MM:SS) */
	private String operateTime = "";
	/** 备注 */
	private String remark = "";
	/** 发件人 */
	private CreateESenderInfo senderInfo = new CreateESenderInfo();
	/** 收件人 */
	private CreateEReceiverInfo receiverInfo = new CreateEReceiverInfo();

	public Integer getTotalOrderLines() {
		return totalOrderLines;
	}

	public void setTotalOrderLines(Integer totalOrderLines) {
		this.totalOrderLines = totalOrderLines;
	}

	public String getEntryOrderCode() {
		return entryOrderCode;
	}

	public void setEntryOrderCode(String entryOrderCode) {
		this.entryOrderCode = entryOrderCode;
	}

	public String getPurchaseOrderCode() {
		return purchaseOrderCode;
	}

	public void setPurchaseOrderCode(String purchaseOrderCode) {
		this.purchaseOrderCode = purchaseOrderCode;
	}

	public String getWarehouseCode() {
		return warehouseCode;
	}

	public void setWarehouseCode(String warehouseCode) {
		this.warehouseCode = warehouseCode;
	}

	public String getOrderCreateTime() {
		return orderCreateTime;
	}

	public void setOrderCreateTime(String orderCreateTime) {
		this.orderCreateTime = orderCreateTime;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getExpectStartTime() {
		return expectStartTime;
	}

	public void setExpectStartTime(String expectStartTime) {
		this.expectStartTime = expectStartTime;
	}

	public String getExpectEndTime() {
		return expectEndTime;
	}

	public void setExpectEndTime(String expectEndTime) {
		this.expectEndTime = expectEndTime;
	}

	public String getLogisticsCode() {
		return logisticsCode;
	}

	public void setLogisticsCode(String logisticsCode) {
		this.logisticsCode = logisticsCode;
	}

	public String getLogisticsName() {
		return logisticsName;
	}

	public void setLogisticsName(String logisticsName) {
		this.logisticsName = logisticsName;
	}

	public String getExpressCode() {
		return expressCode;
	}

	public void setExpressCode(String expressCode) {
		this.expressCode = expressCode;
	}

	public String getSupplierCode() {
		return supplierCode;
	}

	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getOperatorCode() {
		return operatorCode;
	}

	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public String getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public CreateESenderInfo getSenderInfo() {
		return senderInfo;
	}

	public void setSenderInfo(CreateESenderInfo senderInfo) {
		this.senderInfo = senderInfo;
	}

	public CreateEReceiverInfo getReceiverInfo() {
		return receiverInfo;
	}

	public void setReceiverInfo(CreateEReceiverInfo receiverInfo) {
		this.receiverInfo = receiverInfo;
	}
	
}
