<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
       <%@ page import="java.util.*" %>>
       <%@ page import="dateop.operate" %>
       <%@ page import="pao.Student" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<jsp:useBean id="udao" class="dateop.operate" scope="page"></jsp:useBean>

   <table border="1">
   <tr><th>学号</th><th>姓名</th><th>性别</th><th>专业</th></tr>
   <c:forEach items="${users }" var="u">
   <tr><td>${u.snumber }</td>
   <td>${u.sname }</td><td>${u.ssex }</td><td>${u.sdept }</td></tr>
   </c:forEach>
</table>	



</body>
</html>
