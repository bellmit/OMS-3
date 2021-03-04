<template>
    <div class="tree-details" @click="rowClick(detailProp.data)">
        <el-row type="flex">
            <el-col style="flex: 1">
                 <span :class="labelClassName">
                  【{{ detailProp.data.data.label }}】
                 </span>
            </el-col>
            <el-col class="operationRow" v-if="detailProp.showOperation">
                <!--<el-tooltip v-for="(item,index) in defaultButtons" v-if="item.show" :key="index"-->
                <!--class="item" effect="dark" :content="item.text" placement="right">-->
                <el-button v-for="(item,index) in defaultButtons"
                           v-if="item.show"
                           :key="index"
                           @click="rowHandle(item.method,detailProp.data,$event)"
                           :type="item.type" :icon="item.icon"
                           :circle="item.circle"
                           :size="item.size"></el-button>
                <!--</el-tooltip>-->
            </el-col>
        </el-row>
    </div>
</template>
<script>

    export default {
        name: 'TreeDetail',
        props: {
            detailProp: {
                icon: {
                    type: String,
                    required: true
                },
                showOperation: {
                    type: Boolean,
                    required: true
                },
                showDistributeMenu: {
                    type: Boolean,
                    required: false
                },
                data: {
                    type: Object,
                    required: true
                },
            },
        },
        data() {
            return {
                defaultButtons: [{
                    type: 'success',
                    size: 'mini',
                    icon: 'oms-icon icon-jiahao',
                    circle: true,
                    text: '新增',
                    method: 'add',
                    show: true
                }, {
                    type: 'primary',
                    size: 'mini',
                    icon: 'oms-icon icon-edit',
                    circle: true,
                    text: '编辑',
                    method: 'edit',
                    show: true
                }, {
                    type: 'danger',
                    size: 'mini',
                    icon: 'oms-icon icon-delete',
                    circle: true,
                    text: '删除',
                    method: 'del',
                    show: true
                }, {
                    type: 'warning',
                    size: 'mini',
                    icon: 'oms-icon icon-quanxianfenpei',
                    circle: true,
                    text: '权限分配',
                    method: 'deliver',
                    show: true
                }],
                labelClassName: 'node-title',
            }
        },
        methods: {
            rowHandle(method, node, e) {
                switch (method) {
                    case 'add':
                        this.add(node, e);
                        break;
                    case 'edit':
                        this.edit(node, e);
                        break;
                    case 'del':
                        this.del(node, e);
                        break;
                    case 'deliver':
                        this.deliverMenu(node, e);
                }
            },
            deliverMenu(node, e) {
                this.$emit('deliver-category', node.data, node);
                e.cancelBubble = true;
            },
            rowClick(node) {
                this.$emit('row-click', node);
            },
            del(node, e) {
                this.$emit('delete-category', node.data, node);
                e.cancelBubble = true;
            },
            add(node, e) {
                this.$emit('add-category', node.data, node);
                e.cancelBubble = true;
            },
            edit(node, e) {
                this.$emit('edit-category', node.data, node);
                e.cancelBubble = true;
            },
        },
        created() {
            if (this.detailProp.showDistributeMenu === null) {
                this.defaultButtons.forEach(item => {
                    if (item.method === 'deliver') {
                        item.show = false;
                    }
                });
            } else {
                this.defaultButtons.forEach(item => {
                    if (item.method === 'deliver') {
                        item.show = this.detailProp.showDistributeMenu;
                    }
                });
            }

            if (this.detailProp.showOperation == null) {
                this.detailProp.showOperation = true;
            }
            if (this.detailProp.icon) {
                if (this.detailProp.icon.indexOf(',') > 0) {
                    let iconArray = this.detailProp.icon.split(',');
                    if (this.detailProp.data.childNodes.length > 0) {
                        this.labelClassName = 'node-title ' + iconArray[0];
                    } else {
                        this.labelClassName = 'node-title ' + iconArray[1];
                    }
                } else {
                    this.labelClassName = 'node-title ' + this.detailProp.icon;
                }
            }
        }
    }
</script>
<style lang="less">
    .tree-details {
        flex: 1;
    }

    .operationRow {
        width: 150px;
        padding-left: 10px;
    }

    .node-title {
        color: #3c8dbc;
        font-size: 12px;
        line-height: 28px;
        font-weight: 800
    }

    .el-button + .el-button {
        margin-left: 5px !important;
    }

</style>