/**
 * 管理初始化
 */
var SoItem = {
    id: "SoItemTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
SoItem.initColumn = function () {
    return [
        {field: 'selectItem', radio: false},
          //  {title: '', field: 'id', visible: true, align: 'center', valign: 'middle'},
          //  {title: '用户id', field: 'endUserId', visible: true, align: 'center', valign: 'middle'},
         //   {title: '订单id', field: 'orderId', visible: true, align: 'center', valign: 'middle'},
            {title: '平台单号', field: 'platformOrderCode', visible: true, align: 'center', valign: 'middle'},
            {title: '节点', field: 'remark', visible: true, align: 'center', valign: 'middle'},
            {title: '平台规格品', field: 'platformSkuId', visible: true, align: 'center', valign: 'middle'},
         //   {title: '平台规格品编码', field: 'platformSkuCode', visible: true, align: 'center', valign: 'middle'},
            {title: '商品名称', field: 'productCname', visible: true, align: 'center', valign: 'middle'},
            {title: '产品id', field: 'productId', visible: true, align: 'center', valign: 'middle'},
            {title: '产品编码', field: 'productCode', visible: true, align: 'center', valign: 'middle'},
//            {title: '成本价', field: 'cost', visible: true, align: 'center', valign: 'middle'},
//            {title: '套餐码', field: 'mainProductCode', visible: true, align: 'center', valign: 'middle'},
            {title: '商家id', field: 'merchantId', visible: true, align: 'center', valign: 'middle'},
            
            {title: '商品单价', field: 'orderItemPrice', visible: true, align: 'center', valign: 'middle'},
            {title: '商品购买数量', field: 'orderItemNum', visible: true, align: 'center', valign: 'middle'},
            {title: '商品总金额', field: 'actualPrice', visible: true, align: 'center', valign: 'middle'}
//            {title: '库存冻结数量', field: 'frozenStockNum', visible: true, align: 'center', valign: 'middle'},
//            {title: '平台规格名称', field: 'platformSkuName', visible: true, align: 'center', valign: 'middle'},
//            {title: '是否赠品 0:否 1:是 2:送赠品，换购赠品 3:landingpage的商品', field: 'isGift', visible: true, align: 'center', valign: 'middle'},
//            {title: '商品销售类型 1:备货 2: 寄售 3:预定 4:代售 5:聚单 6:赠品 7:新聚单', field: 'productSaleType', visible: true, align: 'center', valign: 'middle'},
//            {title: '父订单明细id', field: 'parentSoItemId', visible: true, align: 'center', valign: 'middle'},
//            {title: '是否叶子节点 1:是 2:否', field: 'isItemLeaf', visible: true, align: 'center', valign: 'middle'},
//            {title: '仓库id', field: 'warehouseId', visible: true, align: 'center', valign: 'middle'},
//            {title: '是否需要等货 0:不需要 1:需要', field: 'virtualStockStatus', visible: true, align: 'center', valign: 'middle'},
//            {title: '更新时间', field: 'updateTime', visible: true, align: 'center', valign: 'middle'},
//            {title: '产品类型 0:普通产品 1:主系列产品 2:子系列产品 3:捆绑产品 4:实物礼品卡 5:虚拟商品 7:电子礼品卡', field: 'productType', visible: true, align: 'center', valign: 'middle'},
//            {title: '下单时的产品图片url', field: 'productPicPath', visible: true, align: 'center', valign: 'middle'},
//            {title: '分摊到此soitem的促销优惠金额', field: 'promotionAmount', visible: true, align: 'center', valign: 'middle'},
//            {title: '分摊到此so_item的运费金额', field: 'deliveryFeeAmount', visible: true, align: 'center', valign: 'middle'},
//            {title: '结算价', field: 'settlementPrice', visible: true, align: 'center', valign: 'middle'},
//            {title: '创建时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'},
//            {title: '删除状态 0：未删除', field: 'isDeleted', visible: true, align: 'center', valign: 'middle'},
//            {title: '产品毛重', field: 'grossWeight', visible: true, align: 'center', valign: 'middle'},
//            {title: '商家优惠金额', field: 'merchantDiscount', visible: true, align: 'center', valign: 'middle'},
//            {title: '平台优惠金额', field: 'platformDiscount', visible: true, align: 'center', valign: 'middle'},
//            {title: '是否处方药标记 0：否 1：是', field: 'prescription', visible: true, align: 'center', valign: 'middle'},
//            {title: '药品规格', field: 'specification', visible: true, align: 'center', valign: 'middle'},
//            {title: '代发价格', field: 'insteaPrice', visible: true, align: 'center', valign: 'middle'},
//            {title: '是否代运营 0 否 1 是', field: 'agentOperate', visible: true, align: 'center', valign: 'middle'},
//            {title: '分销商/供销商租户id', field: 'ralationTenantId', visible: true, align: 'center', valign: 'middle'},
//            {title: '', field: 'tenantId', visible: true, align: 'center', valign: 'middle'},
//            {title: '实发数量', field: 'actualQty', visible: true, align: 'center', valign: 'middle'},
//            {title: '包装数', field: 'stdPackQty', visible: true, align: 'center', valign: 'middle'},
//            {title: '备注', field: 'remark', visible: true, align: 'center', valign: 'middle'},
//            {title: '商品销售方式1 直接销售10 上门换货11淘宝补发12淘宝换货13配件2搭销3赠品4补发5换货6对寄7退转换8检修9原件返', field: 'salesMethod', visible: true, align: 'center', valign: 'middle'},
//            {title: '序号,单据行号', field: 'gnum', visible: true, align: 'center', valign: 'middle'},
//            {title: '明细预估税金', field: 'itemEstimateFcy', visible: true, align: 'center', valign: 'middle'},
//            {title: '税费-买家支付税金', field: 'itemTaxFcy', visible: true, align: 'center', valign: 'middle'},
//            {title: '实际海关收取税金', field: 'itemActualFcy', visible: true, align: 'center', valign: 'middle'},
//            {title: '实际成交价', field: 'actualPrice', visible: true, align: 'center', valign: 'middle'},
//            {title: '分办名称--百洋', field: 'officeName', visible: true, align: 'center', valign: 'middle'},
//            {title: '批号', field: 'lotNo', visible: true, align: 'center', valign: 'middle'},
//            {title: '失效日期', field: 'expireTime', visible: true, align: 'center', valign: 'middle'},
//            {title: '生产日期', field: 'productionTime', visible: true, align: 'center', valign: 'middle'}
    ];
};





$(function () {
	 var queryData = {};
	 queryData['condition'] = $("#condition").val();
    var defaultColunms = SoItem.initColumn();
    var table = new BSTable(SoItem.id, "/soItem/list", defaultColunms);
    table.queryParams= queryData;
    table.setPaginationType("client");
    SoItem.table = table.init();
});

