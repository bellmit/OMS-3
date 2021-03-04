/**
 * 退款单管理管理初始化
 */
var RefundOrder = {
    id: "RefundOrderTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};


// /**
//  * 格式化退款单号列
//  * @param value
//  * @param row
//  * @param index
//  * @returns {string}
//  */
// function formatRefundOrder(value, row, index) {
//     return [//"+value+"
//         "<a onclick=\"openRefundOrderModal('" + row.code + "')\">" + value + "</a>"
//     ].join("''")
// }

/**
 * 打开退款单详情页
 * @param value
 */
function openRefundOrderModal(value) {
    var index = layer.open({
        type: 2,
        title: '退款单详情',
        area: ['90%', '90%'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/refundOrder/detail/' + value
    });
    this.layerIndex = index;
}


// /**
//  * 格式化退货单号列
//  * @param value
//  * @param row
//  * @param index
//  * @returns {string}
//  */
// function formatReturnOrder(value, row, index) {
//     return [//"+value+"
//         "<a onclick=\"openReturnOrderModal('" + row.code + "')\">" + value + "</a>"
//     ].join("''")
// }

/**
 * 打开退货单详情页
 * @param value
 */
function openReturnOrderModal(value) {
    var index = layer.open({
        type: 2,
        title: '退货单详情',
        area: ['90%', '90%'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/grfHeader/detail/' + value
    });
    this.layerIndex = index;
}

/**
 * 格式化平台订单列
 * @param value
 * @param row
 * @param index
 * @returns {string}
 */
function formatPlantFormOrder(value, row, index) {
    return [//"+value+"
        "<a onclick=\"openPlantFormModal('" + row.asnCode + "')\">" + value + "</a>"
    ].join("''")
}

/**
 * 打开平台订单详情页
 * @param value
 */
function openPlantFormModal(value) {
    var index = layer.open({
        type: 2,
        title: '平台订单详情',
        area: ['90%', '90%'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/tempSo/tempSo_update/' + value
    });
    this.layerIndex = index;
}

/**
 * 导出订单
 */
RefundOrder.openExcelIn = function () {
    var index = layer.open({
        title: 'Excel导出提示信息',
        btn: ['关闭'],
        fix: false, //不固定
        maxmin: true,
        content: "您导出的数据已加入导出队列," +
            "请记好导出编号【9527,可通过导出编号在" +
            "<font color='red'>导出任务管理</font>" +
            "里查询和下载导出文件。"
    });
    RefundOrder.layerIndex = index;
}
/**
 * 初始化表格的列
 */
RefundOrder.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: '退款单号', field: 'code', visible: true,  align: 'center', valign: 'middle'},
        {title: '退货单号', field: 'grfCode', visible: true,  align: 'center', valign: 'middle'},
        {title: '平台订单号', field: 'originalCode', visible: true,  align: 'center', valign: 'middle'},
        {title: '渠道', field: 'platformName', visible: true, align: 'center', valign: 'middle' , class: 'W80'},
        {title: '商家', field: 'merchantName', visible: true, align: 'center', valign: 'middle',class: 'W120'},
        {title: '店铺名称', field: 'shopName', visible: true, align: 'center', valign: 'middle',class: 'W120'},
        {title: '退款类型', field: 'typeName', visible: true, align: 'center', valign: 'middle'},
        {title: '退款原因', field: 'refundReason', visible: true, align: 'center', valign: 'middle',class: 'W120'},
        {title: '退款方式', field: 'refundMethod', visible: true, align: 'center', valign: 'middle'},
        {title: '退款金额', field: 'refundAmount', visible: true, align: 'center', valign: 'middle'},
        {title: '审核人', field: 'responsiblePerson', visible: true, align: 'center', valign: 'middle'},
        {title: '审核时间', field: 'createTime', visible: true, align: 'center', valign: 'middle',class: 'W120'},
        {title: '退回物流单号', field: 'backCarrierShipCode', visible: true, align: 'center', valign: 'middle',class: 'W120'},
        {title: '卖家备注', field: 'remark', visible: true, align: 'center', valign: 'middle'},
        {title: '买家', field: 'buyerNick', visible: true, align: 'center', valign: 'middle'},
        {title: '确认退款时间', field: 'confirmTime', visible: true, align: 'center', valign: 'middle'},
    ];
};


/**
 * 检查是否选中
 */
RefundOrder.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
        RefundOrder.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加退款单管理
 */
RefundOrder.openAddRefundOrder = function () {
    var index = layer.open({
        type: 2,
        title: '添加退款单管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/refundOrder/refundOrder_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看退款单管理详情
 */
RefundOrder.openRefundOrderDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '退款单管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/refundOrder/refundOrder_update/' + RefundOrder.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除退款单管理
 */
RefundOrder.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/refundOrder/delete", function (data) {
            Feng.success("删除成功!");
            RefundOrder.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("refundOrderId", this.seItem.id);
        ajax.start();
    }
};

/**
 * 重置搜索条件
 */
RefundOrder.reset = function () {
    Feng.resetForm("searchForm");
    RefundOrder.search();
}
/**
 * 查询退款单管理列表
 */
RefundOrder.search = function () {
    var queryData = {};
    queryData['code'] = $("#code").val();
    queryData['platformId'] = $("#platformId").val();
    queryData['merchantId'] = $("#merchantId").val();
    queryData['shopId'] = $("#shopId").val();
    queryData['grfCode'] = $("#grfCode").val();
    queryData['refundAmount'] = $("#refundAmount").val();
    queryData['accountBank'] = $("#accountBank").val();
    queryData['refundAccount'] = $("#refundAccount").val();
    queryData['buyerNick'] = $("#buyerNick").val();
    queryData['buyerNick'] = $("#buyerNick").val();
    queryData['buyerNick'] = $("#buyerNick").val();
    queryData['createTimeBegin'] = $("#createTimeBegin").val();
    queryData['createTimeEnd'] = $("#createTimeEnd").val();
    RefundOrder.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = RefundOrder.initColumn();
    var table = new BSTable(RefundOrder.id, "/refundOrder/list", defaultColunms);
    table.setPaginationType("server");
    RefundOrder.table = table.init();
});
