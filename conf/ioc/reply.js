var ioc = {
		replyService:{
			type:"com.friendship.service.ReplyService",
			args:[{
				refer:"dao"
			}
		 ]
		},
		replyAction:{
			type:"com.friendship.web.ReplyAction",
			fields:{
				replyService:{
					refer:"replyService"
				}
			}
		}
};