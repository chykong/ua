package com.critc.sys.controller;

import com.critc.sys.model.SysModule;
import com.critc.sys.service.SysModuleService;
import com.critc.util.backurl.BackUrlUtil;
import com.critc.util.cache.EhCacheUtil;
import com.critc.util.config.PubConfig;
import com.critc.util.controller.BaseController;
import com.critc.util.json.JsonUtil;
import com.critc.util.redis.RedisUtil;
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
 * 系统模块管理
 *
 * @author 孔垂云
 */
@Controller
@RequestMapping("/sys/module")
public class SysModuleController extends BaseController {
    @Autowired
    private SysModuleService sysModuleService;
    @Autowired
    private PubConfig pubConfig;

    /**
     * 进入模块维护界面
     *
     * @return
     */
    @RequestMapping("/index")
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/sys/module");
        setBtnAutho(request, "SysModule");
        List<SysModule> list = sysModuleService.list();
        mv.addObject("list", list);// 把获取的记录放到mv里面
        String url = "/sys/module/index.htm?";
        mv.addObject("backUrl", StringUtil.encodeUrl(url));
        return mv;
    }

    /**
     * 新增模块
     *
     * @param request
     * @param response
     * @param sysModule
     * @return
     */
    @RequestMapping("/toAdd")
    public ModelAndView toAdd(HttpServletRequest request, HttpServletResponse response, SysModule sysModule) {
        ModelAndView mv = new ModelAndView();
        String ztree = sysModuleService.createZtreeByModule();
        mv.addObject("ztree", ztree);
        mv.setViewName("/sys/moduleAdd");
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
        SysModule sysModule = sysModuleService.get(id);
        String ztree = sysModuleService.createZtreeByModule();
        mv.addObject("ztree", ztree);
        mv.addObject("sysModule", sysModule);
        mv.setViewName("/sys/moduleUpdate");
        BackUrlUtil.setBackUrl(mv, request);// 设置返回的url
        return mv;
    }

    @RequestMapping("/add")
    public String add(HttpServletRequest request, HttpServletResponse response, SysModule sysModule) {
        if (sysModule.getParent_id() == null)
            sysModule.setParent_id(1);
        int flag = sysModuleService.add(sysModule);
        if (flag == 0)
            return "forward:/error.htm?msg=" + StringUtil.encodeUrl("模块新增失败");
        else
            return "forward:/success.htm?msg=" + StringUtil.encodeUrl("模块新增成功");
    }

    @RequestMapping("/update")
    public String update(HttpServletRequest request, HttpServletResponse response, SysModule sysModule) {
        if (sysModule.getParent_id() == null)
            sysModule.setParent_id(1);
        if (sysModule.getId() == sysModule.getParent_id()) {
            return "forward:/error.htm?msg=" + StringUtil.encodeUrl("不能和上级节点一样");
        } else {
            int flag = sysModuleService.update(sysModule);
            if (flag == 0)
                return "forward:/error.htm?msg=" + StringUtil.encodeUrl("模块修改失败");
            else if (flag == 2)
                return "forward:/error.htm?msg=" + StringUtil.encodeUrl("上级节点不存在");
            else
                return "forward:/success.htm?msg=" + StringUtil.encodeUrl("模块修改成功");
        }
    }

    @RequestMapping("/delete")
    public String delete(HttpServletRequest request, HttpServletResponse response, int id) {
        int flag = sysModuleService.delete(id);
        if (flag == 0)
            return "forward:/error.htm?msg=" + StringUtil.encodeUrl("删除失败");
        else if (flag == 2)
            return "forward:/error.htm?msg=" + StringUtil.encodeUrl("还有下级节点，不能删除");
        else
            return "forward:/success.htm?msg=" + StringUtil.encodeUrl("删除成功");
    }

    /**
     * 模块tree grid列表
     *
     * @param request
     * @param response
     */
    @RequestMapping("/getTreeGrid")
    public void getTreeGrid(HttpServletRequest request, HttpServletResponse response) {
        String json = sysModuleService.getTreeGridJson();
        WebUtil.out(response, json);
    }

    /**
     * 清空缓存
     *
     * @param request
     * @param response
     */
    @RequestMapping("/clearCache")
    public void clearCache(HttpServletRequest request, HttpServletResponse response) {
        EhCacheUtil.removeAll("sysCache");
        WebUtil.out(response, JsonUtil.createOperaStr(true, "操作成功"));
    }


    /**
     * 清空redis
     *
     * @param request
     * @param response
     */
    @RequestMapping("/clearRedis")
    public void clearRedis(HttpServletRequest request, HttpServletResponse response) {
        RedisUtil.flushDb();
        WebUtil.out(response, JsonUtil.createOperaStr(true, "操作成功"));
    }

}
