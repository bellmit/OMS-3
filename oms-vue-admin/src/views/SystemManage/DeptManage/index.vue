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
                    icon: 'oms-icon icon-e66f',
                    showOperation: true,
                    config: {
                        defaultExpandedKeys: ["0"],
                    }
                },
                showEditForm: false,
                editFormProps: {
                    col: 2,
                    labelWidth: '100px',
                    formItemArray: [{
                        label: '部门名称',
                        prop: 'simplename',
                        placeholder: "请输入部门简称",
                        rules: [{required: true, message: '部门名称不能为空!', trigger: 'blur'}],
                        value: ''
                    }, {
                        label: '部门全称',
                        prop: 'fullname',
                        placeholder: "请输入部门全称",
                        rules: [{required: true, message: '部门全称不能为空!', trigger: 'blur'}],
                        value: ''
                    }, {
                        label: '上级部门',
                        prop: 'pName',
                        treeType: 'dept',
                        itemType: 'tree',
                        readonly: true,
                        valueProp: 'pid',
                        valueId: '',
                        value: ''
                    }, {
                        label: '排序',
                        min: 1,
                        max: 9999,
                        prop: 'num',
                        itemType: 'inputNumber',
                        value: '1'
                    }, {
                        label: '备注',
                        placeholder: "请输入备注信息",
                        prop: 'tips',
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
                action: ''
            }
        },
        methods: {
            handleReset(formName) {
                this.$refs.form.resetForm(formName);
                this.editFormProps.formItemArray[2].value = this.pName;
            },
            openDialog() {
                this.$nextTick(() => {
                    this.editFormProps.formItemArray[2].valueId = this.parentId;
                    this.editFormProps.formItemArray[2].value = this.pName;
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
                this.$store.commit(this.$Config.MUTATION_FORM_TREE_DATA, false);
            },
            deleteCategory(data, node) {
                this.parentId = data.value;
                if (data.id === '0') {
                    this.$Config.MessageModal('warning', '根部门不支持删除操作！');
                    return;
                }
                if (node.childNodes.length > 0) {
                    this.$Config.MessageModal('warning', '请先删除子部门后再删除当前部门！');
                    return;
                }
                this.action = 'del';
                this.del(data.label, {deptId: data.id});
            },
            addCategory(data) {
                this.action = 'add';
                this.dialogTitle = '新增部门';
                this.pName = data.label;
                this.parentId = data.id;
                this.dialogVisible = true;
                this.fullscreen = false;
                this.$store.commit(this.$Config.MUTATION_FORM_TREE_DATA, true);
            },
            editCategory(data) {
                if (data.id === '0') {
                    this.$Config.MessageModal('warning', '根部门不支持修改操作！');
                    return;
                }
                this.nodeData = data;
                this.action = 'edit';
                this.dialogTitle = '编辑部门';
                this.pName = this.treeMap[data.value].label;
                this.parentId = data.value;
                this.dialogVisible = true;
                this.fullscreen = false;
                this.$store.commit(this.$Config.MUTATION_FORM_TREE_DATA, true);
            },
            toggleFullScreen() {
                if (this.fullscreen) {
                    this.fullscreen = false;
                } else {
                    this.fullscreen = true;
                }
            },
            coverFormData(value) {
                switch (this.action) {
                    case 'add':
                        this.add(value);
                        break;
                    case 'edit':
                        if (this.nodeData.id === value.pid) {
                            this.$Config.MessageModal('warning', '部门【' + this.nodeData.label + '】父级不能是自己');
                            return;
                        }
                        value.id = this.nodeData.id;
                        this.edit(value);
                        break;
                }
            },
            add(data) {
                this.$Api[this.$Methods.ADD_DEPT](data).then(() => {
                    this.dialogVisible = false;
                    this.$Config.MessageModal('success', '添加成功');
                    this.treeProp.config.defaultExpandedKeys = ["0", data.pid];
                    this.getTreeList();
                });
            },
            edit(data) {
                this.$Api[this.$Methods.UPDATE_DEPT](data).then(() => {
                    this.dialogVisible = false;
                    this.$Config.MessageModal('success', '修改成功');
                    this.treeProp.config.defaultExpandedKeys = ["0", data.id];
                    this.getTreeList();
                });
            },
            del(label, data) {
                this.$Config.ConfirmModal('确定要删除部门【' + label + '】吗?', () => {
                    this.$Api[this.$Methods.DEL_DEPT](data).then(() => {
                        this.$Config.MessageModal('success', '删除成功');
                        this.treeProp.config.defaultExpandedKeys = ["0", this.parentId];
                        this.getTreeList();
                    });
                });
            },
            getTreeList() {
                this.$Api[this.$Methods.DEPT_TREE]().then(resp => {
                    resp.sort(this.$Config.compareArray('id'));
                    this.treeProp.treeData = this.buildTree(resp);
                });
            },
            buildNode(node) {
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
