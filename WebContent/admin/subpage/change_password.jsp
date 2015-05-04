<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="../../js/jquery-1.11.1.min.js"></script>
<title>密码修改</title>
<style type="text/css">
body{
  margin: 0;
  padding: 0;
  background-color: #EFF6FF;
}
div,p,a{
  margin: 0;
  padding: 0;
}
.main{
  width: 400px;
  margin: 0 auto;
  margin-top: 20px;
  /*background-color: #1ADEE0;*/
  border: 1px solid #7AD5F1;
}
a{
  float: right;
  display: block;
  height: 34px;
  width: 83px;
  background: url(../../images/admin/body_button_ascertain_p.png);
}
a:active{
  background: url(../../images/admin/body_button_ascertain_active_p.png);
}
table{
  text-align: right;
}
td{
  height: 60px;
  font-size: 20px; 
}
input{
  font-size: 18px;
}
</style>
<script type="text/javascript">
$(document).ready(function(){
  $("#passwordSure").click(function(){
	  
	if($("#newPassword").val()==""|| $("#re_password").val()==""||$("#oldPassword").val()==""){
		alert("请填写所有信息");
		return false;
	}  
    if($("#newPassword").val() != $("#re_password").val()){
      alert("两次密码不一致！");
    }else{
      $.post("/friendship/admin/changePass.nut",
      {
    	id:$("#id").val(),
      	oldpass:$("#oldPassword").val(),
      	newpass:$("#newPassword").val()
      },
      function(data){
      console.log(data)
      	if(data == 1){
      		alert("修改成功！");
      	}else if(data == 0){
      		alert("原始密码不正确！");
      	}
      })
    }
  })
})
</script>
</head>
<body>
  <div style="background-color:#16ADD8;color:white;height:30px;font-size:14px;line-height:30px;">
    大学城高校联谊平台管理>密码修改
  </div>
  <div class="main">
  <form id="changeForm">
    <table>
	  <input type="hidden" id="id"name="id" value="${admin.id}"/>
      <tr>
        <td width="40%">旧密码：</td>
        <td width="60%"><input name="oldPassword" id="oldPassword" type="password" size="20" /></td>
      </tr>
      <tr>
        <td>新密码：</td>
        <td><input name="newPassword" id="newPassword" type="password" id="input1" size="20" /></td>
      </tr>
      <tr>
        <td>确认新密码：</td>
        <td><input id="re_password" type="password" id="input2" size="20"/></td>
      </tr>
      <tr>
        <td></td>
        <td><a id="passwordSure" href="javasctipt:;" id="check"></a></td>
      </tr>
    </table>
    
  </form>
  </div>
</body>
</html>
