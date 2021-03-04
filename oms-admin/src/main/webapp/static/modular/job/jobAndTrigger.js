/**
 * 任务管理初始化
 */
var JobAndTrigger = {
    id: "JobAndTriggerTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
JobAndTrigger.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '任务组名称', field: 'jobGroup', visible: true, align: 'center', valign: 'middle'},
            {title: '定时任务名称', field: 'jobName', visible: true, align: 'center', valign: 'middle'},
            {title: '触发器组名称', field: 'triggerGroupName', visible: true, align: 'center', valign: 'middle'},
            {title: '触发器名称', field: 'triggerName', visible: true, align: 'center', valign: 'middle'},
            {title: '时间表达式', field: 'cronExpr', visible: true, align: 'center', valign: 'middle'},
            {title: '上次运行时间', field: 'previousFireTime', visible: true, align: 'center', valign: 'middle'},
            {title: '下次运行时间', field: 'nextFireTime', visible: true, align: 'center', valign: 'middle'},
            {title: '运行状态', field: 'jobStatus', visible: true, align: 'center', valign: 'middle'},
            {title: '开始时间', field: 'startTime', visible: true, align: 'center', valign: 'middle'},
            {title: '结束时间', field: 'endTime', visible: true, align: 'center', valign: 'middle'},
            {title: '任务类名', field: 'jobClass', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 添加任务
 */
JobAndTrigger.openAddJob = function () {
    var index = layer.open({
        type: 2,
        title: '添加任务',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/job/toAdd'
    });
    this.layerIndex = index;
};


/**
 * 检查是否被选中
 * @returns {boolean}
 */
JobAndTrigger.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        JobAndTrigger.seItem = selected[0];
        return true;
    }
};

/**
 * 编辑定时任务详细信息
 */
JobAndTrigger.openJobDetail = function () {
    if (this.check()) {
        ;  var queryData={};
        queryData['jobName']=  this.seItem['jobName'];
        queryData['jobGroupName'] = this.seItem['jobGroup'];
        queryData['triggerName'] =this.seItem['triggerName'];
        queryData['triggerGroupName'] = this.seItem['triggerGroupName'];
        var json=JSON.stringify(queryData);
        var index = layer.open({
            type: 2,
            title: '任务管理详情',
            area: ['1000px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/job/toEdit?json='+encodeURIComponent(json)
        });
        this.layerIndex = index;
    }
};



/**
 * 暂停定时任务
 */
JobAndTrigger.pauseJob = function () {
    if(this.check()){
        var ajax = new $ax(Feng.ctxPath + "/job/pauseJob", function (data) {
            JobAndTrigger.table.refresh();
            Feng.success("暂停成功!");
        }, function (data) {
            Feng.error("暂停失败!" + data.responseJSON.message + "!");
        });
        var queryData={};
        queryData['jobName']=  this.seItem['jobName'];
        queryData['jobGroupName'] = this.seItem['jobGroup'];
        queryData['triggerName'] =this.seItem['triggerName'];
        queryData['triggerGroupName'] = this.seItem['triggerGroupName'];
        ajax.setData(queryData);
        ajax.start();
    }
};

/**
 * 恢复定时任务
 */
JobAndTrigger.resumeJob = function () {
    if(this.check()){
        var ajax = new $ax(Feng.ctxPath + "/job/resumeJob", function (data) {
            JobAndTrigger.table.refresh();
            Feng.success("恢复成功!");
        }, function (data) {
            Feng.error("恢复失败!" + data.responseJSON.message + "!");
        });
        var queryData={};
        queryData['jobName']=  this.seItem['jobName'];
        queryData['jobGroupName'] = this.seItem['jobGroup'];
        queryData['triggerName'] =this.seItem['triggerName'];
        queryData['triggerGroupName'] = this.seItem['triggerGroupName'];
        ajax.setData(queryData);
        ajax.start();
    }
};


/**
* 删除定时任务
*/
JobAndTrigger.deleteJob = function () {
    if(this.check()){
        var ajax = new $ax(Feng.ctxPath + "/job/deleteJob", function (data) {
            JobAndTrigger.table.refresh();
            Feng.success("删除成功!");
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        var queryData={};
        queryData['jobName']=  this.seItem['jobName'];
        queryData['jobGroupName'] = this.seItem['jobGroup'];
        queryData['triggerName'] =this.seItem['triggerName'];
        queryData['triggerGroupName'] = this.seItem['triggerGroupName'];
        ajax.setData(queryData);
        ajax.start();
    }
};




$(function () {
    var defaultColunms = JobAndTrigger.initColumn();
    var table = new BSTable(JobAndTrigger.id, "/job/list", defaultColunms);
    table.setPaginationType("client");
    JobAndTrigger.table = table.init();
});
