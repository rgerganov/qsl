<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@page session="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
  <head>
    <title>Edit QSL card</title>
  </head>
  <body>
    <h3>Edit QSL card</h3>
    <form action="/admin/edit" method="post">
      <input type="hidden" name="id" value="${card.id}">
      <p>Callsign: <input type="text" name="callsign" value="${card.callsign}"></p>
      <p>Front Image URL: <input type="text" name="frontImageUrl" value="${card.frontImageUrl}"></p>
      <p>Back Image URL: <input type="text" name="backImageUrl" value="${card.backImageUrl}"></p>
      <p><input type="submit" value="Update"></p>
    </form>
  </body>
</html>