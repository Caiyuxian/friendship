var ioc = {
		managerService : {
			type : "com.friendship.service.ManagerService",
			args : [ {
				refer : "dao"
			} ]
		},
		//前台Action
		managerAdminAction : {
			type : "com.friendship.web.ManagerAdminAction",
			fields : {
				managerService : {
					refer : "managerService"
				}
			}
		},
};