/**
 * 仓库管理管理初始化
 */
var BWarehouse = {
    id: "BWarehouseTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
BWarehouse.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: '仓库名称', field: 'warehouseName', visible: true, align: 'center', valign: 'middle'},
        {title: '省', field: 'provinceName', visible: true, align: 'center', valign: 'middle'},
        {title: '市', field: 'cityName', visible: true, align: 'center', valign: 'middle'},
        {title: '区', field: 'countyName', visible: true, align: 'center', valign: 'middle'},
        {title: '仓库类型', field: 'houseTypeName', visible: true, align: 'center', valign: 'middle'},
        {title: '状态', field: 'statusName', visible: true, align: 'center', valign: 'middle'},
        {title: '外部编码', field: 'code', visible: true, align: 'center', valign: 'middle'},
        {title: '配送商', field: '', visible: true, align: 'center', valign: 'middle'},
        {title: '存储类型', field: 'storageClassName', visible: true, align: 'center', valign: 'middle'},
        {title: '功能类型', field: 'functionTypeName', visible: true, align: 'center', valign: 'middle'},
        {title: '贸易类型', field: 'tradeTypeName', visible: true, align: 'center', valign: 'middle'},
        {title: '是否实体库', field: 'isEntityLibraryName', visible: true, align: 'center', valign: 'middle'},
        {title: '地址', field: 'addressName', visible: true, align: 'center', valign: 'middle'},
        {title: '负责人', field: 'contactor', visible: true, align: 'center', valign: 'middle'},
        {title: '联系电话', field: 'phone', visible: true, align: 'center', valign: 'middle'},
        {title: '手机号', field: 'mobile', visible: true, align: 'center', valign: 'middle'},
        {title: '传真', field: 'fax', visible: true, align: 'center', valign: 'middle'},
        {title: '更新时间', field: 'updateTime', visible: true, align: 'center', valign: 'middle'}

    ];
};

/**
 * 检查是否选中
 */
BWarehouse.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
        BWarehouse.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加仓库管理
 */
BWarehouse.openAddBWarehouse = function () {
    var index = layer.open({
        type: 2,
        title: '添加仓库管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/bWarehouse/bWarehouse_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看仓库管理详情
 */
BWarehouse.openBWarehouseDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '仓库管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/bWarehouse/bWarehouse_update/' + BWarehouse.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除仓库管理
 */
BWarehouse.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/bWarehouse/delete", function (data) {
            Feng.success("删除成功!");
            BWarehouse.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("bWarehouseId", this.seItem.id);
        ajax.start();
    }
};

/**
 * 冻结
 * @param userId
 */
BWarehouse.freezeAccount = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/bWarehouse/freeze", function (data) {
            Feng.success("冻结成功!");
            BWarehouse.table.refresh();
        }, function (data) {
            Feng.error("冻结失败!" + data.responseJSON.message + "!");
        });
        ajax.set("bWarehouseId", this.seItem.id);
        ajax.start();
    }
};

/**
 * 解除冻结
 * @param userId
 */
BWarehouse.unfreeze = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/bWarehouse/unfreeze", function (data) {
            Feng.success("启用成功!");
            BWarehouse.table.refresh();
        }, function (data) {
            Feng.error("启用失败!");
        });
        ajax.set("bWarehouseId", this.seItem.id);
        ajax.start();
    }
}

/**
 * 查询仓库管理列表
 */
BWarehouse.search = function () {
    var queryData = {};
    queryData['houseTypeId'] = $("#houseTypeId").val();
    queryData['houseName'] = $("#houseName").val();
    queryData['storageTypeId'] = $("#storageTypeId").val();
    queryData['functionTypeId'] = $("#functionTypeId").val();
    queryData['tradeTypeId'] = $("#tradeTypeId").val();
    BWarehouse.table.refresh({query: queryData});
};

/**
 * 重置搜索条件
 */
BWarehouse.reset = function () {
    Feng.resetForm('searchForm');
    BWarehouse.search();
}

$(function () {
    var defaultColunms = BWarehouse.initColumn();
    var table = new BSTable(BWarehouse.id, "/bWarehouse/list", defaultColunms);
    table.setPaginationType("client");
    BWarehouse.table = table.init();
});
