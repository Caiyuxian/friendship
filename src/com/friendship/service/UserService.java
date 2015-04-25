package com.friendship.service;

import java.util.List;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.pager.Pager;
import org.nutz.service.IdNameEntityService;

import com.friendship.model.Activity;
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

	public List<User> allact(Pager pager) {
		return this.query(null, pager);
	}

	public int getCount(Class<User> class1) {
		return this.count();
	}

	public int getMaxCount(int count, int pagesizeadmin) {
		if(count%pagesizeadmin==0){
			return count/pagesizeadmin;
		}else{
			return count/pagesizeadmin+1;
		}
	}
}
