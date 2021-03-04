/**
 * 初始化商家管理详情对话框
 */
var MerchantInfoDlg = {
    merchantInfoData : {}
};

/**
 * 清除数据
 */
MerchantInfoDlg.clearData = function() {
    this.merchantInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
MerchantInfoDlg.set = function(key, val) {
    this.merchantInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
MerchantInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
MerchantInfoDlg.close = function() {
    parent.layer.close(window.parent.Merchant.layerIndex);
}

/**
 * 收集数据
 */
MerchantInfoDlg.collectData = function() {
    this
    .set('id')
    .set('groupMerchantId')
    .set('merchantName')
    .set('createTime')
    .set('updateTime')
    .set('createdBy')
    .set('updatedBy')
    .set('isDeleted')
    .set('isSent')
    .set('taxPayerCode')
    .set('invoicePlatformCode')
    .set('invoiceInterfaceUrl')
    .set('tenantId')
    .set('code')
    .set('addressPhone')
    .set('param3')
    .set('param2')
    .set('param1')
    .set('taxPayerName');
}

/**
 * 提交添加
 */
MerchantInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/merchant/add", function(data){
        Feng.success("添加成功!");
        window.parent.Merchant.table.refresh();
        MerchantInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.merchantInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
MerchantInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/merchant/update", function(data){
        Feng.success("修改成功!");
        window.parent.Merchant.table.refresh();
        MerchantInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.merchantInfoData);
    ajax.start();
}

$(function() {

});
