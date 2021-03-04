/**
 * 初始化退款单管理详情对话框
 */
var RefundOrderInfoDlg = {
    refundOrderInfoData: {},
    id: "refundGoodsTable",
    table: null,

};

/**
 * 清除数据
 */
RefundOrderInfoDlg.clearData = function () {
    this.refundOrderInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
RefundOrderInfoDlg.set = function (key, val) {
    this.refundOrderInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
RefundOrderInfoDlg.get = function (key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
RefundOrderInfoDlg.close = function () {
    parent.layer.close(window.parent.RefundOrder.layerIndex);
}

/**
 * 收集数据
 */
RefundOrderInfoDlg.collectData = function () {
    this
        .set('id')
        .set('code')
        .set('grfCode')
        .set('soCode')
        .set('originalCode')
        .set('type')
        .set('refundReason')
        .set('refundMethod')
        .set('refundAmount')
        .set('accountBank')
        .set('backCarrierShipCode')
        .set('refundAccount')
        .set('state')
        .set('remark')
        .set('shopId')
        .set('endUserId')
        .set('endUserName')
        .set('buyerNick')
        .set('shipmentNo')
        .set('confirmTime')
        .set('confirmUserName')
        .set('responsiblePerson')
        .set('checkBy')
        .set('createTime')
        .set('createdBy')
        .set('updatedBy')
        .set('updateTime')
        .set('tenantId')
        .set('refundName')
        .set('reasonType');
}

/**
 * 提交添加
 */
RefundOrderInfoDlg.addSubmit = function () {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/refundOrder/add", function (data) {
        Feng.success("添加成功!");
        window.parent.RefundOrder.table.refresh();
        RefundOrderInfoDlg.close();
    }, function (data) {
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.refundOrderInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
RefundOrderInfoDlg.editSubmit = function () {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/refundOrder/update", function (data) {
        Feng.success("修改成功!");
        window.parent.RefundOrder.table.refresh();
        RefundOrderInfoDlg.close();
    }, function (data) {
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.refundOrderInfoData);
    ajax.start();
}


RefundOrderInfoDlg.initColumn = function () {
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
    var defaultColunms = RefundOrderInfoDlg.initColumn();
    var table = new BSTable(RefundOrderInfoDlg.id, "/grfHeader/list", defaultColunms);
    table.setPaginationType("server");
    RefundOrderInfoDlg.table = table.init();
});
