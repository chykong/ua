package com.critc.sys.controller;

import com.critc.job.service.DayCalculateService;
import com.critc.sys.model.SysConfig;
import com.critc.sys.service.SysConfigService;
import com.critc.util.controller.BaseController;
import com.critc.util.json.JsonUtil;
import com.critc.util.web.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 系统配置Controller
 * @author  孔垂云
 */
@Controller
@RequestMapping("/sys/config/")
public class SysConfigController extends BaseController {

	@Autowired
	private SysConfigService sysConfigService;
	@Autowired
	private DayCalculateService dayCalculateService;

	/**
	 * 进入界面
	 * 
	 * @return
	 */
	@RequestMapping("/index")
	public ModelAndView index(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/sys/config");
		setBtnAutho(request, "SysConfig");
		return mv;
	}

	/**
	 * 进入Test界面
	 * 
	 * @return
	 */
	@RequestMapping("/test")
	public ModelAndView test(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/test/test");
		//		setBtnAutho(request, "SysConfig");
		return mv;
	}

	/**
	 * 进入Test界面
	 * 
	 * @return
	 */
	@RequestMapping("/testDetail")
	public ModelAndView testDetail(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/test/testDetail");
		//		setBtnAutho(request, "SysConfig");
		return mv;
	}

	/**
	 * 新增
	 * @param request
	 * @param response
	 */
	@RequestMapping("/add")
	public void add(HttpServletRequest request, HttpServletResponse response, SysConfig SysConfig) {
		int flag = sysConfigService.add(SysConfig);
		WebUtil.outOpera(response, flag);
	}

	/**
	 * 修改
	 * @param request
	 * @param response
	 */
	@RequestMapping("/update")
	public void update(HttpServletRequest request, HttpServletResponse response, SysConfig SysConfig) {
		int flag = sysConfigService.update(SysConfig);
		WebUtil.outOpera(response, flag);
	}

	/**
	 * 删除
	 * @param request
	 * @param response
	 */
	@RequestMapping("/delete")
	public void delete(HttpServletRequest request, HttpServletResponse response, String syskey) {
		int flag = sysConfigService.delete(syskey);
		if (flag == 0)
			WebUtil.out(response, JsonUtil.createOperaStr(false, "删除失败"));
		else if (flag == 1)
			WebUtil.out(response, JsonUtil.createOperaStr(true, "删除成功"));
	}

	/**
	 *列表
	 * @param request
	 * @param response
	 */
	@RequestMapping("/search")
	public void search(HttpServletRequest request, HttpServletResponse response) {
		String json = JsonUtil.toStr(sysConfigService.list());
		WebUtil.out(response, json);
	}

}
