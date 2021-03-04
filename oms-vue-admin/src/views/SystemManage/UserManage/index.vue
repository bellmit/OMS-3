<template>
    <div>
        <v-page-table ref="VTable" :tableParams="tableParams"
                      v-on:handleOperation="handleOperation"
                      v-on:handleSearchTopBtn="handleSearchTopBtn"
                      v-on:handleSelectionChange="handleSelectionChange"
                      v-on:submitSearchCondition="submitSearchCondition"
                      v-on:keywordSearch="keywordSearch"
        ></v-page-table>
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
            <v-form :formProps="editFormProps" ref="form" v-if="dialogType === 1"
                    v-on:handleReset="handleReset"
                    v-on:coverFormData="coverFormData"></v-form>
            <div v-if="dialogType === 2">
                <span style="font-weight: 600;font-size: 12px;color: #666">请选择数据权限</span>
                <el-select v-model="groupId">
                    <el-option label="请选择" value=""></el-option>
                    <el-option
                            v-for="item in dataPermissionArray"
                            :key="item.groupName"
                            :label="item.groupName"
                            :value="item.id">
                    </el-option>
                </el-select>
                <el-button type="primary" @click="sureBindGroupBtnClick" size="small">确认</el-button>
            </div>


        </el-dialog>
    </div>
</template>

