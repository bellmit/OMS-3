/**
 * 管理初始化
 */
var MdRegion = {
    id: "MdRegionTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
MdRegion.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '代码', field: 'code', visible: true, align: 'center', valign: 'middle'},
            {title: '名称', field: 'name', visible: true, align: 'center', valign: 'middle'},
            {title: '1:省市自治区;2:城市;3:区县', field: 'type', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'parentName', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'parentCode', visible: true, align: 'center', valign: 'middle'},
            {title: '父区域ID', field: 'parentId', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'fullname', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'createTime', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'updateTime', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'createdBy', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'updatedBy', visible: true, align: 'center', valign: 'middle'},
            {title: '是否启用 1：是 0', field: 'enabled', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'isDeleted', visible: true, align: 'center', valign: 'middle'},
            {title: 'v3接口是否已同步到wms:1,已同步，0未同步', field: 'isSent', visible: true, align: 'center', valign: 'middle'},
            {title: '是否已同步到wms:1,已同步，0未同步(wmsv2拆库)', field: 'dataExchangeFlag', visible: true, align: 'center', valign: 'middle'},
            {title: '别名', field: 'aliasName', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'tenantId', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
MdRegion.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        MdRegion.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加
 */
MdRegion.openAddMdRegion = function () {
    var index = layer.open({
        type: 2,
        title: '添加',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/mdRegion/mdRegion_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看详情
 */
MdRegion.openMdRegionDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/mdRegion/mdRegion_update/' + MdRegion.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除
 */
MdRegion.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/mdRegion/delete", function (data) {
            Feng.success("删除成功!");
            MdRegion.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("mdRegionId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询列表
 */
MdRegion.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    MdRegion.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = MdRegion.initColumn();
    var table = new BSTable(MdRegion.id, "/mdRegion/list", defaultColunms);
    table.setPaginationType("client");
    MdRegion.table = table.init();
});
