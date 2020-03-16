<%-- 
    Document   : admin
    Created on : Mar 15, 2020, 4:04:26 PM
    Author     : thien
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Admin Page</h1>
        <p>${requestScope.INFO}</p>
        <form action="CrawlController" method="POST">
            Website <select name="website">
                <c:forEach items="${sessionScope.WEBSITE}" var="dto">
                    <option value="${dto.website}">${dto.website}</option>
                </c:forEach>
                
            </select><br/>
            Subpage <select name="subpage">
                <c:forEach items="${sessionScope.SUBPAGE}" var="dto">
                    <option value="${dto.subpage}">${dto.subpage}</option>
                </c:forEach>
            </select><br/>
            <input type="submit" value="Crawl">
        </form>
    </body>
</html>
