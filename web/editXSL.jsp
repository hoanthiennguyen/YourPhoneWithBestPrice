<%-- 
    Document   : EditXSL
    Created on : Mar 29, 2020, 5:16:13 PM
    Author     : thien
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Best price</title>
        <link href="./assets/css/editXSL.css" rel="stylesheet" type="text/css">
        <link rel="icon" href="./assets/img/icon.png" type="image/x-icon">
        <script src="./assets/js/editXSL.js"></script>
    </head>
    <body>
        <a href="admin.jsp">Back to crawl page</a>
        <h1>Edit XSL page</h1>
        <label>Website</label> <select name="website" id="website" onchange="onChangeWebsite(this);">
            <c:forEach items="${sessionScope.WEBSITE}" var="dto" varStatus="counter">
                <option id="website${counter.count}" value="${dto}">${dto}</option>
            </c:forEach>

        </select><br/>
        <label>Subpage</label> <select name="subpage" id="subpage">
            <option value="">--Select--</option>
            <c:forEach items="${sessionScope.SUBPAGE}" var="dto">
                <option class="subpage" value="${dto.subpage}" website="${dto.website}">${dto.subpage}</option>
            </c:forEach>
        </select><br/>
        <div id="view">
            <div id="xslContainer">
                XSL <br/>
                <textarea id="xsl" placeholder="Remember, be nice!" cols="120" rows="30"></textarea><br/>

                <button onclick="onTestTransform();">Test transform</button>
                <button onclick="onSave();">Save</button>
                <button onclick="onReset();">Reset</button>
            </div>
            <div id="xmlContainer">
                XML transform <br/>
                <xmp id="xml" disabled="disabled" ></xmp>
            </div>

        </div>


    </body>
</html>
