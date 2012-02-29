<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>
<script type="text/javascript">
function showHide(id){
	var obj = document.getElementById(id);
		if(obj.style.display == 'block' || obj.style.display =='')
			obj.style.display = 'none';
		else
			obj.style.display = 'block';
}
</script>
<table width='100%' height="100%" border='0' cellspacing='0' cellpadding='0'>
	<tr>
		<td style='padding-left: 3px; padding-top: 8px' valign="top">
			<dl class='bitem'>
				<dt onClick='showHide("item1")'>
					<b>个人办公</b>
				</dt>
				<dd style='display: block' class='sitem' id='item1'>
				   <common:menuitem itemName="我的办公桌" href="#" />
				   <common:menuitem itemName="公文维护" href="#" />
				   <common:menuitem itemName="归档处理" href="#" />
				   <common:menuitem itemName="通信录" href="#" />
				</dd>
			</dl>
			<!-- Item2 -->
			<dl class='bitem'>
				<dt onClick='showHide("item2")'>
					<b>工作流程</b>
				</dt>
				<dd style='display: block' class='sitem' id='item2'>
				   <common:menuitem itemName="流程管理" href="#" />
				   <common:menuitem itemName="表单定义" href="#" />
				</dd>
			</dl>
			<!-- item 3 -->
			<dl class='bitem'>
				<dt onClick='showHide("item3")'>
					<b>组织管理</b>
				</dt>
				<dd style='display: block' class='sitem' id='item3'>
				   <common:menuitem itemName="机构管理" href="#" />
				   <common:menuitem itemName="人员管理" href="#" />
				</dd>
			</dl>
			<!-- item 3 -->
			<dl class='bitem'>
				<dt onClick='showHide("item4")'>
					<b>权限管理</b>
				</dt>
				<dd style='display: block' class='sitem' id='item4'>
				   <common:menuitem itemName="模块管理" href="#" />
				   <common:menuitem itemName="角色管理" href="#" />
				   <common:menuitem itemName="用户管理" href="#" />
				</dd>
			</dl>
			<!-- item 4 -->
			<dl class='bitem'>
				<dt onClick='showHide("item5")'>
					<b>系统管理</b>
				</dt>
				<dd style='display: block' class='sitem' id='item5'>
				   <common:menuitem itemName="密码修改" href="#" />
				   <common:menuitem itemName="代码定义" href="#" />
				   <common:menuitem itemName="系统初始化" href="#" />
				</dd>
			</dl>
		</td>
	</tr>
</table>