<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>
<%@ include file="/commons/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>OA-AUTH-001</title>
</head>
<body>
   	<div class="boxTitleBar">
		<div class="contenttitle">用户管理</div>
	</div>
	<div class="emptyBlock">
   <form:form id="enquireForm" commandName="internalUserEnquiryCriteria" action="${ctx}/userMagement/searchInternalUsers.do" method="post">
        <common:errorTable path="emailMessageDTO"/>
        <div id="topBar">
		   <table align="left" border="0" cellpadding="0" cellspacing="0" style="background-color:#EFEFEF;width:100%" >
		      <tr>
		         <td>
		            <input type="submit" value="创建新用户"/>
		            <input type="button" value="查询" onclick="saveAsDraft();"/>
		            <input type="button" value="重置" onclick="location.href='${ctx}/message/initInbox.do'"/>
		         </td>
		      </tr>
		   </table>
		</div>
		<div class="emptyBlock"></div> 
		<table id="t1" width="100%" class="standardTableForm" border="1" cellspacing="0" cellpadding="0">
        	<tr></tr>
			<tr >
				<td class="labelColumn" width="20%">Login ID</td>
				<td width="30%">
					<form:input cssClass="normalTextField" path="loginId" />
				</td>
				<td class="labelColumn" width="20%">Full Name</td>
				<td width="30%">
					<form:input cssClass="normalTextField" path="fullName" />
				</td>
			</tr>
			<tr>
				<td class="labelColumn" width="20%">Site</td>
				<td width="30%">
					<form:input cssClass="normalTextField" path="site" />
				</td>
				<td class="labelColumn" width="20%">Team</td>
				<td width="30%">
					<form:input cssClass="normalTextField" path="team" />
				</td>
			</tr>
            <tr>
				<td class="labelColumn" width="20%">Token</td>
				<td width="30%">
					<form:input cssClass="normalTextField" path="token" />
				</td>
			</tr>
		</table>
		<div class="emptyDiv"></div>
        <div id="topBar">
		   <table align="left" border="0" cellpadding="0" cellspacing="0" style="background-color:#EFEFEF;width:100%" >
		      <tr>
		         <td>
		            <input type="submit" value="创建新用户"/>
		            <input type="button" value="查询" onclick="saveAsDraft();"/>
		            <input type="button" value="重置" onclick="location.href='${ctx}/message/initInbox.do'"/>
		         </td>
		      </tr>
		   </table>
		</div>	
		<div class="emptyDiv"></div>	
		<div>
		    <a href="${accountMaintenanceURL}">Test</a>
		</div>
	</form:form>
</body>
</html>