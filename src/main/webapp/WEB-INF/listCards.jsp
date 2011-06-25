<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@page session="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
  <head><title>Admin panel</title></head>
  <body>
    <form action="/admin" method="get">
      <input type="text" name="q" value="${param.q}">
      <input type="submit" value="Search">
    </form>
    <table>
    <c:forEach items="${page.cards}" var="card">
      <tr>
        <td>${card.callsign}</td>
        <td><a href="#">Edit</a></td>
        <td><a href="/admin/delete?id=${card.id}">Delete</a></td>
      </tr>
    </c:forEach>
    </table>
    <p><a href="/admin/add">Add new card</a></p>
    <c:forEach var="x" begin="${page.startPage}" end="${page.endPage}" step="1">
      <c:choose>
        <c:when test="${x == currentPage}">${x}</c:when>
        <c:otherwise>
          <c:url value="/admin" var="url">
            <c:param name="q" value="${param.q}"/>
            <c:param name="p" value="${x}"/>
          </c:url>
          <a href="${url}">${x}</a>
        </c:otherwise>
      </c:choose>
    </c:forEach>
  </body>
</html>