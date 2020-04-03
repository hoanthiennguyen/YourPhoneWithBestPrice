/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function createXmlHttpObj() {
    let xhttp;
    if (window.ActiveXObject) {
        xhttp = new ActiveXObject("Msxml2.XMLHTTP");
    } else {
        xhttp = new XMLHttpRequest();
    }
    return xhttp;
}
function getAJAX(url, callback)
{
    var xhttp = createXmlHttpObj();
    xhttp.open("GET", url, true);
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            callback(xhttp);
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
    let controller = "SearchSuggestionController?search=";
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
    } else if (e.keyCode === 8 && (search.endsWith(" ") || search === "")) {
        loadSuggestions(search.trim(), autocomplete);
    }
}
function onSearch() {
    let input = document.getElementById("search").value;
    getAJAX("SearchController?search=" + input, xhttp => {
        let xml  = xhttp.responseXML;
        console.log(xml);
        getAJAX("./assets/xsl/clientPhones.xsl", xhttpXSL => {
            if (document.implementation && document.implementation.createDocument) {
                let xsl = xhttpXSL.responseXML;
                let xsltProcessor = new XSLTProcessor();
                xsltProcessor.importStylesheet(xsl);
                let resultDocument = xsltProcessor.transformToFragment(xml, document);
                let maincontent = document.getElementById("maincontent");
                if (maincontent.firstChild)
                    maincontent.removeChild(maincontent.firstElementChild);
                maincontent.appendChild(resultDocument);
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


