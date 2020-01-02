package com.chen.programmer.service.admin;

import org.springframework.stereotype.Service;

import com.chen.programmer.entity.admin.Menu;

/**
 * 菜单管理service
 * @author Administrator
 *
 */
@Service
public interface MenuService {
	public int add(Menu menu);
}
