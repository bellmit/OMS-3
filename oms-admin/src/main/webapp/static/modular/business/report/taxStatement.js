/**
 * 管理初始化
 */
var TaxStatement = {
    id: "taxStatementTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    isNormalOrder: 1,  //1：正常订单  0:问题订单
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
TaxStatement.initColumn = function () {
    return [
        {field: 'selectItem', radio: false,visible:false},
        {title:'序号',formatter: function (value, row, index){return index+1;}, align: 'center'},
        {title: '平台', field: 'platformName', visible: true, align: 'center', valign: 'middle', class: 'W80'},
        {title: '店铺', field: 'shopName', visible: true, align: 'center', valign: 'middle', class: 'W150'},
        {title: '仓库', field: 'warehouseName', visible: true, align: 'center', valign: 'middle', class: 'W150'},
        {title: '分销', field: 'officeName', visible: true, align: 'center', valign: 'middle', class: 'W60'},
        {title: '地推', field: 'salesMethod', visible: true, align: 'center', valign: 'middle', class: 'W60'},
        {title: '平台单号', field: 'platformOrderCode', visible: true, align: 'center', valign: 'middle'},
        {title: '付款时间', field: 'orderPaymentConfirmDate', visible: true, align: 'center', valign: 'middle', class: 'W150'},
        {title: '产品编码', field: 'pmInfoId', visible: true, align: 'center', valign: 'middle', class: 'W80'},
        {title: '商品名称', field: 'goodsname', visible: true, align: 'center', valign: 'middle', class: 'W150'},
        {title: '数量', field: 'itemNum', visible: true, align: 'center', valign: 'middle', class: 'W60'},
        {title: '销售金额', field: 'itemAmount', visible: true, align: 'center', valign: 'middle', class: 'W80'},
        {title: '税额(预估税金)', field: 'estimateFcy', visible: true, align: 'center', valign: 'middle'},
        {title: '税费核销金额', field: 'actualFcy', visible: true, align: 'center', valign: 'middle'},
        {title: '税费差额', field: 'taxDifference', visible: true, align: 'center', valign: 'middle'},
        {title: '税费核销时间', field: 'taxCollectionDate', visible: true, align: 'center', valign: 'middle',class: 'W150'},
    ];
};
/**
 * 检查是否选中
 */
TaxStatement.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
        TaxStatement.seItem = selected[0];
        return true;
    }
};


//订单详情----点击事件
function aFormatter(value, row, index) {
    return [//"+value+"
        "<a onclick=\"orderDetail('" + value + "')\">" + value + "</a>"
    ].join("''")
}


/**
 * 导出平台订单
 */
TaxStatement.exportOrder = function () {
    var queryData = {};
    queryData['platformOrderCode'] = $("#platformOrderCode").val();
    queryData['warehouseName'] = $("#warehouseName").val();
    queryData['pmInfoId'] = $("#pmInfoId").val();
    queryData['provinceId'] = $("#provinceId").val();
    queryData['cityId'] = $("#cityId").val();
    queryData['countyId'] = $("#countyId").val();

    var ajax = new $ax(Feng.ctxPath + "/report/taxStatement/exportPlarformOrder/"+TaxStatement.isNormalOrder, function(data){
        var index = layer.open({
            title: 'Excel导出提示信息',
            btn: ['关闭'],
            fix: false, //不固定
            maxmin: true,
            content: "您导出的数据已加入导出队列," +
            "请记好导出编号【" + data + "】,可通过导出编号在" +
            "<font color='red'>导出任务管理</font>" +
            "里查询和下载导出文件。"
        });
//	        window.parent.BWarehouse.table.refresh();
//	        BWarehouseInfoDlg.close();
    },function(data){
        Feng.error("导出失败!请联系管理员!");
    });
    ajax.set(queryData);
    ajax.start();

//    SoOrder.layerIndex = index;
}


/**
 * 查询列表
 */
TaxStatement.search = function () {
    var queryData = {};
    queryData['originalCode'] = $("#originalCode").val();
    queryData['warehouseId'] = $("#warehouseId").val();
    queryData['productCode'] = $("#productCode").val();
    queryData['provinceId'] = $("#provinceId").val();
    queryData['cityId'] = $("#cityId").val();
    queryData['countyId'] = $("#countyId").val();
    queryData['payTimeBegin'] = $("#payTimeBegin").val();
    queryData['payTimeEnd'] = $("#payTimeEnd").val();
    queryData['riseTimeBegin'] = $("#riseTimeBegin").val();
    queryData['riseTimeEnd'] = $("#riseTimeEnd").val();

    TaxStatement.table.refresh({query: queryData});
};

