/**
 * 管理初始化
 */
var TempSoDownload = {
    id: "TempSoDownloadTaskTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 点击下载按钮
 */
TempSoDownload.downloadCheckFile = function () {
    if (TempSoDownload.check()) {
        let filePathArray = [];
        let fileCodeArray = [];
        TempSoDownload.seItem.forEach(function (item) {
            item.filePath = item.filePath.replace(/\\/g, "/");
            filePathArray.push(item.filePath);
            fileCodeArray.push(item.fileCode);
        });
        // alert("filePathArray.join(','):"+filePathArray.join(','));
        //开始下载
        downLoadFile(filePathArray.join(','), fileCodeArray.join(","))
    }
}


/**
 * 格式化操作列
 * @param value
 * @param row
 * @param index
 * @returns {string}
 */
function formatDownloadModal(value, row, index) {
    value = value.replace(/\\/g, "/");
    return [//"+value+"
        "<a onclick=\"downLoadFile('" + value + "','" + row.fileCode + "')\">下载</a>"
    ].join("''")
}

/**
 * 点击列超链接下载文件
 * @param filePathArray
 * @param fileCodeArray
 */
function downLoadFile(filePathArray, fileCodeArray) {
    // console.log(222)
    //开始下载
    let loading = layer.load(0, {shade: false}); //0代表加载的风格，支持0-2
    $.ajax({
        url: Feng.ctxPath + '/tempSoDownload/downloadValidate',
        type: 'POST',
        data: {filePath: filePathArray, fileCode: fileCodeArray},
        success: function (resp) {
            layer.close(loading);
            window.location.href = Feng.ctxPath + '/tempSoDownload/download?filePath=' + filePathArray;
        }, error: function (error) {
            layer.close(loading);
            if (error.responseJSON) {
                Feng.error('下载失败,' + error.responseJSON.message);
            } else {
                Feng.error("下载失败！");
            }

        }
    });
};


/**
 * 批量删除
 */
TempSoDownload.delete = function () {
    if (TempSoDownload.check()) {
        let fileCodes = [];
        TempSoDownload.seItem.forEach(function (item) {
            fileCodes.push(item.fileCode);
        });
        console.log(fileCodes)
        fileCodes = fileCodes.join(',');
        let confirmBox = layer.confirm('确认要删除文件编号为【' + fileCodes + '】的数据？', {
            btn: ['确定', '取消'],
            shade: false //不显示遮罩
        }, function () {
            let loading = layer.load(0, {shade: false}); //0代表加载的风格，支持0-2
            $.ajax({
                url: Feng.ctxPath + '/tempSoDownload/delete',
                type: 'POST',
                data: {fileCodes: fileCodes},
                success: function (resp) {
                    Feng.success("删除成功！");
                    layer.close(loading);
                    layer.close(confirmBox);
                    //刷新数据
                    TempSoDownload.table.refresh({});
                }, error: function (error) {
                    Feng.error("删除失败！");
                    layer.close(loading);
                    layer.close(confirmBox);
                }

            });
        });

    }
}


/**
 * 初始化表格的列
 */
TempSoDownload.initColumn = function () {
    return [
        {field: 'selectItem', radio: false},
        {
            title: '操作',
            field: 'filePath',
            visible: true,
            formatter: formatDownloadModal,
            align: 'center',
            valign: 'middle'
        },
        {title: '编号', field: 'fileCode', visible: true, align: 'center', valign: 'middle'},
        {title: '文件名称', field: 'fileName', visible: true, align: 'center', valign: 'middle'},
        {title: '状态', field: 'statusName', visible: true, align: 'center', valign: 'middle'},
        {title: '创建时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'},

    ];
};

/**
 * 检查是否选中
 */
TempSoDownload.check = function () {
    let selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
        TempSoDownload.seItem = selected;
        return true;
    }
};


/**
 * 重置搜索条件
 */
TempSoDownload.reset = function () {
    Feng.resetForm("searchForm");
    TempSoDownload.search();
}


/**
 * 查询列表
 */
TempSoDownload.search = function () {
    let queryData = {};
    queryData['fileCode'] = $("#fileCode").val();
    queryData['status'] = $("#status").val();
    TempSoDownload.table.refresh({query: queryData});
};

/**
 * 初始化表单数据
 */
TempSoDownload.initTableData = function () {
    let defaultColunms = TempSoDownload.initColumn();
    let table = new BSTable(TempSoDownload.id, "/tempSoDownload/list", defaultColunms);
    table.setPaginationType("server");
    TempSoDownload.table = table.init();
}

$(function () {
    TempSoDownload.initTableData();
});
