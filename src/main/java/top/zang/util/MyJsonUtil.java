package top.zang.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


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
