package com.critc.sys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.critc.sys.dao.SysConfigDao;
import com.critc.sys.model.SysConfig;

@Service
public class SysConfigService {
	@Autowired
	private SysConfigDao sysConfigDao;

	/**
	 * 新增
	 * 
	 * @return
	 */
	public synchronized int add(SysConfig sysConfig) {
		return sysConfigDao.add(sysConfig);
	}

	public int update(SysConfig sysConfig) {
		return sysConfigDao.update(sysConfig);
	}

	public int delete(String syskey) {
		return sysConfigDao.delete(syskey);
	}

	/**
	 * 列表
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public List<SysConfig> list() {
		return sysConfigDao.list();
	}

}
