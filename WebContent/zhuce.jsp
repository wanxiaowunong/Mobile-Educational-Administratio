<%@ page language="java" contentType="text/html;pageEncoding=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>register</title>
<script>
function onOK()
{
	   var text1=document.getElementById("t1");
	   var text2=document.getElementById("t2");
	   var text3=document.getElementById("t3");
	   if(text1.value=="")
	   {
	     alert("用户名不能为空哦！");
	     return false;
	   }
	   else if(text2.value==""||text3.value=="")
		   {alert("密码不能为空哦！"); return false;}
		   
	   else if(text2.value!=text3.value)
		   {alert("两次密码输入不同哦！");
		   return false;
		   }
	   else {return true;}
	}
	</script>
</head>
<body>
<form action="regiser?action=onOK" method="get" onsubmit="return onOK()">
<table cols="2">
<tr><td colspan="2"><th>会员系统</th> </td>
</tr>
<tr><td>用户名:</td><td><input type="text" placeholder="请输入字母" name="username" id="t1"></td>
</tr>
<tr><td>密码：</td><td><input type="password" placeholder="请输入6-12位数字" name="pwd" id="t2"></td></tr>
<tr><td>确认密码：</td><td><input type="password" placeholder="请输入6-12位数字" name="pwd1" id="t3"></td></tr>
<tr><td>生日:</td><td><input type="text" placeholder="请输入字母" name="birthday" id="t4"></td></tr>
<tr><td><input type="submit" value="注册"></td><td><input type="reset" value="清空"></td>
</tr>
 <tr><td colspan="2"><a href="index.jsp">--返回主页--</a></td></tr>
</table>
</form>
</body>
</html></html>
