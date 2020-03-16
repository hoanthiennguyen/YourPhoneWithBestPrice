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
        <script>
            
            function displayOnlySubpageFromWebsite(website){
                let arr = Array.from(document.getElementsByClassName("subpage"))
                arr.forEach(option => {
                    if (option.getAttribute("website") !== website)
                        option.style.display = "none";
                    else
                        option.style.display = "block";
                });
            }
            window.onload = (e) =>{
                let firstwebsite = document.getElementById("website1").value;
                displayOnlySubpageFromWebsite(firstwebsite);
            };
            function onChangeWebsite(e) {
                let website = e.value;
                displayOnlySubpageFromWebsite(website);
            }
        </script>
    </head>
    <body>
        <h1>Admin Page</h1>
        <p>${requestScope.INFO}</p>
        <form action="CrawlController" method="POST">
            Website <select name="website" onchange="onChangeWebsite(this);">
                <c:forEach items="${sessionScope.WEBSITE}" var="dto" varStatus="counter">
                    <option id="website${counter.count}" value="${dto.website}">${dto.website}</option>
                </c:forEach>

            </select><br/>
            Subpage <select name="subpage">
                <option>--Select--</option>
                <c:forEach items="${sessionScope.SUBPAGE}" var="dto">
                    <option class="subpage" value="${dto.subpage}" website="${dto.website}">${dto.subpage}</option>
                </c:forEach>
            </select><br/>
            <input type="submit" value="Crawl">
        </form>
    </body>
</html>
