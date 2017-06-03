package com.critc.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.critc.common.dao.CommonDao;
import com.critc.common.vo.ComboboxVO;

@Service
public class CommonService {
	@Autowired
	private CommonDao commonDao;

	/**
	 * 下拉列表取值
	 * @param table_name 表名
	 * @param value 下拉框值
	 * @param content下拉框显示
	 * @param order 排序
	 * @param sort 升序还是降序
	 * @param condition 查询条件
	 * @return
	 */
	public List<ComboboxVO> listCombobox(String table_name, String value, String content, String order, String sort, String condition) {
		return commonDao.listCombobox(table_name, value, content, order, sort, condition);
	}
	
	public List<ComboboxVO> listComboboxByErp(String table_name, String value, String content, String order, String sort, String condition) {
		return commonDao.listComboboxByErp(table_name, value, content, order, sort, condition);
	}
}
