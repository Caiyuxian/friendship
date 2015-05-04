package com.friendship.service;

import java.util.List;

import org.nutz.dao.Dao;
import org.nutz.service.IdNameEntityService;

import com.friendship.model.Manager;

public class ManagerService extends IdNameEntityService<Manager>{
	public ManagerService(Dao dao){
		super(dao);	
	}

	public List<Manager> getAll() {
		return this.query(null, null);
	}
}
