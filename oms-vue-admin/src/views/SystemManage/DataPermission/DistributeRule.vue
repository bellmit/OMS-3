<template>
    <div>
        <v-page-table ref="VTable" :tableParams="tableParams"
                      v-on:handleSearchTopBtn="handleSearchTopBtn"
                      v-on:handleSelectionChange="handleSelectionChange"
                      v-on:handleOperation="handleOperation"></v-page-table>
        <el-dialog
                :visible.sync="dialogVisible"
                :fullscreen="fullscreen"
                @close="closeDialog"
                @open="openDialog"
                :modal="false"
                width="900"
        >
            <div slot="title">
                {{dialogTitle}}
                <a href="javascript:void(0);" @click="toggleFullScreen"
                   class="dialog-title-fullScree oms-icon icon-quanping2"></a>
            </div>
            <div>
                <span class="distribute_rule_title">
                    规则类型:
                </span>
                <el-select v-model="typeId" placeholder="请选择规则类型" @change="ruleTypeChange">
                    <el-option
                            label="请选择"
                            value="">
                    </el-option>
                    <el-option
                            v-for="item in options"
                            :key="item.id"
                            :label="item.name"
                            :value="item.num">
                    </el-option>
                </el-select>
            </div>
            <div>
                <el-checkbox :indeterminate="isIndeterminate" v-model="checkAll" @change="handleCheckAllChange">全选
                </el-checkbox>
                <div style="margin: 15px 0;"></div>
                <el-checkbox-group v-model="checkedRules" @change="handleCheckedRulesChange">
                    <el-checkbox v-for="rule in ruleArray" :label="rule" border style="margin-top: 4px"
                                 :key="rule.code">{{rule.ruleName}}
                    </el-checkbox>
                </el-checkbox-group>
            </div>
            <div style="margin: 15px 0;"></div>
            <div style="text-align: center">
                <el-button size="small" type="primary" @click="submitRules">提交</el-button>
                <el-button size="small" type="default" @click="dialogVisible=false">取消</el-button>
            </div>


        </el-dialog>
    </div>
</template>

<script>
    import VPageTable from '~/components/VPageTable';
    import VForm from '~/components/VForm';

    export default {
        name: 'DistributeRule',
        props: {
            groupId: {
                required: true,
                type: String
            }
        },
        components: {
            VPageTable, VForm
        },
        data() {
            return {
                ruleArray: [],
                checkedRules: [],
                isIndeterminate: true,
                checkAll: false,
                typeId: '',
                options: [],
                action: '',
                checkData: '',
                fullscreen: false,
                editFormProps: {
                    col: 1,
                    labelWidth: '100px',
                    formItemArray: [{
                        label: '分组名称',
                        prop: 'groupName',
                        placeholder: "请输入分组名称",
                    }, {
                        label: '分组说明',
                        prop: 'description',
                        placeholder: "请输入分组说明",
                    }],
                },
                //dialog 显隐控制
                dialogVisible: false,
                dialogTitle: '新增规则',
                //table属性
                tableParams: {
                    operationColumnWidth: 60,
                    rowHandle: {
                        view: {
                            show: false
                        }, update: {
                            show: false
                        }
                    },
                    queryCriteria: {
                        showSearchCondition: false,
                        searchFormProps: {
                            col: 4,
                            labelWidth: '100px',
                            formItemArray: [{
                                label: '分组名称',
                                prop: 'groupName',
                            }, {
                                label: '分组说明',
                                prop: 'description',
                            }],
                        },
                    },
                    apiMethodName: this.$Methods.DATA_PERMISSION_RULE_LIST,
                    searchParams: {
                        groupId: this.groupId,
                    },
                    columnArray: [
                        {label: '规则类型', prop: 'typeName', sortable: true},
                        {label: '规则值', prop: 'contentName', sortable: true},
                        {label: '分组名称', prop: 'groupName', sortable: true},
                        {label: '分组说明', prop: 'description', sortable: true},
                    ],
                },
            }
        },
        methods: {
            submitRules() {
                if (this.typeId === "") {
                    this.$Config.MessageModal('warning', '请选择规则类型！');
                    return;
                }
                if (this.checkedRules.length < 1) {
                    this.$Config.MessageModal('warning', '请选择需要添加的规则值！');
                    return;
                }
                let params = [];
                this.checkedRules.forEach(item => {
                    let obj = {
                        type: parseInt(this.typeId),
                        content: parseInt(item.code),
                        groupId: this.groupId
                    }
                    params.push(obj);
                });
                this.$Api[this.$Methods.ADD_DATA_PERMISSION_RULE](params).then(() => {
                    this.dialogVisible = false;
                    this.$Config.MessageModal('success', '添加规则成功');
                    this.$refs.VTable.refreshTableData();
                });
            },
            handleCheckedRulesChange(value) {
                let checkedCount = value.length;
                this.checkAll = checkedCount === this.ruleArray.length;
                this.isIndeterminate = checkedCount > 0 && checkedCount < this.ruleArray.length;
            }, handleCheckAllChange(val) {
                this.checkedRules = val ? this.ruleArray : [];
                this.isIndeterminate = false;
            }, ruleTypeChange(value) {
                if ("" !== value) {
                    this.chooseRule(value);
                    this.checkedRules = [];
                    this.checkAll = false;
                } else {
                    this.ruleArray = [];
                }
            }, handleSelectionChange(val) {
                this.checkData = val;
            }, openDialog() {
                this.$nextTick(() => {
                    this.typeId = '';
                    this.ruleArray = [];
                });
            }, closeDialog() {
                this.getRuleTypeList();
            }, toggleFullScreen() {
                if (this.fullscreen) {
                    this.fullscreen = false;
                } else {
                    this.fullscreen = true;
                }
            }, handleOperation(index, row) {
                this.deleteData([row.id]);
            }, getIdList() {
                let idList = [];
                this.checkData.forEach(item => {
                    idList.push(item.id);
                });
                return idList;
            }, handleSearchTopBtn(methodName) {
                if (methodName === 'add') {
                    this.dialogVisible = true;
                    this.fullscreen = false;
                    this.action = 'add';
                } else if (methodName === 'delete') {
                    if (this.$Config.hasSelectRows(this.checkData)) {
                        this.deleteData(this.getIdList());
                    }
                } else {
                    this.$refs.VTable.refreshTableData();
                }

            }, deleteData(idList) {
                this.$Config.ConfirmModal('确定要删除吗?', () => {
                    this.$Api[this.$Methods.DEL_DATA_PERMISSION_RULE]({idList: idList}).then(() => {
                        this.$Config.MessageModal('success', '删除成功');
                        this.$refs.VTable.refreshTableData();
                    });
                });
            }, coverFormData(value) {
                if (this.action === 'add') {
                    this.$Api[this.$Methods.ADD_DATA_PERMISSION](value).then(() => {
                        this.dialogVisible = false;
                        this.$Config.MessageModal('success', '添加成功');
                        this.$refs.VTable.refreshTableData();
                    });
                }
            }, chooseRule(type) {
                this.$Api[this.$Methods.CHOOSE_DATA_PERMISSION_RULE]({type: type}).then((resp) => {
                    this.ruleArray = resp.ruleList;
                });
            }, getRuleTypeList() {
                this.$Api[this.$Methods.DATA_PERMISSION_GROUP_TYPE]().then((resp) => {
                    this.options = resp.dataType;
                });
            }

        }, mounted() {
            this.getRuleTypeList();
        }
    }
</script>
<style lang="less" scoped>
    .distribute_rule_title {
        color: black;
        font-size: 12px;
        font-weight: 400
    }
</style>
