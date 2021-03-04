<template>
    <div>
        <v-page-table ref="VTable" :tableParams="tableParams"
                      v-on:submitSearchCondition="submitSearchCondition"
                      v-on:handleSearchTopBtn="handleSearchTopBtn"
                      v-on:keywordSearch="keywordSearch"
                      v-on:handleClick="handleClick"></v-page-table>
        <el-dialog
                :visible.sync="dialogVisible"
                :fullscreen="fullscreen"
                width="80%"
        >
            <div slot="label">
                {{dialogTitle}}
                <a href="javascript:void(0);" @click="toggleFullScreen"
                   class="dialog-label-fullScree fa fa-arrows-alt"></a>
            </div>
            <plat-form-order-detail v-if="dialogType === 1"
                                    :platform-order-code="platformOrderCode"></plat-form-order-detail>
            <exchange-order-detail v-if="dialogType === 2"
                                   :platform-order-code="platformOrderCode"></exchange-order-detail>
            <refund-order-detail :platform-order-code="platformOrderCode" v-if="dialogType === 3"></refund-order-detail>
        </el-dialog>
    </div>
</template>

<script>
    import VPageTable from '~/components/VPageTable'
    import {USER_MANAGE_LIST} from '~/api/methods'
    import ExchangeOrderDetail from '../ExchangeOrder/ExchangeOrderDetail'
    import PlatFormOrderDetail from '../ExchangeOrder/PlatFormOrderDetail'
    import RefundOrderDetail from './RefundOrderDetail'

    export default {
        name: 'ExchangeOrder',
        components: {
            VPageTable, ExchangeOrderDetail, PlatFormOrderDetail, RefundOrderDetail
        },
        data() {
            return {
                dialogType: '',
                platformOrderCode: '',
                code: '',
                fullscreen: false,
                //dialog 显隐控制
                dialogVisible: false,
                dialogTitle: '',
                //table属性
                tableParams: {
                    queryCriteria: {
                        operationBtn: {
                            add: {show: false},
                            deleted: {show: false},
                        },
                        searchFormProps: {
                            col: 4,
                            labelWidth: '100px',
                            formItemArray: [{
                                label: '退款单号',
                                prop: 'code',
                            }, {
                                label: '渠道',
                                prop: 'platformId',
                                itemType: 'select',
                                options: [],
                                value:''
                            }, {
                                label: '商家',
                                prop: 'merchantId',
                                itemType: 'select',
                                options: [],
                                value:''
                            }, {
                                label: '店铺',
                                prop: 'shopId',
                                itemType: 'select',
                                options: [],
                                value:''
                            }, {
                                label: '退货单号',
                                prop: 'grfCode',
                            }, {
                                label: '退款金额',
                                prop: 'refundAmount',
                            }, {
                                label: '开户行',
                                prop: 'accountBank',
                            }, {
                                label: '退款账号',
                                prop: 'refundAccount',
                            }, {
                                label: '退单开始时间',
                                prop: 'createTimeBegin',
                                itemType: 'date',
                                placeholder: '退单开始时间',
                                value: '',
                            }, {
                                label: '退单结束时间',
                                prop: 'createTimeEnd',
                                itemType: 'date',
                                placeholder: '退单结束时间',
                                value: '',
                            }, {
                                label: '买家账号',
                                prop: 'buyerNick',
                            },],
                        },
                    },
                    searchParams: {
                        keyword: '',
                    },
                    showOperationColumn: false,
                    showMultiSelection: false,
                    apiMethodName: this.$Methods.REFUND_ORDER_LIST,
                    columnArray: [
                        {label: '退款单号', prop: 'code',width:200, sortable: true},
                        {label: '退货单号', prop: 'grfCode', width: 100, sortable: true},
                        {label: '平台订单号', prop: 'originalCode',width:200},
                        {label: '渠道', prop: 'platformName',width:100 },
                        {label: '商家', prop: 'merchantName',width:140},
                        {label: '店铺名称', prop: 'shopName',width:100},
                        {label: '退款类型', prop: 'typeName',width:100},
                        {label: '退款原因', prop: 'refundReason',width:400},
                        {label: '退款方式', prop: 'refundMethod',width:100},
                        {label: '退款金额', prop: 'refundAmount',width:100},
                        {label: '审核人', prop: 'responsiblePerson',width:100},
                        {label: '审核时间', prop: 'createTime',width:150},
                        {label: '退回物流单号', prop: 'backCarrierShipCode',width:100},
                        {label: '卖家备注', prop: 'remark',width:100},
                        {label: '买家', prop: 'buyerNick',width:100},
                        {label: '确认退款时间', prop: 'confirmTime',width:100},                    ],
                },
            }
        }
        ,
        methods: {
            submitSearchCondition(data) {
                this.searchData = data;
                console.log(data);
                this.$refs.VTable.searchHandle(data);
            },
            keywordSearch(keyword) {
                this.$refs.VTable.searchHandle({keyword: keyword});
            },
            /**
             * 处理单元格a标签点击事件
             * @param row 列对象
             * @param prop 列属性名称
             */
            handleClick(row, prop) {
                this.platformOrderCode = row.platformOrderCode;
                this.code = row.code;
                this.dialogType = 1;
                this.dialogTitle = '平台订单详情';
                if (prop === 'code') {
                    this.dialogType = 2;
                    this.dialogTitle = '退货单基本信息';
                } else if (prop === 'refCode') {
                    this.dialogType = 3;
                    this.dialogTitle = '退款单基本信息';
                }
                this.fullscreen = false;
                this.dialogVisible = true;
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
            handleSearchTopBtn(methodName) {
                switch (methodName) {
                    case 'refresh' :
                        this.$refs.VTable.refreshTableData();
                        break;
                }
            },
            /**
             * dialog submit 返回值
             * @param value
             */
            coverFormData(value) {
                console.log(value);
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
                this.$Api[this.$Methods.REFUND_ORDER_INIT]().then((resp) => {
                    this.tableParams.queryCriteria.searchFormProps.formItemArray.forEach(item => {
                        switch (item.prop) {
                            case 'platformId':
                                let platformIdArray = resp['platformList'];
                                item.options = this.buildOptions('platformName', 'id', platformIdArray);
                                break;
                            case 'merchantId':
                                let merchantIdArray = resp['merchantList'];
                                item.options = this.buildOptions('merchantName', 'id', merchantIdArray);
                                break;
                            case 'shopId':
                                let shopIdArray = resp['shopMap'];
                                item.options = this.buildOptions('name', 'id', shopIdArray);
                                break;
                        }
                    });
                });
            },
        }, mounted() {
            this.initData();
        }
    }
</script>
