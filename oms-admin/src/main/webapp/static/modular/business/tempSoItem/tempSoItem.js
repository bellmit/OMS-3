/**
 * 管理初始化
 */
var TempSoItem = {
    id: "TempSoItemTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
TempSoItem.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '平台订单id', field: 'platformOrderCode', visible: true, align: 'center', valign: 'middle'},
            {title: '商家id', field: 'merchantId', visible: true, align: 'center', valign: 'middle'},
            {title: '平台的主品，如果是普通品为NULL,如果是系列品，则为主品id', field: 'platformMainSkuId', visible: true, align: 'center', valign: 'middle'},
            {title: '平台主商品CODE', field: 'platformMainSkuCode', visible: true, align: 'center', valign: 'middle'},
            {title: '平台skid', field: 'platformSkuId', visible: true, align: 'center', valign: 'middle'},
            {title: '如果是普通品为NULL,如果是系列品，则为主品id', field: 'mainSkuId', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'skuId', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'skuCode', visible: true, align: 'center', valign: 'middle'},
            {title: '产品中文名称', field: 'skuName', visible: true, align: 'center', valign: 'middle'},
            {title: '商家商品id（pminfoid）', field: 'pmInfoId', visible: true, align: 'center', valign: 'middle'},
            {title: '商品总金额', field: 'itemAmount', visible: true, align: 'center', valign: 'middle'},
            {title: '商品单价', field: 'itemPrice', visible: true, align: 'center', valign: 'middle'},
            {title: '商品购买数量', field: 'itemNum', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'},
            {title: '更新时间', field: 'updateTime', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'merchantDiscount', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'platformDiscount', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'platformId', visible: true, align: 'center', valign: 'middle'},
            {title: '是否处方药标记 0：否 1：是', field: 'prescription', visible: true, align: 'center', valign: 'middle'},
            {title: '药品规格', field: 'specification', visible: true, align: 'center', valign: 'middle'},
            {title: '是否代运营 0 否 1 是', field: 'agentOperate', visible: true, align: 'center', valign: 'middle'},
            {title: '厂家代发标识 1 代发打标', field: 'isInstea', visible: true, align: 'center', valign: 'middle'},
            {title: '是否缺货', field: 'isOos', visible: true, align: 'center', valign: 'middle'},
            {title: '缺货数量', field: 'oosNum', visible: true, align: 'center', valign: 'middle'},
            {title: '编辑标记(0:原有产品 1：新替换的产品 2 促销，添加赠品)', field: 'editType', visible: true, align: 'center', valign: 'middle'},
            {title: '编辑替换备注', field: 'remark', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'pictureUrl', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'tenantId', visible: true, align: 'center', valign: 'middle'},
            {title: '商品销售方式1 直接销售10 上门换货11淘宝补发12淘宝换货13配件2搭销3赠品4补发5换货6对寄7退转换8检修9原件返', field: 'salesMethod', visible: true, align: 'center', valign: 'middle'},
            {title: '序号,单据行号', field: 'gnum', visible: true, align: 'center', valign: 'middle'},
            {title: '实际成交价', field: 'actualPrice', visible: true, align: 'center', valign: 'middle'},
            {title: '分办名称--百洋', field: 'officeName', visible: true, align: 'center', valign: 'middle'},
            {title: '明细税费', field: 'taxFcy', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
TempSoItem.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        TempSoItem.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加
 */
TempSoItem.openAddTempSoItem = function () {
    var index = layer.open({
        type: 2,
        title: '添加',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/tempSoItem/tempSoItem_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看详情
 */
TempSoItem.openTempSoItemDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/tempSoItem/tempSoItem_update/' + TempSoItem.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除
 */
TempSoItem.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/tempSoItem/delete", function (data) {
            Feng.success("删除成功!");
            TempSoItem.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("tempSoItemId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询列表
 */
TempSoItem.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    TempSoItem.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = TempSoItem.initColumn();
    var table = new BSTable(TempSoItem.id, "/tempSoItem/list", defaultColunms);
    table.setPaginationType("client");
    TempSoItem.table = table.init();
});
