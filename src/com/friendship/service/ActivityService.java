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

	public List<Activity> allact(Pager pager, String type) {
		if(type!=null){
			return this.query(Cnd.where("acttype", "=", type).desc("edittime"), pager);
		}else{
			return this.query(Cnd.orderBy().desc("edittime"), pager);
		}
	}

	public int getMaxCount(int count, int pagesize) {
		if(count%pagesize==0){
			return count/pagesize;
		}else{
			return count/pagesize+1;
		}
	}

	public int getCount(Class<Activity> class1, String type) {
		if(type!=null){
			List<Activity> actlist = this.query(Cnd.where("acttype", "=", type), null);
			return actlist.size();
		}else{
			return this.count();
		}
	}

	public List<Activity> getActByUser(int id,Pager pager) {
		return this.query(Cnd.where("userid", "=", id), pager);
	}

	public int getMyCount(int id) {
		List<Activity> list = this.query(Cnd.where("userid", "=", id), null);
		return list.size();
	}

}
