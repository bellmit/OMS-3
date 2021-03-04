import request from './build'

export const signIn = ((params) => {
    return request({
        url: '/oms/login',
        data: params,
        method: 'POST'
    });
});

export const signOut = (() => {
    return request({
        url: '/oms/logout',
        method: 'GET'
    });
});

export const getOnlineUserInfo = (() => {
    return request({
        url: '/oms/mgr/onlineUser',
        method: 'GET'
    });
});

export const isOnline = (() => {
    return request({
        url: '/oms/mgr/online',
        method: 'GET'
    });
});

export const getRoleMenu = (() => {
    return request({
        url: '/oms/roleMenu',
        method: 'GET'
    });
});

export const getUserManageList = ((params) => {
    return request({
        url: '/oms/mgr/list',
        data: params,
        method: 'POST'
    });
});

export const addUser = ((params) => {
    return request({
        url: '/oms/mgr/add',
        data: params,
        method: 'POST'
    });
});

export const editUser = ((params) => {
    return request({
        url: '/oms/mgr/edit',
        data: params,
        method: 'POST'
    });
});

export const deleteUser = ((params) => {
    return request({
        url: '/oms/mgr/delete',
        data: params,
        method: 'POST'
    });
});

export const resetPassword = ((params) => {
    return request({
        url: '/oms/mgr/reset',
        data: params,
        method: 'POST'
    });
});

export const changeUserPwd = ((params) => {
    return request({
        url: '/oms/mgr/changePwd',
        data: params,
        method: 'POST'
    });
});

export const freezeUser = ((params) => {
    return request({
        url: '/oms/mgr/freeze',
        data: params,
        method: 'POST'
    });
});

export const unfreezeUser = ((params) => {
    return request({
        url: '/oms/mgr/unfreeze',
        data: params,
        method: 'POST'
    });
});

export const getDeptTree = ((params) => {
    return request({
        url: '/oms/dept/tree',
        data: params,
        method: 'POST'
    });
});

export const addDept = ((params) => {
    return request({
        url: '/oms/dept/add',
        data: params,
        method: 'POST'
    });
});

export const updateDept = ((params) => {
    return request({
        url: '/oms/dept/update',
        data: params,
        method: 'POST'
    });
});

export const delDept = ((params) => {
    return request({
        url: '/oms/dept/delete',
        data: params,
        method: 'POST'
    });
});


export const getRoleList = ((params) => {
    return request({
        url: '/oms/role/list',
        data: params,
        method: 'POST'
    });
});

export const addRole = ((params) => {
    return request({
        url: '/oms/role/add',
        data: params,
        method: 'POST'
    });
});

export const setRoleAuthority = ((params) => {
    return request({
        url: '/oms/role/setAuthority',
        data: params,
        method: 'POST'
    });
});


export const editRole = ((params) => {
    return request({
        url: '/oms/role/edit',
        data: params,
        method: 'POST'
    });
});

export const delRole = ((params) => {
    return request({
        url: '/oms/role/remove',
        data: params,
        method: 'POST'
    });
});

export const getDictList = ((params) => {
    return request({
        url: '/oms/dict/list',
        data: params,
        method: 'POST'
    });
});

export const addDict = ((params) => {
    return request({
        url: '/oms/dict/add',
        data: params,
        method: 'POST'
    });
});

export const editDict = ((params) => {
    return request({
        url: '/oms/dict/update',
        data: params,
        method: 'POST'
    });
});

export const delDict = ((params) => {
    return request({
        url: '/oms/dict/delete',
        data: params,
        method: 'POST'
    });
});

export const getMenuList = ((params) => {
    return request({
        url: '/oms/menu/list',
        data: params,
        method: 'POST'
    });
});

export const addMenu = ((params) => {
    return request({
        url: '/oms/menu/add',
        data: params,
        method: 'POST'
    });
});

export const editMenu = ((params) => {
    return request({
        url: '/oms/menu/edit',
        data: params,
        method: 'POST'
    });
});

export const delMenu = ((params) => {
    return request({
        url: '/oms/menu/remove',
        data: params,
        method: 'POST'
    });
});

export const menuTreeListByRoleId = ((roleId) => {
    return request({
        url: '/oms/menu/menuTreeListByRoleId/' + roleId,
        method: 'GET'
    });
});

