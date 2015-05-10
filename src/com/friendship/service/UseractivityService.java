package com.friendship.service;

import org.nutz.dao.Dao;
import org.nutz.service.IdNameEntityService;

import com.friendship.model.User_activity;

public class UseractivityService extends IdNameEntityService<User_activity>{

	public UseractivityService(Dao dao){
		super(dao);
	}
}
