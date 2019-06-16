<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
       <%@ page import="java.util.*" %>>
       <%@ page import="bean.Teachers" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <% 
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<link href="css/bootstrap.css" rel="stylesheet" type="text/css">
<title>Insert title here</title>
</head>
<body>
<% Teachers tea=(Teachers)request.getAttribute("teacher");%>
<center><p bgcolor="skyblue">欢迎你--<%=tea.getTdept() %>学院，<%=tea.getTname() %>老师</p></center>
 <br>
 <br>
 <div id="titleDiv" style="padding-left:650px">
 	<span style="font-size: 20px;font-weight: bolder;">课程申请</span>
 </div>
  <%session.setAttribute("teacher", tea); %>
 <div style="margin-left:400px">
 <form class="form-horizontal" action="addcourse" method="post">

  <div class="form-group">
    <label for="coursename" class="col-sm-2 control-label">课程名</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" name="coursename" style="width:200px" id="coursename"/>
    </div>
  </div>
  <div class="form-group">
    <label for="coursenumber" class="col-sm-2 control-label">课程编号</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" name="coursenumber" style="width:200px" id="coursenumber" >
    </div>
  </div>
  <div class="form-group">
    <label for="coursetime" class="col-sm-2 control-label">课时</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" name="coursetime" style="width:200px" id="coursetime" >
    </div>
  </div>
  <div class="form-group">
    <label for="coursstart" class="col-sm-2 control-label">开始周数</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" name="coursstart" style="width:200px" id="coursstart" >
    </div>
  </div>
  <div class="form-group">
    <label for="coursend" class="col-sm-2 control-label">结束周数</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" name="coursend" style="width:200px" id="coursend" >
    </div>
  </div>
  
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <button type="submit" class="btn btn-default">申请</button>
      <button type="reset" class="btn btn-default">重置</button>
    </div>
  </div>
</form>
 </div>
 <% String dept=tea.getTdept();%>>
 <div style="padding-left:550px">
	<a href="userlist2.jsp?dept=<%=dept %>">点击此处进入管理教师申请课程界面</a>
</div>

<div style="padding-left:550px">
	<a href="userlist3.jsp?dept=<%=dept %>">点击此处进入学生信息界面</a>
</div>
<form>
</form>
 
 

</body>
</html>