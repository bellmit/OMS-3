/**
 * 管理初始化
 */
var soOrderCarry = {
    layerIndex: -1
};

/**
 * 关闭此对话框
 */
soOrderCarry.close = function () {
    var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
    parent.layer.close(index);  // 关闭layer
}

/**
 * 提交保存数据
 */

soOrderCarry.submit = function () {
//	alert($('#orderCodeId').val())
//	alert($('#carrid').val())
//	alert()
//	return;
  var ajax = new $ax(Feng.ctxPath + "/soOrder/updateRealCarry", function (data) {
//  	alert(data)
  	if(data == 'ERROR'){
  		 layer.msg('修改失败', {icon: 1});
  		 return;
  	}
  	parent.layer.close(1);
      Feng.success("修改成功!"); 
  }, function (data) {
      layer.msg('修改失败', {icon: 1});
//		    	 Feng.error("审核失败!" + data.responseJSON.message + "!");
  });
  ajax.set("orderCode", $('#orderCodeId').val());
  ajax.set("carryId", $('#carrid').val());
  ajax.set("carryName", $('#carrid option:selected').text());
  ajax.start();
}

$(function () {

});
