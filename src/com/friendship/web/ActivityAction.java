package com.friendship.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.nutz.dao.Cnd;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.nutz.trans.Atom;
import org.nutz.trans.Trans;

import com.friendship.model.Activity;
import com.friendship.model.Reply;
import com.friendship.model.User;
import com.friendship.model.User_activity;
import com.friendship.service.ActivityService;
import com.friendship.service.ReplyService;
import com.friendship.service.UserService;
import com.friendship.service.UseractivityService;
import com.friendship.util.PageUtil;
import com.friendship.util.SentEmail;

@InjectName
@IocBean
@At("/base")
public class ActivityAction {

	private ActivityService activityService;
	private UserService userService;
	private UseractivityService useractivityService;
	private ReplyService replyService;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * 参加活动
	 * @param userid  用户id
	 * @param actid   活动id
	 * @return
	 */
	@Ok("json")
	@At
	public int attendAct(@Param("userid") final int userid,
			@Param("actid") final int actid) {
		
		//先检查表中是否有该条记录，有得话直接返回，避免重复插入数据
		List<User_activity> user_act =  useractivityService.query(null, null);
		for(User_activity uu:user_act){
			if(uu.getUserid()==userid&&uu.getActid()==actid){
				return 2;
			}
		}
		// 拿到当前活动内容
		Activity act = activityService.fetch(actid);
		// 拿到参加者的信息
		User u = userService.fetch(userid);
		// 拿到活动发布者的信息
		User user = userService.fetch(act.getUserid());
		// 邮箱通知活动发布者
		String content = act.getUsername() + ",您好！您发布的活动“" + act.getContent()
				+ "”有了新动态，用户" + u.getUsername() + u.getEmail() + "申请参加您的活动！";
		String emailto = user.getEmail();
		boolean flag = SentEmail.sendEmail(emailto, content);

		if (flag == true) {
			// 保存到用户活动表
			User_activity uc = new User_activity();
			uc.setActid(actid);
			uc.setUserid(userid);
			useractivityService.dao().insert(uc);
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * 获取所有活动信息列表
	 * @return activityList
	 */
	@At
	@Ok("jsp:jsp.index")
	public List<Activity> list(@Param("currentPage") int currentPage,
			@Param("type") String type, HttpServletRequest req) {
		List<Activity> list;
		if (currentPage == 0) {
			currentPage = 1;
		}
		Pager pager = activityService.dao().createPager(currentPage,
				PageUtil.PageSize);
		/* 对带参数检索和布带参数的分别处理 */
		if (type != null) {
			list = activityService.allact(pager, type);
			req.setAttribute("type", type);
		}else {
			list = activityService.allact(pager, null);
			req.setAttribute("type", null);
		}
		/* 将分页信息传到前台 */
		int count = activityService.getCount(Activity.class, type);
		int maxPage = activityService.getMaxCount(count, PageUtil.PageSize);
		req.setAttribute("currentPage", currentPage);
		req.setAttribute("maxPage", maxPage);
		for(Activity a: list){
			System.out.println("execute");
			List<Reply> replylist = new ArrayList<Reply>();
			replylist = replyService.query(Cnd.where("actid", "=", a.getId()).desc("replytime"), null);
			a.setReplylist(replylist);
		}
		return list;
	}

	/**
	 * 获取我的发布的活动信息
	 * 
	 * @param currentPage
	 * @param req
	 * @param session
	 * @return
	 */
	@At
	@Ok("jsp:jsp.mylist")
	@Fail("redirect:/base/list.nut")
	public List<Activity> myAct(@Param("currentPage") int currentPage,
			HttpServletRequest req, HttpSession session) {

		if (session.getAttribute("user") == null) {
			throw new RuntimeException("用户没登陆");
		}
		if (currentPage == 0) {
			currentPage = 1;
		}
		User u = (User) session.getAttribute("user");
		Pager pager = activityService.dao().createPager(currentPage,
				PageUtil.PageSize);
		//获取我发布的活动信息
		List<Activity> list = activityService.getActByUser(u.getId(), pager);
		/* 将分页信息传到前台 */
		int count = activityService.getMyCount(u.getId());
		int maxPage = activityService.getMaxCount(count, PageUtil.PageSize);
		req.setAttribute("currentPage", currentPage);
		req.setAttribute("maxPage", maxPage);
		if(list.size()==0){
			list = null;
		}
		return list;
	}
	/**
	 * 获取我参加的活动信息
	 * @param currentPage
	 * @param req
	 * @param session
	 * @return
	 */
	@At
	@Ok("jsp:jsp.myattlist")
	@Fail("redirect:/base/list.nut")
	public List<Activity> myattAct(@Param("currentPage") int currentPage,
			HttpServletRequest req, HttpSession session){
		if (session.getAttribute("user") == null) {
			throw new RuntimeException("用户没登陆");
		}
		if (currentPage == 0) {
			currentPage = 1;
		}
		User u = (User) session.getAttribute("user");
		Pager pager = activityService.dao().createPager(currentPage,
				PageUtil.PageSize);
		//获取我参加的活动信息
		/*获取我参加的活动id*/
		List<User_activity> user_actlist = useractivityService.query(Cnd.where("userid", "=", u.getId()), pager);
		List<Activity> attendlist = new ArrayList<Activity>();
		for(User_activity uc:user_actlist){
			Activity act = new Activity();
			act = activityService.findById(uc.getActid());
			attendlist.add(act);
		}
		/* 将分页信息传到前台 */
		int count = user_actlist.size();
		int maxPage = activityService.getMaxCount(count, PageUtil.PageSize);
		req.setAttribute("currentPage", currentPage);
		req.setAttribute("maxPage", maxPage);
		if(attendlist.size()==0){
			attendlist = null;
		}
		return attendlist;
	}
	/**
	 * 保存活动信息
	 * 
	 * @param content
	 * @param time
	 * @param type
	 * @param address
	 * @param session
	 */
	@At
	@Ok("redirect:/base/list.nut")
	public void save(@Param("actcontent") String content,
			@Param("acttime") String time, @Param("acttype") String type,
			@Param("actaddress") String address, HttpSession session) {
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
