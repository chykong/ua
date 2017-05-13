package com.critc.sys.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.critc.sys.dao.SysFunctionDao;
import com.critc.sys.dao.SysModuleDao;
import com.critc.sys.model.SysModule;
import com.critc.util.string.StringUtil;

@Service
public class SysModuleService {
	@Autowired
	private SysModuleDao sysModuleDao;
	@Autowired
	private SysFunctionDao sysOperaDao;

	public int add(SysModule sysModule) {
		return sysModuleDao.add(sysModule);
	}

	public int update(SysModule sysModule) {
		return sysModuleDao.update(sysModule);
	}

	public int delete(int id) {
		if (sysModuleDao.getChildCount(id) > 0)
			return 2;
		else {
			sysOperaDao.deleteByModule_id(id);
			return sysModuleDao.delete(id);
		}
	}

	public SysModule get(int id) {
		return sysModuleDao.get(id);
	}

	/**
	 * 模块列表
	 * 
	 * @return
	 */
	public List<SysModule> list() {
		List<SysModule> list = sysModuleDao.list();
		List<SysModule> listRet = new ArrayList<>();
		listRet = createModuleList(list, listRet, 1);
		return listRet;
	}

	private List<SysModule> createModuleList(List<SysModule> list, List<SysModule> listRet, int parent_id) {
		for (SysModule sysModule : list) {
			if (sysModule.getParent_id() == parent_id) {
				listRet.add(sysModule);
				if (sysModule.getCnt() > 0) {
					listRet = createModuleList(list, listRet, sysModule.getId());
				}
			}
		}
		return listRet;
	}

	/**
	 * 根据用户类型获取能看的模块
	 * 
	 * @return
	 */
	public List<SysModule> listByType(int type) {
		int role_id = 0;
		if (type == 1)//管理员
			role_id = 1;
		else if (type == 2)
			role_id = 2;//门店
		List<SysModule> list = null;
		if (type == 1)//管理员取所有的
			list = sysModuleDao.list();
		else
			list = sysModuleDao.listByRole_id(role_id);
		return list;
	}

	/**
	 * 取得模块 treegrid json
	 * 
	 * @return
	 */
	public String getTreeGridJson() {
		List<SysModule> list = list();
		String json = "";
		json = createTreeGridJson(list, 1);
		return "[" + json + "]";
	}

	private String createTreeGridJson(List<SysModule> list, int parent_id) {
		String str = "";
		for (SysModule sysModule : list) {
			if (sysModule.getParent_id() == parent_id) {
				str += "{\"id\":" + sysModule.getId() + ",\"parent_id\":" + sysModule.getParent_id() + ",\"name\":\"" + sysModule.getName() + "\"" + ",\"code\":\"" + sysModule.getCode() + "\""
						+ ",\"url\":\"" + sysModule.getUrl() + "\"" + ",\"target\":\"" + sysModule.getTarget() + "\"" + ",\"iconImg\":\"" + sysModule.getIconImg() + "\"" + ",\"display_order\":"
						+ sysModule.getDisplay_order();
				if (sysModule.getCnt() > 0)
					str += ",\"leaf\":false,\"expanded\":true,\"children\":[" + createTreeGridJson(list, sysModule.getId()) + "]";
				else
					str += ",\"leaf\":true";
				str += "},";
			}
		}
		str = StringUtil.subTract(str);
		return str;
	}

	/**
	 * 取得模块 comboboxTree json
	 * 
	 * @return
	 */
	public String getComboboxTreeJson() {
		List<SysModule> list = list();
		String json = "";
		json = createComboboxTreeJson(list, 0);
		return "[" + json + "]";
	}

	private String createComboboxTreeJson(List<SysModule> list, int parent_id) {
		String str = "";
		for (SysModule sysModule : list) {
			if (sysModule.getParent_id() == parent_id) {
				str += "{\"id\":\"" + sysModule.getId() + "\",\"parent_id\":" + sysModule.getParent_id() + ",\"text\":\"" + sysModule.getName() + "\"";
				if (sysModule.getParent_id() == 0) {
					str += ",\"expanded\":" + true + "";
				} else {
					str += ",\"expanded\":" + false + "";
				}
				if (sysModule.getCnt() > 0)
					str += ",\"children\":[" + createComboboxTreeJson(list, sysModule.getId()) + "]";
				else {
					str += ",\"leaf\":true";
				}
				str += "},";
			}
		}
		str = StringUtil.subTract(str);
		return str;
	}

	public int getChildCount(int id) {
		return sysModuleDao.getChildCount(id);
	}

	/**
	 * 生成Ztree的树节点,新增模块时使用，只有模块上下级
	 * 
	 * @return
	 */
	public String createZtreeByModule() {
		List<SysModule> listModule = sysModuleDao.list();// 模块列表
		StringBuilder sb = new StringBuilder();
		for (SysModule sysModule : listModule) {
			if (sysModule.getParent_id() != 0) {
				sb.append("{id : \"" + sysModule.getId() + "\",pId :\"" + sysModule.getParent_id() + "\",name :\"" + sysModule.getName() + "\",open : false");
				sb.append("},");
			}
		}
		return StringUtil.subTract(sb.toString());
	}

	/**
	 * 根据user_id获取所有功能菜单
	 * @param user_id
	 * @return
	 */
	public List<SysModule> listByUser_id(int user_id) {
		return sysModuleDao.listByUser_id(user_id);
	}
}
