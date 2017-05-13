package com.critc.util.session;

import javax.servlet.http.HttpServletRequest;

/**
 * session工具类，用于获取用户session
 * @author  孔垂云
 */
public class SessionUtil {
	/**
	 * 
	 * 功能描述:获取用户session
	 * 
	 * @param request
	 * @return UserSession
	 * @version 1.0.0
	 * @author 孔垂云
	 */
	public static UserSession getUserSession(HttpServletRequest request) {
		if (request.getSession().getAttribute("userSession") != null)
			return (UserSession) request.getSession().getAttribute("userSession");
		else {
			//			UserSession userSession = new UserSession();
			//			userSession.setShop_id(1);
			//			userSession.setRole_id(2);
			//			userSession.setUser_id(1);
			//			userSession.setUser_name("admin");
			//			return userSession;
			return null;
		}
	}
}
