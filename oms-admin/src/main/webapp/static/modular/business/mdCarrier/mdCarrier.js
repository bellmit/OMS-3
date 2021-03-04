/**
 * 管理初始化
 */
var MdCarrier = {
    id: "MdCarrierTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
MdCarrier.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '代码', field: 'code', visible: true, align: 'center', valign: 'middle'},
            {title: '名称', field: 'name', visible: true, align: 'center', valign: 'middle'},
            {title: '简称', field: 'abbr', visible: true, align: 'center', valign: 'middle'},
            {title: '自有配送公司自有配送公司(0：3PL,1:自配送)', field: 'isNon3pl', visible: true, align: 'center', valign: 'middle'},
            {title: '联系人', field: 'contacter', visible: true, align: 'center', valign: 'middle'},
            {title: '邮件', field: 'email', visible: true, align: 'center', valign: 'middle'},
            {title: '电话', field: 'telephone', visible: true, align: 'center', valign: 'middle'},
            {title: '地址', field: 'address', visible: true, align: 'center', valign: 'middle'},
            {title: '平台ID', field: 'domainId', visible: true, align: 'center', valign: 'middle'},
            {title: '公司ID', field: 'companyId', visible: true, align: 'center', valign: 'middle'},
            {title: '是否删除', field: 'isDeleted', visible: true, align: 'center', valign: 'middle'},
            {title: '备注', field: 'remark', visible: true, align: 'center', valign: 'middle'},
            {title: '支付服务支持', field: 'paymentService', visible: true, align: 'center', valign: 'middle'},
            {title: '支持配送属性', field: 'deliveryFeature', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'},
            {title: '创建人姓名', field: 'createdBy', visible: true, align: 'center', valign: 'middle'},
            {title: '最后修改时间', field: 'updateTime', visible: true, align: 'center', valign: 'middle'},
            {title: '修改人姓名', field: 'updatedBy', visible: true, align: 'center', valign: 'middle'},
            {title: '打印配置', field: 'printConfig', visible: true, align: 'center', valign: 'middle'},
            {title: '是否需要称重', field: 'isWeight', visible: true, align: 'center', valign: 'middle'},
            {title: '环号/库区', field: 'dcZoneCode', visible: true, align: 'center', valign: 'middle'},
            {title: '滑道号/线路', field: 'lineCode', visible: true, align: 'center', valign: 'middle'},
            {title: '面单编号', field: 'codeForBill', visible: true, align: 'center', valign: 'middle'},
            {title: '日志系统承运商代码', field: 'logSysCode', visible: true, align: 'center', valign: 'middle'},
            {title: '类型', field: 'type', visible: true, align: 'center', valign: 'middle'},
            {title: '发车时段', field: 'shippingPeriod', visible: true, align: 'center', valign: 'middle'},
            {title: '网站', field: 'website', visible: true, align: 'center', valign: 'middle'},
            {title: '是否打印面单', field: 'isNeedPrint', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'tenantId', visible: true, align: 'center', valign: 'middle'},
            {title: '配送商认证key', field: 'appKey', visible: true, align: 'center', valign: 'middle'},
            {title: '配送商认证密匙', field: 'appSecret', visible: true, align: 'center', valign: 'middle'},
            {title: '配送商认证token', field: 'appToken', visible: true, align: 'center', valign: 'middle'},
            {title: '配送商认证URL', field: 'contentUrl', visible: true, align: 'center', valign: 'middle'},
            {title: '配送商认证备份URL', field: 'backupUrl', visible: true, align: 'center', valign: 'middle'},
            {title: '扩展字段1', field: 'ext1', visible: true, align: 'center', valign: 'middle'},
            {title: '扩展字段2', field: 'ext2', visible: true, align: 'center', valign: 'middle'},
            {title: '扩展字段3', field: 'ext3', visible: true, align: 'center', valign: 'middle'},
            {title: '扩展字段4', field: 'ext4', visible: true, align: 'center', valign: 'middle'},
            {title: '扩展字段5', field: 'ext5', visible: true, align: 'center', valign: 'middle'},
            {title: '物流公司id', field: 'logisticsCompanyId', visible: true, align: 'center', valign: 'middle'},
            {title: '仓库id', field: 'warehouseId', visible: true, align: 'center', valign: 'middle'},
            {title: '物流公司编码', field: 'logisticsCompanyCode', visible: true, align: 'center', valign: 'middle'},
            {title: '店铺', field: 'shopId', visible: true, align: 'center', valign: 'middle'},
            {title: '排除编码', field: 'productCodes', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'province', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'city', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'county', visible: true, align: 'center', valign: 'middle'},
            {title: '网点名称', field: 'expressStationName', visible: true, align: 'center', valign: 'middle'},
            {title: '月结账号', field: 'monthlyAccount', visible: true, align: 'center', valign: 'middle'},
            {title: '邮编', field: 'zipCode', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'platformCode', visible: true, align: 'center', valign: 'middle'},
            {title: '快递计费方式1:按单计费;2:按箱计费', field: 'carrierChargeType', visible: true, align: 'center', valign: 'middle'},
            {title: '同步至WMS 0-未同步，1-已同步', field: 'isSyncWms', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'waybillType', visible: true, align: 'center', valign: 'middle'},
            {title: '是否按单交接', field: 'isByCarton', visible: true, align: 'center', valign: 'middle'},
            {title: '手机', field: 'mobile', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
MdCarrier.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        MdCarrier.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加
 */
MdCarrier.openAddMdCarrier = function () {
    var index = layer.open({
        type: 2,
        title: '添加',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/mdCarrier/mdCarrier_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看详情
 */
MdCarrier.openMdCarrierDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/mdCarrier/mdCarrier_update/' + MdCarrier.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除
 */
MdCarrier.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/mdCarrier/delete", function (data) {
            Feng.success("删除成功!");
            MdCarrier.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("mdCarrierId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询列表
 */
MdCarrier.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    MdCarrier.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = MdCarrier.initColumn();
    var table = new BSTable(MdCarrier.id, "/mdCarrier/list", defaultColunms);
    table.setPaginationType("client");
    MdCarrier.table = table.init();
});
