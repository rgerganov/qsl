<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@page session="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
  <head>
    <title>Admin panel</title>
  </head>
  <body>
    <form action="/admin" method="get">
      <input type="text" name="q" value="${param.q}">
      <input type="submit" value="Search">
    </form>
    <c:choose>
      <c:when test="${not empty page.cards}">
        <table>
        <c:forEach items="${page.cards}" var="card">
          <tr>
	        <td>${card.callsign}</td>
	        <td><a href="/admin/edit?id=${card.id}">Edit</a></td>
	        <td><a href="/admin/delete?id=${card.id}">Delete</a></td>
	      </tr>
	    </c:forEach>
	    </table>
        <%@ include file="paging.jsp" %>
      </c:when>
      <c:otherwise>
        <p>No QSL cards found</p>
      </c:otherwise>
    </c:choose>    
    <div><a href="/admin/add">Add new card</a></div>
  </body>
</html>