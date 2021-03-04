<template>
    <div>
        <v-page-table ref="VTable" :tableParams="tableParams"
                      v-on:handleSearchTopBtn="handleSearchTopBtn"
                      v-on:handleSelectionChange="handleSelectionChange"
                      v-on:submitSearchCondition="submitSearchCondition"
                      v-on:keywordSearch="keywordSearch"
                      v-on:handleOperation="handleOperation"></v-page-table>
    </div>
</template>

<script>
    import VPageTable from '~/components/VPageTable'

    export default {
        name: 'Goods_Manage',
        components: {
            VPageTable
        },
        data() {
            return {
                //table属性
                tableParams: {
                    queryCriteria: {
                        operationBtn: {
                            add: {
                                text: '下载',
                                methodName: 'download',
                                icon: 'oms-icon icon-download'
                            },
                        },
                        searchFormProps: {
                            col: 4,
                            labelWidth: '100px',
                            formItemArray: [{
                                label: '编号',
                                prop: 'fileCode',
                            }, {
                                label: '状态',
                                prop: 'status',
                                itemType: 'select',
                                options: [
                                    {
                                        label: '请选择',
                                        value: ''
                                    }, {
                                        label: '正在进行',
                                        value: '0'
                                    }, {
                                        label: '已完成',
                                        value: '1'
                                    }, {
                                        label: '导出异常',
                                        value: '2'
                                    },
                                ]
                            },],
                        },
                    },
                    searchParams: {
                        keyword: '',
                    },
                    rowHandle: {
                        update: {show: false},
                        view: {text: '下载', methodName: 'download'},
                    },
                    showOperationColumn: true,
                    showMultiSelection: true,
                    apiMethodName: this.$Methods.EXPORT_FILE_LIST,
                    columnArray: [
                        {label: '编号', prop: 'fileCode',},
                        {label: '文件名称', prop: 'fileName',},
                        {label: '状态', prop: 'statusName',},
                        {label: '创建时间', prop: 'createTime',},
                    ],
                }, checkData: '',
            }
        }
        ,
        methods: {
            submitSearchCondition(data) {
                this.$refs.VTable.searchHandle(data);
            },
            keywordSearch(keyword) {
                this.$refs.VTable.searchHandle({keyword: keyword});
            },
            handleSelectionChange(val) {
                this.checkData = val;
            },
            /**
             * 操作列操作事件处理
             * @param index 行索引
             * @param row 列值
             * @param method 方法名称
             */
            handleOperation(index, row, method) {
                if (method === 'delete') {
                    this.deleteByCodes([row.id]);
                } else {
                    if (!row['fileCode']) {
                        this.$Config.MessageModal('warning', '当前文件还在生成中，请稍后再下载！');
                        return;
                    }
                    this.downloadFile([row.id]);
                }
            },
            handleSearchTopBtn(methodName) {
                switch (methodName) {
                    case 'refresh' :
                        this.$refs.VTable.refreshTableData();
                        break;
                    case 'download':
                        if (this.$Config.hasSelectRows(this.checkData)) {
                            let fileIdArray = [];
                            this.checkData.forEach(item => {
                                if (!item['fileCode']) {
                                    this.$Config.MessageModal('warning', '当前文件还在生成中，请稍后再下载！');
                                    throw new Error("当前文件还在生成中");
                                }
                                fileIdArray.push(item.id);
                            });
                            this.downloadFile(fileIdArray);
                        }
                        break;

                    case 'delete':
                        if (this.$Config.hasSelectRows(this.checkData)) {
                            let idArray = [];
                            this.checkData.forEach(item => {
                                idArray.push(item.id);
                            });
                            this.deleteByCodes(idArray);
                        }
                        break;
                }
            }, deleteByCodes(idList) {
                this.$Config.ConfirmModal('确定要删除吗?', () => {
                    this.$Api[this.$Methods.DELETE_EXPORT_FILE_BY_CODES](idList).then(() => {
                        this.$Config.MessageModal('success', '删除成功');
                        this.$refs.VTable.refreshTableData();
                    });
                });
            }, downloadFile(fileIdArray) {
                this.$Api[this.$Methods.VALIDATE_EXPORT_FILE](fileIdArray).then((resp) => {
                    if (resp.status === "1") {
                        this.$Config.MessageModal('warning', resp.message);
                    } else {
                        window.location.href=this.$Config.DOWNLOAD_TEMP_SO_FILE_URL+fileIdArray.join(',');
                    }
                });
            },

        },
    }
</script>
