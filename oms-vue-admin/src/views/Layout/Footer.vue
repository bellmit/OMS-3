<template>
  <div class="footer">
    <div class="version">
      <b>Version</b> {{ $Config.version }}
    </div>
    <strong class="c-strong">Copyright © 2018 <a href="javascript:;">{{ $Config.siteName }}</a></strong>
  </div>
</template>

<script>
  export default {
    data() {
      return {};
    },
    methods: {}
  }
</script>
<style lang="less">
  @import "./Footer";
</style>
