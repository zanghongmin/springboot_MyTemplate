package top.zang.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;


/**
 * 全局统一json格式化类
 */
public class MyJsonUtil {
    private final static JSONObject jsonObject = new JSONObject();
    public static JSONObject getInstance() {
        return jsonObject;
    }

    public static String toJSONString(Object obj) {
		return JSON.toJSONString(obj);
    }

	public static <T> T parseObject(String jsonObject,Class<T> clazz) {
		return JSON.parseObject(jsonObject, clazz);
	}

}
