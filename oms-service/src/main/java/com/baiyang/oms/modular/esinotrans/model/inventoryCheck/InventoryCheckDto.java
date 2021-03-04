package com.baiyang.oms.modular.esinotrans.model.inventoryCheck;

public class InventoryCheckDto {
	
	/** 总页数 */
	private Integer totalPage;
	/** 当前页 */
	private Integer currentPage;
	/** 每页记录的条数 */
	private Integer pageSize;
	/** 仓库编码 */
	private String warehouseCode;
	/** 仓储系统的盘点单编码 */
	private String checkOrderId;
	/** 货主编码 */
	private String ownerCode;
	/** 盘点时间 */
	private String checkTime;
	/** 外部业务编码 */
	private String outBizCode;
	/** 备注 */
	private String remark;
	/** 商品行项目 */
	private CheckItems items;

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getWarehouseCode() {
		return warehouseCode;
	}

	public void setWarehouseCode(String warehouseCode) {
		this.warehouseCode = warehouseCode;
	}

	public String getCheckOrderId() {
		return checkOrderId;
	}

	public void setCheckOrderId(String checkOrderId) {
		this.checkOrderId = checkOrderId;
	}

	public String getOwnerCode() {
		return ownerCode;
	}

	public void setOwnerCode(String ownerCode) {
		this.ownerCode = ownerCode;
	}

	public String getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(String checkTime) {
		this.checkTime = checkTime;
	}

	public String getOutBizCode() {
		return outBizCode;
	}

	public void setOutBizCode(String outBizCode) {
		this.outBizCode = outBizCode;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public CheckItems getItems() {
		return items;
	}

	public void setItems(CheckItems items) {
		this.items = items;
	}

}
