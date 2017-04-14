<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<c:forEach items="${menuListDto }" var="top_item" >
	<c:if test="${top_item.depth==1 && top_item.isShow==1 }">
	  <div title="${top_item.menuName}">
		<ul class="easyui-tree">
			   <c:forEach items="${menuListDto }" var="item" >
			      <c:if test="${item.pid == top_item.id && item.isShow==1 }">
			        <c:choose>
			          <c:when test="${not empty item.menuUrl}">
			           <li><span><a onclick="javascript:addTab('${item.menuName}','${ctx}${item.menuUrl}','update');" href="javascript:;" title="${item.menuName}">${item.menuName}</a></span></li>
			          </c:when>
			          <c:otherwise>
			          <li data-options="state:'opened'">
			          <span>${item.menuName}</span>
			           <ul>
			             <c:forEach items="${menuListDto }" var="child_item" >
			               <c:if test="${child_item.pid == item.id && child_item.isShow==1 }">
			                <li><span><a onclick="javascript:addTab('${child_item.menuName}','${ctx}${child_item.menuUrl}','update');" href="javascript:;" title="${child_item.menuName}">${child_item.menuName}</a></span></li>
			               </c:if>
			             </c:forEach>
			             </ul>
			          </li>
			          </c:otherwise>
			        </c:choose>
			      </c:if>
			   </c:forEach>
		 </ul>
	  </div>
	</c:if>
</c:forEach>