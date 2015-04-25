<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="../../js/jquery-1.11.1.min.js"></script>
<title>大学城高校联谊平台后台管理</title>
<style type="text/css">
body,div,p,img,form,input,ul,li,a{
  margin: 0;
  padding: 0;
}
body{
  background-image: url(../../images/admin/Logininterface_body_bg_p.png);
}
.img{
  margin: 0 auto;
  background-image: url(../../images/admin/Logininterface_body_picture_p.png);
  width: 1025px;
  /*height: 891px;*/
  padding-top: 20px;
}
.logo{
  background-image: url(../../images/admin/logo_03.png);
  width: 681px;
  height: 126px;
  margin: 0 auto;
}
.table{
  background-image: url(../../images/admin/Logininterface_body_bg_p.png);
  margin: 369px auto 0;
}
.table_content{
  width: 630px;
  margin: 0 auto;
}
.td_left{
  text-align: right;
  color:#BADCF8; 
  font-size: 20px;
  font-family: "MircoftYahei";
}
table input{
  height: 20px;
  font-size: 15px;
}
.table a{
  color: #9CB6F3;
  font-size: 14px;
}
.table a:hover{
  color: red;
}
.enter{
  width: 148px;
  height: 122px;
  display: block;
  float: left;
  background-image: url("../../images/admin/body_button_normal_p.png");
}
.enter:hover{
  background-image: url("../../images/admin/body_button_click_p.png");
}
.enter:active{
  background-image: url("../../images/admin/body_button_active_p.png");
}
form input{
  height: 30px;
  border-radius: 5px;
  border:none;
}
#formDiv{
  float:left;
  margin-left:340px;
  margin-right:10px;
}
#formDiv p{
  height:30px;
  line-height:30px;
  font-size:18px;
  color:#fff;
}
</style>
<script type="text/javascript">
$(document).ready(function(){
	$("#enter").click(function(){
		$("#adminForm").submit();
	})
})
</script>
</head>
<body>
  <div>
    <div class="img">
      <div class="logo"></div>
      <div class="table">
          <div class="table_content">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
            </table>
        </div>
      </div> 
      <!-- 登陆表单 -->
      <form id="adminForm" action="/University_Followship/servlet/admin_pass">
        <div id="formDiv">
          <p>管理员账号：</p>
          <input name="admin_name" type="text">
          <p>登陆密码：</p>
          <input name="admin_password" type="password">
        </div>
        <a id="enter" href="javascript:;" class="enter"></a>
        <div style="clear:both;height:20px;"></div>
      </form>
    </div>
    
  </div>
</body>
</html>