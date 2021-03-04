/**
 * 管理初始化
 */
var TempSoItem = {
    id: "TempSoItemTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
TempSoItem.initColumn = function () {
    return [
        {title: '商品编码', field: 'platformSkuId', visible: true, align: 'center', valign: 'middle'},
        {title: '商品名称', field: 'skuName', visible: true, align: 'center', valign: 'middle'},
        {title: '商品编码', field: 'skuCode', visible: true, align: 'center', valign: 'middle'},
        {title: '单价', field: 'itemPrice', visible: true, align: 'center', valign: 'middle'},
        {title: '购买数量', field: 'itemNum', visible: true, align: 'center', valign: 'middle'},
        {title: '商品金额', field: 'itemAmount', visible: true, align: 'center', valign: 'middle'},
        {title: '明细税费', field: 'taxFcy', visible: true, align: 'center', valign: 'middle'},

    ];
};


$(function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    var defaultColunms = TempSoItem.initColumn();
    var table = new BSTable(TempSoItem.id, "/tempSoItem/list", defaultColunms);
    table.queryParams = queryData;
    table.setPaginationType("client");
    table.init();
});
