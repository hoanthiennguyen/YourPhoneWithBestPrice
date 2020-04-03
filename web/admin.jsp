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
        <title>Best price</title>
        <link href="./assets/css/admin.css" rel="stylesheet" type="text/css">
        <link rel="icon" href="./assets/img/icon.png" type="image/x-icon">
        <script src="./assets/js/common.js"></script>
        <script src="./assets/js/admin.js"></script>
    </head>
    <body>
        <h1>Crawl page</h1>
        <p>${requestScope.INFO}</p>
        <form action="CrawlController" method="POST" onsubmit="return onCrawl(this);">
            <label>Website</label> <select name="website" onchange="onChangeWebsite(this);">
                <c:forEach items="${sessionScope.WEBSITE}" var="dto" varStatus="counter">
                    <option id="website${counter.count}" value="${dto}">${dto}</option>
                </c:forEach>

            </select><br/>
            <label>Subpage</label> <select name="subpage">
                <option value="">--Select--</option>
                <option value="*all*">All</option>
                <c:forEach items="${sessionScope.SUBPAGE}" var="dto">
                    <option class="subpage" value="${dto.subpage}" website="${dto.website}">${dto.subpage}</option>
                </c:forEach>
            </select><br/>
            <input type="submit" value="Crawl">
        </form>
        <a href="addNewWebsite.jsp">Add new website</a>
        <a href="editXSL.jsp">Edit xsl</a>
        <a href="SummaryController">Summary page</a>
    </body>
</html>
