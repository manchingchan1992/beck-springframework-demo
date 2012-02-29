<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="sitemesh-decorator"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="sitemesh-page"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>
<html>
<head>
<jsp:include page="../commons/meta.jsp"></jsp:include>
<sitemesh-decorator:head></sitemesh-decorator:head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><sitemesh-decorator:title></sitemesh-decorator:title></title>
<script type="text/javascript">
function showHide(id){
	
}
</script>
</head>
<body>
   <div id="page">
      <div class="pageTop">
         <common:top></common:top>
      </div>
      
      <div id="pageMain">
			<table border="0" cellspacing="0" cellpadding="0" width="100%" >
				<tr>
					<td valign="top" width="168">
					<div id="leftMenuArea" class="leftMenuBackground">			
						<common:menu></common:menu>			
					</div>	
					</td>
					<td valign="top" width="10">&nbsp;</td>					
					<td valign="top">
					  	<sitemesh-decorator:body></sitemesh-decorator:body>
					</td>
				</tr>
			</table>
      </div>
   </div>
   <div id="foot">   
      <common:foot></common:foot>
   </div>
</body>
</html>