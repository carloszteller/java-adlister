<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: carlosteller
  Date: 4/21/20
  Time: 10:31 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <title>Login</title>

    <%@ include file="partials/head.jsp" %>
</head>
<body>
    <%@ include file="partials/navbar.jsp" %>
    <form action="/login.jsp" method="POST">
        <p>
            <label for="username">
                Username: <input type="text" id="username" name="username">
            </label>
        </p>
        <p>
            <label for="password">
                Password: <input type="password" id="password" name="password">
            </label>
        </p>
        <p>
            <input type="submit" name="submit" value="Login">
        </p>
    </form>

    <c:if test="${not empty param.submit}">
        <c:choose>
            <c:when test="${param.username.equals('admin') && param.password.equals('password')}">
                <% response.sendRedirect("profile.jsp"); %>
            </c:when>
            <c:otherwise>
                <% response.sendRedirect("login.jsp"); %>
            </c:otherwise>
        </c:choose>
    </c:if>
</body>
</html>
