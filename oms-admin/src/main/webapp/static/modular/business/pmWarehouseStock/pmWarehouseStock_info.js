/**
 * 初始化详情对话框
 */
var PmWarehouseStockInfoDlg = {
    pmWarehouseStockInfoData : {}
};

/**
 * 清除数据
 */
PmWarehouseStockInfoDlg.clearData = function() {
    this.pmWarehouseStockInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
PmWarehouseStockInfoDlg.set = function(key, val) {
    this.pmWarehouseStockInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
PmWarehouseStockInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
PmWarehouseStockInfoDlg.close = function() {
    parent.layer.close(window.parent.PmWarehouseStock.layerIndex);
}

/**
 * 收集数据
 */
PmWarehouseStockInfoDlg.collectData = function() {
    this
    .set('id')
    .set('productId')
    .set('merchantId')
    .set('warehouseId')
    .set('pmInfoId')
    .set('categoryId')
    .set('realStockNum')
    .set('realFrozenStockNum')
    .set('lockStockNum')
    .set('damageStockNum')
    .set('wtPutawayQty')
    .set('frozenDamageStockNum')
    .set('lockDemageStockNum')
    .set('frozenUpdateTime')
    .set('lastLeavingWhTime')
    .set('createTime')
    .set('updateTime')
    .set('pri')
    .set('tenantId');
}

/**
 * 提交添加
 */
PmWarehouseStockInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/pmWarehouseStock/add", function(data){
        Feng.success("添加成功!");
        window.parent.PmWarehouseStock.table.refresh();
        PmWarehouseStockInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.pmWarehouseStockInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
PmWarehouseStockInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/pmWarehouseStock/update", function(data){
        Feng.success("修改成功!");
        window.parent.PmWarehouseStock.table.refresh();
        PmWarehouseStockInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.pmWarehouseStockInfoData);
    ajax.start();
}

$(function() {
	 $("#productIdspan").show();
	 $("#realStockNumspan").show();
});
