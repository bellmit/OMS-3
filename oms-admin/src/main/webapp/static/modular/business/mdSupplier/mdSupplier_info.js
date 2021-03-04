/**
 * 初始化供应商管理详情对话框
 */
var MdSupplierInfoDlg = {
    mdSupplierInfoData : {}
};

/**
 * 清除数据
 */
MdSupplierInfoDlg.clearData = function() {
    this.mdSupplierInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
MdSupplierInfoDlg.set = function(key, val) {
    this.mdSupplierInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
MdSupplierInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
MdSupplierInfoDlg.close = function() {
    parent.layer.close(window.parent.MdSupplier.layerIndex);
}

/**
 * 收集数据
 */
MdSupplierInfoDlg.collectData = function() {
    this
    .set('id')
    .set('supplierCompanyName')
    .set('supplierContactPerson')
    .set('supplierContactPhone')
    .set('isDeleted')
    .set('supplierContactEmail')
    .set('supplierContactMobile')
    .set('supplierBankName')
    .set('supplierBankAccountName')
    .set('supplierBankAccountNumber')
    .set('supplierStatus')
    .set('createTime')
    .set('supplierCode')
    .set('cooperationStatus')
    .set('isSent')
    .set('balanceAccountsType')
    .set('merchantOfficeAddress')
    .set('createdBy')
    .set('updatedBy')
    .set('updateTime')
    .set('tenantId')
    .set('distributionType')
    .set('expand1')
    .set('expand2')
    .set('expand3')
    .set('expand4')
    .set('taxRegNo');
}

/**
 * 提交添加
 */
MdSupplierInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/mdSupplier/add", function(data){
        Feng.success("添加成功!");
        window.parent.MdSupplier.table.refresh();
        MdSupplierInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.mdSupplierInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
MdSupplierInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/mdSupplier/update", function(data){
        Feng.success("修改成功!");
        window.parent.MdSupplier.table.refresh();
        MdSupplierInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.mdSupplierInfoData);
    ajax.start();
}

$(function() {

});
