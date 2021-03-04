/**
 * 品牌管理管理初始化
 */
var Brand = {
    id: "BrandTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Brand.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: '', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '品牌名', field: 'brandName', visible: true, align: 'center', valign: 'middle'},
        {title: '品牌公司名', field: 'brandCompanyName', visible: true, align: 'center', valign: 'middle'},
        {title: '品牌别名', field: 'brandAlias', visible: true, align: 'center', valign: 'middle'},
        {title: '编码', field: 'code', visible: true, align: 'center', valign: 'middle'},
        {title: '创建时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Brand.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
        Brand.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加品牌管理
 */
Brand.openAddBrand = function () {
    var index = layer.open({
        type: 2,
        title: '添加品牌管理',
        area: ['50%', '40%'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/brand/brand_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看品牌管理详情
 */
Brand.openBrandDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '品牌管理详情',
            area: ['50%', '40%'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/brand/brand_update/' + Brand.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除品牌管理
 */
Brand.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/brand/delete", function (data) {
            Feng.success("删除成功!");
            Brand.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("brandId", this.seItem.id);
        ajax.start();
    }
};

/**
 * 搜索条件重置
 */
Brand.reset = function () {
    Feng.resetForm("searchForm");
    Brand.search();
}

/**
 * 查询品牌管理列表
 */
Brand.search = function () {
    var queryData = {};
    $("#searchForm input[type='text']").each(
        function () {
            queryData[$(this).attr("id")] = $(this).val();
        }
    );
    Brand.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Brand.initColumn();
    var table = new BSTable(Brand.id, "/brand/list", defaultColunms);
    table.setPaginationType("server");
    Brand.table = table.init();
});
