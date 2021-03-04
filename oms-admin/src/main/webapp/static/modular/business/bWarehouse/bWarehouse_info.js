/**
 * 初始化仓库管理详情对话框
 */
var BWarehouseInfoDlg = {
    bWarehouseInfoData : {},
	validateFields: {
		warehouseName: {
	        validators: {
	            notEmpty: {
	                message: '仓库名称不能为空'
	            }
	        }
	    },
	    tradeType: {
	        validators: {
	            notEmpty: {
	                message: '贸易类型不能为空'
	            }
	        }
	    },
	    provinceId: {
	        validators: {
	            notEmpty: {
	                message: '省不能为空'
	            }
	        }
	    },
	    cityId: {
	        validators: {
	            notEmpty: {
	                message: '市不能为空'
	            }
	        }
	    },
	    countyId: {
	        validators: {
	            notEmpty: {
	                message: '区不能为空'
	            }
	        }
	    },
	    shippingMode: {
	        validators: {
	            notEmpty: {
	                message: '发货平台不能为空'
	            }
	        }
	    },
	    mobile: {
	        validators: {
	            notEmpty: {
	                message: '手机号不能为空'
	            }
	        }
	    },
	    contactor: {
	        validators: {
	            notEmpty: {
	                message: '联系人不能为空'
	            }
	        }
	    },
	    addressName: {
	        validators: {
	            notEmpty: {
	                message: '地址不能为空'
	            }
	        }
	    },
	    isRealWarehouse: {
	        validators: {
	            notEmpty: {
	                message: '实体库不能为空'
	            }
	        }
	    },
	    warehouseType: {
	        validators: {
	            notEmpty: {
	                message: '类型不能为空'
	            }
	        }
	    }
	    
	}
};

/**
 * 清除数据
 */
BWarehouseInfoDlg.clearData = function() {
    this.bWarehouseInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BWarehouseInfoDlg.set = function(key, val) {
    this.bWarehouseInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BWarehouseInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
BWarehouseInfoDlg.close = function() {
    parent.layer.close(window.parent.BWarehouse.layerIndex);
}

/**
 * 收集数据
 */
BWarehouseInfoDlg.collectData = function() {
	this
    .set('id')
    .set('warehouseName')
    .set('provinceId')
    .set('countyId')
    .set('countryId')
    .set('cityId')
    .set('addressName')
    .set('distributionCenterId')
    .set('fax')
    .set('phone')
    .set('updateTime')
    .set('updatedBy')
    .set('createTime')
    .set('createdBy')
    .set('isDeleted')
    .set('mobile')
    .set('contactor')
    .set('warehouseType')
    .set('isSent')
    .set('status')
    .set('isRealWarehouse')
    .set('isEnable')
    .set('storageType')
    .set('stockType')
    .set('functionType')
    .set('code')
    .set('carrierId')
    .set('shippingMode')
    .set('tenantId')
    .set('returnWarehouseId')
    .set('tradeType')
    .set('areaCode');
}

BWarehouseInfoDlg.validate = function () {
    $('#houseFourmId').data("bootstrapValidator").resetForm();
    $('#houseFourmId').bootstrapValidator('validate');
    return $("#houseFourmId").data('bootstrapValidator').isValid();
};

/**
 * 提交添加
 */
BWarehouseInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();
    
    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/bWarehouse/add", function(data){
        Feng.success("添加成功!");
        window.parent.BWarehouse.table.refresh();
        BWarehouseInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.bWarehouseInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
BWarehouseInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();
    
    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/bWarehouse/update", function(data){
        Feng.success("修改成功!");
        window.parent.BWarehouse.table.refresh();
        BWarehouseInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.bWarehouseInfoData);
    ajax.start();
}

$(function() {
	
	Feng.initValidator("houseFourmId", BWarehouseInfoDlg.validateFields);
	
	 $("#warehouseNamespan").show();
	 $("#isRealWarehousespan").show();
	 $("#tradeTypespan").show();
	 $("#provinceIdspan").show();
	 $("#cityIdspan").show();
	 $("#countyIdspan").show();
	 $("#shippingModespan").show();
	 $("#mobilespan").show();
	 $("#addressNamespan").show();
	 $("#warehouseTypespan").show();
	 $("#contactorspan").show();
	 
	 $("#warehouseType").val($("#typeValue").val());
	 $("#storageType").val($("#storageClassValue").val());
	 $("#functionType").val($("#functionTypeValue").val());
	 $("#isRealWarehouse").val($("#isEntityLibraryValue").val());
	 $("#tradeType").val($("#patternTradeValue").val());
	 $("#provinceId").val($("#provinceValue").val());
	 $("#cityId").val($("#cityValue").val());
	 $("#countId").val($("#districtValue").val());
	 $("#shippingMode").val($("#shippingModeValue").val());
	 
	 

});
