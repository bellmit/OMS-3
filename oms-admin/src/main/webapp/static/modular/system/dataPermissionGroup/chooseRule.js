/**
 * 用户数据权限分组管理初始化
 */
var ChooseRule = {
    chooseRuleData : {}
};


/**
 * 关闭此对话框
 */
ChooseRule.close = function() {
    parent.layer.close(window.parent.DataPermissionGroupInfoDlg.layerIndex);
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ChooseRule.set = function(key, val) {
    this.chooseRuleData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}


/**
 * 收集数据
 */
ChooseRule.collectData = function() {

    var chk_value =[];
    $('input[name="rule"]:checked').each(function(){
        chk_value.push($(this).val());
    });
    if(chk_value.length==0){
        Feng.info("你还没有选择任何内容！");
        return false;
    }
    this.set('content',chk_value.toString()).set('groupId',parent.$('#groupId').val()).set('type');
    return true;
}


ChooseRule.chooseRule = function(){

    if(this.collectData()){
        //提交信息
        var ajax = new $ax(Feng.ctxPath + "/dataPermissionGroup/addRule", function(data){
            Feng.success("添加成功!");
            window.parent.DataPermissionGroupRule.table.refresh();
            ChooseRule.close();
        },function(data){
            Feng.error("添加失败!" + data.responseJSON.message + "!");
        });
        ajax.set(this.chooseRuleData);
        ajax.start();
    }

}

