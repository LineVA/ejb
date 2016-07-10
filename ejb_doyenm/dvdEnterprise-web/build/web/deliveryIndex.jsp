<%-- 
    Document   : deliveryIndex
    Created on : 6 janv. 2016, 10:14:07
    Author     : doyenm
--%>

<%@page import="java.util.Iterator"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>All deliveries</title>
        <link rel="stylesheet" type="text/css" href="style.css">
        <link href="bootstrap-3.3.6-dist/css/bootstrap.min.css" rel="stylesheet">

    </head>
    <body>
        <h1>All deliveries</h1>
        <form action="./DeliveryServlet" method="POST">
            <input type="submit" name="action" value="Display" class="btn btn-success">
            <table border="1" class="table-striped">
                <thead>
                <th>Delivery's identifier</th>
                <th>Delivery's state</th>
                <th>Subdelivery's identifier</th>
                <th>DVD's id</th>
                <th>DVD's title</th>
                <th>DVD's supplier</th>
                <th>Subdelivery's state</th>
                </thead>
                <tbody
                    <c:forEach items="${allDeliveries}" var="deliv">
                        <tr>
                            <td>${deliv.idDelivery}</td>
                            <td class="${deliv.deliveryState}">${deliv.deliveryState}</td>
                        <tr>
                            <c:forEach items ="${deliv.subdeliveries}"  var="sub">
                                <td style="visibility:hidden"></td>
                                <td  style="visibility:hidden"></td>
                                <td>${sub.idSubdelivery}</td>
                                <td>${sub.dvd.idDVD}</td>
                                <td>${sub.dvd.title}</td>
                                <td>${sub.dvd.supplier.supplier}</td>
                                <td class="${sub.subdeliveryState}">${sub.subdeliveryState}</td>
                                <td><button type="submit" class="btn btn-success" name="action" value="sub_ready<c:out value="${sub.idSubdelivery}"/>">Change in READY</button></td>
                            </tr>
                            </tr>
                        </c:forEach>
                        <tr>
                            <td style="visibility:hidden"></td>
                            <td  style="visibility:hidden"></td>
                            <td style="visibility:hidden"></td>
                            <td  style="visibility:hidden"></td>
                            <td style="visibility:hidden"></td>
                            <td  style="visibility:hidden"></td>
                            <td style="visibility:hidden"></td>
                            <td><button type="submit" name="action" class="btn btn-success" value="gone<c:out value="${deliv.idDelivery}"/>">Change in GONE</button></td>
                        <tr>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>   
        </form>
    </body>                               
</html>
