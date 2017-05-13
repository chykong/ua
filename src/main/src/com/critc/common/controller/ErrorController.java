package com.critc.common.controller;

import com.critc.util.string.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/")
public class ErrorController {

	@RequestMapping("error")
	public ModelAndView error(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("error");
		mv.addObject("msg", StringUtil.decodeUrl(request.getParameter("msg")));
		mv.addObject("backUrl", StringUtil.decodeUrl(request.getParameter("backUrl")));
		return mv;
	}
}
