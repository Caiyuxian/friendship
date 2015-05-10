package com.friendship.web;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.friendship.model.Reply;
import com.friendship.service.ReplyService;
@At("/base")
@IocBean
@InjectName
public class ReplyAction {
	
	private ReplyService replyService;
	SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/**
	 * 用户评论
	 * @param content
	 * @param username
	 * @param id
	 * @param currentPage
	 * @return
	 */
	@At
	@Ok("redirect:/base/list.nut?currentPage=${p.currentPage}")
	public int addReply(@Param("content")String content,
			@Param("username")String username,@Param("actid")int id,
			@Param("currentPage")int currentPage ){
		Reply r = new Reply();
		r.setActid(id);
		r.setContent(content);
		r.setUsername(username);
		r.setReplytime(sf.format(new Date()));
		replyService.dao().insert(r);
		return currentPage;
	}
}
