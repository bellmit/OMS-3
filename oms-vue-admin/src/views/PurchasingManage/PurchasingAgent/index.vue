<template>
    <div>
        <v-page-table ref="ref" :tableParams="tableParams"
                      v-on:handleOperation="handleOperation"></v-page-table>
        <el-dialog
                :visible.sync="dialogVisible"
                :fullscreen="fullscreen"
                width="80%"
        >
            <div slot="title">
                {{dialogTitle}}
                <a href="javascript:void(0);" @click="toggleFullScreen"
                   class="dialog-title-fullScree fa fa-arrows-alt"></a>
            </div>
            <edit v-if="dialogType === 1" brand-id="goodsId"></edit>
        </el-dialog>
    </div>
</template>

<script>
    import VPageTable from '~/components/VPageTable'
    import {USER_MANAGE_LIST} from '~/api/methods'
    import Edit from './Edit'

    const tableData = [{
        createdBy: '跨境-百洋官网',
        nickName: '-',
        isOnlineName: '线下',
        supplierCompanyName: '官网平台',
        merchantOfficeAddress: '跨境-百洋官网商家',
        isDeletedName: '可用',
        syncOrderName: '是',
        syncOrderTime: '2018-07-05 08:40:00',
        createTime: '2018-07-05 04:00:02',
        syncExpressName: '开启',
    }];
    export default {
        name: 'Goods_Manage',
        components: {
            VPageTable, Edit
        },
        data() {
            return {
                dialogType: '',
                fullscreen: false,
                //dialog 显隐控制
                dialogVisible: false,
                dialogTitle: '',
                //table属性
                tableParams: {
                    dataArray: tableData,
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
                            }, {
                                label: '平台名称',
                                prop: 'platformNameSearch',
                            }, {
                                label: '是否启用',
                                prop: 'isDeletedSearch',
                                itemType: 'select',
                                options: [{
                                    label: '请选择',
                                    value: ''
                                }, {
                                    label: '是',
                                    value: '0'
                                }, {
                                    label: '否',
                                    value: '1'
                                }]
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
                                }]
                            }, {
                                label: '商家',
                                prop: 'merchantIdSearch',
                                itemType: 'select',
                                options: [{
                                    label: '请选择',
                                    value: ''
                                }]
                            },],
                        },
                    },
                    searchParams: {
                        keyword: '',
                    },
                    rowHandle: {
                        view: {show: false},
                    },
                    showOperationColumn: true,
                    showMultiSelection: true,
                    apiMethodName: USER_MANAGE_LIST,
                    columnArray: [
                        {label: '供应商公司名', prop: 'supplierCompanyName', width: 140,},
                        {label: '联系人', prop: 'supplierContactPerson', width: 100,},
                        {label: '联系电话', prop: 'supplierContactPhone', width: 100,},
                        {label: '联系人email', prop: 'supplierContactEmail', width: 160,},
                        {label: '联系人手机', prop: 'supplierContactMobile', width: 120,},
                        {label: '开户行(支行)', prop: 'supplierBankName', width: 160,},
                        {label: '账号名', prop: 'supplierBankAccountName', width: 100,},
                        {label: '账户', prop: 'supplierBankAccountNumber', width: 200,},
                        {label: '供应商编码', prop: 'supplierCode', width: 120,},
                        {label: '供应商合作状态', prop: 'cooperationStatus', width: 140,},
                        {label: '结算方式', prop: 'balanceAccountsType', width: 120,},
                        {label: '商务办公地址', prop: 'merchantOfficeAddress', width: 160,},
                        {label: '创建人', prop: 'createdBy', width: 120,},
                        {label: '创立日期', prop: 'createTime', width: 160,},
                        {label: '经销方式', prop: 'distributionType', width: 120,},
                        {label: '纳税人识别号', prop: 'taxRegNo', width: 140,},

                    ],
                },
            }
        }
        ,
        methods: {


            /**
             * 操作列操作事件处理
             * @param index 行索引
             * @param row 列值
             * @param method 方法名称
             */
            handleOperation(index, row, method) {
                if (method === 'update') {
                    this.code = row.code;
                    this.dialogType = 1;
                    this.dialogTitle = '修改品牌信息';
                    this.dialogVisible = true;
                }

            },

            /**
             * dialog全屏
             * */
            toggleFullScreen() {
                if (this.fullscreen) {
                    this.fullscreen = false;
                } else {
                    this.fullscreen = true;
                }
            },
            /**
             * dialog submit 返回值
             * @param value
             */
            coverFormData(value) {
                console.log(value);
            },

        }
        ,
    }
</script>
