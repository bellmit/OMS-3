@/*
查询表单展开收起说明：
searchFormId : 查询表单的浮层id
@*/
<button type="button" class="btn btn-primary"
        style="float: right;padding: 2px 12px"
        aria-label="Left Align" search="${searchFormId}" onclick="toggleSlide(this)">
    收起
</button>
<script>
    function toggleSlide(obj) {
        if ($(obj)[0].innerText === "收起") {
            $(obj)[0].innerText = "展开"
        } else {
            $(obj)[0].innerText = "收起"
        }
        var searchFormId = $(obj)[0].attributes.search.value
        $("#" + searchFormId).slideToggle("slow");
    };
</script>