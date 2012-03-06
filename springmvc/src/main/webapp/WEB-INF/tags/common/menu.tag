<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>
<script type="text/javascript">
	function showHide(id) {
		$("#" + id).slideToggle("slow");
	}
</script>
<table width='100%' height="100%" border='0' cellspacing='0'
	cellpadding='0'>
	<tr>
		<td style='padding-left: 3px; padding-top: 8px' valign="top">
			<!-- item2 -->
			<dl class='bitem'>
				<dt onClick='showHide("item2")'>
					<b>个人办公</b>
				</dt>
				<dd style='display: block;' id='item2'>
					<common:menuitem href="#" itemName="我的办公桌"></common:menuitem>
					<common:menuitem href="#" itemName="公文维护"></common:menuitem>
					<common:menuitem href="#" itemName="归档处理"></common:menuitem>
					<common:menuitem href="#" itemName="通讯录"></common:menuitem>
				</dd>
			</dl>
			
			<!-- item4 -->
			<dl class='bitem'>
				<dt onClick='showHide("item4")'>
					<b>消息管理</b>
				</dt>
				<dd style='display: block;' id='item4'>
					<common:menuitem href="${ctx}/message/initInbox.do" itemName="收件箱"></common:menuitem>
					<common:menuitem href="#" itemName="发件箱"></common:menuitem>
					<common:menuitem href="#" itemName="垃圾箱"></common:menuitem>
					<common:menuitem href="#" itemName="聊天记录"></common:menuitem>
				</dd>
			</dl>
			
			<!-- item5 -->
			<dl class='bitem'>
				<dt onClick='showHide("item5")'>
					<b>工作流程</b>
				</dt>
				<dd style='display: block;' id='item5'>
					<common:menuitem href="#" itemName="流程管理"></common:menuitem>
					<common:menuitem href="#" itemName="表单定义"></common:menuitem>
				</dd>
			</dl>
			
			<!-- item6 -->
			<dl class='bitem'>
				<dt onClick='showHide("item6")'>
					<b>组织管理</b>
				</dt>
				<dd style='display: block;' id='item6'>
					<common:menuitem href="#" itemName="机构管理"></common:menuitem>
					<common:menuitem href="#" itemName="人员管理"></common:menuitem>
				</dd>
			</dl>
			
			<!-- item7 -->
			<dl class='bitem'>
				<dt onClick='showHide("item7")'>
					<b>权限管理</b>
				</dt>
				<dd style='display: block;' id='item7'>
					<common:menuitem href="#" itemName="模块管理"></common:menuitem>
					<common:menuitem href="#" itemName="角色管理"></common:menuitem>
					<common:menuitem href="#" itemName="用户管理"></common:menuitem>
				</dd>
			</dl>
			
			<!-- item8 -->
			<dl class='bitem'>
				<dt onClick='showHide("item8")'>
					<b>系统管理</b>
				</dt>
				<dd style='display: block;' id='item8'>
					<common:menuitem href="#" itemName="密码修改"></common:menuitem>
					<common:menuitem href="#" itemName="代码定义"></common:menuitem>
					<common:menuitem href="#" itemName="系统初始化"></common:menuitem>
				</dd>
			</dl>
		</td>
	</tr>
</table>