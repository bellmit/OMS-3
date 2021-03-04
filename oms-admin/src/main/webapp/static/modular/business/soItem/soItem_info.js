/**
 * 初始化详情对话框
 */
var SoItemInfoDlg = {
    soItemInfoData : {}
};

/**
 * 清除数据
 */
SoItemInfoDlg.clearData = function() {
    this.soItemInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
SoItemInfoDlg.set = function(key, val) {
    this.soItemInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
SoItemInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
SoItemInfoDlg.close = function() {
    parent.layer.close(window.parent.SoItem.layerIndex);
}

/**
 * 收集数据
 */
SoItemInfoDlg.collectData = function() {
    this
    .set('id')
    .set('endUserId')
    .set('orderId')
    .set('platformOrderCode')
    .set('platformSkuId')
    .set('platformSkuCode')
    .set('platformSkuName')
    .set('productId')
    .set('productCode')
    .set('cost')
    .set('mainProductCode')
    .set('merchantId')
    .set('orderItemAmount')
    .set('orderItemPrice')
    .set('orderItemNum')
    .set('frozenStockNum')
    .set('productCname')
    .set('isGift')
    .set('productSaleType')
    .set('parentSoItemId')
    .set('isItemLeaf')
    .set('warehouseId')
    .set('virtualStockStatus')
    .set('updateTime')
    .set('productType')
    .set('productPicPath')
    .set('promotionAmount')
    .set('deliveryFeeAmount')
    .set('settlementPrice')
    .set('createTime')
    .set('isDeleted')
    .set('grossWeight')
    .set('merchantDiscount')
    .set('platformDiscount')
    .set('prescription')
    .set('specification')
    .set('insteaPrice')
    .set('agentOperate')
    .set('ralationTenantId')
    .set('tenantId')
    .set('actualQty')
    .set('stdPackQty')
    .set('remark')
    .set('salesMethod')
    .set('gnum')
    .set('itemEstimateFcy')
    .set('itemTaxFcy')
    .set('itemActualFcy')
    .set('actualPrice')
    .set('officeName')
    .set('lotNo')
    .set('expireTime')
    .set('productionTime');
}

/**
 * 提交添加
 */
SoItemInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/soItem/add", function(data){
        Feng.success("添加成功!");
        window.parent.SoItem.table.refresh();
        SoItemInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.soItemInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
SoItemInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/soItem/update", function(data){
        Feng.success("修改成功!");
        window.parent.SoItem.table.refresh();
        SoItemInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.soItemInfoData);
    ajax.start();
}

$(function() {

});
