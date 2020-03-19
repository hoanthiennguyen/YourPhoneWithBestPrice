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
        <title>JSP Page</title>
        <link href = "https://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css"
              rel = "stylesheet">
        <script src = "https://code.jquery.com/jquery-1.10.2.js"></script>
        <script src = "https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
        <script>
            function createXmlHttpObj() {
                let result = null;
                try {
                    result = new XmlHttpRequest();
                } catch (e) {
                    result = new ActiveXObject("Microsoft.XMLHTTP");
                }
                return result;
            }
            function loadListBrand(subpage) {
                let result = "";
                var xhttp = new XMLHttpRequest();
                xhttp.onreadystatechange = function () {
                    if (this.readyState === 4 && this.status === 200) {
                        // Typical action to be performed when the document is ready:
                        console.log(xhttp.responseText);
                        let brandList = xhttp.responseText.split(",");
                        $("#search").autocomplete({
                            source: brandList
                        });
                    }
                };
                xhttp.open("GET", subpage, true);
                xhttp.send();
                return result;
            }
            window.onload = e => {

                loadListBrand("SearchBrandController");
            };

        </script>
    </head>
    <body>
        <form action="SearchController">
            <input name="search" id="search">
            <input type="submit" value="Search">
        </form>
    </body>
</html>
