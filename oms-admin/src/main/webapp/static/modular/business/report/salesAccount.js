/**
 * 管理初始化
 */
var SalesAccount = {
    id: "salesAccountTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    isNormalOrder: 1,  //1：正常订单  0:问题订单
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
SalesAccount.initColumn = function () {
    return [
        {field: 'selectItem', radio: false,visible:false},
        {title:'序号',formatter: function (value, row, index){return index+1;}, align: 'center'},
        {title: '订单号', field: 'platformOrderCode', visible: true, align: 'center', valign: 'middle'},
        {title: '支付单号', field: 'payOrderNo', visible: true, align: 'center', valign: 'middle', class: 'W80'},
        {title: '付款时间', field: 'orderPaymentConfirmDate', visible: true, align: 'center', valign: 'middle'},
        {title: '供应商 ', field: 'supplier', visible: true, align: 'center', valign: 'middle', class: 'W80'},
        {title: '仓库', field: 'warehouseName', visible: true, align: 'center', valign: 'middle', class: 'W150'},
        {title: '品牌', field: 'brandName', visible: true, align: 'center', valign: 'middle', class: 'W150'},
        {title: '进价（RMB）', field: 'bidCNY', visible: true, align: 'center', valign: 'middle', class: 'W120'},
        {title: '总进价', field: 'totalbid', visible: true, align: 'center', valign: 'middle', class: 'W80'},
        {title: '运费', field: 'fee', visible: true, align: 'center', valign: 'middle', class: 'W80'},
        {title: '申报价格', field: 'declarePrice', visible: true, align: 'center', valign: 'middle', class: 'W80'},
        {title: '税费', field: 'taxFcy', visible: true, align: 'center', valign: 'middle', class: 'W80'},
        {title: '总计', field: 'total', visible: true, align: 'center', valign: 'middle', class: 'W80'},
        {title: '单价', field: 'itemPrice', visible: true, align: 'center', valign: 'middle', class: 'W80'},
        {title: '数量', field: 'itemNum', visible: true, align: 'center', valign: 'middle', class: 'W80'},
        {title: '销售金额', field: 'itemAmount', visible: true, align: 'center', valign: 'middle', class: 'W80'},
        {title: '优惠卷', field: 'preferentialvolume', visible: true, align: 'center', valign: 'middle', class: 'W80'},
        {title: '快递单号', field: 'logisticsNo', visible: true, align: 'center', valign: 'middle', class: 'W80'},
        {title: '地址（省）', field: 'platformProvince', visible: true, align: 'center', valign: 'middle', class: 'W150'},
        {title: '地址（市）', field: 'platformCity', visible: true, align: 'center', valign: 'middle', class: 'W150'},
        {title: '地址（区）', field: 'district', visible: true, align: 'center', valign: 'middle', class: 'W150'},
        {title: '地址（路-号）', field: 'receiverAddress', visible: true, align: 'center', valign: 'middle', class: 'W150'},
        {title: '平台', field: 'platformName', visible: true, align: 'center', valign: 'middle', class: 'W80'},
        {title: '端口', field: 'source', visible: true, align: 'center', valign: 'middle', class: 'W80'},
        {title: '公司', field: 'companyName', visible: true, align: 'center', valign: 'middle', class: 'W150'},
        {title: 'BTOB/BTOC', field: 'btoborbtoc', visible: true, align: 'center', valign: 'middle'},
        {title: '医美/健康', field: 'mborh', visible: true, align: 'center', valign: 'middle', class: 'W80'},
        {title: '分销', field: 'distribution', visible: true, align: 'center', valign: 'middle', class: 'W60'},
        {title: '是否属于易付诊', field: 'ifetv', visible: true, align: 'center', valign: 'middle'},
        {title: '地推', field: 'groundpush', visible: true, align: 'center', valign: 'middle', class: 'W60'},
        {title: '分办', field: 'suboffice', visible: true, align: 'center', valign: 'middle', class: 'W60'},
        {title: '领祥', field: 'collarxiang', visible: true, align: 'center', valign: 'middle', class: 'W60'},
        {title: '购买人', field: 'payName', visible: true, align: 'center', valign: 'middle', class: 'W80'},
        {title: '电话', field: 'payPhone', visible: true, align: 'center', valign: 'middle', class: 'W60'},
        {title: '产品编号', field: 'pmInfoId', visible: true, align: 'center', valign: 'middle', class: 'W80'},
        {title: '商品名称', field: 'goodName', visible: true, align: 'center', valign: 'middle', class: 'W150'},
        {title: '批次', field: 'batch', visible: true, align: 'center', valign: 'middle', class: 'W80'},
        {title: '进价（美元）', field: 'bidUSD', visible: true, align: 'center', valign: 'middle', class: 'W120'},
        {title: '汇率', field: 'exchangerate', visible: true, align: 'center', valign: 'middle', class: 'W80'},
        {title: '是否核销', field: 'ifcancel', visible: true, align: 'center', valign: 'middle', class: 'W80'},
        {title: '备注', field: 'remark', visible: true, align: 'center', valign: 'middle', class: 'W80'},
    ];
};
/**
 * 检查是否选中
 */
SalesAccount.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
        SalesAccount.seItem = selected[0];
        return true;
    }
};


