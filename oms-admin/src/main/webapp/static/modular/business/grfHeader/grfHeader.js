/**
 * 退换货管理初始化
 */
var GrfHeader = {
    id: "GrfHeaderTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};


/**
 * 格式化退货单号列
 * @param value
 * @param row
 * @param index
 * @returns {string}
 */
function formatReturnOrder(value, row, index) {
    return [//"+value+"
        "<a onclick=\"openReturnOrderModal('" + row.code + "')\">" + value + "</a>"
    ].join("''")
}

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
 * 初始化表格的列
 */
GrfHeader.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: '退货单号', field: 'refOriginalCode', visible: true, formatter: formatReturnOrder, align: 'center', valign: 'middle'},
        {title: '订单号', field: 'code', visible: true, align: 'center', valign: 'middle'},
        {title: '平台订单号',field: 'originalCode',visible: true,align: 'center',valign: 'middle'},
        {title: '渠道', field: 'platformName', visible: true, align: 'center', valign: 'middle',class: 'W80'},
        {title: '商家', field: 'merchantName', visible: true, align: 'center', valign: 'middle',class: 'W120'},
        {title: '店铺名称', field: 'shopName', visible: true, align: 'center', valign: 'middle',class: 'W120'},
        {title: '退换货类型', field: 'type', visible: true, align: 'center', valign: 'middle'},
        {title: '买家', field: 'buyerName', visible: true, align: 'center', valign: 'middle',class: 'W80'},
        {title: '买家账号', field: 'buyerNick', visible: true, align: 'center', valign: 'middle'},
        {title: '买家描述', field: 'endUserDesc', visible: true, align: 'center', valign: 'middle',class: 'W150'},
        {title: '申请原因', field: 'endUserApplyReson', visible: true, align: 'center', valign: 'middle'},
        {title: '申请退款总额', field: 'totalAmount', visible: true, align: 'center', valign: 'middle'},
        {title: '是否退运费', field: 'isRefund', visible: true, align: 'center', valign: 'middle'},
        {title: '运费', field: 'deliveryFee', visible: true, align: 'center', valign: 'middle'},
        {title: '退货金额调整备注', field: 'deliveryFee', visible: true, align: 'center', valign: 'middle'},
        {title: '退换货原因说明', field: 'returnReasonDesc', visible: true, align: 'center', valign: 'middle'},
        {title: '退货单状态', field: 'returnReasonDesc', visible: true, align: 'center', valign: 'middle'},
        {title: '审核人', field: 'checkBy', visible: true, align: 'center', valign: 'middle'},
        {title: '卖家备注', field: 'isOtherFee', visible: true, align: 'center', valign: 'middle'},
        {title: '取消备注', field: 'cancelRemark', visible: true, align: 'center', valign: 'middle'},
        {title: '创建时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'},
        {title: '退回物流公司', field: 'backCarrierName', visible: true, align: 'center', valign: 'middle'},
        {title: '退回物流单号', field: 'backCarrierShipCode', visible: true, align: 'center', valign: 'middle'},
        {title: '收货人姓名', field: 'goodReceiverName', visible: true, align: 'center', valign: 'middle'},
        {title: '收货地址_省', field: 'fetchAddress', visible: true, align: 'center', valign: 'middle'},
        {title: '收货地址_市', field: 'fetchAddress', visible: true, align: 'center', valign: 'middle'},
        {title: '收货地址_区', field: 'fetchAddress', visible: true, align: 'center', valign: 'middle'},
        {title: '联系电话', field: 'goodReceiverTel', visible: true, align: 'center', valign: 'middle'},
        {title: '邮编', field: 'goodReceiverTel', visible: true, align: 'center', valign: 'middle'},
        {title: '退货商品种类数', field: 'skuQty', visible: true, align: 'center', valign: 'middle'},
        {title: '退货商品数', field: 'skuQty', visible: true, align: 'center', valign: 'middle'},
    ];
};

/**
 * 检查是否选中
 */
GrfHeader.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
        GrfHeader.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加退换货
 */
GrfHeader.openAddGrfHeader = function () {
    var index = layer.open({
        type: 2,
        title: '添加退换货',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/grfHeader/grfHeader_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看退换货详情
 */
GrfHeader.openGrfHeaderDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '退换货详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/grfHeader/grfHeader_update/' + GrfHeader.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除退换货
 */
GrfHeader.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/grfHeader/delete", function (data) {
            Feng.success("删除成功!");
            GrfHeader.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("grfHeaderId", this.seItem.id);
        ajax.start();
    }
};

/**
 * 重置搜索条件
 */
GrfHeader.reset = function () {
    Feng.resetForm("searchForm");
    GrfHeader.search();
}


/**
 * 查询退换货列表
 */
GrfHeader.search = function () {
    var queryData = {};
    queryData['originalCode'] = $("#originalCode").val();
    queryData['platformId'] = $("#platformId").val();
    queryData['merchantId'] = $("#merchantId").val();
    queryData['shopId'] = $("#shopId").val();
    queryData['code'] = $("#code").val();
    queryData['prescription'] = $("#prescription").val();
    queryData['totalAmount'] = $("#totalAmount").val();
    queryData['deliveryFee'] = $("#deliveryFee").val();
    GrfHeader.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = GrfHeader.initColumn();
    var table = new BSTable(GrfHeader.id, "/grfHeader/list", defaultColunms);
    table.setPaginationType("server");
    GrfHeader.table = table.init();
});
