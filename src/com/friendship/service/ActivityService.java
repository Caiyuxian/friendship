package com.friendship.service;

import java.util.List;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.entity.Entity;
import org.nutz.dao.pager.Pager;
import org.nutz.lang.util.PageInfo;
import org.nutz.service.IdNameEntityService;

import com.friendship.model.Activity;
/**
 * 活动服务类
 * @author Administrator
 */
public class ActivityService extends IdNameEntityService<Activity>{
	public ActivityService(Dao dao){
		super(dao);
	}

	public List<Activity> allact(Pager pager) {
		return this.query(Cnd.orderBy().desc("edittime"), pager);
	}

}
