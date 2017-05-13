package com.critc.util.json;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * JSON工具类，进行对象转string和string转对象
 *
 * @author 孔垂云
 */
public class JsonUtil {
    private static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 把对象转成json串
     *
     * @param obj
     * @return
     */
    public static String toStr(Object obj) {
        String json_str = "";
        try {
            json_str = objectMapper.writer().writeValueAsString(obj);
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json_str;
    }

    /**
     * 把字符串转成List，统一保存时使用
     *
     * @param str
     * @return
     */
    @SuppressWarnings("unchecked")
    public static List<LinkedHashMap<String, Object>> toList(String str) {
        List<LinkedHashMap<String, Object>> list = null;
        try {
            list = objectMapper.readValue(str, List.class);// 把list对象转成LinkedhashMap,然后根据HashMap来取值
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * json转对象
     *
     * @param jsonStr
     * @param valueType
     * @return
     */
    public static <T> T toObject(String jsonStr, Class<T> valueType) {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
        }
        try {
            return objectMapper.readValue(jsonStr, valueType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 生成操作后的json串，{success:false,msgText:'删除失败'}
     *
     * @param b
     * @param msg
     * @return
     */
    public static String createOperaStr(boolean b, String msg) {
        return "{\"success\":" + b + ",\"msgText\":\"" + msg + "\"}";
    }


    /**
     * 创建Extjs分页json
     *
     * @param rows
     * @param data
     * @return
     */
    public static String createExtjsPageJson(long rows, String data) {
        String json = "{\"total\":\"" + rows + "\",\"rows\":" + data + "}";
        return json;
    }

    /**
     * 创建Extjs分页json
     *
     * @param rows
     * @param obj
     * @return
     */
    public static String createExtjsPageJson(long rows, Object obj) {
        String json = "{\"total\":\"" + rows + "\",\"root\":" + toStr(obj) + "}";
        return json;
    }

    /**
     * 生成bootgrid的分页json
     *
     * @param pageIndex
     * @param rows
     * @param data
     * @return
     */
    public static String createBootGridPageJson(int pageIndex, int rows, String data) {
        String json = "{\"current\":" + pageIndex + ",\"rowCount\":\"" + 10 + "\",\"rows\":" + data + ",\"total\":" + rows + "}";
        return json;
    }

    @SuppressWarnings("unchecked")
    public static HashMap<String, String> toHashmap(String str) {
        HashMap<String, String> hashMap = null;
        try {
            hashMap = objectMapper.readValue(str, HashMap.class);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return hashMap;
    }
}
