/**
 * 管理初始化
 */
var TempSo = {
    id: "TempSoTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    isNormalOrder: 10,  //1：正常订单  0:问题订单
    layerIndex: -1
};

/**
 * 重新抓单----这个功能暂时不需要了，放在这，以后可能需要
 * @param value
 */
function tempSocatchedOrder(value) {
    var index1 = layer.load(0, {shade: false}); //0代表加载的风格，支持0-2  //layer.close(index1);
    $.ajax({
        type: "POST",
        url: Feng.ctxPath + '/tempSo/catchedOrderAgain',
        traditional: true,
        data: {
            'platformOrderCode': value,
        },
        dataType: "json",
        success: function (data) {
            // alert(data.status);
            layer.close(index1);
            if (data.status == 'ok') {
                var index = layer.open({
                    title: '提示',
                    fix: false, //不固定
                    maxmin: true,
                    content: "重新抓单成功!"
                });
            } else {
                var index = layer.open({
                    title: '提示',
                    fix: false, //不固定
                    maxmin: true,
                    content: "抓单失败！"
                });
            }
        },
        error: function () {
            layer.close(index1);
            var index = layer.open({
                title: '提示',
                fix: false, //不固定
                maxmin: true,
                content: "网络连接出错！"
            });
        },
    });

};

/**
 * 初始化表格的列
 */
TempSo.initColumn = function () {
    return [
        {field: 'selectItem', radio: false},
        // {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},

        {
            title: '平台单号',
            field: 'platformOrderCode',
            visible: true,
            align: 'center',
            formatter: aFormatter,
            valign: 'middle'
        },
        {
            title: '查看日志',
            field: 'platformOrderCode',
            visible: true,
            align: 'center',
            formatter: tempSoLog,
            valign: 'middle'
        },
        {title: '渠道', field: 'platformName', visible: true, align: 'center', valign: 'middle', class: 'W80'},
        {title: '商家', field: 'merchantIdName', visible: true, align: 'center', valign: 'middle', class: 'W120'},
        {title: '店铺 ', field: 'shopName', visible: true, align: 'center', valign: 'middle', class: 'W80'},
        {title: '仓库 ', field: 'storeCodeName', visible: true, align: 'center', valign: 'middle', class: 'W80'},
        {title: '订单来源 ', field: 'source', visible: true, align: 'center', valign: 'middle'},
        // {title: '买家ID ', field: '', visible: true, align: 'center', valign: 'middle'},
        {title: '买家ID ', field: 'buyerNick', visible: true, align: 'center', valign: 'middle'},
        {title: '下单时间', field: 'createTime', visible: true, align: 'center', valign: 'middle', class: 'W80'},
        {title: '抓单时间', field: 'syncTime', visible: false, align: 'center', valign: 'middle', class: 'W80'},
        {title: '订单状态', field: 'orderStatus', visible: true, align: 'center', valign: 'middle'},
        {title: '处理状态', field: 'status', visible: true, align: 'center', valign: 'middle'},//-1：待生成订单 0:生成订单中  1：已生成订单 2:处理失败 3:铺货一场订单 4:地址解析失败
        {title: '错误原因!', field: 'errReason', visible: true, align: 'center', valign: 'middle'},
        {title: '支付方式 ', field: 'payType', visible: true, align: 'center', valign: 'middle'},// 1在线支付 2 货到付款
        {title: '支付渠道式 ', field: '', visible: true, align: 'center', valign: 'middle'},// 1在线支付 2 货到付款
        {title: '实付金额', field: 'orderPayment', visible: true, align: 'center', valign: 'middle'},//产品金额+运费-商家优惠-平台优惠
        {title: '付款时间', field: 'paidTime', visible: true, align: 'center', valign: 'middle', class: 'W80'},
        {title: '收货人姓名', field: 'receiverName', visible: true, align: 'center', valign: 'middle'},
        {title: '收货地址_省', field: 'platformProvince', visible: true, align: 'center', valign: 'middle'},
        {title: '收货地址_市', field: 'platformCity', visible: true, align: 'center', valign: 'middle'},
        {title: '收货地址_区', field: 'platformCounty', visible: true, align: 'center', valign: 'middle'},
        {title: '收货地址', field: 'receiverAddress', visible: true, align: 'center', valign: 'middle', class: 'W150'},
        {title: '买家备注', field: 'customerRemark', visible: true, align: 'center', valign: 'middle', class: 'W80'},
        {title: '卖买家备注', field: 'csRemark', visible: true, align: 'center', valign: 'middle', class: 'W80'},
    ];
};
/**
 * 检查是否选中
 */
TempSo.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
        TempSo.seItem = selected[0];
        return true;
    }
};

/**
 *  查看订单详情----弹窗显示
 * @param value
 */
