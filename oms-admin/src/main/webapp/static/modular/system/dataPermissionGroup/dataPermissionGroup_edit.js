
/**
 * 用户数据权限分组管理初始化
 */
var DataPermissionGroupRule = {
    id: "DataPermissionGroupRuleTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};


/**
 * 初始化表格的列
 */
DataPermissionGroupRule.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: '规则类型', field: 'typeName', visible: true, align: 'center', valign: 'middle'},
        {title: '操作', field: 'description', visible: true, align: 'center', valign: 'middle'}
    ];
};


/**
 * 初始化用户数据权限分组详情对话框
 */
var DataPermissionGroupInfoDlg = {
    dataPermissionGroupInfoData : {}
};

/**
 * 清除数据
 */
DataPermissionGroupInfoDlg.clearData = function() {
    this.dataPermissionGroupInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
DataPermissionGroupInfoDlg.set = function(key, val) {
    this.dataPermissionGroupInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
DataPermissionGroupInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
DataPermissionGroupInfoDlg.close = function() {
    parent.layer.close(window.parent.DataPermissionGroup.layerIndex);
}

/**
 * 收集数据
 */
DataPermissionGroupInfoDlg.collectData = function() {
    this
    .set('id',$('#groupId').val())
    .set('groupName')
    .set('description');
}

/**
 * 提交修改
 */
DataPermissionGroupInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/dataPermissionGroup/update", function(data){
        Feng.success("修改成功!");
        window.parent.DataPermissionGroup.table.refresh();
        DataPermissionGroupInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.dataPermissionGroupInfoData);
    ajax.start();
}


/**
 * 添加规则
 */
DataPermissionGroupInfoDlg.addRole = function() {
    var selected = $('#type option:selected').val();
    if(selected == ""){
        Feng.info("请先选择类型！");
        return false;
    }

    var index = layer.open({
        type: 2,
        title: '绑定数据权限',
        area: ['400px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/dataPermissionGroup/chooseRule/' + selected
    });
    this.layerIndex = index;
}

/**
 * 查询表单提交参数对象
 * @returns {{}}
 */
DataPermissionGroupRule.formParams = function() {
    var queryData = {};
    queryData['groupId'] = $("#groupId").val();
    return queryData;
}


$(function () {
    var defaultColunms = DataPermissionGroupRule.initColumn();
    var table = new BSTable(DataPermissionGroupRule.id, "/dataPermissionGroup/ruleList", defaultColunms);
    table.setPaginationType("client");
    table.setQueryParams(DataPermissionGroupRule.formParams());
    DataPermissionGroupRule.table = table.initPlus();
});

