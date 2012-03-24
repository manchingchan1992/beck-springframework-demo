<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>
<html>
<head>
<title>OA-MSG-003</title>
</head>
<body>
	<div class="boxTitleBar">
		<div class="contenttitle">收件箱</div>
	</div>
	<div class="emptyBlock">
	<form:form commandName="emailMessageDTO"
		action="${ctx}/message/inboxSearch.do">
		<common:errorTable path="emailMessageDTO" />
		<div class="emptyBlock">
	    <div id="topBar">
		   <table align="left" border="0" cellpadding="0" cellspacing="0" style="background-color:#EFEFEF;width:100%" >
		      <tr>
		         <td>
		            <input type="button" value="创建新邮件" onclick="location.href='${ctx}/message/initEmail.do'"/>
		            <input type="button" value="删除"/>
		            <input type="button" value="彻底删除"/>
		            <input type="button" value="转发"/>
		         </td>
		      </tr>
		   </table>
		</div>
	    <div class="emptyBlock">
	    <div>${html}</div>
	    <div class="emptyBlock">
	    <div id="topBar">
		   <table align="left" border="0" cellpadding="0" cellspacing="0" style="background-color:#EFEFEF;width:100%" >
		      <tr>
		         <td>
		            <input type="button" value="创建新邮件" onclick="location.href='${ctx}/message/initEmail.do'"/>
		            <input type="button" value="删除"/>
		            <input type="button" value="彻底删除"/>
		            <input type="button" value="转发"/>
		         </td>
		      </tr>
		   </table>
		</div>
	</form:form>
</body>
</html>