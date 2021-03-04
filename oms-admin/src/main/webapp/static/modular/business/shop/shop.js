/**
 * 管理初始化
 */
var Shop = {
    id: "ShopTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Shop.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: '店铺名称', field: 'name', visible: true, align: 'center', valign: 'middle'},
        {title: '别名', field: 'nickName', visible: true, align: 'center', valign: 'middle'},
        {title: '店铺类型', field: 'isOnlineName', visible: true, align: 'center', valign: 'middle'},
        {title: '平台名称', field: 'platformIdName', visible: true, align: 'center', valign: 'middle'},
        {title: '商家', field: 'merchantIdName', visible: true, align: 'center', valign: 'middle'},
        {title: '启用', field: 'isDeletedName', visible: true, align: 'center', valign: 'middle'},
        {title: '订单下载', field: 'syncOrderName', visible: true, align: 'center', valign: 'middle'},
        {title: '上次订单同步时间', field: 'syncOrderTime', visible: true, align: 'center', valign: 'middle'},
        {title: '上次库存同步时间', field: 'syncStockTime', visible: true, align: 'center', valign: 'middle'},
        {title: '同步快递', field: 'syncExpressName', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Shop.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
        Shop.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加
 */
Shop.openAddShop = function () {
    var index = layer.open({
        type: 2,
        title: '添加',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/shop/shop_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看详情
 */
Shop.openShopDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/shop/shop_update/' + Shop.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除
 */
Shop.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/shop/delete", function (data) {
            Feng.success("删除成功!");
            Shop.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("shopId", this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询条件重置
 */
Shop.reset = function () {
    Feng.resetForm('searchForm');
    Shop.search();
}

/**
 * 查询列表
 */
Shop.search = function () {
    var queryData = {};
    queryData['nameSearch'] = $("#nameSearch").val();
    queryData['platformNameSearch'] = $("#platformNameSearch").val();
    queryData['isDeletedSearch'] = $("#isDeletedSearch").val();
    queryData['isOnlineSearch'] = $("#isOnlineSearch").val();
    queryData['merchantIdSearch'] = $("#merchantIdSearch").val();
    Shop.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Shop.initColumn();
    var table = new BSTable(Shop.id, "/shop/list", defaultColunms);
    table.setPaginationType("client");
    Shop.table = table.init();
});
