/**
 * 初始化快递公司设置详情对话框
 */
var MdLogisticsCompanyInfoDlg = {
    mdLogisticsCompanyInfoData : {}
};

/**
 * 清除数据
 */
MdLogisticsCompanyInfoDlg.clearData = function() {
    this.mdLogisticsCompanyInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
MdLogisticsCompanyInfoDlg.set = function(key, val) {
    this.mdLogisticsCompanyInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
MdLogisticsCompanyInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
MdLogisticsCompanyInfoDlg.close = function() {
    parent.layer.close(window.parent.MdLogisticsCompany.layerIndex);
}

/**
 * 收集数据
 */
MdLogisticsCompanyInfoDlg.collectData = function() {
    this
    .set('id')
    .set('code')
    .set('name')
    .set('createTime')
    .set('createdBy')
    .set('updateTime')
    .set('updatedBy')
    .set('isDeleted')
    .set('tenantId')
    .set('logisticsCodeRule');
}

/**
 * 提交添加
 */
MdLogisticsCompanyInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/mdLogisticsCompany/add", function(data){
        Feng.success("添加成功!");
        window.parent.MdLogisticsCompany.table.refresh();
        MdLogisticsCompanyInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.mdLogisticsCompanyInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
MdLogisticsCompanyInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/mdLogisticsCompany/update", function(data){
        Feng.success("修改成功!");
        window.parent.MdLogisticsCompany.table.refresh();
        MdLogisticsCompanyInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.mdLogisticsCompanyInfoData);
    ajax.start();
}

$(function() {

});
