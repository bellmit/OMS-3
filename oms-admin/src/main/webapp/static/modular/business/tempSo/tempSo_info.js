/**
 * 初始化详情对话框
 */
var TempSoInfoDlg = {
    tempSoInfoData: {},
    receiverMobile:'',
};


/**
 * 查看手机号
 */
TempSoInfoDlg.showMobile = function () {
    $('#receiverMobile').val(TempSoInfoDlg.receiverMobile);
};
/**
 * 清除数据
 */
TempSoInfoDlg.clearData = function () {
    this.tempSoInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
TempSoInfoDlg.set = function (key, val) {
    this.tempSoInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
TempSoInfoDlg.get = function (key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
TempSoInfoDlg.close = function () {
    parent.layer.close(window.parent.TempSo.layerIndex);
}

/**
 * 收集数据
 */
TempSoInfoDlg.collectData = function () {
    this
        .set('id')
        .set('userId')
        .set('platformOrderCode')
        .set('platformId')
        .set('platformName')
        .set('status')
        .set('errReason')
        .set('customerAccount')
        .set('customerRemark')
        .set('csRemark')
        .set('createTime')
        .set('paidTime')
        .set('payType')
        .set('updateTime')
        .set('expectReceiveDate')
        .set('expectReceiveTime')
        .set('receiverName')
        .set('receiverAddress')
        .set('receiverPostCode')
        .set('receiverPhone')
        .set('balanceUsed')
        .set('receiverMobile')
        .set('amount')
        .set('productAmount')
        .set('fee')
        .set('merchantDiscount')
        .set('platformDiscount')
        .set('orderPayment')
        .set('otherFee')
        .set('platformFee')
        .set('codAmount')
        .set('deliveryMethodType')
        .set('merchantId')
        .set('shopId')
        .set('shopName')
        .set('invoiceTitle')
        .set('needInvoice')
        .set('invoiceContent')
        .set('updateFlag')
        .set('orderStatus')
        .set('modifyTime')
        .set('isDetailInvoice')
        .set('platformProvince')
        .set('platformCity')
        .set('platformCounty')
        .set('soStatus')
        .set('prescription')
        .set('soSyncFailed')
        .set('soSyncFailedReason')
        .set('syncTime')
        .set('logisticsNo')
        .set('invoiceType')
        .set('isAllocate')
        .set('deliveryDate')
        .set('finishTime')
        .set('tenantId')
        .set('buyerNick')
        .set('logisticsCompanyCode')
        .set('platformTown')
        .set('cainiaoShipping')
        .set('logisticsCompany')
        .set('invoiceTaxNo')
        .set('storeCode')
        .set('parentPlatformOrderCode')
        .set('crossBorder')
        .set('freightFcode')
        .set('taxFcy')
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
        .set('source')
        .set('paymentRemark');
}

/**
 * 提交添加
 */
TempSoInfoDlg.addSubmit = function () {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/tempSo/add", function (data) {
        Feng.success("添加成功!");
        window.parent.TempSo.table.refresh();
        TempSoInfoDlg.close();
    }, function (data) {
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.tempSoInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
TempSoInfoDlg.editSubmit = function () {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/tempSo/update", function (data) {
        Feng.success("修改成功!");
        window.parent.TempSo.table.refresh();
        TempSoInfoDlg.close();
    }, function (data) {
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.tempSoInfoData);
    ajax.start();
}

$(function () {
    TempSoInfoDlg.receiverMobile =  $('#receiverMobile').val();
    $('#receiverMobile').val("***********");
});
