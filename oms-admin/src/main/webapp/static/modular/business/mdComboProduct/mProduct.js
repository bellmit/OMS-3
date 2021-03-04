/**
 * 商品管理初始化
 */
var MdProductAdd = {
    id: "MdProductTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
//    operationBtn: {title: '操作', field: '', formatter: renderOperation, align: 'center', class: 'W120'},//操作列按钮
};

/**
 * 初始化表格的列
 */
//function renderOperation(value, row, index) {
//    return [//"+value+"
//        "<a onclick=\"checkOrder('" + row.productCode+ "')\">删除</a>"
//    ].join("''")
//}

function checkOrder(id) {
	var ids = [];//定义一个数组
    ids.push(id);//将要删除的id存入数组
	$('#addMdProductTable').bootstrapTable('remove', {
		field:'id',
		values:ids
	});
//	$("#addMdProductTable").bootstrapTable('removeAll');
}

function stateFormatter(value, row, index) {
//	    if (row.state == true)
	        return {
	            disabled : true,//设置是否可用
	            checked : true//设置选中
	        };
//	    return value;
	}
MdProductAdd.openProductEil = function () {
	if($('#merchantId').val()==''){
		Feng.info("请选择商家");
		return;
	} 
	
    var index = layer.open({
        type: 2,
        title: '添加明细',
        area: ['90%', '90%'], //宽高
        fix: false, //不固定
        resize: true,
        maxmin: true, //控制最大化最小化
        closeBtn: 1,// 控制关闭按钮显示
        content: Feng.ctxPath + '/mdComboProduct/mProduct_add?merchantId='+$('#merchantId').val()
    });
    MdProductAdd.layerIndex = index;
};


function ajaxAddSubmit(){
	 var selectedId = $('#addMdProductTable').bootstrapTable('getSelections');
        var ids = ""
        for (var i = 0; i < selectedId.length; i++) {
        	var id = selectedId[i].id ;
            ids = ids +id + "," +$('#numId'+id).val() + "," +$('#ffId'+id).val() + "," +$('#ffPriceId'+id).val()+"|"
        }
        var productName = $('#productName').val();
        
        if(productName == ""){
        	layer.msg('请输入组合品名称', {icon: 1});
        	return;
        }
        
        var ajax = new $ax(Feng.ctxPath + "/mdComboProduct/addMProduct_ajax", function (data) {
        		 if(data.indexOf("成功") != -1 ){
//        	        	
        	        	 layer.open({
                             title: '添加组合品信息提示',
                             btn: ['关闭'],
                             area: ['420px', '240px'],
                             content: data,
                             end:function (){
                            	 parent.layer.close(window.parent.MdProduct.layerIndex); 
                             }
                         });
        	        }else{
        	        	$('#failId').html(""+data)
        	        }
        		
//        	
        }, function (data) {
            layer.msg('添加失败 数据异常', {icon: 2});
//            $('#failId').html(""+data)
        });
        ajax.set("productMessage", ids);
        ajax.set("productName", productName);
        ajax.set("productCode", $('#productCode').val());
        ajax.set("cateName",$('#categoryResponsible').val());
        ajax.start();
}

function productCode(productCodes){   
	 var ajax = new $ax(Feng.ctxPath + "/mdComboProduct/mProduct_ajax", function (data) {
         $('#addMdProductTable').bootstrapTable('prepend', data); //the method of prepend must defined all fields，but append needn't  
     }, function (data) {
         layer.msg('添加失败', {icon: 1});
     });
     ajax.set("productCode", productCodes);
     ajax.set("merchantId", $('#merchantId').val());
     ajax.start();
	}

	$(function () {
		//1.初始化Table
		 $('#addMdProductTable').bootstrapTable({  
		       data:[],  
		        editable:true,//开启编辑模式  
		        clickToSelect: true,  
		        cache: false,  
//		        showToggle:true, //显示切换按钮来切换表/卡片视图。  
		        showPaginationSwitch:true, //显示分页切换按钮  
		        pagination: true,  
		        pageList: [100],  
		        pageSize:100,  
		        pageNumber:1,  
//		        uniqueId: 'index', //将index列设为唯一索引  
//		        striped: true,  
//		        search: true,  
//		        showRefresh: true,  
//		        minimumCountColumns: 0,  
		        smartDisplay:true,  
		        columns: [  
		            [  
		                {field: 'checked',checkbox:true,formatter:stateFormatter},
		                {title: '操作', field: '', visible: true,
		                	formatter:function(value, row, index)
		                	{ return ["<a onclick=\"checkOrder('" + row.id+ "')\">删除</a>"].join("''")}, align: 'center'},
		                {field:"productCode",title:"商品编码",edit:true,align:"center"},
		                {field:"originalCode",title:"关联编码",edit:true,align:"center"},
		                {field:"productCname",title:"商品名称",edit:true,align:"center"},
		                {field:"specification",title:"规格",edit:true,align:"center"},
//		                {field:"id",title:"ID",align:"center"},
		                {field:"expand2",title:"厂家",edit:true,align:"center"},
		                {field:"num",title:"数量",
		                	formatter:function(value, row, index){
		                		return ["<input type='text' value='1' id='numId"+row.id+"'/>"].join("''")}
		                	},
		                {field:"ff",title:"分摊占比%",edit:true,align:"center",
			                formatter:function(value, row, index){
			                return ["<input type='text'id='ffId"+row.id+"' value=''/>"].join("''")}
			             },
			             {field:"ffPrice",title:"应占售价",edit:true,align:"center",
				             formatter:function(value, row, index){
				             return ["<input type='text' id='ffPriceId"+row.id+"' value=''/>"].join("''")}
				         }
		               
		            ]  
		        ]  
		    }); 
	});


