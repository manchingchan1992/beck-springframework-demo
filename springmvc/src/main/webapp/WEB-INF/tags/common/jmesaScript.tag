<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ attribute name="actionFlagStr" required="false"%>
<%@ attribute name="action"  required="false"%>
<script type="text/javascript">
function onInvokeAction(id){
   var actionFlag = document.getElementById('actionFlag');
   var actionFlagStr = '${actionFlagStr}';
   if(actionFlag != 'undefined' && actionFlagStr != ""){
       actionFlag.value = actionFlagStr;
	   setExportToLimit(id, '');
	   createHiddenInputFieldsForLimitAndSubmit(id);
   }
}
function onInvokeExportAction(id) {
	var parameterString = createParameterStringForLimit(id);
	location.href = '${action}' + parameterString;
}
</script>