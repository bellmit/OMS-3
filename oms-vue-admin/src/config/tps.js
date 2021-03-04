import {Notification, Message, MessageBox} from 'element-ui'

export default {
    NotificationModal(type, title, msg, duration) {
        Notification[type]({
            title: title,
            message: msg,
            duration: duration
        });
    },
    MessageModal(type, msg) {
        Message[type]({
            message: msg
        })
    },
    ConfirmModal(msg, callSuccess, callCancel) {
        MessageBox.confirm(msg, '系统提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
        }).then(() => {
            callSuccess();
        }).catch(() => {
            if (callCancel) {
                callCancel();
            }
        })
    },
    hasSelectRows(selects) {
        if (selects.length < 1) {
            this.MessageModal('warning', '请先选择需要操作的列！');
            return false;
        } else {
            return true;
        }
    },
    compareArray(prop) {
        return function (obj1, obj2) {
            let val1 = obj1[prop];
            let val2 = obj2[prop];
            if (!isNaN(Number(val1)) && !isNaN(Number(val2))) {
                val1 = Number(val1);
                val2 = Number(val2);
            }
            if (val1 < val2) {
                return -1;
            } else if (val1 > val2) {
                return 1;
            } else {
                return 0;
            }
        }
    }, buildSelectOptions(labelKey, valueKey, array) {
        let options = [{label: '请选择', value: ''}];
        array.forEach(item => {
            options.push({
                label: item[labelKey],
                value: item[valueKey]
            });
        });
        return options;
    },
};