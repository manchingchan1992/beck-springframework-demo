<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>
<%@ include file="/commons/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>OA-USR-007</title>
</head>
<body>
   	<div class="boxTitleBar">
		<div class="contenttitle">用户信息确认</div>
	</div>
	<div class="emptyBlock"></div>
    <table class="contentTableBody1" cellspacing="0" width="100%">
		<tr>
			<td class="note">
			   <common:message code="message.account.update.succ" text="You have successfully updated the office user account" needSuccSign="true"></common:message>
			</td>
		</tr>
	</table>
	<div class="emptyBlock"></div>
	<div id="topBar">
	<table align="left" border="0" cellpadding="0" cellspacing="0" style="background-color:#EFEFEF;width:100%" >
		<tr>
		   <td>
		      <input type="button" value="列印" onclick=""/>&nbsp;
		      <input type="reset" value="返回"  onclick="location.href='${ctx}/authentication/usrMgmt/initUsrMgmt.do'"/>&nbsp;
		   </td>
		</tr>
	</table>
	</div>
	<div class="emptyBlock"></div>
	<table class="contentTableBody1" cellspacing="0" >
	   <tr><td colspan="4"></td></tr>
	   <tr class="contentTableRow1" valign="middle">
		   <td width="20%" class="contentLableTd">用户ID</td>
		   <td colspan="3" class="contentViewTd"><c:out value="${officeUserDto.userRecId}" escapeXml="true"></c:out></td>
		</tr>
		<tr class="contentTableRow1">
	      <td colspan="4" class="contentLableTd">
	      <strong><br>Part I &nbsp;-&nbsp;用户基本信息 </strong>
		  </td>
	   </tr>
	   <tr class="contentTableRow1">
		   <td width="20%" class="contentLableTd">用户名</td>
		   <td width="30%" class="contentViewTd"><c:out value="${officeUserDto.loginId}" escapeXml="true"></c:out></td>
		   <td width="20%" class="contentLableTd">邮箱</td>
		   <td width="30%" class="contentViewTd"><c:out value="${officeUserDto.email}" escapeXml="true"></c:out></td>
		</tr>
		<tr class="contentTableRow1">
		   <td width="20%" class="contentLableTd">中文名</td>
		   <td width="30%" class="contentViewTd"><c:out value="${officeUserDto.cnName}" escapeXml="true"></c:out></td>
		   <td width="20%" class="contentLableTd">英文名</td>
		   <td width="30%" class="contentViewTd"><c:out value="${officeUserDto.enName}" escapeXml="true"></c:out></td>
		</tr>
		<tr class="contentTableRow1">
	      <td colspan="4" class="contentLableTd">
	      <strong><br>Part II &nbsp;-&nbsp;用户状态信息 </strong>
		  </td>
	    </tr>
	    <tr class="contentTableRow1">
		   <td width="20%" class="contentLableTd">状态</td>
		   <td colspan="3" class="contentViewTd">
		      <c:if test="${officeUserDto.accountStatus == 'AC'}"><c:out value="启用" escapeXml="true"></c:out></c:if>
		      <c:if test="${officeUserDto.accountStatus == 'IN'}"><c:out value="禁止" escapeXml="true"></c:out></c:if>
		   </td>
		</tr>
		<tr class="contentTableRow1">
		   <td width="20%" class="contentLableTd">角色</td>
		   <td colspan="3" class="contentViewTd">
		      <c:forEach items="${officeUserDto.roles}" var="role">
		         <c:out value="${role.roleName}"></c:out>/
		      </c:forEach>
		   </td>
		</tr>
	</table>
	<div class="emptyBlock"></div>
	<div id="bottomBar">
	<table align="left" border="0" cellpadding="0" cellspacing="0" style="background-color:#EFEFEF;width:100%" >
		<tr>
		   <td>
		      <input type="button" value="列印" onclick=""/>&nbsp;
		      <input type="reset" value="返回"  onclick="location.href='${ctx}/authentication/usrMgmt/initUsrMgmt.do'"/>&nbsp;
		   </td>
		</tr>
	</table>
	</div>
</body>
</html>