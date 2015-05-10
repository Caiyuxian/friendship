<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>大学城高校联谊平台</title>
<script type="text/javascript" src="../js/jquery-1.11.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="../css/reset.css">
<style type="text/css">
html{
	height: 100%;
}
body{
	background: url(../images/login-bg.jpg);
	height: 100%;
}
/*登陆*/
#login{
	width: 353px;
	height: 262px;
	border-radius: 18px;
	background: #fff;
	opacity: 0.8;
	margin-left: 10px;
}
#inputLogin,#inputPassword{
	height: 36px;
	width: 300px;
	margin-top: 25px;
	margin-left: 23px;
	background: #fff;
	border-radius: 18px;
	color:#808080;
	font-size: 1.15em;
	border: 1px solid #ccc;
	padding-left: 5px;
}
#login a{
	display: inline-block;
	width: 140px;
	height: 43px;
	line-height: 43px;
	text-decoration: none;
	font-size: 1.15em;
	color: white;
	border-radius: 15px;
	text-align: center;
}
#aLogin{
	background:#39c;
}
#aSign{
	background: #F06698;
}
/*注册*/
#register{
	width: 353px;
	height: 320px;
	border-radius: 18px;
	background: #fff;
	opacity: 0.8;
	margin-left: 10px;
	display: none;
}
#register input{
	height: 36px;
	width: 300px;
	margin-top: 5px;
	margin-left: 23px;
	background: #fff;
	border-radius: 18px;
	color:#808080;
	font-size: 1.15em;
	border: 1px solid #ccc;
	padding-left: 5px;
}
#sure{
	display: block;
	width: 140px;
	height: 43px;
	line-height: 43px;
	text-decoration: none;
	font-size: 1.15em;
	color: white;
	border-radius: 15px;
	text-align: center;
	background:#39c;
	margin-left: 100px;
	margin-top: 5px;
}
</style>
<script type="text/javascript">
$(document).ready(function(){
	//登录
		$("#aLogin").click(function(){ 
		if($("#inputLogin").val().length == 0){
			alert("用户名不能为空！");
			return false;
		}
		if($("#inputPassword").val().length == 0){
			alert("密码不能为空！");
			return false;
		}
		$.post("login.nut",
		{
			username:$("#inputLogin").val(),
			password:$("#inputPassword").val(),
		},
		function(data){
			console.log(data)
			if(data == '"1"'){
				alert("用户不存在！");
			}
			else if(data == '"0"'){
				alert("密码错误！");
			}else if(data=='"2"'){
				alert("可能你的帐号发步了违反规定的内容，目前帐号已经被封，有什么问题请联系站长");
			}
			else{
				window.location.href = "list.nut";
			} 
		});
	});
	//注册
	$("#aSign").click(function(){
		$("#login").fadeOut("slow",function(){
			$("#register").fadeIn();
		});
	});
	//检验用户名是否已经注册
	$("#username").blur(function(){
		
	 	if($("#username").val()=="")
			return false;
		$.post("isUserExit.nut",
		{
			username:$("#username").val()
		},
		function(data){
			var div = document.getElementById("isUserExit");
			if(data=="true"){				
				div.innerHTML = "**此用户名已经被注册";
				$("#username").focus();
			}else if(data=="false"){
				div.innerHTML = "此用户名可用";
			}
		}) 
	});
	$("#sure").click(function(){
		//输入框不能为空
		var length = $("#register input").length;
		for(var i = 0; i < length; i ++){
			if($("#register input").eq(i).val() == ""){
				alert($("#register input").eq(i).attr("placeholder") + "不能为空！");
				break;
			}
		}
		//验证联系方式
		var pho = /^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8}$/;//手机号码
		if(!(pho.test($("#connect").val()))){
            alert("请填写正确的联系方式！")
            return false;
        }
		//验证邮箱
		var reg = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
		if(!(reg.test($("#mail").val()))){
			alert("请输入正确的邮箱！");
			return false;
		}
		//密码确认
		if($("#password").val() != $("#repassword").val()){
			alert("两次密码输入不一致！");
			return false;
		}
		
		if(true){
		alert("注册成功，立即登录");
		//注册登录
		$("#registerForm").submit();
		}
		
	});
});
</script>
</head>
<body>
	<div style="padding-top:85px;">
		<!-- 登陆 -->
		<div id="login">
			<form id="loginForm">
				<input name = "username" id="inputLogin" type="text" placeholder="请输入邮箱或账号">
				<input name = "password" id="inputPassword" type="password" placeholder="请输入登陆密码">
				<div style="text-align:center;padding-top:27px;">
					<a id="aLogin" href="javascript:void(0);">登陆</a>
					<a id="aSign" href="javascript:;">注册</a>
				</div>
			</form>
		</div> 
		<!-- 注册 -->
		<div id="register">
			<img style="margin-left: 26px;margin-top: 10px;" src="../images/login and register.png">
			<form id = "registerForm" action = "register.nut" method = "post">
				<!-- <input name = "user_school" id="school" type="text" placeholder="学校">
				<input name = "user_speciality" id="speciality" type="text" placeholder="专业">
				<input name = "user_grade" id="grade" type="text" placeholder="年级">
				<input name = "user_class" id="class" type="text" placeholder="班级"> -->
				<input name = "username" id="username" type="text" placeholder="昵称">
				<div id="isUserExit"style="clear:both;padding-left:30px;color:red;"></div>
				<input name = "phone" id="connect" type="text" placeholder="联系方式(手机号码)">
				<input name = "email" id="mail" type="text" placeholder="邮箱（登陆名为邮箱名）">
				<input name = "password" id="password" type="password" placeholder="登陆密码">
				<input id="repassword" type="password" placeholder="确认密码">
				<a id="sure" href="javascript:;" onclick="return ">确认</a>
			</form>
		</div>
	</div>
</body>
</html>