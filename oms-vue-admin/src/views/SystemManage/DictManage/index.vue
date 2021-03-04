<template>
    <div>
        <div style="margin-top: 10px">
            <el-input style="width: 590px"
                      placeholder="输入关键字进行过滤"
                      :clearable="true"
                      v-model="treeProp.filterText" size="small">
            </el-input>
            <v-tree :treeProp="treeProp"
                    v-on:addCategory="addCategory"
                    v-on:editCategory="editCategory"
                    v-on:deleteCategory="deleteCategory"
                    style="width: 600px;"></v-tree>
        </div>

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
            <v-form :formProps="editFormProps" ref="form"
                    v-on:handleReset="handleReset"
                    v-on:coverFormData="coverFormData"></v-form>

        </el-dialog>

    </div>
</template>

<script>
    import VTree from '~/components/VTree'
    import VForm from '~/components/VForm';

    export default {
        name: 'userManage',
        components: {
            VForm, VTree
        },
        data() {
            return {
                treeProp: {
                    treeData: [],
                    filterText: '',
                    nodeKey: 'id',
                    icon: 'oms-icon icon-zidian,oms-icon icon-zidian1',
                    showOperation: true,
                    config: {
                        defaultExpandedKeys: [0],
                    }
                },
                showEditForm: false,
                editFormProps: {
                    col: 2,
                    labelWidth: '100px',
                    formItemArray: [{
                        label: '字典名称',
                        prop: 'name',
                        placeholder: "请输入字典简称",
                        rules: [{required: true, message: '字典名称不能为空!', trigger: 'blur'}],
                        value: ''
                    }, {
                        label: '上级字典',
                        prop: 'pName',
                        readonly: true,
                        value: ''
                    }, {
                        label: '字典值',
                        prop: 'num',
                        value: ''
                    }],
                },
                dialogVisible: false,
                dialogTitle: '编辑用户信息',
                fullscreen: false,
                treeMap: {},
                parentId: '',
                pName: '',
                nodeData: '',
                action: '',
                rootNode: {
                    id: 0,
                    name: '字典结构',
                    pid: null,
                }
            }
        },
        methods: {
            handleReset(formName) {
                this.$refs.form.resetForm(formName);
                this.editFormProps.formItemArray[1].value = this.pName;
            },
            openDialog() {
                this.$nextTick(() => {
                    this.editFormProps.formItemArray[1].value = this.pName;
                    if (this.action === 'edit') {
                        this.editFormProps.formItemArray.forEach(item => {
                            for (let key in this.nodeData) {
                                if (key === item.prop) {
                                    item.value = this.nodeData[key];
                                }
                            }
                        });
                    }
                });
            },
            closeDialog() {
                this.$refs.form.resetForm('validateMyForm');
            },
            deleteCategory(data, node) {
                this.parentId = data.value;
                if (data.id === 0) {
                    this.$Config.MessageModal('warning', '根字典不支持删除操作！');
                    return;
                }
                if (node.childNodes.length > 0) {
                    this.$Config.MessageModal('warning', '请先删除子字典后再删除当前字典！');
                    return;
                }
                this.action = 'del';
                this.del(data.label, {dictId: data.id});
            },
            addCategory(data) {
                this.action = 'add';
                this.dialogTitle = '新增字典';
                this.pName = data.label;
                this.parentId = data.id;
                this.dialogVisible = true;
                this.fullscreen = false;
            },
            editCategory(data) {
                if (data.id === 0) {
                    this.$Config.MessageModal('warning', '根字典不支持修改操作！');
                    return;
                }
                this.nodeData = data;
                this.action = 'edit';
                this.dialogTitle = '编辑字典';
                this.pName = this.treeMap[data.value].label;
                this.parentId = data.value;
                this.dialogVisible = true;
                this.fullscreen = false;
            },
            toggleFullScreen() {
                if (this.fullscreen) {
                    this.fullscreen = false;
                } else {
                    this.fullscreen = true;
                }
            },
            coverFormData(value) {
                value.pid = this.parentId;
                switch (this.action) {
                    case 'add':
                        this.add(value);
                        break;
                    case 'edit':
                        value.id = this.nodeData.id;
                        this.edit(value);
                        break;
                }
            },
            add(data) {
                this.$Api[this.$Methods.ADD_DICT](data).then(() => {
                    this.dialogVisible = false;
                    this.$Config.MessageModal('success', '添加成功');
                    this.treeProp.config.defaultExpandedKeys = ["0", data.pid];
                    this.getTreeList();
                });
            },
            edit(data) {
                this.$Api[this.$Methods.EDIT_DICT](data).then(() => {
                    this.dialogVisible = false;
                    this.$Config.MessageModal('success', '修改成功');
                    this.treeProp.config.defaultExpandedKeys = ["0", data.id];
                    this.getTreeList();
                });
            },
            del(label, data) {
                this.$Config.ConfirmModal('确定要删除字典【' + label + '】吗?', () => {
                    this.$Api[this.$Methods.DEL_DICT](data).then(() => {
                        this.$Config.MessageModal('success', '删除成功');
                        this.treeProp.config.defaultExpandedKeys = ["0", this.parentId];
                        this.getTreeList();
                    });
                });
            },
            getTreeList() {
                this.$Api[this.$Methods.GET_DICT_LIST]().then(resp => {
                    resp.push(this.rootNode);
                    this.treeProp.treeData = this.buildTree(resp);
                });
            },
            buildNode(node) {
                return {
                    id: node.id,
                    label: node.name,
                    value: node.pid,
                    num: node.num,
                    name: node.name,
                }
            },
            buildTree(data) {
                let treeArray = [];
                let that_ = this;
                data.forEach(function (item) {
                    treeArray.push(that_.buildNode(item));
                });
                let map = {};
                treeArray.forEach(function (item) {
                    delete item.children;
                    map[item.id] = item;
                });
                this.treeMap = map;
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
            }
        }, mounted() {
            this.getTreeList();
        }
    }
</script>
<style lang="less">

</style>
