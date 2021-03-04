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
                    nodeKey: 'code',
                    icon: 'oms-icon icon-zidian,oms-icon icon-zidian1',
                    showOperation: true,
                    config: {
                        defaultExpandedKeys: ['0'],
                    }
                },
                showEditForm: false,
                editFormProps: {
                    col: 2,
                    labelWidth: '100px',
                    formItemArray: [{
                        label: '菜单名称',
                        prop: 'name',
                        placeholder: "请输入菜单简称",
                        rules: [{required: true, message: '菜单名称不能为空!', trigger: 'blur'}],
                        value: ''
                    }, {
                        label: '请求地址',
                        prop: 'url',
                        placeholder: "请输入请求地址",
                        rules: [{required: true, message: '请求地址不能为空!', trigger: 'blur'}],
                        value: ''
                    }, {
                        label: '菜单编号',
                        prop: 'code',
                        placeholder: "请输入菜单编号",
                        rules: [{required: true, message: '菜单编号不能为空!', trigger: 'blur'}],
                        value: ''
                    }, {
                        label: '排序',
                        prop: 'num',
                        min: 1,
                        max: 9999,
                        itemType: 'inputNumber',
                        placeholder: "请输入排序值",
                        rules: [{required: true, message: '排序值不能为空!', trigger: 'blur'}],
                        value: ''
                    }, {
                        label: '上级菜单',
                        prop: 'pName',
                        treeType: 'menu',
                        itemType: 'tree',
                        readonly: true,
                        valueProp: 'pcode',
                        valueId: '',
                        value: ''
                    }, {
                        label: '图标',
                        prop: 'icon',
                        value: ''
                    }, {
                        label: '是否是菜单',
                        itemType: 'select',
                        options: [{
                            label: '是',
                            value: '1',
                        }, {
                            label: '否',
                            value: '0',
                        }],
                        prop: 'ismenu',
                        value: '1'
                    }],
                },
                dialogVisible: false,
                dialogTitle: '编辑用户信息',
                fullscreen: false,
                treeMap: {},
                parentCode: '',
                pName: '',
                nodeData: '',
                action: '',
                rootNode: {
                    id: 0,
                    name: '菜单结构',
                    code: '0',
                    pcode: null,
                }
            }
        },
        methods: {
            handleReset(formName) {
                this.$refs.form.resetForm(formName);
                this.editFormProps.formItemArray[4].value = this.pName;
            },
            openDialog() {
                this.$nextTick(() => {
                    this.editFormProps.formItemArray[4].value = this.pName;
                    this.editFormProps.formItemArray[4].valueId = this.parentCode;
                    if (this.action === 'edit') {
                        this.editFormProps.formItemArray.forEach(item => {
                            for (let key in this.nodeData) {
                                if (key === item.prop) {
                                    item.value = this.nodeData[key];
                                }
                            }
                        });
                        this.editFormProps.formItemArray[6].value = this.nodeData.ismenu + '';
                    }
                });
            },
            closeDialog() {
                this.$refs.form.resetForm('validateMyForm');
            },
            deleteCategory(data, node) {
                if (data.id === 0) {
                    this.$Config.MessageModal('warning', '根菜单不支持删除操作！');
                    return;
                }
                if (node.childNodes.length > 0) {
                    this.$Config.MessageModal('warning', '请先删除子菜单后再删除当前菜单！');
                    return;
                }
                this.action = 'del';
                this.del(data.label, {menuId: data.id});
            },
            addCategory(data) {
                this.action = 'add';
                this.dialogTitle = '新增菜单';
                this.pName = data.label;
                this.parentCode = data.code;
                this.dialogVisible = true;
                this.fullscreen = false;
            },
            editCategory(data) {
                if (data.id === 0) {
                    this.$Config.MessageModal('warning', '根菜单不支持修改操作！');
                    return;
                }
                this.nodeData = data;
                this.action = 'edit';
                this.dialogTitle = '编辑菜单';
                this.pName = this.treeMap[data.value].label;
                this.parentCode = data.value;
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
                switch (this.action) {
                    case 'add':
                        this.add(value);
                        break;
                    case 'edit':
                        if (value.code === value.pcode) {
                            this.$Config.MessageModal('warning', '菜单【' + this.nodeData.label + '】父级不能是自己');
                            return;
                        }
                        value.id = this.nodeData.id;
                        this.edit(value);
                        break;
                }
            },
            add(data) {
                this.$Api[this.$Methods.ADD_MENU](data).then(() => {
                    this.dialogVisible = false;
                    this.$Config.MessageModal('success', '添加成功');
                    this.treeProp.config.defaultExpandedKeys = ["0", data.code];
                    this.getTreeList();
                });
            },
            edit(data) {
                this.$Api[this.$Methods.EDIT_MENU](data).then(() => {
                    this.dialogVisible = false;
                    this.$Config.MessageModal('success', '修改成功');
                    this.treeProp.config.defaultExpandedKeys = ["0", data.code];
                    this.getTreeList();
                });
            },
            del(label, data) {
                this.$Config.ConfirmModal('确定要删除菜单【' + label + '】吗?', () => {
                    this.$Api[this.$Methods.DEL_MENU](data).then(() => {
                        this.$Config.MessageModal('success', '删除成功');
                        this.treeProp.config.defaultExpandedKeys = ["0", this.parentId];
                        this.getTreeList();
                    });
                });
            },
            getTreeList() {
                this.$Api[this.$Methods.GET_MENU_LIST]().then(resp => {
                    resp.push(this.rootNode);
                    this.treeProp.treeData = this.buildTree(resp);
                });
            },
            buildNode(node) {
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
            buildTree(data) {
                let treeArray = [];
                let that_ = this;
                data.forEach(function (item) {
                    treeArray.push(that_.buildNode(item));
                });
                let map = {};
                treeArray.forEach(function (item) {
                    delete item.children;
                    map[item.code] = item;
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
