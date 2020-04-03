<%-- 
    Document   : index
    Created on : Mar 13, 2020, 8:00:57 PM
    Author     : thien
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Best price</title>
        <link href="./assets/css/index.css" rel="stylesheet" type="text/css">
        <link rel="icon" href="./assets/img/icon.png" type="image/x-icon">
        <script src="./assets/js/index.js"></script>
        <script src="./assets/js/myIndex.js"></script>
    </head>
    <body>

        <form autocomplete="off" onsubmit="return onSearch();">
            <h2>Best price</h2>
            <div class="autocomplete" style="width:300px;">
                <input name="search" id="search" style="width:300px;">
            </div>
            <input type="submit" value="Search">
        </form>
        <div id="maincontent"></div>
    </body>
</html>
