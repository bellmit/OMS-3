/**
 * 订单管理管理初始化
 */
var SoOrder = {
    id: "SoOrderTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1,
    orderStatus:0,
    operationBtn: {title: '操作', field: '', formatter: renderOperation, align: 'center'},//操作列按钮
    viewLogBtn: {title: '查看日志', field: '', formatter: renderViewLog, align: 'center'},//查看日志按钮
};


function statusFunc(value, row, index) {
    if (value == "待审核") {
        return [//"+value+"
            "<a onclick=\"passStatusFunc('" + row.id + "')\">审核</a>"
        ].join("''")
    }
}

function passStatusFunc(value) {

    layer.confirm('确定审核该订单', {
        btn: ['是', '否'] //按钮
    }, function () {
        var ajax = new $ax(Feng.ctxPath + "/soOrder/passStatus", function (data) {
            layer.msg('审核成功', {icon: 1});
//				 Feng.success("审核成功!");
            SoOrder.table.refresh();
        }, function (data) {
            layer.msg('审核失败', {icon: 1});
//		    	 Feng.error("审核失败!" + data.responseJSON.message + "!");
        });
        ajax.set("soOrderId", value);
        ajax.start();
    }, function () {
//		  layer.msg('也可以这样', {
//		    time: 20000, //20s后自动关闭
//		    btn: ['明白了', '知道了']
//		  });
        return
    });


};

//订单详情
function aFormatter(value, row, index) {
    return [//"+value+"
        "<a onclick=\"orderDetail('" + value + "')\">" + value + "</a>"
    ].join("''")
}

/**
 * 订单详情页面
 * @param value
 */
