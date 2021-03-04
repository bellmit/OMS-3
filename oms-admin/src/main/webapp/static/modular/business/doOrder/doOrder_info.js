/**
 * 初始化详情对话框
 */
var DoOrderInfoDlg = {
    doOrderInfoData : {}
};

/**
 * 清除数据
 */
DoOrderInfoDlg.clearData = function() {
    this.doOrderInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
DoOrderInfoDlg.set = function(key, val) {
    this.doOrderInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
DoOrderInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
DoOrderInfoDlg.close = function() {
    parent.layer.close(window.parent.DoOrder.layerIndex);
}

/**
 * 收集数据
 */
DoOrderInfoDlg.collectData = function() {
    this
    .set('id')
    .set('doNo')
    .set('soNo')
    .set('parentSoNo')
    .set('wvNo')
    .set('sortingNo')
    .set('grfNo')
    .set('doCreateTime')
    .set('soCreateTime')
    .set('lastOperateTime')
    .set('state')
    .set('doType')
    .set('deliveryMode')
    .set('deliveryMethodType')
    .set('deliverymanId')
    .set('orderSource')
    .set('cartonQuantity')
    .set('paymentMode')
    .set('productAmount')
    .set('toCollectAmount')
    .set('whId')
    .set('productCodes')
    .set('carrierId')
    .set('consignee')
    .set('consigneeTelephone')
    .set('consigneeMobile')
    .set('provinceId')
    .set('province')
    .set('cityId')
    .set('city')
    .set('districtId')
    .set('district')
    .set('area')
    .set('areaId')
    .set('address')
    .set('isNeedInvoice')
    .set('leavingWhTime')
    .set('arriveDcTime')
    .set('leaveDcTime')
    .set('returnTime')
    .set('createdBy')
    .set('createTime')
    .set('updatedBy')
    .set('updateTime')
    .set('isLock')
    .set('shippedSkuQty')
    .set('shippedUnitsQty')
    .set('shipmentNo')
    .set('isSynWms')
    .set('isSynDts')
    .set('payTime')
    .set('syncTime')
    .set('remark')
    .set('soId')
    .set('notice')
    .set('orderSaleMethod')
    .set('orderQty')
    .set('packQty')
    .set('isThirdPartyBill')
    .set('orderPaymentMethodId')
    .set('orderAmount')
    .set('accountPayable')
    .set('discountAmount')
    .set('orderDeliveryFee')
    .set('invoiceContent')
    .set('invoiceNumber')
    .set('invoiceAmount')
    .set('receiveAmount')
    .set('merchantId')
    .set('isDeleted')
    .set('dataExchangeFlag')
    .set('shopId')
    .set('originalSoCode')
    .set('prescription')
    .set('csRemark')
    .set('customerRemark')
    .set('orderVolume')
    .set('orderWeight')
    .set('isPrintWaybill')
    .set('isPrintDo')
    .set('tenantId')
    .set('parentPlatformOrderCode')
    .set('crossBorder')
    .set('isDeal')
    .set('outerOrderCode')
    .set('outerOrderType')
    .set('teamBuyRuleId')
    .set('subType')
    .set('shopName')
    .set('merchantName');
}

/**
 * 提交添加
 */
DoOrderInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/doOrder/add", function(data){
        Feng.success("添加成功!");
        window.parent.DoOrder.table.refresh();
        DoOrderInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.doOrderInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
DoOrderInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/doOrder/update", function(data){
        Feng.success("修改成功!");
        window.parent.DoOrder.table.refresh();
        DoOrderInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.doOrderInfoData);
    ajax.start();
}

$(function() {

});
