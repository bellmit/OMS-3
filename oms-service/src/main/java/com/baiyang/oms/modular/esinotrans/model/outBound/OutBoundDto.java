package com.baiyang.oms.modular.esinotrans.model.outBound;

public class OutBoundDto {
	
	/** 转出转入单号 */
	private String inOutBoundNo;
	/** 仓库编码 */
	private String warehouseCode;
	/** 货主编码 */
	private String ownerCode;
	/** 入库抬头 */
	private OutBound outbound;
	
	public String getInOutBoundNo() {
		return inOutBoundNo;
	}
	
	public void setInOutBoundNo(String inOutBoundNo) {
		this.inOutBoundNo = inOutBoundNo;
	}
	
	public String getWarehouseCode() {
		return warehouseCode;
	}
	
	public void setWarehouseCode(String warehouseCode) {
		this.warehouseCode = warehouseCode;
	}
	
	public String getOwnerCode() {
		return ownerCode;
	}
	
	public void setOwnerCode(String ownerCode) {
		this.ownerCode = ownerCode;
	}
	
	public OutBound getOutbound() {
		return outbound;
	}
	
	public void setOutbound(OutBound outbound) {
		this.outbound = outbound;
	}

}
