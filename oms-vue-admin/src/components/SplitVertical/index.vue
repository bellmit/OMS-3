<template>
    <div>
        <h4 class="row_h4">
            <span class="row_span_split"></span>{{title}}
        </h4>
    </div>
</template>

<script>
    export default {
        name: "splitVertical",
        props:{
            title:{
                type:String,
                required:true
            }
        }
    }
</script>

<style type="less" scoped>
    .row_h4 {
        padding: 0 10px 10px 10px ;

    }

    .row_span_split {
        line-height: 16px;
        height: 16px;
        margin-right: 4px;
        border-right: 4px #3C8DBC solid;
    }
</style>