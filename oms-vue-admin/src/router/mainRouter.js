export const routerView = [
    //Index
    {
        path: '/',
        name: 'Dashboard',
        meta: {
            title: '首页',
            keepAlive: false
        },
        component: resolve => require(['~/views/Home'], resolve),
    },
    //System Manage
    {
        path: '/user_list',
        name: 'UserList',
        meta: {
            title: '用户管理',
            keepAlive: false
        },
        component: resolve => require(['~/views/SystemManage/UserManage'], resolve),
    },
    {
        path: '/role_list',
        name: 'RoleList',
        meta: {
            title: '角色管理',
            keepAlive: false
        },
        component: resolve => require(['~/views/SystemManage/RoleManage'], resolve),
    },
    {
        path: '/dept',
        name: 'Dept_Manage',
        meta: {
            title: '部门管理',
            keepAlive: false
        },
        component: resolve => require(['~/views/SystemManage/DeptManage'], resolve),
    },
    {
        path: '/dict',
        name: 'Dict_Manage',
        meta: {
            title: '字典管理',
            keepAlive: false
        },
        component: resolve => require(['~/views/SystemManage/DictManage'], resolve),
    },
    {
        path: '/menu',
        name: 'Menu_Manage',
        meta: {
            title: '菜单管理',
            keepAlive: false
        },
        component: resolve => require(['~/views/SystemManage/MenuManage'], resolve),
    },
    {
        path: '/loginLog',
        name: 'Login_Log',
        meta: {
            title: '登录日志',
            keepAlive: true
        },
        component: resolve => require(['~/views/SystemManage/LoginLog'], resolve),
    },
    {
        path: '/business_log',
        name: 'Business_Log',
        meta: {
            title: '业务日志',
            keepAlive: true
        },
        component: resolve => require(['~/views/SystemManage/BusinessLog'], resolve),
    },
    {
        path: '/notice',
        name: 'Notice_Manage',
        meta: {
            title: '通知管理',
            keepAlive: true
        },
        component: resolve => require(['~/views/SystemManage/NoticeManage'], resolve),
    },
    {
        path: '/data_permission',
        name: 'Data_Permission',
        meta: {
            title: '数据权限分组',
            keepAlive: true
        },
        component: resolve => require(['~/views/SystemManage/DataPermission'], resolve),
    },
    {
        path: '/time_task',
        name: 'Time_Task',
        meta: {
            title: '定时任务管理',
            keepAlive: true
        },
        component: resolve => require(['~/views/SystemManage/TimeTaskManage'], resolve),
    },
    //order manage
    {
        path: '/platForm_order',
        name: 'Platform_Order',
        meta: {
            title: '平台订单',
            keepAlive: true
        },
        component: resolve => require(['~/views/OrderManage/PlatformOrder'], resolve),
    },
    {
        path: '/so_order',
        name: 'So_Order',
        meta: {
            title: '实仓订单',
            keepAlive: true
        },
        component: resolve => require(['~/views/OrderManage/SoOrder'], resolve),
    }, {
        path: '/virtual_order',
        name: 'Virtual_Order',
        meta: {
            title: '虚仓订单',
            keepAlive: true
        },
        component: resolve => require(['~/views/OrderManage/VirtualOrder'], resolve),
    },
    //售后
    {
        path: '/exchange_order',
        name: 'Exchange_Order',
        meta: {
            title: '退换货管理',
            keepAlive: true
        },
        component: resolve => require(['~/views/AfterSale/ExchangeOrder'], resolve),
    },
    {
        path: '/refund_order',
        name: 'Refund_Order',
        meta: {
            title: '退款单管理',
            keepAlive: true
        },
        component: resolve => require(['~/views/AfterSale/RefundOrder'], resolve),
    },
    {
        path: '/goods_manage',
        name: 'Goods_Manage',
        meta: {
            title: '商品管理',
            keepAlive: true
        },
        component: resolve => require(['~/views/MaintainGoods/GoodsManage'], resolve),
    }, {
        path: '/combo_product',
        name: 'Combo_Product',
        meta: {
            title: '组合商品',
            keepAlive: true
        },
        component: resolve => require(['~/views/MaintainGoods/ComboProduct'], resolve),
    }, {
        path: '/brand_manage',
        name: 'Brand_Manage',
        meta: {
            title: '品牌管理',
            keepAlive: true
        },
        component: resolve => require(['~/views/MaintainGoods/BrandManage'], resolve),
    }, {
        path: '/merchant_manage',
        name: 'Merchant_Manage',
        meta: {
            title: '商家管理',
            keepAlive: true
        },
        component: resolve => require(['~/views/MaintainGoods/MerchantManage'], resolve),
    },
    {
        path: '/warehouse_manage',
        name: 'Warehouse_Manage',
        meta: {
            title: '仓库管理',
            keepAlive: true
        },
        component: resolve => require(['~/views/Inventory/Warehouse'], resolve),
    },
    {
        path: '/goods_stock',
        name: 'Goods_Stock',
        meta: {
            title: '商品库存',
            keepAlive: true
        },
        component: resolve => require(['~/views/Inventory/GoodsStock'], resolve),
    },
    //店铺管理
    {
        path: '/shop_manage',
        name: 'Shop_Manage',
        meta: {
            title: '店铺管理',
            keepAlive: true
        },
        component: resolve => require(['~/views/PlatformManage/ShopManage'], resolve),
    },
    {
        path: '/purchasing_agent',
        name: 'Purchasing_Agent',
        meta: {
            title: '供应商管理',
            keepAlive: true
        },
        component: resolve => require(['~/views/PurchasingManage/PurchasingAgent'], resolve),
    },
    {
        path: '/purchasing_order',
        name: 'Purchasing_Order',
        meta: {
            title: '采购单管理',
            keepAlive: true
        },
        component: resolve => require(['~/views/PurchasingManage/PurchasingOrder'], resolve),
    }, {
        path: '/express_company',
        name: 'Express_Company',
        meta: {
            title: '快递公司设置',
            keepAlive: true
        },
        component: resolve => require(['~/views/Setting/ExpressCompany'], resolve),
    },
    {
        path: '/export_task',
        name: 'Export_Task',
        meta: {
            title: '导出任务',
            keepAlive: true
        },
        component: resolve => require(['~/views/MyTask/ExportTask'], resolve),
    },
    //个人中心，可能有修改密码，头像修改等路由
    {
        path: '/personal',
        name: 'Personal',
        meta: {
            title: '个人中心',
            keepAlive: true
        },
        component: resolve => require(['~/views/PersonalCenter'], resolve),
    }
    ,
    //报表
    {
        path: '/sales_account',
        name: 'Sales_account',
        meta: {
            title: '销售台账-订单明细',
            keepAlive: true
        },
        component: resolve => require(['~/views/Report/SalesAccount'], resolve),
    },
    {
        path: '/tax_statement',
        name: 'Tax_statement',
        meta: {
            title: '税金报表',
            keepAlive: true
        },
        component: resolve => require(['~/views/Report/TaxStatement'], resolve),
    },
    {
        path: '/tax_balance',
        name: 'Tax_balance',
        meta: {
            title: '回款对账单',
            keepAlive: true
        },
        component: resolve => require(['~/views/Report/TaxBalance'], resolve),
    }
]