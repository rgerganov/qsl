<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@page session="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
  <head>
    <title>QSL cards</title>
  </head>
  <body>
    <form action="/main" method="get">
      <input type="text" name="q" value="${param.q}">
      <input type="submit" value="Search">
    </form>
    <c:choose>
      <c:when test="${not empty page.cards}">
	    <c:forEach items="${page.cards}" var="card">
	      <img src="${card.frontImageUrl}" alt="${card.callsign}" width="415" height="265"/>
	    </c:forEach>
	    <%@ include file="paging.jsp" %>
      </c:when>
      <c:otherwise>
        <p>No QSL cards found</p>
      </c:otherwise>
    </c:choose>    
  </body>
</html>