export const getLoginLogList = ((params) => {
    return request({
        url: '/oms/loginLog/list',
        data: params,
        method: 'POST'
    });
});

export const emptyLoginLog = (() => {
    return request({
        url: '/oms/loginLog/delLoginLog',
        method: 'POST'
    });
});


export const getOperationLogList = ((params) => {
    return request({
        url: '/oms/log/list',
        data: params,
        method: 'POST'
    });
});

export const emptyOperationLog = (() => {
    return request({
        url: '/oms/log/delLog',
        method: 'POST'
    });
});

export const operationLogDetail = ((params) => {
    return request({
        url: '/oms/log/detail',
        data: params,
        method: 'POST'
    });
});

export const noticeList = ((params) => {
    return request({
        url: '/oms/notice/list',
        data: params,
        method: 'POST'
    });
});

export const addNotice = ((params) => {
    return request({
        url: '/oms/notice/add',
        data: params,
        method: 'POST'
    });
});

export const delNotice = ((params) => {
    return request({
        url: '/oms/notice/delete',
        data: params,
        method: 'POST'
    });
});

export const editNotice = ((params) => {
    return request({
        url: '/oms/notice/update',
        data: params,
        method: 'POST'
    });
});

export const dataPermissionList = ((params) => {
    return request({
        url: '/oms/dataPermissionGroup/list',
        data: params,
        method: 'POST'
    });
});

export const addDataPermission = ((params) => {
    return request({
        url: '/oms/dataPermissionGroup/add',
        data: params,
        method: 'POST'
    });
});

export const updateDataPermission = ((params) => {
    return request({
        url: '/oms/dataPermissionGroup/update',
        data: params,
        method: 'POST'
    });
});


export const delDataPermission = ((params) => {
    return request({
        url: '/oms/dataPermissionGroup/delete',
        data: params,
        method: 'POST'
    });
});

export const dataPermissionRuleList = ((params) => {
    return request({
        url: '/oms/dataPermissionGroup/ruleList',
        data: params,
        method: 'POST'
    });
});

export const addDataPermissionRule = ((params) => {
    return request({
        url: '/oms/dataPermissionGroup/addRule',
        data: params,
        method: 'POST'
    });
});

export const delDataPermissionRule = ((params) => {
    return request({
        url: '/oms/dataPermissionGroup/deleteRule',
        data: params,
        method: 'POST'
    });
});

export const dataPermissionGroupType = ((params) => {
    return request({
        url: '/oms/dataPermissionGroup/dataPermissionGroupType',
        data: params,
        method: 'POST'
    });
});

export const chooseDataPermissionRule = ((params) => {
    return request({
        url: '/oms/dataPermissionGroup/chooseRule/' + params.type,
        data: params,
        method: 'GET'
    });
});

export const dataPermissionGroupNameList = ((params) => {
    return request({
        url: '/oms/dataPermissionGroup/groupNameList',
        data: params,
        method: 'POST'
    });
});

export const dataPermissionBindingGroup = ((params) => {
    return request({
        url: '/oms/dataPermissionGroup/bindingGroup',
        data: params,
        method: 'POST'
    });
});

export const jobList = ((params) => {
    return request({
        url: '/oms/job/list',
        data: params,
        method: 'POST'
    });
});

export const addJob = ((params) => {
    return request({
        url: '/oms/job/add',
        data: params,
        method: 'POST'
    });
});

export const editJob = ((params) => {
    return request({
        url: '/oms/job/edit',
        data: params,
        method: 'POST'
    });
});


export const deleteJob = ((params) => {
    return request({
        url: '/oms/job/deleteJob',
        data: params,
        method: 'POST'
    });
});

export const pauseJob = ((params) => {
    return request({
        url: '/oms/job/pauseJob',
        data: params,
        method: 'POST'
    });
});

export const resumeJob = ((params) => {
    return request({
        url: '/oms/job/resumeJob',
        data: params,
        method: 'POST'
    });
});

export const exportFilePageList = ((params) => {
    return request({
        url: '/oms/tempSoDownload/list',
        data: params,
        method: 'POST'
    });
});

