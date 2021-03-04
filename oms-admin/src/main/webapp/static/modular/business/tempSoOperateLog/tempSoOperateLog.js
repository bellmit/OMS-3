/**
 * 管理初始化
 */
var TempSoOperateLog = {
    id: "TempSoOperateLogTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
TempSoOperateLog.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
            {title: '单号', field: 'code', visible: true, align: 'center', valign: 'middle'},
            {title: '操作内容', field: 'remark', visible: true, align: 'center', valign: 'middle'},
            {title: '操作时间', field: 'operateTime', visible: true, align: 'center', valign: 'middle'},
            {title: '操作人', field: 'operator', visible: true, align: 'center', valign: 'middle'}
            // {title: '', field: 'tenantId', visible: true, align: 'center', valign: 'middle'},
            // {title: '平台', field: 'platformId', visible: true, align: 'center', valign: 'middle'}
    ];
};



$(function () {
	 var queryData = {};
	 queryData['condition'] = $("#condition").val();
	 
    var defaultColunms = TempSoOperateLog.initColumn();
    var table = new BSTable(TempSoOperateLog.id, "/tempSoOperateLog/list", defaultColunms);
    table.queryParams= queryData;
    table.setPaginationType("client");
    table.init();
});
