package com.friendship.service;

import org.nutz.dao.Dao;
import org.nutz.service.IdNameEntityService;

import com.friendship.model.Reply;

/**
 * 活动评论逻辑类
 * @author Administrator
 *
 */

public class ReplyService extends IdNameEntityService<Reply>{
	public ReplyService(Dao dao){
		super(dao);
	}
}
