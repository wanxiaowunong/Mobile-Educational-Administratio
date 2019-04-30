<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
       <%@ page import="java.util.*" %>>
       <%@ page import="pao.Teachers" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<% Teachers tea=(Teachers)request.getAttribute("teacher");
%>
<center><p>欢迎你--<%=tea.getTdept() %>学院，<%=tea.getTname() %>老师</p></center>
 <br>
 <br>
 <h1 > 申请开课</h1>
 <h1>查看本专业学生信息</h1>
 
 <form action="addcourse" method="post" >
 <table border="0px" width="100%" height="100%" align="center" cellpadding="0px" cellspacing="0px" bgcolor="white">
<tr>
<td colspan="2">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<font size="5">课程申请</font>&nbsp;&nbsp;&nbsp;</td>
<%session.setAttribute("teacher", tea); %>
</tr>
<tr>
<td width="180px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
<label for="user">课程名</label>
</td>

<td><em style="color: red;">*</em>&nbsp;&nbsp;&nbsp;<input type="text" name="coursename" size="35px" id="coursename" /></td>
</tr>
<tr><td>课程编号</td><td><input type="text" name="coursenumber" size="35px" id="coursenumber" /></td></tr>
<tr>
<td>课时</td><td><em style="color: red;">*</em>&nbsp;&nbsp;&nbsp;<input type="text" name="coursetime" size="35px" id="coursetime" /></td>
</tr>
<tr>
<td>开始周数</td><td><em style="color: red;">*</em>&nbsp;&nbsp;&nbsp;<input type="text" name="coursstart" size="35px" id="coursstart" /></td>
</tr>
<tr>
<td>结束周数</td><td><em style="color: red;">*</em>&nbsp;&nbsp;&nbsp;<input type="text" name="coursend" size="35px" id="coursend" /></td>
</tr>
				
<tr>
<td style="padding-left: 75px;"><input type="submit" value="申请" style="cursor: pointer;" /><input type="reset" value="重置"  style="cursor: pointer;"/>
					</td>
					
				</tr>
				</table>
</form>
<a href="userlist2.jsp">点击此处进入管理教师申请课程界面</a>
<form>
</form>
 
 

</body>
</html>