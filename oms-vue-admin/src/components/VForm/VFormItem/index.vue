<template>
    <div>
        <el-input v-if="item.itemType === 'text' || item.itemType === 'password'"
                  v-model="item.value"
                  :type="item.itemType"
                  :maxlength="item.maxlength"
                  :minlength="item.minlength"
                  :placeholder="item.placeholder"
                  :clearable="true"
                  :suffix-icon="item.suffixIcon"
                  :disabled="item.disabled"
                  :readonly="item.readonly">
        </el-input>
        <el-input-number v-if="item.itemType === 'inputNumber'" style="width: 100%"
                         v-model="item.value"
                         controls-position="right"
                         size="small"
                         :disabled="item.disabled"
                         :min="item.min"
                         :max="item.max"
                         :step="item.step"
        ></el-input-number>
        <el-select v-if="item.itemType === 'select'" @change="selectOnChange" style="width:100%"
                   v-model="item.value"
                   :multiple="item.multiple"
                   filterable
                   clearable
                   :disabled="item.disabled"
                   :readonly="item.readonly"
                   :placeholder="item.placeholder">
            <el-option
                    v-for="(opt ,index5) in item.options"
                    :key="index5"
                    :label="opt.label"
                    :value="opt.value">
            </el-option>
        </el-select>
        <el-date-picker v-if="item.itemType === 'date'"
                        v-model="item.value"
                        :disabled="item.disabled"
                        :readonly="item.readonly"
                        :format="item.format"
                        :align="item.align"
                        :placeholder="item.placeholder">
        </el-date-picker>
        <el-date-picker v-if="item.itemType === 'dateRange'"
                        v-model="item.value"
                        :disabled="item.disabled"
                        :readonly="item.readonly"
                        :format="item.format"
                        :align="item.align"
                        type="daterange"
                        range-separator="-"
                        start-placeholder="开始日期"
                        end-placeholder="结束日期">
        </el-date-picker>
        <el-input v-if="item.itemType === 'textarea'"
                  v-model="item.value"
                  type="textarea"
                  :maxlength="item.maxlength"
                  :minlength="item.minlength"
                  :placeholder="item.placeholder"
                  :clearable="true"
                  :disabled="item.disabled"
                  :readonly="item.readonly"
                  :autosize="item.autosize"
                  :rows="item.rows">
        </el-input>
        <el-radio-group v-if="item.itemType === 'radio'"
                        :disabled="item.disabled"
                        :readonly="item.readonly"
                        v-model="item.value">
            <el-radio
                    v-for="(opt,index5) in item.options"
                    :key="index5"
                    :label="opt.label">{{opt.name}}
            </el-radio>
        </el-radio-group>
        <tiny-mce v-if="item.itemType === 'tinyMce'" :height="200" v-model="item.value"></tiny-mce>

        <el-row v-if="item.itemType === 'tree'">
            <el-dropdown :hide-on-click="false" trigger="click" @click.native="dropClick"
                         placement="bottom-start"
                         class="dropdown_input">
                <span>
                    <el-input v-model="item.value"
                              :placeholder="item.placeholder"
                              :clearable="true"
                              :disabled="item.disabled"
                              :readonly="item.readonly"
                    ></el-input>
                </span>

                <el-dropdown-menu slot="dropdown" style="width: 260px">
                    <el-dropdown-item>
                        <v-tree :treeProp="treeProp" ref="itemTree" v-on:rowClick="rowClick"></v-tree>
                    </el-dropdown-item>
                </el-dropdown-menu>
            </el-dropdown>
        </el-row>
        <el-button v-if="item.itemType==='button'"
                   @click="itemBtnClick"
                   :disabled="item.disabled"
                   size="small"
                   :type="item.type">{{item.text}}
        </el-button>
    </div>
