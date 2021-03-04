/**
 * 管理初始化
 */
var DoOrder = {
    id: "DoOrderTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
DoOrder.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: 'DO号', field: 'doNo', visible: true, align: 'center', valign: 'middle'},
            {title: 'SO号', field: 'soNo', visible: true, align: 'center', valign: 'middle'},
            {title: '父SO号', field: 'parentSoNo', visible: true, align: 'center', valign: 'middle'},
            {title: '波次号', field: 'wvNo', visible: true, align: 'center', valign: 'middle'},
            {title: '分拣序号', field: 'sortingNo', visible: true, align: 'center', valign: 'middle'},
            {title: '取件单号', field: 'grfNo', visible: true, align: 'center', valign: 'middle'},
            {title: 'DO创建时间', field: 'doCreateTime', visible: true, align: 'center', valign: 'middle'},
            {title: 'SO创建时间', field: 'soCreateTime', visible: true, align: 'center', valign: 'middle'},
            {title: '最后操作时间', field: 'lastOperateTime', visible: true, align: 'center', valign: 'middle'},
            {title: '配送单状态： 100 WMS初始化，110 生成波次，115 拣货完成 ...', field: 'state', visible: true, align: 'center', valign: 'middle'},
            {title: '订单类型：1 普通DO,  2 换货DO', field: 'doType', visible: true, align: 'center', valign: 'middle'},
            {title: '用户选择的配送服务类型：10001普通快递，10002 指定收货日期， 10003 一日三送， 10004 半日达', field: 'deliveryMode', visible: true, align: 'center', valign: 'middle'},
            {title: ''配送方式类型 10001.普通快递 20001.ems 30001.供应商直送 40001.自提 30002.商城商家直送'', field: 'deliveryMethodType', visible: true, align: 'center', valign: 'middle'},
            {title: '配送人员id', field: 'deliverymanId', visible: true, align: 'center', valign: 'middle'},
            {title: '订单来源：来自1号店、1号商城、淘宝、qq', field: 'orderSource', visible: true, align: 'center', valign: 'middle'},
            {title: '箱数', field: 'cartonQuantity', visible: true, align: 'center', valign: 'middle'},
            {title: '0.账户支付 1: 网上支付 2. 货到付款3 邮局汇款4 银行转账 5:pos机 7:分期付款 8:合同账期 9:货到转账 10:货到付支票', field: 'paymentMode', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'productAmount', visible: true, align: 'center', valign: 'middle'},
            {title: '应收金额', field: 'toCollectAmount', visible: true, align: 'center', valign: 'middle'},
            {title: '仓库ID', field: 'whId', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'productCodes', visible: true, align: 'center', valign: 'middle'},
            {title: '配送商ID', field: 'carrierId', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'consignee', visible: true, align: 'center', valign: 'middle'},
            {title: '收货人电话', field: 'consigneeTelephone', visible: true, align: 'center', valign: 'middle'},
            {title: '收货人手机', field: 'consigneeMobile', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'provinceId', visible: true, align: 'center', valign: 'middle'},
            {title: '省', field: 'province', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'cityId', visible: true, align: 'center', valign: 'middle'},
            {title: '市', field: 'city', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'districtId', visible: true, align: 'center', valign: 'middle'},
            {title: '区', field: 'district', visible: true, align: 'center', valign: 'middle'},
            {title: '自定义区域', field: 'area', visible: true, align: 'center', valign: 'middle'},
            {title: '四级区域ID', field: 'areaId', visible: true, align: 'center', valign: 'middle'},
            {title: '详细地址', field: 'address', visible: true, align: 'center', valign: 'middle'},
            {title: '发票类型  0:不开发票，1：普通发票，2：增值税发票', field: 'isNeedInvoice', visible: true, align: 'center', valign: 'middle'},
            {title: '出库时间', field: 'leavingWhTime', visible: true, align: 'center', valign: 'middle'},
            {title: '入分拣中心时间', field: 'arriveDcTime', visible: true, align: 'center', valign: 'middle'},
            {title: '出分拣中心时间', field: 'leaveDcTime', visible: true, align: 'center', valign: 'middle'},
            {title: '回单时间', field: 'returnTime', visible: true, align: 'center', valign: 'middle'},
            {title: '创建人ID', field: 'createdBy', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'},
            {title: '修改人ID', field: 'updatedBy', visible: true, align: 'center', valign: 'middle'},
            {title: '最后修改时间', field: 'updateTime', visible: true, align: 'center', valign: 'middle'},
            {title: '订单锁定标识：1 锁定 0 释放', field: 'isLock', visible: true, align: 'center', valign: 'middle'},
            {title: '出库SKU总数', field: 'shippedSkuQty', visible: true, align: 'center', valign: 'middle'},
            {title: '出库UNITS总数', field: 'shippedUnitsQty', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'shipmentNo', visible: true, align: 'center', valign: 'middle'},
            {title: '是否同步到WMS 0:未同步 1:已同步', field: 'isSynWms', visible: true, align: 'center', valign: 'middle'},
            {title: '是否同步到DTS 0:未同步 1:已同步 2:同步失败', field: 'isSynDts', visible: true, align: 'center', valign: 'middle'},
            {title: '调WMS接口时间', field: 'payTime', visible: true, align: 'center', valign: 'middle'},
            {title: '预计出库时间同步wms时间', field: 'syncTime', visible: true, align: 'center', valign: 'middle'},
            {title: '备注', field: 'remark', visible: true, align: 'center', valign: 'middle'},
            {title: '订单ID', field: 'soId', visible: true, align: 'center', valign: 'middle'},
            {title: '用户名称', field: 'notice', visible: true, align: 'center', valign: 'middle'},
            {title: '订单购买类型:0普通(默认值) 1实体经销商 2网络经销商 ', field: 'orderSaleMethod', visible: true, align: 'center', valign: 'middle'},
            {title: '应发数量（各个DETAIL 条目的NUM之和）', field: 'orderQty', visible: true, align: 'center', valign: 'middle'},
            {title: '实发数量（WMS系统回写）', field: 'packQty', visible: true, align: 'center', valign: 'middle'},
            {title: '是否要代收货款 1：是 0：否', field: 'isThirdPartyBill', visible: true, align: 'center', valign: 'middle'},
            {title: '付款方式ID', field: 'orderPaymentMethodId', visible: true, align: 'center', valign: 'middle'},
            {title: '订单总额（PRODUCT_AMOUNT+ORDER_DELIVERY_FEE-商家优惠-平台优惠)', field: 'orderAmount', visible: true, align: 'center', valign: 'middle'},
            {title: '已预付货款', field: 'accountPayable', visible: true, align: 'center', valign: 'middle'},
            {title: '总返利金额', field: 'discountAmount', visible: true, align: 'center', valign: 'middle'},
            {title: '运费', field: 'orderDeliveryFee', visible: true, align: 'center', valign: 'middle'},
            {title: '发票内容', field: 'invoiceContent', visible: true, align: 'center', valign: 'middle'},
            {title: '发票号（不用），现表示SO单需要的发票数（一般是商品发票和运费发票）', field: 'invoiceNumber', visible: true, align: 'center', valign: 'middle'},
            {title: '发票金额', field: 'invoiceAmount', visible: true, align: 'center', valign: 'middle'},
            {title: '实收金额', field: 'receiveAmount', visible: true, align: 'center', valign: 'middle'},
            {title: '商家ID', field: 'merchantId', visible: true, align: 'center', valign: 'middle'},
            {title: '是否失效', field: 'isDeleted', visible: true, align: 'center', valign: 'middle'},
            {title: '0：不需要等待发货通知 1：换货do等待发货  2：已收到换货通知  ', field: 'dataExchangeFlag', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'shopId', visible: true, align: 'center', valign: 'middle'},
            {title: '原始订单号', field: 'originalSoCode', visible: true, align: 'center', valign: 'middle'},
            {title: '是否处方药标记 0：否 1：是', field: 'prescription', visible: true, align: 'center', valign: 'middle'},
            {title: '客服备注(卖家备注)', field: 'csRemark', visible: true, align: 'center', valign: 'middle'},
            {title: '买家备注', field: 'customerRemark', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'orderVolume', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'orderWeight', visible: true, align: 'center', valign: 'middle'},
            {title: '是否已经打印电子面单 0:否,1:是', field: 'isPrintWaybill', visible: true, align: 'center', valign: 'middle'},
            {title: '是否已经打印发货单 0:否,1:是', field: 'isPrintDo', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'tenantId', visible: true, align: 'center', valign: 'middle'},
            {title: '父平台单号', field: 'parentPlatformOrderCode', visible: true, align: 'center', valign: 'middle'},
            {title: '跨境方式 0 非跨境；1 邮关；2 bc直邮；3 bbc保税；4 个人物品；5 一般贸易', field: 'crossBorder', visible: true, align: 'center', valign: 'middle'},
            {title: '销售单确认定时任务处理状态（0：未处理 1：处理成功 2：处理失败）', field: 'isDeal', visible: true, align: 'center', valign: 'middle'},
            {title: '外部发货单号-奇门', field: 'outerOrderCode', visible: true, align: 'center', valign: 'middle'},
            {title: '外部订单类型JYCK=一般交易出库单, HHCK=换货出库单, BFCK=补发出库单，QTCK=其他出库单', field: 'outerOrderType', visible: true, align: 'center', valign: 'middle'},
            {title: '团购规则ID', field: 'teamBuyRuleId', visible: true, align: 'center', valign: 'middle'},
            {title: 'do出库子类型（其他出库）', field: 'subType', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'shopName', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'merchantName', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
DoOrder.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        DoOrder.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加
 */
DoOrder.openAddDoOrder = function () {
    var index = layer.open({
        type: 2,
        title: '添加',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/doOrder/doOrder_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看详情
 */
DoOrder.openDoOrderDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/doOrder/doOrder_update/' + DoOrder.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除
 */
DoOrder.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/doOrder/delete", function (data) {
            Feng.success("删除成功!");
            DoOrder.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("doOrderId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询列表
 */
DoOrder.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    DoOrder.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = DoOrder.initColumn();
    var table = new BSTable(DoOrder.id, "/doOrder/list", defaultColunms);
    table.setPaginationType("client");
    DoOrder.table = table.init();
});
