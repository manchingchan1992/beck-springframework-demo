<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>
<%@ include file="/commons/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>OA-ROL-001</title>
</head>
<body>
   	<div class="boxTitleBar">
		<div class="contenttitle">系统角色管理</div>
	</div>
	<div class="emptyBlock">
    <form:form id="enquireForm" commandName="officeRoleDto" action="${ctx}/authentication/roleMgmt/searchOfficeRoles.do" method="post">
        <common:errorTable path="officeRoleDto"/>
        <div id="topBar">
		   <table align="left" border="0" cellpadding="0" cellspacing="0" style="background-color:#EFEFEF;width:100%" >
		      <tr>
		         <td>
		            <input type="button" value="创建角色" onclick="location.href='${ctx}/authentication/roleMgmt/preCreateOfficeRole.do'"/>
		            <input type="submit" value="查询"/>
		            <input type="reset" value="重置"/>
		         </td>
		      </tr>
		   </table>
		</div>
		<div class="emptyBlock"></div> 
		<table id="t1" width="100%" class="standardTableForm" border="0" cellspacing="0" cellpadding="0" style="background-color:#EFEFEF;width:100%">
        	<tr></tr>
			<tr >
				<td class="labelColumn" width="20%">角色代号</td>
				<td width="80%">
					<form:input cssClass="standardInputText" path="roleId" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="labelColumn">(角色代号必须以"ROLE_"开头)</span>
				</td>
			</tr>
			<tr>
			    <td class="labelColumn" width="20%">角色名称</td>
				<td width="80%">
					<form:input cssClass="standardInputText" path="roleName" />
				</td>
			</tr>
			<tr>
			    <td class="labelColumn" width="20%">角色描述</td>
				<td width="80%">
					<form:input cssClass="standardInputText" path="roleDesc" />
				</td>
			</tr>
			<tr>
			    <td class="labelColumn" width="20%">角色状态</td>
				<td width="80%">
					<form:select path="status">
					   <form:option value="">--- 请选择 ---</form:option>
					   <form:option value="AC">启用</form:option>
					   <form:option value="IN">禁止</form:option>
					</form:select>
				</td>
			</tr>
		</table>
		<div class="emptyBlock"></div>
        <div id="bottomBar">
		   <table align="left" border="0" cellpadding="0" cellspacing="0" style="background-color:#EFEFEF;width:100%" >
		      <tr>
		         <td>
		            <input type="button" value="创建角色" onclick="location.href='${ctx}/authentication/roleMgmt/preCreateOfficeRole.do'"/>
		            <input type="submit" value="查询" />
		            <input type="reset" value="重置" />
		         </td>
		      </tr>
		   </table>
		</div>	
		<div class="emptyBlock"></div>	
		<div>
		    ${html}
		</div>
	</form:form>
</body>
</html>