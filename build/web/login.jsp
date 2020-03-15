<%-- 
    Document   : login
    Created on : Mar 13, 2020, 8:44:18 PM
    Author     : thien
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Login Page</h1>
        <form action="LoginController" method="POST">
            Username <input name="username" type="text"><br>
            Password <input name="password" type="text"><br>
            <input type="submit" value="Login">
        </form>
    </body>
</html>
