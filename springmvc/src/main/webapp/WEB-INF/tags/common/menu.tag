<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>
<table width='99%' height="100%" border='0' cellspacing='0' cellpadding='0'>
	<tr>
		<td style='padding-left: 3px; padding-top: 8px' valign="top">
			<dl class='bitem'>
				<dt onClick='showHide("item1")'>
					<b>基本信息管理</b>
				</dt>
				<dd style='display: block' class='sitem' id='item1'>
				   <common:menuitem itemName="个人信息" href="#" />
				   <common:menuitem itemName="素质拓展管理" href="#" />
				</dd>
			</dl>
		</td>
	</tr>
</table>