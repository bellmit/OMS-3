/**
 * 用户数据权限分组管理初始化
 */
var DataPermissionGroup = {
    id: "DataPermissionGroupTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
DataPermissionGroup.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '分组名称', field: 'groupName', visible: true, align: 'center', valign: 'middle'},
        {title: '分组说明', field: 'description', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
DataPermissionGroup.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        DataPermissionGroup.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加用户数据权限分组
 */
DataPermissionGroup.openAddDataPermissionGroup = function () {
    var index = layer.open({
        type: 2,
        title: '添加用户数据权限分组',
        area: ['800px', '300px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/dataPermissionGroup/dataPermissionGroup_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看用户数据权限分组详情
 */
DataPermissionGroup.openDataPermissionGroupDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '用户数据权限分组详情',
            area: ['800px', '600px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/dataPermissionGroup/dataPermissionGroup_update/' + DataPermissionGroup.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除用户数据权限分组
 */
DataPermissionGroup.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/dataPermissionGroup/delete", function (data) {
            Feng.success("删除成功!");
            DataPermissionGroup.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("dataPermissionGroupId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询用户数据权限分组列表
 */
DataPermissionGroup.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    DataPermissionGroup.table.refresh({query: queryData});
};




$(function () {
    var defaultColunms = DataPermissionGroup.initColumn();
    var table = new BSTable(DataPermissionGroup.id, "/dataPermissionGroup/list", defaultColunms);
    table.setPaginationType("client");
    DataPermissionGroup.table = table.init();
});
