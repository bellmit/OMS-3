<template>
    <div>
        <div style="margin-top: 10px">
            <v-tree :treeProp="treeProp" ref="tree"
            ></v-tree>
        </div>
        <div style="text-align: center">
            <el-button type="primary" size="small" @click="submitBtnClick">提交</el-button>
            <el-button type="default" size="small" @click="closeDialogModal">关闭</el-button>
        </div>

    </div>
</template>

<script>
    import VTree from '~/components/VTree'

    export default {
        props: {
            closeDialogModal: {
                type: Function,
                default: null
            },
            roleId: {
                type: String,
                required: true,
            }
        },
        components: {
            VTree
        },
        data() {
            return {
                keyArray: [],
                treeProp: {
                    treeData: [],
                    filterText: '',
                    nodeKey: 'id',
                    icon: 'oms-icon icon-zidian,oms-icon icon-zidian1',
                    showOperation: false,
                    config: {
                        checkStrictly: true,
                        showCheckbox: true,
                        defaultExpandedKeys: ['0'],
                        defaultCheckedKeys: [],
                    }
                },
                treeMap: {},
                parentCode: '',
                nodeData: '',
            }
        },
        methods: {
            submitBtnClick() {
                let nodeArray = this.$refs.tree.getCheckedNodes();
                let menuIdArray = [];
                nodeArray.forEach(item => {
                    menuIdArray.push(item.id);
                });
                this.$Api[this.$Methods.SET_ROLE_AUTHORITY]({
                    ids: menuIdArray.join(','),
                    roleId: this.roleId
                }).then(() => {
                    this.$Config.MessageModal('success', '权限分配成功');
                    this.closeDialogModal();
                });
            },
            getTreeList() {
                this.$Api[this.$Methods.MENU_TREE_LIST_BY_ROLE_ID](this.roleId).then(resp => {
                    let checkedArray = [];
                    let openedArray = [];
                    resp.forEach(item => {
                        if (item.checked === true) {
                            checkedArray.push(item.id);
                        }
                        if (item.open === true) {
                            openedArray.push(item.id);
                        }
                    });
                    this.treeProp.config.defaultCheckedKeys = checkedArray;
                    this.treeProp.config.defaultExpandedKeys = this.treeProp.config.defaultExpandedKeys.concat(openedArray);
                    this.treeProp.treeData = this.buildTree(resp);
                });
            },
            buildNode(node) {
                return {
                    id: node.id,
                    label: node.name,
                    value: node.pId,
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
        }, created() {
            this.getTreeList();
        }, watch: {
            roleId() {
                if (this.roleId !== '') {
                    this.getTreeList();
                }
            }
        }
    }
</script>
<style lang="less">

</style>
