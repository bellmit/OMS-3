/**
 * 初始化详情对话框
 */
var MdCarrierInfoDlg = {
    mdCarrierInfoData : {}
};

/**
 * 清除数据
 */
MdCarrierInfoDlg.clearData = function() {
    this.mdCarrierInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
MdCarrierInfoDlg.set = function(key, val) {
    this.mdCarrierInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
MdCarrierInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
MdCarrierInfoDlg.close = function() {
    parent.layer.close(window.parent.MdCarrier.layerIndex);
}

/**
 * 收集数据
 */
MdCarrierInfoDlg.collectData = function() {
    this
    .set('id')
    .set('code')
    .set('name')
    .set('abbr')
    .set('isNon3pl')
    .set('contacter')
    .set('email')
    .set('telephone')
    .set('address')
    .set('domainId')
    .set('companyId')
    .set('isDeleted')
    .set('remark')
    .set('paymentService')
    .set('deliveryFeature')
    .set('createTime')
    .set('createdBy')
    .set('updateTime')
    .set('updatedBy')
    .set('printConfig')
    .set('isWeight')
    .set('dcZoneCode')
    .set('lineCode')
    .set('codeForBill')
    .set('logSysCode')
    .set('type')
    .set('shippingPeriod')
    .set('website')
    .set('isNeedPrint')
    .set('tenantId')
    .set('appKey')
    .set('appSecret')
    .set('appToken')
    .set('contentUrl')
    .set('backupUrl')
    .set('ext1')
    .set('ext2')
    .set('ext3')
    .set('ext4')
    .set('ext5')
    .set('logisticsCompanyId')
    .set('warehouseId')
    .set('logisticsCompanyCode')
    .set('shopId')
    .set('productCodes')
    .set('province')
    .set('city')
    .set('county')
    .set('expressStationName')
    .set('monthlyAccount')
    .set('zipCode')
    .set('platformCode')
    .set('carrierChargeType')
    .set('isSyncWms')
    .set('waybillType')
    .set('isByCarton')
    .set('mobile');
}

/**
 * 提交添加
 */
MdCarrierInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/mdCarrier/add", function(data){
        Feng.success("添加成功!");
        window.parent.MdCarrier.table.refresh();
        MdCarrierInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.mdCarrierInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
MdCarrierInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/mdCarrier/update", function(data){
        Feng.success("修改成功!");
        window.parent.MdCarrier.table.refresh();
        MdCarrierInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.mdCarrierInfoData);
    ajax.start();
}

$(function() {

});
