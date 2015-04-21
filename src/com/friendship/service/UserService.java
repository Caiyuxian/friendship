package com.friendship.service;

import java.util.List;

import org.nutz.dao.Dao;
import org.nutz.service.IdNameEntityService;

import com.friendship.model.User;
/**
 * 用户服务类
 * @author Administrator
 */
public class UserService extends IdNameEntityService<User>{

	public UserService(Dao dao){
		super(dao);
	}

	public List<User> alluser() {
		return this.query(null, null);
	}
}
