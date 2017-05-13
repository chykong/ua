package com.critc.util.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日志工具类
 *
 * @author 孔垂云
 */
public class LogUtil {
    private static Logger sysLog = LoggerFactory.getLogger("sysLog");

    /**
     * 功能描述:记录系统日志
     *
     * @param str void
     * @version 1.0.0
     * @author 孔垂云
     */
    public static void infoSys(String str) {
        sysLog.info(str);
    }

    public static void setSysLog(Logger sysLog) {
        LogUtil.sysLog = sysLog;
    }

    public static void debug(String str) {
        sysLog.debug(str);
    }
}
