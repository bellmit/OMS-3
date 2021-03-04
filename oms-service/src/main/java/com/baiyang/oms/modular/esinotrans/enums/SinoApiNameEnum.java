package com.baiyang.oms.modular.esinotrans.enums;

public enum SinoApiNameEnum {
	
	/** 订单创建api */
	CREATE_ORDER(1, "baiyangShopOrder"),
	
	/** 出库单创建api */
	CREATE_STOCK_OUT(2, "stockout.create"),
	
	/** 取消订单api */
	CANCEL_ORDER(3, "baiyangOrderCancel"),
	
	/** 库存查询api */
	INVENTORY_QUERY(4, "InventoryQuery_HZBS"),
	
	/** 创建入库单 */
	CREATE_ENTRY_ORDER(5, "baiyangPurchase"),
	
	/** 订单流水通知*/
	DO_STATUS_REWRITE(6, "doStatusRewrite"),
	
	/** 订单出库通知（中外运）*/
	DO_DETAIL_REWRITE(7, "doDetailRewrite"),
	
	/** 订单出库通知（塞隆）*/
	BY_DO_DETAIL(8, "byDoDetail"),
	
	/** 入库单确认 */
	PURCHASE_ORDER_CONFIRM(11, "purchaseOrderConfirm"),
	
	/** 库存盘点 */
	STOCK_TAKING(11, "stockTaking"),
	
	/** 库存转出 */
	BY_STOCK_OUT(11, "byStockOut"),
	
	/** 库存转入 */
	BY_STOCK_IN(12, "byStockIn");
	
	
	
	
	SinoApiNameEnum(Integer code, String message) {
		this.code = code;
		this.message = message;
	}
	
	private Integer code;
	
	private String message;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
