<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>OA-MSG-004</title>
<script type="text/javascript">
function singleDelete(sysRefMsg ,actionFlagStr){
	if(actionFlagStr != '' && confirm('确认删除该邮件？')){
		$.ajax({
			async:false,
			url:'${ctx}/message/singledelete.do?ref=' + sysRefMsg +'&actionFlag=' + actionFlagStr,
			success:function (){
				window.location.href = '${back}'
			},
			error:function(){
				alert('删除失败!');
			}
		});
	}
}
</script>
</head>
<body>
	<div class="boxTitleBar">
		<div class="contenttitle">查看邮件</div>
	</div>
	<div class="emptyBlock">
	<form:form commandName="emailMessageDTO" >
	    <common:errorTable path="emailMessageDTO"/>
	    <div id="topBar">
		   <table align="left" border="0" cellpadding="0" cellspacing="0" style="background-color:#EFEFEF;width:100%" >
		      <tr>
		         <td>
		            <input type="button" value="<<返回" onclick="location.href='${back}'"/>
		            <input type="button" value="回复" onclick="location.href='${ctx}/message/reply.do?ref=${emailMessageDTO.sysRefMessage}'"/>
		            <input type="button" value="回复全部" onclick="location.href='${ctx}/message/reply.do?ref=${emailMessageDTO.sysRefMessage}&type=all'"/>
		            <input type="button" value="转发" onclick="location.href='${ctx}/message/forward.do?ref=${emailMessageDTO.sysRefMessage}'"/>
		            <input type="button" value="删除" onclick="singleDelete('${emailMessageDTO.sysRefMessage}', '40')"/>
		            <input type="button" value="彻底删除" onclick="singleDelete('${emailMessageDTO.sysRefMessage}' ,'50')"/>
		         </td>
		      </tr>
		   </table>
		</div>
	    <div class="emptyBlock"></div>
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
	    <div id="bottomBar">
		   <table align="left" border="0" cellpadding="0" cellspacing="0" style="background-color:#EFEFEF;width:100%" >
		      <tr>
		         <td>
		            <input type="button" value="<<返回" onclick="location.href='${back}'"/>
		            <input type="button" value="回复" onclick="location.href='${ctx}/message/reply.do?ref=${emailMessageDTO.sysRefMessage}'"/>
		            <input type="button" value="回复全部" onclick="location.href='${ctx}/message/reply.do?ref=${emailMessageDTO.sysRefMessage}&type=all'"/>
		            <input type="button" value="转发" onclick="location.href='${ctx}/message/forward.do?ref=${emailMessageDTO.sysRefMessage}'"/>
		            <input type="button" value="删除" onclick="singleDelete('${emailMessageDTO.sysRefMessage}','40')"/>
		            <input type="button" value="彻底删除" onclick="singleDelete('${emailMessageDTO.sysRefMessage}','50')"/>
		         </td>
		      </tr>
		   </table>
		</div>
	</form:form>
</body>
</html>