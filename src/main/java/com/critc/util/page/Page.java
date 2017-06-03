package com.critc.util.page;

import java.util.List;

import com.critc.util.global.GlobalConst;

/**
 * 类说明：分页方法封装
 * 
 * @author 作者: LiuJunGuang
 * @version 创建时间：2011-11-9 下午02:22:35
 */
public class Page<T> {
	/**
	 * 总页数，通过总记录数和每页显示记录条数计算获得
	 */
	private int countPage;
	/**
	 * 总记录数
	 */
	private int countRecord;
	/**
	 * 当前页，默认是第一页
	 */
	private int currentPage = 1;
	/**
	 * 结果列表
	 */
	private List<T> list = null;
	/**
	 * 每页显示记录条数 ，默认是每页显示13条记录
	 */
	private int onePageCount = GlobalConst.pageSize;
	/**
	 * 开始索引，通过当前页和每页显示记录条数计算获得
	 */
	private int startIndex;

	public Page() {
	}

	/**
	 * 两个参数的构造方法，调用该构造方法需要另行设置结果list
	 * 
	 * @param currentPage
	 *            当前页
	 * @param countRecord
	 *            总页数
	 */
	public Page(int currentPage, int countRecord) {
		this.currentPage = currentPage;
		this.countRecord = countRecord;
		calculate();
	}

	/**
	 * 能够设置一页显示多少条记录的构造方法
	 * 
	 * @param currentPage
	 *            当前页
	 * @param countRecord
	 *            总记录数
	 * @param onePageCount
	 *            每页最多显示的记录条数
	 */
	public Page(int currentPage, int countRecord, int onePageCount) {
		super();
		this.countRecord = countRecord;
		this.currentPage = currentPage;
		this.onePageCount = onePageCount;
		calculate();
	}

	/**
	 * 计算开始索引和总页数
	 */
	private void calculate() {
		// 计算开始索引
		this.startIndex = (currentPage - 1) * onePageCount;
		// 计算总页数
		this.countPage = (countRecord % onePageCount == 0) ? (countRecord / onePageCount)
				: (countRecord / onePageCount + 1);
	}

	public int getCountPage() {
		return countPage;
	}

	public int getCountRecord() {
		return countRecord;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public List<T> getList() {
		return list;
	}

	public int getOnePageCount() {
		return onePageCount;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setCountPage(int countPage) {
		this.countPage = countPage;
	}

	public void setCountRecord(int countRecord) {
		this.countRecord = countRecord;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public void setOnePageCount(int onePageCount) {
		this.onePageCount = onePageCount;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
}
