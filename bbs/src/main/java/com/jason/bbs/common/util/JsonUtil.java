package com.jason.bbs.common.util;

import java.util.List;

import com.jason.bbs.pojo.entity.Comment;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Created by jason on 2016/8/11.
 */
public class JsonUtil {

	private JsonUtil() {
	}

	public static Object jsonStrToObject(String jsonData, Class<?> objClass){
		
		if(jsonData==null||objClass==null){

             return null;
		}
		JSONObject jsonobj=JSONObject.fromObject(jsonData);

		return JSONObject.toBean(jsonobj, objClass);
	}
    public static String objectToJsonStr(Object obj){

    	if(obj==null){
    		return null;
		}

    	JSONObject jsonobj=JSONObject.fromObject(obj);

		String jsonStr=jsonobj.toString();
    	
		return jsonStr;
	}
    
    @SuppressWarnings("unchecked")
	public static <T> List<T> jsonStrToList(String jsonData,Class<T> objClass){

		if(jsonData==null||objClass==null){

			return null;
		}
    	
    	JSONArray jsonArray=JSONArray.fromObject(jsonData);

    	List<T> list=(List<T>)JSONArray.toCollection(jsonArray, objClass);
		
		return list;
	}
    
   public static <T> String listToJsonStr(List<T> list){

   	    if(null==list||list.size()==0){

   	    	return null;
		}
    	JSONArray jsonArray=JSONArray.fromObject(list);
    	
    	String jsonArrayStr=jsonArray.toString();
		
		return jsonArrayStr;
	}
}
