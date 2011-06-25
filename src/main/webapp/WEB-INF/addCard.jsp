<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@page session="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
  <head><title>Add new card</title></head>
  <body>
    <h3>Add new card</h3>
    <form action="/admin/add" method="post">
      <input type="text" name="callsign">
      <input type="text" name="frontImageUrl">
      <input type="text" name="backImageUrl">
      <input type="submit" value="Submit">
    </form>
  </body>
</html>