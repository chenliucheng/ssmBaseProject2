package com.chen.programmer.service.admin.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chen.programmer.dao.admin.UserDao;
import com.chen.programmer.entity.admin.User;
import com.chen.programmer.service.admin.UserService;

/*
 * user用户serviceimpl
 */
@Service //标注为service，让spring框架识别，自动加入容器
public class UserServiceimpl implements UserService {
	
	@Autowired //让spring框架识别，自动从spring容器中拿出变量
	private UserDao userDao;
	@Override
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		return userDao.findByUsername(username);
	}

}
