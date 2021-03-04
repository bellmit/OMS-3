/**
 * 初始化详情对话框
 */
var SoOperateLogInfoDlg = {
    soOperateLogInfoData : {}
};

/**
 * 清除数据
 */
SoOperateLogInfoDlg.clearData = function() {
    this.soOperateLogInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
SoOperateLogInfoDlg.set = function(key, val) {
    this.soOperateLogInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
SoOperateLogInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
SoOperateLogInfoDlg.close = function() {
    parent.layer.close(window.parent.SoOperateLog.layerIndex);
}

/**
 * 收集数据
 */
SoOperateLogInfoDlg.collectData = function() {
    this
    .set('id')
    .set('soCode')
    .set('operator')
    .set('operatorId')
    .set('operationTime')
    .set('operateType')
    .set('remark')
    .set('tenantId')
    .set('platformOrderCode');
}

/**
 * 提交添加
 */
SoOperateLogInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/soOperateLog/add", function(data){
        Feng.success("添加成功!");
        window.parent.SoOperateLog.table.refresh();
        SoOperateLogInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.soOperateLogInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
SoOperateLogInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/soOperateLog/update", function(data){
        Feng.success("修改成功!");
        window.parent.SoOperateLog.table.refresh();
        SoOperateLogInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.soOperateLogInfoData);
    ajax.start();
}

$(function() {

});
