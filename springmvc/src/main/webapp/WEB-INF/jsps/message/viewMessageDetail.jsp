<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>OA-MSG-004</title>
</head>
<body>
	<div class="boxTitleBar">
		<div class="contenttitle">查看邮件</div>
	</div>
	<form:form commandName="emailMessageDTO" >
	    <common:errorTable path="emailMessageDTO"/>
	    <table class="contentTableBody1" cellspacing="0">
	       <tr class="contentTableRow1">
	          <td class="contentLableTd">发件人</td>
	          <td class="contentInputSingleTd">
	             <form:input path="messageFrom" cssClass="standardInputText" cssStyle="width:100%" readonly="true"/>
	          </td>
	       </tr>
	       <tr class="contentTableRow1">
	          <td class="contentLableTd">收件人</td>
	          <td class="contentInputSingleTd">
	             <form:input path="messageTo" cssClass="standardInputText" cssStyle="width:100%" readonly="true"/>
	          </td>
	       </tr>
	       <tr class="contentTableRow1">
	          <td class="contentLableTd">抄送</td>
	          <td class="contentInputSingleTd">
	             <form:input path="messageCc" cssClass="standardInputText" cssStyle="width:100%" readonly="true"/>
	          </td>
	       </tr>
	       <tr class="contentTableRow1">
	          <td class="contentLableTd">主题</td>
	          <td class="contentInputSingleTd">
	             <form:input path="messageTitle" cssClass="standardInputText" cssStyle="width:100%" readonly="true"/>
	          </td>
	       </tr>
	       <tr class="contentTextAreaRow">
	          <td class="contentLableTd">正文</td>
	          <td class="contentInputSingleTd">
	             <form:textarea path="messageContent" cssClass="standardInputText" cssStyle="width:100%;height:100%" readonly="true"/>
	          </td>
	       </tr>
	    </table>
	    <div class="emptyBlock"></div> 
		<div id="buttonArea">
		   <div class="buttonmenubox_R">
		      <a class="buttondefault" href="${back}">返回</a>
		   </div>
		</div>
	</form:form>
</body>
</html>