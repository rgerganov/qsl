<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
  <head><title>QSL cards</title></head>
  <body>
    <h3>Heading</h3>
    <c:forEach items="${cards}" var="card">
      <c:out value="${card.frontImageUrl}"/>
    </c:forEach>
  </body>
</html>