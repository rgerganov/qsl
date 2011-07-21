<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@page session="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>Upload image</title>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
    <link rel="stylesheet" type="text/css" href="../css/admin.css" />
  </head>
  <body>
    <div id="sidebar">
      <ul>
        <li><a href="/admin">All cards</a></li>
        <li><a href="/admin/add">Add new card</a></li>
        <li><a href="/admin/upload">Upload file</a></li>
        <li><a href="#">Logout</a></li>
      </ul>
    </div>
    <div id="main">
      <c:if test="${not empty errorMessage}">
          <p class="error"><c:out value="${errorMessage}"/></p>
      </c:if>
      <form action="/admin/upload" method="post" enctype="multipart/form-data">
        <label for="file" class="form-label">Image for upload (max 1MB):</label>
        <input type="file" id="file" name="file" class="form-input"/>
        <input type="submit" name="submit" class="form-button" value="Upload"/>
      </form>
    </div>
  </body>
</html>