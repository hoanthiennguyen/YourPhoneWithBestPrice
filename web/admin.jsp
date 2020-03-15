<%-- 
    Document   : admin
    Created on : Mar 15, 2020, 4:04:26 PM
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
        <h1>Admin Page</h1>
        <p>${requestScope.INFO}</p>
        <form action="CrawlController" method="POST">
            Website <select name="website">
                <option value="https://hoanghamobile.com/dien-thoai-di-dong-c14.html">
                    https://hoanghamobile.com/dien-thoai-di-dong-c14.html
                </option>
                <option value="https://bachlongmobile.com/">
                    https://bachlongmobile.com/
                </option>
            </select><br/>
            Subpage <select name="subpage">
                <option value="dien-thoai-xiaomi.html">dien-thoai-xiaomi.html</option>
                <option value="dt-nokia.html">dt-nokia.html</option>
            </select><br/>
            <input type="submit" value="Crawl">
        </form>
    </body>
</html>
