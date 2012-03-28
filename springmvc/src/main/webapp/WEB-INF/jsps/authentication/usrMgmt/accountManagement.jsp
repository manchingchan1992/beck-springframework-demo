<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>
<%@ include file="/commons/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>OA-USR-001</title>
</head>
<body>
   	<div class="boxTitleBar">
		<div class="contenttitle">用户管理</div>
	</div>
	<div class="emptyBlock">
   <form:form id="enquireForm" commandName="officeUserDto" action="${ctx}/authentication/userMagement/searchOfficeUsers.do" method="post">
        <common:errorTable path="officeUserDto"/>
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
				<td class="labelColumn" width="20%">用户名</td>
				<td width="30%">
					<form:input cssClass="normalTextField" path="loginId" />
				</td>
				<td class="labelColumn" width="20%">邮箱</td>
				<td width="30%">
					<form:input cssClass="normalTextField" path="email" />
				</td>
			</tr>
			<tr>
				<td class="labelColumn" width="20%">中文名</td>
				<td width="30%">
					<form:input cssClass="normalTextField" path="cnName" />
				</td>
				<td class="labelColumn" width="20%">英文名</td>
				<td width="30%">
					<form:input cssClass="normalTextField" path="enName" />
				</td>
			</tr>
            <tr>
				<td class="labelColumn" width="20%">账户状态</td>
				<td width="30%">
					<form:select path="accountStatus">
					   <form:option value="">--- 请选择  ---</form:option>
					   <form:option value="AC">启用</form:option>
					   <form:option value="IN">禁止</form:option>
					</form:select>
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
		    
		</div>
	</form:form>
</body>
</html>