/**
 * 搜索条件重置
 */
TaxStatement.resetSearch = function () {
    Feng.resetForm("searchForm");
    TaxStatement.search();
};

/**
 * 初始化数据
 */
function initTableData() {
    var defaultColunms = TaxStatement.initColumn();
    var table = new BSTable(TaxStatement.id, "/report/taxStatement/list/" + TaxStatement.isNormalOrder, defaultColunms);
    table.setPaginationType("server");
    TaxStatement.table = table.init();
}

$(function () {
    initTableData();
});

//ajax请求  -- 获取省市区三级联动
$("#provinceId").bind("change", function () {
    //alert($("#province").val())
    var ajax = new $ax(Feng.ctxPath + "/tempSo/area", function (data) {
        var selDom = $("#cityId")
        selDom.empty();
        selDom.append("<option value=''>请选择市</option>");
        var selDomDistrict = $("#countyId")
        selDomDistrict.empty();
        selDomDistrict.append("<option value=''>请选择区</option>");
        for (var i = 0; i < data.length; i++) { //循环后台传过来的Json数组
            var pro = data[i]
            selDom.append("<option value='" + pro.id + "'>" + pro.name + "</option>");
        }
    }, function (data) {
        Feng.error("获取市失败!");
    });
    ajax.set("pid", $("#provinceId").val());
    ajax.start();
});

$("#cityId").bind("change", function () {
    var ajax = new $ax(Feng.ctxPath + "/tempSo/area", function (data) {
        var selDom = $("#countyId")
        selDom.empty();
        selDom.append("<option value=''>请选择区</option>");
        for (var i = 0; i < data.length; i++) { //循环后台传过来的Json数组
            var pro = data[i]
            selDom.append("<option value='" + pro.id + "'>" + pro.name + "</option>");
        }
    }, function (data) {
        Feng.error("获取区失败!");
    });
    ajax.set("pid", $("#cityId").val());
    ajax.start();
});


/**
 * 导入税金对账单数据
 */
var importindex = null;

function openExcelIn() {
    importindex = layer.open({
        type: 1,
        skin: 'layui-layer-rim', //加上边框
        area: ['420px', '240px'], //宽高
        //content: '<form action="/soOrder/excelIn" method="post" enctype="multipart/form-data">'+
        //'<input type="file" name="file" value="选择jar包"/><input type="submit"  value="保存"/></form>'
        content: '<div class="ibox float-e-margins">' +
        '<div class="ibox-content">' +
        '<div class="form-horizontal">' +
        ' <div class="row">' +
        '  <div class="col-sm-12">' +
        '     <div class="input-group">' +
        '    <form action="'+Feng.ctxPath+'/soOrder/excelIn" id = "fileInFormId" method="post" enctype="multipart/form-data">' +
        //            '    <form action="/soOrder/excelIn" id = "fileInFormId" method="post" enctype="multipart/form-data">' +
        '	  <input type="file" name="file" id="excelFile" value="选中Excel文件" accept=".csv, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel"/>' +
        ' </form>' +
        '  <a href="'+Feng.ctxPath+'/report/taxStatement/excelOut" class="a-download-modal">下载模板</a>' +
        ' </div>' +
        ' </div>' +
        ' </div>' +
        '<div  class="row-tip"  >' +
        '   请将导出的税金对账单文件填写完毕，再导入系统' +
        ' </div>' +
        ' <div class="row btn-group-m-t">' +
        '  <div class="col-sm-12" style="text-align: center;margin-top: 10px">' +
        '<button type="button" class="btn btn-success" name="确定" id="ensure"  onclick="submitFile()"><i class="fa fa-check"></i>&nbsp;确定</button>' +
        '  </div>' +
        '</div>' +
        ' </div>' +
        ' </div>' +
        '</div>'
    });
}

/**
 * ajax提交导入数据
 */
function submitFile() {
    if ($("#excelFile").val() == null)
        layer.msg("请选择一个Excel文件！")
    var formData = new FormData($("#fileInFormId")[0]);
    $.ajax({
        url: Feng.ctxPath + '/report/taxStatement/importExcel',
        type: "post",
        data: formData,
        processData: false,
        contentType: false,
        async: false,
        success: function (data) {
            if (data === 'success') {
                layer.close(importindex);
                layer.open({
                    content: '导入成功！'
                    , btn: ['确定']
                    , yes: function (index, layero) {
                        location.reload(false);
                    }
                    , cancel: function () {
                        location.reload(false);
                    }
                });
            } else {
                layer.msg(data);
            }
        },
        error: function (e) {
            layer.msg("发生未知错误！");
        }
    });
}