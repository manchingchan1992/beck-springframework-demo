<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>
<html>
<head>
<title>OA-WFL-001</title>
<script type="text/javascript">
function setOverlayDimension (){
	 var overlay = document.getElementById('fade');
	 var _11 = AJS.getWindowSize();
		if (AJS.isMozilla() || AJS.isOpera()) {
			AJS.setWidth(overlay, "100%");
		} else {
			AJS.setWidth(overlay, _11.w);
		}
		var _12 = Math.max(AJS.getScrollTop() + _11.h, AJS.getScrollTop()
				+ this.height);
		if (_12 < AJS.getScrollTop()) {
			AJS.setHeight(overlay, _12);
		} else {
			AJS.setHeight(overlay, AJS.getScrollTop() + _11.h);
		}
}

function hideAllObject(){
	var e = document.getElementsByTagName('select');

	if(e == null){
		return;
	}
	for(var i=0;i<e.length;i++){
		e[i].style.display = "none";
	}
}

function showAllObject(){
	var e = document.getElementsByTagName('select'); 
	if(e == null){
		return;
	}
	for(var i=0;i<e.length;i++){
		e[i].style.display = "block";
	}
}

function popUpFrame(lightDivId, fadeDivId) {
	document.getElementById(lightDivId).style.display = 'block';
	document.getElementById(fadeDivId).style.display = 'block'
}

function popUpSelector(){
	setOverlayDimension('fade');
	hideAllObject();
	
	popUpFrame('light','fade');
}
</script>
</head>
<body>
	<div class="boxTitleBar">
		<div class="contenttitle">工作流查询</div>
	</div>
	<div class="emptyBlock">
	<form:form id="workflowSearch" commandName="workflowEnquireDto"
		action="${ctx}/workflow/workflowSearch.do">
		<common:errorTable path="workflowEnquireDto" />
		<div class="emptyBlock">
	    <div id="topBar">
		   <table align="left" border="0" cellpadding="0" cellspacing="0" style="background-color:#EFEFEF;width:100%" >
		      <tr>
		         <td>
		            <input type="button" value="创建新工作流" onclick="popUpSelector();"/>
		            <input type="button" value="查询" />
		            <input type="button" value="重置" />
		            <input type="button" value="关闭" />
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
	    <jsp:include page="createModeOption_pop.jsp"></jsp:include>
	    <div class="emptyBlock">
	    <div id="topBar">
		   <table align="left" border="0" cellpadding="0" cellspacing="0" style="background-color:#EFEFEF;width:100%" >
		      <tr>
		         <td>
		            <input type="button" value="创建新工作流" onclick="popUpSelector();"/>
		            <input type="button" value="查询" />
		            <input type="button" value="重置" />
		            <input type="button" value="关闭" />
		         </td>
		      </tr>
		   </table>
		</div>
	</form:form>
</body>
</html>