<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>

<div id="fade" class="black_overlay"></div>
<div id="light" class="white_content">
	<table class="contentTableBody1" cellspacing="0">
	    <tr>
			<td class="note">
				Please select the mode to submit the workflow(s).
			</td>
		</tr>
		<tr>
			<td align="left">
				<input id="single" type="radio" value="single" name="type" checked/>Submit Only One Workflow.
			</td>
		</tr>
		<tr>	
			<td align="left">
				<input id="multiple" type="radio" value="multiple" name="type" />Submit Batch Workflows.
			</td>
		</tr>
	</table>
	
	<div id="buttonArea">
		<div class="buttonmenubox_R">	
		  <a href="#" class="button" onclick="document.getElementById('light').style.display = 'none'; document.getElementById('fade').style.display = 'none';showAllObject();">OK</a>
		  <a href="#" class="button" onclick="document.getElementById('light').style.display = 'none';document.getElementById('fade').style.display = 'none';showAllObject();">Cancel</a>		 		 
		</div>
	</div>
</div>

