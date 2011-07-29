<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@page session="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>Add new QSL card</title>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
    <link rel="stylesheet" type="text/css" href="../css/admin.css" />
  </head>
  <body>
    <%@ include file="sidebar.jsp" %>
    <div id="main">
      <c:if test="${not empty errorMessage}">
        <p class="error"><c:out value="${errorMessage}"/></p>
      </c:if>
      <form action="/admin/add" method="post">
        <label for="callsign" class="form-label">Callsign:</label>
        <input type="text" id="callsign" name="callsign" class="form-input" value="${param.callsign}"/>
        <label for="frontImageUrl" class="form-label">Front image URL:</label>
        <input type="text" id="frontImageUrl" name="frontImageUrl" class="form-input" value="${param.frontImageUrl}"/>
        <label for="backImageUrl" class="form-label">Back image URL:</label>
        <input type="text" id="backImageUrl" name="backImageUrl" class="form-input" value="${param.backImageUrl}"/>
        <input type="submit" name="submit" class="form-button" value="Add"/>
      </form>
    </div>
  </body>
</html>