//订单详情----点击事件
function aFormatter(value, row, index) {
    return [//"+value+"
        "<a onclick=\"orderDetail('" + value + "')\">" + value + "</a>"
    ].join("''")
}


/**
 * 导出平台订单
 */
SalesAccount.exportOrder = function () {
    var queryData = {};
    queryData['platformOrderCode'] = $("#platformOrderCode").val();
    queryData['productCode'] = $("#productCode").val();
    queryData['payOrderNo'] = $("#payOrderNo").val();
    queryData['merchantExpressNbr'] = $("#merchantExpressNbr").val();
    queryData['productCname'] = $("#productCname").val();
    queryData['warehouseId'] = $("#warehouseId").val();
    queryData['shopId'] = $("#shopId").val();
    queryData['merchantId'] = $("#merchantId").val();
    queryData['timeType'] = $("#timeType").val();
    queryData['timeSearchBegin'] = $("#timeSearchBegin").val();
    queryData['timeSearchEnd'] = $("#timeSearchEnd").val();
    if($("#orderNoDeliverGoods").is(':checked')){
        queryData['orderNoDeliverGoods'] = 'selected';
    }else{
        queryData['orderNoDeliverGoods'] = 'unSelected';
    }
    // alert("queryData.orderNoDeliverGoods:"+queryData.orderNoDeliverGoods);
    if($("#removeHuangdaoOrder").is(':checked')){
        queryData['removeHuangdaoOrder'] = 'selected';
    }else{
        queryData['removeHuangdaoOrder'] = 'unSelected';
    }
    // queryData['receiverName'] = $("#receiverName").val();
    queryData['provinceId'] = $("#provinceId").val();
    queryData['cityId'] = $("#cityId").val();
    queryData['countyId'] = $("#countyId").val();

    var ajax = new $ax(Feng.ctxPath + "/report/salesAccount/exportPlarformOrder/"+SalesAccount.isNormalOrder, function(data){
        var index = layer.open({
            title: 'Excel导出提示信息',
            btn: ['关闭'],
            fix: false, //不固定
            maxmin: true,
            content: "您导出的数据已加入导出队列," +
            "请记好导出编号【" + data + "】,可通过导出编号在" +
            "<font color='red'>导出任务管理</font>" +
            "里查询和下载导出文件。"
        });
//	        window.parent.BWarehouse.table.refresh();
//	        BWarehouseInfoDlg.close();
    },function(data){
        Feng.error("导出失败!请联系管理员!");
    });
    ajax.set(queryData);
    ajax.start();

//    SoOrder.layerIndex = index;
}


/**
 * 查询列表
 */
SalesAccount.search = function () {
    var queryData = {};
    queryData['platformOrderCode'] = $("#platformOrderCode").val();
    queryData['productCode'] = $("#productCode").val();
    queryData['payOrderNo'] = $("#payOrderNo").val();
    queryData['merchantExpressNbr'] = $("#merchantExpressNbr").val();
    queryData['productCname'] = $("#productCname").val();
    queryData['warehouseId'] = $("#warehouseId").val();
    queryData['shopId'] = $("#shopId").val();
    queryData['merchantId'] = $("#merchantId").val();
    queryData['timeType'] = $("#timeType").val();
    queryData['timeSearchBegin'] = $("#timeSearchBegin").val();
    queryData['timeSearchEnd'] = $("#timeSearchEnd").val();
    if($("#orderNoDeliverGoods").is(':checked')){
        queryData['orderNoDeliverGoods'] = 'selected';
    }else{
        queryData['orderNoDeliverGoods'] = 'unSelected';
    }
    // alert("queryData.orderNoDeliverGoods:"+queryData.orderNoDeliverGoods);
    if($("#removeHuangdaoOrder").is(':checked')){
        queryData['removeHuangdaoOrder'] = 'selected';
    }else{
        queryData['removeHuangdaoOrder'] = 'unSelected';
    }
    // alert("queryData.removeHuangdaoOrder:"+queryData.removeHuangdaoOrder);
    // queryData['receiverName'] = $("#receiverName").val();
    queryData['provinceId'] = $("#provinceId").val();
    queryData['cityId'] = $("#cityId").val();
    queryData['countyId'] = $("#countyId").val();
    // console.log(queryData);
    SalesAccount.table.refresh({query: queryData});
    getTotalCountSum(queryData);
};

