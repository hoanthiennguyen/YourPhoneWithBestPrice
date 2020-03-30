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
        <title>JSP Page</title>
        <link href="./assets/css/editXSL.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <label>Website</label> <select name="website" onchange="onChangeWebsite(this);">
            <c:forEach items="${sessionScope.WEBSITE}" var="dto" varStatus="counter">
                <option id="website${counter.count}" value="${dto}">${dto}</option>
            </c:forEach>

        </select><br/>
        <div id="view">
            <div id="xsl">
                XSL <br/>
                <textarea placeholder="Remember, be nice!" cols="50" rows="30"></textarea><br/>
                <button>Test transform</button>
                <button>Save</button>
                <button>Discard</button>
            </div>
            <div id="xml">
                XML transform <br/>
                <textarea disabled="disabled" cols="50" rows="30"></textarea>
            </div>
            
        </div>
        
        
    </body>
</html>
