<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>OA-MSG-001</title>
<script type="text/javascript">
function sendEmail(){
	document.getElementById('submitButton').click();
}
</script>
</head>
<body>
	<div class="boxTitleBar">
		<div class="contenttitle">新建邮件</div>
	</div>
	<form:form commandName="emailMessageDTO" action="${ctx}/message/sendEmail.do">
	    <common:errorTable path="emailMessageDTO"/>
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
	    <input class="submit" type="submit" value="Submit" id="submitButton" style="display:none"/>
		<div id="buttonArea">
		   <div class="buttonmenubox_R">
		      <a class="buttondefault" onclick="sendEmail();" href="#">发送</a>
		      <a class="button" onclick="saveAsDraft()" href="#">存草稿</a>
		      <a class="button" href="${ctx}/message/initInbox.do">关闭</a>
		   </div>
		</div>
	</form:form>
</body>
</html>