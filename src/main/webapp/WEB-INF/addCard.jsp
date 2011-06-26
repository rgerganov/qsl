<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@page session="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
  <head>
    <title>Add new QSL card</title>
  </head>
  <body>
    <h3>Add new QSL card</h3>
    <form action="/admin/add" method="post">
      <p>Callsign: <input type="text" name="callsign"></p>
      <p>Front Image URL: <input type="text" name="frontImageUrl"></p>
      <p>Back Image URL: <input type="text" name="backImageUrl"></p>
      <p><input type="submit" value="Add"></p>
    </form>
  </body>
</html>