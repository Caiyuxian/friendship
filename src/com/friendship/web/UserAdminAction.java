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
import com.friendship.model.User;
import com.friendship.service.ActivityService;
import com.friendship.service.UserService;
import com.friendship.util.PageUtil;
@InjectName
@IocBean
@At("/admin")
public class UserAdminAction {
	private UserService userService;
	
	/**
	 * 获取所有活动信息列表
	 * @return activityList
	 */
	@At("/userlist")
	@Ok("jsp:jsp.userlist")
	public List<User> list(@Param("currentPage")int currentPage,HttpServletRequest req){
		if(currentPage==0){
			currentPage = 1;
		}
		Pager pager = userService.dao().createPager(currentPage, PageUtil.PageSizeAdmin);
		List<User> list =  userService.allact(pager);
		int count = userService.getCount(User.class);
		int maxPage = userService.getMaxCount(count,PageUtil.PageSizeAdmin);
		req.setAttribute("currentPage", currentPage);
		req.setAttribute("maxPage", maxPage);
		return list;
	}
	
	@At
	@Ok("redirect:/admin/userlist.nut")
	public void ManagerAccount(@Param("id")int id,@Param("type")String type){
		User u = userService.fetch(id);
		//封号，前台传过来的状态值为1
		if(type.equals("1")){
			u.setType(0);
			userService.dao().update(u);
		}
		//解封，前天传过来的状态值为0
		else if(type.equals("0")){
			u.setType(1);
			userService.dao().update(u);
		}
	}
}
