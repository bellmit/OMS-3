<template>
    <div>
        <el-table
                height="480"
                :data="dataArray"
                border
                @selection-change="handleSelectionChange">
            <el-table-column v-if="tableParams.showMultiSelection"
                             type="selection"
                             :fixed="true"
                             width="32">

            </el-table-column>
            <el-table-column label="操作" v-if="tableParams.showOperationColumn"
                             :width="tableParams.operationColumnWidth+'px'"
                             align="center"
                             :fixed="true"
                             header-align="center"
            >
                <template slot-scope="scope">
                    <el-button v-for="(item,index) in colBtnArray" v-if="item.show" :key="index"
                               :type="item.type"
                               :disabled="item.disabled"
                               :loading="item.loading" :class="item.class" :style="item.style"
                               size="mini"
                               @click="handleOperation(scope.$index,scope.row,item.methodName,scope)">
                        {{item.text}}
                    </el-button>
                    <el-dropdown v-if="colDropdownBtnArray.length>0"
                                 @command="handleCommand">
                                <span class="el-dropdown-link">
                                   更多<i class="el-icon-arrow-down el-icon--right"></i>
                                </span>
                        <el-dropdown-menu slot="dropdown">
                            <el-dropdown-item v-for="(item,index) in colDropdownBtnArray"
                                              v-if="item.show"
                                              :command="scope.$index+'#'+JSON.stringify(scope.row)+'#'+item.methodName"
                                              :key="index"
                                              :divided="item.divided">
                                {{item.text}}
                            </el-dropdown-item>
                        </el-dropdown-menu>
                    </el-dropdown>

                </template>
            </el-table-column>
            <el-table-column v-for="(item,index_key) in tableParams.columnArray"
                             v-if="item.show"
                             :label="item.label"
                             :width="item.width"
                             :prop="item.prop"
                             :column-key="item.prop"
                             :fixed="item.fixed"
                             :render-header="item.render"
                             :sortable="item.sortable"
                             :resizable="item.resizable"
                             :show-overflow-tooltip="item.showTip"
                             :align="item.align"
                             :header-align="item.align"
                             :key="index_key">
                <template slot-scope="scope">
                    <a v-if="item.formatType === 'a'"
                       @click="handleClick(scope.row,item.prop)"
                       href="javascript:void(0);">
                        {{scope.row[item.prop]}}
                    </a>
                    <span v-else-if="item.formatType === 'input'">
                        <el-input v-model="scope.row[item.prop]" clearable></el-input>
                    </span>
                    <span v-else v-html="scope.row[item.prop]"></span>
                </template>

            </el-table-column>
        </el-table>
        <el-pagination class="el-pagination"
                       layout="total, prev, pager, next,sizes,jumper"
                       :total="page.total"
                       :current-page="page.currentPage"
                       @size-change="sizeChange"
                       @current-change="currentChange"
        >
        </el-pagination>
    </div>
</template>

