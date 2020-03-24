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
                let xhttp;
                if (window.ActiveXObject) {
                    xhttp = new ActiveXObject("Msxml2.XMLHTTP");
                } else {
                    xhttp = new XMLHttpRequest();
                }
                return xhttp;
            }
            function loadXMLDoc(filename, callback)
            {
                var xhttp = createXmlHttpObj();
                xhttp.open("GET", filename, true);
                xhttp.onreadystatechange = function () {
                    if (this.readyState === 4 && this.status === 200) {
                        callback(xhttp.responseXML);
                    }
                };
                xhttp.send();
                return xhttp;
            }

            function loadSuggestions(search, callback) {
                var xhttp = createXmlHttpObj();
                xhttp.onreadystatechange = function () {
                    if (this.readyState === 4 && this.status === 200) {
                        // Typical action to be performed when the document is ready:
                        console.log(xhttp.responseText);
                        let suggestions = xhttp.responseText.split(",")
                                .map(str => search ? search + " " + str : str);
                        callback(document.getElementById("search"), suggestions);
                    }
                };
                let controller = "SearchAJAXController?search=";
                if (search !== null)
                    controller += search;
                xhttp.open("GET", controller, true);
                xhttp.send();
            }
            function onGetSuggestion(e) {
                let search = e.target.value;
                //space is pressed
                if (e.keyCode === 32) {
                    
                    loadSuggestions(search.trim(), autocomplete);
                }
                else if(e.keyCode === 8 && (search.endsWith(" ") || search === "")){
                    loadSuggestions(search.trim(), autocomplete);
                }
            }
            function onSearch() {
                let input = document.getElementById("search").value;
                loadXMLDoc("SearchController?search=" + input, xml => {
                    loadXMLDoc("./assets/xsl/clientPhones.xsl", xsl => {
                        if (document.implementation && document.implementation.createDocument) {
                            let xsltProcessor = new XSLTProcessor();
                            xsltProcessor.importStylesheet(xsl);
                            let resultDocument = xsltProcessor.transformToFragment(xml, document);
                            document.getElementById("maincontent").appendChild(resultDocument);
                        }
                    });
                });
                return false;
            }
            window.onload = e => {

                loadSuggestions("", autocomplete);
                let inputSearch = document.getElementById("search");
                inputSearch.onkeyup = onGetSuggestion;

            };
            
            

        </script>
    </head>
    <body>

        <form autocomplete="off" onsubmit="return onSearch();">
            <h2>Best price</h2>
            <div class="autocomplete" style="width:300px;">
                <input name="search" id="search" style="width:300px;">
            </div>
            <input type="submit" value="Search">
        </form>
        <div id="maincontent">${requestScope.NAMES}</div>
    </body>
</html>
