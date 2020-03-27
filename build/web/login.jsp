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
        <title>Best price</title>
        <link href="./assets/css/login.css" rel="stylesheet" type="text/css">
        <link rel="icon" href="./assets/img/icon.png" type="image/x-icon">
    </head>
    <body>
        <h1>Login Page</h1>
        <form action="LoginController" method="POST">
            <label>Username</label> <input name="username" type="text"><br>
            <label>Password</label> <input name="password" type="password"><br>
            <input type="submit" value="Login">
        </form>
    </body>
</html>
