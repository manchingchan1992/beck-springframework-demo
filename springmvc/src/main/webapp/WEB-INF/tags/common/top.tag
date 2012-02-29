<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<table width="100%" border="0" cellpadding="0" cellspacing="0"
	background="skin/images/frame/topbg.gif">
	<tr>
		<td width='20%' height="60"><img
			src="${ctx}/images/common/logo.gif" />
		</td>
		<td width='80%' align="right" valign="bottom">
			<table width="750" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td align="right" height="26" style="padding-right: 10px; line-height: 26px;">
					你好：<span class="username"></span>，欢迎试用在线办公系统
						 [<a href="" target="_blank">修改密码</a>] 
						 [<a href="" target="_top">注销退出</a>]&nbsp;
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>