/**
 * 采购单管理管理初始化
 */
var Asn = {
    id: "AsnTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Asn.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '制单人', field: 'createdBy', visible: true, align: 'center', valign: 'middle'},
        {title: '采购单编码', field: 'poCode', visible: true, align: 'center', valign: 'middle'},
        {title: '仓库', field: 'warehouseId', visible: true, align: 'center', valign: 'middle'},
        {title: '商家', field: 'merchantId', visible: true, align: 'center', valign: 'middle'},
        {title: '供应商', field: 'supplierId', visible: true, align: 'center', valign: 'middle'},
        {title: '创立日期', field: 'createTime', visible: true, align: 'center', valign: 'middle'},
        {title: 'ASN单据号', field: 'asnCode', visible: true, align: 'center', valign: 'middle'},
        {title: '采购类型', field: 'asnType', visible: true, align: 'center', valign: 'middle'},
        {title: 'ASN单状态', field: 'status', visible: true, align: 'center', valign: 'middle'},
        {title: '确认收货时间', field: 'verifiedReceiveTime', visible: true, align: 'center', valign: 'middle'},
        {title: '开始收货时间', field: 'startReceiveTime', visible: true, align: 'center', valign: 'middle'},
        {title: '完成收货时间', field: 'finishReceiveTime', visible: true, align: 'center', valign: 'middle'},
        {title: '预计发货数量', field: 'expectedValidNum', visible: true, align: 'center', valign: 'middle'},
        {title: '实收数量', field: 'receivedNum', visible: true, align: 'center', valign: 'middle'},
        {title: '预计到货日期', field: 'exptArriveTime', visible: true, align: 'center', valign: 'middle'},
        {title: '产品状态', field: 'prodState', visible: true, align: 'center', valign: 'middle'},
        {title: '配货类型', field: 'deliveryType', visible: true, align: 'center', valign: 'middle'},
        {title: '产品效期类型', field: 'periodValid', visible: true, align: 'center', valign: 'middle'},
        {title: '订单过期日', field: 'validDate', visible: true, align: 'center', valign: 'middle'},
        {title: '退换货单编码', field: 'grfCode', visible: true, align: 'center', valign: 'middle'},
        {title: '退换货类型', field: 'grfType', visible: true, align: 'center', valign: 'middle'},
        {title: '订单号', field: 'soCode', visible: true, align: 'center', valign: 'middle'},
        {title: '发货单编码', field: 'doCode', visible: true, align: 'center', valign: 'middle'},
        {title: '调出仓库', field: 'fromWarehouseId', visible: true, align: 'center', valign: 'middle'},
        {title: '实收坏品数', field: 'damageQuality', visible: true, align: 'center', valign: 'middle'},
        {title: '实收好品数', field: 'normalQuality', visible: true, align: 'center', valign: 'middle'},
        {title: '外部发货单号', field: 'outerOrderCode', visible: true, align: 'center', valign: 'middle'},
        {title: '外部订单类型', field: 'outerOrderType', visible: true, align: 'center', valign: 'middle'},
        {title: '平台', field: 'platformId', visible: true, align: 'center', valign: 'middle'},
    ];
};

/**
 * 检查是否选中
 */
Asn.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
        Asn.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加采购单管理
 */
Asn.openAddAsn = function () {
    var index = layer.open({
        type: 2,
        title: '添加采购单管理',
        area: ['90%', '90%'], //宽高
        fix: false, //不固定
        resize: true,
        maxmin: true, //控制最大化最小化
        closeBtn: 1,// 控制关闭按钮显示
        content: Feng.ctxPath + '/asn/asn_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看采购单管理详情
 */
Asn.openAsnDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '采购单管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/asn/asn_update/' + Asn.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除采购单管理
 */
Asn.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/asn/delete", function (data) {
            Feng.success("删除成功!");
            Asn.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("asnId", this.seItem.id);
        ajax.start();
    }
};

/**
 * 搜索条件重置
 */
Asn.reset = function () {
    Feng.resetForm("searchForm");
    Asn.search();
}

/**
 * 查询采购单管理列表
 */
Asn.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Asn.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Asn.initColumn();
    var table = new BSTable(Asn.id, "/asn/list", defaultColunms);
    table.setPaginationType("client");
    Asn.table = table.init();
});
