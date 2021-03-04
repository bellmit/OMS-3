package com.baiyang.oms.modular.esinotrans.model.inBound;

public class InBoundDto {
	
	/** 转出转入单号 */
	private String inOutBoundNo;
	/** 仓库编码 */
	private String warehouseCode;
	/** 货主编码 */
	private String ownerCode;
	/** 入库抬头 */
	private InBound inbound;
	
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
	
	public InBound getInbound() {
		return inbound;
	}
	
	public void setInbound(InBound inbound) {
		this.inbound = inbound;
	}

}
