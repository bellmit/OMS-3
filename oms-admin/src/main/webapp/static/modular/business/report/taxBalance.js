/**
 * 管理初始化
 */
var TaxBalance = {
    id: "taxBalanceTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    isNormalOrder: 1,  //1：正常订单  0:问题订单
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
TaxBalance.initColumn = function () {
    return [
        {field: 'selectItem', radio: false, visible: false},
        {
            title: '序号', formatter: function (value, row, index) {
                return index + 1;
            }, align: 'center', class: 'W60'
        },
        {title: '平台单号', field: 'platformOrderCode', visible: true, align: 'center', valign: 'middle'},
        {title: '店铺', field: 'shopName', visible: true, align: 'center', valign: 'middle'},
        {
            title: '售后类型',
            field: 'afterSaleType',
            visible: true,
            align: 'center',
            valign: 'middle',
            formatter: function (value, row, index) {
                var str = '';
                switch (value) {
                    case "1":
                        str = "正常发货订单";
                        break;
                    case "2":
                        str = "快递丢失补发";
                        break;
                    case "3":
                        str = "售后补偿订单";
                        break;
                    default:
                        str = value;
                        break;
                }
                return str;
            }
        },
        {title: '仓库', field: 'warehouseName', visible: true, align: 'center', valign: 'middle'},
        {title: 'BTOB/BTOC', field: 'btoborbtoc', visible: true, align: 'center', valign: 'middle'},
        {title: '医美/健康', field: 'mborh', visible: true, align: 'center', valign: 'middle'},
        {title: '商品编码', field: 'originalCode', visible: true, align: 'center', valign: 'middle'},
        {title: '商品名称', field: 'goodsname', visible: true, align: 'center', valign: 'middle'},
        {title: '数量', field: 'itemNum', visible: true, align: 'center', valign: 'middle'},
        {title: '不含税销售额', field: 'itemAmount', visible: true, align: 'center', valign: 'middle'},
        {title: '税额', field: 'estimateFcy', visible: true, align: 'center', valign: 'middle'},
        {title: '税费核销金额', field: 'taxFcy', visible: true, align: 'center', valign: 'middle'},
        {title: '未核销金额', field: 'notTaxFcy', visible: true, align: 'center', valign: 'middle'},
        {title: '备注', field: 'remarks', visible: true, align: 'center', valign: 'middle'},
    ];
};


/**
 * 导出平台订单
 */
TaxBalance.exportOrder = function () {
    var queryData = {};
    queryData['platformOrderCode'] = $("#platformOrderCode").val();
    queryData['logisticsNo'] = $("#logisticsNo").val();
    queryData['warehouseName'] = $("#warehouseName").val();

    var ajax = new $ax(Feng.ctxPath + "/report/taxBalance/exportPlarformOrder/" + TaxBalance.isNormalOrder, function (data) {
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
    }, function (data) {
        Feng.error("导出失败!请联系管理员!");
    });
    ajax.set(queryData);
    ajax.start();

//    SoOrder.layerIndex = index;
}


/**
 * 查询列表
 */
TaxBalance.search = function () {
    var queryData = {};
    queryData['platformOrderCode'] = $("#platformOrderCode").val();
    queryData['logisticsNo'] = $("#logisticsNo").val();
    queryData['warehouseName'] = $("#warehouseName").val();
    TaxBalance.table.refresh({query: queryData});
};

/**
 * 搜索条件重置
 */
TaxBalance.resetSearch = function () {
    Feng.resetForm("searchForm");
    TaxBalance.search();
};

/**
 * 初始化数据
 */
function initTableData() {
    var defaultColunms = TaxBalance.initColumn();
    var table = new BSTable(TaxBalance.id, "/report/taxBalance/list/" + TaxBalance.isNormalOrder, defaultColunms);
    table.setPaginationType("server");
    TaxBalance.table = table.init();
}

$(function () {
    initTableData();
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
            '  <a href="/report/taxBalance/excelOut" class="a-download-modal">下载模板</a>' +
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
        url: Feng.ctxPath + '/report/taxBalance/importExcel',
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