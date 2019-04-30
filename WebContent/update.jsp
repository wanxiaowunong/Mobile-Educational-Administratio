<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="updateuser" method="get" >
	<table >
		<input type="hidden" name="userId" value="${user.id }"/>
				<tr>
					<td colspan="2">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<font size="5">用户修改</font>&nbsp;&nbsp;&nbsp;
					</td>
				</tr>
				<tr>
					<td width="180px">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
						<label for="user">姓名</label>
					</td>
					<td>
						<em style="color: red;">*</em>&nbsp;&nbsp;&nbsp;<input type="text" name="username" size="35px" id="username" value="${user.name }"/>
					</td>
				</tr>
					
				<tr>
					<td>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; 密码
					</td>
					<td>
						<em style="color: red;">*</em>&nbsp;&nbsp;&nbsp;<input type="password" name="password" size="35px" id="password" value="${user.password }"/>
					</td>
				</tr>
				
				<tr>
					<td>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;生日
					</td>
					<td>
						<em style="color: red;">*</em>&nbsp;&nbsp;&nbsp;<input type="text" name="birthday" size="35px" id="birthday" value="${user.birthday }"/>
					</td>
				</tr>
				<tr>
					<td style="padding-left: 75px;">
						<input type="submit" value="修改" style="cursor: pointer;" />
						<input type="reset" value="重置"  style="cursor: pointer;"/>
					</td>
					
				</tr>
				</table>
			</form>
		</div>
</body>
</html>