package com.critc.sys.controller;

import com.critc.sys.model.SysUser;
import com.critc.sys.model.SysUserLogin;
import com.critc.sys.service.SysRoleService;
import com.critc.sys.service.SysUserLoginService;
import com.critc.sys.service.SysUserService;
import com.critc.sys.vo.SysUserSearchVO;
import com.critc.util.backurl.BackUrlUtil;
import com.critc.util.config.PubConfig;
import com.critc.util.controller.BaseController;
import com.critc.util.json.JsonUtil;
import com.critc.util.page.PageNavigate;
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
import java.util.Date;
import java.util.List;

/**
 * 系统用户管理
 *
 * @author 孔垂云
 */
@RequestMapping("/sys/user")
@Controller
public class SysUserController extends BaseController {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysUserLoginService sysUserLoginService;
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private PubConfig pubConfig;

    /**
     * 进入用户管理界面
     *
     * @return
     */
    @RequestMapping("/index")
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response, SysUserSearchVO sysUserSearchVO) {
        setBtnAutho(request, "SysUser");//设置界面权限
//        if (StringUtil.isNotNullOrEmpty(sysUserSearchVO.getRealname()))//如果为模糊查询或者中文，一定要decode
//            sysUserSearchVO.setRealname(StringUtil.decodeUrl(sysUserSearchVO.getRealname()));
        ModelAndView mv = new ModelAndView();
        int recordCount = sysUserService.listCount(sysUserSearchVO);// 获取查询总数
        String url = createUrl(sysUserSearchVO);
        PageNavigate pageNavigate = new PageNavigate(url, sysUserSearchVO.getPageIndex(), sysUserSearchVO.getPageSize(), recordCount);//定义分页对象
        List<SysUser> list = sysUserService.list(sysUserSearchVO);
        mv.addObject("pageNavigate", pageNavigate);// 设置分页的变量
        mv.addObject("list", list);// 把获取的记录放到mv里面
        mv.addObject("listRole", sysRoleService.list());// 角色列表
        mv.setViewName("/sys/user");// 跳转至指定页面
        BackUrlUtil.createBackUrl(mv, request, url);// 设置返回url
        return mv;
    }

    // 设置分页url，一般有查询条件的才会用到
    private String createUrl(SysUserSearchVO sysUserSearchVO) {
        String url = "/sys/user/index.htm?";
        if (StringUtil.isNotNullOrEmpty(sysUserSearchVO.getUsername()))
            url += "&username=" + sysUserSearchVO.getUsername();
        if (StringUtil.isNotNullOrEmpty(sysUserSearchVO.getRealname()))//如果为模糊查询，要把该字段encode
            url += "&realname=" + StringUtil.encodeUrl(sysUserSearchVO.getRealname());
        if (sysUserSearchVO.getStatus() != null)
            url += "&status=" + sysUserSearchVO.getStatus();
        if (sysUserSearchVO.getRole_id() != null)
            url += "&role_id=" + sysUserSearchVO.getRole_id();
        return url;
    }

    /**
     * 进入添加用户界面
     *
     * @param request
     * @param response
     * @param
     * @retursysUsern
     */
    @RequestMapping("/toAdd")
    public ModelAndView toAdd(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        UserSession userSession = SessionUtil.getUserSession(request);
        mv.addObject("listRole", sysRoleService.list());// 角色列表
        SysUser sysUser = new SysUser();
        mv.addObject("sysUser", sysUser);
        mv.setViewName("/sys/userAdd");
        BackUrlUtil.setBackUrl(mv, request);// 设置返回的url
        return mv;
    }

    /**
     * 进入修改用户界面
     *
     * @param request
     * @param response
     * @param
     * @return
     */
    @RequestMapping("/toUpdate")
    public ModelAndView toUpdate(HttpServletRequest request, HttpServletResponse response, int id) {
        ModelAndView mv = new ModelAndView();
        SysUser sysUser = sysUserService.get(id);
        mv.addObject("sysUser", sysUser);
        UserSession userSession = SessionUtil.getUserSession(request);
        mv.addObject("listRole", sysRoleService.list());// 角色列表
        mv.setViewName("/sys/userUpdate");
        BackUrlUtil.setBackUrl(mv, request);// 设置返回的url
        return mv;
    }

    @RequestMapping("/add")
    public String add(HttpServletRequest request, HttpServletResponse response, SysUser sysUser) {
        sysUser.setCreate_date(new Date());
        sysUser.setStatus(1);
        UserSession userSession = SessionUtil.getUserSession(request);
        sysUser.setCreate_person(userSession.getRealname());
        sysUser.setType(userSession.getType());//类型
        int flag = sysUserService.add(sysUser);
        if (flag == 0)
            return "forward:/error.htm?msg=" + StringUtil.encodeUrl("用户信息新增失败");
        else if (flag == 2)
            return "forward:/error.htm?msg=" + StringUtil.encodeUrl("用户手机号已存在");
        else
            return "forward:/success.htm?msg=" + StringUtil.encodeUrl("用户信息新增成功");
    }

    @RequestMapping("/update")
    public String update(HttpServletRequest request, HttpServletResponse response, SysUser sysUser) {
        int flag = sysUserService.update(sysUser);
        if (flag == 0)
            return "forward:/error.htm?msg=" + StringUtil.encodeUrl("用户信息修改失败");
        else
            return "forward:/success.htm?msg=" + StringUtil.encodeUrl("用户信息修改成功");
    }

    @RequestMapping("/delete")
    public String delete(HttpServletRequest request, HttpServletResponse response, int id) {
        int flag = sysUserService.delete(id);
        if (flag == 0)
            return "forward:/error.htm?msg=" + StringUtil.encodeUrl("用户删除失败");
        else
            return "forward:/success.htm?msg=" + StringUtil.encodeUrl("用户删除成功");
    }

    /**
     * 用户grid列表
     *
     * @param request
     * @param response
     */
    @RequestMapping("/search")
    public void search(HttpServletRequest request, HttpServletResponse response, SysUserSearchVO sysUserSearchVO) {
        int count = sysUserService.listCount(sysUserSearchVO);
        String json = JsonUtil.createExtjsPageJson(count, sysUserService.list(sysUserSearchVO));
        WebUtil.out(response, json);
    }

    /**
     * 重置密码
     *
     * @param request
     * @param response
     */
    @RequestMapping("/saveResetPass")
    public String saveResetPass(HttpServletRequest request, HttpServletResponse response, int id) {
        int flag = sysUserService.saveResetPass(id);
        if (flag == 0)
            return "forward:/error.htm?msg=" + StringUtil.encodeUrl("用户重置密码失败");
        else
            return "forward:/success.htm?msg=" + StringUtil.encodeUrl("用户重置密码成功，重置后密码变为123456");
    }

    /**
     * 用户加锁，状态由1变为0
     *
     * @param request
     * @param response
     * @param id
     */
    @RequestMapping("/saveLock")
    public String saveLock(HttpServletRequest request, HttpServletResponse response, int id) {
        int flag = sysUserService.updateStatus(id, 2);
        if (flag == 0)
            return "forward:/error.htm?msg=" + StringUtil.encodeUrl("用户锁定失败");
        else
            return "forward:/success.htm?msg=" + StringUtil.encodeUrl("用户锁定成功");
    }

    /**
     * 用户解锁，状态由2变为1
     *
     * @param request
     * @param response
     * @param id
     */
    @RequestMapping("/saveUnlock")
    public String saveUnlock(HttpServletRequest request, HttpServletResponse response, int id) {
        int flag = sysUserService.updateStatus(id, 1);
        if (flag == 0)
            return "forward:/error.htm?msg=" + StringUtil.encodeUrl("用户解锁失败");
        else
            return "forward:/success.htm?msg=" + StringUtil.encodeUrl("用户解锁成功");
    }

    /**
     * 用户登录信息
     *
     * @param request
     * @param response
     */
    @RequestMapping("/searchUserLogin")
    public ModelAndView searchUserLogin(HttpServletRequest request, HttpServletResponse response, int id) {
        ModelAndView mv = new ModelAndView();
        int recordCount = sysUserLoginService.listCount(id);// 获取查询总数
        int pageIndex = WebUtil.getSafeInt(request.getParameter("pageIndex"), 1);// 获取当前页数
        int pageSize = 10;//  每页记录数
        String url = createUserLoginUrl(id, pageIndex, pageSize);
        PageNavigate pageNavigate = new PageNavigate(url, pageIndex, pageSize, recordCount);//
        List<SysUserLogin> list = sysUserLoginService.list(id, pageNavigate.getPageIndex(), pageSize);
        mv.addObject("pageNavigate", pageNavigate);// 设置分页的变量
        mv.addObject("list", list);// 把获取的记录放到mv里面
        mv.setViewName("/sys/userLogin");// 跳转至指定页面
        return mv;
    }

    // 设置分页url，一般有查询条件的才会用到
    private String createUserLoginUrl(int id, int pageIndex, int pageSize) {
        String url = pubConfig.getDynamicServer() + "/sys/user/searchUserLogin.htm?id=" + id;
        return url;
    }


    /**
     * 验证用户代码是否存在
     *
     * @param request
     * @param response
     */
    @RequestMapping("/checkUserExist")
    public void checkUserExist(HttpServletRequest request, HttpServletResponse response, String username) {
        SysUser sysUser = sysUserService.getByUsername(username);
        if (sysUser == null)
            WebUtil.out(response, "true");
        else
            WebUtil.out(response, "false");
    }
}
