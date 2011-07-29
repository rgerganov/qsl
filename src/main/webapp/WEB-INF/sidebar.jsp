<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<%
    UserService userService = UserServiceFactory.getUserService();
%>
<div id="sidebar">
  <ul>
    <li><a href="/admin">All cards</a></li>
    <li><a href="/admin/add">Add new card</a></li>
    <li><a href="/admin/upload">Upload file</a></li>
    <li><a href="<%=userService.createLogoutURL("/")%>">Logout</a></li>
  </ul>
</div>
