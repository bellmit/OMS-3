export default {
    siteName: 'OMS 管理系统',
    minSiteMame: 'OMS',
    tokenKey: 'Authorization',
    USER_NAME_KEY: 'USER_NAME',
    MUTATION_FORM_TREE_DATA: "MUTATION_FORM_TREE_DATA",
    V_FORM_COMPONENTS_NAME: 'validateMyForm',
    TAX_BALANCE_EXCEL_OUT: '/oms/report/taxBalance/excelOut',
    TAX_BALANCE_IMPORT_EXCEL: '/oms/report/taxBalance/importExcel',
    DOWNLOAD_TEMP_SO_FILE_URL: window.location.protocol + "//" + window.location.hostname + (window.location.port ? ':' + window.location.port : '') + "/oms/tempSoDownload/download?fileIds=",
    SO_ORDER_EXCEL_OUT: '/oms/soOrder/excelOut?type=0',
    SO_ORDER_IMPORT_EXCEL: '/oms/soOrder/excelIn',
    UPLOAD_AVATAR_URL:'/oms/mgr/upload',
}