</template>
<script>
    import TinyMce from '~/components/Tinymce'
    import VTree from '~/components/VTree'
    import {mapState} from 'vuex'

    export default {
        name: 'VFormItem',
        components: {TinyMce, VTree},
        props: {
            itemProp: {
                type: Object,
                required: true,
            }
        },
        data() {
            return {
                treeProp: {
                    treeData: [],
                    filterText: '',
                    nodeKey: 'id',
                    icon: 'oms-icon icon-e66f',
                    showOperation: false,
                    showSearch: false,
                    config: {
                        expandOnClickNode: false,
                        defaultExpandedKeys: [0]
                    }
                },
                item: {},
                rootRoleNode: {
                    deptName: "",
                    deptid: 26,
                    id: 0,
                    name: "角色结构",
                    num: 2,
                    pName: "",
                    pid: null,
                    tips: ""
                },
                rootMenuNode: {
                    id: 0,
                    name: '菜单结构',
                    code: '0',
                    pcode: null,
                },
            };
        },
        computed: {
            ...mapState({
                mutationFormTreeData: 'mutationFormTreeData'
            })
        },
        methods: {
            itemBtnClick() {
                this.$emit('itemBtnClick');
            },
            dropClick() {
                this.$refs.itemTree.setCurrentKey(this.item.valueId);
                this.treeProp.config.defaultExpandedKeys = [this.item.valueId];
            },
            getRoleTree() {
                this.$Api[this.$Methods.GET_ROLE_LIST]().then(resp => {
                    resp.push(this.rootRoleNode);
                    this.treeProp.treeData = this.buildTree(resp, 'buildRoleNode', 'id');
                });
            },
            getDeptTree() {
                this.$Api[this.$Methods.DEPT_TREE]().then(resp => {
                    this.treeProp.treeData = this.buildTree(resp, 'buildDeptNode', 'id');
                });
            },
            getMenuTree() {
                this.$Api[this.$Methods.GET_MENU_LIST]().then(resp => {
                    resp.push(this.rootMenuNode);
                    this.treeProp.treeData = this.buildTree(resp, 'buildMenuNode', 'code');
                });
            },
            buildRoleNode(node) {
                return {
                    id: node.id,
                    label: node.name,
                    value: node.pid,
                    deptName: node.deptName,
                    name: node.name,
                    deptid: node.deptid,
                    num: node.num,
                    tips: node.tips,
                }
            },
            buildDeptNode(node) {
                return {
                    id: node.id,
                    label: node.name,
                    value: node.pId,
                    opened: node.isOpen,
                    fullname: node.fullName,
                    simplename: node.name,
                    num: node.num,
                    tips: node.tips,
                    selected: node.checked,
                    disabled: false,
                    loading: false,
                }
            },

            buildMenuNode(node) {
                return {
                    id: node.id,
                    label: node.name,
                    value: node.pcode,
                    code: node.code,
                    name: node.name,
                    icon: node.icon,
                    url: node.url,
                    num: node.num,
                    ismenu: node.ismenu,
                    status: node.status,
                    isopen: node.isopen,

                }
            },
            buildTree(data, buildNode, nodeKey) {
                let treeArray = [];
                let that_ = this;
                data.forEach(function (item) {
                    treeArray.push(that_[buildNode](item));
                });
                let map = {};
                treeArray.forEach(function (item) {
                    delete item.children;
                    map[item[nodeKey]] = item;
                });
                let val = [];
                treeArray.forEach(function (item) {
                    let parent = map[item.value];
                    if (parent) {
                        (parent.children || (parent.children = [])).push(item);
                    } else {
                        val.push(item);
                    }
                });
                return val;
            },
            rowClick(node) {
                let data = node.data;
                this.item.value = node.label;
                this.item.valueId = data.id;
                if (this.item.treeType === 'menu') {
                    this.item.valueId = data.code;
                }
            }, buildSelectOptions(labelKey, valueKey, obj) {
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
            initTreeData() {
                if (this.item.itemType === 'tree') {
                    if (this.item.treeType === 'dept') {
                        this.treeProp.config.defaultExpandedKeys = ['0'];
                        this.getDeptTree();
                    } else if (this.item.treeType === 'role') {
                        this.getRoleTree();
                    } else if (this.item.treeType === 'menu') {
                        this.treeProp.nodeKey = 'code';
                        this.treeProp.config.defaultExpandedKeys = ['0'];
                        this.getMenuTree();
                    }
                } else if (this.item.itemType === 'select' && this.item['selectType'] === 'province') {
                    this.$Api[this.$Methods.GET_AREAS]().then(resp => {
                        this.item.options = this.buildSelectOptions('name', 'id', resp['areas']);
                    });
                }

            }, selectOnChange(value) {
                this.$emit('selectOnChange', value, this.item);
            }
        },
        created() {
            this.item = this.itemProp;
            this.initTreeData();
        }, watch: {
            mutationFormTreeData(current) {
                if (current) {
                    this.initTreeData();
                }
            }
        }
    }
</script>
<style lang="less" scoped>
    .el-dropdown-menu__item:focus, .el-dropdown-menu__item:not(.is-disabled):hover {
        background-color: white !important;
        color: #3c8dbc !important;
    }

    .dropdown_input {
        width: 100% !important;
        margin-left: 0 !important;
    }

    .el-dropdown-menu__item {
        overflow-y: scroll !important;
        height: 300px !important;
        font-size: 12px !important;
    }
</style>
