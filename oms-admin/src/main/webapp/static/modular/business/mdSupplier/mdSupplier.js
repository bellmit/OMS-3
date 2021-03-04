/**
 * 供应商管理管理初始化
 */
var MdSupplier = {
    id: "MdSupplierTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
MdSupplier.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '供应商公司名', field: 'supplierCompanyName', visible: true, align: 'center', valign: 'middle'},
        {title: '联系人', field: 'supplierContactPerson', visible: true, align: 'center', valign: 'middle'},
        {title: '联系电话', field: 'supplierContactPhone', visible: true, align: 'center', valign: 'middle'},
        {title: '联系人email', field: 'supplierContactEmail', visible: true, align: 'center', valign: 'middle'},
        {title: '联系人手机', field: 'supplierContactMobile', visible: true, align: 'center', valign: 'middle'},
        {title: '开户行(支行)', field: 'supplierBankName', visible: true, align: 'center', valign: 'middle'},
        {title: '账号名', field: 'supplierBankAccountName', visible: true, align: 'center', valign: 'middle'},
        {title: '账户', field: 'supplierBankAccountNumber', visible: true, align: 'center', valign: 'middle'},
        {title: '供应商编码', field: 'supplierCode', visible: true, align: 'center', valign: 'middle'},
        {title: '供应商合作状态', field: 'cooperationStatus', visible: true, align: 'center', valign: 'middle'},
        {title: '结算方式', field: 'balanceAccountsType', visible: true, align: 'center', valign: 'middle'},
        {title: '商务办公地址', field: 'merchantOfficeAddress', visible: true, align: 'center', valign: 'middle'},
        {title: '创建人', field: 'createdBy', visible: true, align: 'center', valign: 'middle'},
        {title: '创立日期', field: 'createTime', visible: true, align: 'center', valign: 'middle'},
        {title: '经销方式', field: 'distributionType', visible: true, align: 'center', valign: 'middle'},
        {title: '纳税人识别号', field: 'taxRegNo', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
MdSupplier.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
        MdSupplier.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加供应商管理
 */
MdSupplier.openAddMdSupplier = function () {
    var index = layer.open({
        type: 2,
        title: '添加供应商管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/mdSupplier/mdSupplier_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看供应商管理详情
 */
MdSupplier.openMdSupplierDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '供应商管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/mdSupplier/mdSupplier_update/' + MdSupplier.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除供应商管理
 */
MdSupplier.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/mdSupplier/delete", function (data) {
            Feng.success("删除成功!");
            MdSupplier.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("mdSupplierId", this.seItem.id);
        ajax.start();
    }
};


/**
 * 搜索条件重置
 */
MdSupplier.reset = function () {
    Feng.resetForm("searchForm");
    MdSupplier.search();
}


/**
 * 查询供应商管理列表
 */
MdSupplier.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    MdSupplier.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = MdSupplier.initColumn();
    var table = new BSTable(MdSupplier.id, "/mdSupplier/list", defaultColunms);
    table.setPaginationType("client");
    MdSupplier.table = table.init();
});
