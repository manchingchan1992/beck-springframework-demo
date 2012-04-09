<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>
<%@ include file="/commons/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title>OA-USR-0505</title>
</head>
<body>
   	<div class="boxTitleBar">
		<div class="contenttitle">重置密码</div>
	</div>
	<div class="emptyBlock"></div>
    <form:form id="resetPasswordForm" commandName="officeUserDto" action="${ctx}/authentication/usrMgmt/resetPassword.do" method="post">
        <common:errorTable path="officeUserDto"/>
        <div id="topBar">
		   <table align="left" border="0" cellpadding="0" cellspacing="0" style="background-color:#EFEFEF;width:100%" >
		      <tr>
		         <td>
		            <input type="submit" value="保存" />
		            <input type="reset" value="重置"/>
		            <input type="button" value="返回" onclick="location.href='${back}'"/>
		         </td>
		      </tr>
		   </table>
		</div>
		<div class="emptyBlock"></div> 

		<table id="t1" width="100%" class="standardTableForm" border="1" cellspacing="0" cellpadding="0">
        	<tr></tr>
			<tr >
				<td class="labelColumn" width="20%">新密码 <span class="mandatoryField">*</span></td>
				<td width="30%">
					<form:password path="password" size="30" maxlength="8"/>
				</td>
			</tr>
			<tr>
				<td class="labelColumn" width="20%">确认密码 <span class="mandatoryField">*</span></td>
				<td width="30%">
					<form:password path="confirmPassword" size="30" maxlength="8"/>
				</td>
			</tr>
		</table>
		<div class="emptyBlock"></div> 
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
		   <tr>
		      <td><b>Point(s) to note:</b><br> 
	              Length of password must be 8 characters consisting of both alphabetical and numeric characters.
	          </td>
	       </tr>
	    </table>
	    <div class="emptyBlock"></div> 
	    <div id="bottomBar">
		   <table align="left" border="0" cellpadding="0" cellspacing="0" style="background-color:#EFEFEF;width:100%" >
		      <tr>
		         <td>
		            <input type="submit" value="保存" />
		            <input type="reset" value="重置"/>
		            <input type="button" value="返回" onclick="location.href='${back}'"/>
		         </td>
		      </tr>
		   </table>
		</div>
	</form:form>
</body>
</html>