export const deleteExportFileByCodes = ((params) => {
    return request({
        url: '/oms/tempSoDownload/delete',
        data: params,
        method: 'POST'
    });
});

export const validateExportFile = ((params) => {
    return request({
        url: '/oms/tempSoDownload/downloadValidate',
        data: params,
        method: 'POST'
    });
});

export const mdLogisticsCompanyList = ((params) => {
    return request({
        url: '/oms/mdLogisticsCompany/list',
        data: params,
        method: 'POST'
    });
});

export const addMdLogisticsCompany = ((params) => {
    return request({
        url: '/oms/mdLogisticsCompany/add',
        data: params,
        method: 'POST'
    });
});

export const editMdLogisticsCompany = ((params) => {
    return request({
        url: '/oms/mdLogisticsCompany/update',
        data: params,
        method: 'POST'
    });
});

export const delMdLogisticsCompany = ((params) => {
    return request({
        url: '/oms/mdLogisticsCompany/delete',
        data: params,
        method: 'POST'
    });
});

export const shopList = ((params) => {
    return request({
        url: '/oms/shop/list',
        data: params,
        method: 'POST'
    });
});

export const initShop = (() => {
    return request({
        url: '/oms/shop/init',
        method: 'GET'
    });
});


export const getShopById = ((id) => {
    return request({
        url: '/oms/shop/find/' + id,
        method: 'GET'
    });
});

export const addShop = ((params) => {
    return request({
        url: '/oms/shop/add',
        data: params,
        method: 'POST'
    });
});

export const updateShop = ((params) => {
    return request({
        url: '/oms/shop/update',
        data: params,
        method: 'POST'
    });
});
export const warehouseList = ((params) => {
    return request({
        url: '/oms/bWarehouse/list',
        data: params,
        method: 'POST'
    });
});

export const addWarehouse = ((params) => {
    return request({
        url: '/oms/bWarehouse/add',
        data: params,
        method: 'POST'
    });
});

export const updateWarehouse = ((params) => {
    return request({
        url: '/oms/bWarehouse/update',
        data: params,
        method: 'POST'
    });
});


export const freezeWarehouse = ((params) => {
    return request({
        url: '/oms/bWarehouse/freeze',
        data: params,
        method: 'POST'
    });
});

export const unfreezeWarehouse = ((params) => {
    return request({
        url: '/oms/bWarehouse/unfreeze',
        data: params,
        method: 'POST'
    });
});

export const deleteWarehouse = ((params) => {
    return request({
        url: '/oms/bWarehouse/delete',
        data: params,
        method: 'POST'
    });
});


export const getAreas = (() => {
    return request({
        url: '/oms/bWarehouse/getArea',
        method: 'GET'
    });
});


export const getAreaByPid = ((pid) => {
    return request({
        url: '/oms/bWarehouse/area/' + pid,
        method: 'GET'
    });
});

export const findWarehouseById = ((id) => {
    return request({
        url: '/oms/bWarehouse/find/' + id,
        method: 'GET'
    });
});

export const salesAccountList = ((params) => {
    return request({
        url: '/oms/report/salesAccount/list',
        data: params,
        method: 'POST'
    });
});

export const salesAccountInit = ((params) => {
    return request({
        url: '/oms/report/salesAccount/init',
        data: params,
        method: 'POST'
    });
});

export const salesAccountDownloadExport = ((params) => {
    return request({
        url: '/oms/report/salesAccount/exportPlarformOrder',
        data: params,
        method: 'POST'
    });
});

export const taxBalanceList = ((params) => {
    return request({
        url: '/oms/report/taxBalance/list',
        data: params,
        method: 'POST'
    });
});

export const taxBalanceInit = ((params) => {
    return request({
        url: '/oms/report/taxBalance/init',
        data: params,
        method: 'POST'
    });
});

export const taxBalanceDownloadExport = ((params) => {
    return request({
        url: '/oms/report/taxBalance/exportPlarformOrder',
        data: params,
        method: 'POST'
    });
});

export const taxStatementList = ((params) => {
    return request({
        url: '/oms/report/taxStatement/list',
        data: params,
        method: 'POST'
    });
});

export const taxStatementInit = ((params) => {
    return request({
        url: '/oms/report/taxStatement/init',
        data: params,
        method: 'POST'
    });
});

