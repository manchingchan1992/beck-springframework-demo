<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp" %>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome To Web OA System</title>
<script type="text/javascript">
function clear(){
	$('#j_username').val("");
	$('#j_password').val("");
}
</script>
</head>
<body>
<form action="${ctx}/j_spring_security_check" method="post">  
  	<div id="pageAll">
		<div class="pageTop">
		  <common:top></common:top>
		</div>
		<div id="pageMain">
		  <c:if test="${not empty param.login_error }">
		    <div class="errorContainer" id='mvcErrorContainer'>
		       <h4><img src="${imagePath}/icon/icon_warning.gif" alt="Warning">Error:</h4>
	           <br clear="all"/>
	           <ul>
                 <li><c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}" /></li>
               </ul>
            </div>
          </c:if>
		  <table border="0" width="100%" cellspacing="6" cellpadding="3" align="left">		
			<tr>
				<td colspan="0" align="center"></td>
			</tr>
			<tr>
				<td class="note" colspan="2">
					<spring:message code="cih.login.note" text="Enter your User ID and password. Do not disclose the password to anybody. You are strongly recommended to change your password at least every 6 months to enhance the security level of your account."/>
				</td>
			</tr>
			<tr>
				<td width="25%">
				<spring:message code="label.user.id" text="User ID"/>&nbsp;<span class="mandatoryField">*</span>
				</td>
				<td>
				<input type="text" size="20" maxlength="20" class="required" value="${param.j_username}" id="j_username" name="j_username" 
				 value='<c:if test="${not empty param.login_error}">${SPRING_SECURITY_LAST_USERNAME_KEY}</c:if>' />
				</td>
			</tr>	
			<tr>
				<td width="25%">
				<spring:message code="label.user.password" text="Password"/>&nbsp;<span class="mandatoryField">*</span>
				</td>
				<td >
				<input type="password" autocomplete="off" size="20" class="required" name="j_password" id="j_password" value="${param.j_password}"/>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div id="buttonArea" align="left">
						<div class="buttonmenubox_R">						
							    <input type="submit" id="submitButton" name="submtButton" style="display:none" />
								<a class="buttondefault" id="loginButton" onclick="if (!this.disabled){this.disabled=true;document.getElementById('submitButton').click();}"  href="#"><spring:message code="cih.ln.login" text="Login"/></a>
								<a class="button" id="clearButton" href="javascript:clear();"><spring:message code="gene.clear" text="Clear"/></a>
								<a class="button" id="clearButton" href="javascript:window.close();"><spring:message code="gene.cancel" text="Cancel"/></a>
						</div>
					</div>
				</td>
			</tr>
		</table>
		
		<div class="emptyBlock"></div>	
		</div>
	</div>
</form>
</body>
</html>