function orderDetail(value) {
    var index = layer.open({
        type: 2,
        title: '订单详情',
        area: ['90%', '90%'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/soOrder/soOrder_update/' + value
    });
    this.layerIndex = index;
};

/**
 * 订单审核操作
 * @param 订单编号
 */
function checkOrder(orderCode) {
    alert("你点击了审核按钮,订单编号为" + orderCode);
}

/**
 * 订单备注操作
 * @param 订单编号
 */
function remarkOrder(orderCode) {
    var index = layer.open({
        type: 2,
        title: '订单备注',
        area: ['600px', '278px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/soOrder/remark/' + orderCode
    });
    this.layerIndex = index;
}

function carryRemark(orderCode) {
    var index = layer.open({
        type: 2,
        title: '回填物流单号',
        area: ['900px', '600px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/soOrder/carryRemark/' + orderCode
    });
    this.layerIndex = index;
}


/**
 * 渲染操作列
 * @param value
 * @param row
 * @param index
 * @returns {string}
 */
function renderOperation(value, row, index) {
    return [//"+value+"
        "<a onclick=\"orderUserDetail('" + row.orderCode + "')\">修改订单信息</a></br></br></br>" +
        "<a onclick=\"remarkOrder('" + row.orderCode + "')\">备注</a></br></br></br>"+
        "<a onclick=\"carryRemark('" + row.orderCode + "')\">回传快递单号</a>"
    ].join("''")
}

/**
 * 渲染查看日志列
 * @param value
 * @param row
 * @param index
 * @returns {string}
 */
function renderViewLog(value, row, index) {
    return [//"+value+"
        "<a onclick=\"viewLogs('" + row.orderCode + "')\">查看日志</a>"
    ].join("''")
}


/**
 * 日志查看操作
 * @param 订单编号
 */
function viewLogs(orderCode) {
    var index = layer.open({
        type: 2,
        title: '查看日志',
        area: ['90%', '90%'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/soOperateLog/logList/' + orderCode
    });
    this.layerIndex = index;
}


/**
 * 修改收货信息
 * @param value
 */
function orderUserDetail(value) {
    var index = layer.open({
        type: 2,
        title: '收货信息',
        area: ['90%', '90%'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/soOrder/soOrderUserUpdate/' + value
    });
    this.layerIndex = index;
};

/**
 * 点击table顶部备注按钮
 */
SoOrder.openRemarkModal = function () {
    if (this.check()) {
        remarkOrder(SoOrder.seItem.orderCode);
    }
}

/**
 * 点击table顶部修改收货信息按钮
 */
SoOrder.orderUserDetail = function () {
    if (this.check()) {
        orderUserDetail(SoOrder.seItem.orderCode);
    }
}

/**
 * 初始化表格的列
 */
SoOrder.initColumn = function () {
    return [
        {field: 'selectItem', radio: false},
        {title: '订单号', field: 'orderCode', visible: true, align: 'center', formatter: aFormatter, valign: 'middle'},
        {title: '平台单号', field: 'originalCode', visible: true, align: 'center', valign: 'middle'},
        {title: '渠道', field: 'orderSourceName', visible: true, align: 'center', valign: 'middle'},
        {title: '商家', field: 'merchantId', visible: true, align: 'center', valign: 'middle',class: 'W120'},
        {title: '店铺名称', field: 'shopName', visible: true, align: 'center', valign: 'middle'},
        {title: '订单来源', field: 'shopName', visible: true, align: 'center', valign: 'middle'},
        {title: '发货仓', field: 'warehouseId', visible: true, align: 'center', valign: 'middle'},
        {title: '订单状态', field: 'orderStatus', visible: true, align: 'center', valign: 'middle'},
        {title: '清关状态 ', field: 'payServiceType', visible: true, align: 'center', valign: 'middle'},
        {title: '下单时间', field: 'orderCreateTime', visible: true, align: 'center', valign: 'middle'},
        {title: '实付金额', field: 'accountPayable', visible: true, align: 'center', valign: 'middle'},
        {title: '商品金额', field: 'productAmount', visible: true, align: 'center', valign: 'middle'},
        {title: '商家优惠', field: 'merchantDiscount', visible: true, align: 'center', valign: 'middle'},
        {title: '平台优惠', field: 'platformDiscount', visible: true, align: 'center', valign: 'middle'},
        {title: '税费', field: 'taxFcy', visible: true, align: 'center', valign: 'middle'},
        {title: '运费', field: 'orderDeliveryFee', visible: true, align: 'center', valign: 'middle'},
        {title: '支付方式 ', field: 'payServiceType', visible: true, align: 'center', valign: 'middle'},
        {title: '支付渠道 ', field: 'payServiceType', visible: true, align: 'center', valign: 'middle'},
//        {title: '付款时间', field: 'orderPaymentConfirmDate', visible: true, align: 'center', valign: 'middle'},
        {title: '交易流水号 ', field: 'thirdPartyPayNo', visible: true, align: 'center', valign: 'middle'},
        {title: '买家备注', field: 'orderRemark', visible: true, align: 'center', valign: 'middle'},
        {title: '卖家备注', field: 'orderCsRemark', visible: true, align: 'center', valign: 'middle'},
        {title: '备注', field: 'orderCsRemark', visible: true, align: 'center', valign: 'middle'},
        {title: '买家ID', field: 'buyerNick', visible: true, align: 'center', valign: 'middle'},
        {title: '买家账号', field: '', visible: true, align: 'center', valign: 'middle'},
        {title: '购买人姓名', field: 'goodReceiverName', visible: true, align: 'center', valign: 'middle'},
        {title: '收货人姓名', field: 'goodReceiverName', visible: true, align: 'center', valign: 'middle'},
        {title: '收货地址_省', field: 'goodReceiverProvince', visible: true, align: 'center', valign: 'middle'},
        {title: '收货地址_市', field: 'goodReceiverCity', visible: true, align: 'center', valign: 'middle'},
        {title: '收货地址_区', field: 'goodReceiverCounty', visible: true, align: 'center', valign: 'middle'},
        {title: '收货地址', field: 'goodReceiverAddress', visible: true, align: 'center', valign: 'middle'},
        {title: '联系电话', field: 'goodReceiverMobile', visible: true, align: 'center', valign: 'middle'},
        {title: '邮编', field: '', visible: true, align: 'center', valign: 'middle'},
        {title: '配送方式', field: 'deliveryMethodType', visible: true, align: 'center', valign: 'middle'},
        {title: '物流公司', field: 'deliverySupplierName', visible: true, align: 'center', valign: 'middle'},
        {title: '物流单号', field: 'merchantExpressNbr', visible: true, align: 'center', valign: 'middle'},
        {title: '购买人邮箱', field: '', visible: true, align: 'center', valign: 'middle'},
        {title: '确认时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'},
        {title: '结束时间', field: 'orderFinishedTime', visible: true, align: 'center', valign: 'middle'},
        {title: '发票类型', field: 'orderNeedInvoice', visible: true, align: 'center', valign: 'middle'},
        {title: '发票抬头', field: 'invoiceTitle', visible: true, align: 'center', valign: 'middle'},
        {title: '发票内容类型', field: '', visible: true, align: 'center', valign: 'middle'},
    ];
};


/**
 * 检查是否选中
 */
SoOrder.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
        SoOrder.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加订单管理
 */
SoOrder.openAddSoOrder = function () {
    var index = layer.open({
        type: 2,
        title: '添加订单管理',
        area: ['90%', '90%'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/soOrder/soOrder_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看订单管理详情
 */
SoOrder.openSoOrderDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '订单管理详情',
            area: ['90%', '90%'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/soOrder/soOrder_update/' + SoOrder.seItem.id
        });
        this.layerIndex = index;
    }
};

///**
// * 删除订单管理
// */
//SoOrder.delete = function () {
//    if (this.check()) {
//        var ajax = new $ax(Feng.ctxPath + "/soOrder/delete", function (data) {
//            Feng.success("删除成功!");
//            SoOrder.table.refresh();
//        }, function (data) {
//            Feng.error("删除失败!" + data.responseJSON.message + "!");
//        });
//        ajax.set("soOrderId", this.seItem.id);
//        ajax.start();
//    }
//};

/**
 * 导入订单
 */
SoOrder.openImportFIle = function () {
    var index = layer.open({
        type: 2,
        title: '订单导入',
        area: ['400px', '196px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/soOrder/virtual/import'
    });
    this.layerIndex = index;
}

function submit() {
    $('#fileInFormId').submit()
}

/**
 * 取消订单
 */
SoOrder.cancelOrder = function (data) {
    if (this.check() == 0) {
        Feng.info("请先选中表格中的某一记录！");
    } else {
        var selectedId = $('#' + this.id).bootstrapTable('getSelections');
        if(selectedId.length > 1){
            Feng.info("请选择一条记录进行取消处理！");
            return;
        }
        var ajax = new $ax(Feng.ctxPath + "/soOrder/cancelOrder", function (data) {
            layer.msg('取消成功', {icon: 1});
            SoOrder.table.refresh();
        }, function (data) {
            layer.msg('取消失败', {icon: 1});
        });
        ajax.set("soOrderCode", selectedId[0].orderCode);
        ajax.set("cancelType", data);
        ajax.start();
    }
};



/**
 * 导入订单
 */
function openExcelIn() {
	var index = layer.open({
        type: 1,
        skin: 'layui-layer-rim', //加上边框
        area: ['420px', '240px'], //宽高
        //content: '<form action="/soOrder/excelIn" method="post" enctype="multipart/form-data">'+
        //'<input type="file" name="file" value="选择jar包"/><input type="submit"  value="保存"/></form>'
        content: '<div class="ibox float-e-margins">' +
            '<div class="ibox-content">' +
            '<div class="form-horizontal">' +
            ' <div class="row">' +
            '  <div class="col-sm-12">' +
            '     <div class="input-group">' +
            '    <form id = "fileInFormId" method="post" enctype="multipart/form-data">' +
//            '    <form action="'+Feng.ctxPath+'/soOrder/excelIn" id = "fileInFormId" method="post" enctype="multipart/form-data">' +
            '	  <input type="file" name="file" value="选择jar包"/>' +
            ' </form>' +
            '  <a href="'+Feng.ctxPath+'/soOrder/excelOut?type=0" class="a-download-modal">下载模板</a>' +
            ' </div>' +
            ' </div>' +
            ' </div>' +
            '<div  class="row-tip"  >' +
            '   请将导出的订单文件填写好物流信息，再导入系统' +
            ' </div>' +
            ' <div class="row btn-group-m-t">' +
            '  <div class="col-sm-12" style="text-align: center;margin-top: 10px">' +
            '<button type="button" class="btn btn-success" name="确定" id="ensure"  onclick="set()"><i class="fa fa-check"></i>&nbsp;确定</button>' +
            '  </div>' +
            '</div>' +
            ' </div>' +
            ' </div>' +
            '</div>'
    });
    this.layerIndex = index;
}

function set(){
	$("#fileInFormId").ajaxSubmit({
		type:"post",
		url:Feng.ctxPath+"/soOrder/excelIn",
		dataType:"json",
			success:function(data){
			//墨绿深蓝风
				layer.close(layerIndex);
				if(data.type){
					layAlert(data.successMessage)
				}else{
					layAlert(data.failResion)
				}
			}, //end success
			error:function(){
				layer.close(layerIndex);
				layer.msg('系统异常', {icon: 1});
			}
		}); //end ajaxSubmit
}


/**
 * 导出订单
 */
function openExcelOut() {
    layer.confirm('信息确认', {
        btn: ['下载模板', '导出数据'] //按钮
    }, function () {
        location.href = "/oms/soOrder/excelOut?type=0";
    }, function () {
        location.href = "/oms/soOrder/excelOut?type=1";
        return
    });
}

/**
 * 虚拟订单管理--导出
 */
SoOrder.virtualOrderExport = function () {
    var queryData = {};
    queryData['platformOrderCode'] = $("#platformOrderCodeSearch").val();
    queryData['platformIdSearch'] = $("#platformIdSearch").val();
    queryData['merchantIdSearch'] = $("#merchantIdSearch").val();
    queryData['shopIdSearch'] = $("#shopIdSearch").val();
    queryData['buyerName'] = $("#buyerName").val();
    queryData['buyerNickId'] = $("#buyerNickId").val();
    queryData['receiverMobile'] = $("#receiverMobile").val();
    queryData['createTimeSearchBegin'] = $("#createTimeSearchBegin").val();
    queryData['createTimeSearchEnd'] = $("#createTimeSearchEnd").val();

    queryData['payTimeSearchBegin'] = $("#payTimeSearchBegin").val();
    queryData['payTimeSearchEnd'] = $("#payTimeSearchEnd").val();

    queryData['outTimeSearchBegin'] = $("#outTimeSearchBegin").val();
    queryData['outTimeSearchEnd'] = $("#outTimeSearchEnd").val();

    queryData['finishTimeSearchBegin'] = $("#finishTimeSearchBegin").val();
    queryData['finishTimeSearchEnd'] = $("#finishTimeSearchEnd").val();

    queryData['goodsName'] = $("#goodsName").val();
    queryData['goodsCode'] = $("#goodsCode").val();
    queryData['orderRemark'] = $("#orderRemark").val();
    queryData['csRemark'] = $("#csRemark").val();
    queryData['wareHouseId'] = $("#wareHouseId").val();
    queryData['deliveryMethodType'] = $("#deliveryMethodType").val();
    queryData['supplierId'] = $("#supplierId").val();

    var ajax = new $ax(Feng.ctxPath + "/soOrder/exportVirtualOrder/0/" + SoOrder.orderStatus, function (data) {
        var index = layer.open({
            title: 'Excel导出提示信息',
            btn: ['关闭'],
            fix: false, //不固定
            maxmin: true,
            content: "您导出的数据已加入导出队列," +
            "请记好导出编号【" + data + ",可通过导出编号在" +
            "<font color='red'>导出任务管理</font>" +
            "里查询和下载导出文件。"
        });
//	        window.parent.BWarehouse.table.refresh();
//	        BWarehouseInfoDlg.close();
    }, function (data) {
        Feng.error("导出失败!请联系管理员!");
    });
    ajax.set(queryData);
    ajax.start();
}


/**
 * 重置搜索条件
 */
SoOrder.reset = function () {
    Feng.resetForm("searchForm");
    SoOrder.search();
}

/**
 * 查询订单管理列表
 */
SoOrder.search = function () {
    var queryData = {};
    queryData['platformOrderCode'] = $("#platformOrderCodeSearch").val();

    queryData['platformIdSearch'] = $("#platformIdSearch").val();
    queryData['merchantIdSearch'] = $("#merchantIdSearch").val();
    queryData['shopIdSearch'] = $("#shopIdSearch").val();
    queryData['buyerName'] = $("#buyerName").val();
    queryData['buyerNickId'] = $("#buyerNickId").val();
    queryData['receiverMobile'] = $("#receiverMobile").val();
    queryData['createTimeSearchBegin'] = $("#createTimeSearchBegin").val();
    queryData['createTimeSearchEnd'] = $("#createTimeSearchEnd").val();
    queryData['payTimeSearchBegin'] = $("#payTimeSearchBegin").val();
    queryData['payTimeSearchEnd'] = $("#payTimeSearchEnd").val();
    queryData['outTimeSearchBegin'] = $("#outTimeSearchBegin").val();
    queryData['outTimeSearchEnd'] = $("#outTimeSearchEnd").val();
    queryData['finishTimeSearchBegin'] = $("#finishTimeSearchBegin").val();
    queryData['finishTimeSearchEnd'] = $("#finishTimeSearchEnd").val();
    queryData['goodsName'] = $("#goodsName").val();
    queryData['goodsCode'] = $("#goodsCode").val();
    queryData['orderRemark'] = $("#orderRemark").val();
    queryData['csRemark'] = $("#csRemark").val();
    queryData['wareHouseId'] = $("#wareHouseId").val();
    queryData['deliveryMethodType'] = $("#deliveryMethodType").val();
    queryData['supplierId'] = $("#supplierId").val();
    SoOrder.table.refresh({query: queryData});

};

/**
 * 初始化table data
 */
SoOrder.initTable = function () {
    //获取订单类型 1 实单 ，0 虚单
    var warehouseOrderType = $('#warehouseOrderType').val();
    if (warehouseOrderType === '0') {
        //tab
        $('#soOrder-nav').hide();
        $('#soVirtualOrder-nav').show();
        //btn
        $('#soOrderBtn').hide();
        $('#soVirtualOrderBtn').show();
        //search condition 代发供应商
        $('#substituteSupplier').show();
    }
    var defaultColunms = SoOrder.initColumn();
    var tabName = window.sessionStorage.getItem("soVirtualOrder-tab");
    // var orderStatus = 4;
    if (tabName) {
        switch (tabName) {
        	case '全部':
	            defaultColunms.splice(2, 0,  SoOrder.viewLogBtn);
	           // $('#exportOrder').show();
	          //  $('#updateAddress').show();
	          //  $('#checkOrder').show();
	          //  $('#remarkOrder').show();
	            $('#virtualCancelOrder').hide();
	            SoOrder.orderType = 0;
	            break;
            case '待审核':
                defaultColunms.splice(2, 0, SoOrder.operationBtn, SoOrder.viewLogBtn);
                $('#importOrder').show();
                $('#updateVirtualAddress').show();
                $('#remarkVirtualOrder').show();
                SoOrder.orderStatus = 4;
                break;
            case '挂起':
                defaultColunms.splice(2, 0, SoOrder.viewLogBtn);
                $('#updateVirtualAddress').hide();
                $('#remarkVirtualOrder').hide();
                SoOrder.orderStatus = 30;
                break;
            case '已取消':
                defaultColunms.splice(2, 0, SoOrder.viewLogBtn);
                $('#updateVirtualAddress').hide();
                $('#remarkVirtualOrder').hide();
                $('#virtualCancelOrder').hide();
                SoOrder.orderStatus = 99;
                break;
            case '异常':
                defaultColunms.splice(2, 0, SoOrder.operationBtn,SoOrder.viewLogBtn);
                $('#updateVirtualAddress').hide();
                $('#remarkVirtualOrder').hide();
                SoOrder.orderStatus = 15;
                break;
            case '已发货':
            	
                defaultColunms.splice(2, 0, SoOrder.viewLogBtn);
                $('#updateVirtualAddress').hide();
                $('#remarkVirtualOrder').hide();
                $('#virtualCancelOrder').hide();
                SoOrder.orderStatus = 20;
                break;
            case '已完成':
                defaultColunms.splice(2, 0, SoOrder.viewLogBtn);
                $('#updateVirtualAddress').hide();
                $('#remarkVirtualOrder').hide();
                $('#virtualCancelOrder').hide();
                SoOrder.orderStatus = 35;
                break;
        }
        $("#soVirtualOrder-nav a[name=" + tabName + "]").tab('show');

    } else {
        $('#soVirtualOrder-nav a:first').tab('show');
    }

    var table = new BSTable(SoOrder.id, "/soOrder/list/0/" + SoOrder.orderStatus, defaultColunms);
    table.setPaginationType("server");
    SoOrder.table = table.init();
}


function getUrlParam(name) { 
	  var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i"); 
	  var r = window.location.search.substr(1).match(reg); 
	  if (r != null) return decodeURI(r[2]); return null; 
}

function layAlert(data){
	layer.open({
        title: 'EXCEL提示信息',
        btn: ['关闭'],
        fix: false, //不固定
        maxmin: true,
        content: ""+data
    });
}

$(function () {
    SoOrder.initTable();
    $('#soVirtualOrder-nav a').click(function (e) {
        e.preventDefault();
        $(this).tab('show');
        window.sessionStorage.setItem("soVirtualOrder-tab", $(this).context.innerText);
        setTimeout(function () {
            window.location.reload(true);
        }, 50)
    });
});
