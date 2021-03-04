/**
 * 初始化详情对话框
 */
var TempSoOperateLogInfoDlg = {
    tempSoOperateLogInfoData : {}
};

/**
 * 清除数据
 */
TempSoOperateLogInfoDlg.clearData = function() {
    this.tempSoOperateLogInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
TempSoOperateLogInfoDlg.set = function(key, val) {
    this.tempSoOperateLogInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
TempSoOperateLogInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
TempSoOperateLogInfoDlg.close = function() {
    parent.layer.close(window.parent.TempSoOperateLog.layerIndex);
}

/**
 * 收集数据
 */
TempSoOperateLogInfoDlg.collectData = function() {
    this
    .set('id')
    .set('code')
    .set('remark')
    .set('operateTime')
    .set('operator')
    .set('tenantId')
    .set('platformId');
}

/**
 * 提交添加
 */
TempSoOperateLogInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/tempSoOperateLog/add", function(data){
        Feng.success("添加成功!");
        window.parent.TempSoOperateLog.table.refresh();
        TempSoOperateLogInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.tempSoOperateLogInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
TempSoOperateLogInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/tempSoOperateLog/update", function(data){
        Feng.success("修改成功!");
        window.parent.TempSoOperateLog.table.refresh();
        TempSoOperateLogInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.tempSoOperateLogInfoData);
    ajax.start();
}

$(function() {

});
