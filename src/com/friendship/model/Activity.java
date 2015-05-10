package com.friendship.model;



import java.util.List;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Many;
import org.nutz.dao.entity.annotation.One;
import org.nutz.dao.entity.annotation.Table;

@Table(value="activity")
/**
 * 活动实体类
 * @author Administrator
 */
public class Activity {

	@Column
	@Id
	private int id;			//活动id
	@Column
	private String content;		//活动内容
	@Column
	private String address;		//地点
	@Column
	private String username;	//发布人
	@Column
	private String   activitytime; //活动开展时间
	@Column
	private String acttype;		//活动类型
	@Column
	private String  edittime;	//发布时间
	@Column
	private int userid;			//发布用户id
	@One(target=User.class,field="userid")
	private User user;			//发布用户
	@Many(target=Reply.class,field="actid")
	private List<Reply> replylist;
	
	public List<Reply> getReplylist() {
		return replylist;
	}
	public void setReplylist(List<Reply> replylist) {
		this.replylist = replylist;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getActivitytime() {
		return activitytime;
	}
	public void setActivitytime(String activitytime) {
		this.activitytime = activitytime;
	}
	public String getActtype() {
		return acttype;
	}
	public void setActtype(String acttype) {
		this.acttype = acttype;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getEdittime() {
		return edittime;
	}
	public void setEdittime(String edittime) {
		this.edittime = edittime;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
}
