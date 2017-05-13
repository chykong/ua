package com.critc.sys.controller;

import com.critc.sys.model.SysLog;
import com.critc.sys.service.SysLogService;
import com.critc.sys.service.SysUserService;
import com.critc.sys.vo.SysLogSearchVO;
import com.critc.util.backurl.BackUrlUtil;
import com.critc.util.config.PubConfig;
import com.critc.util.controller.BaseController;
import com.critc.util.date.DateUtil;
import com.critc.util.global.GlobalConst;
import com.critc.util.page.PageNavigate;
import com.critc.util.string.StringUtil;
import com.critc.util.web.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 系统操作日志查询
 *
 * @author 孔垂云
 */
@Controller
@RequestMapping("/sys/log")
public class SysLogController extends BaseController {
    @Autowired
    private SysLogService sysLogService;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private PubConfig pubConfig;

    /**
     * 进入日志查看界面
     *
     * @return
     */
    @RequestMapping("/index")
    public ModelAndView index(HttpServletRequest request, SysLogSearchVO sysLogSearchVO) {
        setBtnAutho(request, "SysLog");
        if (StringUtil.isNullOrEmpty(sysLogSearchVO.getS_date()))
            sysLogSearchVO.setS_date(DateUtil.getOpeDate(DateUtil.getSystemDate(), -30));
        if (StringUtil.isNullOrEmpty(sysLogSearchVO.getE_date()))
            sysLogSearchVO.setE_date(DateUtil.getSystemDate());
        ModelAndView mv = new ModelAndView();
        int recordCount = sysLogService.listCount(sysLogSearchVO);// 获取查询总数
        int pageIndex = WebUtil.getSafeInt(request.getParameter("pageIndex"), 1);// 获取当前页数
        int pageSize = GlobalConst.pageSize;// 直接取全局变量，每页记录数
        String url = createUrl(sysLogSearchVO, pageIndex, pageSize);
        PageNavigate pageNavigate = new PageNavigate(url, pageIndex, pageSize, recordCount);//
        List<SysLog> list = sysLogService.list(sysLogSearchVO, pageNavigate.getPageIndex(), pageSize);
        mv.addObject("pageNavigate", pageNavigate);// 设置分页的变量
        mv.addObject("list", list);// 把获取的记录放到mv里面
        mv.addObject("listUser", sysUserService.listAll());// 用户列表
        mv.setViewName("/sys/log");// 跳转至指定页面
        BackUrlUtil.createBackUrl(mv, request, url);// 设置返回url
        return mv;
    }

    // 设置分页url，一般有查询条件的才会用到
    private String createUrl(SysLogSearchVO sysLogSearchVO, int pageIndex, int pageSize) {
        String url = pubConfig.getDynamicServer() + "/sys/log/index.htm?";
        if (sysLogSearchVO.getUser_id() != null)
            url += "&user_id=" + sysLogSearchVO.getUser_id();
        if (StringUtil.isNotNullOrEmpty(sysLogSearchVO.getS_date()))
            url += "&s_date=" + sysLogSearchVO.getS_date();
        if (StringUtil.isNotNullOrEmpty(sysLogSearchVO.getE_date()))
            url += "&e_date=" + sysLogSearchVO.getE_date();
        return url;
    }

    /**
     * 下载操作日志
     *
     * @param request
     * @param response
     */
    @RequestMapping("/downloadLog")
    public void downloadLog(HttpServletRequest request, HttpServletResponse response) {
        int user_id = WebUtil.getSafeInt(request.getParameter("user_id"));
        String s_date = WebUtil.getSafeStr(request.getParameter("s_date"));
        String e_date = WebUtil.getSafeStr(request.getParameter("e_date"));
        SysLogSearchVO sysLogSearchVO = new SysLogSearchVO();
        sysLogSearchVO.setUser_id(user_id);
        sysLogSearchVO.setS_date(s_date);
        sysLogSearchVO.setE_date(e_date);
        sysLogService.exportLog(request.getServletContext().getRealPath("/download"), sysLogSearchVO, request, response);
    }

    @RequestMapping("/systemLog")
    public ModelAndView systemLog(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/common/systemlog");
        return mv;
    }
}
