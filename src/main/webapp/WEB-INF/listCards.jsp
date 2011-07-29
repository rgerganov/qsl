<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@page session="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>Admin panel</title>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
    <link rel="stylesheet" type="text/css" href="../css/admin.css" />
  </head>
  <body>
    <%@ include file="sidebar.jsp" %>
    <div id="main">
      <form action="/admin" method="get">
        <input type="text" name="q" value="${param.q}"/>
        <input type="submit" value="Search"/>
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
    </div>
  </body>
</html>