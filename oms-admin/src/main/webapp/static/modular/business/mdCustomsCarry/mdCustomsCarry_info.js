/**
 * 初始化详情对话框
 */
var MdCustomsCarryInfoDlg = {
    mdCustomsCarryInfoData : {}
};

/**
 * 清除数据
 */
MdCustomsCarryInfoDlg.clearData = function() {
    this.mdCustomsCarryInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
MdCustomsCarryInfoDlg.set = function(key, val) {
    this.mdCustomsCarryInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
MdCustomsCarryInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
MdCustomsCarryInfoDlg.close = function() {
    parent.layer.close(window.parent.MdCustomsCarry.layerIndex);
}

/**
 * 收集数据
 */
MdCustomsCarryInfoDlg.collectData = function() {
    this
    .set('id')
    .set('logisticsCode')
    .set('logisCompanyName')
    .set('logisCompanyCode')
    .set('tenantId')
    .set('createTime')
    .set('updateTime')
    .set('createBy')
    .set('updateBy')
    .set('isDelete')
    .set('werehoueCode')
    .set('carrierId');
}

/**
 * 提交添加
 */
MdCustomsCarryInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/mdCustomsCarry/add", function(data){
        Feng.success("添加成功!");
        window.parent.MdCustomsCarry.table.refresh();
        MdCustomsCarryInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.mdCustomsCarryInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
MdCustomsCarryInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/mdCustomsCarry/update", function(data){
        Feng.success("修改成功!");
        window.parent.MdCustomsCarry.table.refresh();
        MdCustomsCarryInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.mdCustomsCarryInfoData);
    ajax.start();
}

$(function() {

});
