
/**
 * 用户数据权限分组管理初始化
 */
var DataPermissionGroupRule = {};


/**
 * 初始化用户数据权限分组详情对话框
 */
var DataPermissionGroupInfoDlg = {
    dataPermissionGroupInfoData : {}
};

/**
 * 清除数据
 */
DataPermissionGroupInfoDlg.clearData = function() {
    this.dataPermissionGroupInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
DataPermissionGroupInfoDlg.set = function(key, val) {
    this.dataPermissionGroupInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
DataPermissionGroupInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
DataPermissionGroupInfoDlg.close = function() {
    parent.layer.close(window.parent.DataPermissionGroup.layerIndex);
}

/**
 * 收集数据
 */
DataPermissionGroupInfoDlg.collectData = function() {
    this
    .set('groupName')
    .set('description');
}

/**
 * 提交添加
 */
DataPermissionGroupInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/dataPermissionGroup/add", function(data){
        Feng.success("添加成功!");
        window.parent.DataPermissionGroup.table.refresh();
        DataPermissionGroupInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.dataPermissionGroupInfoData);
    ajax.start();
}
