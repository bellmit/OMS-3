/**
 * 商品管理初始化
 */
var MdProduct = {
    id: "MdProductTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
MdProduct.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: '', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '商品编码', field: 'productCode', visible: true, align: 'left', valign: 'middle'},
        {title: '商家', field: 'merchantName', visible: true, align: 'left', valign: 'middle', class: 'W120'},
        {title: '商品货号', field: 'originalCode', visible: true, align: 'left', valign: 'middle', class: 'W120'},
        {title: '商品类型', field: 'productTypeName', visible: true, align: 'left', valign: 'middle'},
        {title: '商品名称', field: 'productCname', visible: true, align: 'left', valign: 'middle', class: 'W150'},
        {title: '条形码', field: 'ean13', visible: true, align: 'left', valign: 'middle'},
        {title: '特殊类型', field: 'specialTypeName', visible: true, align: 'left', valign: 'middle'},
        {title: '创建时间', field: 'createTime', visible: true, align: 'left', valign: 'middle'},
        {title: '品类负责人', field: 'categoryResponsible', visible: true, align: 'left', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
MdProduct.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
        MdProduct.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加商品
 */
MdProduct.openAddMdProduct = function () {
    var index = layer.open({
        type: 2,
        title: '添加组合商品',
        area: ['90%', '90%'], //宽高
        fix: false, //不固定
        resize: true,
        maxmin: true, //控制最大化最小化
        closeBtn: 1,// 控制关闭按钮显示
        content: Feng.ctxPath + '/mdComboProduct/mdComboProduct_add'
    });
    MdProduct.layerIndex = index;
};

/**
 * 打开查看商品详情
 */
MdProduct.openMdProductDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '商品详情',
            area: ['90%', '90%'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/mdProduct/mdProduct_update/' + MdProduct.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除商品
 */
MdProduct.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/mdProduct/delete", function (data) {
            Feng.success("删除成功!");
            MdProduct.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("mdProductId", this.seItem.id);
        ajax.start();
    }
};

/**
 * 重置搜索条件
 */
MdProduct.reset = function () {
    Feng.resetForm("searchForm");
    MdProduct.search();
}

/**
 * 查询商品列表
 */
MdProduct.search = function () {
    var queryData = {};
    queryData['productCode'] = $("#productCode").val();
    queryData['originalCode'] = $("#originalCode").val();
    queryData['merchantId'] = $("#merchantId").val();
    queryData['productCname'] = $("#productCname").val();
    MdProduct.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = MdProduct.initColumn();
    var table = new BSTable(MdProduct.id, "/mdComboProduct/list", defaultColunms);
    table.setPaginationType("server");
    MdProduct.table = table.init();
});
