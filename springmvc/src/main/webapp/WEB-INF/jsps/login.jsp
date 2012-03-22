<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome To Web OA System</title>
</head>
<body>
<form action="${ctx}/j_spring_security_check" method="post">
  Login ID : <input type="text" name="j_username" /><br/>
  Password : <input type="password" name="j_password" /><br/>
  <input type="submit" value="Login" /> 
</form>
</body>
</html>