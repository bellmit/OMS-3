
/**
 * 用户数据权限分组管理初始化
 */
var UserDataAssign = {
    id: "UserDataAssignTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 检查是否选中
 */
UserDataAssign.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
        UserDataAssign.seItem = selected[0];
        return true;
    }
};


/**
 * 初始化表格的列
 */
UserDataAssign.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '分组名称', field: 'groupName', visible: true, align: 'center', valign: 'middle'},
        {title: '名称说明', field: 'description', visible: true, align: 'center', valign: 'middle'}
    ];
};


/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
UserDataAssign.set = function(key, val) {
    this.UserDataAssignData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
UserDataAssign.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
UserDataAssign.close = function() {
    parent.layer.close(window.parent.UserDataAssign.layerIndex);
}

/**
 * 收集数据
 */
UserDataAssign.collectData = function() {
    this
    .set('id',$('#groupId').val())
    .set('groupName')
    .set('description');
}


/**
 * 添加绑定
 */
UserDataAssign.bindingGroup = function() {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/dataPermissionGroup/bindingGroup", function (data) {
            Feng.success("绑定成功!");
            MdProduct.table.refresh();
        }, function (data) {
            Feng.error("绑定失败!" + data.responseJSON.message + "!");
        });
        ajax.set("groupId", this.seItem.id);
        ajax.set("userId", $("#userId").val());
        ajax.start();
    }
}


/**
 * 解除绑定
 */
UserDataAssign.delBindingGroup = function() {
    var ajax = new $ax(Feng.ctxPath + "/dataPermissionGroup/delBindingGroup", function (data) {
        Feng.success("解除绑定成功!");
        MdProduct.table.refresh();
    }, function (data) {
        Feng.error("解除绑定失败!" + data.responseJSON.message + "!");
    });
    ajax.set("userId", $("#userId").val());
    ajax.start();
}


/**
 * 重置搜索条件
 */
UserDataAssign.reset = function () {
    Feng.resetForm("searchForm");
    MdProduct.search();
}

/**
 * 查询列表
 */
UserDataAssign.search = function () {
    var queryData = {};
    queryData['groupName'] = $("#groupName").val();
    UserDataAssign.table.refresh({query: queryData});
};


/**
 * 查询表单提交参数对象
 * @returns {{}}
 */
UserDataAssign.formParams = function() {
    var queryData = {};
    queryData['groupName'] = $("#groupName").val();
    return queryData;
}


$(function () {
    var defaultColunms = UserDataAssign.initColumn();
    var table = new BSTable(UserDataAssign.id, "/dataPermissionGroup/groupNameList", defaultColunms);
    table.setPaginationType("client");
    table.setQueryParams(UserDataAssign.formParams());
    UserDataAssign.table = table.initPlus();
});

