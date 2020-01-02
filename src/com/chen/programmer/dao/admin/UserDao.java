package com.chen.programmer.dao.admin;

import org.springframework.stereotype.Repository;

import com.chen.programmer.entity.admin.User;

/**
 * User用户Dao
 * @author Administrator
 *
 */
@Repository //标注为dao类，让spring框架识别
public interface UserDao {
	public User findByUsername(String username);
}
