package com.critc.util.web;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.critc.util.json.JsonUtil;

/**
 * 字符串操作，用于保存和Web输入输出有关的方法
 *
 * @author chykong
 */
public class WebUtil {

    /**
     * Servlet打印字符串
     *
     * @param response
     * @param str
     */
    public static void out(HttpServletResponse response, String str) {
        try {
            response.setContentType("text/html; charset=UTF-8");
            response.getWriter().println(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 输出application/json字符串
     * @param response 响应
     * @param obj 需要转换为String的对象
     */
    public static void outJson(HttpServletResponse response, Object obj) {
        try {
            response.setContentType("application/json; charset=UTF-8");
            response.getWriter().println(JsonUtil.toStr(obj));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 返回操作成功与否的json串，并传向前台
     *
     * @param response
     * @param flag
     */
    public static void outOpera(HttpServletResponse response, int flag) {
        String operaStr = "";
        if (flag == -1)
            operaStr = JsonUtil.createOperaStr(false, "编号超出最大值");
        else if (flag == 0)
            operaStr = JsonUtil.createOperaStr(false, "操作失败");
        else if (flag == 1)
            operaStr = JsonUtil.createOperaStr(true, "操作成功");
        else if (flag == 2)
            operaStr = JsonUtil.createOperaStr(false, "代码已存在!");
        else if (flag == 9)
            operaStr = JsonUtil.createOperaStr(false, "提交后不允许修改和删除!");
        WebUtil.out(response, operaStr);
    }

    /**
     * 根据字符串转换，如果为null，则变成""
     *
     * @param strName
     * @return
     */
    public static String getSafeStr(Object obj) {
        return obj == null ? "" : String.valueOf(obj);
    }

    /**
     * 根据字符串转换，如果为null，则变成defaultStr
     *
     * @param strName
     * @return
     */
    public static String getSafeStr(Object obj, String strDefault) {
        return obj == null ? strDefault : String.valueOf(obj);
    }

    /**
     * 根据字符串转换，如果为null，则变成0
     *
     * @param strName
     * @return
     */
    public static int getSafeInt(Object obj) {
        return obj == null || obj.toString().equals("") ? 0 : Integer.parseInt(String.valueOf(obj));
    }

    /**
     * 根据字符串转换，如果为null，则变成defaultInt
     *
     * @param strName
     * @return
     */
    public static int getSafeInt(Object obj, int nDefualt) {
        return obj == null || obj.toString().equals("") ? nDefualt : Integer.parseInt(String.valueOf(obj));
    }

    /**
     * 根据字符串转换，如果为null，则变成0
     *
     * @param strName
     * @return
     */
    public static double getSafeDouble(Object obj) {
        return obj == null ? 0 : Double.parseDouble(String.valueOf(obj));
    }

    /**
     * 根据字符串转换，如果为null，则变成defaultDouble
     *
     * @param strName
     * @return
     */
    public static double getSafeDouble(Object obj, double nDefualt) {
        return obj == null ? 0 : Double.parseDouble(String.valueOf(obj));
    }

    /**
     * 根据字符串转换，如果为null，则变成0
     *
     * @param strName
     * @return
     */
    public static float getSafeFloat(Object obj) {
        return obj == null ? 0 : Float.parseFloat(String.valueOf(obj));
    }

    /**
     * 根据字符串转换，如果为null，则变成defaultDouble
     *
     * @param strName
     * @return
     */
    public static float getSafeFloat(Object obj, float nDefualt) {
        return obj == null ? 0 : Float.parseFloat(String.valueOf(obj));
    }

}
