/**
 * 初始化采购单管理详情对话框
 */
var AsnInfoDlg = {
    asnInfoData : {}
};

/**
 * 清除数据
 */
AsnInfoDlg.clearData = function() {
    this.asnInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
AsnInfoDlg.set = function(key, val) {
    this.asnInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
AsnInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
AsnInfoDlg.close = function() {
    parent.layer.close(window.parent.Asn.layerIndex);
}

/**
 * 收集数据
 */
AsnInfoDlg.collectData = function() {
    this
    .set('poCode')
    .set('warehouseId')
    .set('merchantId')
    .set('supplierId')
    .set('asnCode')
    .set('asnType')
    .set('verifiedReceiveTime')
    .set('startReceiveTime')
    .set('finishReceiveTime')
    .set('expectedValidNum')
    .set('receivedNum')
    .set('exptArriveTime')
    .set('prodState')
    .set('deliveryType')
    .set('periodValid')
    .set('reserveCode')
    .set('reserveDate')
    .set('validDate')
    .set('businessType')
    .set('remark')
    .set('damageQuality')
    .set('normalQuality')
}

/**
 * 提交添加
 */
AsnInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/asn/add", function(data){
        Feng.success("添加成功!");
        window.parent.Asn.table.refresh();
        AsnInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.asnInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
AsnInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/asn/update", function(data){
        Feng.success("修改成功!");
        window.parent.Asn.table.refresh();
        AsnInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.asnInfoData);
    ajax.start();
}

$(function() {

});
