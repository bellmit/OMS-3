
/*var SoOrder = {
    id: "SoOrderTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1,
    operationBtn: {title: '操作', field: '', formatter: renderOperation, align: 'center'},//操作列按钮
    viewLogBtn: {title: '查看日志', field: '', formatter: renderViewLog, align: 'center'},//查看日志按钮
};*/


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
//    alert("提交的内容为："+$('#remarkContent').val());
//    alert("提交的code："+$('#orderCodeId').val());
	
//	 var input1 =$("input").eq(0);//取出第一个input
//     var id1=input1.attr("id");//取出第一个input的id
	var price="";
	var prouct=""

//		alert("dd=="+$('input[id^="carryPriceId"]').val());
	var count = parseInt($('#countId').val());
	
	if($('#carryShopId').val() == ''){
		layer.msg('代发供应商不能为空', {icon: 2});
		return;
	}
	//productId~price~carryNo~carryNameId||productId~price~carryNo~carryNameId
	var message = "";
	for (var i=1;i<count+1;i++)
	{
//	document.write(cars[i] + "<br>");
		if($('#carryPriceId'+i).val() == ''){
			layer.msg('代发价格不能为空', {icon: 2});
			return;
		}
		if($('#carryNoId'+i).val() == ''){
			layer.msg('运单号不能为空', {icon: 2});
			return;
		}
		if($('#carryNameId'+i).val() == ''){
			layer.msg('快递公司不能为空', {icon: 2});
			return;
		}
		var partMessage = $('#productId'+i).val()+"~"+$('#carryPriceId'+i).val()+"~"+$('#carryNoId'+i).val()+"~"+$('#carryNameId'+i).val();
		message = message + partMessage +"|";
	}
	
//    return ;
    
    var ajax = new $ax(Feng.ctxPath + "/soOrder/updateCarry", function (data) {
//    	alert(data)
    	if(data == 'ERROR'){
    		 layer.msg('修改失败', {icon: 2});
    		 return;
    	}
    	if(data == '30'){
   		 layer.msg('错误 格式错误', {icon: 2});
   		 return;
   	}
    	if(data == '40'){
   		 layer.msg('错误 无此快递公司', {icon: 2});
   		 return;
   	}
    	parent.layer.close(1);
        Feng.success("修改成功!"); 
//        SoOrder.table.refresh();
        window.parent.SoOrder.table.refresh();
    }, function (data) {
        layer.msg('修改失败', {icon: 1});
//		    	 Feng.error("审核失败!" + data.responseJSON.message + "!");
    });
    ajax.set("orderCode", $('#orderCodeId').val());
    ajax.set("message", message);
    ajax.set("carryShopName", $('#carryShopId').val());
    ajax.start();
}

$(function () {

});
