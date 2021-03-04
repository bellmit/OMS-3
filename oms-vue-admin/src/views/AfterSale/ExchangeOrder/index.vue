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
                :width="dialogWidth"
                @close="closeDialog"
        >
            <div slot="title">
                {{dialogTitle}}
                <a href="javascript:void(0);" @click="toggleFullScreen"
                   class="dialog-title-fullScree oms-icon icon-quanping2"></a>
            </div>
            <plat-form-order-detail v-if="dialogType === 1"
                                    :code="code"
                                    :action="action"
            ></plat-form-order-detail>
            <exchange-order-detail v-if="dialogType === 2"
                                   :platform-order-code="platformOrderCode"></exchange-order-detail>
        </el-dialog>
    </div>
</template>

<script>
    import VPageTable from '~/components/VPageTable'
    import {USER_MANAGE_LIST} from '~/api/methods'
    import ExchangeOrderDetail from './ExchangeOrderDetail.vue'
    import PlatFormOrderDetail from './PlatFormOrderDetail'

    export default {
        name: 'ExchangeOrder',
        components: {
            VPageTable, ExchangeOrderDetail, PlatFormOrderDetail
        },
        data() {
            return {
                dialogType: '',
                platformOrderCode: '',
                code: '',
                action: '',
                fullscreen: false,
                //dialog 显隐控制
                dialogVisible: false,
                dialogTitle: '',
                dialogWidth: '80%',
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
                                label: '平台单号',
                                prop: 'originalCode',
                                itemType: 'textarea',
                                rows: 1
                            }, {
                                label: '渠道',
                                prop: 'platformId',
                                itemType: 'select',
                                options: [],
                                value: ''
                            }, {
                                label: '商家',
                                prop: 'merchantId',
                                itemType: 'select',
                                options: [],
                                value: ''
                            }, {
                                label: '店铺',
                                prop: 'shopId',
                                itemType: 'select',
                                options: [],
                                value: ''
                            }, {
                                label: '退换货单号',
                                prop: 'code',
                            }, {
                                label: '申请原因',
                                prop: 'prescription',
                                itemType: 'select',
                                options: [{
                                    label: '请选择',
                                    value: ''
                                }, {
                                    label: '七天无理由',
                                    value: '0'
                                }]
                            }, {
                                label: '退款总额',
                                prop: 'totalAmount',
                            }, {
                                label: '是否退税费',
                                prop: 'deliveryFee',
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
                                }]
                            }],
                        },
                    },
                    searchParams: {
                        keyword: '',
                    },
                    showOperationColumn: false,
                    showMultiSelection: false,
                    apiMethodName: this.$Methods.GRF_HEADER_LIST,
                    columnArray: [
                        {label: '退货单号', prop: 'refOriginalCode', formatType: 'a', width: 180, sortable: true},
                        {label: '订单号', prop: 'code', width: 100},
                        {label: '平台订单号', prop: 'originalCode', width: 200},
                        {label: '渠道', prop: 'platformName', width: 100},
                        {label: '商家', prop: 'merchantName', width: 150},
                        {label: '店铺名称', prop: 'shopName', width: 100},
                        {label: '退换货类型', prop: 'type', width: 100},
                        {label: '买家', prop: 'buyerName', width: 100},
                        {label: '买家账号', prop: 'buyerNick', width: 100},
                        {label: '买家描述', prop: 'endUserDesc', width: 400},
                        {label: '申请原因', prop: 'endUserApplyReson', width: 100},
                        {label: '申请退款总额', prop: 'totalAmount', width: 100},
                        {label: '是否退运费', prop: 'isRefund', width: 100},
                        {label: '运费', prop: 'deliveryFee', width: 100},
                        {label: '退货金额调整备注', prop: 'deliveryFee', width: 120},
                        {label: '退换货原因说明', prop: 'returnReasonDesc', width: 120},
                        {label: '退货单状态', prop: 'returnReasonDesc', width: 100},
                        {label: '审核人', prop: 'checkBy', width: 100},
                        {label: '卖家备注', prop: 'isOtherFee', width: 100},
                        {label: '取消备注', prop: 'cancelRemark', width: 100},
                        {label: '创建时间', prop: 'createTime', width: 140},
                        {label: '退回物流公司', prop: 'backCarrierName', width: 100},
                        {label: '退回物流单号', prop: 'backCarrierShipCode', width: 100},
                        {label: '收货人姓名', prop: 'goodReceiverName', width: 100},
                        {label: '收货地址_省', prop: 'fetchAddress', width: 100},
                        {label: '收货地址_市', prop: 'fetchAddress', width: 100},
                        {label: '收货地址_区', prop: 'fetchAddress', width: 100},
                        {label: '联系电话', prop: 'goodReceiverTel', width: 100},
                        {label: '邮编', prop: 'goodReceiverTel', width: 100},
                        {label: '退货商品种类数', prop: 'skuQty', width: 120},
                        {label: '退货商品数', prop: 'skuQty', width: 100},
                    ],

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
                this.code = row.code;
                this.dialogType = 1;
                this.dialogTitle = '平台订单详情';
                if (prop === 'code') {
                    this.dialogType = 2;
                    this.dialogTitle = '退货单基本信息';
                }
                this.action = 'detail';
                this.dialogWidth = '1400px';
                this.fullscreen = false;
                this.dialogVisible = true;
            },
            refreshTable() {
                this.$refs.VTable.refreshTableData();
            },
            closeDialog() {
                this.action = "";
            },
            handleSearchTopBtn(methodName) {
                switch (methodName) {
                    case 'refresh' :
                        this.$refs.VTable.refreshTableData();
                        break;
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
                this.$Api[this.$Methods.GRF_HEADER_INIT]().then((resp) => {
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
