@/*
    名称查询条件标签的参数说明:

    name : 查询条件的名称
    id : 查询内容的textarea框id
@*/
<div class="input-group">
    <div class="input-group-btn">
        <button data-toggle="dropdown" style="border: 0px;width: 100px;text-align: left" class="btn btn-white dropdown-toggle"
                type="button">${name}:
        </button>
    </div>
    <textarea  style="height: 100px;width: 275px" class="form-control" id="${id}" placeholder="${placeholder!}" ></textarea>
</div>