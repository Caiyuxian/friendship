<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
<header>
		<div id="headerNav">
			<a href="list.nut?currentPage=1" style="color: #39c;"><i class="icon-home icon-2x"></i>首页</a>
			<a id="aNews" href="javascript:0;"><i class="icon-comments icon-2x"></i>消息</a>
			<c:if test="${user==null }">
				<a href="tologin.nut"><i class="icon-user icon-2x"></i>登录</a>
			</c:if>
			<c:if test="${user!=null&&user!='' }">
				<a href="myAct.nut"><i class="icon-user icon-2x"></i><c:out value="${user.username}"></c:out>个人主页</a>
				<a id="exit" href="logout.nut"><i class="icon-coffee icon-2x"></i>退出</a>
			</c:if>
			<form  id="souform" action="list.nut?currentPage=1" method="post" style="display: inline;">
			<input type="text" name="type" id="shousuocontent" placeholder="活动类型"><i id="shousuo" style="margin-left:-20px;color:#ccc;cursor: pointer;" class="icon-search icon-large"></i>
			</form>
		</div>
		<!-- 消息 -->
		<div id="news">
			<div id="newsTop"></div>
			<div class="jioner">
				<i class="icon-comment-alt"></i>
				<span>参加者一</span>
				<i class="icon-exchange"></i>
				<select>
					<option>同意参加</option>
					<option>删除该参与者</option>
				</select>
			</div>
			<div id="newsBot">
				<a href="javascript:0;">确定</a>
			</div>
		</div>
	</header>