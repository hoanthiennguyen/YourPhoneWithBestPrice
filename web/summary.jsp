<%-- 
    Document   : summary
    Created on : Mar 31, 2020, 10:30:05 AM
    Author     : thien
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <link rel="icon" href="./assets/img/icon.png" type="image/x-icon">
         <link href="./assets/css/summary.css" rel="stylesheet" type="text/css">
        <title>Best price</title>
    </head>
    <body>
        <h1>Summary page</h1>
        <table>
            <thead>
                <tr>
                    <td>No</td>
                    <td>Category</td>
                    <td>Average price</td>
                    <td>Number of websites</td>
                </tr>
            </thead>
        </tr>

        <tbody>
            <c:forEach items="${requestScope.CATEGORIES}" var="dto" varStatus="counter">
                <tr>
                    <td>${counter.count}</td>
                    <td>${dto.category}</td>
                    <td>${dto.averagePrice}</td>
                    <td>${dto.numOfWebsites}</td>
                </tr>
            </c:forEach>
        </tbody>

    </table>

</body>
</html>
