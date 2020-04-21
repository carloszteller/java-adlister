<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: carlosteller
  Date: 4/21/20
  Time: 1:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Pick Color</title>
</head>
<body>
    <form action="bgcolor.jsp" method="POST">
        <p>
            <label for="color">
                Enter the name your favorite HTML color:
                <input type="text" name="color" id="color">
            </label>
        </p>
        <p>
            <input type="submit" name="submit" value="Submit">
        </p>
    </form>
</body>
</html>
