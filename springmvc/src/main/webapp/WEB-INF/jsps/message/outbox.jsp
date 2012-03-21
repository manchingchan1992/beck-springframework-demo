<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>
<html>
<head>
<title>OA-MSG-002</title>
<script type="text/javascript">
function emailSearch(){
	document.getElementById("submitButton").click();
}
</script>
</head>
<body>
	<div class="boxTitleBar">
		<div class="contenttitle">发件箱</div>
	</div>
	<div class="emptyBlock">
	<form:form commandName="emailMessageDTO"
		action="${ctx}/message/outboxSearch.do">
		<common:errorTable path="emailMessageDTO" />
		<table class="contentTableBody1" cellspacing="0">
			<tr class="contentTableRow1">
				<td class="contentLableTd">收件人</td>
				<td class="contentInputTd"><form:input path="messageTo" cssClass="standardInputText" /></td>
				<td class="contentLableTd">主题</td>
				<td class="contentInputTd"><form:input path="messageTitle" cssClass="standardInputText" /></td>
			</tr>
		</table>
		<div class="emptyBlock">
		<input class="submit" type="submit" value="Submit" id="submitButton" style="display:none"/>
	    <div id="buttonArea">
		   <div class="buttonmenubox_R">
		      <a class="buttondefault" onclick="emailSearch();" href="#">查询</a>
		      <a class="button" onclick="" href="#">重置</a>
		   </div>
		</div>
	    <div class="emptyBlock">
	    <div>${html}</div>
	</form:form>
</body>
</html>