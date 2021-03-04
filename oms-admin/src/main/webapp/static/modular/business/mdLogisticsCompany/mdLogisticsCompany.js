/**
 * 快递公司设置管理初始化
 */
var MdLogisticsCompany = {
    id: "MdLogisticsCompanyTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
MdLogisticsCompany.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'id', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: 'code', field: 'code', visible: true, align: 'center', valign: 'middle'},
            {title: 'name', field: 'name', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'},
            {title: '创建人', field: 'createdBy', visible: true, align: 'center', valign: 'middle'},
            {title: '更新时间', field: 'updateTime', visible: true, align: 'center', valign: 'middle'},
            {title: '更新人', field: 'updatedBy', visible: true, align: 'center', valign: 'middle'},
            {title: '是否删除 ', field: 'isDeleted', visible: true, align: 'center', valign: 'middle'},
            {title: 'tenantId', field: 'tenantId', visible: true, align: 'center', valign: 'middle'},
            {title: '物流单号规则', field: 'logisticsCodeRule', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
MdLogisticsCompany.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        MdLogisticsCompany.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加快递公司设置
 */
MdLogisticsCompany.openAddMdLogisticsCompany = function () {
    var index = layer.open({
        type: 2,
        title: '添加快递公司设置',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/mdLogisticsCompany/mdLogisticsCompany_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看快递公司设置详情
 */
MdLogisticsCompany.openMdLogisticsCompanyDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '快递公司设置详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/mdLogisticsCompany/mdLogisticsCompany_update/' + MdLogisticsCompany.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除快递公司设置
 */
MdLogisticsCompany.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/mdLogisticsCompany/delete", function (data) {
            Feng.success("删除成功!");
            MdLogisticsCompany.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("mdLogisticsCompanyId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询快递公司设置列表
 */
MdLogisticsCompany.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    MdLogisticsCompany.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = MdLogisticsCompany.initColumn();
    var table = new BSTable(MdLogisticsCompany.id, "/mdLogisticsCompany/list", defaultColunms);
    table.setPaginationType("client");
    MdLogisticsCompany.table = table.init();
});
