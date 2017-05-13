package com.critc.sys.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.critc.sys.dao.SysFunctionDao;
import com.critc.sys.model.SysFunction;
import com.critc.sys.model.SysRoleFunction;
import com.critc.util.cache.EhCacheUtil;

@Service
public class SysFunctionService {
	@Autowired
	private SysFunctionDao sysFunctionDao;

	public int add(SysFunction sysFunction) {
		EhCacheUtil.removeAll("sysCache");
		return sysFunctionDao.add(sysFunction);
	}

	public int update(SysFunction sysFunction) {
		EhCacheUtil.removeAll("sysCache");
		return sysFunctionDao.update(sysFunction);
	}

	public int delete(int id) {
		EhCacheUtil.removeAll("sysCache");
		return sysFunctionDao.delete(id);
	}

	public SysFunction get(int id) {
		return sysFunctionDao.get(id);
	}

	public List<SysFunction> list(int module_id) {
		return sysFunctionDao.list(module_id);
	}

	/**
	 * 根据角色和模块id获取下面的所有按钮
	 * @param role_id
	 * @param module_id
	 * @return
	 */
	public List<SysFunction> listRoleModuleFunction(int role_id, int module_id) {
		List<SysFunction> list = new ArrayList<SysFunction>();
		//先根据module_id获取所有function
		//根据role_id获取已经赋值的function
		//循环设置checkbox
		List<SysFunction> listFunction = sysFunctionDao.listByModule_id(module_id);
		List<SysRoleFunction> listRoleFunctions = sysFunctionDao.listRoleFunctionByRole_id(role_id);
		HashMap<Integer, Integer> hashRoleFunction = new HashMap<Integer, Integer>();
		for (SysRoleFunction sysRoleFunction : listRoleFunctions) {
			hashRoleFunction.put(sysRoleFunction.getFunction_id(), sysRoleFunction.getId());
		}

		for (SysFunction sysFunction : listFunction) {
			if (hashRoleFunction.containsKey(sysFunction.getId())) {
				sysFunction.setChecked(1);
			} else {
				sysFunction.setChecked(0);
			}
			list.add(sysFunction);
		}
		return list;

	}

	/**
	 * 获取所有function，返回hashmap，key是url，用户记录操作日志时使用
	 * @return
	 */
	public HashMap<String, SysFunction> getAllFunction() {
		List<SysFunction> list = sysFunctionDao.getAll();
		HashMap<String, SysFunction> hashMap = null;
		hashMap = EhCacheUtil.get("sysCache", "allFunction");
		if (hashMap == null) {
			hashMap = new HashMap<String, SysFunction>();
			for (SysFunction sysFunction : list)
				hashMap.put(sysFunction.getUrl(), sysFunction);
			EhCacheUtil.put("sysCache", "allFunction", hashMap);
		}
		return hashMap;
	}

	/**
	 * 获取所有function
	 * @param url
	 * @return
	 */
	public List<SysFunction> getAll() {
		return sysFunctionDao.getAll();
	}
}
