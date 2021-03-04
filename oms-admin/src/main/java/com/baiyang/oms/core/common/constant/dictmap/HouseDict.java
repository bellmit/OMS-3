package com.baiyang.oms.core.common.constant.dictmap;

import com.baiyang.oms.core.common.constant.dictmap.base.AbstractDictMap;

/**
 *仓库的字典
 *
 * @author fengshuonan
 * @date 2017-05-06 15:01
 */
public class HouseDict extends AbstractDictMap {

    @Override
    public void init() {
    	put("name","仓库里");
    	put("sex","性别");
        
    }

    @Override
    protected void initBeWrapped() {
//    	putFieldWrapperMethodName("sex","getSexName");
    }
}