//计算订单明细中的  合计
function getTotalCountSum(queryData){
    var ajax = new $ax(Feng.ctxPath + "/report/salesAccount/getTotalCountSum/"+SalesAccount.isNormalOrder, function(data){
        // console.log(data.itemAmountTotal);
        $("#itemAmountTotal").text(parseFloat(data.itemAmountTotal).toFixed(2));//四舍五入保留两位小数
        $("#itemNumTotal").text(data.itemNumTotal);
        $("#taxFcyTotal").text(parseFloat(data.taxFcyTotal).toFixed(2));
        $("#platformOrderCodeTotal").text(data.platformOrderCodeTotal);
        $("#preferentialvolumeTotal").text(parseFloat(data.preferentialvolumeTotal).toFixed(2));
        $("#feeTotal").text(parseFloat(data.feeTotal).toFixed(2));
        $("#totalTotal").text(parseFloat(data.totalTotal).toFixed(2));
    },function(data){
        Feng.error("查询总计出错!");
    });
    ajax.set(queryData);
    ajax.start();
}

/**
 * 搜索条件重置
 */
SalesAccount.resetSearch = function () {
    $("#orderNoDeliverGoods").attr("checked",false);
    $("#removeHuangdaoOrder").attr("checked",true);
    Feng.resetForm("searchForm");
    getDefaultCondition();
    // SalesAccount.search();

};

/**
 * 初始化数据
 */
function initTableData() {
    var defaultColunms = SalesAccount.initColumn();
    var table = new BSTable(SalesAccount.id, "/report/salesAccount/list/" + SalesAccount.isNormalOrder, defaultColunms);
    table.setPaginationType("server");
    SalesAccount.table = table.init();
}

$(function () {
    // pickTimeDayT();
    initTableData();
    // getTotalCountSum();
});

//ajax请求  -- 获取省市区三级联动
$("#provinceId").bind("change", function () {
    //alert($("#province").val())
    var ajax = new $ax(Feng.ctxPath + "/tempSo/area", function (data) {
        var selDom = $("#cityId")
        selDom.empty();
        selDom.append("<option value=''>请选择市</option>");
        var selDomDistrict = $("#countyId")
        selDomDistrict.empty();
        selDomDistrict.append("<option value=''>请选择区</option>");
        for (var i = 0; i < data.length; i++) { //循环后台传过来的Json数组
            var pro = data[i]
            selDom.append("<option value='" + pro.id + "'>" + pro.name + "</option>");
        }
    }, function (data) {
        Feng.error("获取市失败!");
    });
    ajax.set("pid", $("#provinceId").val());
    ajax.start();
});

$("#cityId").bind("change", function () {
    var ajax = new $ax(Feng.ctxPath + "/tempSo/area", function (data) {
        var selDom = $("#countyId")
        selDom.empty();
        selDom.append("<option value=''>请选择区</option>");
        for (var i = 0; i < data.length; i++) { //循环后台传过来的Json数组
            var pro = data[i]
            selDom.append("<option value='" + pro.id + "'>" + pro.name + "</option>");
        }
    }, function (data) {
        Feng.error("获取区失败!");
    });
    ajax.set("pid", $("#cityId").val());
    ajax.start();
});

//时间选择--当天，昨天，本周，上周，本月，上月
//获取今天
var nowDate= new Date(); //当天日期
// console.log(nowDate);
//今天是本周的第几天==getDay
var nowDayOfWeek= nowDate.getDay();
// console.log(nowDayOfWeek);
//当前日==getDate
var nowDay = nowDate.getDate();
// console.log(nowDay);
//当前月--getmonth()这个方法获取的其实是索引值。他的值是从0开始的。所以要加1才会得到真正的月份
var nowMonth = nowDate.getMonth();
// console.log(nowMonth);
//当前年
var nowYear = nowDate.getFullYear();
// console.log(nowYear);
nowYear += (nowYear < 2000) ? 1900 : 0;
// console.log(nowYear);
//上月日期
var lastMonthDate = new Date();
// console.log(lastMonthDate);
lastMonthDate.setDate(1);//设置日期为1号
// console.log(lastMonthDate.setDate(1));
lastMonthDate.setMonth(lastMonthDate.getMonth()-1);
// console.log(ss);
// var lastYear = lastMonthDate.getYear();
// console.log(lastYear);
var lastMonth = lastMonthDate.getMonth();
// console.log(lastMonth);

