<template>
    <div id="app">
        <router-view name="blank"></router-view>
        <router-view></router-view>
    </div>
</template>

<script>
    export default {
        name: 'app',
    }
</script>
 
<style lang="less">
    #app {
        font-size: 12px;
        background-color: #222d32;
    }

</style>
