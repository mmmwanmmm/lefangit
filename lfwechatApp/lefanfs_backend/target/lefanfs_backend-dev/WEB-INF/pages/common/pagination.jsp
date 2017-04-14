<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp"%>

<c:set var="requestUrl" value="${param.requestUrl}"/>
<c:set var="paginationObjectName" value="${(not empty param.paginationObjectName)?(param.paginationObjectName):'pagination'}"/>
<c:set var="pageNoName" value="${(not empty param.pageNoName)?(param.pageNoName):'page'}"/>
<c:set var="pagination" value="${requestScope[paginationObjectName]}"/>
<c:set var="totalPages" value="${pagination.totalPages}"/>
<c:set var="curPage" value="${pagination.curPage}"/>
<c:set var="refreshDiv" value="${param.refreshDiv}"/>
<c:choose>
	<c:when test="${!fn:contains(requestUrl,'?') && !fn:contains(requestUrl,'&')}">
		<c:set var="requestUrl" value="${requestUrl}?${pageNoName}"/>
	</c:when>
	<c:otherwise>
		<c:set var="requestUrl" value="${requestUrl}&${pageNoName}"/>
	</c:otherwise>
</c:choose>
<c:if test="${totalPages > 1}">
	<!-- <div class="pagi"> -->
		<ul class="pagination">
			<c:choose>
				<c:when test="${curPage==1}">
					<li class="disabled"><a href="javascript:;">«</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="javascript:gotoPage('${requestUrl}=${curPage-1}','${refreshDiv}')" >« <span class="sr-only">(current)</span></a></li>
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${curPage <= 10 }">
					<c:forEach var="n" begin="1" end="${totalPages>10?10:totalPages}" step="1">
						<c:choose>
							<c:when test="${n==curPage}">
								<li class="active"><a href="javascript:;">${n}</a></li>
							</c:when>
							<c:otherwise>
								<li><a href="javascript:gotoPage('${requestUrl}=${n}','${refreshDiv}')" >${n}</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</c:when>
				<c:when test="${curPage+5 <= totalPages}">
					<c:forEach var="n" begin="${curPage-4}" end="${curPage+5}" step="1">
						<c:choose>
							<c:when test="${n==curPage}">
								<li class="active"><a href="javascript:;">${n}</a></li>
							</c:when>
							<c:otherwise>
								<li><a href="javascript:gotoPage('${requestUrl}=${n}','${refreshDiv}')" >${n}</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<c:forEach var="n" begin="${curPage-4}" end="${totalPages}" step="1">
						<c:choose>
							<c:when test="${n==curPage}">
								<li class="active"><a href="javascript:;">${n}</a></li>
							</c:when>
							<c:otherwise>
								<li><a href="javascript:gotoPage('${requestUrl}=${n}','${refreshDiv}')" >${n}</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</c:otherwise>
			</c:choose>
			<li><span>...</span></li>
			<c:choose>
				<c:when test="${curPage==totalPages}">
					<li class="disabled"><a href="javascript:;" >»</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="javascript:gotoPage('${requestUrl}=${curPage+1}','${refreshDiv}')" >»</a></li>
				</c:otherwise>
			</c:choose>
			<li>
				<div class="input-group" style="width:120px; float:left; margin-left:10px">
				  <input id="jumptoPageNo" name="jumptoPageNo" value="${curPage}" type="number" class="form-control">
				  <span class="input-group-btn">
					<a href="javascript:jumpTo('${requestUrl}=','${refreshDiv}');" class="btn btn-default" role="button">Go!</a>
				  </span>
				</div><!-- /input-group -->
			</li>
		</ul>
</c:if>
<script>
function jumpTo(pagingUrl,refreshDiv){
	var jumptoPageNo=parseInt(document.getElementById("jumptoPageNo").value);
	pagingUrl+=jumptoPageNo;
	return gotoPage(pagingUrl,refreshDiv);
}
</script>