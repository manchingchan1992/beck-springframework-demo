<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="sitemesh-decorator"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page"
	prefix="sitemesh-page"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>
<html>
<head>
<jsp:include page="../commons/meta.jsp"></jsp:include>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><sitemesh-decorator:title></sitemesh-decorator:title>
</title>
<sitemesh-decorator:head></sitemesh-decorator:head>
</head>
<body>
	<div id="pageAll">
		<div class="pageTop">
		  <common:top></common:top>
		</div>
		<div id="pageMain">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td class="leftMenuStyle" width="168" valign="top">
						<common:menu></common:menu>
					</td>
					<td valign="top" width="10">&nbsp;</td>
					<td valign="top" align="left">
					   <sitemesh-decorator:body></sitemesh-decorator:body>
					</td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>