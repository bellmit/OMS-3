<template>
    <div>
        <v-page-table ref="VTable" :tableParams="tableParams"
                      v-on:handleSearchTopBtn="handleSearchTopBtn"
                      v-on:handleSelectionChange="handleSelectionChange"
                      v-on:submitSearchCondition="submitSearchCondition"
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
        name: 'Goods_Manage',
        components: {
            VPageTable, VForm
        },
        data() {
            const validateMobile = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('手机号不能为空！'));
                }
                let r = value.match(/^[0-9]*$/);
                if (r == null) {
                    callback(new Error('手机号由数字组成！'));
                } else if (value.trim().length !== 11) {
                    callback(new Error('手机号码格式错误！'));
                } else {
                    callback();
                }
            };
            return {
                action: '',
                checkData: [],
                cityArray: [],
                districtArray: [],
                fullscreen: false,
                dialogVisible: false,
                dialogTitle: '',
                row: '',
                editFormProps: {
                    col: 3,
                    labelWidth: '100px',
                    formItemArray: [{
                        label: '仓库名称',
                        prop: 'warehouseName',
                        rules: [{required: true, message: '仓库名称不能为空!', trigger: 'blur'}],
                        value: '',
                    }, {
                        label: '外部编码',
                        prop: 'code',
                        value: '',
                    }, {
                        label: '类型',
                        prop: 'warehouseType',
                        itemType: 'select',
                        options: [{
                            label: '仓库',
                            value: '0'
                        }, {
                            label: '门店',
                            value: '1'
                        }], value: '0',
                        rules: [{required: true, message: '类型不能为空!', trigger: 'blur'}],
                    }, {
                        label: '省',
                        prop: 'provinceId',
                        itemType: 'select',
                        selectType: 'province',
                        options: [{
                            label: '请选择',
                            value: ''
                        }], value: '',
                        rules: [{required: true, message: '省不能为空!', trigger: 'blur'}],
                    }, {
                        label: '市',
                        prop: 'cityId',
                        itemType: 'select',
                        selectType: 'city',
                        options: [{
                            label: '请选择',
                            value: ''
                        }], value: '',
                        rules: [{required: true, message: '市不能为空!', trigger: 'blur'}],
                    }, {
                        label: '区',
                        prop: 'countyId',
                        itemType: 'select',
                        selectType: 'district',
                        options: [{
                            label: '请选择',
                            value: ''
                        }], value: '',
                        rules: [{required: true, message: '区不能为空!', trigger: 'blur'}],
                    }, {
                        label: '是否实体库',
                        prop: 'isRealWarehouse',
                        itemType: 'select',
                        options: [{
                            label: '请选择',
                            value: ''
                        }, {
                            label: '否',
                            value: '0'
                        }, {
                            label: '是',
                            value: '1'
                        }], value: ''
                    }, {
                        label: '发货平台',
                        prop: 'shippingMode',
                        itemType: 'select',
                        options: [{
                            label: 'erp',
                            value: '1'
                        }, {
                            label: 'wms',
                            value: '2'
                        }], value: '1',
                        rules: [{required: true, message: '发货平台不能为空!', trigger: 'blur'}],
                    }, {
                        label: '负责人',
                        prop: 'contactor',
                        value: '',
                        rules: [{required: true, message: '负责人不能为空!', trigger: 'blur'}],
                    }, {
                        label: '手机',
                        prop: 'mobile',
                        value: '',
                        maxlength: 11,
                        rules: [{required: true, validator: validateMobile, trigger: 'blur'}],
                    }, {
                        label: '贸易类型',
                        prop: 'tradeType',
                        itemType: 'select',
                        options: [{
                            label: '普通仓',
                            value: '0'
                        }, {
                            label: '保税仓',
                            value: '1'
                        }], value: '0',
                        rules: [{required: true, message: '贸易类型不能为空!', trigger: 'blur'}],
                    }, {
                        label: '仓库地址',
                        prop: 'addressName',
                        value: '',
                        rules: [{required: true, message: '仓库地址不能为空!', trigger: 'blur'}],
                    }, {
                        label: '配送商',
                        prop: 'carrierId',
                        value: '',
                    }, {
                        label: '存储类型',
                        prop: 'storageType',
                        itemType: 'select',
                        options: [{
                            label: '请选择',
                            value: ''
                        }, {
                            label: '常温',
                            value: '0'
                        }, {
                            label: '冷藏',
                            value: '1'
                        }, {
                            label: '冷冻',
                            value: '2'
                        }], value: ''
                    }, {
                        label: '功能类型',
                        prop: 'functionType',
                        itemType: 'select',
                        options: [{
                            label: '请选择',
                            value: ''
                        }, {
                            label: '存储+销售',
                            value: '0'
                        }, {
                            label: '存储不销售',
                            value: '1'
                        }], value: ''
                    }, {
                        label: '联系电话',
                        prop: 'phone',
                        value: '',
                    }, {
                        label: '退换货仓',
                        prop: 'returnWarehouseId',
                        itemType: 'select',
                        options: [{
                            label: '请选择',
                            value: ''
                        }],
                    }, {
                        label: '关区编码',
                        prop: 'areaCode',
                        value: '',
                    },]
                },
                tableParams: {
                    queryCriteria: {
                        operationBtn: {
                            rowHandle: [{
                                text: '冻结',
                                show: true,
                                type: 'primary',
                                methodName: 'frozen',
                                icon: 'oms-icon icon-cf-c09',
                            }, {
                                text: '解除冻结',
                                show: true,
                                type: 'primary',
                                methodName: 'unfrozen',
                                icon: 'oms-icon icon-jiedong',
                            }]
                        },
                        searchFormProps: {
                            col: 4,
                            labelWidth: '100px',
                            formItemArray: [{
                                label: '仓库名称',
                                prop: 'houseName',
                                value:''
                            }, {
                                label: '仓库类型',
                                prop: 'houseTypeId',
                                itemType: 'select',
                                options: [{
                                    label: '请选择',
                                    value: ''
                                }, {
                                    label: '仓库',
                                    value: '0'
                                }, {
                                    label: '门店',
                                    value: '1'
                                },],value:''
                            }, {
                                label: '存储类型',
                                prop: 'storageTypeId',
                                itemType: 'select',
                                options: [{
                                    label: '请选择',
                                    value: ''
                                }, {
                                    label: '常温',
                                    value: '0'
                                }, {
                                    label: '冷藏',
                                    value: '1'
                                }, {
                                    label: '冷冻',
                                    value: '2'
                                },], value: ''
                            }, {
                                label: '功能类型',
                                prop: 'functionTypeId',
                                itemType: 'select',
                                options: [{
                                    label: '请选择',
                                    value: ''
                                }, {
                                    label: '存储+销售',
                                    value: '0'
                                }, {
                                    label: '存储不销售',
                                    value: '1'
                                },],value:''
                            }, {
                                label: '贸易类型',
                                prop: 'tradeTypeId',
                                itemType: 'select',
                                options: [{
                                    label: '请选择',
                                    value: ''
                                }, {
                                    label: '普通仓',
                                    value: '0'
                                }, {
                                    label: '保税仓',
                                    value: '1'
                                },], value: ''
                            }],
                        },
                    },
                    rowHandle: {
                        view: {show: false},
                        buttonArray: [{
                            text: '冻结',
                            show: true,
                            type: 'text',
                            methodName: 'frozen'
                        }, {
                            text: '启用',
                            show: true,
                            type: 'text',
                            methodName: 'unfreeze'
                        }]
                    },
                    showOperationColumn: true,
                    showMultiSelection: true,
                    apiMethodName: this.$Methods.WAREHOUSE_LIST,
                    columnArray: [
                        {label: '仓库名称', prop: 'warehouseName', width: 140, sortable: true},
                        {label: '省', prop: 'provinceName', width: 80, sortable: true},
                        {label: '市', prop: 'cityName', width: 80, sortable: true},
                        {label: '区', prop: 'countyName', width: 80, sortable: true},
                        {label: '仓库类型', prop: 'houseTypeName', width: 100, sortable: true},
                        {label: '状态', prop: 'statusName', width: 80, sortable: true},
                        {label: '外部编码', prop: 'code', width: 100, sortable: true},
                        {label: '配送商', prop: '', width: 100, sortable: true},
                        {label: '存储类型', prop: 'storageClassName', width: 100, sortable: true},
                        {label: '功能类型', prop: 'functionTypeName', width: 100, sortable: true},
                        {label: '贸易类型', prop: 'tradeTypeName', width: 100, sortable: true},
                        {label: '是否实体库', prop: 'isEntityLibraryName', width: 110, sortable: true},
                        {label: '地址', prop: 'addressName', width: 300, sortable: true},
                        {label: '负责人', prop: 'contactor', width: 100, sortable: true},
                        {label: '联系电话', prop: 'phone', width: 100, sortable: true},
                        {label: '手机号', prop: 'mobile', width: 100, sortable: true},
                        {label: '更新时间', prop: 'updateTime', width: 160, sortable: true},
                    ],
                },
            }
        },
        methods: {
            keywordSearch(keyword) {
                this.$refs.VTable.searchHandle({keyword: keyword});
            },
            submitSearchCondition(data) {
                this.$refs.VTable.searchHandle(data);
            }, openDialog() {
                this.$nextTick(() => {
                    if (this.action === 'update') {
                        this.editFormProps.formItemArray.forEach(item => {
                            if (item.selectType === 'city') {
                                item.options = this.cityArray;
                            } else if (item.selectType === 'district') {
                                item.options = this.districtArray;
                            }
                            for (let key in this.row) {
                                if (key === item.prop) {
                                    item.value = this.row[key] + '';
                                }
                            }
                        });
                    } else {
                        this.row = [];
                        this.editFormProps.formItemArray.forEach(item => {
                            if (item.selectType === 'city') {
                                item.options = [{label: '请选择', value: ''}];
                            } else if (item.selectType === 'district') {
                                item.options = [{label: '请选择', value: ''}];
                            }
                        });
                    }
                });
            },
            closeDialog() {
                this.$refs.form.resetForm('validateMyForm');
            },
            handleReset(formName) {
                this.$refs.form.resetForm(formName);
            },
            handleSelectionChange(val) {
                this.checkData = val;
            },
            buildSelectOptions(labelKey, valueKey, obj) {
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
            },
            handleOperation(index, row, method) {
                switch (method) {
                    case 'update':
                        this.$Api[this.$Methods.FIND_WAREHOUSE_BY_ID](row.id).then((resp) => {
                            this.action = 'update';
                            this.row = resp['warehouse'];
                            this.cityArray = this.buildSelectOptions('name', 'id', resp['cityList']);
                            this.districtArray = this.buildSelectOptions('name', 'id', resp['districtList']);
                            this.fullscreen = false;
                            this.dialogTitle = '编辑仓库信息';
                            this.dialogVisible = true;
                        });
                        break;
                    case 'delete':
                        this.deleteWarehouse([row.id]);
                        break;
                    case 'frozen':
                        this.freezeWarehouse([row.id]);
                        break;
                    case 'unfreeze':
                        this.unfreezeWarehouse([row.id]);
                        break;
                }
            },
            handleSearchTopBtn(methodName) {
                switch (methodName) {
                    case 'refresh' :
                        this.$refs.VTable.refreshTableData();
                        break;
                    case 'add':
                        this.dialogTitle = '添加仓库';
                        this.action = 'add';
                        this.dialogVisible = true;
                        this.fullscreen = false;
                        break;
                    case 'delete':
                        if (this.$Config.hasSelectRows(this.checkData)) {
                            this.deleteWarehouse(this.getIdList());
                        }
                        break;
                    case 'frozen':
                        if (this.$Config.hasSelectRows(this.checkData)) {
                            this.freezeWarehouse(this.getIdList());
                        }
                        break;
                    case 'unfrozen':
                        if (this.$Config.hasSelectRows(this.checkData)) {
                            this.unfreezeWarehouse(this.getIdList());
                        }
                        break;
                }
            },
            getIdList() {
                let idList = [];
                this.checkData.forEach(item => {
                    idList.push(item.id);
                });
                return idList;
            },
            toggleFullScreen() {
                if (this.fullscreen) {
                    this.fullscreen = false;
                } else {
                    this.fullscreen = true;
                }
            },
            deleteWarehouse(idList) {
                this.$Config.ConfirmModal("确定要删除吗？", () => {
                    this.$Api[this.$Methods.DELETE_WAREHOUSE](idList).then(() => {
                        this.$Config.MessageModal('success', '删除仓库成功！');
                        this.$refs.VTable.refreshTableData();
                    });
                });
            },
            freezeWarehouse(idList) {
                this.$Config.ConfirmModal("确定要冻结吗？", () => {
                    this.$Api[this.$Methods.FREEZE_WAREHOUSE](idList).then(() => {
                        this.$Config.MessageModal('success', '冻结仓库成功！');
                        this.$refs.VTable.refreshTableData();
                    });
                });
            },
            unfreezeWarehouse(idList) {
                this.$Config.ConfirmModal("确定要解除冻结吗？", () => {
                    this.$Api[this.$Methods.UNFREEZE_WAREHOUSE](idList).then(() => {
                        this.$Config.MessageModal('success', '解除仓库冻结成功！');
                        this.$refs.VTable.refreshTableData();
                    });
                });
            },
            coverFormData(value) {
                if (this.action === 'add') {
                    this.$Api[this.$Methods.ADD_WAREHOUSE](value).then(() => {
                        this.dialogVisible = false;
                        this.$Config.MessageModal('success', '添加仓库成功');
                        this.$refs.VTable.refreshTableData();
                    });
                } else {
                    value.id = this.row.id;
                    this.$Api[this.$Methods.UPDATE_WAREHOUSE](value).then(() => {
                        this.dialogVisible = false;
                        this.$Config.MessageModal('success', '修改仓库信息成功');
                        this.$refs.VTable.refreshTableData();
                    });
                }
            },
        },
    }
</script>
