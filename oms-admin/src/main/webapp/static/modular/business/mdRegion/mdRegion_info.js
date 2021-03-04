/**
 * 初始化详情对话框
 */
var MdRegionInfoDlg = {
    mdRegionInfoData : {}
};

/**
 * 清除数据
 */
MdRegionInfoDlg.clearData = function() {
    this.mdRegionInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
MdRegionInfoDlg.set = function(key, val) {
    this.mdRegionInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
MdRegionInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
MdRegionInfoDlg.close = function() {
    parent.layer.close(window.parent.MdRegion.layerIndex);
}

/**
 * 收集数据
 */
MdRegionInfoDlg.collectData = function() {
    this
    .set('id')
    .set('code')
    .set('name')
    .set('type')
    .set('parentName')
    .set('parentCode')
    .set('parentId')
    .set('fullname')
    .set('createTime')
    .set('updateTime')
    .set('createdBy')
    .set('updatedBy')
    .set('enabled')
    .set('isDeleted')
    .set('isSent')
    .set('dataExchangeFlag')
    .set('aliasName')
    .set('tenantId');
}

/**
 * 提交添加
 */
MdRegionInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/mdRegion/add", function(data){
        Feng.success("添加成功!");
        window.parent.MdRegion.table.refresh();
        MdRegionInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.mdRegionInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
MdRegionInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/mdRegion/update", function(data){
        Feng.success("修改成功!");
        window.parent.MdRegion.table.refresh();
        MdRegionInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.mdRegionInfoData);
    ajax.start();
}

$(function() {

});
