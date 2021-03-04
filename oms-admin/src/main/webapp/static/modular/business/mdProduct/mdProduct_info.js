/**
 * 初始化商品详情对话框
 */
var MdProductInfoDlg = {
    mdProductInfoData : {},

    validateFields: {
        productCode: {
            validators: {
                notEmpty: {
                    message: '商品编码不能为空'
                }
            }
        }
    ,
        merchantId: {
            validators: {
                notEmpty: {
                    message: '商家不能为空'
                }
            }
        },
        productCname: {
            validators: {
                notEmpty: {
                    message: '商品名称不能为空'
                }
            }
        },
        productType: {
            validators: {
                notEmpty: {
                    message: '商品类型不能为空'
                }
            }
        },
        productTaxRate: {
            validators: {
                notEmpty: {
                    message: '采购税率不能为空'
                }
            }
        },
        salesTax: {
            validators: {
                notEmpty: {
                    message: '销售税率不能为空'
                }
            }
        }

    }
};

MdProductInfoDlg.validate = function () {
    $('#houseFourmId').data("bootstrapValidator").resetForm();
    $('#houseFourmId').bootstrapValidator('validate');
    return $("#houseFourmId").data('bootstrapValidator').isValid();
};

/**
 * 清除数据
 */
MdProductInfoDlg.clearData = function() {
    this.mdProductInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
MdProductInfoDlg.set = function(key, val) {
    this.mdProductInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
MdProductInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
MdProductInfoDlg.close = function() {
    parent.layer.close(window.parent.MdProduct.layerIndex);
}

/**
 * 收集数据
 */
MdProductInfoDlg.collectData = function() {
    this
    .set('id')
    .set('productCode')
    .set('originalCode')
    .set('productCname')
    .set('productSaleType')
    .set('productBrandId')
    .set('createTime')
    .set('createdBy')
    .set('storeCode')
    .set('productListPrice')
    .set('categoryId')
    .set('categoryResponsible')
    .set('stockUpperLimit')
    .set('stockLowerLimit')
    .set('mfid')
    .set('specification')
    .set('ean13')
    .set('length')
    .set('width')
    .set('height')
    .set('weight')
    .set('packageLength')
    .set('packageWidth')
    .set('packageHeight')
    .set('packageVolume')
    .set('packageWeight')
    .set('packageNum')
    .set('avgPackageVolume')
    .set('keepTemperature')
    .set('keepHumidity')
    .set('productQualityAssuranceDay')
    .set('isDeleted')
    .set('unit')
    .set('inPrice')
    .set('volume')
    .set('updateTime')
    .set('updatedBy')
    .set('stdPackQty')
    .set('productType')
    .set('productTaxRate')
    .set('color')
    .set('grossWeight')
    .set('weightWithMaterial')
    .set('categorySearchName')
    .set('productSize')
    .set('placeOfOrigin')
    .set('canPurchase')
    .set('canSale')
    .set('needBatchControl')
    .set('useExpireControl')
    .set('isMustInvoice')
    .set('productIsGift')
    .set('specialType')
    .set('productSname')
    .set('registerNo')
    .set('taxCategoryCode')
    .set('needSnControl')
    .set('salesTax')
    .set('isSent')
    .set('newProductStatus')
    .set('isLarge')
    .set('deliveryFeature')
    .set('tenantId')
    .set('syncStock')
    .set('wholesalePrice')
    .set('storageCondition')
    .set('productLabel')
    .set('productLabel2')
    .set('expand1')
    .set('expand2')
    .set('expand3')
    .set('expand4')
    .set('expand5')
    .set('expand6')
    .set('customTaxRate')
    .set('originCountry')
    .set('codeHs')
    .set('firstQty')
    .set('firstUnit')
    .set('itemUnit')
    .set('merchantId')
    .set('declarePrice')
    .set('virtualCode')
    .set('dosageForm')
    .set('isSpecialDrug')
    .set('productForbiddenSellDay')
    .set('productForbiddenCollectDay')
    .set('productDepartment');
}

/**
 * 提交添加
 */
MdProductInfoDlg.addSubmit = function() {

    if (!this.validate()) {
        return;
    }

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/mdProduct/add", function(data){
        Feng.success("添加成功!");
        window.parent.MdProduct.table.refresh();
        MdProductInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.mdProductInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
MdProductInfoDlg.editSubmit = function() {

    if (!this.validate()) {
        return;
    }

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/mdProduct/update", function(data){
        Feng.success("修改成功!");
        window.parent.MdProduct.table.refresh();
        MdProductInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.mdProductInfoData);
    ajax.start();
}

$(function() {
    Feng.initValidator("houseFourmId", MdProductInfoDlg.validateFields);

    $("#productCodespan").show();
    $("#merchantIdspan").show();
    $("#productCnamespan").show();
    $("#productTypespan").show();
    $("#productTaxRatespan").show();
    $("#salesTaxspan").show();

    $("#storeCode").val($("#storeCodeValue").val());
    $("#productSaleType").val($("#productSaleTypeValue").val());
    $("#useExpireControl").val($("#useExpireControlValue").val());
    $("#productBrandId").val($("#productBrandIdValue").val());
    $("#productType").val($("#productTypeValue").val());
    $("#specialType").val($("#specialTypeValue").val());
    $("#merchantId").val($("#merchantIdValue").val());
    $("#mfid").val($("#mfidValue").val());
    $("#unit").val($("#unitValue").val());
    $("#originCountry").val($("#originCountryValue").val());
    $("#firstUnit").val($("#firstUnitValue").val());
    $("#expand3").val($("#expand3Value").val());
    $("#itemUnit").val($("#itemUnitValue").val());

});
