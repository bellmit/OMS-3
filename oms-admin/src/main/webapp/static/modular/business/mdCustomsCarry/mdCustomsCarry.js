/**
 * 管理初始化
 */
var MdCustomsCarry = {
    id: "MdCustomsCarryTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
MdCustomsCarry.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '保税仓 物流公司编码', field: 'logisticsCode', visible: true, align: 'center', valign: 'middle'},
            {title: '物流企业备案名称', field: 'logisCompanyName', visible: true, align: 'center', valign: 'middle'},
            {title: '物流企业海关备案编码', field: 'logisCompanyCode', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'tenantId', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'createTime', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'updateTime', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'createBy', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'updateBy', visible: true, align: 'center', valign: 'middle'},
            {title: '是否停用 0停用 1使用', field: 'isDelete', visible: true, align: 'center', valign: 'middle'},
            {title: '保税仓编码', field: 'werehoueCode', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'carrierId', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
MdCustomsCarry.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        MdCustomsCarry.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加
 */
MdCustomsCarry.openAddMdCustomsCarry = function () {
    var index = layer.open({
        type: 2,
        title: '添加',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/mdCustomsCarry/mdCustomsCarry_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看详情
 */
MdCustomsCarry.openMdCustomsCarryDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/mdCustomsCarry/mdCustomsCarry_update/' + MdCustomsCarry.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除
 */
MdCustomsCarry.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/mdCustomsCarry/delete", function (data) {
            Feng.success("删除成功!");
            MdCustomsCarry.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("mdCustomsCarryId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询列表
 */
MdCustomsCarry.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    MdCustomsCarry.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = MdCustomsCarry.initColumn();
    var table = new BSTable(MdCustomsCarry.id, "/mdCustomsCarry/list", defaultColunms);
    table.setPaginationType("client");
    MdCustomsCarry.table = table.init();
});
