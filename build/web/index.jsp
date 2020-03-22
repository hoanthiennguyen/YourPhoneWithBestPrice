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
            function loadListBrand(search) {
                let result = "";
                var xhttp = new XMLHttpRequest();
                xhttp.onreadystatechange = function () {
                    if (this.readyState === 4 && this.status === 200) {
                        // Typical action to be performed when the document is ready:
                        console.log(xhttp.responseText);
                        let suggestions = xhttp.responseText.split(",")
                                .filter(str => str !== "")
                                .map(str => search ? search + " " + str : str);
                        console.log(suggestions);
                        autocomplete(document.getElementById("search"), suggestions);
                    }
                };
                let controller = "SearchAJAXController?search=";
                if(search !== null)
                    controller += search;
                xhttp.open("GET", controller, true);
                xhttp.send();
                return result;
            }
            window.onload = e => {

                loadListBrand("");
                document.getElementById("search").onkeydown = onGetSuggestion;
            };
            function onGetSuggestion(e){
                console.log(e.keyCode);
                //ENTER is pressed
                if(e.keyCode === 32){
                    let search = e.target.value;
                    loadListBrand(search);
                }
            }
            
        </script>
    </head>
    <body>
        
        <form autocomplete="off" action="SearchController">
            <h2>Best price</h2>
            <div class="autocomplete" style="width:300px;">
                <input name="search" id="search" style="width:300px;">
            </div>
            <input type="submit" value="Search">
        </form>
        <div>${requestScope.NAMES}</div>
    </body>
</html>
