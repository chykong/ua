package com.critc.config.interceptor;

import com.critc.sys.service.SysLogService;
import com.critc.sys.service.SysRoleService;
import com.critc.util.config.PubConfig;
import com.critc.util.date.DateUtil;
import com.critc.util.http.RequestUtil;
import com.critc.util.json.JsonUtil;
import com.critc.util.session.SessionUtil;
import com.critc.util.session.UserSession;
import com.critc.util.string.StringUtil;
import com.critc.util.web.WebUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 系统权限控制拦截器，访问每个操作时先进行权限校验。
 * 同时记录下日志
 *
 * @author 孔垂云
 */
public class AuthorityInterceptor implements HandlerInterceptor {
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysLogService sysLogService;
    @Autowired
    private PubConfig pubConfig;

    private static Logger logger = LoggerFactory.getLogger("operationLog");

    /**
     * 操作前先判断是否登录，未登录跳转到登录界面
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        UserSession userSession = SessionUtil.getUserSession(request);
        //校验权限
        String path = request.getServletPath();
        path = path.substring(1, path.length());
        String operaMethod = path.substring(path.lastIndexOf("/"));
        operaMethod = operaMethod.substring(1, operaMethod.length());
        String parameters = RequestUtil.getOperaParams(request);
        //记操作日志
        logOperation(path, parameters, userSession);
        //目前只校验add/update/delete/save/import开头的方法，其余不校验
        if (checkUrl(operaMethod)) {
            boolean checked = sysRoleService.checkAuthority(userSession.getRole_id(), path);
            if (checked) {
                //记录数据库日志
                sysLogService.addLog(userSession.getUser_id(), path, parameters, userSession.getUser_ip());
                return true;
            } else {
                boolean isAjaxRequest = StringUtil.checkAjaxRequest(request);
                if (isAjaxRequest) {
                    Message msg = new Message();
                    msg.setSuccess(false);
                    msg.setExName("错误提示");
                    msg.setMessage("权限不足");
                    msg.setMsgText("权限不足");
                    msg.setIsException(true);
                    WebUtil.out(response, JsonUtil.toStr(msg));
                } else {
                    String location = pubConfig.getDynamicServer() + "/error.htm?msg=" + StringUtil.encodeUrl("权限不足");
                    String str = "<script>location.href='" + location + "';</script>";
                    WebUtil.out(response, str);
                }
                return false;
            }
        } else {
            return true;
        }
    }

    /**
     * 记录文本日志
     *
     * @param path
     * @param parameters
     * @param us
     */
    public void logOperation(String path, String parameters, UserSession us) {
        String log = "";
        log = "[OPERALOG]" + "-[" + us.getUser_ip() + "]" + "-[" + DateUtil.getSystemTime() + "]-" + "[" + us.getUser_name() + "]-" + "[INFO]-" + path + "-" + parameters;
        logger.info(log);
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    private static boolean checkUrl(String url) {
        Pattern pattern = Pattern.compile("^(add|update|delete|save|import).*");
        Matcher matcher = pattern.matcher(url);
        return matcher.matches();
    }

    public static void main(String[] args) {
        //当条件满足时，将返回true，否则返回false
        System.out.println(AuthorityInterceptor.checkUrl("aupdate.htm"));
    }
}