<script>
    const viewBtn = {
        text: '查看',
        show: true,
        type: 'text',
        methodName: 'view'
    }
    const updateBtn = {
        text: '修改',
        show: true,
        type: 'text',
        methodName: 'update'
    }
    const deletedBtn =
        {
            text: '删除',
            show: true,
            type: 'text',
            methodName: 'delete'
        };
    export default {
        name: "VTable",
        props: {
            tableParams: {
                type: Object,
                required: true
            }
        }, data() {
            return {
                dataArray: [],
                alert: {
                    show: false,
                    type: '',
                    title: '',
                    description: ''
                },
                page: {
                    currentPage: 1,
                    pageSize: 10,
                    total: 0,
                },
                colBtnArray: [],
                colDropdownBtnArray: [],
                viewBtn: {},
                updateBtn: {},
                deletedBtn: {}
            }
        }, methods: {
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
            sizeChange(pageSize) {
                this.page.pageSize = pageSize;
                this.page.currentPage = 1;
                this.getApiData();
            },
            currentChange(currentPage) {
                this.page.currentPage = currentPage;
                this.getApiData();
            },
            refreshTableData() {
                this.getApiData();
            },
            searchHandle(val) {
                if (this.tableParams.searchParams) {
                    this.tableParams.searchParams = Object.assign({}, this.tableParams.searchParams, val);
                }
                this.page.currentPage = 1;
                this.page.pageSize = 10;
                this.getApiData();
            },
            getApiData() {
                if (this.tableParams.dataArray != null) {
                    let array = Object.values(Object.assign({}, this.tableParams.dataArray));
                    this.dataArray = array.slice((this.page.currentPage - 1) * this.page.pageSize, this.page.pageSize * this.page.currentPage);
                    this.page.total = Object.values(this.tableParams.dataArray).length
                } else {
                    this.dataArray = [];
                    let searchParams = {
                        pageNo: this.page.currentPage,
                        pageSize: this.page.pageSize
                    };
                    if (this.tableParams.searchParams) {
                        searchParams = Object.assign({}, this.tableParams.searchParams, searchParams);
                    }
                    searchParams.sort = this.tableParams.searchParams.sort != null ? this.tableParams.searchParams.sort : "ID";
                    searchParams.order = this.tableParams.searchParams.order != null ? this.tableParams.searchParams.order : "DESC";
                    this.$Api[this.tableParams.apiMethodName](searchParams).then(resp => {
                        this.dataArray = resp.rows;
                        this.page.total = parseInt(resp.total);
                    });
                }
            },

            handleClick(row, prop) {
                this.$emit('handleClick', row, prop);
            },
            initButtonArray() {
                if (this.tableParams.rowHandle == null) {
                    this.tableParams.rowHandle = {};
                }
                this.viewBtn = viewBtn;
                this.updateBtn = updateBtn;
                this.deletedBtn = deletedBtn;
                if (this.tableParams.rowHandle.view) {
                    this.viewBtn = Object.assign({}, viewBtn, this.tableParams.rowHandle.view);
                }
                if (this.tableParams.rowHandle.update) {
                    this.updateBtn = Object.assign({}, updateBtn, this.tableParams.rowHandle.update);
                }
                if (this.tableParams.rowHandle.deleted) {
                    this.deletedBtn = Object.assign({}, deletedBtn, this.tableParams.rowHandle.deleted);
                }
                let defaultBtnArray = [this.viewBtn, this.updateBtn, this.deletedBtn];
                if (this.tableParams.rowHandle.buttonArray == null) {
                    this.tableParams.rowHandle.buttonArray = defaultBtnArray;
                } else {
                    this.tableParams.rowHandle.buttonArray = defaultBtnArray.concat(this.tableParams.rowHandle.buttonArray);
                }
                let btnArray = Object.values(this.tableParams.rowHandle.buttonArray);
                let hash = {};
                btnArray = btnArray.reduce(function (pre, next) {
                    hash[next.text] ? '' : hash[next.text] = true && pre.push(next);
                    return pre
                }, []);
                let count = 0;
                let numArray = [];
                btnArray.forEach(item => {
                    if (!item.show) {
                        numArray.push(count);
                    }
                    count++;
                });
                for (let index = 0; index < numArray.length; index++) {
                    btnArray.splice(numArray[index] - index, 1);
                }
                if (btnArray.length > 3) {
                    this.colBtnArray = btnArray.slice(0, 2);
                    this.colDropdownBtnArray = btnArray.slice(2, btnArray.length);
                } else {
                    this.colBtnArray = btnArray;
                    this.colDropdownBtnArray = [];
                }
            },
            checkApiMethod() {
                if (!this.tableParams.apiMethodName) {
                    this.alert = {
                        show: true,
                        title: '系统提示',
                        type: 'error',
                        description: '请配置Api接口请求方法名!'
                    }
                } else {
                    this.alert.show = false;
                }
            },
        },
        created() {
            this.tableParams.columnArray.forEach(item => {
                if (item.show == null) {
                    item.show = true;
                }
            });
            if (this.tableParams.showOperationColumn == null) {
                this.tableParams.showOperationColumn = true;
            }
            if (this.tableParams.showMultiSelection == null) {
                this.tableParams.showMultiSelection = true;
            }
            if (this.tableParams.operationColumnWidth == null) {
                this.tableParams.operationColumnWidth = '140';
            }
            if (this.tableParams.searchParams == null) {
                this.tableParams.searchParams = {};
            }
            this.checkApiMethod();
            this.initButtonArray();
            this.getApiData();
        }, watch: {
            'tableParams.dataArray'() {
                let array = Object.values(Object.assign({}, this.tableParams.dataArray));
                this.dataArray = array.slice((this.page.currentPage - 1) * this.page.pageSize, this.page.pageSize * this.page.currentPage);
                this.page.total = Object.values(this.tableParams.dataArray).length;
            }
        }
    }
</script>

<style scoped>
    .page-table-wrap .el-pagination {
        margin-top: 20px;
        text-align: right;
    }

    .el-alert-wrap {
        margin-bottom: 20px;
    }
</style>