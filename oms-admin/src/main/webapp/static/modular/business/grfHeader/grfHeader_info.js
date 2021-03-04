/**
 * 初始化退换货详情对话框
 */
var GrfHeaderInfoDlg = {
    grfHeaderInfoData: {},
    id: "returnGoodsTable",
    table: null,
};

/**
 * 清除数据
 */
GrfHeaderInfoDlg.clearData = function () {
    this.grfHeaderInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
GrfHeaderInfoDlg.set = function (key, val) {
    this.grfHeaderInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
GrfHeaderInfoDlg.get = function (key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
GrfHeaderInfoDlg.close = function () {
    parent.layer.close(window.parent.GrfHeader.layerIndex);
}

/**
 * 收集数据
 */
GrfHeaderInfoDlg.collectData = function () {
    this
        .set('id')
        .set('code')
        .set('soCode')
        .set('asnCode')
        .set('platformGrfCode')
        .set('merchantId')
        .set('originalCode')
        .set('refOriginalCode')
        .set('warehouseId')
        .set('type')
        .set('returnType')
        .set('endUserDesc')
        .set('endUserApplyReson')
        .set('isRefund')
        .set('deliveryFee')
        .set('isOtherFee')
        .set('fee')
        .set('adjustRemark')
        .set('returnReasonCode')
        .set('codPayStatus')
        .set('returnReasonDesc')
        .set('checker')
        .set('checkBy')
        .set('state')
        .set('checkRemark')
        .set('cancelRemark')
        .set('arriveRemark')
        .set('arriveTime')
        .set('source')
        .set('creator')
        .set('createdBy')
        .set('createTime')
        .set('updatedBy')
        .set('updateTime')
        .set('totalAmount')
        .set('billCode1')
        .set('billCode2')
        .set('billCode3')
        .set('backCarrierName')
        .set('backCarrierShipCode')
        .set('shopId')
        .set('fetchAddress')
        .set('goodReceiverName')
        .set('goodReceiverTel')
        .set('refundAccount')
        .set('refundReasonType')
        .set('attachment1')
        .set('attachment2')
        .set('attachment3')
        .set('attachment4')
        .set('tenantId')
        .set('needChangeOrder')
        .set('changeReceiverTel')
        .set('changeReceiverProvince')
        .set('changeReceiverCity')
        .set('changeReceiverCounty')
        .set('changeReceiverAddress')
        .set('changeReceiverName')
        .set('changeReceiverProvinceId')
        .set('changeReceiverCityId')
        .set('changeReceiverCountyId')
        .set('checkTime')
        .set('customerId')
        .set('businessType')
        .set('skuQty')
        .set('unitsQty')
        .set('refundAmount')
        .set('refundName')
        .set('syncCount')
        .set('isSync')
        .set('isDamage')
        .set('syncState')
        .set('transTime')
        .set('transType')
        .set('refundMethod')
        .set('isForceReturn')
        .set('wmsRewriteDate');
}

/**
 * 提交添加
 */
GrfHeaderInfoDlg.addSubmit = function () {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/grfHeader/add", function (data) {
        Feng.success("添加成功!");
        window.parent.GrfHeader.table.refresh();
        GrfHeaderInfoDlg.close();
    }, function (data) {
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.grfHeaderInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
GrfHeaderInfoDlg.editSubmit = function () {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/grfHeader/update", function (data) {
        Feng.success("修改成功!");
        window.parent.GrfHeader.table.refresh();
        GrfHeaderInfoDlg.close();
    }, function (data) {
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.grfHeaderInfoData);
    ajax.start();
}

GrfHeaderInfoDlg.initColumn = function () {
    return [
        {title: '商品编码', field: 'code', visible: true, align: 'center', valign: 'middle'},
        {title: '商品名称', field: 'soCode', visible: true, align: 'center', valign: 'middle'},
        {title: '商品货号', field: 'soCode', visible: true, align: 'center', valign: 'middle'},
        {title: '批号', field: 'soCode', visible: true, align: 'center', valign: 'middle'},
        {title: '单价', field: 'soCode', visible: true, align: 'center', valign: 'middle'},
        {title: '购买数量', field: 'soCode', visible: true, align: 'center', valign: 'middle'},
        {title: '退货数量', field: 'soCode', visible: true, align: 'center', valign: 'middle'},
        {title: '商品金额', field: 'soCode', visible: true, align: 'center', valign: 'middle'},
    ]
};
$(function () {
    var defaultColunms = GrfHeaderInfoDlg.initColumn();
    var table = new BSTable(GrfHeaderInfoDlg.id, "/grfHeader/list", defaultColunms);
    table.setPaginationType("server");
    GrfHeaderInfoDlg.table = table.init();
});