function orderDetail(value) {
    var index = layer.open({
        type: 2,
        title: '平台订单详情',
        area: ['90%', '90%'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/tempSo/tempSo_update/' + value
    });
    this.layerIndex = index;
};


//订单详情----点击事件
function aFormatter(value, row, index) {
    return [//"+value+"
        "<a onclick=\"orderDetail('" + value + "')\">" + value + "</a>"
    ].join("''")
}

/**
 * 查看日志----点击事件
 * @param value
 * @param row
 * @param index
 * @returns {string}
 */
function tempSoLog(value, row, index) {
    return [//"+value+"
        "<a onclick=\"tempSoLogDetail('" + value + "')\">查看日志</a>"
    ].join("''")
}

/**
 * 查看日志----弹窗显示
 * @param value
 */
function tempSoLogDetail(value) {
    var index = layer.open({
        type: 2,
        title: '日志详情',
        area: ['90%', '90%'],
        shade: 0.8,
        maxmin: true,
        closeBtn: 1,
        shadeClose: false,
        content: Feng.ctxPath + '/tempSoOperateLog/logList/' + value
    });
    this.layerIndex = index;
};

/**
 * 铺货异常处理----点击事件
 * @param value
 * @param row
 * @param index
 * @returns {string}
 */
function tempSoHandleAbnormalOrder(value, row, index) {
    return [//"+value+"
        "<a onclick=\"handleAbnormalOrder('" + value + "')\">铺货异常处理</a>"
    ].join("''")
}

/**
 * 铺货异常处理----弹窗显示
 * @param value
 */
function handleAbnormalOrder(value) {
    var index = layer.open({
        type: 2,
        title: '铺货异常处理',
        area: ['90%', '90%'],
        shade: 0.8,
        maxmin: true,
        closeBtn: 1,
        shadeClose: false,
        content: Feng.ctxPath + "/tempSo/toAbnormal/" + value
    });
    this.layerIndex = index;
};

/**
 * 订单下载--手动抓单
 */
TempSo.downloadOrder = function () {
    var index = layer.open({
        type: 2,
        title: '订单下载',
        area: ['600px', '306px'],
        shade: 0.8,
        maxmin: true,
        closeBtn: 1,
        shadeClose: false,
        content: Feng.ctxPath + '/tempSo/downloadOrder'
    });
    this.layerIndex = index;
    // alert("你点击了订单下载按钮")
}
/**
 * 生成订单
 */
TempSo.generateOrder = function () {
    if (this.check()) {
        var selectedDataList = $('#' + this.id).bootstrapTable('getSelections');
        // alert("你点击了生成订单按钮"+JSON.stringify(selectedDataList))

        $.ajax({
            type: "POST",
            url: Feng.ctxPath + '/tempSo/generateOrderManual',
            traditional: true,
            data: {
                'selectedDataList': JSON.stringify(selectedDataList),
            },
            dataType: "json",
            success: function (data) {
                // alert(data.status);
                if (data.status == 'ok') {
                    Feng.success("生成订单成功!");
                    tempSoHandle.close();
                } else {
                    var index = layer.open({
                        title: '提示',
                        fix: false, //不固定
                        maxmin: true,
                        content: "生成订单失败！ " + data.message
                    });
                }
            },
            error: function () {
                var index = layer.open({
                    title: '提示',
                    fix: false, //不固定
                    maxmin: true,
                    content: "网络连接出错！"
                });
            },
        });


    }
}

/**
 * 导出平台订单
 */
TempSo.exportOrder = function () {
    var queryData = {};
    queryData['platformOrderCodeSearch'] = $("#platformOrderCodeSearch").val();
    queryData['platformIdSearch'] = $("#platformIdSearch").val();
    queryData['merchantIdSearch'] = $("#merchantIdSearch").val();
    queryData['shopIdSearch'] = $("#shopIdSearch").val();
    queryData['receiverMobileSearch'] = $("#receiverMobileSearch").val();
    queryData['buyerNickSearch'] = $("#buyerNickSearch").val();
    queryData['statusSearch'] = $("#statusSearch").val();
    queryData['errReasonSearch'] = $("#errReasonSearch").val();
    queryData['prescriptionSearch'] = $("#prescriptionSearch").val();//这个条件暂时不用了
    queryData['orderStatusSearch'] = $("#orderStatusSearch").val();
    queryData['sourceSearch'] = $("#sourceSearch").val();
    queryData['payTypeSearch'] = $("#payTypeSearch").val();
    queryData['createTimeSearchBegin'] = $("#createTimeSearchBegin").val();
    queryData['createTimeSearchEnd'] = $("#createTimeSearchEnd").val();
    queryData['receiverNameSearch'] = $("#receiverNameSearch").val();
    queryData['orderPaymentSearch'] = $("#orderPaymentSearch").val();
    queryData['provinceId'] = $("#provinceId").val();
    queryData['cityId'] = $("#cityId").val();
    queryData['countyId'] = $("#countyId").val();
    queryData['customerRemarkSearch'] = $("#customerRemarkSearch").val();
    queryData['csRemarkSearch'] = $("#csRemarkSearch").val();
    queryData['paidTimeSearchBegin'] = $("#paidTimeSearchBegin").val();
    queryData['paidTimeSearchEnd'] = $("#paidTimeSearchEnd").val();

    var ajax = new $ax(Feng.ctxPath + "/tempSo/exportPlarformOrder/"+TempSo.isNormalOrder, function(data){
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
 * 铺货异常处理----应该是没用到这个方法
 */
TempSo.topAbNormalHandle = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
}


/**
 * 打开查看详情----代码自动生成的，没用这个，查看订单详情用的是自己写的
 */
TempSo.openTempSoDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '详情',
            area: ['90%', '90%'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/tempSo/tempSo_update/' + TempSo.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除----自动生成代码，暂时没用到
 */
TempSo.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/tempSo/delete", function (data) {
            Feng.success("删除成功!");
            TempSo.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("tempSoId", this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询列表
 */
TempSo.search = function () {
    var queryData = {};
    queryData['platformOrderCodeSearch'] = $("#platformOrderCodeSearch").val();
    queryData['platformIdSearch'] = $("#platformIdSearch").val();
    queryData['merchantIdSearch'] = $("#merchantIdSearch").val();
    queryData['shopIdSearch'] = $("#shopIdSearch").val();
    queryData['receiverMobileSearch'] = $("#receiverMobileSearch").val();
    queryData['buyerNickSearch'] = $("#buyerNickSearch").val();
    queryData['statusSearch'] = $("#statusSearch").val();
    queryData['errReasonSearch'] = $("#errReasonSearch").val();
    queryData['prescriptionSearch'] = $("#prescriptionSearch").val();//这个条件暂时不用了
    queryData['orderStatusSearch'] = $("#orderStatusSearch").val();
    queryData['sourceSearch'] = $("#sourceSearch").val();
    queryData['payTypeSearch'] = $("#payTypeSearch").val();
    queryData['createTimeSearchBegin'] = $("#createTimeSearchBegin").val();
    queryData['createTimeSearchEnd'] = $("#createTimeSearchEnd").val();
    queryData['receiverNameSearch'] = $("#receiverNameSearch").val();
    queryData['orderPaymentSearch'] = $("#orderPaymentSearch").val();
    queryData['provinceId'] = $("#provinceId").val();
    queryData['cityId'] = $("#cityId").val();
    queryData['countyId'] = $("#countyId").val();
    queryData['customerRemarkSearch'] = $("#customerRemarkSearch").val();
    queryData['csRemarkSearch'] = $("#csRemarkSearch").val();
    queryData['paidTimeSearchBegin'] = $("#paidTimeSearchBegin").val();
    queryData['paidTimeSearchEnd'] = $("#paidTimeSearchEnd").val();
    TempSo.table.refresh({query: queryData});
};

/**
 * 搜索条件重置
 */
TempSo.resetSearch = function () {
    Feng.resetForm("searchForm");
    TempSo.search();
};

//操作列--铺货异常操作
var operationColumn = {
    title: '操作',
    field: 'platformOrderCode',
    visible: true,
    align: 'center',
    formatter: tempSoHandleAbnormalOrder,
    valign: 'middle',
    class: 'W120'
};

/**
 * 初始化数据----列表查询中问题订单和正常订单切换
 */
function initTableData() {
    var defaultColunms = TempSo.initColumn();
    if (window.sessionStorage.getItem("tempSo-tab") === '问题订单') {
        defaultColunms.splice(2, 0, operationColumn);
        $('#tempSole-nav a[name=问题订单]').tab('show');
        TempSo.isNormalOrder = 0;
    } else if(window.sessionStorage.getItem("tempSo-tab") === '正常订单') {
        $('#tempSole-nav a[name=正常订单]').tab('show');
        TempSo.isNormalOrder = 1;
    }else if(window.sessionStorage.getItem("tempSo-tab") === '全部') {
        $('#tempSole-nav a[name=全部]').tab('show');
        TempSo.isNormalOrder = 10;
    }
    var table = new BSTable(TempSo.id, "/tempSo/list/" + TempSo.isNormalOrder, defaultColunms);
    table.setPaginationType("server");
    TempSo.table = table.init();
}

$(function () {
    initTableData();
    $('#tempSole-nav a').click(function (e) {
        e.preventDefault();
        $(this).tab('show');
        window.sessionStorage.setItem("tempSo-tab", $(this).context.innerText);
        setTimeout(function () {
            window.location.reload(true);
        }, 50)
    });
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