package com.chen.programmer.page.admin;

import org.springframework.stereotype.Component;

/**
 * 分页基本信息
 * @author Administrator
 *
 */
@Component //让spring自动加载到容器中
public class Page {
	private int page = 1; //当前页码
	private int rows; //每页显示数量
	private int offset; //对应数据库偏移量 例如；select * from menu limit 0 , 10;此处的0即代表偏移量，从0处拿出10条数据
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public int getOffset() {
		this.offset = (page - 1) * rows;//偏移量计算
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	
}
