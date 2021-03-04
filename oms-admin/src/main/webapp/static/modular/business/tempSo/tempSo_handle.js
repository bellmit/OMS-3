/**
 * 管理初始化
 */
var tempSoHandle = {
    id: "abnormalGoodsTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};


/**
 * 关闭此对话框
 */
tempSoHandle.close = function () {
    var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
    parent.layer.close(index);  // 关闭layer
}

/**
 * input 处理异常订单---铺货异常处理 输入商品编码
 * @param value
 * @param row
 * @param index
 * @returns {string}
 */
function formatInput(value, row, index) {
    var id = row.id;
    return [
        "<input type='text' value='' key='inputValue'  onblur=\"buildSubmitValue(this,'" + id + "')\"/>"
    ].join("''")
}


/**
 * 构建提交参数--铺货异常构建提交的参数
 */
var submitData = [];
function buildSubmitValue(obj, id) {
    var first = false;
    var param = {
        id: id,
        localCode: $(obj).val()
    }
    if (submitData.length >0){
        submitData.forEach(function (item) {
            if (item.id === id) {
                item.localCode = $(obj).val();
            }else{
                // console.log(first);
                first = true;
            }
        });
    }else {
        first =true;
    }
    if (first){
        submitData.push(param)
    }
}

/**
 * 提交保存数据--铺货异常处理  提交到后台处理
 */
tempSoHandle.submit = function () {
    var abnormalGoodsPlatformOrderCode = $("#platformOrderCode").val();
    // alert("铺货异常订单单号："+abnormalGoodsPlatformOrderCode);
    // alert("提交的参数为" + JSON.stringify(submitData));
    $.ajax({
        type: "POST",
        url: Feng.ctxPath + '/tempSo/handleAbnormalGoods',
        traditional: true,
        data: {
            'abnormalGoodsData': JSON.stringify(submitData),
            'abnormalGoodsPlatformOrderCode':abnormalGoodsPlatformOrderCode,
        },
        dataType: "json",
        success: function (data) {
            // alert(data.status);
            if (data.status == 'ok') {
                Feng.success("修改成功!");
                tempSoHandle.close();
            } else {
                var index = layer.open({
                    title: '提示',
                    fix: false, //不固定
                    maxmin: true,
                    content: "修改失败！ "+data.message
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

/**
 * 重新抓单--现在暂时没有这个功能了，暂时不用这个方法
 * @param value
 */
function tempSoHandlecatchedOrder(value) {
    var index1 = layer.load(0, {shade: false}); //0代表加载的风格，支持0-2
    $.ajax({
        type: "POST",
        url: Feng.ctxPath + '/tempSoHandle/catchedOrderAgain',
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
tempSoHandle.initColumn = function () {
    return [
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '平台商品编码', field: 'skuCode', visible: true, align: 'center', valign: 'middle'},
        {title: '商品名称', field: 'skuName', visible: true, align: 'center', valign: 'middle'},
        {title: '本地商品编码', field: 'localCode', visible: true, align: 'center', formatter: formatInput, valign: 'middle'},
    ];
};

/**
 * 检查是否选中
 */
tempSoHandle.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
        tempSoHandle.seItem = selected[0];
        return true;
    }
};

/**
 * 初始化数据
 */
function initTableData() {
    var queryData = {};
    queryData['condition'] = $("#platformOrderCode").val();
    var defaultColunms = tempSoHandle.initColumn();
    var table = new BSTable(tempSoHandle.id, "/tempSoItem/list", defaultColunms);
    table.queryParams = queryData;
    table.setPaginationType("client");
    tempSoHandle.table = table.init();
}

$(function () {
    initTableData();
});
