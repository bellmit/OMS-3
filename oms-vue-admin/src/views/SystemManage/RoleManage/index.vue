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
                    v-on:deliverCategory="deliverCategory"
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
            <v-form :formProps="editFormProps" v-if="dialogType === '1'" ref="form"
                    v-on:handleReset="handleReset"
                    v-on:coverFormData="coverFormData"></v-form>

            <distribute-menu v-if="dialogType === '2'"
                             :closeDialogModal="closeDialogModal"
                             :role-id="roleId"></distribute-menu>
        </el-dialog>

    </div>
</template>

<script>
    import VTree from '~/components/VTree'
    import VForm from '~/components/VForm';
    import DistributeMenu from './DistributeMenu'

    export default {
        components: {
            VForm, VTree, DistributeMenu
        },
        data() {
            return {
                roleId: '',
                dialogType: '1',
                treeProp: {
                    filterText: '',
                    treeData: [],
                    nodeKey: 'id',
                    icon: 'oms-icon icon-jiaose',
                    showOperation: true,
                    showDistributeMenu: true,
                    config: {
                        defaultExpandedKeys: [0],
                    }
                },
                showEditForm: false,
                editFormProps: {
                    col: 2,
                    labelWidth: '100px',
                    formItemArray: [{
                        label: '角色名称',
                        prop: 'name',
                        placeholder: "请输入角色名称",
                        rules: [{required: true, message: '角色名称不能为空!', trigger: 'blur'}],
                        value: ''
                    }, {
                        label: '别名',
                        prop: 'tips',
                        placeholder: "请输入角色全称",
                        rules: [{required: true, message: '角色全称不能为空!', trigger: 'blur'}],
                        value: ''
                    }, {
                        label: '上级角色',
                        prop: 'pName',
                        treeType: 'role',
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
                        label: '部门名称',
                        placeholder: "请输入部门名称",
                        prop: 'deptName',
                        itemType: 'tree',
                        treeType: 'dept',
                        valueId: '',
                        readonly: true,
                        valueProp: 'deptid',
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
                    deptName: "",
                    deptid: 26,
                    id: 0,
                    name: "角色结构",
                    num: 2,
                    pName: "",
                    pid: null,
                    tips: ""
                }
            }
        },
        methods: {
            closeDialogModal() {
                this.roleId = '';
                this.fullscreen = false;
                this.dialogVisible = false;
            },
            deliverCategory(data) {
                if (data.id === 0) {
                    this.$Config.MessageModal('warning', '根角色不支持权限分配操作！');
                    return;
                }
                this.dialogVisible = true;
                this.fullscreen = false;
                this.dialogType = '2';
                this.dialogTitle = '权限分配';
                this.roleId = data.id + '';
            },
            handleReset(formName) {
                this.$refs.form.resetForm(formName);
                this.editFormProps.formItemArray[2].value = this.pName;
            },
            openDialog() {
                this.$nextTick(() => {
                    this.editFormProps.formItemArray[2].value = this.pName;
                    this.editFormProps.formItemArray[2].valueId = this.parentId;
                    if (this.action === 'edit') {
                        this.editFormProps.formItemArray[4].valueId = this.nodeData.deptid;
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
                if (this.dialogType === '1') {
                    this.$refs.form.resetForm('validateMyForm');
                    this.$store.commit(this.$Config.MUTATION_FORM_TREE_DATA, false);
                } else {
                    this.roleId = '';
                }
            },
            deleteCategory(data, node) {
                this.parentId = data.value;
                if (data.id === 0) {
                    this.$Config.MessageModal('warning', '根角色不支持删除操作！');
                    return;
                }
                if (node.childNodes.length > 0) {
                    this.$Config.MessageModal('warning', '请先删除子角色后再删除当前角色！');
                    return;
                }
                this.action = 'del';
                this.del(data.label, {roleId: data.id});
            },
            addCategory(data) {
                this.dialogType = '1';
                this.action = 'add';
                this.dialogTitle = '新增角色';
                this.pName = data.label;
                this.parentId = data.id;
                this.dialogVisible = true;
                this.fullscreen = false;
                this.$store.commit(this.$Config.MUTATION_FORM_TREE_DATA, true);
            },
            editCategory(data) {
                if (data.id === 0) {
                    this.$Config.MessageModal('warning', '根角色不支持修改操作！');
                    return;
                }
                this.nodeData = data;
                this.dialogType = '1';
                this.action = 'edit';
                this.dialogTitle = '编辑角色';
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
                            this.$Config.MessageModal('warning', '角色【' + this.nodeData.label + '】父级不能是自己');
                            return;
                        }
                        value.id = this.nodeData.id;
                        this.edit(value);
                        break;
                }
            },
            add(data) {
                this.$Api[this.$Methods.ADD_ROLE](data).then(() => {
                    this.dialogVisible = false;
                    this.$Config.MessageModal('success', '添加成功');
                    this.treeProp.config.defaultExpandedKeys = [0, data.pid];
                    this.getTreeList();
                });
            },
            edit(data) {
                this.$Api[this.$Methods.EDIT_ROLE](data).then(() => {
                    this.dialogVisible = false;
                    this.$Config.MessageModal('success', '修改成功');
                    this.treeProp.config.defaultExpandedKeys = [0, data.id];
                    this.getTreeList();
                });
            },
            del(label, data) {
                this.$Config.ConfirmModal('确定要删除角色【' + label + '】吗?', () => {
                    this.$Api[this.$Methods.DEL_ROLE](data).then(() => {
                        this.$Config.MessageModal('success', '删除成功');
                        this.treeProp.config.defaultExpandedKeys = [0, this.parentId];
                        this.getTreeList();
                    });
                });
            },
            getTreeList() {
                this.$Api[this.$Methods.GET_ROLE_LIST]().then(resp => {
                    resp.push(this.rootNode);
                    this.treeProp.treeData = this.buildTree(resp);
                });
            },
            buildNode(node) {
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
