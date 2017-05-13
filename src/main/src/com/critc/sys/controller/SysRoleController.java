package com.critc.sys.controller;

import com.critc.sys.model.SysFunction;
import com.critc.sys.model.SysModule;
import com.critc.sys.model.SysRole;
import com.critc.sys.service.SysFunctionService;
import com.critc.sys.service.SysModuleService;
import com.critc.sys.service.SysRoleService;
import com.critc.util.backurl.BackUrlUtil;
import com.critc.util.config.PubConfig;
import com.critc.util.controller.BaseController;
import com.critc.util.json.JsonUtil;
import com.critc.util.session.SessionUtil;
import com.critc.util.session.UserSession;
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
 * 角色管理
 *
 * @author 孔垂云
 */
@RequestMapping("/sys/role")
@Controller
public class SysRoleController extends BaseController {
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private PubConfig pubConfig;
    @Autowired
    private SysModuleService sysModuleService;
    @Autowired
    private SysFunctionService sysFunctionService;

    /**
     * 进入角色维护界面
     *
     * @return
     */
    @RequestMapping("/index")
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/sys/role");
        setBtnAutho(request, "SysRole");
        UserSession userSession = SessionUtil.getUserSession(request);
        List<SysRole> list = sysRoleService.list();
        mv.addObject("list", list);
        String url = "/sys/role/index.htm?";
        mv.addObject("backUrl", StringUtil.encodeUrl(url));
        return mv;
    }

    /**
     * 新增角色
     *
     * @param request
     * @param response
     * @param sysRole
     * @return
     */
    @RequestMapping("/toAdd")
    public ModelAndView toAdd(HttpServletRequest request, HttpServletResponse response, SysRole sysRole) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/sys/roleAdd");
        List<SysModule> listModule = sysModuleService.listByType(SessionUtil.getUserSession(request).getType());
        mv.addObject("listModule", listModule);
        List<SysFunction> listFunction = sysFunctionService.getAll();
        mv.addObject("listFunction", listFunction);
        BackUrlUtil.setBackUrl(mv, request);// 设置返回的url
        return mv;
    }

    /**
     * 修改模块
     *
     * @param request
     * @param response
     * @param id
     * @return
     */
    @RequestMapping("/toUpdate")
    public ModelAndView toUpdate(HttpServletRequest request, HttpServletResponse response, int id) {
        ModelAndView mv = new ModelAndView();
        SysRole sysRole = sysRoleService.get(id);
        mv.addObject("sysRole", sysRole);
        mv.setViewName("/sys/roleUpdate");
        List<SysModule> listModule = sysModuleService.listByType(SessionUtil.getUserSession(request).getType());
        mv.addObject("listModule", listModule);
        List<SysFunction> listFunction = sysFunctionService.getAll();
        mv.addObject("listFunction", listFunction);
        String checkButton = sysRoleService.checkModuleAndFunction(sysRole.getId());
        mv.addObject("checkButton", checkButton);
        BackUrlUtil.setBackUrl(mv, request);// 设置返回的url
        return mv;
    }

    @RequestMapping("/add")
    public String add(HttpServletRequest request, HttpServletResponse response, SysRole sysRole) {
        sysRole.setCreate_person(SessionUtil.getUserSession(request).getRealname());//创建人
        sysRole.setIs_delete(1);
        String moduleArr = WebUtil.getSafeStr(request.getParameter("moduleArr"));
        String functionArr = WebUtil.getSafeStr(request.getParameter("functionArr"));
        int flag = sysRoleService.add(sysRole, moduleArr, functionArr);
        if (flag == 0)
            return "forward:/error.htm?msg=" + StringUtil.encodeUrl("角色新增失败");
        else
            return "forward:/success.htm?msg=" + StringUtil.encodeUrl("角色新增成功");
    }

    @RequestMapping("/update")
    public String update(HttpServletRequest request, HttpServletResponse response, SysRole sysRole) {
        String moduleArr = WebUtil.getSafeStr(request.getParameter("moduleArr"));
        String functionArr = WebUtil.getSafeStr(request.getParameter("functionArr"));
        int flag = sysRoleService.update(sysRole, moduleArr, functionArr);
        if (flag == 0)
            return "forward:/error.htm?msg=" + StringUtil.encodeUrl("角色修改失败");
        else
            return "forward:/success.htm?msg=" + StringUtil.encodeUrl("角色修改成功");
    }

    @RequestMapping("/delete")
    public String delete(HttpServletRequest request, HttpServletResponse response, int id) {
        int flag = sysRoleService.delete(id);
        if (flag == 0)
            return "forward:/error.htm?msg=" + StringUtil.encodeUrl("角色删除失败");
        else
            return "forward:/success.htm?msg=" + StringUtil.encodeUrl("角色删除成功");
    }

    /**
     * 角色 grid列表
     *
     * @param request
     * @param response
     */
    @RequestMapping("/listJson")
    public void listJson(HttpServletRequest request, HttpServletResponse response) {
        UserSession userSession = SessionUtil.getUserSession(request);
        String json = JsonUtil.toStr(sysRoleService.list());
        WebUtil.out(response, json);
    }

    /**
     * 角色 对应的模块列表，checkbox tree
     *
     * @param request
     * @param response
     */
    @RequestMapping("/listRoleModuleJson")
    public void listRoleModuleJson(HttpServletRequest request, HttpServletResponse response) {
        int role_id = WebUtil.getSafeInt(request.getParameter("role_id"));
        WebUtil.out(response, sysRoleService.listRoleModuleJson(role_id));
    }

    /**
     * 修改角色对应的模块
     *
     * @param request
     * @param response
     */
    @RequestMapping("/updateRoleModule")
    public void updateRoleModule(HttpServletRequest request, HttpServletResponse response, int role_id) {
        String moduleList = WebUtil.getSafeStr(request.getParameter("moduleList"));
        String functionList = WebUtil.getSafeStr(request.getParameter("functionList"));
        int flag = sysRoleService.updateRoleModule(role_id, moduleList, functionList);
        if (flag == 0)
            WebUtil.out(response, JsonUtil.createOperaStr(false, "设置失败"));
        else if (flag == 1)
            WebUtil.out(response, JsonUtil.createOperaStr(true, "设置成功"));
    }
}
