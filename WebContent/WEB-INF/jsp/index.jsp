<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>大学城高校联谊平台</title>
<script type="text/javascript" src="../js/jquery-1.11.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="../css/reset.css">
<link rel="stylesheet" type="text/css" href="../css/Font-Awesome-3.2.1/css/font-awesome.min.css">
<style type="text/css">
body{
	background: url(../images/body_bg.jpg) repeat-x;
	background-color: #b4daf0;
}
/*导航栏*/
header{
	width: 100%;
	height: 55px;
	border-top: 5px solid #39c;
	background: white;
	position: fixed;
	top: 0;
	box-shadow: 0 1px 5px #888888;
}
/*我要评论，参加*/
.wantCom,.wantJoin{
	float:right;
	cursor: pointer;
	color:#39c;
	margin-right:5px;
	margin-left:5px;
}
/*评论框*/
#comment{
	position:absolute;
	z-index:2;
	background:white;
	width:480px;
	height:250px;
	top:50%;
	left:50%;
	margin:-150px 0 0 -250px;
	border-radius:25px;
	padding:10px;
	display:none;
}
#comment textarea{
	width: 450px;
	margin-left: 10px;
	margin-top: 10px;
	padding: 5px;
	height: 150px;
	resize:none;
}
#comment a{
	display:inline-block;
	text-decoration:none;
	color:white;
	width:50px;
	height:30px;
	line-height:30px;
	background:#39c;
	border-radius:4px;
}
#headerNav{
	width: 960px;
	margin: 0 auto;
}
#headerNav a{
	text-decoration: none;
	color: #ccc;
	display: inline-block;
	width: 18%;
	height: 55px;
	line-height: 55px;
	text-align: center;
}
#headerNav a:hover{
	color: #39c;
}
#headerNav input{
	border-radius: 15px;
	height: 30px;
	border: 1px solid #ccc;
	padding-left: 5px;
	width: 200px;
}
#main{
	width: 960px;
	min-height: 600px;
	margin: 0 auto;
}
/*发布活动*/
#publish{
	width: 770px;
	padding: 5px;
	margin: 0 auto;
	margin-top: 10px;
	background: white;
}
#publish textarea{
	width: 756px;
	height: 58px;
	padding: 5px;
	font-size: 14px;
	color: #333;
	resize: none;
}
#aPublish{
	text-decoration: none;
	color: white;
	display: inline-block;
	width: 80px;
	background: #FFC09F;
	font-size: 15px;
	text-align: center;
	border-radius: 3px;
}
#publishTime{
	padding-left: 5px;
}
/*全部活动*/
#content{
	width: 770px;
	padding: 5px;
	margin: 0 auto;
	margin-top: 10px;
	/*background: white;*/
}
.pubisher{
	background: white;
	padding: 10px;
	margin-top: 10px;
}
.pubisher p{
	color: #39c;
	font-size: 14px;
}
.answer{
	background:white;
	padding:10px;
}
/*消息*/
#news{
	width: 300px;
	min-height: 200px;
	border: 1px solid #F0F0F0;
	box-shadow: 1px 2px 5px #000;
	background: white;
	position: absolute;
	top: 59px;
	left: 484px;
	border-radius: 10px;
	display: none;
}
#newsTop{
	width:0px;
	height:0px;
	border-left:15px solid transparent;  /* left arrow slant */
	border-right:15px solid transparent; /* right arrow slant */
	border-bottom:15px solid #F0F0F0; /* bottom, add background color here */
	margin-top: -15px;
	margin-left: 135px;
}
.jioner{
	height: 40px;
	line-height: 40px;
	padding-left: 10px;
	font-size: 16px;
	color: #39c;
}
#newsBot{
	position: absolute;
	bottom: 0;
	width: 180px;
	border-top: 1px solid #ccc;
	padding-left: 120px;
	padding-top: 5px;
}
#newsBot a{
	display: block;
	width: 50px;
	height: 26px;
	line-height: 26px;
	border-radius: 5px;
	background: #39c;
	text-decoration: none;
	color: white;
	text-align: center;
}
/*尾部*/
footer{
	text-align: center;
	background-color: white;
	height: 40px;
	line-height: 40px;
}
footer a{
	text-decoration: none;
	color: #808080;
}
#bg{
	position: absolute;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	background: #000;
	opacity: 0.5;
	display: none;
}
/*底部*/
.dibu{
	font-weight: bold;
 	font-size: 18px;
	color:#000000;
	font-family:宋体;
}
</style>
<script type="text/javascript">
$(document).ready(function(){
	// 消息
	$("#aNews").click(function(){
		if($("#news").css("display") == "block"){
			$("#news").fadeOut();
		}else{
			$("#news").fadeIn();
		}
		return false;
	});
	$("#newsBot").find("a").click(function(){
		$("#news").fadeOut();
		return false;
	});
	//参加活动
	$(".wantJoin").click(function(){
		if($("#username").val()== ""){
			alert("你还没有登录，请先登录");
			return false;
		}
		if($(this).css("color") == "rgb(51, 153, 204)"){
			$(this).css("color","#FE9660");
			$.post("${base}/base/attendAct.nut",
			{
				userid:$("#userid").val(),
				actid:$(this).parent().find("input").val(),
			},
			function(data){
				if(data==1){
					alert("已经发送邮件通知该用户！")
				}else if(data==0){
					alert("邮件发送失败！");
				}else if(data==2){
					alert("您已经点击参加，请勿重复操作！");
				}
			}) 
		}
	})
	//发布活动
	$("#aPublish").click(function(){
		if($("#username").val()== ""){
			alert("你还没有登录，请先登录");
			return false;
		}
		if($("#activityContent").val() == ""){
			alert("发布活动内容不能为空！")
			return false;
		}
		if($("#publishTime").val() == ""){
			alert("请输入举办活动的时间！");
			return false;
		}
		if($("#publishAddress").val() == ""){
			alert("请输入举办活动的地点！");
			return false;
		}
		$("#publishForm").submit();
	});
	//搜索
	$("#shousuo").click(function(){
		
		if($("#shousuocontent").val()==""){
			alert("dd");
			return false;
		}
		$("#souform").submit();
	});
	//评论
	$(".wantCom").click(function(){
		if($("#username").val()==""){
			alert("您还未登录，请先登录！");
			return false;
		}
		$("#bg").css("height",$("html").height());
		$("#bg").fadeIn();
		var sTop=$(window).scrollTop();
		var cTop=$(window).height()/2;
		$("#comment").css(
				"top",sTop+cTop+"px"
		).fadeIn();
		var val = $(this).next().attr("value");
		$("#commentInput").attr("value",val);
	})
	$("#sure").click(function(){
		$("#commentForm").submit();
		$("#comment").fadeOut();
	})
	$("#quit").click(function(){
		$("#bg").fadeOut();
		$("#comment").fadeOut();
	})
});
</script>
</head>
<body>
	<%@include file="/commons/header.jsp" %>
	<div style="height:60px;"></div>
	<div id="main">
		<!-- 发布活动 -->
		<div id="publish">
			<p style="font-family: '隶书';font-size:20px;color:#39c;">有活动要发布？</p>
			<form id = "publishForm" action="save.nut" method="post">
				<input type="hidden" id="username" value="${user.username }"/>
				<input type="hidden" id="userid" value="${user.id }"/>
				<textarea name="actcontent" id = "activityContent"></textarea>
				<div style="float:right;color:#FFC09F;height:30px;line-height:30px;">
					<label>活动类型</label>
					<select name="acttype" id = "typeSelect">
						<option>电影</option>
						<option>运动</option>
						<option>展览</option>
						<option>公益</option>
						<option>旅行</option>
						<option>音乐</option>
					</select>
					<label>举办活动时间：</label>
					<input id="publishTime" type="text" placeholder="xx-xx-xx" name="acttime">
					<label>举办活动地点：</label>
					<input id="publishAddress" type="text" placeholder="例：中心湖" name="actaddress">
					<a id="aPublish" href="javascript:;">发布活动</a>
				</div>
				<div style="clear:both;"></div>
			</form>
		</div>
		<!-- 首页全部活动 -->
		<div id="content">
			<c:forEach items="${obj}" var="act">
			 <div class="pubisher">
				<p><i class="icon-group icon-2x"></i><span>${act.username}</span></p>
				<p style="padding-left:30px;">活动简介：
					<span>活动类型：</span><span>${act.acttype}</span>
					<span>活动地点：</span><span>${act.address}</span>
					<span>举办时间：</span><span>${act.activitytime}</span>
				</p>
				<p style="padding-left:30px;">
					<span style="color:red"><div>${act.content}</div></span>
				</p>
				 <span>发布时间：</span><span>${act.edittime}</span>
				<div class="wantJoin"><i class=" icon-thumbs-up icon-2x"></i>我要参加</div>
				<div class="wantCom"><i class="icon-tag icon-2x"></i>我要评论</div>
				<input type="hidden" id="actid" value="${act.id }"/>
				<div style="clear:both;"></div>
			</div>
				<c:forEach items="${act.replylist}" var="reply" >
				<div class="answer">
					<p style="color: #39c; font-size: 15px;padding-left:25px;">
						<span><i class="icon-external-link icon-2x"></i>${reply.username }&nbsp;&nbsp;</span><span>评论:</span>
					</p>
					<p style="padding-left: 30px;">${reply.content }</p>
					<span style="padding-left: 30px;">评论时间：${reply.replytime }</span>
				</div>
				</c:forEach>
			</c:forEach>
		</div>
		<div class="dibu" align="center">
			<!-- 1 -->
			<c:if test="${type!=null }">
				<a href="list.nut?currentPage=1&type=${type}">首页</a>&nbsp;&nbsp;
			</c:if>
			<c:if test="${type==null }">
				<a href="list.nut?currentPage=1">首页</a>&nbsp;&nbsp;
			</c:if>
			<!-- 2 -->
			<c:if test="${type!=null }">
				<c:if test="${currentPage!=1&&currentPage!=null}"><a href="list.nut?currentPage=${currentPage-1}&type=${type}">上一页&nbsp;&nbsp;</a></c:if>
			</c:if>
			<c:if test="${type==null }">
				<c:if test="${currentPage!=1&&currentPage!=null}"><a href="list.nut?currentPage=${currentPage-1}">上一页&nbsp;&nbsp;</a></c:if>
			</c:if>
			<!-- 3 -->
			<c:if test="${type!=null }">
				<c:if test="${currentPage!=maxPage&&currentPage!=null}"><a href="list.nut?currentPage=${currentPage+1}&type=${type}">下一页&nbsp;&nbsp;</a></c:if>
			</c:if>
			<c:if test="${type==null }">
				<c:if test="${currentPage!=maxPage&&currentPage!=null}"><a href="list.nut?currentPage=${currentPage+1}">下一页&nbsp;&nbsp;</a></c:if>
			</c:if>
			<!-- 4 -->
			<c:if test="${type!=null }">
				<a href="list.nut?currentPage=${maxPage}&type=${type}">末页</a>
			</c:if>
			<c:if test="${type==null }">
				<a href="list.nut?currentPage=${maxPage}">末页</a>
			</c:if>
			<h6 style="font-size:14px;">第${currentPage}页|共${maxPage }页</h6>
		</div>
		<!--评论框-->
		<div id = "comment">
			<p style = "font-size: 17px;padding-left: 10px;color: #39c;">发表评论</p>
			<form id = "commentForm" action = "${base }/base/addReply.nut">
				<input name = "username" type = "hidden" value = ${user.username }>
				<input name = "actid" id = "commentInput" type="hidden" value="">
				<input type="hidden"name="currentPage" value="${currentPage }"/>
				<textarea name="content" rows="" cols=""></textarea>
				<div style="text-align:center;padding-top:10px;">
					<a id = "sure" href = "javascript:;">确认</a>
					<a id = "quit" href = "javascript:;">取消</a>
				</div>
			</form>
		</div>
	</div>
<%@include file="/commons/footer.jsp" %>	
<div id="bg"></div>
</body>
</html>