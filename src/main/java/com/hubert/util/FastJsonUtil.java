package com.hubert.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class FastJsonUtil {
	public static String bean2Json(Object obj){  
		return JSON.toJSONString(obj);
    }  
      
    public static JSONObject json2Bean(String jsonStr){  
        return JSON.parseObject(jsonStr);  
    }  
    
    public static <T> T json2BeanWithList(String jsonStr,Class<T> objClass){
    	return JSON.parseObject(jsonStr,objClass);
    }
}
