<template>
    <div>
        <el-tree style="margin-top: 10px"
                 ref="domTree"
                 :data="treeProp.treeData"
                 :node-key="treeProp.nodeKey"
                 highlight-current
                 accordion
                 :expand-on-click-node="treeProp.config.expandOnClickNode"
                 :default-expanded-keys="treeProp.config.defaultExpandedKeys"
                 :default-checked-keys="treeProp.config.defaultCheckedKeys"
                 :show-checkbox="treeProp.config.showCheckbox"
                 :check-strictly="treeProp.config.checkStrictly"
                 :check-on-click-node="treeProp.config.checkOnClickNode"
                 :filter-node-method="filterNode"
                 :render-content="renderContent"
        >
        </el-tree>
    </div>
</template>

<script>
    import TreeDetail from './TreeDetail'

    export default {
        name: "VTree",
        components: {TreeDetail},
        props: {
            treeProp: {
                filterText: {
                    type: String,
                    required: false,
                },
                icon: {
                    type: String,
                    required: false,
                },
                config: {
                    type: Object,
                    required: false,
                },
                nodeKey: {
                    type: String,
                    required: true
                },
                treeData: {
                    type: Array,
                    required: true
                },
                showOperation: {
                    type: Boolean,
                    required: true
                }, showDistributeMenu:{
                    type: Boolean,
                    required: false
                },
            }
        },
        data() {
            return {
                filterText: '',
            }
        },
        methods: {
            getCheckedNodes(){
               return this.$refs.domTree.getCheckedNodes();
            },
            setCurrentKey(key) {
                this.$refs.domTree.setCurrentKey(key);
            },
            filterNode(value, data) {
                if (!value) return true;
                return data.label.indexOf(value) !== -1;
            },
            rowClick(node) {
                this.$emit('rowClick', node);
            },
            deleteCategory(data, node) {
                this.$emit('deleteCategory', data, node);
            },
            addCategory(data, node) {
                this.$emit('addCategory', data, node);
            },
            editCategory(data, node) {
                this.$emit('editCategory', data, node);
            },
            deliverCategory(data, node) {
                this.$emit('deliverCategory', data, node);
            },
            renderContent(h, {node, data, store}) {
                return this.$createElement('TreeDetail', {
                    props: {
                        detailProp: {
                            showDistributeMenu:this.treeProp.showDistributeMenu,
                            data: node,
                            icon: this.treeProp.icon,
                            showOperation: this.treeProp.showOperation
                        }
                    }, on: {
                        'add-category': this.addCategory,
                        'edit-category': this.editCategory,
                        'delete-category': this.deleteCategory,
                        'row-click': this.rowClick,
                        'deliver-category': this.deliverCategory
                    }
                });
            }
        }, created() {
            if (!this.treeProp.config) {
                this.treeProp.config = {};
            }
            if (this.treeProp.config.defaultCheckedKeys == null) {
                this.treeProp.config.defaultCheckedKeys = [];
            }
            if (this.treeProp.config.showCheckbox == null) {
                this.treeProp.config.showCheckbox = false;
            }
            if (this.treeProp.config.checkStrictly == null) {
                this.treeProp.config.checkStrictly = false;
            }
            if (this.treeProp.config.checkOnClickNode == null) {
                this.treeProp.config.checkOnClickNode = false;
            }
            if (this.treeProp.config.expandOnClickNode == null) {
                this.treeProp.config.expandOnClickNode = true;
            }
        }, watch: {
            'treeProp.filterText'(val) {
                this.$refs.domTree.filter(val);
            }
        },
    }
</script>