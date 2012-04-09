<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>
<%@ include file="/commons/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title>OA-USR-006</title>
<script type="text/javascript">
function assign(){
	var selected = document.getElementById('availableRoles');
	var asgned = document.getElementById('roles');
	if(selected.selectedIndex == 0){
		alert('请选择一项可分配角色');
		return ;
	}
	var text = selected.options[selected.selectedIndex].text;
	var value = selected.options[selected.selectedIndex].value;
	for(var i=0 ; i<asgned.options.length ; i++){
		if(asgned.options[i].text == text){
			alert('该角色已被分配，请重新选择');
			return ;
		}
	}
	asgned.options[asgned.options.length] = new Option(text,value);	
}

function deleteSelected(){
	var selectedFlag = false;
	var asgned = document.getElementById('roles');
	for(var i=0 ; i<asgned.options.length ; i++){
		var curr = asgned.options[i];
		if(curr.selected){
			selectedFlag = true;
			asgned.options[i] = null;
			i--;
		}
	}
	if(!selectedFlag){
		alert('请至少选择一项');
		return ;
	}
}

function deleteAll(){
	var asgned = document.getElementById('roles');
	for(var i=0 ; i<asgned.options.length ; i++){
		asgned.options[i] = null;
		i--;
	}
}
</script>
</head>
<body>
   	<div class="boxTitleBar">
		<div class="contenttitle">分配/修改用户角色</div>
	</div>
	<div class="emptyBlock">
    <form:form id="roleAssignForm" commandName="officeUserDto" action="${ctx}/authentication/usrMgmt/assignRolesToUserAccount.do" method="post">
		<common:errorTable path="officeUserDto"/>
        <div id="topBar">
		   <table align="left" border="0" cellpadding="0" cellspacing="0" style="background-color:#EFEFEF;width:100%" >
		      <tr>
		         <td>
		         <input type="button" value="保存" />&nbsp;
				 <input type="button" value="关闭" onclick="location.href='${ctx}/authentication/usrMgmt/initUsrMgmt.do'" /> 
		         </td>
		      </tr>
		   </table>
		</div>
		<div class="emptyBlock"></div>
	    <table id="t1" width="100%" class="standardTableForm" border="1" cellspacing="0" cellpadding="3">
        	<tr></tr>
			<tr >
				<td class="labelColumn" width="20%">用户名<span class="mandatoryField">*</span></td>
				<td width="30%">
					<form:input cssClass="standardInputText" path="loginId" maxlength="6" readonly="true"/>
				</td>
				<td class="labelColumn" width="20%">邮箱<span class="mandatoryField">*</span></td>
				<td width="30%">
					<form:input cssClass="standardInputText" path="email" maxlength="20" readonly="true"/>
				</td>
			</tr>
			<tr>
				<td class="labelColumn" width="20%">中文名</td>
				<td width="30%">
					<form:input cssClass="standardInputText" path="cnName" readonly="true"/>
				</td>
				<td class="labelColumn" width="20%">英文名</td>
				<td width="30%">
					<form:input cssClass="standardInputText" path="enName" readonly="true"/>
				</td>
			</tr>
            <tr>
				<td class="labelColumn" width="20%">用户状态 <span class="mandatoryField">*</span></td>
				<td width="30%">
					<form:select path="accountStatus" disabled="true">
					   <form:option value="">--- 请选择  ---</form:option>
					   <form:option value="AC">启用</form:option>
					   <form:option value="IN">禁止</form:option>
					</form:select>
				</td>
				<td><form:hidden path=""/> </td>
			</tr>
		</table>
		<div class="emptyBlock"></div>
		<table id="t1" width="100%" class="standardTableForm" border="1" cellspacing="0" cellpadding="0">
        	<tr></tr>
			<tr>
			   <td class="labelColumn" width="20%">可选角色</td>
			   <td width="30%">
			        <select id="availableRoles">
			            <option>--- 请选择  ---</option>
			            <c:forEach items="${availableRoles}" var="role">
			               <option value="${role.roleId}">${role.roleName }</option>
			            </c:forEach>
			        </select>
				</td>
				<td>
				   <input type="button" value="添加" onclick="assign()">
				</td>
			</tr>
			<tr>
			   <td class="labelColumn" width="20%">已分配角色</td>
			   <td width="30%">
			      <form:select path="roles" multiple="multiple" cssStyle="width:50%">
			         <form:options itemLabel="roleName" itemValue="roleId" items="${officeUserDto.roles}" htmlEscape="true" />
			      </form:select>
				</td>
				<td>
				   <input type="button" value="移除" onclick="deleteSelected()"><br/>
				   <input type="button" value="移除全部" onclick="deleteAll()">
				</td>
			</tr>
		</table>
		<div class="emptyBlock"></div>
        <div id="topBar">
		   <table align="left" border="0" cellpadding="0" cellspacing="0" style="background-color:#EFEFEF;width:100%" >
		      <tr>
		         <td>
		         <input type="button" value="保存" />&nbsp;
				 <input type="button" value="关闭" onclick="location.href='${ctx}/authentication/usrMgmt/initUsrMgmt.do'" /> 
		         </td>
		      </tr>
		   </table>
		</div>		
	</form:form>
</body>
</html>