/**
 * 管理初始化
 */
var PmWarehouseStock = {
    id: "PmWarehouseStockTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
PmWarehouseStock.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: '商家id', field: 'merchantId', visible: true, align: 'center', valign: 'middle'},
        {title: '仓库id', field: 'warehouseId', visible: true, align: 'center', valign: 'middle'},
        {title: '商品', field: 'pmInfoId', visible: true, align: 'center', valign: 'middle'},

        {title: '商品名称', field: 'productName', visible: true, align: 'center', valign: 'middle'},
        {title: '规格', field: 'specification', visible: true, align: 'center', valign: 'middle'},
        {title: '厂家', field: '', visible: true, align: 'center', valign: 'middle'},
        {title: '关联编码', field: 'originalCode', visible: true, align: 'center', valign: 'middle'},
        {title: '商品类目', field: '', visible: true, align: 'center', valign: 'middle'},
        {title: '良品', field: 'realStockNum', visible: true, align: 'center', valign: 'middle'},
        {title: '良品冻结', field: 'realFrozenStockNum', visible: true, align: 'center', valign: 'middle'},
        {title: '良品锁定', field: 'lockStockNum', visible: true, align: 'center', valign: 'middle'},
        {title: '坏品', field: 'damageStockNum', visible: true, align: 'center', valign: 'middle'},
        {title: '锁定坏品', field: 'lockDemageStockNum', visible: true, align: 'center', valign: 'middle'},
        {title: '坏品冻结', field: 'frozenDamageStockNum', visible: true, align: 'center', valign: 'middle'},

        {title: '待上架', field: 'wtPutawayQty', visible: true, align: 'center', valign: 'middle'},

        {title: '箱规', field: 'stdPackQty', visible: true, align: 'center', valign: 'middle'},
        {title: '条码', field: 'ean', visible: true, align: 'center', valign: 'middle'},
        {title: '日均销量(最近3个月)', field: '', visible: true, align: 'center', valign: 'middle'},
        {title: '最近7天销量', field: '', visible: true, align: 'center', valign: 'middle'},
        {title: '最近1个月销量', field: '', visible: true, align: 'center', valign: 'middle'},
        {title: '日均退货(最近3个月)', field: '', visible: true, align: 'center', valign: 'middle'},
        {title: '最近7天退货', field: '', visible: true, align: 'center', valign: 'middle'},
        {title: '最近一个月退货', field: '', visible: true, align: 'center', valign: 'middle'},
        {title: '最近采购供应商', field: '', visible: true, align: 'center', valign: 'middle'},
        {title: '最近采购价', field: '', visible: true, align: 'center', valign: 'middle'},
        {title: '最近销售出库时间', field: 'lastLeavingWhTime', visible: true, align: 'center', valign: 'middle'},
        {title: '成本价', field: '', visible: true, align: 'center', valign: 'middle'},
        {title: '记录更新的时间', field: 'updateTime', visible: true, align: 'center', valign: 'middle'},
    ];
};

/**
 * 检查是否选中
 */
PmWarehouseStock.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
        PmWarehouseStock.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加
 */
PmWarehouseStock.openAddPmWarehouseStock = function () {
    var index = layer.open({
        type: 2,
        title: '添加',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/pmWarehouseStock/pmWarehouseStock_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看详情
 */
PmWarehouseStock.openPmWarehouseStockDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/pmWarehouseStock/pmWarehouseStock_update/' + PmWarehouseStock.seItem.id
        });
        this.layerIndex = index;
    }
};


/**
 * 搜索条件重置
 */
PmWarehouseStock.reset = function () {
    Feng.resetForm("searchForm");
    PmWarehouseStock.search();
}
/**
 * 查询列表
 */
PmWarehouseStock.search = function () {
    var queryData = {};
    queryData['productCodeId'] = $("#productCodeId").val();
    PmWarehouseStock.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = PmWarehouseStock.initColumn();
    var table = new BSTable(PmWarehouseStock.id, "/pmWarehouseStock/list", defaultColunms);
    table.setPaginationType("server");
    PmWarehouseStock.table = table.init();
});
