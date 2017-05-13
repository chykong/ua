package com.critc.util.controller;

import com.critc.config.interceptor.Message;
import com.critc.util.json.JsonUtil;
import com.critc.util.string.StringUtil;
import com.critc.util.web.WebUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 基础Controller，用于权限设置
 *
 * @author 孔垂云
 */
public class BaseController {
    private static Logger logger = LoggerFactory.getLogger("controllerLog");

    @ExceptionHandler(Exception.class)
    public ModelAndView exception(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        ex.printStackTrace();
        logger.error(ex.getMessage());
        //判断是否是Ajax请求
        boolean isAjaxRequest = StringUtil.checkAjaxRequest(request);// this.isAjaxRequest(request);
        //获取异常的详细信息
        if (isAjaxRequest) {
            Message msg = new Message();// MessageManager.exception(ex);
            //Ajax请求处理
            msg.setSuccess(false);
            msg.setIsException(true);
            msg.setMessage(ex.getMessage());
            msg.setExName("错误提示");
            msg.setMsgText(ex.getMessage());
            WebUtil.out(response, JsonUtil.toStr(msg));
            return null;
        } else {
            //URL请求处理
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("message", ex.getMessage());
            map.put("isError", true);
            map.put("exceptionName", ex.getMessage());
            return new ModelAndView("exception", map);
        }
    }

    /**
     * 设置当前显示的按钮
     *
     * @param request
     * @param moduleCode
     */
    protected void setBtnAutho(HttpServletRequest request, String moduleCode) {
//        request.setAttribute("activeMenuUrl", request.getRequestURL().toString());
    }
}
