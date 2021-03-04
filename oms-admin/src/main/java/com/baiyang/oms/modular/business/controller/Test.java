package com.baiyang.oms.modular.business.controller;

import com.alibaba.fastjson.JSONObject;
import com.baiyang.oms.modular.business.util.Constants;
import com.baiyang.oms.modular.business.util.HttpUtil;
import com.baiyang.oms.modular.business.util.JavaWebToken;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Created by Administrator on 2018/7/18.
 */

public class Test {

    public static void main(String[] args) {
//        HashMap<String,Object> mapb = new LinkedHashMap<String, Object>();
//        Date date = new Date();
//        Long dateiat = date.getTime()/1000;
//        Long dateexp = date.getTime()/1000+(2 * 60 * 60);
//        mapb.put("app_id", Constants.app_id);
//        mapb.put("iat",dateiat);
//        mapb.put("exp",dateexp);
//        mapb.put("jti","session_id");
//        String token = JavaWebToken.createJavaWebToken(Constants.secret,mapb);
//        JSONObject json = new JSONObject();
//        json.put("ids", "G20180516100750538846928");
//        json.put("page", "1");
//        json.put("pageSize", "500");
////		json.put("startTime", "2018-04-16 08:00:00");
////		json.put("endTime", "2018-04-16 18:00:00");
//        String message = HttpUtil.sendPost("https://sapi.baiyangwang.com/erp/getOrderList?yxtoken="+token, json.toJSONString());
//        System.out.println("===================================message:"+message);
//        System.out.println("===================================token:"+token);
    	
    	for(int i=0;i<100;i++){
    		for(int j=0 ;j<10;j++){
    			if(j==2){
    				
    				break;
    			}
    			System.out.println("j=="+j);
    		}
    		if(i==2){
    			continue;
    		}
    		System.out.println("i=="+i);
    	}
    	
    }
}
