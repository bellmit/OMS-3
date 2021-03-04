/**
 * 商家管理管理初始化
 */
var Merchant = {
    id: "MerchantTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Merchant.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '', field: 'id', visible: false, align: 'center', valign: 'middle'},
            {title: '销售商用户名', field: 'merchantName', visible: true, align: 'center', valign: 'middle'},
            {title: '纳税人识别码', field: 'taxPayerCode', visible: true, align: 'center', valign: 'middle'},
            {title: '商家平台编码', field: 'invoicePlatformCode', visible: true, align: 'center', valign: 'middle'},
            {title: '编码', field: 'code', visible: true, align: 'center', valign: 'middle'},
            {title: '集团商家', field: 'groupMerchantId', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Merchant.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Merchant.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加商家管理
 */
Merchant.openAddMerchant = function () {
    var index = layer.open({
        type: 2,
        title: '添加商家管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/merchant/merchant_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看商家管理详情
 */
Merchant.openMerchantDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '商家管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/merchant/merchant_update/' + Merchant.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除商家管理
 */
Merchant.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/merchant/delete", function (data) {
            Feng.success("删除成功!");
            Merchant.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("merchantId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 搜索条件重置
 */
Merchant.reset = function () {
    Feng.resetForm("searchForm");
    Merchant.search();
}

/**
 * 查询商家管理列表
 */
Merchant.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Merchant.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Merchant.initColumn();
    var table = new BSTable(Merchant.id, "/merchant/list", defaultColunms);
    table.setPaginationType("client");
    Merchant.table = table.init();
});
