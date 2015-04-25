<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="../../js/jquery-1.11.1.min.js"></script>
<title></title>
<style type="text/css">
  body {
  margin-left: 0px;
  margin-top: 0px;
  margin-right: 0px;
  margin-bottom: 0px;
  background-color: #EFF6FF;
}
div,p,table,tr,td{
  padding: 0;
  margin: 0;
}

</style>
<script type="text/javascript">
$(document).ready(function(){
  $(".announceA").click(function(){
    var txt = $(this).next().val();
    $.post("/University_Followship/servlet/delete_adminAty",{
      aty_id:txt
    },function(data){
      if(data == 1){
        alert("删除成功！");
      }else{
        alert("删除失败！");
      }
    })
    $(this).parent().parent().remove();
  })
})
window.onload=function(){
  if (location.href.indexOf("?xyz=")<0)
 {
 location.href=location.href+"?xyz="+Math.random();
 }
}
</script>
</head>

<body>
  <div style="background-color:#16ADD8;color:white;height:30px;font-size:14px;line-height:30px;">
    <p>大学城高校联谊平台管理>用户发布活动管理</p>
  </div>
  <div>
    <form action="#" method="post">
      <table width="100%" style="text-align:center" cellspacing="1" cellpadding="0" bgcolor="#464646" >
        <tr>
          <td height="35" width="10%" bgcolor="#E0FCFA" >序号</td>
          <td height="35" width="40%" bgcolor="#E0FCFA" >活动内容</td>
          <td height="35" width="15%" bgcolor="#E0FCFA" >发布时间</td>
          <td height="35" width="10%" bgcolor="#E0FCFA" >发布人</td>
          <td height="35" width="10%" bgcolor="#E0FCFA" >操作</td>
        </tr>
        <!--  -->
		<c:forEach items="${obj}" var="act" varStatus="sta">
        <tr>
          <td height="35" width="10%" bgcolor="#FFFFFF" >${sta.index+1 }</td>
          <td height="35" width="35%" bgcolor="#FFFFFF" >${act.content }</td>
          <td height="35" width="20%" bgcolor="#FFFFFF" >${act.edittime }</td>
          <td height="35" width="15%" bgcolor="#FFFFFF" >${act.username }</td>
          <td height="35" width="30%" bgcolor="#FFFFFF" >
            <a class="announceA" href="javascript:;">删除</a>
            <input type="hidden" value=${act.id }/>
          </td>
        </tr> 
    	</c:forEach>
        <!--  -->
      </table>
      <table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="6"></td>
        </tr>
        <tr>
          <td height="33"><table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="right-font08">
              <tr>
                <td width="50%"><span class="right-text09"></span> 第${currentPage }页 | 共${maxPage }页 <span class="right-text09"></span></td>
                <td width="49%" align="right">[<a href="actlist.nut?currentPage=1" class="right-font08">第一页</a> | <c:if test="${currentPage!=null&&currentPage!=1 }"><a href="actlist.nut?currentPage=${currentPage-1 }" class="right-font08">上一页</a> | </c:if><c:if test="${currentPage!=null&&currentPage!=maxPage }"><a href="actlist.nut?currentPage=${currentPage+1 }" class="right-font08">下一页</a> | </c:if><a href="actlist.nut?currentPage=${maxPage}" class="right-font08">最后一页</a>]</td>
                <td width="1%"><table width="20" border="0" cellspacing="0" cellpadding="0">
                </table></td>
              </tr>
          </table></td>
        </tr>
      </table>
    </form>
  </div>
</body>
</html>
