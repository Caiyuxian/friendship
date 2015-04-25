var ioc = {
		userService : {
			type : "com.friendship.service.UserService",
			args : [ {
				refer : "dao"
			} ]
		},
		userAction : {
			type : "com.friendship.web.UserAction",
			fields : {
				userService : {
					refer : "userService"
				}
			}
		},
		userAdminAction : {
			type : "com.friendship.web.UserAdminAction",
			fields : {
				userService : {
					refer : "userService"
				}
			}
		},
};