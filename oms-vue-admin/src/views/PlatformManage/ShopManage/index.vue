<template>
    <div>
        <v-page-table ref="VTable" :tableParams="tableParams"
                      v-on:handleSearchTopBtn="handleSearchTopBtn"
                      v-on:submitSearchCondition="submitSearchCondition"
                      v-on:handleSelectionChange="handleSelectionChange"
                      v-on:keywordSearch="keywordSearch"
                      v-on:handleOperation="handleOperation"></v-page-table>
        <el-dialog
                :visible.sync="dialogVisible"
                :fullscreen="fullscreen"
                @close="closeDialog"
                @open="openDialog"
                width="900"
        >
            <div slot="title">
                {{dialogTitle}}
                <a href="javascript:void(0);" @click="toggleFullScreen"
                   class="dialog-title-fullScree oms-icon icon-quanping2"></a>
            </div>
            <v-form :formProps="editFormProps" ref="form"
                    v-on:handleReset="handleReset"
                    v-on:coverFormData="coverFormData"></v-form>

        </el-dialog>
    </div>
</template>

<script>
    import VPageTable from '~/components/VPageTable'
    import VForm from '~/components/VForm';


    export default {
        name: 'Shop_Manage',
        components: {
            VPageTable, VForm
        },
        data() {
            return {
                action: '',
                checkData: '',
                fullscreen: false,
                dialogVisible: false,
                dialogTitle: '',
                row: '',
                editFormProps: {
                    col: 2,
                    labelWidth: '120px',
                    formItemArray: [{
                        label: '名称',
                        prop: 'name',
                        rules: [{required: true, message: '名称不能为空!', trigger: 'blur'}],
                        value: '',
                    }, {
                        label: '别名',
                        prop: 'nickName',
                        value: '',
                    }, {
                        label: '编码',
                        prop: 'code',
                        value: '',
                        rules: [{required: true, message: '编码不能为空!', trigger: 'blur'}],
                    }, {
                        label: '类型',
                        prop: 'isOnline',
                        itemType: 'select',
                        options: [{
                            label: '请选择',
                            value: ''
                        }, {
                            label: '线下',
                            value: 1
                        }, {
                            label: '线上',
                            value: 2
                        }],
                        value: ''
                    }, {
                        label: '商家',
                        prop: 'merchantId',
                        itemType: 'select',
                        options: [],
                        value: '',
                        rules: [{required: true, message: '请选择商家!', trigger: 'blur'}],
                    }, {
                        label: '平台',
                        prop: 'platformId',
                        itemType: 'select',
                        options: [],
                        value: '',
                        rules: [{required: true, message: '请选择平台!', trigger: 'blur'}],
                    }, {
                        label: '店铺网址',
                        prop: 'url',
                        value: '',
                    }, {
                        label: '店铺电话',
                        prop: 'telephone',
                        value: '',
                    }, {
                        label: 'appKey',
                        prop: 'appKey',
                        value: '',
                    }, {
                        label: 'appSecret',
                        prop: 'appSecret',
                        value: '',
                    }, {
                        label: '店铺手机',
                        prop: 'mobile',
                        value: '',
                    }, {
                        label: '取消订单通知平台',
                        prop: 'syncCancelOrder',
                        itemType: 'select',
                        options: [{
                            label: '请选择',
                            value: ''
                        }, {
                            label: '是',
                            value: '1'
                        }, {
                            label: '否',
                            value: '0'
                        }],
                        value: '',
                    }, {
                        label: '启用店铺',
                        prop: 'isDeleted',
                        itemType: 'select',
                        options: [{
                            label: '请选择',
                            value: ''
                        }, {
                            label: '不可用',
                            value: '1'
                        }, {
                            label: '可用',
                            value: '0'
                        }],
                        value: ''
                    }, {
                        label: '订单下载',
                        prop: 'syncOrder',
                        itemType: 'select',
                        options: [{
                            label: '请选择',
                            value: ''
                        }, {
                            label: '是',
                            value: '1'
                        }, {
                            label: '否',
                            value: '0'
                        }],
                        value: ''
                    }, {
                        label: '同步物流',
                        prop: 'syncExpress',
                        itemType: 'select',
                        options: [{
                            label: '请选择',
                            value: ''
                        }, {
                            label: '开启',
                            value: '1'
                        }, {
                            label: '关闭',
                            value: '0'
                        }], value: ''
                    },]
                },
                tableParams: {
                    queryCriteria: {
                        operationBtn: {
                            deleted: {
                                show: false
                            }
                        },
                        searchFormProps: {
                            col: 4,
                            labelWidth: '100px',
                            formItemArray: [{
                                label: '店铺名称',
                                prop: 'nameSearch',
                                value: '',
                            }, {
                                label: '平台',
                                prop: 'platformNameSearch',
                                itemType: 'select',
                                options: [],
                                value: ''
                            }, {
                                label: '商家',
                                prop: 'merchantIdSearch',
                                itemType: 'select',
                                options: [],
                                value: ''
                            }, {
                                label: '类型',
                                prop: 'isOnlineSearch',
                                itemType: 'select',
                                options: [{
                                    label: '请选择',
                                    value: ''
                                }, {
                                    label: '线下',
                                    value: '1'
                                }, {
                                    label: '线上',
                                    value: '2'
                                }], value: ''
                            }, {
                                label: '是否启用',
                                prop: 'isDeletedSearch',
                                itemType: 'select',
                                options: [{
                                    label: '请选择',
                                    value: ''
                                }, {
                                    label: '可用',
                                    value: '1'
                                }, {
                                    label: '不可用',
                                    value: '0'
                                }], value: ''
                            }],
                        },
                    },
                    searchParams: {
                        keyword: '',
                    },
                    rowHandle: {
                        view: {show: false},
                        deleted: {
                            show: false
                        }
                    },
                    showOperationColumn: true,
                    showMultiSelection: false,
                    apiMethodName: this.$Methods.SHOP_LIST,
                    columnArray: [
                        {label: '店铺名称', prop: 'name', width: 120, sortable: true},
                        {label: '别名', prop: 'nickName', width: 120, sortable: true},
                        {label: '店铺类型', prop: 'isOnlineName', width: 100, sortable: true},
                        {label: '平台名称', prop: 'platformIdName', width: 110, sortable: true},
                        {label: '商家', prop: 'merchantIdName', width: 150, sortable: true},
                        {label: '启用', prop: 'isDeletedName', width: 80, sortable: true},
                        {label: '订单下载', prop: 'syncOrderName', width: 100, sortable: true},
                        {label: '上次订单同步时间', prop: 'syncOrderTime', width: 160, sortable: true},
                        {label: '上次库存同步时间', prop: 'syncStockTime', width: 160, sortable: true},
                        {label: '同步快递', prop: 'syncExpressName'},
                    ],
                },
            }
        }
        ,
        methods: {
            submitSearchCondition(data) {
                this.$refs.VTable.searchHandle(data);
            },
            getIdList() {
                let idList = [];
                this.checkData.forEach(item => {
                    idList.push(item.id);
                });
                return idList;
            },
            keywordSearch(keyword) {
                this.$refs.VTable.searchHandle({keyword: keyword});
            },
            handleReset(formName) {
                this.$refs.form.resetForm(formName);
            },
            openDialog() {
                this.$nextTick(() => {
                    if (this.action === 'update') {
                        this.editFormProps.formItemArray.forEach(item => {
                            for (let key in this.row) {
                                if (key === item.prop) {
                                    if (item.prop === 'syncCancelOrder' ||
                                        item.prop === 'isDeleted' ||
                                        item.prop === 'syncOrder' ||
                                        item.prop === 'syncExpress'
                                    ) {
                                        item.value = this.row[key] + '';
                                    } else {
                                        item.value = this.row[key];
                                    }
                                }
                            }
                        });
                    }
                });
            },
            closeDialog() {
                this.$refs.form.resetForm('validateMyForm');
            },
            handleSelectionChange(val) {
                this.checkData = val;
            },
            handleOperation(index, row, method) {
                if (method === 'update') {

                    this.$Api[this.$Methods.GET_SHOP_BY_ID](row.id).then((resp) => {
                        this.row = resp;
                        this.action = 'update';
                        this.dialogTitle = '修改店铺';
                        this.dialogVisible = true;
                        this.fullscreen = false;
                    });
                }
            },
            handleSearchTopBtn(methodName) {
                switch (methodName) {
                    case 'refresh' :
                        this.$refs.VTable.refreshTableData();
                        break;
                    case 'add':
                        this.dialogVisible = true;
                        this.fullscreen = false;
                        this.action = 'add';
                        this.dialogTitle = '新增店铺';
                        break;
                }
            },
            toggleFullScreen() {
                if (this.fullscreen) {
                    this.fullscreen = false;
                } else {
                    this.fullscreen = true;
                }
            },
            coverFormData(value) {
                console.log(value);
                if (this.action === 'add') {
                    this.$Api[this.$Methods.ADD_SHOP](value).then(() => {
                        this.dialogVisible = false;
                        this.$Config.MessageModal('success', '添加店铺成功');
                        this.$refs.VTable.refreshTableData();
                    });
                } else {
                    value.id = this.row.id;
                    this.$Api[this.$Methods.UPDATE_SHOP](value).then(() => {
                        this.dialogVisible = false;
                        this.$Config.MessageModal('success', '编辑店铺成功');
                        this.$refs.VTable.refreshTableData();
                    });
                }

            }, buildOptions(labelKey, valueKey, obj) {
                let options = [{
                    label: '请选择',
                    value: ''
                }];
                obj.forEach(item => {
                    options.push({
                        label: item[labelKey],
                        value: item[valueKey]
                    });
                });
                return options;
            }, initData() {
                this.$Api[this.$Methods.INIT_SHOP]().then((resp) => {
                    this.editFormProps.formItemArray.forEach(item => {
                        switch (item.prop) {
                            case 'merchantId':
                                let merchantArray = resp['mdMerchantList'];
                                item.options = this.buildOptions('merchantName', 'id', merchantArray);
                                break;
                            case 'platformId':
                                let platformArray = resp['platformList'];
                                item.options = this.buildOptions('platformName', 'id', platformArray);
                                break;
                        }
                    });
                    this.tableParams.queryCriteria.searchFormProps.formItemArray.forEach(item => {
                        switch (item.prop) {
                            case 'merchantIdSearch':
                                let merchantArray = resp['mdMerchantList'];
                                item.options = this.buildOptions('merchantName', 'id', merchantArray);
                                break;
                            case 'platformNameSearch':
                                let platformArray = resp['platformList'];
                                item.options = this.buildOptions('platformName', 'id', platformArray);
                                break;
                        }
                    });
                });
            }
        }, mounted() {
            this.initData();
        }
    }
</script>
