<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新闻编辑</title>
<style type="text/css">
  body {
  margin-left: 0px;
  margin-top: 0px;
  margin-right: 0px;
  margin-bottom: 0px;
  background-color: #EFF6FF;
}
div,table,p,form{
  padding: 0;
  margin: 0;
}
</style>
		<link rel="stylesheet" href="../../css/default.css" />
		<script charset="utf-8" src="../../js/kindeditor-min.js"></script>
		<script charset="utf-8" src="../../js/zh_CN.js"></script>
		<script>
			var editor;
			KindEditor.ready(function(K) {
				editor = K.create('textarea[name="content"]', {
					resizeType : 1,
					allowPreviewEmoticons : false,
					allowImageUpload : false,
					items : [
						'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
						'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
						'insertunorderedlist', '|', 'emoticons', 'image', 'link']
				});
			});
		</script>
	</head>
	<body>
		<div style="background-color:#16ADD8;color:white;height:30px;font-size:14px;line-height:30px;">
    <p>大学城高校联谊平台管理>管理员公告</p>
  </div>
  <div class="article">
    <form action="/University_Followship/servlet/report" method="post">
    
      <table>
        <tr>
          <td>标题：</td>
          <td><input name="report_name" type="text" maxlength="26" id="title" style="width:100%"/></td>
        </tr>
        <tr>
          <td>内容：</td>
          <td>
            <textarea name="content" style="width:700px;height:200px;visibility:hidden;"></textarea>
          </td>
        </tr>
        <tr>
          <td></td>
          <td>
            <input type="submit" class="button" value="提交" style="float:right;" /> 
          </td>
        </tr>
      </table>
      
    </form>
  </div>
	</body>
</html>
