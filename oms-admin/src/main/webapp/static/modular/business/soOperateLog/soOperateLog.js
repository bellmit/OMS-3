/**
 * 管理初始化
 */
var SoOperateLog = {
    id: "SoOperateLogTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
SoOperateLog.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'soCode', field: 'soCode', visible: true, align: 'center', valign: 'middle'},
            {title: '操作人', field: 'operator', visible: true, align: 'center', valign: 'middle'},
            {title: '操作时间', field: 'operationTime', visible: true, align: 'center', valign: 'middle'},
            {title: '操作内容', field: 'remark', visible: true, align: 'center', valign: 'middle'},
            {title: '官网订单号', field: 'platformOrderCode', visible: true, align: 'center', valign: 'middle'}
    ];
};









/**
 * 查询列表
 */
SoOperateLog.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    SoOperateLog.table.refresh({query: queryData});
};

$(function () {
	var queryData = {};
	 queryData['condition'] = $("#condition").val();
	
    var defaultColunms = SoOperateLog.initColumn();
    var table = new BSTable(SoOperateLog.id, "/soOperateLog/list", defaultColunms);
    table.queryParams= queryData;
    table.setPaginationType("client");
    SoOperateLog.table = table.init();
});
