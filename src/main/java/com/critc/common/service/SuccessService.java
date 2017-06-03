package com.critc.common.service;

import org.springframework.stereotype.Service;

@Service
public class SuccessService {

	/**
	 * 根据sucCode返回返回的信息
	 * @param errCode
	 * @return
	 */
	public String getMsg(String successCode) {
		String msg = "";
		switch (successCode) {
		case "SysUserUpdateSuccess"://用户修改成功
			msg = "订单支付成功！";
			break;

		default:
			msg = "操作成功！";
			break;
		}
		return msg;
	}
}
