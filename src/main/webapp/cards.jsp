<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@page session="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
  <head><title>QSL cards</title></head>
  <body>
    <h3>Heading</h3>
    <c:forEach items="${cards}" var="card">
      <img src="${card.frontImageUrl}" alt="${card.callsign}" width="415" height="265"/>
    </c:forEach>
  </body>
</html>