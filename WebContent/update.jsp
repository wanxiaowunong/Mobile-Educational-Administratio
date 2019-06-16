<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
        <%@ page import="bean.Student" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
    String sname=request.getParameter("sname");
    String snumber=request.getParameter("snumber");
    String pwd=request.getParameter("pwd");
    String dept=request.getParameter("dept");
    String sex=request.getParameter("ssex");
    System.out.println(sex);
%>	
<form action="updateuser" method="get" >
	<table >
				<tr>
					<td colspan="2">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<font size="5">用户修改</font>&nbsp;&nbsp;&nbsp;
					</td>
				</tr>
				<tr>
					<td width="180px">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
						<label for="user">学号</label>
					</td>
					<td>
						<em style="color: red;">*</em>&nbsp;&nbsp;&nbsp;
						<input type="text" name="snumber" readonly size="35px" id="username" value=<%=snumber %>>
					</td>
				</tr>
				<tr>
					<td width="180px">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
						<label for="user">姓名</label>
					</td>
					<td>
						<em style="color: red;">*</em>&nbsp;&nbsp;&nbsp;
						<input type="text" name="sname"  readonly size="35px" id="username" value=<%=sname %>>
					</td>
				</tr>
					
				<tr>
					<td>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; 密码
					</td>
					<td>
						<em style="color: red;">*</em>&nbsp;&nbsp;&nbsp;
						<input type="password" name="pwd" size="35px" id="password" value=<%=pwd %>>
					</td>
				</tr>
				
				<tr>
					<td>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;专业
					</td>
					<td>
						<em style="color: red;">*</em>&nbsp;&nbsp;&nbsp;
						<input type="text" readonly name="dept" size="35px" id="birthday" value=<%=dept %>>
					</td>
				</tr>
				<tr>
					<td width="180px">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
						<label for="user">性别</label>
					</td>
					<td>
						<em style="color: red;">*</em>&nbsp;&nbsp;&nbsp;
						<input type="text" readonly name="sex" size="35px" id="sex" value=<%=sex %>>
					</td>
				</tr>
				<tr>
					<td style="padding-left: 75px;">
						<input type="submit" value="修改"  />
						<input type="reset" value="重置"  />
					</td>
					
				</tr>
				</table>
			</form>
		</div>
</body>
</html>