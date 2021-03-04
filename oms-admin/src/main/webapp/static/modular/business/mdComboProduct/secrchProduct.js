/**
 * 商品管理初始化
 */
var MdProduct = {
    id: "MdProductTable",	//表格id
    seItem: null,		//选中的条目
    table: null
};


function addProduct(){
	
	 var selected = $('#' + MdProduct.id).bootstrapTable('getSelections');
	    if (selected.length == 0) {
		        Feng.info("请先选中表格中的某一记录！");
	        return 0;
	    }
	 var ids = ""
	        for (var i = 0; i < selected.length; i++) {
	            ids = ids + selected[i].productCode + ","
	        }
	parent.layer.close(window.parent.MdProductAdd.layerIndex);
	window.parent.productCode(ids);
}

/**
 * 初始化表格的列
 */
MdProduct.initColumn = function () {
    return [
        {field: 'selectItem', radio: false},
        {title: '', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '商品编码', field: 'productCode', visible: true, align: 'left', valign: 'middle'},
        {title: '关联编码', field: 'merchantName', visible: true, align: 'left', valign: 'middle', class: 'W120'},
        {title: '商品名称', field: 'productCname', visible: true, align: 'left', valign: 'middle', class: 'W150'},
        {title: '规格', field: 'specification', visible: true, align: 'left', valign: 'middle'},
        {title: '厂家', field: 'mfCompanyName', visible: true, align: 'left', valign: 'middle'}
    ];
};

MdProduct.search = function () {
    var queryData = {};
    queryData['searchProductName'] = $("#searchProductName").val();
    queryData['searchProductCode'] = $("#searchProductCode").val();
    queryData['searchOriginalCode'] = $("#searchOriginalCode").val();
    queryData['type'] = 1;
    MdProduct.table.refresh({query: queryData});
};


$(function () {
    var defaultColunms = MdProduct.initColumn();
    var table = new BSTable(MdProduct.id, "/mdComboProduct/mdProductList", defaultColunms);
    var queryParams = {}
    queryParams['type']=0;
    queryParams['merchantId']=$("#merchantId").val();
    table.setQueryParams(queryParams);
    table.setPaginationType("server");
    MdProduct.table = table.init();
});


