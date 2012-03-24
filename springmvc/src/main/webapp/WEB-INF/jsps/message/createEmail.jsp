<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>OA-MSG-001</title>
</head>
<body>
	<div class="boxTitleBar">
		<div class="contenttitle">新建邮件</div>
	</div>
	<div class="emptyBlock">
	<form:form commandName="emailMessageDTO" action="${ctx}/message/sendEmail.do">
	    <common:errorTable path="emailMessageDTO"/>
	    <div id="topBar">
		   <table align="left" border="0" cellpadding="0" cellspacing="0" style="background-color:#EFEFEF;width:100%" >
		      <tr>
		         <td>
		            <input type="submit" value="发送"/>
		            <input type="button" value="存草稿" onclick="saveAsDraft();"/>
		            <input type="button" value="关闭" onclick="location.href='${ctx}/message/initInbox.do'"/>
		         </td>
		      </tr>
		   </table>
		</div>
		<div class="emptyBlock"></div> 
	    <table class="contentTableBody1" cellspacing="0">
	       <tr class="contentTableRow1">
	          <td class="contentLableTd">收件人</td>
	          <td class="contentInputSingleTd">
	             <form:input path="messageTo" cssClass="standardInputText" cssStyle="width:100%"/>
	          </td>
	       </tr>
	       <tr class="contentTableRow1">
	          <td class="contentLableTd">抄送</td>
	          <td class="contentInputSingleTd">
	             <form:input path="messageCc" cssClass="standardInputText" cssStyle="width:100%"/>
	          </td>
	       </tr>
	       <tr class="contentTableRow1">
	          <td class="contentLableTd">主题</td>
	          <td class="contentInputSingleTd">
	             <form:input path="messageTitle" cssClass="standardInputText" cssStyle="width:100%"/>
	          </td>
	       </tr>
	       <tr class="contentTextAreaRow">
	          <td class="contentLableTd">正文</td>
	          <td class="contentInputSingleTd">
	             <form:textarea path="messageContent" cssClass="standardInputText" cssStyle="width:100%;height:100%"/>
	          </td>
	       </tr>
	    </table>
	    <div class="emptyBlock"></div> 
	    <div id="bottomBar">
		   <table align="left" border="0" cellpadding="0" cellspacing="0" style="background-color:#EFEFEF;width:100%" >
		      <tr>
		         <td>
		            <input type="submit" value="发送"/>
		            <input type="button" value="存草稿" onclick="saveAsDraft();"/>
		            <input type="button" value="关闭" onclick="location.href='${ctx}/message/initInbox.do'"/>
		         </td>
		      </tr>
		   </table>
		</div>
	</form:form>
</body>
</html>