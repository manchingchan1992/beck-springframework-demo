<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>
<html>
<head>
<title>OA-MSG-002</title>
<script type="text/javascript">
function deleteEmail(actionFlagStr){
	var selectCount = 0;
	var selections = document.getElementsByName("jmesaDto.select");
	if(selections != 'undefined'){
		for(var i=0 ; i<selections.length ; i++){
			if(selections[i].checked){
				selectCount++;
			}
		}
	}
	if(selectCount==0){
		alert('请至少选择一份邮件!');
		return ;
	}
	document.forms[0].action = '${ctx}/message/batchdelete.do';
	var actionFlag = document.getElementById('actionFlag');
	if(actionFlag != 'undefined' && actionFlagStr != ''){
		actionFlag.value = actionFlagStr;
	}
	
	if(confirm('确认删除?')){
		$('#outboxForm').ajaxSubmit({
			async:false,
			success:function(){
				var actionFlag = document.getElementById('actionFlag');
				if(actionFlag != 'undefined'){
					actionFlag.value = '10';
				}
				document.forms[0].action='${ctx}/message/outboxSearch.do';
				document.forms[0].submit();
			}
		});
	}
}

function forward(){
	var selectCount = 0;
	var selectedRec = '';
	var selections = document.getElementsByName("jmesaDto.select");
	if(selections != 'undefined'){
		for(var i=0 ; i<selections.length ; i++){
			if(selections[i].checked){
				selectedRec = selections[i].value;
				selectCount++;
				break;
			}
		}
	}
	if(selectCount==0){
		alert('请至少选择一份邮件!');
		return ;
	}
	
	if(selectedRec != ''){
		window.location.href = '${ctx}/message/forward.do?ref=' + selectedRec;
	}
}
</script>
</head>
<body>
	<div class="boxTitleBar">
		<div class="contenttitle">发件箱</div>
	</div>
	<div class="emptyBlock">
	<form:form id="outboxForm" commandName="emailMessageEnquireDto"
		action="${ctx}/message/outboxSearch.do">
		<common:errorTable path="emailMessageEnquireDto" />
		<div class="emptyBlock">
	    <div id="topBar">
		   <table align="left" border="0" cellpadding="0" cellspacing="0" style="background-color:#EFEFEF;width:100%" >
		      <tr>
		         <td>
		            <input type="button" value="创建新邮件" onclick="location.href='${ctx}/message/initEmail.do'"/>
		            <input type="button" value="删除" onclick="deleteEmail('40')"/>
		            <input type="button" value="彻底删除" onclick="deleteEmail('50')"/>
		            <input type="button" value="转发" onclick="forward()"/>
		         </td>
		      </tr>
		   </table>
		</div>
	    <div class="emptyBlock">
	    <div align="center">
	       <input type="hidden" id="actionFlag" name="actionFlag"> 
	       <common:jmesaScript actionFlagStr="30"></common:jmesaScript>
	    </div>
	    <div>${html}</div>
	    <div class="emptyBlock">
	    <div id="bottomBar">
		   <table align="left" border="0" cellpadding="0" cellspacing="0" style="background-color:#EFEFEF;width:100%" >
		      <tr>
		         <td>
		            <input type="button" value="创建新邮件" onclick="location.href='${ctx}/message/initEmail.do'"/>
		            <input type="button" value="删除" onclick="deleteEmail('40')"/>
		            <input type="button" value="彻底删除" onclick="deleteEmail('50')"/>
		            <input type="button" value="转发" onclick="forward()"/>
		         </td>
		      </tr>
		   </table>
		</div>
	</form:form>
</body>
</html>