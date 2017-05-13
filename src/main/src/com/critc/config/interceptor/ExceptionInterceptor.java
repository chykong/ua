package com.critc.config.interceptor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.critc.util.json.JsonUtil;
import com.critc.util.string.StringUtil;
import com.critc.util.web.WebUtil;

public class ExceptionInterceptor {
	/** 
	 * 公共异常处理，URL请求返回异常页面，Ajax请求返回json数据 
	 * @param request HttpServletRequest 
	 * @param response HttpServletResponse 
	 * @param ex Exception 
	 * @return 
	 */
	@ExceptionHandler(Exception.class)
	public ModelAndView exception(HttpServletRequest request, HttpServletResponse response, Exception ex) {
	//	ex.printStackTrace();
		//判断是否是Ajax请求 
		boolean isAjaxRequest = StringUtil.checkAjaxRequest(request);// this.isAjaxRequest(request);
		//获取异常的详细信息
		Message msg = new Message();// MessageManager.exception(ex);
		if (isAjaxRequest) {
			//Ajax请求处理
			WebUtil.out(response, JsonUtil.toStr(msg));
			return null;
		} else {
			//URL请求处理  
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("message", msg.getMessage());
			map.put("isError", true);
			map.put("exceptionName", msg.getExName());
			return new ModelAndView("exception", map);
		}
	}
}
