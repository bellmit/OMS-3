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
    parent.layer.close(window.parent.layerIndex);
}

/**
 * 收集数据
 */
SoOrderInfoDlg.collectData = function() {
    this
    .set('id')
    .set('goodReceiverName')
    .set('provinceId')
    .set('cityId')
    .set('countyId')
    .set('addressName')
    .set('goodReceiverMobile')
    .set('soOrderId')
    .set('idCard')
    .set('payName')
    .set('payPhone')
    ;
}


SoOrderInfoDlg.editUserSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/soOrder/addSoOrderUserUpdate", function(data){
        Feng.success("修改成功!");
        window.parent.SoOrder.table.refresh();
        SoOrderInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.soOrderInfoData);
    ajax.start();
}

SoOrderInfoDlg.editPayUserSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/soOrder/soOrderPayUserUpdate", function(data){
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
	$("#provinceId").val($("#provinceValue").val());
	 $("#cityId").val($("#cityValue").val());
	 $("#countyId").val($("#districtValue").val());
});
