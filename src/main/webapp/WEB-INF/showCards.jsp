<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@page session="false" %>
<%@page trimDirectiveWhitespaces="true"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>QSL cards</title>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
    <link rel="stylesheet" type="text/css" href="css/global.css" />
  <link media="screen" rel="stylesheet" href="css/colorbox.css" />
  <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.6.1/jquery.min.js"></script>
  <script type="text/javascript" src="colorbox/jquery.colorbox.js"></script>
  <script type="text/javascript">
    $(document).ready(function(){
      // select all links which have 'rel' attribute starting with 'card'
      $("a[rel^='card']").colorbox();
      $(".frontLink").click(function(e) {
        $(this).parents(".img").children(".front").css('display', 'block');
        $(this).parents(".img").children(".back").css('display', 'none');
        return false;
      });
      $(".backLink").click(function(e) {
        $(this).parents(".img").children(".front").css('display', 'none');
        $(this).parents(".img").children(".back").css('display', 'block');
        return false;
      });
    });
  </script>
  </head>
  <body>
    <div class="search" align="center">
      <form action="/main" method="get">
        <input type="text" name="q" value="${param.q}"/>
        <input type="submit" value="Search"/>
      </form>
    </div>
    <c:choose>
      <c:when test="${not empty page.cards}">
        <div id="main">
        <c:forEach items="${page.cards}" var="card">
          <div class="img">
            <a href="${card.frontImageUrl}" class="front" rel="card-${card.callsign}" title="${card.callsign}-front">
              <img src="${card.frontImageUrl}" title="${card.callsign}" alt="${card.callsign}" width="415" height="265"/>
            </a>
            <a href="${card.backImageUrl}" class="back" rel="card-${card.callsign}" title="${card.callsign}-back" style="display:none">
              <img src="${card.backImageUrl}" title="${card.callsign}" alt="${card.callsign}" width="415" height="265"/>
            </a>
            <div class="desc">
              <a class="frontLink" href="#">&larr;</a>
              <span class="callsign">${card.callsign}</span>
              <a class="backLink" href="#">&rarr;</a>
            </div>
          </div>
        </c:forEach>
        <div style="clear: both"></div>
        </div>
        <%@ include file="paging.jsp" %>
      </c:when>
      <c:otherwise>
        <div id="main"><p>No QSL cards found</p></div>
      </c:otherwise>
    </c:choose>
    <div id="footer" align="center">
      <p class="copyright">Copyright &copy; 2011 <a href="http://www.lz2zg.com">LZ2ZG</a>. All rights reserved.</p>
    </div>
  </body>
</html>