<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="sitemesh-decorator"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="sitemesh-page"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>
<html>
<head>
<jsp:include page="../commons/meta.jsp"></jsp:include>
<sitemesh-decorator:head></sitemesh-decorator:head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<sitemesh-decorator:title></sitemesh-decorator:title>
</head>
<body>
   <div id="top">
      <common:top></common:top>
   </div>
   <div id="menu">
      <common:menu></common:menu>
   </div>
   <div id="main">   
      <sitemesh-decorator:body></sitemesh-decorator:body>
   </div>
   <div id="foot">   
      <common:foot></common:foot>
   </div>
</body>
</html>