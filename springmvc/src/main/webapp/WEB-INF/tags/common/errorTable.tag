<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ attribute name="path" required="true" type="java.lang.String"%>

<spring:hasBindErrors name="${path}">
   <div id="errorContainer" class="errorContainer" >
      <h4>
         <img src="${imagePath}/icon/icon_warning.gif" alt="Warning">Error:
      </h4>
      <br clear="all">
      <ul>
         <c:forEach var="errmsg" items="${errors.allErrors }">
            <li>${errmsg.defaultMessage }</li>
         </c:forEach>
      </ul>
   </div>
</spring:hasBindErrors>
