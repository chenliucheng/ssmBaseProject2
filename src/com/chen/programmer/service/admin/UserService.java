package com.chen.programmer.service.admin;

import org.springframework.stereotype.Service;

import com.chen.programmer.entity.admin.User;

/**
 * user用户关键字
 * @author Administrator
 *
 */
@Service // 用来标注service类,让spring框架识别
public interface UserService {
	//定义一个与User实体类相关的方法；
	public User findByUsername(String username);
}