<script>
    import VPageTable from '~/components/VPageTable';
    import VForm from '~/components/VForm';


    export default {
        name: 'userManage',
        components: {
            VPageTable, VForm
        },
        data() {
            const validatePwd = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请输入您的密码！'));
                } else if (value.trim().length < 4 || value.trim().length > 9) {
                    return callback(new Error('密码为4~8个字符之间!'))
                } else {
                    callback();
                }
            };

            const validatePassCheck = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请再次输入密码！'));
                } else if (value !== this.editFormProps.formItemArray[2].value) {
                    callback(new Error('两次输入的密码不匹配!'));
                } else {
                    callback();
                }
            };
            const validateMobile = (rule, value, callback) => {
                if (value === '') {
                    callback();
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
                row: '',
                groupId: '',
                checkData: [],
                dialogType: '',
                fullscreen: false,
                dataPermissionArray: [],
                editFormProps: {
                    col: 2,
                    labelWidth: '100px',
                    formItemArray: [{
                        label: '账号',
                        prop: 'account',
                        placeholder: "请输入账号",
                        rules: [{required: true, message: '账号不能为空!', trigger: 'blur'}],
                        readonly: false,
                        value: ''
                    }, {
                        label: '姓名',
                        prop: 'name',
                        placeholder: "请输入姓名",
                        rules: [{required: true, message: '姓名不能为空!', trigger: 'blur'}],
                        value: ''
                    }, {
                        label: '密码',
                        show: false,
                        placeholder: "请输入密码",
                        prop: 'password',
                        rules: [{required: true, validator: validatePwd, trigger: 'blur'}],
                        itemType: 'password',
                        value: ''
                    }, {
                        show: false,
                        label: '确认密码',
                        placeholder: "请再次输入密码",
                        prop: 'rePassword',
                        rules: [{required: true, validator: validatePassCheck, trigger: 'blur'}],
                        itemType: 'password',
                        value: ''
                    }, {
                        label: '性别',
                        prop: 'sex',
                        itemType: 'select',
                        placeholder: "请选择性别",
                        options: [{
                            label: '男',
                            value: 1
                        }, {
                            label: '女',
                            value: 2
                        }],
                        value: ''
                    }, {
                        label: '出生日期',
                        placeholder: "年-月-日",
                        prop: 'birthday',
                        itemType: 'date',
                        value: ''
                    }, {
                        label: '角色',
                        placeholder: "请选择角色",
                        readonly: true,
                        prop: 'roleName',
                        itemType: 'tree',
                        treeType: 'role',
                        valueProp: 'roleid',
                        valueId: '',
                        value: ''
                    }, {
                        label: '部门',
                        placeholder: "请选择部门",
                        prop: 'deptName',
                        treeType: 'dept',
                        itemType: 'tree',
                        readonly: true,
                        valueProp: 'deptid',
                        valueId: '',
                        value: ''
                    }, {
                        label: '邮箱',
                        placeholder: "请输入邮箱地址",
                        prop: 'email',
                        rules: [{type: 'email', message: '邮箱格式不合法', trigger: 'blur'}],
                        value: ''
                    }, {
                        label: '手机号',
                        rules: [{validator: validateMobile, trigger: 'blur'}],
                        placeholder: "请输入联系方式",
                        maxlength: 11,
                        prop: 'phone',
                        value: ''
                    }],
                },
                dialogVisible: false,
                dialogTitle: '编辑用户信息',
                tableParams: {
                    rowHandle: {
                        view: {
                            show: false
                        },
                        buttonArray: [
                            {
                                text: '冻结',
                                show: true,
                                type: 'text',
                                methodName: 'frozen'
                            },
                            {
                                text: '解除冻结',
                                show: true,
                                type: 'text',
                                methodName: 'unfreeze'
                            },
                            {
                                text: '重置密码',
                                show: true,
                                type: 'text',
                                methodName: 'resetPwd'
                            }
                        ]
                    },
                    queryCriteria: {
                        operationBtn: {
                            rowHandle: [
                                {
                                    text: '重置密码',
                                    show: true,
                                    type: 'primary',
                                    methodName: 'resetPwd',
                                    icon: 'oms-icon icon-resetpwd',
                                }, {
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
                                }, {
                                    text: '数据权限',
                                    show: true,
                                    type: 'primary',
                                    methodName: 'dataPermission',
                                    icon: 'oms-icon icon-shujuquanxian',
                                }
                            ]
                        },
                        searchFormProps: {
                            col: 4,
                            labelWidth: '100px',
                            formItemArray: [{
                                label: '用户名称',
                                prop: 'name',
                                placeholder: "账号/姓名/手机号",
                            }, {
                                label: '注册开始时间',
                                prop: 'beginTime',
                                itemType: 'date',
                                placeholder: "注册开始时间",
                                value: '',

                            }, {
                                label: '注册结束时间',
                                prop: 'endTime',
                                itemType: 'date',
                                placeholder: "注册结束时间",
                                value: '',
                            }],
                            add: false,
                            delete: false,
                            refresh: true,
                        },
                    },
                    apiMethodName: this.$Methods.USER_MANAGE_LIST,
                    columnArray: [
                        {label: '账号', prop: 'account', sortable: true},
                        {label: '姓名', prop: 'name', width: 120, sortable: true},
                        {label: '性别', prop: 'sexName', sortable: true},
                        {label: '角色', prop: 'roleName', sortable: true},
                        {label: '部门', prop: 'deptName', width: 120, sortable: true},
                        {label: '邮箱', prop: 'email', width: 170, sortable: true},
                        {label: '电话', prop: 'phone', width: 120, sortable: true},
                        {label: '创建时间', prop: 'createtime', width: 160, sortable: true},
                        {label: '状态', prop: 'statusName', sortable: true}
                    ],
                },
                action: '',
            }
        },
        methods: {
            sureBindGroupBtnClick() {
                let array = [];
                this.checkData.forEach(item => {
                    array.push({
                        userId: item.id,
                        groupId: this.groupId
                    });
                });
                this.bindingDataGroup(array);
            },
            bindingDataGroup(param) {
                this.$Api[this.$Methods.DATA_PERMISSION_BINDING_GROUP](param).then(() => {
                    this.$Config.MessageModal('success', '数据权限绑定成功！');
                    this.dialogVisible = false;
                    this.fullscreen = false;
                });
            },

            keywordSearch(keyword) {
                this.$refs.VTable.searchHandle({keyword: keyword});
            },
            submitSearchCondition(data) {
                this.$refs.VTable.searchHandle(data);
            },
            openDialog() {
                this.$nextTick(() => {
                    if (this.action === 'update') {
                        this.editFormProps.formItemArray.forEach(item => {
                            if (item.prop === 'password' ||
                                item.prop === 'rePassword') {
                                item.show = true;
                            }
                            if (item.prop === 'account') {
                                item.readonly = true;
                            }
                            for (let key in this.row) {
                                if (item.prop === key) {
                                    item.value = this.row[key];
                                }
                                if (item.valueProp) {
                                    item.valueId = this.row[item.valueProp];
                                }
                            }
                        });

                    } else {
                        this.editFormProps.formItemArray.forEach(item => {
                            if (item.valueProp) {
                                item.valueId = '';
                            }
                        });
                        this.editFormProps.formItemArray[0].readonly = false;
                        this.editFormProps.formItemArray[2].show = false;
                        this.editFormProps.formItemArray[3].show = false;
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
            handleSearchTopBtn(methodName) {
                switch (methodName) {
                    case 'refresh' :
                        this.$refs.VTable.refreshTableData();
                        break;
                    case 'add':
                        this.dialogTitle = '新增用户';
                        this.action = 'add';
                        this.dialogVisible = true;
                        this.fullscreen = false;
                        this.dialogType = 1;
                        break;
                    case 'delete':
                        if (this.$Config.hasSelectRows(this.checkData)) {
                            this.deleteUser(this.getIdList());
                        }
                        break;
                    case 'resetPwd':
                        if (this.$Config.hasSelectRows(this.checkData)) {
                            this.resetPassword(this.getIdList());
                        }
                        break;
                    case 'frozen':
                        if (this.$Config.hasSelectRows(this.checkData)) {
                            this.frozenUser(this.getIdList());
                        }
                        break;
                    case 'unfrozen':
                        if (this.$Config.hasSelectRows(this.checkData)) {
                            this.unfrozenUser(this.getIdList());
                        }
                        break;
                    case 'dataPermission':
                        if (this.$Config.hasSelectRows(this.checkData)) {
                            this.$Api[this.$Methods.DATA_PERMISSION_GROUP_NAME_LIST]({}).then((resp) => {
                                this.dataPermissionArray = resp;
                                this.dialogVisible = true;
                                this.dialogTitle = '分配数据权限';
                                this.fullscreen = false;
                                this.dialogType = 2;
                            });
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
            handleOperation(index, row, method) {
                switch (method) {
                    case 'update':
                        this.row = row;
                        this.dialogTitle = '编辑用户信息';
                        this.action = 'update';
                        this.dialogVisible = true;
                        this.fullscreen = false;
                        this.dialogType = 1;
                        break;
                    case 'resetPwd':
                        this.resetPassword([row.id]);
                        break;
                    case 'delete':
                        this.deleteUser([row.id]);
                        break;
                    case 'frozen':
                        this.frozenUser([row.id]);
                        break;
                    case 'unfreeze':
                        this.unfrozenUser([row.id]);
                        break;
                }
            },
            deleteUser(idList) {
                let msg = '确定要删除当前数据吗？';
                if (idList.length > 1) {
                    msg = '确认要删除选中数据吗？';
                }
                this.$Config.ConfirmModal(msg, () => {
                    this.$Api[this.$Methods.DELETE_USER](idList).then(() => {
                        this.$Config.MessageModal('success', '删除成功，本次共删除' + idList.length + '条记录！');
                        this.$refs.VTable.refreshTableData();
                    });
                });
            },
            resetPassword(idList) {
                this.$Config.ConfirmModal('确定要重置密码为 111111 吗？', () => {
                    this.$Api[this.$Methods.RESET_PASSWORD](idList).then(() => {
                        this.$Config.MessageModal('success', '重置成功，本次共处理' + idList.length + '条记录！');
                    });
                })
            },
            frozenUser(idList) {
                this.$Config.ConfirmModal('确定要冻结吗？', () => {
                    this.$Api[this.$Methods.FREEZE_USER](idList).then(() => {
                        this.$Config.MessageModal('success', '冻结成功，本次共处理' + idList.length + '条记录！');
                    });
                })
            },
            unfrozenUser(idList) {
                this.$Config.ConfirmModal('确定要解除冻结吗？', () => {
                    this.$Api[this.$Methods.UNFREEZE_USER](idList).then(() => {
                        this.$Config.MessageModal('success', '解除冻结成功，本次共处理' + idList.length + '条记录！');
                    });
                });
            },
            toggleFullScreen() {
                if (this.fullscreen) {
                    this.fullscreen = false;
                } else {
                    this.fullscreen = true;
                }
            },
            coverFormData(value) {
                if (this.action === 'add') {
                    this.$Api[this.$Methods.ADD_USER](value).then(() => {
                        this.$Config.MessageModal('success', '新增用户成功！');
                        this.dialogVisible = false;
                        this.fullscreen = false;
                        this.action = '';
                        this.dialogType = '';
                        this.$refs.VTable.refreshTableData();
                    });
                } else {
                    value.id = this.row.id;
                    this.$Api[this.$Methods.EDIT_USER](value).then(() => {
                        this.$Config.MessageModal('success', '修改用户信息成功！');
                        this.dialogVisible = false;
                        this.fullscreen = false;
                        this.action = '';
                        this.dialogType = '';
                        this.$refs.VTable.refreshTableData();
                    });
                }
            },

        },
    }
</script>
<style lang="less">

</style>
