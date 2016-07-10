<%-- 
    Document   : cartItemInfo
    Created on : 4 janv. 2016, 20:57:57
    Author     : doyenm
--%>

<%@page import="java.util.Iterator"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Your cart item</title>
        <link href="bootstrap-3.3.6-dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <c:forEach items="${deliverResult}" var="result">
            ${result}
        </c:forEach>

        <h1>Your cart : </h1>
        <form action="./CartItemServlet" method="POST">
            <input type="submit" name="action" value="Display" class="btn btn-success btn btn-success"/>

            <table border="1" class="table-striped">
                <th>Title</th>
                <th>Year</th>
                    <c:forEach items="${cartAll}" var="dvd">
                    <tr>
                        <td>${dvd.title}</td>
                        <td>${dvd.year}</td>

                    </tr>
                </c:forEach>
            </table>   
            <input type="submit" name="action" value="Deliver" class="btn btn-success btn btn-success">
        </form>
    </body>
</html>