export const taxStatementDownloadExport = ((params) => {
    return request({
        url: '/oms/report/taxStatement/exportPlarformOrder',
        data: params,
        method: 'POST'
    });
});


export const warehouseStockList = ((params) => {
    return request({
        url: '/oms/pmWarehouseStock/list',
        data: params,
        method: 'POST'
    });
});

export const addWarehouseStock = ((params) => {
    return request({
        url: '/oms/pmWarehouseStock/add',
        data: params,
        method: 'POST'
    });
});

export const updateWarehouseStock = ((params) => {
    return request({
        url: '/oms/pmWarehouseStock/update',
        data: params,
        method: 'POST'
    });
});

export const getWarehouseStockDetail = ((id) => {
    return request({
        url: '/oms/pmWarehouseStock/detail/' + id,
        method: 'GET'
    });
});

export const brandList = ((params) => {
    return request({
        url: '/oms/brand/list',
        data: params,
        method: 'POST'
    });
});

export const addBrand = ((params) => {
    return request({
        url: '/oms/brand/add',
        data: params,
        method: 'POST'
    });
});

export const updateBrand = ((params) => {
    return request({
        url: '/oms/brand/update',
        data: params,
        method: 'POST'
    });
});

export const deleteBrand = ((params) => {
    return request({
        url: '/oms/brand/delete',
        data: params,
        method: 'POST'
    });
});

export const merchantList = ((params) => {
    return request({
        url: '/oms/merchant/list',
        data: params,
        method: 'POST'
    });
});
export const addMerchant = ((params) => {
    return request({
        url: '/oms/merchant/add',
        data: params,
        method: 'POST'
    });
});
export const updateMerchant = ((params) => {
    return request({
        url: '/oms/merchant/update',
        data: params,
        method: 'POST'
    });
});
export const deleteMerchant = ((params) => {
    return request({
        url: '/oms/merchant/delete',
        data: params,
        method: 'POST'
    });
});

export const productList = ((params) => {
    return request({
        url: '/oms/mdProduct/list',
        data: params,
        method: 'POST'
    });
});

export const productEditInit = ((params) => {
    return request({
        url: '/oms/mdProduct/mdProduct_add',
        data: params,
        method: 'POST'
    });
});

export const addProduct = ((params) => {
    return request({
        url: '/oms/mdProduct/add',
        data: params,
        method: 'POST'
    });
});

export const updateProduct = ((params) => {
    return request({
        url: '/oms/mdProduct/update',
        data: params,
        method: 'POST'
    });
});

export const deleteProduct = ((params) => {
    return request({
        url: '/oms/mdProduct/delete',
        data: params,
        method: 'POST'
    });
});

export const productDetail = ((mdProductId) => {
    return request({
        url: '/oms/mdProduct/detail/' + mdProductId,
        method: 'GET'
    });
});


export const tempSoList = ((params) => {
    return request({
        url: '/oms/tempSo/list',
        data: params,
        method: 'POST'
    });
});

export const tempSoInit = ((params) => {
    return request({
        url: '/oms/tempSo/init',
        data: params,
        method: 'POST'
    });
});

export const tempSoExport = ((params) => {
    return request({
        url: '/oms/tempSo/exportPlarformOrder',
        data: params,
        method: 'POST'
    });
});

export const tempSoGenerate = ((params) => {
    return request({
        url: '/oms/tempSo/generateOrderManual',
        data: params,
        method: 'POST'
    });
});

export const tempSoPullingOrder = ((params) => {
    return request({
        url: '/oms/tempSo/catchedOrderManual',
        data: params,
        method: 'POST'
    });
});

export const soOrderList = ((params) => {
    return request({
        url: '/oms/soOrder/list',
        data: params,
        method: 'POST'
    });
});

export const soOrderInit = ((params) => {
    return request({
        url: '/oms/soOrder/init',
        data: params,
        method: 'POST'
    });
});

export const soOrderExportVirtualOrder = ((params) => {
    return request({
        url: '/oms/soOrder/exportVirtualOrder',
        data: params,
        method: 'POST'
    });
});

