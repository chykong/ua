package com.critc.config.listener;

import com.critc.util.session.UserSession;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 统计在线人数
 * @author 孔垂云
 *
 */
public class OnLineListener implements ServletContextListener, HttpSessionListener, HttpSessionAttributeListener {
	// 声明一个ServletContext对象   
	private ServletContext application = null;
	public static HashMap<String, UserSession> hashMap = new HashMap<String, UserSession>();

	public void contextInitialized(ServletContextEvent sce) {
		// 容器初始化时，向application中存放一个空的容器   
		this.application = sce.getServletContext();
		this.application.setAttribute("alluser", new ArrayList<UserSession>());
	}

	public void contextDestroyed(ServletContextEvent sce) {
	}

	public void sessionCreated(HttpSessionEvent se) {
		//		UserSession userSession = (UserSession) se.getSession().getAttribute("userSession");
		//		if (userSession != null) {
		//			hashMap.put(se.getSession().getId(), userSession);
		//			System.out.println("用户登录，当前用户总数：" + hashMap.size());
		//		}
		//		System.out.println(se.getSession().getId());
	}

	public void sessionDestroyed(HttpSessionEvent se) {
		// 将用户名称从列表中删除   
		//		List l = (List) this.application.getAttribute("alluser");
		//		UserSession userSession = (UserSession) se.getSession().getAttribute("userSession");
		//		if (userSession != null)
		//			l.remove(userSession);
		//		System.out.println("session过期，当前用户数：" + l.size());
		//		this.application.setAttribute("alluser", l);
		System.out.println(se.getSession().getId());
		if (hashMap.containsKey(se.getSession().getId())) {
			hashMap.remove(se.getSession().getId());
			System.out.println("session自动销毁：" + hashMap.size());
		}
	}

	public void attributeAdded(HttpSessionBindingEvent se) {
		// 如果登录成功，则将用户名保存在列表之中   
		//		List<UserSession> l = (List<UserSession>) this.application.getAttribute("alluser");
		//		l.add((UserSession) se.getValue());
		//		
		//		this.application.setAttribute("alluser", l);
		//		hashMap.put(se.getSession().getId(), (UserSession) se.getValue());
		//		System.out.println(se.getSession().getId());
		//		System.out.println("系统登录，当前用户数：" + hashMap.size());

		UserSession userSession = (UserSession) se.getSession().getAttribute("userSession");
		if (userSession != null) {
			System.out.println(se.getSession().getId());
			hashMap.put(se.getSession().getId(), userSession);
			System.out.println("用户登录，当前用户总数：" + hashMap.size());
		}
	}

	public void attributeRemoved(HttpSessionBindingEvent se) {
		//		List<UserSession> l = (List<UserSession>) this.application.getAttribute("alluser");
		//		UserSession userSession = (UserSession) se.getValue();
		//		if (userSession != null)
		//			l.remove(userSession);
		//		System.out.println(se.getSession().getId());
		//		hashMap.remove(se.getSession().getId());
		//		System.out.println("用户注销，当前用户数：" + hashMap.size());
		//		this.application.setAttribute("alluser", l);
	}

	public void attributeReplaced(HttpSessionBindingEvent se) {
	}

}
