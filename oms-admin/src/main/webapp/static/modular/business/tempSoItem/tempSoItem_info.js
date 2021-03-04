/**
 * 初始化详情对话框
 */
var TempSoItemInfoDlg = {
    tempSoItemInfoData : {}
};

/**
 * 清除数据
 */
TempSoItemInfoDlg.clearData = function() {
    this.tempSoItemInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
TempSoItemInfoDlg.set = function(key, val) {
    this.tempSoItemInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
TempSoItemInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
TempSoItemInfoDlg.close = function() {
    parent.layer.close(window.parent.TempSoItem.layerIndex);
}

/**
 * 收集数据
 */
TempSoItemInfoDlg.collectData = function() {
    this
    .set('id')
    .set('platformOrderCode')
    .set('merchantId')
    .set('platformMainSkuId')
    .set('platformMainSkuCode')
    .set('platformSkuId')
    .set('mainSkuId')
    .set('skuId')
    .set('skuCode')
    .set('skuName')
    .set('pmInfoId')
    .set('itemAmount')
    .set('itemPrice')
    .set('itemNum')
    .set('createTime')
    .set('updateTime')
    .set('merchantDiscount')
    .set('platformDiscount')
    .set('platformId')
    .set('prescription')
    .set('specification')
    .set('agentOperate')
    .set('isInstea')
    .set('isOos')
    .set('oosNum')
    .set('editType')
    .set('remark')
    .set('pictureUrl')
    .set('tenantId')
    .set('salesMethod')
    .set('gnum')
    .set('actualPrice')
    .set('officeName')
    .set('taxFcy');
}

/**
 * 提交添加
 */
TempSoItemInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/tempSoItem/add", function(data){
        Feng.success("添加成功!");
        window.parent.TempSoItem.table.refresh();
        TempSoItemInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.tempSoItemInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
TempSoItemInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/tempSoItem/update", function(data){
        Feng.success("修改成功!");
        window.parent.TempSoItem.table.refresh();
        TempSoItemInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.tempSoItemInfoData);
    ajax.start();
}

$(function() {

});