export const soOrderExportTrueOut = ((params) => {
    return request({
        url: '/oms/soOrder/excelTrueOut',
        data: params,
        method: 'POST'
    });
});

export const soOrderPassStatus = ((params) => {
    return request({
        url: '/oms/soOrder/passStatus',
        data: params,
        method: 'POST'
    });
});

export const soOrderCancelOrder = ((params) => {
    return request({
        url: '/oms/soOrder/cancelOrder',
        data: params,
        method: 'POST'
    });
});

export const soOrderRemark = ((params) => {
    return request({
        url: '/oms/soOrder/updateRemark',
        data: params,
        method: 'POST'
    });
});
export const soOrderUserUpdateInit = ((soOrderId) => {
    return request({
        url: '/oms/soOrder/soOrderUserUpdate/' + soOrderId,
        method: 'POST'
    });
});

export const soOrderUserUpdate = ((params) => {
    return request({
        url: '/oms/soOrder/addSoOrderUserUpdate',
        data: params,
        method: 'POST'
    });
});

export const soOperateLogList = ((params) => {
    return request({
        url: '/oms/soOperateLog/list',
        data: params,
        method: 'POST'
    });
});

export const tempSoOperateLog = ((params) => {
    return request({
        url: '/oms/tempSoOperateLog/list',
        data: params,
        method: 'POST'
    });
});

export const tempSoDetail = ((soOrderId) => {
    return request({
        url: '/oms/tempSo/tempSoDetail/' + soOrderId,
        method: 'POST'
    });
});


export const tempSoItemList = ((params) => {
    return request({
        url: '/oms/tempSoItem/list',
        data: params,
        method: 'POST'
    });
});

export const soOrderDetail = ((soOrderId) => {
    return request({
        url: '/oms/soOrder/soOrderDetail/' + soOrderId,
        method: 'POST'
    });
});


export const soOrderItemList = ((params) => {
    return request({
        url: '/oms/soItem/list',
        data: params,
        method: 'POST'
    });
});

export const soOrderCarryRemark = ((soOrderId) => {
    return request({
        url: '/oms/soOrder/carryRemark/' + soOrderId,
        method: 'POST'
    });
});

export const soOrderUpdateCarry = ((params) => {
    return request({
        url: '/oms/soOrder/updateCarry',
        data: params,
        method: 'POST'
    });
});

export const grfHeaderInit = (() => {
    return request({
        url: '/oms/grfHeader/init',
        method: 'GET'
    });
});

export const grfHeaderList = ((params) => {
    return request({
        url: '/oms/grfHeader/list',
        data: params,
        method: 'POST'
    });
});

export const grfHeaderDetail = ((grfHeaderId) => {
    return request({
        url: '/oms/grfHeader/detail/' + grfHeaderId,
        method: 'POST'
    });
});

export const refundOrderInit = (() => {
    return request({
        url: '/oms/refundOrder/init',
        method: 'GET'
    });
});

export const refundOrderList = ((params) => {
    return request({
        url: '/oms/refundOrder/list',
        data: params,
        method: 'POST'
    });
});

export const tempSOToAbnormal = ((params) => {
    return request({
        url: '/oms/tempSo/toAbnormal/' + params.tempSoId,
        data: params,
        method: 'POST'
    });
});

export const tempSOToHandleAbnormalGoods = ((params) => {
    return request({
        url: '/oms/tempSo/handleAbnormalGoods',
        data: params,
        method: 'POST'
    });
});

export const mdComboProductList = ((params) => {
    return request({
        url: '/oms/mdComboProduct/list',
        data: params,
        method: 'POST'
    });
});

export const addMdComboProduct = ((params) => {
    return request({
        url: '/oms/mdComboProduct/add',
        data: params,
        method: 'POST'
    });
});


export const mdComboProductDetail = ((productId) => {
    return request({
        url: '/oms/mdComboProduct/detail/' + productId,
        method: 'GET'
    });
});

export const updateMdComboProduct = ((params) => {
    return request({
        url: '/oms/mdComboProduct/update',
        data: params,
        method: 'POST'
    });
});

export const deleteMdComboProduct = ((comboProductId) => {
    return request({
        url: '/oms/mdComboProduct/delete/' + comboProductId,
        method: 'GET'
    });
});