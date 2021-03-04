<template>
    <div class="search-wrap">
        <el-row>
            <el-col :span="12">
                <el-tooltip v-for="(item,index) in queryCriteria.operationBtn.rowHandle" :key="index"
                            v-if="item.show"
                            class="item" effect="dark" :content="item.text" placement="top-start">
                    <el-button :type="item.type"
                               size="small"
                               :icon="item.icon" @click="handleTopBtn(item.methodName)"><!--&nbsp;{{item.text}}-->
                    </el-button>
                </el-tooltip>
            </el-col>
            <el-col :span="12" style="text-align: right" v-if="queryCriteria.showSearchCondition">
                <el-input placeholder="请输入搜索内容" class="search-keyword"
                          clearable
                          v-model="keyword" @keyup.enter.native="keywordSearch">
                    <el-button slot="append" size="small" icon="el-icon-search" @click="keywordSearch"
                    >搜索
                    </el-button>
                </el-input>
                <el-button v-if="showAdvancedSearchBtn" type="text" style="margin-left: 10px" size="small"
                           @click="showAdvancedSearchFormToggle">{{searchWord}}
                </el-button>
            </el-col>


        </el-row>
        <v-form :formProps="queryCriteria.searchFormProps" ref="VForm"
                v-on:coverFormData="coverFormData"
                v-on:handleReset="searchReset"
                class="search-form"
                size="small"
                v-if="showAdvancedSearchForm"></v-form>

    </div>
</template>

<script>
    import VForm from '~/components/VForm'

    const refresh = {
        text: '刷新',
        show: true,
        type: 'primary',
        methodName: 'refresh',
        icon: 'oms-icon icon-shuaxin3',
    }
    const add = {
        text: '新增',
        show: true,
        type: 'success',
        methodName: 'add',
        icon: 'oms-icon icon-jiahao',

    }
    const deleted = {
        text: '删除',
        show: true,
        type: 'danger',
        methodName: 'delete',
        icon: 'oms-icon icon-delete',
    }
    export default {
        name: 'search',
        components: {
            VForm
        },
        props: {
            queryCriteria: {
                showSearchCondition: {
                    type: Boolean,
                    required: false
                },
                searchFormProps: {
                    type: Object,
                    required: false
                },
                operationBtn: {
                    add: {
                        type: Object,
                        required: false
                    },
                    refresh: {
                        type: Object,
                        required: false
                    },
                    deleted: {
                        type: Object,
                        required: false
                    },
                    rowHandle: {
                        type: Array,
                        required: false
                    },
                    type: Object,
                    required: false
                },
                type: Object,
                required: true
            },
        },
        data() {
            return {
                showAdvancedSearchForm: false,
                showAdvancedSearchBtn: false,
                searchWord: '高级搜索',
                form: {},
                add: {},
                deleted: {},
                refresh: {},
                keyword: '',
            }
        },
        methods: {
            searchReset(formName) {
                this.keyword = '';
                this.$refs.VForm.resetForm(formName);
                this.$emit('searchReset');
            },
            keywordSearch() {
                this.$emit('keywordSearch', this.keyword);
            },
            showAdvancedSearchFormToggle() {
                if (this.showAdvancedSearchForm) {
                    this.showAdvancedSearchForm = false;
                    this.searchWord = '高级搜索';
                } else {
                    this.showAdvancedSearchForm = true;
                    this.searchWord = '简单搜索';
                }
            },
            handleSelectionChange(val) {
                this.$emit('handleSelectionChange', val);
            },
            handleTopBtn(methodName) {
                this.$emit('handleSearchTopBtn', methodName);
            },
            coverFormData(val) {
                this.$emit('submitSearchCondition', val)
            },
            initOperationBtn() {
                if (this.queryCriteria.operationBtn == null) {
                    this.queryCriteria.operationBtn = {};
                }
                if (this.queryCriteria.searchFormProps) {
                    this.showAdvancedSearchBtn = true;
                } else {
                    this.showAdvancedSearchBtn = false;
                }
                if (this.queryCriteria.showSearchCondition == null) {
                    this.queryCriteria.showSearchCondition = true;
                }
                this.initTopBtn();
            },
            initTopBtn() {
                this.add = add;
                this.deleted = deleted;
                this.refresh = refresh;
                if (this.queryCriteria.operationBtn.add) {
                    this.add = Object.assign({}, add, this.queryCriteria.operationBtn.add);
                }
                if (this.queryCriteria.operationBtn.refresh) {
                    this.refresh = Object.assign({}, refresh, this.queryCriteria.operationBtn.refresh);
                }
                if (this.queryCriteria.operationBtn.deleted) {
                    this.deleted = Object.assign({}, deleted, this.queryCriteria.operationBtn.deleted);
                }
                let defaultBtnArray = [this.refresh, this.add, this.deleted];
                if (this.queryCriteria.operationBtn.rowHandle == null) {
                    this.queryCriteria.operationBtn.rowHandle = defaultBtnArray;
                } else {
                    this.queryCriteria.operationBtn.rowHandle = defaultBtnArray.concat(this.queryCriteria.operationBtn.rowHandle);
                }
                let hash = {};
                this.queryCriteria.operationBtn.rowHandle = this.queryCriteria.operationBtn.rowHandle.reduce(function (pre, next) {
                    hash[next.text] ? '' : hash[next.text] = true && pre.push(next);
                    return pre
                }, []);

            }
        },
        created() {
            this.initOperationBtn();
        }, watch: {
            'queryCriteria.operationBtn': function () {
                this.initTopBtn();
            }
        }
    }
</script>

<style scoped>
    .search-wrap {
        padding: 5px 0 10px 0;
    }

    .search-wrap .search-keyword {
        width: 60%;
    }

    .search-wrap .search-form {
        padding: 10px 0;
    }


</style>
