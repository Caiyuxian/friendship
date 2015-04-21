package com.friendship.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.nutz.dao.pager.Pager;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.friendship.model.Activity;
import com.friendship.model.User;
import com.friendship.service.ActivityService;

@InjectName
@IocBean
@At("/base")
public class ActivityAction {
	
	private ActivityService activityService;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/**
	 * 获取所有活动信息列表
	 * @return activityList
	 */
	@At
	@Ok("jsp:jsp.index")
	public List<Activity> list(@Param("currentPage")int currentPage){
		
		Pager pager = activityService.dao().createPager(currentPage, 6);
		return activityService.allact(pager);
	}
	/**
	 * 保存活动信息
	 * @param content
	 * @param time
	 * @param type
	 * @param address
	 * @param session
	 */
	@At
	@Ok("redirect:/base/list.nut") 
	public void save(@Param("actcontent")String content,@Param("acttime")String time,
			@Param("acttype")String type,@Param("actaddress")String address,HttpSession session){
		Activity act = new Activity();
		User user = (User) session.getAttribute("user");
		act.setAddress(address);
		act.setContent(content);
		act.setActtype(type);
		act.setActivitytime(time);
		act.setEdittime(sdf.format(new Date()));
		act.setUserid(user.getId());
		act.setUsername(user.getUsername());
		activityService.dao().insert(act);
	}
}
