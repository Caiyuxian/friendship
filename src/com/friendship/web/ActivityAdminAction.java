package com.friendship.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.nutz.dao.pager.Pager;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.friendship.model.Activity;
import com.friendship.service.ActivityService;
import com.friendship.util.PageUtil;
@InjectName
@IocBean
@At("/admin")
public class ActivityAdminAction {
	private ActivityService activityService;
	
	/**
	 * 获取所有活动信息列表
	 * @return activityList
	 */
	@At("/actlist")
	@Ok("jsp:jsp.activitylist")
	public List<Activity> list(@Param("currentPage")int currentPage,HttpServletRequest req){
		if(currentPage==0){
			currentPage = 1;
		}
		Pager pager = activityService.dao().createPager(currentPage, PageUtil.PageSizeAdmin);
		List<Activity> list =  activityService.allact(pager,null);
		int count = activityService.getCount(Activity.class,null);
		int maxPage = activityService.getMaxCount(count,PageUtil.PageSizeAdmin);
		req.setAttribute("currentPage", currentPage);
		req.setAttribute("maxPage", maxPage);
		return list;
	}
}
