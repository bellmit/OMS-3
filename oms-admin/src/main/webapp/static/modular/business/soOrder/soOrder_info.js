/**
 * 初始化订单管理详情对话框
 */
var SoOrderInfoDlg = {
    soOrderInfoData : {}
};

/**
 * 清除数据
 */
SoOrderInfoDlg.clearData = function() {
    this.soOrderInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
SoOrderInfoDlg.set = function(key, val) {
    this.soOrderInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
SoOrderInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
SoOrderInfoDlg.close = function() {
    parent.layer.close(window.parent.SoOrder.layerIndex);
}

/**
 * 收集数据
 */
SoOrderInfoDlg.collectData = function() {
    this
    .set('id')
    .set('endUserId')
    .set('orderAmount')
    .set('orderCode')
    .set('originalCode')
    .set('grfCode')
    .set('shopId')
    .set('shopName')
    .set('orderStatus')
    .set('reasonFailure')
    .set('orderType')
    .set('orderAttr')
    .set('orderFulfillmentType')
    .set('orderRemark')
    .set('orderDeliveryFee')
    .set('orderSource')
    .set('orderPaymentConfirmDate')
    .set('orderCreateTime')
    .set('orderToLogisticsTime')
    .set('orderOutOfInventoryStatus')
    .set('orderNeedInvoice')
    .set('parentSoId')
    .set('deliveryDate')
    .set('receiveDate')
    .set('accountPayable')
    .set('productAmount')
    .set('goodReceiverName')
    .set('goodReceiverAddress')
    .set('goodReceiverProvince')
    .set('goodReceiverCity')
    .set('goodReceiverCounty')
    .set('goodReceiverPhone')
    .set('cancelDate')
    .set('isLeaf')
    .set('orderCsRemark')
    .set('toLogisticFlag')
    .set('goodReceiverMobile')
    .set('specProcFlag')
    .set('goodReceiverCityId')
    .set('goodReceiverCountyId')
    .set('goodReceiverProvinceId')
    .set('warehouseId')
    .set('warehouseIds')
    .set('merchantExpressNbr')
    .set('virtualStockStatus')
    .set('updateTime')
    .set('orderCsReason')
    .set('orderPromotionDiscount')
    .set('deliveryMethodType')
    .set('payServiceType')
    .set('orderWeight')
    .set('grossWeight')
    .set('collectOnDeliveryAmount')
    .set('merchantId')
    .set('version')
    .set('isDeleted')
    .set('createTime')
    .set('merchantDiscount')
    .set('platformDiscount')
    .set('prescription')
    .set('deliverySupplierId')
    .set('agentOperate')
    .set('isSplit')
    .set('orderCsBy')
    .set('orderCsTime')
    .set('isCpm')
    .set('generateWaveTime')
    .set('wmsIsLock')
    .set('wmsLockCode')
    .set('insteaStatus')
    .set('insteaSupplier')
    .set('orderFinishedTime')
    .set('orderEndTime')
    .set('invoiceType')
    .set('invoiceTitle')
    .set('orderVolume')
    .set('deliveryFeature')
    .set('orderCreateType')
    .set('exceptionCode')
    .set('exceptionRemark')
    .set('mergeOrderCode')
    .set('carrierDistributeFailedReason')
    .set('isCarrierDistribute')
    .set('carrierDistributeFailed')
    .set('tenantId')
    .set('freight')
    .set('freightAdjust')
    .set('ralationTenantId')
    .set('buyerNick')
    .set('isSync')
    .set('logisticsStatus')
    .set('auditFail')
    .set('receiptTime')
    .set('goodReceiverTown')
    .set('goodReceiverTownId')
    .set('collectionStatus')
    .set('collectionDate')
    .set('businessType')
    .set('orderFlag')
    .set('orderMergeFlag')
    .set('unfreezeTime')
    .set('deliverymanId')
    .set('invoiceTaxNo')
    .set('saleType')
    .set('parentPlatformOrderCode')
    .set('crossBorder')
    .set('freightFcode')
    .set('taxFcy')
    .set('actualFcy')
    .set('estimateFcy')
    .set('currCode')
    .set('insuranceAmount')
    .set('receiveType')
    .set('receiveNo')
    .set('payCompanyCode')
    .set('thirdPartyPayNo')
    .set('payOrderNo')
    .set('companyName')
    .set('companyCode')
    .set('eCommerceCode')
    .set('eCommerceName')
    .set('outerOrderCode')
    .set('outerOrderType')
    .set('customsCompletedTime')
    .set('prescriptionUrl')
    .set('source')
    .set('createdBy')
    .set('wmsRewriteDate')
    .set('taxCollectionDate')
    .set('paymentRemark');
}

/**
 * 提交添加
 */
SoOrderInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/soOrder/add", function(data){
        Feng.success("添加成功!");
        window.parent.SoOrder.table.refresh();
        SoOrderInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.soOrderInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
SoOrderInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/soOrder/update", function(data){
        Feng.success("修改成功!");
        window.parent.SoOrder.table.refresh();
        SoOrderInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.soOrderInfoData);
    ajax.start();
}

SoOrderInfoDlg.editUserSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/soOrder/soOrderUserUpdate", function(data){
        Feng.success("修改成功!");
        window.parent.SoOrder.table.refresh();
        SoOrderInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.soOrderInfoData);
    ajax.start();
}

$(function() {
});
