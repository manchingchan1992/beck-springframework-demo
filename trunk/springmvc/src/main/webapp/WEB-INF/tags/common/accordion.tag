<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>
<div class="arrowlistmenu">
			<h3 class="menuheader expandable">个人办公</h3>
			<ul class="categoryitems">
			<li><a href="#">我的办公桌</a></li>
			<li><a href="#">公文维护</a></li>
			<li><a href="#">归档处理</a></li>
			<li><a href="#">通讯录</a></li>
			</ul>
			
			<h3 class="menuheader expandable">消息管理</h3>
			<ul class="categoryitems">
			<li><a href="${ctx}/message/initEmail.do">新建邮件</a></li>
			<li><a href="${ctx}/message/initInbox.do">收件箱</a></li>
			<li><a href="${ctx}/message/initOutbox.do">发件箱</a></li>
			<li><a href="${ctx}/message/initRecycle.do">垃圾箱</a></li>
			</ul>
			
			<h3 class="menuheader expandable">工作流程</h3>
			<ul class="categoryitems">
			<li><a href="${ctx}/workflow/initWorkflowSearch.do">流程管理</a></li>
			<li><a href="#">表单定义</a></li>
			</ul>
						
			<h3 class="menuheader expandable">组织管理</h3>
			<ul class="categoryitems">
			<li><a href="#">机构管理</a></li>
			<li><a href="#">人员管理</a></li>
			</ul>
			
			<security:authorize ifAnyGranted="ROLE_ADMIN">
			<h3 class="menuheader expandable">权限管理</h3>
			<ul class="categoryitems">
			<li><a href="#">模块管理</a></li>
			<li><a href="${ctx}/authentication/roleMgmt/initRoleMgmt.do">角色管理</a></li>
			<li><a href="${ctx}/authentication/usrMgmt/initUsrMgmt.do">用户管理</a></li>
			</ul>
			</security:authorize>
			
			<h3 class="menuheader expandable">系统管理</h3>
			<ul class="categoryitems">
			<li><a href="#">密码修改</a></li>
			<li><a href="#">代码定义</a></li>
			<li><a href="#">系统初始化</a></li>
			<li><a href="${ctx}/j_spring_security_logout">退出系统</a></li>
			</ul>
</div>