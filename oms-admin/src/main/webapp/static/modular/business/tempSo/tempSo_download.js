/**
 * 管理初始化
 */
var tempSoDownload = {
    layerIndex: -1
};

/**
 * 关闭此对话框
 */
tempSoDownload.close = function () {
    var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
    parent.layer.close(index);  // 关闭layer
}

/**
 * 提交保存数据
 */
tempSoDownload.submit = function () {
    var index1 = layer.load(0, {shade: false}); //0代表加载的风格，支持0-2
    var orderCatched = $("#orderIds").val();
    // alert("手工抓单的单号" + orderCatched);
    $.ajax({
        type: "POST",
        url: Feng.ctxPath + '/tempSo/catchedOrderManual',
        traditional: true,
        data: {
            'platformOrderCodes': orderCatched,
        },
        dataType: "json",
        success: function (data) {
            layer.close(index1);
            // alert(data.status);
            if (data.status == 'ok') {
                Feng.success("手工抓单成功!");
                tempSoDownload.close();
            } else {
                var index = layer.open({
                    title: '提示',
                    fix: false, //不固定
                    maxmin: true,
                    content: "手工抓单失败！message:" + data.message
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

$(function () {

});
