<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
       <%@ page import="java.util.*" %>>
       <%@ page import="dateop.operate" %>
       <%@ page import="pao.Courseapply" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<jsp:useBean id="udao" class="dateop.opcourseapply" scope="page"></jsp:useBean>
<%
	List<Courseapply> users=udao.select(null,null);
request.setAttribute("users", users);
%>
   <table border="1">
   <tr><th>教师姓名</th><th>课程名</th><th>上课地点</th><th>上课时间</th><th colspan="2">操作</th></tr>
   <c:forEach items="${users }" var="u">
   <tr><td>${u.tname }</td>
   <td>${u.cname }</td><td>${u.courseplace }</td><td>${u.coursetime }</td><td><a href="update?userId=${u.id}&coursetime=${u.coursetime}&courseplace=${u.courseplace}&flag=1">同意</a>
   </td><td><a href="update?userId=${u.id }&flag=0">驳回</a></td></tr>
   </c:forEach>
</table>	