//当天
function pickTimeDayT(){
    getDefaultCondition();
    var currentDate = new Date(); //当天日期
    var dayT = formatDate(currentDate);
    $("#timeSearchBegin").val(dayT);
    // alert($("#timeSearchBegin").val());
    $("#timeSearchEnd").val(dayT);
    // console.log("dayT:"+dayT);
}
//昨天
function pickTimeDayY(){
    getDefaultCondition();
    var yesterdayDate = new Date(nowYear, nowMonth, nowDay - 1);//昨天日期
    var dayY = formatDate(yesterdayDate);
    $("#timeSearchBegin").val(dayY);
    $("#timeSearchEnd").val(dayY);
    // console.log("dayY:"+dayY);
}
//本周
function pickTimeWeekT(){
    getDefaultCondition();
    //获得本周的开始日期
    var weekTStartDate = new Date(nowYear, nowMonth, nowDay - nowDayOfWeek +1); //从周一开始需要+1
    var weekTStart = formatDate(weekTStartDate);
    $("#timeSearchBegin").val(weekTStart);
    // console.log("weekTStart:"+weekTStart);
    //获得本周的结束日期
    var weekTEndDate = new Date(nowYear, nowMonth, nowDay + (7 - nowDayOfWeek));
    var weekTEnd = formatDate(weekTEndDate);
    $("#timeSearchEnd").val(weekTEnd);
    // console.log("weekTEnd:"+weekTEnd);
}
//上周
function pickTimeWeekY(){
    getDefaultCondition();
    //获得上周的开始日期
    var weekYStartDate = new Date(nowYear, nowMonth, nowDay - nowDayOfWeek -6);
    var weekYStart = formatDate(weekYStartDate);
    // console.log("weekYStart:"+weekYStart);
    $("#timeSearchBegin").val(weekYStart);
    //获得上周的结束日期
    var weekYEndDate = new Date(nowYear, nowMonth, nowDay + (7 - nowDayOfWeek - 7));
    var weekYEnd = formatDate(weekYEndDate);
    // console.log("weekYEnd:"+weekYEnd);
    $("#timeSearchEnd").val(weekYEnd);
}
//本月
function pickTimeMonthT(){
    getDefaultCondition();
    //获得本月的开始日期
    var monthTStartDate = new Date(nowYear, nowMonth, 1);
    var monthTStart = formatDate(monthTStartDate);
    // console.log("monthTStart:"+monthTStart);
    $("#timeSearchBegin").val(monthTStart);
    //获得本月的结束日期
    var monthTEndDate = new Date(nowYear, nowMonth, getMonthDays(nowMonth));
    var monthTEnd = formatDate(monthTEndDate);
    // console.log("monthTEnd:"+monthTEnd);
    $("#timeSearchEnd").val(monthTEnd);
}
//上月
function pickTimeMonthY(){
    getDefaultCondition();
    //获得上月开始时间
    var monthYStartDate = new Date(nowYear, lastMonth, 1);
    var monthYStart = formatDate(monthYStartDate);
    // console.log("monthYStart:"+monthYStart);
    $("#timeSearchBegin").val(monthYStart);
    //获得上月结束时间
    var monthYEndDate = new Date(nowYear, lastMonth, getMonthDays(lastMonth));
    var monthYEndDate = formatDate(monthYEndDate);
    // console.log("monthYEndDate:"+monthYEndDate);
    $("#timeSearchEnd").val(monthYEndDate);
}

//格式化日期：yyyy-MM-dd
function formatDate(date) {
    var myyear = date.getFullYear();
    var mymonth = date.getMonth()+1;
    var myday = date.getDate();
    if(mymonth < 10){
        mymonth = "0" + mymonth;
    }
    if(myday < 10){
        myday = "0" + myday;
    }
    return (myyear+"-"+mymonth + "-" + myday);
}
//获得某月的天数
function getMonthDays(myMonth){
    var monthStartDate = new Date(nowYear, myMonth, 1);
    var monthEndDate = new Date(nowYear, myMonth + 1, 1);
    var days = (monthEndDate - monthStartDate)/(1000 * 60 * 60 * 24);
    return days;
}

//重置的时候，若自主筛选条件为空，则默认为付款时间
function getDefaultCondition(){
    var timeType = $("#timeType").val();
    // console.log("timeType:"+timeType);
    if(timeType == "" || timeType == null || timeType == undefined){
        $("#timeType").val("2");
    }
}
