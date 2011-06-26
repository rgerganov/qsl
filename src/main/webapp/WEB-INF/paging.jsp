<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${page.startPage < page.endPage}">
<div>
  <c:forEach var="x" begin="${page.startPage}" end="${page.endPage}" step="1">
    <c:choose>
      <c:when test="${x == currentPage}">${x}</c:when>
      <c:otherwise>
        <c:url value="${request.contextPath}" var="url">
          <c:param name="q" value="${param.q}"/>
          <c:param name="p" value="${x}"/>
        </c:url>
        <a href="${url}">${x}</a>
      </c:otherwise>
    </c:choose>
  </c:forEach>
</div>
</c:if>