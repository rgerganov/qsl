<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@page session="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
  <head>
    <title>Upload QSL card</title>
  </head>
  <body>
    <h3>Upload new QSL card</h3>
    <c:if test="${not empty errorMessage}">
      <div><c:out value="${errorMessage}"/></div>
    </c:if>
    <form action="/admin/upload" method="post" enctype="multipart/form-data">
      <p>Image: <input type="file" name="file"></p>
      <p>
        <input type="submit" value="Upload">
        <a href="/admin"><input type="button" name="cancel" value="Cancel"/></a>
      </p>
    </form>
  </body>
</html>