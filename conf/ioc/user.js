var ioc = {
		activityService : {
			type : "com.friendship.service.ActivityService",
			args : [ {
				refer : "dao"
			} ]
		},
		activityAction : {
			type : "com.friendship.web.ActivityAction",
			fields : {
				activityService : {
					refer : "activityService"
				}
			}
		}
};