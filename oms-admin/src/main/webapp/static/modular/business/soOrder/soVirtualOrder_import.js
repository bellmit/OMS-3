/**
 * 管理初始化
 */
var soOrderRemark = {
    layerIndex: -1
};

/**
 * 关闭此对话框
 */
soOrderRemark.close = function () {
    var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
    parent.layer.close(index);  // 关闭layer
}

/**
 * 提交保存数据
 */
soOrderRemark.submit = function () {
    alert("提交的内容为："+$('#remarkContent').val());
    return ;
    $.ajax({
        type: "POST",
        url: Feng.ctxPath + '/tempSo/handleAbnormalGoods',
        traditional: true,
        data: {
            'abnormalGoodsData': JSON.stringify(submitData),
        },
        dataType: "json",
        success: function (data) {
            // alert(data.status);
            if (data.status == 'ok') {
                Feng.success("下载成功!");
                soOrderRemark.close();
            } else {
                var index = layer.open({
                    title: '提示',
                    fix: false, //不固定
                    maxmin: true,
                    content: "下载失败！ " + data.message
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
