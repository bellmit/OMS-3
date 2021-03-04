<template>
    <div class="page-table-wrap">
        <v-search :queryCriteria="tableParams.queryCriteria"
                  @keywordSearch="keywordSearch"
                  @submitSearchCondition="submitSearchCondition"
                  @searchReset="searchReset"
                  @handleSearchTopBtn="handleSearchTopBtn"></v-search>
        <div>
            <el-tabs type="border-card" @tab-click="tabClick"
                     v-model="tableParams.headTabs.default"
                     v-if="tableParams.headTabs.show">
                <el-tab-pane v-for="(item,index) in tableParams.headTabs.tabsArray"
                             :key="index"
                             :label="item.label"
                             :name="item.value">
                    <v-table :table-params="tableParams" ref="VTable"
                             v-if="tableParams.headTabs.default == item.value"
                             v-on:handleClick="handleClick"
                             v-on:handleSelectionChange="handleSelectionChange"
                             v-on:handleOperation="handleOperation"></v-table>
                </el-tab-pane>
            </el-tabs>
            <el-row v-else>
                <v-table :table-params="tableParams" ref="VTable"
                         v-on:handleOperation="handleOperation"
                         v-on:handleSelectionChange="handleSelectionChange"
                         v-on:handleClick="handleClick"></v-table>
            </el-row>
        </div>
    </div>
</template>

<script>
    import VSearch from './VSearch'
    import VTable from './VTable';

    export default {
        components: {VSearch, VTable},
        name: 'PageTable',
        props: {
            tableParams: {
                dataArray: {
                    type: Array,
                    required: true,
                },
                queryCriteria: {
                    type: Object,
                    required: false,
                },
                columnArray: {
                    type: Array,
                    required: true
                },
                operationColumnWidth: {
                    type: String,
                    required: false,
                },
                showMultiSelection: {
                    type: Boolean,
                    required: false,
                },
                showOperationColumn: {
                    type: Boolean,
                    required: false,
                },
                rowHandle: {
                    view: {
                        type: Object,
                        required: false,
                    },
                    update: {
                        type: Object,
                        required: false,
                    },
                    deleted: {
                        type: Object,
                        required: false,
                    },
                    buttonArray: {
                        type: Array,
                        required: false,
                    },
                    type: Object,
                    required: false,
                },
                apiMethodName: {
                    type: String,
                    required: true,
                },
                searchParams: {
                    type: Object,
                    required: false
                },
                headTabs: {
                    value: {
                        type: Object,
                        required: true
                    },
                    show: {
                        type: Boolean,
                        required: false
                    },
                    tabsArray: {
                        type: Array,
                        required: false
                    },
                    type: Boolean,
                    required: false
                },
                type: Object,
                required: true
            },
        },
        data() {
            return {}
        },
        methods: {
            searchReset() {
                this.$emit('searchReset');
            },
            keywordSearch(val) {
                this.$emit('keywordSearch', val);
            },
            handleSelectionChange(val) {
                this.$emit('handleSelectionChange', val);
            },
            handleOperation(rowIndex, row, methodName) {
                this.$emit('handleOperation', rowIndex, row, methodName);
            },
            handleCommand(command) {
                let array = command.split('#');
                this.handleOperation(array[0], JSON.parse(array[1]), array[2])
            },
            submitSearchCondition(val) {
                this.$emit('submitSearchCondition', val);
            },
            refreshTableData() {
                if (this.tableParams.headTabs.show) {
                    this.$refs.VTable[0].refreshTableData();
                } else {
                    this.$refs.VTable.refreshTableData();
                }
            },
            searchHandle(val) {
                if (this.tableParams.headTabs.show) {
                    this.$refs.VTable[0].searchHandle(val);
                }else{
                    this.$refs.VTable.searchHandle(val);
                }
            },
            handleClick(row, prop) {
                this.$emit('handleClick', row, prop);
            },
            tabClick(item) {
                this.$emit('tabClick', item);
            },
            handleSearchTopBtn(methodName) {
                this.$emit('handleSearchTopBtn', methodName);
            },
        },
        created() {
            if (!this.tableParams.headTabs) {
                this.tableParams.headTabs = {};
            }
            if (this.tableParams.headTabs.show == null) {
                this.tableParams.headTabs.show = false;
            }
        }
    }
</script>
