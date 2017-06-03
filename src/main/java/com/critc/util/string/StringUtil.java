package com.critc.util.string;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 用户字符串操作
 *
 * @author 孔垂云
 */
public class StringUtil {




    /**
     * 把前台传过来的含中文的url字符串转换成标准中文，比如%25E5%258C%2597%25E4%25BA%25AC转换成北京
     */
    public static String decodeUrl(String url) {
        if (url == null)
            return "";
        String str = "";
        try {
            str = URLDecoder.decode(url, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return str;
    }

    /**
     * 把比如北京转换成%25E5%258C%2597%25E4%25BA%25AC
     */
    public static String encodeUrl(String url) {
        if (url == null)
            return "";
        String str = "";
        try {
            str = URLEncoder.encode(url, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return str;
    }

    /**
     * 取字符除最后一位的子串，比如 aaa,bbb, 返回aaa,bbb
     *
     * @param str
     * @return
     */
    public static String subTract(String str) {
        if (str.length() == 0)
            return "";
        else
            return str.substring(0, str.length() - 1);
    }

    /**
     * 判断字符串是null或空
     *
     * @param str
     * @return
     */
    public static boolean isNullOrEmpty(String str) {
        if (str == null || str.equals(""))
            return true;
        else
            return false;
    }

    /**
     * 把字符串里面的\r\n替换掉，json处理
     *
     * @param str
     * @return
     */
    public static String dealJsonFormat(String str) {
        str = str.replace("\r", "");
        str = str.replace("\n", "");
        return str;
    }

    /**
     * 把字符串里面的"-"和空格" "替换掉，并截取年月日成八位数日期字符串（18点日期格式），日期处理
     *
     * @param str
     * @return
     */
    public static String dealDateFormat(String str) {
        str = str.replace("-", "");
        str = str.replace(" ", "");
        str = str.substring(0, 8);
        return str;
    }

    /**
     * 判断字符串是null或空
     *
     * @param str
     * @return
     */
    public static boolean isNotNullOrEmpty(String str) {
        if (str != null && !str.equals(""))
            return true;
        else
            return false;
    }

    /**
     * 如果为null不trim
     *
     * @param str
     * @return
     */
    public static String isNullNoTrim(String str) {
        if (!(str == null)) {
            return str.trim();
        } else {
            return str;
        }
    }


    /**
     * 判断是否是ajax请求
     *
     * @param request
     * @return
     */
    public static boolean checkAjaxRequest(HttpServletRequest request) {
        String requestType = request.getHeader("X-Requested-With");
        if (requestType != null && requestType.equalsIgnoreCase("XMLHttpRequest")) {
            return true;
        } else
            return false;
    }

    /**
     * 把数字补零，比如传入T001，需要处理成T002，如果超出最大长度，返回"";
     *
     * @param pre       代码前缀
     * @param code      已经存在的最大代码
     * @param numLength 数字长度
     * @return
     */
    public static String addZero(String pre, String code, int numLength) {
        String str = "";
        if (StringUtil.isNullOrEmpty(code)) {
            str = pre;
            for (int i = 0; i < numLength - 1; i++) {
                str += "0";
            }
            str += "1";
        } else {
            str = pre;
            int num = Integer.parseInt(code.substring(pre.length(), code.length())) + 1;
            for (int i = 0; i < numLength - String.valueOf(num).length(); i++) {
                str += "0";
            }
            str += num;
        }
        if (str.length() > pre.length() + numLength)
            return "";
        else
            return str;
    }

    /**
     * 获取ip地址
     *
     * @param request
     * @return
     */
    public static String getIp(HttpServletRequest request) {
        String szClientIP = request.getHeader("x-forwarded-for");
        if (szClientIP == null || szClientIP.length() == 0 || "unknown".equalsIgnoreCase(szClientIP))
            szClientIP = request.getHeader("Proxy-Client-IP");
        if (szClientIP == null || szClientIP.length() == 0 || "unknown".equalsIgnoreCase(szClientIP))
            szClientIP = request.getHeader("WL-Proxy-Client-IP");
        if (szClientIP == null || szClientIP.length() == 0 || "unknown".equalsIgnoreCase(szClientIP))
            szClientIP = request.getRemoteAddr();
        return szClientIP;
    }


    /**
     * 把模糊查询里面的特殊字符转过滤一下
     *
     * @param str
     * @return
     */
    public static String filterSpecialCharacter(String str) {
        if (StringUtil.isNullOrEmpty(str))
            return "";
        else
            return str.replaceAll("'", "");
    }

    /**
     * 过滤表情
     *
     * @param source
     * @return
     */
    public static String filterEmoji(String source) {
        if (StringUtil.isNotNullOrEmpty(source)) {
            return source.replaceAll("[\\ud800\\udc00-\\udbff\\udfff\\ud800-\\udfff]", "*");
        } else {
            return source;
        }
    }

    /**
     * 获取url地址
     *
     * @param request 请求
     * @return url地址
     */
    public static String getUrlPath(HttpServletRequest request) {
        String url;
        if (request.getServerPort() == 80) {
            url = request.getScheme() + "://" + request.getServerName() + request.getRequestURI();
        } else {
            url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getRequestURI();
        }
        if (url.contains("#")) {
            url = url.substring(0, url.indexOf("#"));
        }
        return url;
    }


}
