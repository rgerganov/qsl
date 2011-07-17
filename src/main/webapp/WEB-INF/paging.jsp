<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${page.startPage < page.endPage}">
<div id="paging" align="center">
  <ul>
  <c:if test="${currentPage > 1}">
    <li>
    <c:url value="${request.contextPath}" var="prev">
      <c:param name="q" value="${param.q}"/>
      <c:param name="p" value="${currentPage-1}"/>
    </c:url>
    <a href="${prev}">Previous</a>
    </li>
  </c:if>
  <c:forEach var="x" begin="${page.startPage}" end="${page.endPage}" step="1">
    <li>
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
    </li>
  </c:forEach>
  <c:if test="${currentPage < page.endPage}">
    <li>
    <c:url value="${request.contextPath}" var="next">
      <c:param name="q" value="${param.q}"/>
      <c:param name="p" value="${currentPage+1}"/>
    </c:url>
    <a href="${next}">Next</a>
    </li>
  </c:if>
  </ul>
</div>
</c:if>