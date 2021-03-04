 //ajax请求
	 $("#provinceId").bind("change", function () {
		 //alert($("#province").val())
    	   var ajax = new $ax(Feng.ctxPath + "/bWarehouse/area", function (data) {
             var selDom = $("#cityId")
             $("#addressName").val("")
             selDom.empty();
             selDom.append("<option value=''>请选择市</option>");
             var selDomDistrict = $("#countyId")
             selDomDistrict.empty();
             selDomDistrict.append("<option value=''>请选择区</option>");
           	for(var i = 0; i < data.length; i++) { //循环后台传过来的Json数组  
               var pro = data[i]
               selDom.append("<option value='"+pro.id+"'>"+pro.name+"</option>");
           	}  
         }, function (data) {
        	 Feng.error("获取市失败!");
         });
    	  ajax.set("pid", $("#provinceId").val());
         ajax.start(); 
	 });
	 
	 $("#cityId").bind("change", function () {
    	  var ajax = new $ax(Feng.ctxPath + "/bWarehouse/area", function (data) {
             var selDom = $("#countyId")
             $("#addressName").val("")
             selDom.empty();
             selDom.append("<option value=''>请选择区</option>");
           	for(var i = 0; i < data.length; i++) { //循环后台传过来的Json数组  
               var pro = data[i]
               selDom.append("<option value='"+pro.id+"'>"+pro.name+"</option>");
           	}  
         }, function (data) {
        	 Feng.error("获取区失败!");
         });
    	  ajax.set("pid", $("#cityId").val());
         ajax.start();
	 });
	 
	 $("#countyId").bind("change", function () {
		 $("#addressName").val("")
	 });