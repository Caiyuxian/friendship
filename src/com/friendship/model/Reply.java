package com.friendship.model;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.One;
import org.nutz.dao.entity.annotation.Table;

/**
 * 活动评论类
 * @author Administrator
 */
@Table(value="reply")
public class Reply {
	@Id
	@Column
	private int id;
	@Column
	private String username;
	@Column
	private String replytime;
	@Column
	private String content;
	@Column
	private int actid;
	@One(target=Activity.class,field="actid")
	private Activity activity;
	
	public int getActid() {
		return actid;
	}
	public void setActid(int actid) {
		this.actid = actid;
	}
	public Activity getActivity() {
		return activity;
	}
	public void setActivity(Activity activity) {
		this.activity = activity;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getReplytime() {
		return replytime;
	}
	public void setReplytime(String  replytime) {
		this.replytime = replytime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
