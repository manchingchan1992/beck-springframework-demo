<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Struts2-Demo</title>
</head>
<body>
<s:form action="/struts/login.action" method="post" >
<s:textfield name="user.loginID" label="登录名："/><br>
<s:password name="user.password" label="密码："/><br>
<s:token name="token" />
<s:submit value="登录"/>
</s:form>
</body>
</html>