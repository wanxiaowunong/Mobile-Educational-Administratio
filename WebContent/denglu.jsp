<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>login</title>
<script>
function onOK()
{
   var text1=document.getElementById("t1");
   var text2=document.getElementById("t2");
   if(text1.value=="")
   {
     alert("用户名不能为空哦！");
     return false;
   }
   else if(text2.value=="")
	   {alert("密码不能为空哦！");return false;}
   else {return true;}
}
</script>
</head>
<body>
<form action="login?action=onOK" method="post" onsubmit="return onOK()">
<table cols="2">
<tr><td colspan="2"><th>会员系统</th> </td>
</tr>
<tr><td>用户名:</td><td><input type="text" placeholder="请输入编号" name="username" id="t1"></td>
</tr>
<tr><td>密码：</td><td><input type="password" placeholder="请输入密码" name="pwd" id="t2"></td></tr>
<tr>
  <td>验证码：</td>
  <td><input type="text " name="code"></td>
  <td><img src="image" ><label onclick="self.location.reload()">--看不清--</label></td>
  </tr>
<tr><td><center><input type="submit" value="提交"></center></td><td><center><input type="reset" value="清空"></center></td>
</tr>

<tr><td colspan="2"><a href="index.jsp">--返回首页--</a></td></tr>
</table>
</form>

</body>
</html>
