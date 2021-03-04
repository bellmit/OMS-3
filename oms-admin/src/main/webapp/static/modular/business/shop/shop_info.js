/**
 * 初始化详情对话框
 */
var ShopInfoDlg = {
    shopInfoData : {},
    validateFields: {
        name: {
            validators: {
                notEmpty: {
                    message: '店铺名称不能为空'
                }
            }
        },
        code: {
            validators: {
                notEmpty: {
                    message: '店铺编码不能为空'
                }
            }
        },
        merchantId: {
            validators: {
                notEmpty: {
                    message: '商家不能为空'
                }
            }
        },
        platformId: {
            validators: {
                notEmpty: {
                    message: '平台不能为空'
                }
            }
        }
    }
};

/**
 * 清除数据
 */
ShopInfoDlg.clearData = function() {
    this.shopInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ShopInfoDlg.set = function(key, val) {
    this.shopInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ShopInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
ShopInfoDlg.close = function() {
    parent.layer.close(window.parent.Shop.layerIndex);
}

/**
 * 收集数据
 */
ShopInfoDlg.collectData = function() {
    this
    .set('id')
    .set('code')
    .set('name')
    .set('nickName')
    .set('merchantId')
    .set('platformId')
    .set('appKey')
    .set('appSecret')
    .set('sessionKey')
    .set('remark')
    .set('isDeleted')
    .set('shopTradeUserName')
    .set('shopTradeUserId')
    .set('createBy')
    .set('createName')
    .set('createTime')
    .set('updateBy')
    .set('updateTime')
    .set('updateName')
    .set('contacter')
    .set('email')
    .set('mobile')
    .set('telephone')
    .set('url')
    .set('businessId')
    .set('cainiaoCode')
    .set('syncStock')
    .set('stockPercentage')
    .set('syncExpress')
    .set('tenantId')
    .set('syncOrder')
    .set('isAgent')
    .set('syncStockTime')
    .set('syncOrderTime')
    .set('isOnline')
    .set('otherSwitch')
    .set('expireTime')
    .set('isSynWms')
    .set('syncCancelOrder');
}

/**
 * 验证数据是否为空
 */
ShopInfoDlg.validate = function () {
    $('#shopInfoForm').data("bootstrapValidator").resetForm();
    $('#shopInfoForm').bootstrapValidator('validate');
    return $("#shopInfoForm").data('bootstrapValidator').isValid();
};

/**
 * 提交添加
 */
ShopInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/shop/add", function(data){
        Feng.success("添加成功!");
        window.parent.Shop.table.refresh();
        ShopInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.shopInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
ShopInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/shop/update", function(data){
        Feng.success("修改成功!");
        window.parent.Shop.table.refresh();
        ShopInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.shopInfoData);
    ajax.start();
}

$(function() {
    Feng.initValidator("shopInfoForm", ShopInfoDlg.validateFields);
    //非空验证带红色星号
    $("#namespan").show();
    $("#codespan").show();
    $("#merchantIdspan").show();
    $("#platformIdspan").show();

    //初始化店铺类型
    $("#isOnline").val($("#isOnlineValue").val());
    //初始化 取消订单通知平台
    $("#syncCancelOrder").val($("#syncCancelOrderValue").val());
    //初始化物流同步
    $("#syncExpress").val($("#syncExpressValue").val());
    //初始化是否启用店铺
    $("#isDeleted").val($("#isDeletedValue").val());
    $("#merchantId").val($("#merchantIdValue").val());
    $("#platformId").val($("#platformIdValue").val());
    $("#syncOrder").val($("#syncOrderValue").val());
});
