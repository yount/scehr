<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>注册</title>
</head>
<body>
	<form action="<%=basePath %>register.do">
		<div class="inputArea"><span class="nameText">用户名：</span><input type="text" id="userName"  placeholder="用户名/邮箱/手机号码"/></div>
        <div class="inputArea"><span class="nameText">验证码：</span><input type="password" id="passWord"  placeholder="验证码"/></div>
	</form>
</body>
</html>