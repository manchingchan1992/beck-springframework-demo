<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>OA-MSG-001</title>
</head>
<body>
	<div class="boxTitleBar">
		<div class="contenttitle">新建邮件</div>
	</div>
	<form:form commandName="messageDto" action="${ctx}/message/createEamil.do">
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
	    <input class="submit" type="submit" value="Submit" id="submitButton" style="display: none" />
		<div id="buttonArea">
		   <div class="buttonmenubox_R">
		      <a class="buttondefault" onclick="validateAndSubmitForm('1')" href="#">发送</a>
		      <a class="button" onclick="validateAndSubmitForm('1')" href="#">存草稿</a>
		      <a class="button" onclick="validateAndSubmitForm('1')" href="#">关闭</a>
		   </div>
		</div>
	</form:form>
</body>
</html>