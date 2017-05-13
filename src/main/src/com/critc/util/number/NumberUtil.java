package com.critc.util.number;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 孔垂云 on 2017/5/13.
 */
public class NumberUtil {
    /**
     * 格式化小数
     *
     * @param val
     * @param point 小数位
     * @return
     */
    public static String formatDouble(String val, int point) {
        String str = "";
        DecimalFormat nf = new DecimalFormat();
        nf.setMaximumFractionDigits(point);
        str = nf.format(Double.parseDouble(val));
        return str.replace(",", "");
    }

    /**
     * 格式化小数
     *
     * @param val
     * @param point 小数位
     * @return
     */
    public static double formatDouble(double val, int point) {
        String str = "";
        DecimalFormat nf = new DecimalFormat();
        nf.setMaximumFractionDigits(point);
        str = nf.format(val);
        return Double.parseDouble(str.replace(",", ""));
    }

    /**
     * 格式化两位小数
     *
     * @param val
     * @return
     */
    public static String formatDouble(String val) {
        String str = "";
        DecimalFormat nf = new DecimalFormat();
        nf.setMaximumFractionDigits(2);
        str = nf.format(Double.parseDouble(val));
        return str.replace(",", "");
    }

    /**
     * 格式化两位小数
     *
     * @param val
     * @return
     */
    public static double formatDouble(double val) {
        String str = "";
        DecimalFormat nf = new DecimalFormat();
        nf.setMaximumFractionDigits(2);
        str = nf.format(val);
        return Double.parseDouble(str.replace(",", ""));
    }

    /**
     * 格式化两位小数
     *
     * @param val
     * @return
     */
    public static float formatFloat(float val) {
        String str = "";
        DecimalFormat nf = new DecimalFormat();
        nf.setMaximumFractionDigits(2);
        str = nf.format(val);
        return Float.parseFloat(str.replace(",", ""));
    }

    /**
     * 格式化两位小数
     *
     * @param val
     * @return
     */
    public static float formatFloat(String val) {
        String str = "";
        DecimalFormat nf = new DecimalFormat();
        nf.setMaximumFractionDigits(2);
        str = nf.format(Float.parseFloat(val));
        return Float.parseFloat(str.replace(",", ""));
    }

    /**
     * 格式化一位小数
     *
     * @param val
     * @return
     */
    public static String formatFloat1(float val) {
        String str = "";
        DecimalFormat nf = new DecimalFormat();
        nf.setMaximumFractionDigits(1);
        str = nf.format(val);
        return str.replace(",", "");
    }

    /**
     * 格式化金钱
     *
     * @param val
     * @return
     */
    public static String formatAmount(double val) {
        NumberFormat nf = new DecimalFormat("#,###.##");
        String str = nf.format(val);
        return str;
    }

    /**
     * 元转换成分
     *
     * @param amount
     * @return
     */
    public static int getMoney(float amount) {
        Float f = new Float(Math.round(amount * 100));
        int fen = f.intValue();
        return fen;
    }

    /**
     * 十六进制字符串转数字
     *
     * @param str
     * @return
     */
    public static int hexTrans(String str) {
        return Integer.parseInt(str.replaceAll("^0[x|X]", ""), 16);
    }

    /**
     * 判断是否是整数
     *
     * @param integer
     * @return
     */
    public static boolean isInteger(String integer) {
        Pattern p = Pattern.compile("\\d*");
        Matcher m = p.matcher(integer);
        boolean b = m.matches();
        return b;
    }

    /**
     * 判断是否是正整数
     *
     * @param integer
     * @return
     */
    public static boolean isInteger2(String integer) {
        Pattern p = Pattern.compile("^[0-9]*[1-9][0-9]*$");
        Matcher m = p.matcher(integer);
        boolean b = m.matches();
        return b;
    }
}
