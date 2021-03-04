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
        {title: '发货仓', field: 'storeCode', visible: true, align: 'left', valign: 'middle', class: 'W120'},
        {title: '商品类型', field: 'productTypeName', visible: true, align: 'left', valign: 'middle'},
        {title: '商品名称', field: 'productCname', visible: true, align: 'left', valign: 'middle', class: 'W150'},
        {title: '规格', field: 'specification', visible: true, align: 'left', valign: 'middle', class: 'W120'},
        {title: '品牌', field: 'brandName', visible: true, align: 'left', valign: 'middle',class: 'W120'},
        {title: '厂商', field: 'mfCompanyName', visible: true, align: 'left', valign: 'middle'},
        {title: '条形码', field: 'ean13', visible: true, align: 'left', valign: 'middle'},
        {title: '库存上限', field: 'stockUpperLimit', visible: true, align: 'left', valign: 'middle'},
        {title: '库存下限', field: 'stockLowerLimit', visible: true, align: 'left', valign: 'middle'},
        {title: '重量', field: 'weight', visible: true, align: 'left', valign: 'middle'},
        {title: '批发价', field: 'wholesalePrice', visible: true, align: 'left', valign: 'middle'},
        {title: '采购税率', field: 'productTaxRate', visible: true, align: 'left', valign: 'middle'},
        {title: '销售税率', field: 'salesTax', visible: true, align: 'left', valign: 'middle'},
        {title: '颜色', field: 'color', visible: true, align: 'left', valign: 'middle'},
        {title: '尺码', field: 'productSize', visible: true, align: 'left', valign: 'middle'},
        {title: '特殊类型', field: 'specialTypeName', visible: true, align: 'left', valign: 'middle'},
        {title: '批准文号', field: 'registerNo', visible: true, align: 'left', valign: 'middle'},
        {title: '虚拟商品编码', field: 'virtualCode', visible: true, align: 'left', valign: 'middle'},
        {title: '保质天数', field: 'productQualityAssuranceDay', visible: true, align: 'left', valign: 'middle'},
        {title: '禁售天数', field: 'productForbiddenSellDay', visible: true, align: 'left', valign: 'middle'},
        {title: '禁收天数', field: 'productForbiddenCollectDay', visible: true, align: 'left', valign: 'middle'}
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
        title: '添加商品',
        area: ['90%', '90%'], //宽高
        fix: false, //不固定
        resize: true,
        maxmin: true, //控制最大化最小化
        closeBtn: 1,// 控制关闭按钮显示
        content: Feng.ctxPath + '/mdProduct/mdProduct_add'
    });
    this.layerIndex = index;
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
    var table = new BSTable(MdProduct.id, "/mdProduct/list", defaultColunms);
    table.setPaginationType("server");
    MdProduct.table = table.init();
});
