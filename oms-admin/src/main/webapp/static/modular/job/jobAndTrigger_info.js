/**
 * 初始化任务管理详情对话框
 */
var JobAndTriggerInfoDlg = {
    jobAndTriggerInfoData : {},
	validateFields: {
		cron: {
	        validators: {
	            notEmpty: {
	                message: 'cron表达式不能为空'
	            }
	        }
	    },
		clazz: {
	        validators: {
	            notEmpty: {
	                message: '任务完整类名不能为空'
	            }
	        }
	    },
		jobName: {
	        validators: {
	            notEmpty: {
	                message: '任务名称不能为空'
	            }
	        }
	    },
		jobGroupName: {
	        validators: {
	            notEmpty: {
	                message: '任务组名称不能为空'
	            }
	        }
	    },
		triggerName: {
	        validators: {
	            notEmpty: {
	                message: '触发器名称不能为空'
	            }
	        }
	    },
		triggerGroupName: {
	        validators: {
	            notEmpty: {
	                message: '触发器组名称不能为空'
	            }
	        }
	    }
	    
	}
};

/**
 * 清除数据
 */
JobAndTriggerInfoDlg.clearData = function() {
    this.jobAndTriggerInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
JobAndTriggerInfoDlg.set = function(key, val) {
    this.jobAndTriggerInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
JobAndTriggerInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
JobAndTriggerInfoDlg.close = function() {
    parent.layer.close(window.parent.JobAndTrigger.layerIndex);
}

/**
 * 收集数据---------------此处必须设置 相关字段否则 不能进行   提交
 * 编辑时，隐藏字段虽然已经进行了赋值，
 * 但是必须要通过在此设置 如：oldjobName oldjobGroup oldtriggerName oldtriggerGroup等字段
 */
JobAndTriggerInfoDlg.collectData = function() {
	this
    .set('cron')
    .set('jobName')
    .set('jobGroupName')
    .set('triggerName')
    .set('triggerGroupName')
    .set('clazz')
	.set('oldjobName')
	.set('oldjobGroup')
	.set('oldtriggerName')
	.set('oldtriggerGroup');


}

JobAndTriggerInfoDlg.validate = function () {
    $('#jobAndTriggerFormId').data("bootstrapValidator").resetForm();
    $('#jobAndTriggerFormId').bootstrapValidator('validate');
    return $("#jobAndTriggerFormId").data('bootstrapValidator').isValid();
};

/**
 * 提交添加
 */
JobAndTriggerInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();
    
    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/job/add", function(data){
        Feng.success("添加成功!");
        window.parent.JobAndTrigger.table.refresh();
        JobAndTriggerInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.jobAndTriggerInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
JobAndTriggerInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();
;
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/job/edit", function(data){
        Feng.success("修改成功!");
        window.parent.JobAndTrigger.table.refresh();
        JobAndTriggerInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.jobAndTriggerInfoData);
    ajax.start();
}

$(function() {
	Feng.initValidator("jobAndTriggerFormId", JobAndTriggerInfoDlg.validateFields);
});
