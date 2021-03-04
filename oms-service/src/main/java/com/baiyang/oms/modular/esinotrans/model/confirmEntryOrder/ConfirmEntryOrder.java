package com.baiyang.oms.modular.esinotrans.model.confirmEntryOrder;

public class ConfirmEntryOrder {
	
	/** 入库单编码 */
	private String entryOrderCode;
	/** 货主编码 */
	private String ownerCode;
	/** 仓库编码 */
	private String warehouseCode;
	/** 仓储系统入库单ID */
	private String entryOrderId;
	/** 入库单类型 */
	private String entryOrderType;
	/** 外部业务编码 */
	private String outBizCode;
	/** 支持出入库单多次收货 */
	private Integer onfirmType;
	/** 入库单状态 */
	private String status;
	/** 操作时间 (YYYY-MM-DD HH:MM:SS；(当status=FULFILLED, operateTime为入库时间))*/
	private String operateTime;
	/** 入库开始时间 */
	private String arrivalbegintime;
	/** 入库结束时间 */
	private String arrivalendtime;

	public String getEntryOrderCode() {
		return entryOrderCode;
	}

	public void setEntryOrderCode(String entryOrderCode) {
		this.entryOrderCode = entryOrderCode;
	}

	public String getOwnerCode() {
		return ownerCode;
	}

	public void setOwnerCode(String ownerCode) {
		this.ownerCode = ownerCode;
	}

	public String getWarehouseCode() {
		return warehouseCode;
	}

	public void setWarehouseCode(String warehouseCode) {
		this.warehouseCode = warehouseCode;
	}

	public String getEntryOrderId() {
		return entryOrderId;
	}

	public void setEntryOrderId(String entryOrderId) {
		this.entryOrderId = entryOrderId;
	}

	public String getEntryOrderType() {
		return entryOrderType;
	}

	public void setEntryOrderType(String entryOrderType) {
		this.entryOrderType = entryOrderType;
	}

	public String getOutBizCode() {
		return outBizCode;
	}

	public void setOutBizCode(String outBizCode) {
		this.outBizCode = outBizCode;
	}

	public Integer getOnfirmType() {
		return onfirmType;
	}

	public void setOnfirmType(Integer onfirmType) {
		this.onfirmType = onfirmType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
	}

	public String getArrivalbegintime() {
		return arrivalbegintime;
	}

	public void setArrivalbegintime(String arrivalbegintime) {
		this.arrivalbegintime = arrivalbegintime;
	}

	public String getArrivalendtime() {
		return arrivalendtime;
	}

	public void setArrivalendtime(String arrivalendtime) {
		this.arrivalendtime = arrivalendtime;
	}
	

}
