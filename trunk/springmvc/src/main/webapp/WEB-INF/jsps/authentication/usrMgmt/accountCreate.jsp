<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>
<%@ include file="/commons/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>OA-USR-004</title>
</head>
<body>
   	<div class="boxTitleBar">
		<div class="contenttitle">创建新用户</div>
	</div>
	<div class="emptyBlock">
    <form:form id="createUserAcountForm" commandName="officeUserDto" action="${ctx}/authentication/usrMgmt/createUserAccount.do" method="post">
        <common:errorTable path="officeUserDto"/>
        <div id="topBar">
		   <table align="left" border="0" cellpadding="0" cellspacing="0" style="background-color:#EFEFEF;width:100%" >
		      <tr>
		         <td>
		         <input type="button" value="分配/修改用户角色" onclick="location.href='${ctx}/authentication/usrMgmt/initUsrRoleAsgn.do'"/>&nbsp;
				 <input type="submit" value="保存" />&nbsp;
				 <input type="reset" value="重置"  />&nbsp;
				 <input type="button" value="关闭" onclick="location.href='${ctx}/authentication/usrMgmt/initUsrMgmt.do'" /> 
		         </td>
		      </tr>
		   </table>
		</div>
		<div class="emptyBlock">
	    <table id="t1" width="100%" class="standardTableForm" border="1" cellspacing="0" cellpadding="3">
        	<tr></tr>
			<tr >
				<td class="labelColumn" width="20%">用户名<span class="mandatoryField">*</span></td>
				<td width="30%">
					<form:input cssClass="standardInputText" path="loginId" maxlength="6"/>
				</td>
				<td class="labelColumn" width="20%">邮箱<span class="mandatoryField">*</span></td>
				<td width="30%">
					<form:input cssClass="standardInputText" path="email" maxlength="20"/>
				</td>
			</tr>
			<tr>
				<td class="labelColumn" width="20%">中文名</td>
				<td width="30%">
					<form:input cssClass="standardInputText" path="cnName" />
				</td>
				<td class="labelColumn" width="20%">英文名</td>
				<td width="30%">
					<form:input cssClass="standardInputText" path="enName" />
				</td>
			</tr>
            <tr>
				<td class="labelColumn" width="20%">用户状态 <span class="mandatoryField">*</span></td>
				<td width="30%">
					<form:select path="accountStatus">
					   <form:option value="">--- 请选择  ---</form:option>
					   <form:option value="AC">启用</form:option>
					   <form:option value="IN">禁止</form:option>
					</form:select>
				</td>
				<td><form:hidden path=""/> </td>
			</tr>
		</table>
		<div class="emptyBlock"></div>
		<table id="t2" width="100%" class="standardTableForm" border="1" cellspacing="0" cellpadding="3">
        	<tr></tr>
			<tr >
				<td class="labelColumn" width="20%">密码<span class="mandatoryField">*</span></td>
				<td width="80%">
					<form:password path="password" size="30" maxlength="8"/>
				</td>
			</tr>
			<tr>
				<td class="labelColumn" width="20%">确认密码<span class="mandatoryField">*</span></td>
				<td width="80%">
					<form:password path="confirmPassword" size="30" maxlength="8"/>
				</td>
			</tr>
		</table>
		<div class="emptyBlock" ></div>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
		   <tr>
		      <td><b>Point(s) to note:</b><br> 
	              Length of password must be 8 characters consisting of both alphabetical and numeric characters.
	          </td>
	       </tr>
	    </table>
	    <div class="emptyBlock" />
	    <div id="bottomBar">
		   <table align="left" border="0" cellpadding="0" cellspacing="0" style="background-color:#EFEFEF;width:100%" >
		      <tr>
		         <td>
		         <input type="button" value="分配/修改用户角色" onclick="location.href='${ctx}/authentication/usrMgmt/initUsrRoleAsgn.do'"/>&nbsp;
				 <input type="submit" value="保存" />&nbsp;
				 <input type="reset" value="重置"  />&nbsp;
				 <input type="button" value="关闭" onclick="location.href='${ctx}/authentication/usrMgmt/initUsrMgmt.do'" /> 
		         </td>
		      </tr>
		   </table>
		</div>
	</form:form>
</body>
</html>